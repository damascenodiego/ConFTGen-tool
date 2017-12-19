package org.yakindu.sct.model.stext.test.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.yakindu.sct.test.models.AbstractTestModelsUtil.VALIDATION_TESTMODEL_DIR;

import java.util.HashMap;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.validation.ValidatorTester;
import org.junit.After;
import org.junit.Before;
import org.yakindu.sct.model.sgraph.SGraphFactory;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.sgraph.Vertex;
import org.yakindu.sct.model.stext.resource.StextResource;
import org.yakindu.sct.model.stext.test.util.AbstractSTextTest;
import org.yakindu.sct.model.stext.validation.STextJavaValidator;
import org.yakindu.sct.test.models.AbstractTestModelsUtil;

import com.google.inject.Inject;
import com.google.inject.Injector;

public abstract class AbstractSTextValidationTest extends AbstractSTextTest {

	@Inject
	public STextJavaValidator validator;
	@Inject
	public Injector injector;

	protected ValidatorTester<STextJavaValidator> tester;
	protected BasicDiagnostic diagnostics;
	protected SGraphFactory factory;
	protected Statechart statechart;
	protected StextResource resource;

	@Before public void setup() {
		factory = SGraphFactory.eINSTANCE;
	
		resource = new StextResource(URI.createURI(""));
		injector.injectMembers(resource);
		statechart = factory.createStatechart();
		resource.getContents().add(statechart);
	
		diagnostics = new BasicDiagnostic();
		tester = new ValidatorTester<STextJavaValidator>(validator, injector);
	}

	@After public void teardown() {
		tester = null;
	}

	protected void resetDiagnostics() {
		diagnostics = new BasicDiagnostic();
	}

	protected Transition createTransition(Vertex source, Vertex target) {
		Transition trans = SGraphFactory.eINSTANCE.createTransition();
		trans.setSource(source);
		trans.setTarget(target);
		source.getOutgoingTransitions().add(trans);
		target.getIncomingTransitions().add(trans);
		return trans;
	}

	protected void assertError(Diagnostic diag, String message) {
		Diagnostic d = issueByName(diag, message);
		assertNotNull("Issue '" + message + "' does not exist.", issueByName(diag, message));
		assertEquals("Issue '" + message + "' is no error.", Diagnostic.ERROR, d.getSeverity());
	}

	protected void assertWarning(Diagnostic diag, String message) {
		Diagnostic d = issueByName(diag, message);
		assertNotNull("Issue '" + message + "' does not exist.", issueByName(diag, message));
		assertEquals("Issue '" + message + "' is no warning.", Diagnostic.WARNING, d.getSeverity());
	}

	protected void assertIssueCount(Diagnostic diag, int count) {
		int c = diag.getChildren().size();
		assertEquals("expected " + count + " issue(s) but were " + c + " [" + diag.toString() + "]", count, c);
	}

	protected Diagnostic issueByName(Diagnostic diag, String message) {
		for (Diagnostic issue : diag.getChildren()) {
			if (message.equals(issue.getMessage()))
				return issue;
		}
		return null;
	}

	protected boolean validate(EObject obj) {
		return validator.validate(obj, diagnostics,
				new HashMap<Object, Object>());
	}

	protected Statechart loadStatechart(String modelName) {
		return AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR
						+ modelName);
	}

}
