/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.usermacro;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Macro</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.usermacro.UserMacro#getMacroItems <em>Macro Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.usermacro.UserMacroPackage#getUserMacro()
 * @model
 * @generated
 */
public interface UserMacro extends com.hundsun.ares.studio.core.model.JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Macro Items</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.usermacro.UserMacroItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Macro Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Macro Items</em>' containment reference list.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroPackage#getUserMacro_MacroItems()
	 * @model containment="true"
	 * @generated
	 */
	EList<UserMacroItem> getMacroItems();

} // UserMacro
