/**
  Copyright (c) 2013 committers of YAKINDU and others.
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
  Contributors:
  	Markus Mühlbrandt - Initial contribution and API
 */
package org.yakindu.sct.generator.cpp

import com.google.inject.Inject
import org.yakindu.sct.generator.c.GenmodelEntries
import org.yakindu.sct.generator.c.IContentTemplate
import org.yakindu.sct.generator.c.IGenArtifactConfigurations
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry

class StatemachineInterface implements IContentTemplate {
	
	@Inject
	extension Naming
	
	@Inject
	extension GenmodelEntries
	
	override content(ExecutionFlow it, GeneratorEntry entry, IGenArtifactConfigurations locations) {
		'''
		«entry.licenseText»
		
		#ifndef «statemachineInterface.define»_H_
		#define «statemachineInterface.define»_H_
		
		/*! \file Basic interface for state machines.
		 */
		class «statemachineInterface»
		{
			public:
			
				virtual ~«statemachineInterface»() = 0;
				
				/*! Initializes the state machine. Used to initialize internal variables etc.
				*/
				virtual void init() = 0;
			
				/*! Enters the state machine. Sets the state machine into a defined state.
				*/
				virtual void enter() = 0;
			
				/*! Exits the state machine. Leaves the state machine with a defined state.
				*/
				virtual void exit() = 0;
			
				/*! Start a run-to-completion cycle.
				*/
				virtual void runCycle() = 0;
				
				/*! Checks whether the state machine is active. 
			 	    A state machine is active if it has been entered. It is inactive if it has not been entered at all or if it has been exited.
			 	*/	
				virtual	sc_boolean isActive() = 0;
				
				/*! Checks if all active states are final. 
			 		If there are no active states then the state machine is considered being inactive. In this case this method returns false.
			 	*/
				virtual sc_boolean isFinal() = 0;
		};
		
		inline StatemachineInterface::~StatemachineInterface() {}
		
		#endif /* «statemachineInterface.define»_H_ */
		'''
	}
}