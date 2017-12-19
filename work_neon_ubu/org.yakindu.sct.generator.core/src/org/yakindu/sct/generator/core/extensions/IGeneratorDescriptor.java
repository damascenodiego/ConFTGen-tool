/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.generator.core.extensions;

import java.net.URL;
import java.util.List;

import org.yakindu.sct.generator.core.execution.IGeneratorEntryExecutor;
import org.yakindu.sct.model.sgen.GeneratorEntry;

import com.google.inject.Module;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public interface IGeneratorDescriptor {

	IGeneratorEntryExecutor createExecutor();

	Module getBindings(GeneratorEntry entry);

	List<String> getLibraryIDs();

	String getId();

	String getName();

	URL getImagePath();

	String getContentType();

	String getDescription();

	String getElementRefType();

}