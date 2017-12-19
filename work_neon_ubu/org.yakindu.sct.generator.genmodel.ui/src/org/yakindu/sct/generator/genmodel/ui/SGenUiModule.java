/** 
 * Copyright (c) 2015 committers of YAKINDU and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * Contributors:
 * committers of YAKINDU - initial API and implementation
 *
*/
package org.yakindu.sct.generator.genmodel.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.hover.DispatchingEObjectTextHover;
import org.eclipse.xtext.ui.editor.hover.IEObjectHover;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.JavaClassPathResourceForIEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.ResourceForIEditorInputFactory;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.SimpleResourceSetProvider;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.ui.shared.Access;
import org.yakindu.base.utils.jface.help.CrossRefObjectTextHover;
import org.yakindu.base.utils.jface.help.HelpHoverProvider;
import org.yakindu.sct.generator.core.filesystem.ISCTWorkspaceAccess;
import org.yakindu.sct.generator.genmodel.ui.help.SGenUserHelpDocumentationProvider;
import org.yakindu.sct.generator.genmodel.ui.highlighting.SGenHighlightingConfiguration;
import org.yakindu.sct.generator.genmodel.ui.highlighting.SGenSemanticHighlightingCalculator;
import org.yakindu.sct.generator.genmodel.ui.templates.SGenTemplateProposalProvider;
import org.yakindu.sct.generator.genmodel.ui.ws.DefaultSCTWorkspaceAccess;

/**
 * Use this class to register components to be used within the IDE.
 */
public class SGenUiModule extends org.yakindu.sct.generator.genmodel.ui.AbstractSGenUiModule {
	public SGenUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return SGenSemanticHighlightingCalculator.class;
	}

	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return SGenHighlightingConfiguration.class;
	}

	public Class<? extends IEObjectDocumentationProvider> bindIEObjectDocumentationProvider() {
		return SGenUserHelpDocumentationProvider.class;
	}

	public Class<? extends DispatchingEObjectTextHover> bindDispatchingEObjectTextHover() {
		return CrossRefObjectTextHover.class;
	}

	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return HelpHoverProvider.class;
	}

	@Override
	public Class<? extends IEObjectHover> bindIEObjectHover() {
		return CrossRefObjectTextHover.class;
	}

	@Override
	public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
		return SGenTemplateProposalProvider.class;
	}

	@Override
	public com.google.inject.Provider<org.eclipse.xtext.resource.containers.IAllContainersState> provideIAllContainersState() {
		return Access.getWorkspaceProjectsState();
	}

	@Override
	public Class<? extends IResourceSetProvider> bindIResourceSetProvider() {
		if (Access.getJdtHelper().get().isJavaCoreAvailable()) {
			return XtextResourceSetProvider.class;
		} else {
			return SimpleResourceSetProvider.class;
		}
	}

	@Override
	public Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
		if (Access.getJdtHelper().get().isJavaCoreAvailable()) {
			return JavaClassPathResourceForIEditorInputFactory.class;
		} else {
			return ResourceForIEditorInputFactory.class;
		}
	}

	public Class<? extends ISCTWorkspaceAccess> bindISCTWorkspaceAccess() {
		return DefaultSCTWorkspaceAccess.class;
	}

}
