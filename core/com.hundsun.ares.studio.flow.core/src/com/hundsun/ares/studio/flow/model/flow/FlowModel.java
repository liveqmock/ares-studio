/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getBegin <em>Begin</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getEnds <em>Ends</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getNodes <em>Nodes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFlowModel()
 * @model abstract="true"
 * @generated
 */
public interface FlowModel<T extends FlowNode, K extends Begin, V extends End> extends EObject {
	/**
	 * Returns the value of the '<em><b>Begin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Begin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Begin</em>' containment reference.
	 * @see #setBegin(Begin)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFlowModel_Begin()
	 * @model containment="true"
	 * @generated
	 */
	K getBegin();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.FlowModel#getBegin <em>Begin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Begin</em>' containment reference.
	 * @see #getBegin()
	 * @generated
	 */
	void setBegin(K value);

	/**
	 * Returns the value of the '<em><b>Ends</b></em>' containment reference list.
	 * The list contents are of type {@link V}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ends</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ends</em>' containment reference list.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFlowModel_Ends()
	 * @model containment="true"
	 * @generated
	 */
	EList<V> getEnds();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link T}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFlowModel_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<T> getNodes();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.flow.model.flow.Connection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFlowModel_Connections()
	 * @model containment="true"
	 * @generated
	 */
	EList<Connection> getConnections();

} // FlowModel
