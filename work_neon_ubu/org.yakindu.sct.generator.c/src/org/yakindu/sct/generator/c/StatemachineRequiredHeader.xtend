/**
 * Copyright (c) 2012 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.generator.c

import com.google.inject.Inject
import org.yakindu.base.types.Declaration
import org.yakindu.sct.generator.core.types.ICodegenTypeSystemAccess
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sexec.naming.INamingService
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.model.stext.stext.OperationDefinition
import org.yakindu.sct.model.stext.stext.StatechartScope

class StatemachineRequiredHeader implements IContentTemplate {

	@Inject extension Naming cNaming
	@Inject extension Navigation
	@Inject extension ICodegenTypeSystemAccess
	@Inject extension GenmodelEntries
	@Inject extension INamingService
	
	override content(ExecutionFlow it, GeneratorEntry entry, extension IGenArtifactConfigurations artifactConfigs) '''
		«entry.licenseText»
		
		#ifndef «module.client.define»_H_
		#define «module.client.define»_H_

		#include "«(typesModule.h).relativeTo(module.client.h)»"
		«IF timed || operations.size > 0 || entry.tracingEnterState || entry.tracingExitState»
		#include "«(module.h).relativeTo(module.client.h)»"
		«ENDIF»

		#ifdef __cplusplus
		extern "C"
		{
		#endif 
		
		/*! \file This header defines prototypes for all functions that are required by the state machine implementation.
		
		«IF timed»
			This is a state machine uses time events which require access to a timing service. Thus the function prototypes:
				- «type.toFirstLower»_setTimer and
				- «type.toFirstLower»_unsetTimer
			are defined.
			
		«ENDIF»
		«IF operations.size > 0»
			This state machine makes use of operations declared in the state machines interface or internal scopes. Thus the function prototypes:
				«FOR o : operations»
				- «o.asFunction»
				«ENDFOR»
			are defined.
			
		«ENDIF»
		These functions will be called during a 'run to completion step' (runCycle) of the statechart. 
		There are some constraints that have to be considered for the implementation of these functions:
			- never call the statechart API functions from within these functions.
			- make sure that the execution time is as short as possible.
		 
		*/
		«FOR s : it.scopes »
		«s.scopeFunctionPrototypes»
		
		«ENDFOR»
		«IF timed»
		/*!
		 * This is a timed state machine that requires timer services
		 */ 
		
		/*! This function has to set up timers for the time events that are required by the state machine. */
		/*! 
			This function will be called for each time event that is relevant for a state when a state will be entered.
			\param evid An unique identifier of the event.
			\time_ms The time in milli seconds
			\periodic Indicates the the time event must be raised periodically until the timer is unset 
		*/
		extern void «type.toFirstLower»_setTimer(«scHandleDecl», const sc_eventid evid, const sc_integer time_ms, const sc_boolean periodic);

		/*! This function has to unset timers for the time events that are required by the state machine. */
		/*! 
			This function will be called for each time event taht is relevant for a state when a state will be left.
			\param evid An unique identifier of the event.
		*/
		extern void «type.toFirstLower»_unsetTimer(«scHandleDecl», const sc_eventid evid);
		«ENDIF»
		
		
		«IF entry.tracingEnterState || entry.tracingExitState»
		/*!
		 * Tracing callback functions
		 */
		«IF entry.tracingEnterState»
			/*! This function is called when a state is entered. */
			extern void «type.toFirstLower»_stateEntered(«scHandleDecl», const «statesEnumType» state);
		«ENDIF»
		
		«IF entry.tracingExitState»
			/*! This function is called when a state is exited. */
			extern void «type.toFirstLower»_stateExited(«scHandleDecl», const «statesEnumType» state);
		«ENDIF»
		«ENDIF»
		
		#ifdef __cplusplus
		}
		#endif 
		
		#endif /* «module.client.define»_H_ */
	'''
	
	
	def dispatch scopeFunctionPrototypes(StatechartScope it) '''
		«FOR d : declarations »
		«d.functionPrototypes »
		«ENDFOR»
	'''	

	def dispatch scopeFunctionPrototypes(Object it) ''''''	
	

	def dispatch functionPrototypes(Declaration it) ''''''

	def dispatch functionPrototypes(OperationDefinition it) '''
		extern «typeSpecifier.targetLanguageName» «asFunction»(const «scHandleDecl»«FOR p : parameters BEFORE ', ' SEPARATOR ', '»const «p.typeSpecifier.targetLanguageName» «p.name.asIdentifier»«ENDFOR»);
	'''

}