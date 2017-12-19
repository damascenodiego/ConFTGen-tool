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
package org.yakindu.sct.generator.core.execution;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.yakindu.base.types.typesystem.AbstractTypeSystem;
import org.yakindu.base.types.typesystem.ITypeSystem;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.domain.extension.IDomain;
import org.yakindu.sct.generator.core.extensions.GeneratorExtensions;
import org.yakindu.sct.generator.core.extensions.IGeneratorDescriptor;
import org.yakindu.sct.model.sgen.GeneratorEntry;
import org.yakindu.sct.model.sgen.GeneratorModel;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class GeneratorExecutorLookup {

	protected Module getContextModule() {
		return new Module() {
			@Override
			public void configure(Binder binder) {
			}
		};
	}

	public void execute(GeneratorModel model) {
		EList<GeneratorEntry> entries = model.getEntries();
		for (GeneratorEntry generatorEntry : entries) {
			final IGeneratorEntryExecutor executor = createExecutor(generatorEntry, model.getGeneratorId());
			executor.execute(generatorEntry);
		}
	}

	public IGeneratorEntryExecutor createExecutor(GeneratorEntry entry, String generatorId) {
		IGeneratorDescriptor description = GeneratorExtensions.getGeneratorDescriptor(generatorId);
		if (description == null)
			throw new RuntimeException("No generator registered for ID: " + generatorId);
		final IGeneratorEntryExecutor executor = description.createExecutor();
		if (executor == null)
			throw new RuntimeException("Failed to create generator instance for ID:" + generatorId);
		Injector injector = createInjector(entry, description, generatorId);
		injector.injectMembers(executor);
		ITypeSystem typeSystem = injector.getInstance(ITypeSystem.class);
		if (typeSystem instanceof AbstractTypeSystem) {
			ResourceSet set = entry.getElementRef().eResource().getResourceSet();
			set.getResources().add(((AbstractTypeSystem) typeSystem).getResource());
			EcoreUtil.resolveAll(set);
		}
		Assert.isNotNull(entry.getElementRef().eResource());

		return executor;
	}

	protected Injector createInjector(GeneratorEntry entry, IGeneratorDescriptor description, String generatorId) {
		Module generatorSpecificModule = description.getBindings(entry);
		Module executionContextModule = getContextModule();
		Module domainModule = DomainRegistry.getDomain(entry.getElementRef()).getModule(IDomain.FEATURE_GENERATOR,
				generatorId);
		Module combined = Modules.override(Modules.combine(generatorSpecificModule, executionContextModule))
				.with(domainModule);
		return Guice.createInjector(combined);
	}
}
