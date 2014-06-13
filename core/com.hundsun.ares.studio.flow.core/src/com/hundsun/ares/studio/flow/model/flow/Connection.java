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
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Connection#getTarget <em>Target</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Connection#getSource <em>Source</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Connection#getColorView <em>Color View</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Connection#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Connection#getBendpoints <em>Bendpoints</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(IViewProvider)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection_Target()
	 * @model
	 * @generated
	 */
	IViewProvider getTarget();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(IViewProvider value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(IViewProvider)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection_Source()
	 * @model
	 * @generated
	 */
	IViewProvider getSource();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(IViewProvider value);

	/**
	 * Returns the value of the '<em><b>Color View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color View</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color View</em>' containment reference.
	 * @see #setColorView(ColorView)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection_ColorView()
	 * @model containment="true"
	 * @generated
	 */
	ColorView getColorView();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getColorView <em>Color View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color View</em>' containment reference.
	 * @see #getColorView()
	 * @generated
	 */
	void setColorView(ColorView value);

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
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection_Value()
	 * @model default=""
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Connection#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.flow.model.flow.Bendpoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' containment reference list.
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getConnection_Bendpoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bendpoint> getBendpoints();

} // Connection
