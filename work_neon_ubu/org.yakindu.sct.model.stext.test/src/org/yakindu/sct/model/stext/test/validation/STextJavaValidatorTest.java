/**
 * Copyright (c) 2012 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	itemis AG - initial API and implementation
 * 
 */
package org.yakindu.sct.model.stext.test.validation;

import static org.eclipse.xtext.junit4.validation.AssertableDiagnostics.errorCode;
import static org.eclipse.xtext.junit4.validation.AssertableDiagnostics.errorMsg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.yakindu.sct.test.models.AbstractTestModelsUtil.VALIDATION_TESTMODEL_DIR;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.validation.AssertableDiagnostics;
import org.eclipse.xtext.validation.Check;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yakindu.base.expressions.expressions.Expression;
import org.yakindu.base.types.Annotation;
import org.yakindu.base.types.Operation;
import org.yakindu.base.types.Package;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.TypesFactory;
import org.yakindu.base.types.TypesPackage;
import org.yakindu.sct.model.sgraph.Entry;
import org.yakindu.sct.model.sgraph.Exit;
import org.yakindu.sct.model.sgraph.Scope;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.sgraph.Trigger;
import org.yakindu.sct.model.stext.inferrer.STextTypeInferrer;
import org.yakindu.sct.model.stext.stext.InterfaceScope;
import org.yakindu.sct.model.stext.stext.InternalScope;
import org.yakindu.sct.model.stext.stext.ReactionEffect;
import org.yakindu.sct.model.stext.stext.ReactionTrigger;
import org.yakindu.sct.model.stext.stext.StatechartSpecification;
import org.yakindu.sct.model.stext.stext.TransitionSpecification;
import org.yakindu.sct.model.stext.stext.impl.StextFactoryImpl;
import org.yakindu.sct.model.stext.test.util.STextInjectorProvider;
import org.yakindu.sct.model.stext.validation.STextJavaValidator;
import org.yakindu.sct.model.stext.validation.STextValidationMessages;
import org.yakindu.sct.test.models.AbstractTestModelsUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author andreas muelder - Initial contribution and API
 * 
 */
@RunWith(XtextRunner.class)
@InjectWith(STextInjectorProvider.class)
public class STextJavaValidatorTest extends AbstractSTextValidationTest implements STextValidationMessages {

	/**
	 * @see STextJavaValidator#checkVariableDefinition(org.yakindu.sct.model.stext.stext.VariableDefinition)
	 */
	@Test
	public void checkVariableDefinition() {
		Scope context = (Scope) parseExpression("interface if : var i : void",
				InterfaceScope.class.getSimpleName());
		AssertableDiagnostics validationResult = tester.validate(context);
		validationResult.assertErrorContains(STextTypeInferrer.VARIABLE_VOID_TYPE);
	}

	/**
	 * @see STextJavaValidator#checkAssignmentExpression(org.yakindu.sct.model.stext.stext.AssignmentExpression)
	 */
	@Test
	public void checkAssignmentExpression() {

		String context = "interface: var i : integer = 42 var j : integer =23";

		EObject expression = super.parseExpression("i += (i+=3) +4", Expression.class.getSimpleName(), context);
		AssertableDiagnostics validationResult = tester.validate(expression);
		validationResult.assertErrorContains(STextJavaValidator.ASSIGNMENT_EXPRESSION);

		expression = super.parseExpression("i += (j+=3) +4", Expression.class.getSimpleName(), context);
		validationResult = tester.validate(expression);
		validationResult.assertOK();
	}

	@Test
	public void checkTimeEventSpecValueExpression() {
		EObject expression = super.parseExpression("after true s", ReactionTrigger.class.getSimpleName());
		AssertableDiagnostics validationResult = tester.validate(expression);
		validationResult.assertErrorContains(STextJavaValidator.TIME_EXPRESSION);
	}

	@Test
	public void checkReactionEffectActionExpression() {
		// covered by inferrer tests
	}

