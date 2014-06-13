/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Figure Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getColorView <em>Color View</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFigureNode()
 * @model
 * @generated
 */
public interface FigureNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFigureNode_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFigureNode_ColorView()
	 * @model containment="true"
	 * @generated
	 */
	ColorView getColorView();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getColorView <em>Color View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color View</em>' containment reference.
	 * @see #getColorView()
	 * @generated
	 */
	void setColorView(ColorView value);

	/**
	 * Returns the value of the '<em><b>Bounds</b></em>' attribute.
	 * The default value is <code>"0,0,0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bounds</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bounds</em>' attribute.
	 * @see #setBounds(Rectangle)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getFigureNode_Bounds()
	 * @model default="0,0,0,0" dataType="com.hundsun.ares.studio.flow.model.flow.Rectangle"
	 * @generated
	 */
	Rectangle getBounds();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.FigureNode#getBounds <em>Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bounds</em>' attribute.
	 * @see #getBounds()
	 * @generated
	 */
	void setBounds(Rectangle value);

} // FigureNode
