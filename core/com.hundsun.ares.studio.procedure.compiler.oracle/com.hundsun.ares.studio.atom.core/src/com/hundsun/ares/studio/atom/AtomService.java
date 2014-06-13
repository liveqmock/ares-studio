/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.atom.AtomService#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomService()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface AtomService extends AtomFunction {
	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(String)
	 * @see com.hundsun.ares.studio.atom.AtomPackage#getAtomService_Timeout()
	 * @model
	 * @generated
	 */
	String getTimeout();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.atom.AtomService#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(String value);

} // AtomService
