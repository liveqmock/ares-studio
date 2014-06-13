/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic;

import com.hundsun.ares.studio.atom.AtomFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.logic.LogicFunction#isIsTransFunc <em>Is Trans Func</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.logic.LogicPackage#getLogicFunction()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface LogicFunction extends AtomFunction {
	/**
	 * Returns the value of the '<em><b>Is Trans Func</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Trans Func</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Trans Func</em>' attribute.
	 * @see #setIsTransFunc(boolean)
	 * @see com.hundsun.ares.studio.logic.LogicPackage#getLogicFunction_IsTransFunc()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsTransFunc();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.logic.LogicFunction#isIsTransFunc <em>Is Trans Func</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Trans Func</em>' attribute.
	 * @see #isIsTransFunc()
	 * @generated
	 */
	void setIsTransFunc(boolean value);

} // LogicFunction
