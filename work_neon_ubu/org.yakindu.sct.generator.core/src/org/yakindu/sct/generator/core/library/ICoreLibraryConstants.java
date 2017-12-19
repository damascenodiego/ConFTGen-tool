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
package org.yakindu.sct.generator.core.library;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public interface ICoreLibraryConstants {

	String LIBRARY_NAME = "Core";
	String OUTLET_FEATURE = "Outlet";
	String OUTLET_FEATURE_TARGET_PROJECT = "targetProject";
	String OUTLET_FEATURE_TARGET_FOLDER = "targetFolder";
	String OUTLET_FEATURE_LIBRARY_TARGET_FOLDER = "libraryTargetFolder";
	String OUTLET_FEATURE_API_TARGET_FOLDER = "apiTargetFolder";
	String LICENSE_HEADER = "LicenseHeader";
	String LICENSE_TEXT = "licenseText";

	String DEBUG_FEATURE = "Debug";
	String DEBUG_FEATURE_DUMP_SEXEC = "dumpSexec";

	String FUNCTION_INLINING_FEATURE = "FunctionInlining";
	String FUNCTION_INLINING_FEATURE_INLINE_REACTIONS = "inlineReactions";
	String FUNCTION_INLINING_FEATURE_INLINE_ENTRY_ACTIONS = "inlineEntryActions";
	String FUNCTION_INLINING_FEATURE_INLINE_ENTER_SEQUENCES = "inlineEnterSequences";
	String FUNCTION_INLINING_FEATURE_INLINE_EXIT_ACTIONS = "inlineExitActions";
	String FUNCTION_INLINING_FEATURE_INLINE_EXIT_SEQUENCES = "inlineExitSequences";
	String FUNCTION_INLINING_FEATURE_INLINE_CHOICES = "inlineChoices";
	String FUNCTION_INLINING_FEATURE_INLINE_ENTRIES = "inlineEntries";
	String FUNCTION_INLINING_FEATURE_INLINE_ENTER_REGION = "inlineEnterRegion";
	String FUNCTION_INLINING_FEATURE_INLINE_EXIT_REGION = "inlineExitRegion";

}