	@Test
	public void checkLeftHandAssignment() {

		String scope =
				"interface if : operation myOperation() : boolean event Event1 : boolean var myVar : boolean";

		EObject model = super.parseExpression("3 = 3", Expression.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertErrorContains(STextJavaValidator.LEFT_HAND_ASSIGNMENT);

		// Check for referenced elements in interface
		model = super.parseExpression("if.myOperation() = true", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertErrorContains(STextJavaValidator.LEFT_HAND_ASSIGNMENT);

		model = super.parseExpression("if.Event1 = true", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertErrorContains(STextJavaValidator.LEFT_HAND_ASSIGNMENT);

		model = super.parseExpression("if.myVar = true", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertOK();

		// check for internal referenced elements
		scope = "internal : operation myOperation() : integer event Event1 : integer var myVar : integer";

		model = super.parseExpression("myOperation() = 5", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertErrorContains(STextJavaValidator.LEFT_HAND_ASSIGNMENT);

		model = super.parseExpression("Event1 = true", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertErrorContains(STextJavaValidator.LEFT_HAND_ASSIGNMENT);

		model = super.parseExpression("myVar = 5", Expression.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertOK();

	}

	/**
	 * @see STextJavaValidator#checkOperationArguments_FeatureCall(org.yakindu.sct.model.stext.stext.FeatureCall)
	 */
	@Test
	public void checkOperationArguments_FeatureCall() {
		String scope = "interface if : operation myOperation(param1 : integer, param2: boolean";
		EObject model = super.parseExpression("if.myOperation(5,true)", Expression.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertOK();
	}

	/**
	 * @see STextJavaValidator#checkOperationArguments_TypedElementReferenceExpression(TypedElementReferenceExpression)
	 */
	@Test
	public void checkOperationArguments_TypedElementReferenceExpression() {
		String scope = "internal: operation myOperation(param1 : integer, param2: boolean)";
		EObject model = super.parseExpression("myOperation(5,true)", Expression.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertOK();
	}

	/**
	 * @see STextJavaValidator#checkGuardHasBooleanExpression(org.yakindu.sct.model.stext.stext.ReactionTrigger)
	 */
	@Test
	public void checkGuard() {
		EObject expression = super.parseExpression("[3 * 3]", ReactionTrigger.class.getSimpleName());
		AssertableDiagnostics validationResult = tester.validate(expression);
		validationResult.assertErrorContains(STextJavaValidator.GUARD_EXPRESSION);

		String scope = "internal: var myInt : integer var myBool : boolean = true";
		expression = super.parseExpression("[myInt <= 5 || !myBool ]", ReactionTrigger.class.getSimpleName(), scope);
		validationResult = tester.validate(expression);
		validationResult.assertOK();
	}

	@Test
	public void checkNoAssignmentInGuard() {
		String scope = "internal: var myInt : integer var myBool : boolean = true";
		EObject expression = super.parseExpression("[myBool = false]", ReactionTrigger.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(expression);
		validationResult.assertErrorContains(STextJavaValidator.GUARD_CONTAINS_ASSIGNMENT);

		expression = super.parseExpression("[myInt = 5]", ReactionTrigger.class.getSimpleName(), scope);
		validationResult = tester.validate(expression);
		Iterator<Diagnostic> diag = validationResult.getAllDiagnostics().iterator();
		while (diag.hasNext()) {
			Diagnostic d = diag.next();
			if (d.getMessage().equals(GUARD_EXPRESSION)) {
				assertEquals(STextJavaValidator.GUARD_EXPRESSION, d.getMessage());
			} else {
				assertEquals(STextJavaValidator.GUARD_CONTAINS_ASSIGNMENT, d.getMessage());
			}
		}

	}

	/**
	 * @see STextJavaValidator#checkFeatureCall(org.yakindu.sct.model.stext.stext.FeatureCall)
	 * @see STextJavaValidator#checkFeatureCall(TypedElementReferenceExpression)
	 */
	@Test
	public void checkFeatureCall() {
		String scope = "interface if : in event a : integer";
		EObject model = super.parseExpression("if.a / raise if.a:1",
				TransitionSpecification.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertOK();
	}

	/**
	 * 
	 * @see STextJavaValidator#checkReactionTrigger(org.yakindu.sct.model.stext.stext.ReactionTrigger)
	 */
	@Test
	public void checkReactionTrigger() {
		// ENTRY, EXIT not allowed in transitions
		String scope = "internal : event a : integer var myVar : integer";
		EObject model = super.parseExpression("entry / myVar = 5", TransitionSpecification.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertError(ENTRY_EXIT_TRIGGER_NOT_ALLOWED);

		model = super.parseExpression("exit / myVar = 5", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertError(ENTRY_EXIT_TRIGGER_NOT_ALLOWED);

		model = super.parseExpression("oncycle / myVar = 5", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertOK();

		model = super.parseExpression("always / myVar = 5", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertOK();
	}
	
	@Test
	public void checkReactionTriggerRegularEvent(){
		
		String scope = "interface : in event e  var x : integer  var y : integer  operation op():integer";

		EObject model = super.parseExpression("e", TransitionSpecification.class.getSimpleName(), scope);
		AssertableDiagnostics validationResult = tester.validate(model);
		validationResult.assertOK();
		
		model = super.parseExpression("x", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertError(TRIGGER_IS_NO_EVENT);

		model = super.parseExpression("e, x", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertError(TRIGGER_IS_NO_EVENT);

		model = super.parseExpression("op()", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertError(TRIGGER_IS_NO_EVENT);
		
		model = super.parseExpression("x, y", TransitionSpecification.class.getSimpleName(), scope);
		validationResult = tester.validate(model);
		validationResult.assertAll(errorMsg("Trigger 'x' is no event."), errorMsg("Trigger 'y' is no event."));
		
	}

	/**
	 * @see STextJavaValidator#checkReactionEffectActions(org.yakindu.sct.model.stext.stext.ReactionEffect)
	 */
	@Test
	public void checkReactionEffectActions() {
		String s1 = "internal : var a : integer event e operation o () : void";
		String s2 = "interface if : var a : integer in event e operation o()";

		EObject model = super.parseExpression("a", ReactionEffect.class.getSimpleName(), s1);
		AssertableDiagnostics result = tester.validate(model);
		result.assertError(FEATURE_CALL_HAS_NO_EFFECT);

		model = super.parseExpression("1+3", ReactionEffect.class.getSimpleName(), s1);
		result = tester.validate(model);
		result.assertError(FEATURE_CALL_HAS_NO_EFFECT);

		model = super.parseExpression("valueof(e)", ReactionEffect.class.getSimpleName(), s1);
		result = tester.validate(model);
		result.assertError(FEATURE_CALL_HAS_NO_EFFECT);

		model = super.parseExpression("o()", ReactionEffect.class.getSimpleName(), s1);
		result = tester.validate(model);
		result.assertOK();

		model = super.parseExpression("if.a", ReactionEffect.class.getSimpleName(), s2);
		result = tester.validate(model);
		result.assertError(FEATURE_CALL_HAS_NO_EFFECT);

		model = super.parseExpression("valueof(if.e)", ReactionEffect.class.getSimpleName(), s2);
		result = tester.validate(model);
		result.assertError(FEATURE_CALL_HAS_NO_EFFECT);

		model = super.parseExpression("if.o", ReactionEffect.class.getSimpleName(), s2);
		result = tester.validate(model);
		result.assertOK();

	}

	
	
	/**
	 * @see STextJavaValidator#checkEventDefinition(org.yakindu.sct.model.stext.stext.EventDefinition)
	 */
	@Test
	public void checkEventDefinition() {
		// No local declarations in interface scope
		EObject model = super.parseExpression("interface MyInterface: event Event1", 
				InterfaceScope.class.getSimpleName());
		AssertableDiagnostics result = tester.validate(model);
		result.assertErrorContains(LOCAL_DECLARATIONS);
		// No in declarations in internal scope
		model = super.parseExpression("internal: in event Event1", InternalScope.class.getSimpleName());
		result = tester.validate(model);
		result.assertDiagnosticsCount(1);
		result.assertErrorContains(STextJavaValidator.IN_OUT_DECLARATIONS);
		// No out declarations in internal scope
		model = super.parseExpression("internal: out event Event1", InternalScope.class.getSimpleName());
		result = tester.validate(model);
		result.assertDiagnosticsCount(1);
		result.assertErrorContains(IN_OUT_DECLARATIONS);
	}

	/**
	 * @see STextJavaValidator#checkInterfaceScope(Statechart)
	 */
	@Test
	public void checkInterfaceScope() {
		EObject model = super.parseExpression("interface: in event event1 interface: in event event2", 
				StatechartSpecification.class.getSimpleName());
		AssertableDiagnostics result = tester.validate(model);
		result.assertDiagnosticsCount(2);
		result.assertAll(errorCode(ONLY_ONE_INTERFACE), errorCode(ONLY_ONE_INTERFACE));
	}

	/**
	 * @see STextJavaValidator#checkChoiceWithoutDefaultTransition(org.yakindu.sct.model.sgraph.Choice)
	 */
	@Test
	public void checkChoiceWithoutDefaultTransition() {
		// TODO
	}

	/**
	 * @see STextJavaValidator#checkUnresolvableProxies(Statechart)
	 */
	@Test
	public void checkUnresolvableProxies() {
		// Nothing to do
	}

	/**
	 * @see STextJavaValidator#checkcheckSyntaxErrors(Statechart)
	 */
	@Test
	public void checkSyntaxErrors() {
		// Nothing to do
	}

	@Test
	public void checkExpression() {
		// Nothing to do
	}
	
	@Test
	public void checkContextElement(){
		//Nothing to do -> this is covered by ContextPredicateProviderTest
	}

	@Test
	public void checkValueOfNoEvent(){
		String decl = "interface: in event e1:integer var x:integer operation op():integer interface i: in event e2:integer var y:integer";

		EObject model = super.parseExpression("valueof(e1)", Expression.class.getSimpleName(), decl);
		AssertableDiagnostics result = tester.validate(model);
		result.assertOK();
	
		model = super.parseExpression("valueof(i.e2)", Expression.class.getSimpleName(), decl);
		result = tester.validate(model);
		result.assertOK();
	
		model = super.parseExpression("valueof(x)", Expression.class.getSimpleName(), decl);
		result = tester.validate(model);
		result.assertError(VALUE_OF_REQUIRES_EVENT);
		
		model = super.parseExpression("valueof(i.y)", Expression.class.getSimpleName(), decl);
		result = tester.validate(model);
		result.assertError(VALUE_OF_REQUIRES_EVENT);
		
		model = super.parseExpression("valueof(op())", Expression.class.getSimpleName(), decl);
		result = tester.validate(model);
		result.assertError(VALUE_OF_REQUIRES_EVENT);
		
	}
	
	
	/**
	 * checks tht each @Check method of {@link STextJavaValidator} has a @Test
	 * method in this class with the same name
	 */
	@Test
	public void testAllChecksHaveTests() throws Exception {
		Iterable<Method> methods = Lists.newArrayList(STextJavaValidator.class.getDeclaredMethods());
		methods = Iterables.filter(methods, new Predicate<Method>() {
			public boolean apply(Method input) {
				return input.getAnnotation(Check.class) != null;
			}
		});
		for (Method checkMethod : methods) {
			Method testMethod = getClass().getMethod(checkMethod.getName());
			assertNotNull("Missing @Test Annotation for method " + checkMethod.getName(),
					testMethod.getAnnotation(Test.class));
		}
	}

	@Test
	public void checkUnusedEntry() {
		statechart = AbstractTestModelsUtil.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnusedEntryPoint.sct");
		Iterator<EObject> iter = statechart.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof Entry) {
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
		}

		assertIssueCount(diagnostics, 1);
		assertWarning(diagnostics, ENTRY_UNUSED);
	}

	@Test
	public void checkTopLeveEntryIsDefaultEntry() {
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TopLevelEntryIsDefaultEntryError.sct");
		doValidateAllContents(Entry.class);

		assertIssueCount(diagnostics, 1);
		assertError(diagnostics, TOP_LEVEL_REGION_ENTRY_HAVE_TO_BE_A_DEFAULT_ENTRY);
		resetDiagnostics();
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TopLevelEntryIsDefaultEntryWarn.sct");
		doValidateAllContents(Entry.class);

		assertIssueCount(diagnostics, 1);
		assertWarning(diagnostics, TOP_LEVEL_REGION_ENTRY_HAVE_TO_BE_A_DEFAULT_ENTRY);
	}

	@Test
	public void checkUnusedExit() {
		statechart = AbstractTestModelsUtil.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnusedExitPoint.sct");
		doValidateAllContents(Exit.class);

		assertIssueCount(diagnostics, 1);
		assertError(diagnostics, EXIT_UNUSED);

		resetDiagnostics();
		statechart = AbstractTestModelsUtil.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnusedDefaultExitPoint.sct");
		doValidateAllContents(Exit.class);

		assertIssueCount(diagnostics, 1);
		assertError(diagnostics, EXIT_DEFAULT_UNUSED);
	}

	protected void doValidateAllContents(Class<? extends EObject> clazz) {
		Iterator<EObject> iter = statechart.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (clazz.isInstance(element)) {
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
		}
	}

	protected void assertAllTransitionsAreValid(Class<? extends EObject> clazz) {
		Iterator<EObject> iter;
		iter = statechart.eAllContents();

		while (iter.hasNext()) {
			EObject element = iter.next();
			if (clazz.isInstance(element)) {
				assertTrue(validator.validate(element, diagnostics, new HashMap<Object, Object>()));
			}
		}
	}

	@Test
	public void checkTransitionPropertySpec() {
		// Test source state isn't composite
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TransitionEntrySpecNotComposite.sct");
		doValidateAllContents(Transition.class);
		// Test target state isn't composite
		assertIssueCount(diagnostics, 2);
		assertWarning(diagnostics, TRANSITION_ENTRY_SPEC_NOT_COMPOSITE);

		resetDiagnostics();
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TransitionExitSpecNotComposite.sct");
		assertAllTransitionsAreValid(Transition.class);

		assertIssueCount(diagnostics, 1);
		assertWarning(diagnostics, TRANSITION_EXIT_SPEC_NOT_COMPOSITE);

		// Test exit spec is used on multiple transition siblings.
		resetDiagnostics();
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TransitionExitSpecOnMultipleSiblings.sct");
		assertAllTransitionsAreValid(Transition.class);

		assertIssueCount(diagnostics, 4);
		assertWarning(diagnostics, TRANSITION_EXIT_SPEC_ON_MULTIPLE_SIBLINGS);

		// Test transition unbound named exit point spec.
		resetDiagnostics();
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "TransitionNotExistingNamedExitPoint.sct");
		doValidateAllContents(Transition.class);

		assertIssueCount(diagnostics, 1);
		assertError(diagnostics, TRANSITION_NOT_EXISTING_NAMED_EXIT_POINT);
	}

	@Test
	public void checkUnboundEntryPoints() {
		statechart = AbstractTestModelsUtil.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnboundDefaultEntryPoints.sct");
		Iterator<EObject> iter = statechart.eAllContents();

		// create and add triggers to all transitions to prevent to trigger
		// additional warnings
		// (see Check in SGrapJavaValidator transitionsWithNoGuard)
		Trigger trigger = StextFactoryImpl.init().createDefaultTrigger();

		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof Transition) {
				((Transition) element).setTrigger(trigger);
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
			if (element instanceof State) {
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
		}

		assertIssueCount(diagnostics, 4);
		assertError(diagnostics, TRANSITION_UNBOUND_DEFAULT_ENTRY_POINT);
		assertError(diagnostics, REGION_UNBOUND_DEFAULT_ENTRY_POINT);

		resetDiagnostics();
		statechart = AbstractTestModelsUtil.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnboundEntryPoints02.sct");
		iter = statechart.eAllContents();

		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof Transition) {
				((Transition) element).setTrigger(trigger);
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
			if (element instanceof State) {
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
		}
		assertIssueCount(diagnostics, 4);
	}

	@Test
	public void checkExitPointSpecWithTrigger() {
		statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "NoTriggerOnTransitionWithExitPointSpec.sct");
		Iterator<EObject> iter = statechart.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof Transition) {
				validator.validate(element, diagnostics, new HashMap<Object, Object>());
			}
		}

		assertIssueCount(diagnostics, 2);
		assertError(diagnostics, EXITPOINTSPEC_WITH_TRIGGER);
	}

	@Test
	public void checkAssignmentToFinalVariable() {
		Statechart statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "AssignmentToValue.sct");
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(statechart);
		assertIssueCount(diagnostics, 2);
		assertError(diagnostics, ASSIGNMENT_TO_VALUE);
	}

	@Test
	public void checkValueDefinitionExpression() {
		Statechart statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "ConstWithVariable.sct");
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(statechart);
		assertIssueCount(diagnostics, 2); //
		assertError(diagnostics, REFERENCE_TO_VARIABLE);
	}

	@Test
	public void checkValueReferenedBeforeDefined() {
		Statechart statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "ReferenceBeforeDefined.sct");
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(statechart);
		assertIssueCount(diagnostics, 2);
		assertError(diagnostics, REFERENCE_CONSTANT_BEFORE_DEFINED);
	}

	@Test
	public void checkUnusedVariablesInInternalScope() {
		Statechart statechart = AbstractTestModelsUtil
				.loadStatechart(VALIDATION_TESTMODEL_DIR + "UnusedInternalDeclarations.sct");
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(statechart);
		assertIssueCount(diagnostics, 3);
		assertWarning(diagnostics, INTERNAL_DECLARATION_UNUSED);
	}

	@Ignore("Test is not executed for TypesPackage elements")
	@Test
	public void checkAnnotationTarget() {

		Package pack = TypesFactory.eINSTANCE.createPackage();
		pack.setName("package");
		
		Annotation annotation = TypesFactory.eINSTANCE.createAnnotation();
		annotation.setName("OperationAnnotation");
		annotation.getTargets().add(TypesPackage.Literals.OPERATION);
		pack.getMember().add(annotation);
		
		Operation operation = TypesFactory.eINSTANCE.createOperation();
		operation.setName("MyOp");
		operation.getAnnotations().add(annotation);
		pack.getMember().add(operation);

		AssertableDiagnostics validationResult = tester.validate(pack);
		validationResult.assertOK();

		Type type = TypesFactory.eINSTANCE.createType();
		type.setName("primitive");
		type.getAnnotations().add(annotation);
		pack.getMember().add(type);

		validationResult = tester.validate(pack);
		validationResult.assertError(ERROR_WRONG_ANNOTATION_TARGET_CODE);
	}

	@Test
	public void transitionsWithNoTrigger() {
	}

}
