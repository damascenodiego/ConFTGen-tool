/**
 */
package org.yakindu.base.expressions.expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shift Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.expressions.expressions.ShiftExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getShiftExpression()
 * @model
 * @generated
 */
public interface ShiftExpression extends BinaryExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.yakindu.base.expressions.expressions.ShiftOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.yakindu.base.expressions.expressions.ShiftOperator
	 * @see #setOperator(ShiftOperator)
	 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getShiftExpression_Operator()
	 * @model
	 * @generated
	 */
	ShiftOperator getOperator();

	/**
	 * Sets the value of the '{@link org.yakindu.base.expressions.expressions.ShiftExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.yakindu.base.expressions.expressions.ShiftOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ShiftOperator value);

} // ShiftExpression
