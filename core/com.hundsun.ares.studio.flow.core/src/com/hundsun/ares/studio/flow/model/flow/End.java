/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.End#getFigureNode <em>Figure Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getEnd()
 * @model abstract="true"
 * @generated
 */
public interface End extends IViewProvider {
	/**
	 * Returns the value of the '<em><b>Figure Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Figure Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Figure Node</em>' containment reference.
	 * @see #setFigureNode(FigureNode)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getEnd_FigureNode()
	 * @model containment="true"
	 * @generated
	 */
	FigureNode getFigureNode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.End#getFigureNode <em>Figure Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Figure Node</em>' containment reference.
	 * @see #getFigureNode()
	 * @generated
	 */
	void setFigureNode(FigureNode value);

} // End
