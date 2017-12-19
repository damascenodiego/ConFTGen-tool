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
 *  Unit TestCase for StatechartActive
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class StatechartActiveTest extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception {
		ExecutionFlow flow = models.loadExecutionFlowFromResource("StatechartActive.sct");
		initInterpreter(flow);
	}
	@Test
	public void inactiveBeforeEnter() throws Exception {
		assertTrue(!interpreter.isActive());
	}
	@Test
	public void activeAfterEnter() throws Exception {
		interpreter.enter();
		assertTrue(interpreter.isActive());
	}
	@Test
	public void inactiveAfterExit() throws Exception {
		interpreter.enter();
		interpreter.exit();
		assertTrue(!interpreter.isActive());
	}
	@Test
	public void activeAfterReenter() throws Exception {
		interpreter.enter();
		interpreter.exit();
		interpreter.enter();
		assertTrue(interpreter.isActive());
	}
}
