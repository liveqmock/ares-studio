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
 * A representation of the model object '<em><b>RTCM Detail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getNewName <em>New Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getOldName <em>Old Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRTCMDetail()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='newName'"
 * @generated
 */
public interface RTCMDetail extends ColumnChangeDetail {
	/**
	 * Returns the value of the '<em><b>New Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Name</em>' attribute.
	 * @see #setNewName(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRTCMDetail_NewName()
	 * @model default=""
	 * @generated
	 */
	String getNewName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getNewName <em>New Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Name</em>' attribute.
	 * @see #getNewName()
	 * @generated
	 */
	void setNewName(String value);

	/**
	 * Returns the value of the '<em><b>Old Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Old Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @deprecated
	 * 为兼容老系统保留这个属性； 推荐使用getSnapshot().getName()代替
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Old Name</em>' attribute.
	 * @see #setOldName(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRTCMDetail_OldName()
	 * @model default="" derived="true"
	 * @generated
	 */
	String getOldName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getOldName <em>Old Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Name</em>' attribute.
	 * @see #getOldName()
	 * @generated
	 */
	void setOldName(String value);

	/**
	 * Returns the value of the '<em><b>Mark</b></em>' attribute.
	 * The default value is <code>""</code>.
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
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRTCMDetail_Mark()
	 * @model default=""
	 * @generated
	 */
	String getMark();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getMark <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mark</em>' attribute.
	 * @see #getMark()
	 * @generated
	 */
	void setMark(String value);

} // RTCMDetail
