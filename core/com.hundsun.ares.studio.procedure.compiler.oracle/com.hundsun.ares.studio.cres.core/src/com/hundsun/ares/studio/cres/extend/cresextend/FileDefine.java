/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Define</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#isIsUsed <em>Is Used</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getFileDefine()
 * @model
 * @generated
 */
public interface FileDefine extends EObject {
	/**
	 * Returns the value of the '<em><b>Is Used</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Used</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Used</em>' attribute.
	 * @see #setIsUsed(boolean)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getFileDefine_IsUsed()
	 * @model default="true"
	 * @generated
	 */
	boolean isIsUsed();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#isIsUsed <em>Is Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Used</em>' attribute.
	 * @see #isIsUsed()
	 * @generated
	 */
	void setIsUsed(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' attribute.
	 * @see #setParameter(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getFileDefine_Parameter()
	 * @model default=""
	 * @generated
	 */
	String getParameter();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getFileDefine_Value()
	 * @model default=""
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Note</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Note</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Note</em>' attribute.
	 * @see #setNote(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getFileDefine_Note()
	 * @model default=""
	 * @generated
	 */
	String getNote();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getNote <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Note</em>' attribute.
	 * @see #getNote()
	 * @generated
	 */
	void setNote(String value);

} // FileDefine
