/**
 */
package com.hundsun.ares.studio.biz;

import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Obj Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.StandardObjField#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.biz.BizPackage#getStandardObjField()
 * @model
 * @generated
 */
public interface StandardObjField extends MetadataItem {

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getStandardObjField_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.StandardObjField#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);
} // StandardObjField
