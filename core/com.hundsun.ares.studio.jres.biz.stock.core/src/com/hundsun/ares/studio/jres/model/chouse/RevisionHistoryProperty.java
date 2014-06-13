/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import com.hundsun.ares.studio.core.model.IReferenceProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Revision History Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getAction <em>Action</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionType <em>Action Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionDescription <em>Action Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRevisionHistoryProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='action'"
 * @generated
 */
public interface RevisionHistoryProperty extends HistoryProperty, IReferenceProvider {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Modification)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRevisionHistoryProperty_Action()
	 * @model containment="true"
	 * @generated
	 */
	Modification getAction();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Modification value);

	/**
	 * Returns the value of the '<em><b>Action Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Type</em>' attribute.
	 * @see #setActionType(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRevisionHistoryProperty_ActionType()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getActionType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionType <em>Action Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Type</em>' attribute.
	 * @see #getActionType()
	 * @generated
	 */
	void setActionType(String value);

	/**
	 * Returns the value of the '<em><b>Action Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Description</em>' attribute.
	 * @see #setActionDescription(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRevisionHistoryProperty_ActionDescription()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getActionDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionDescription <em>Action Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Description</em>' attribute.
	 * @see #getActionDescription()
	 * @generated
	 */
	void setActionDescription(String value);

} // RevisionHistoryProperty
