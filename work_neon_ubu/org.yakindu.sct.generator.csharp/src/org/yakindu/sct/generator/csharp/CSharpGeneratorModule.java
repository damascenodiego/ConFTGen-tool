/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.generator.csharp;

import org.yakindu.sct.generator.core.GeneratorModule;
import org.yakindu.sct.generator.core.IExecutionFlowGenerator;
import org.yakindu.sct.generator.core.types.ICodegenTypeSystemAccess;
import org.yakindu.sct.generator.csharp.types.CSharpTypeSystemAccess;
import org.yakindu.sct.model.sexec.naming.INamingService;
import org.yakindu.sct.model.sgen.GeneratorEntry;

import com.google.inject.Binder;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class CSharpGeneratorModule implements GeneratorModule {

	@Override
	public void configure(GeneratorEntry entry, Binder binder) {
		binder.bind(IExecutionFlowGenerator.class).to(CSharpGenerator.class);
		binder.bind(ICodegenTypeSystemAccess.class).to(CSharpTypeSystemAccess.class);
		binder.bind(INamingService.class).to(CSharpNamingService.class);
		binder.bind(GeneratorEntry.class).toInstance(entry);
	}
}
