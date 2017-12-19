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
package org.yakindu.sct.generator.cpp.features

import org.yakindu.sct.generator.c.GenmodelEntries
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.generator.cpp.features.CPPFeatureConstants.Visibility

class GenmodelEntriesExtension extends GenmodelEntries {

	def private getGeneratorOptionsFeature(GeneratorEntry it) {
		getFeatureConfiguration(CPPFeatureConstants::FEATURE_GENERATOR_OPTIONS)
	}

	def private getVisibilityParameter(GeneratorEntry it) {
		generatorOptionsFeature?.getParameterValue(CPPFeatureConstants.PARAMETER_INNER_FUNCTION_VISIBILITY)
	}

	def getInnerClassVisibility(GeneratorEntry it) {
		if (visibilityParameter != null) {
			return visibilityParameter.stringValue
		}
		return Visibility.PRIVATE.toString.toLowerCase
	}

	def private getStaticOPCParameter(GeneratorEntry it) {
		generatorOptionsFeature?.getParameterValue(CPPFeatureConstants.PARAMETER_STATIC_OPC)
	}

	def useStaticOPC(GeneratorEntry it) {
		if (staticOPCParameter != null) {
			return staticOPCParameter.booleanValue
		}
		return false
	}
}
