/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.RGB;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Color View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getFrontground <em>Frontground</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getBackground <em>Background</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getColorView()
 * @model
 * @generated
 */
public interface ColorView extends EObject {
	/**
	 * Returns the value of the '<em><b>Frontground</b></em>' attribute.
	 * The default value is <code>"0,0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frontground</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frontground</em>' attribute.
	 * @see #setFrontground(RGB)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getColorView_Frontground()
	 * @model default="0,0,0" dataType="com.hundsun.ares.studio.flow.model.flow.RGB"
	 * @generated
	 */
	RGB getFrontground();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getFrontground <em>Frontground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frontground</em>' attribute.
	 * @see #getFrontground()
	 * @generated
	 */
	void setFrontground(RGB value);

	/**
	 * Returns the value of the '<em><b>Background</b></em>' attribute.
	 * The default value is <code>"255,255,255"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background</em>' attribute.
	 * @see #setBackground(RGB)
	 * @see com.hundsun.ares.studio.flow.model.flow.FlowPackage#getColorView_Background()
	 * @model default="255,255,255" dataType="com.hundsun.ares.studio.flow.model.flow.RGB"
	 * @generated
	 */
	RGB getBackground();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.flow.model.flow.ColorView#getBackground <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' attribute.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(RGB value);

} // ColorView
