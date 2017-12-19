/**
 */
package org.yakindu.base.expressions.expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.expressions.expressions.FloatLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getFloatLiteral()
 * @model
 * @generated
 */
public interface FloatLiteral extends Literal {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(float)
	 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getFloatLiteral_Value()
	 * @model
	 * @generated
	 */
	float getValue();

	/**
	 * Sets the value of the '{@link org.yakindu.base.expressions.expressions.FloatLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(float value);

} // FloatLiteral
