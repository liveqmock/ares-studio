/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Field Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn#getStandardField <em>Standard Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldColumn()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='standardField'"
 * @generated
 */
public interface StandardFieldColumn extends EObject {
	/**
	 * Returns the value of the '<em><b>Standard Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Standard Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Standard Field</em>' attribute.
	 * @see #setStandardField(String)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldColumn_StandardField()
	 * @model
	 * @generated
	 */
	String getStandardField();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn#getStandardField <em>Standard Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Field</em>' attribute.
	 * @see #getStandardField()
	 * @generated
	 */
	void setStandardField(String value);

} // StandardFieldColumn
