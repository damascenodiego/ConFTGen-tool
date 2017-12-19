package org.yakindu.sct.ui.editor.providers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramGlobalActionHandler;

/**
 * The purpose of this subclass is to provide our own copy command
 * {@link org.yakindu.sct.ui.editor.providers.SubdiagramAwareCopyCommand.SubdiagramAwareCopyCommand(TransactionalEditingDomain,
 * String, View, List)} to allow for copying states together with their sub diagrams.
 * 
 * @author Thomas Kutz
 *
 */
public class StatechartDiagramGlobalActionHandler extends DiagramGlobalActionHandler {

	@Override
	protected ICommand getCopyCommand(IGlobalActionContext cntxt, IDiagramWorkbenchPart diagramPart,
			final boolean isUndoable) {

		TransactionalEditingDomain editingDomain = getEditingDomain(diagramPart);

		if (editingDomain == null) {
			return null;
		}

		// return own copy command to also copy sub diagrams for copied states
		return new SubdiagramAwareCopyCommand(editingDomain, cntxt.getLabel(), diagramPart.getDiagram(),
				getSelectedViews(cntxt.getSelection())) {

			public boolean canUndo() {
				return isUndoable;
			}

			public boolean canRedo() {
				return isUndoable;
			}

			protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				if (isUndoable) {
					return Status.OK_STATUS;
				}
				return super.doUndo(monitor, info);
			}

			protected IStatus doRedo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				if (isUndoable) {
					return Status.OK_STATUS;
				}
				return super.doRedo(monitor, info);
			}
		};
	}

	/**
	 * Copied from
	 * DiagramGlobalActionHandler.getEditingDomain(IDiagramWorkbenchPart) because it is private.
	 */
	private TransactionalEditingDomain getEditingDomain(IDiagramWorkbenchPart part) {
		TransactionalEditingDomain result = null;
		IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
		if (provider != null) {
			EditingDomain domain = provider.getEditingDomain();

			if (domain != null && domain instanceof TransactionalEditingDomain) {
				result = (TransactionalEditingDomain) domain;
			}
		}
		return result;
	}
}
