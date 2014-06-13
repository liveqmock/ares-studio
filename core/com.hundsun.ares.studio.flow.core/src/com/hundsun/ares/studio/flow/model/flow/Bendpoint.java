/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getFirstRelative <em>First Relative</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getSecondRelative <em>Second Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getBendpoint()
 * @model
 * @generated
 */
public interface Bendpoint extends EObject {
	/**
	 * Returns the value of the '<em><b>First Relative</b></em>' attribute.
	 * The default value is <code>"0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Relative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Relative</em>' attribute.
	 * @see #setFirstRelative(Dimension)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getBendpoint_FirstRelative()
	 * @model default="0,0" dataType="com.hundsun.ares.studio.flow.model.flow.Dimension"
	 * @generated
	 */
	Dimension getFirstRelative();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getFirstRelative <em>First Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Relative</em>' attribute.
	 * @see #getFirstRelative()
	 * @generated
	 */
	void setFirstRelative(Dimension value);

	/**
	 * Returns the value of the '<em><b>Second Relative</b></em>' attribute.
	 * The default value is <code>"0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second Relative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second Relative</em>' attribute.
	 * @see #setSecondRelative(Dimension)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getBendpoint_SecondRelative()
	 * @model default="0,0" dataType="com.hundsun.ares.studio.flow.model.flow.Dimension"
	 * @generated
	 */
	Dimension getSecondRelative();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.Bendpoint#getSecondRelative <em>Second Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second Relative</em>' attribute.
	 * @see #getSecondRelative()
	 * @generated
	 */
	void setSecondRelative(Dimension value);

} // Bendpoint
