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
package org.yakindu.base.expressions.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.JavaClassPathResourceForIEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.ResourceForIEditorInputFactory;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.SimpleResourceSetProvider;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.ui.shared.Access;

/**
 * Use this class to register components to be used within the IDE.
 */
public class ExpressionsUiModule extends org.yakindu.base.expressions.ui.AbstractExpressionsUiModule {
	public ExpressionsUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public com.google.inject.Provider<org.eclipse.xtext.resource.containers.IAllContainersState> provideIAllContainersState() {
		if (Access.getJdtHelper().get().isJavaCoreAvailable()) {
			return Access.getJavaProjectsState();
		} else {
			return Access.getWorkspaceProjectsState();
		}
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
}
