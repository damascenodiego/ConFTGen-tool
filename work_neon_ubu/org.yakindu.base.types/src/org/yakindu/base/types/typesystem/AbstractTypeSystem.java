/**
 * Copyright (c) 2014 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.base.types.typesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.yakindu.base.types.ComplexType;
import org.yakindu.base.types.Operation;
import org.yakindu.base.types.PrimitiveType;
import org.yakindu.base.types.Property;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.TypesFactory;
import org.yakindu.base.types.annotations.TypeAnnotations;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * Abstract base implementation if {@link ITypeSystem}. Provides convenience
 * methods to determine type compatibility.
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public abstract class AbstractTypeSystem implements ITypeSystem {

	protected Map<String, Type> typeRegistry = new HashMap<String, Type>();
	protected ListMultimap<Type, Type> extendsRegistry = ArrayListMultimap.create();
	protected ListMultimap<Type, Operation> extensionOperationRegistry = ArrayListMultimap.create();
	protected ListMultimap<Type, Property> extensionPropertyRegistry = ArrayListMultimap.create();

	protected Map<Type, Type> conversionRegistry = new HashMap<Type, Type>();

	protected abstract void initRegistries();

	protected Resource resource;

	protected TypeAnnotations typeAnnotations;

	public AbstractTypeSystem() {
		resource = new ResourceImpl(URI.createURI("types"));
		typeAnnotations = new TypeAnnotations();
		initRegistries();
	}

	protected void reset() {
		typeRegistry.clear();
		extendsRegistry.clear();
		conversionRegistry.clear();
	}

	public Type getType(String type) {
		Type result = typeRegistry.get(type);
		if (result == null) {
			System.err.println("Could not find type " + type);
		}
		return result;
	}

	public List<Type> getSuperTypes(Type type) {
		List<Type> superTypes = new ArrayList<Type>();
		for (Entry<Type, Type> entry : extendsRegistry.entries()) {
			if (isSame(type, entry.getKey())) {
				superTypes.add(entry.getValue());
			}
		}
		if (type instanceof ComplexType) {
			ComplexType complexType = (ComplexType) type;
			superTypes.addAll(complexType.getSuperTypes());
		}

		return superTypes;
	}

	public boolean isSuperType(Type subtype, Type supertype) {
		List<Type> typehierachy = new ArrayList<Type>();
		typehierachy.add(subtype);
		collectSupertypes(subtype, typehierachy);
		for (Type eObject : typehierachy) {
			if (isSame(eObject, supertype))
				return true;
		}
		return false;
	}

	private void collectSupertypes(Type subtypeClass, List<Type> typeHierachy) {
		if (subtypeClass == null)
			return;

		List<Type> superTypes = getSuperTypes(subtypeClass);
		for (Type superType : superTypes) {
			typeHierachy.add(superType);
			collectSupertypes(superType, typeHierachy);
		}
	}

	public Collection<Type> getTypes() {
		return Collections.unmodifiableCollection(typeRegistry.values());
	}

	public Collection<Type> getConcreteTypes() {
		List<Type> result = new ArrayList<Type>();
		for (Type type : getTypes()) {
			if (!type.isAbstract())
				result.add(type);
		}
		return result;
	}

	protected Type declarePrimitive(String name) {
		PrimitiveType primitive = TypesFactory.eINSTANCE.createPrimitiveType();
		primitive.setName(name);
		declareType(primitive, name);
		resource.getContents().add(primitive);
		return primitive;
	}

	public void declareType(Type type, String name) {
		typeRegistry.put(name, type);
	}

	public void removeType(String name) {
		Type type = typeRegistry.get(name);
		if (type != null) {
			extendsRegistry.removeAll(type);
			resource.getContents().remove(type);
			typeRegistry.remove(type);
		}
	}

	public void declareSuperType(Type subType, Type superType) {
		extendsRegistry.put(subType, superType);
	}

	public void declareConversion(Type baseType, Type targetType) {
		conversionRegistry.put(baseType, targetType);
	}

	public boolean haveCommonType(Type type1, Type type2) {
		return getCommonType(type1, type2) != null;
	}

	public boolean isSame(Type type1, Type type2) {
		return EcoreUtil.equals(type1, type2);
	}

	public Type getCommonType(Type type1, Type type2) {
		Type result = getCommonTypeInternal(type1, type2);
		if (result != null)
			return result;
		return null;
	}

	@Override
	public boolean haveCommonTypeWithConversion(Type type1, Type type2) {
		return getCommonTypeWithConversion(type1, type2) != null;
	}

	public Type getCommonTypeWithConversion(Type type1, Type type2) {
		Type result = getCommonType(type1, type2);
		if (result != null)
			return result;
		Type conversionType1 = getConversionType(type1);
		if (conversionType1 != null) {
			result = getCommonTypeInternal(conversionType1, type2);
			if (result != null)
				return result;
		}
		Type conversionType2 = getConversionType(type2);
		if (conversionType2 != null)
			return getCommonTypeInternal(type1, conversionType2);
		return null;
	}

	private Type getCommonTypeInternal(Type type1, Type type2) {

		if (isSame(type1, type2))
			return type1;
		if (isSuperType(type1, type2)) {
			return type2;
		}
		if (isSuperType(type2, type1))
			return type1;

		List<Type> typehierachy1 = new ArrayList<Type>();
		collectSupertypes(type1, typehierachy1);
		List<Type> typehierachy2 = new ArrayList<Type>();
		collectSupertypes(type2, typehierachy2);

		for (Type type : typehierachy1) {
			if (typehierachy2.contains(type)) {
				return type;
			}
		}
		for (Type type : typehierachy2) {
			if (typehierachy1.contains(type)) {
				return type;
			}
		}

		return null;
	}

	protected Type getConversionType(Type sourceType) {
		return conversionRegistry.get(sourceType);
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public boolean isBuiltInType(Type type) {
		return typeAnnotations.hasBuiltInTypeAnnotation(type);
	}

	@Override
	public List<Operation> getOperationExtensions(Type type) {
		List<Operation> result = new ArrayList<>();
		result.addAll(extensionOperationRegistry.get(type));
		List<Type> superTypes = getSuperTypes(type);
		for (Type superType : superTypes) {
			result.addAll(extensionOperationRegistry.get(superType));
		}
		return result;
	}

	@Override
	public boolean isExtensionOperation(Operation op) {
		return extensionOperationRegistry.containsValue(op);
	}

	@Override
	public List<Property> getPropertyExtensions(Type type) {
		List<Property> result = new ArrayList<>();
		result.addAll(extensionPropertyRegistry.get(type));
		List<Type> superTypes = getSuperTypes(type);
		for (Type superType : superTypes) {
			result.addAll(extensionPropertyRegistry.get(superType));
		}
		return result;
	}

	@Override
	public boolean isExtensionProperty(Property prop) {
		return extensionPropertyRegistry.containsValue(prop);
	}
}
