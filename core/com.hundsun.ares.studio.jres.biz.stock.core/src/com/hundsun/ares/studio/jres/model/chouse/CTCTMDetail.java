/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CTCTM Detail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getNewType <em>New Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getCTCTMDetail()
 * @model
 * @generated
 */
public interface CTCTMDetail extends ColumnChangeDetail {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @deprecated
	 * 为兼容老系统保留这个属性； 推荐使用getSnapshot().getName()代替
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getCTCTMDetail_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>New Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Type</em>' attribute.
	 * @see #setNewType(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getCTCTMDetail_NewType()
	 * @model default=""
	 * @generated
	 */
	String getNewType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getNewType <em>New Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Type</em>' attribute.
	 * @see #getNewType()
	 * @generated
	 */
	void setNewType(String value);

	/**
	 * Returns the value of the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @deprecated
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mark</em>' attribute.
	 * @see #setMark(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getCTCTMDetail_Mark()
	 * @model
	 * @generated
	 */
	String getMark();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getMark <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mark</em>' attribute.
	 * @see #getMark()
	 * @generated
	 */
	void setMark(String value);

} // CTCTMDetail
