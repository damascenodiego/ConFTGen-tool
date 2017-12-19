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
package org.yakindu.sct.simulation.core.sexec.test;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sexec.interpreter.test.util.AbstractExecutionFlowTest;
import org.yakindu.sct.model.sexec.interpreter.test.util.SExecInjectionProvider;
import org.yakindu.sct.test.models.SCTUnitTestModels;
import com.google.inject.Inject;
import static org.junit.Assert.assertTrue;
/**
 *  Unit TestCase for ShallowHistoryWithDeepEntry
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class ShallowHistoryWithDeepEntryTest extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception {
		ExecutionFlow flow = models.loadExecutionFlowFromResource("ShallowHistoryWithDeepEntry.sct");
		initInterpreter(flow);
	}
	@Test
	public void noDeepEntryWithinHistory() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("Y"));
		raiseEvent("toZ");
		interpreter.runCycle();
		assertTrue(isStateActive("A"));
		raiseEvent("toY");
		interpreter.runCycle();
		assertTrue(isStateActive("Y"));
		raiseEvent("toZ");
		interpreter.runCycle();
		assertTrue(isStateActive("A"));
	}
	@Test
	public void deepEntryWithinHistory() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("Y"));
		raiseEvent("toZ");
		interpreter.runCycle();
		assertTrue(isStateActive("A"));
		raiseEvent("toC");
		interpreter.runCycle();
		assertTrue(isStateActive("C"));
		assertTrue(isStateActive("B"));
		raiseEvent("toY");
		interpreter.runCycle();
		assertTrue(isStateActive("Y"));
		raiseEvent("toZ");
		interpreter.runCycle();
		assertTrue(isStateActive("C"));
		assertTrue(isStateActive("B"));
	}
	@Test
	public void directDeepEntryIntoHistory() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("Y"));
		raiseEvent("toC");
		interpreter.runCycle();
		assertTrue(isStateActive("C"));
		assertTrue(isStateActive("B"));
		raiseEvent("toY");
		interpreter.runCycle();
		assertTrue(isStateActive("Y"));
		raiseEvent("toZ");
		interpreter.runCycle();
		assertTrue(isStateActive("C"));
		assertTrue(isStateActive("B"));
	}
}
