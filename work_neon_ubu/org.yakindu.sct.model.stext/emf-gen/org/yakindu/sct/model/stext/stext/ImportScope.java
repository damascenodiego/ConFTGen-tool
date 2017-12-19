/**
 */
package org.yakindu.sct.model.stext.stext;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.sct.model.stext.stext.ImportScope#getImports <em>Imports</em>}</li>
 * </ul>
 *
 * @see org.yakindu.sct.model.stext.stext.StextPackage#getImportScope()
 * @model
 * @generated
 */
public interface ImportScope extends StatechartScope {
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' reference list.
	 * The list contents are of type {@link org.yakindu.base.types.Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' reference list.
	 * @see org.yakindu.sct.model.stext.stext.StextPackage#getImportScope_Imports()
	 * @model
	 * @generated
	 */
	EList<org.yakindu.base.types.Package> getImports();

} // ImportScope
