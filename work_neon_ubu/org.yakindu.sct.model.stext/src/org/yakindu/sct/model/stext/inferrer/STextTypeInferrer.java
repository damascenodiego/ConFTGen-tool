/**
 * Copyright (c) 2014 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.model.stext.inferrer;

import static org.yakindu.base.types.typesystem.ITypeSystem.BOOLEAN;
import static org.yakindu.base.types.typesystem.ITypeSystem.INTEGER;
import static org.yakindu.base.types.typesystem.ITypeSystem.VOID;

import org.eclipse.emf.ecore.EObject;
import org.yakindu.base.expressions.expressions.ElementReferenceExpression;
import org.yakindu.base.expressions.expressions.Expression;
import org.yakindu.base.expressions.expressions.FeatureCall;
import org.yakindu.base.expressions.inferrer.ExpressionsTypeInferrer;
import org.yakindu.base.types.Event;
import org.yakindu.sct.model.sgraph.Scope;
import org.yakindu.sct.model.stext.stext.ActiveStateReferenceExpression;
import org.yakindu.sct.model.stext.stext.EventRaisingExpression;
import org.yakindu.sct.model.stext.stext.EventValueReferenceExpression;
import org.yakindu.sct.model.stext.stext.Guard;
import org.yakindu.sct.model.stext.stext.TimeEventSpec;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

/**
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class STextTypeInferrer extends ExpressionsTypeInferrer {

	public static final String VARIABLE_DEFINITION = "Cannot assign a value of type %s to a variable of type %s.";
	public static final String EVENT_DEFINITION = "Cannot assign a value of type %s to an event of type %s.";
	public static final String GUARD = "The evaluation result of a guard expression must be of type boolean.";
	public static final String TIME_SPEC = "The evaluation result of a time expression must be of type integer.";
	public static final String MISSING_VALUE = "Need to assign a value to an event of type %s.";

	public InferenceResult doInfer(VariableDefinition e) {
		InferenceResult result = inferTypeDispatch(e.getTypeSpecifier());
		assertNotType(result, VARIABLE_VOID_TYPE, getResultFor(VOID));
		if (e.getInitialValue() == null)
			return result;
		InferenceResult result2 = inferTypeDispatch(e.getInitialValue());
		assertAssignable(result, result2, String.format(VARIABLE_DEFINITION, result2, result));
		return result;
	}

	
	
	public InferenceResult doInfer(Event e) {
		// if an event is used within an expression, the type is boolean and the
		// value indicates if the event is raised or not
		return getResultFor(BOOLEAN);
	}

	public InferenceResult doInfer(Guard e) {
		InferenceResult result = inferTypeDispatch(e.getExpression());
		assertIsSubType(result, getResultFor(BOOLEAN), GUARD);
		return result;
	}

	public InferenceResult doInfer(TimeEventSpec e) {
		InferenceResult result = inferTypeDispatch(e.getValue());
		assertIsSubType(result, getResultFor(INTEGER), TIME_SPEC);
		return inferTypeDispatch(result.getType());
	}

	public InferenceResult doInfer(Scope scope) {
		return getResultFor(VOID);
	}

	public InferenceResult doInfer(EventValueReferenceExpression e) {
		Event definition = deresolve(e.getValue());
		if (definition != null)
			return definition.getTypeSpecifier() == null ? getResultFor(VOID) : inferTypeDispatch(definition.getTypeSpecifier());
		return inferTypeDispatch(e.getValue());
	}

	public InferenceResult doInfer(EventRaisingExpression e) {
		Event event = deresolve(e.getEvent());
		InferenceResult eventType = null;
		if(event != null)
			eventType = inferTypeDispatch(event.getTypeSpecifier());
		eventType = eventType != null ? eventType : getResultFor(VOID);
		if (e.getValue() == null) {
			assertSame(eventType, getResultFor(VOID), String.format(MISSING_VALUE, eventType));
			return getResultFor(VOID);
		}
		InferenceResult valueType = inferTypeDispatch(e.getValue());
		assertAssignable(eventType, valueType, String.format(EVENT_DEFINITION, valueType, eventType));
		return valueType;

	}

	protected Event deresolve(Expression e) {
		// TODO This is ugly -> reuse the TypeTrace to determine the context in
		// infer(Event)
		if (e instanceof ElementReferenceExpression) {
			EObject reference = ((ElementReferenceExpression) e).getReference();
			if (reference instanceof Event) {
				return (Event) reference;
			}
		}
		if (e instanceof FeatureCall) {
			EObject reference = ((FeatureCall) e).getFeature();
			if (reference instanceof Event) {
				return (Event) reference;
			}
		}
		return null;
	}

	public InferenceResult doInfer(ActiveStateReferenceExpression e) {
		return getResultFor(BOOLEAN);
	}

}
