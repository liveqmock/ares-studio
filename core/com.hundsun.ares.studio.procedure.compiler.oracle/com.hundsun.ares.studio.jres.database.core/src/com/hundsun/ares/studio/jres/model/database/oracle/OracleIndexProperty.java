/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty#isReverse <em>Reverse</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleIndexProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='reverse'"
 * @generated
 */
public interface OracleIndexProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reverse</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reverse</em>' attribute.
	 * @see #setReverse(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleIndexProperty_Reverse()
	 * @model
	 * @generated
	 */
	boolean isReverse();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty#isReverse <em>Reverse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reverse</em>' attribute.
	 * @see #isReverse()
	 * @generated
	 */
	void setReverse(boolean value);

} // OracleIndexProperty
