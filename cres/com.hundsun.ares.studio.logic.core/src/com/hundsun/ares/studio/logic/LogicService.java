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
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.logic.LogicService#isIsCheckAccess <em>Is Check Access</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.logic.LogicService#getTimeOut <em>Time Out</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.logic.LogicPackage#getLogicService()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface LogicService extends AtomFunction {
	/**
	 * Returns the value of the '<em><b>Is Check Access</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Check Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Check Access</em>' attribute.
	 * @see #setIsCheckAccess(boolean)
	 * @see com.hundsun.ares.studio.logic.LogicPackage#getLogicService_IsCheckAccess()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsCheckAccess();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.logic.LogicService#isIsCheckAccess <em>Is Check Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Check Access</em>' attribute.
	 * @see #isIsCheckAccess()
	 * @generated
	 */
	void setIsCheckAccess(boolean value);

	/**
	 * Returns the value of the '<em><b>Time Out</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Out</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Out</em>' attribute.
	 * @see #setTimeOut(String)
	 * @see com.hundsun.ares.studio.logic.LogicPackage#getLogicService_TimeOut()
	 * @model default=""
	 * @generated
	 */
	String getTimeOut();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.logic.LogicService#getTimeOut <em>Time Out</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Out</em>' attribute.
	 * @see #getTimeOut()
	 * @generated
	 */
	void setTimeOut(String value);

} // LogicService
