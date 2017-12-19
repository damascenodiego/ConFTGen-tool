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

package org.yakindu.sct.generator.java.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.yakindu.scr.constonlyinternalscope.ConstOnlyInternalScopeStatemachine;
import org.yakindu.scr.constonlyinternalscope.ConstOnlyInternalScopeStatemachine.State;
/**
 *  Unit TestCase for ConstOnlyInternalScope
 */
@SuppressWarnings("all")
public class ConstOnlyInternalScopeTest {

	private ConstOnlyInternalScopeStatemachine statemachine;

	@Before
	public void setUp() {
		statemachine = new ConstOnlyInternalScopeStatemachine();
		statemachine.init();
	}

	@After
	public void tearDown() {
		statemachine = null;
	}

	@Test
	public void teststatechartEntry() {
		statemachine.enter();
		assertTrue(statemachine.isStateActive(State.constOnlyInternalScope_main_region_A));
	}
	@Test
	public void teststateTransition() {
		statemachine.enter();
		statemachine.raiseE(1l);
		statemachine.runCycle();
		assertTrue(statemachine.isStateActive(State.constOnlyInternalScope_main_region_B));
	}
}
