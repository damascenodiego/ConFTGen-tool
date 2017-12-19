/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.examples.wizard.pages;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.yakindu.sct.examples.wizard.ExampleActivator;
import org.yakindu.sct.examples.wizard.preferences.ExamplesPreferenceConstants;
import org.yakindu.sct.examples.wizard.service.ExampleData;
import org.yakindu.sct.examples.wizard.service.ExampleWizardConstants;
import org.yakindu.sct.examples.wizard.service.IExampleService;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * 
 * @author t00manysecretss
 * @author andreas muelder - Replace Image Gallery with Browser widget
 * 
 */

public class SelectExamplePage extends WizardPage
		implements ExampleWizardConstants, ISelectionChangedListener, SelectionListener, IPropertyChangeListener {

	private static final String PRO_BUNDLE = "com.yakindu.sct.domain.c";
	private static final String PRO_UPDATE_SITE = "https://info.itemis.com/yakindu/statecharts/pro/";
	@Inject
	private IExampleService exampleService;
	private TableViewer viewer;
	private ExampleData selection;
	private Browser browser;
	private MessageArea messageArea;

	/** ID of example to be installed */
	private String exampleIdToInstall;

	public SelectExamplePage() {
		super(SELECT_PAGE_TITLE);
		setTitle(SELECT_PAGE_TITLE);
		setDescription(SELECT_PAGE_DESCRIPTION);
		setPageComplete(false);
		ExampleActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		ExampleActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite root = new Composite(parent, SWT.NONE);
		root.setLayout(new GridLayout(1, true));
		createUpdateGroup(root);
		SashForm container = new SashForm(root, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		createTreeViewer(container);
		createDetailsPane(container);
		container.setWeights(new int[] { 1, 2 });
		setControl(container);
	}

	private void createUpdateGroup(Composite root) {
		messageArea = new MessageArea(root);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(messageArea);
		messageArea.addSelectionListener(this);
		messageArea.hide();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initAsync();
		} else {
			viewer.setInput(null);
			browser.setUrl("about:blank");
		}

	}

	private boolean revealExamplesAutomatically() {
		return (exampleIdToInstall != null) && (!exampleService.exists() || !exampleService.isUpToDate(null));
	}

	private void initAsync() {
		try {
			getWizard().getContainer().run(true, false, new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					init(monitor);
				}
			});

			if (revealExamplesAutomatically()) {
				Display.getCurrent().asyncExec(new Runnable() {
					@Override
					public void run() {
						messageArea.button.setEnabled(false);
						revealExamples();
					}
				});
			}
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init(final IProgressMonitor monitor) {
		if (!exampleService.exists()) {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					messageArea.showDownload();
					messageArea.getParent().layout(true);
				}
			});
		} else if (!exampleService.isUpToDate(monitor)) {
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					setInput(monitor);
					messageArea.showUpdate();
					messageArea.getParent().layout(true);
				}
			});
		} else {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					setInput(monitor);
				}
			});
		}

	}

	protected void setInput(final IProgressMonitor monitor) {
		final List<ExampleData> input = exampleService.getExamples(new NullProgressMonitor());

		messageArea.hide();
		viewer.setInput(input);
		// explicit layouting required for Unix systems
		viewer.getControl().getParent().getParent().layout(true);

		filterAndSelectExampleToInstall(viewer, input);
	}

	protected void filterAndSelectExampleToInstall(TableViewer viewer, List<ExampleData> input) {
		final ExampleData exampleToInstall = Iterables.find(input, new Predicate<ExampleData>() {
			@Override
			public boolean apply(ExampleData input) {
				if (exampleIdToInstall != null) {
					return exampleIdToInstall.equals(input.getId());
				}
				return true;
			}

		});
		if (exampleToInstall != null) {
			viewer.addFilter(new ViewerFilter() {

				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (exampleIdToInstall == null) {
						return true;
					}
					if (element instanceof ExampleData) {
						return exampleIdToInstall.equals(((ExampleData) element).getId());
					}
					if (element instanceof ExampleContentProvider.Category) {
						return ((ExampleContentProvider.Category) element).getChildren().contains(exampleToInstall);
					}
					return true;
				}
			});
			viewer.setSelection(new StructuredSelection(exampleToInstall), true);
		}
	}

	protected void createTreeViewer(Composite container) {
		viewer = new TableViewer(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new ExampleLabelProvider()));
		viewer.addSelectionChangedListener(this);
	}

	protected void updateSelection(ExampleData data) {
		selection = data;
		setDetailPaneContent(data);
		setPageComplete(true);
		setErrorMessage(null);
		checkInstalledPlugins(data);
		viewer.refresh();
	}

	private void checkInstalledPlugins(ExampleData data) {
		if (data.isProfessional() && Platform.getBundle(PRO_BUNDLE) == null) {
			messageArea.showProInstall();
		} else {
			messageArea.hide();
		}
		messageArea.getParent().layout(true);
		this.getControl().update();
	}

	protected void setDetailPaneContent(ExampleData exampleData) {
		String url = exampleData.getProjectDir().getAbsolutePath() + File.separator + "index.html";
		browser.setUrl(url);
	}

	protected void createDetailsPane(Composite parent) {
		final Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new FillLayout());
		browser = new Browser(composite, SWT.NONE);
	}

	public ExampleData getSelection() {
		return selection;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		Object firstElement = ((StructuredSelection) event.getSelection()).getFirstElement();
		if (firstElement instanceof ExampleData)
			updateSelection((ExampleData) firstElement);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		switch (messageArea.getState()) {
		case DOWNLOAD:
		case UPDATE:
			revealExamples();
			break;
		case INSTALL:
			Program.launch(PRO_UPDATE_SITE);
			break;
		default:
			break;
		}
	}

	protected void revealExamples() {
		try {
			getWizard().getContainer().run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					final IStatus status = exampleService.fetchAllExamples(monitor);
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							if (status.isOK()) {
								SelectExamplePage.this.setInput(monitor);
							} else {
								messageArea.showError();
							}
						}
					});
				}
			});

		} catch (InvocationTargetException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (ExamplesPreferenceConstants.STORAGE_LOCATION.equals(event.getProperty())) {
			initAsync();
		}
	}

	public void setInstallExampleId(String exampleId) {
		this.exampleIdToInstall = exampleId;
	}

}