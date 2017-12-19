/**
 * Copyright (c) 2011 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.model.stext.scoping;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.FilteringScope;
import org.yakindu.base.types.typesystem.ITypeSystem;
import org.yakindu.sct.domain.extension.DomainRegistry;
import org.yakindu.sct.model.sgraph.SGraphPackage;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.util.ContextElementAdapter;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class STextGlobalScopeProvider extends DefaultGlobalScopeProvider {

	@Inject
	private ITypeSystem typeSystem;
	@Inject
	private IQualifiedNameProvider qualifiedNameProvider;

	public static final String FILE_EXTENSION = "sct";

	public IScope getScope(Resource context, EReference reference, Predicate<IEObjectDescription> filter) {
		IScope parentScope = super.getScope(context, reference, filter);
		parentScope = filterExternalDeclarations(context, parentScope);
		final Statechart statechart = getStatechart(context);
		parentScope = new TypeSystemAwareScope(parentScope, typeSystem, qualifiedNameProvider,
				reference.getEReferenceType());
		return new FilteringScope(parentScope, new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String userData = input.getUserData(DomainRegistry.DOMAIN_ID);
				if (userData == null)
					return true;
				return statechart.getDomainID().equals(userData);
			}
		});
	}

	private Statechart getStatechart(Resource context) {
		final ContextElementAdapter provider = (ContextElementAdapter) EcoreUtil.getExistingAdapter(context,
				ContextElementAdapter.class);

		if (provider == null) {
			return (Statechart) EcoreUtil2.getObjectByType(context.getContents(), SGraphPackage.Literals.STATECHART);
		} else {
			return (Statechart) EcoreUtil.getObjectByType(provider.getElement().eResource().getContents(),
					SGraphPackage.Literals.STATECHART);
		}
	}

	/**
	 * Filter all Elements that are part of an SCT file from other resources to
	 * avoid cross document referencing
	 */
	protected IScope filterExternalDeclarations(Resource context, IScope parentScope) {
		final ContextElementAdapter provider = (ContextElementAdapter) EcoreUtil.getExistingAdapter(context,
				ContextElementAdapter.class);
		final URI resourceURI = provider != null ? provider.getElement().eResource().getURI() : context.getURI();
		parentScope = new FilteringScope(parentScope, new Predicate<IEObjectDescription>() {
			public boolean apply(IEObjectDescription input) {
				if (FILE_EXTENSION.equals(input.getEObjectURI().fileExtension())) {
					URI sourceURI = input.getEObjectURI().trimFragment();
					return sourceURI.equals(resourceURI);
				}
				return true;
			}
		});
		return parentScope;
	}
}
