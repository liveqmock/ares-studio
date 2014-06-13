/**
 */
package com.hundsun.ares.studio.biz;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ARES Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.ARESObject#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.biz.BizPackage#getARESObject()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface ARESObject extends JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.biz.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see com.hundsun.ares.studio.biz.BizPackage#getARESObject_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getProperties();

} // ARESObject
