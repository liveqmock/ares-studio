/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.atom.AtomFunction#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.AtomFunction#getPseudoCode <em>Pseudo Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.atom.AtomFunction#getInternalVariables <em>Internal Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomFunction()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface AtomFunction extends BizInterface, JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' attribute.
	 * @see #setDatabase(String)
	 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomFunction_Database()
	 * @model
	 * @generated
	 */
	String getDatabase();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.atom.AtomFunction#getDatabase <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' attribute.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(String value);

	/**
	 * Returns the value of the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pseudo Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pseudo Code</em>' attribute.
	 * @see #setPseudoCode(String)
	 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomFunction_PseudoCode()
	 * @model
	 * @generated
	 */
	String getPseudoCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.atom.AtomFunction#getPseudoCode <em>Pseudo Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pseudo Code</em>' attribute.
	 * @see #getPseudoCode()
	 * @generated
	 */
	void setPseudoCode(String value);

	/**
	 * Returns the value of the '<em><b>Internal Variables</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.atom.InternalParam}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Variables</em>' containment reference list.
	 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomFunction_InternalVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<InternalParam> getInternalVariables();

} // AtomFunction
