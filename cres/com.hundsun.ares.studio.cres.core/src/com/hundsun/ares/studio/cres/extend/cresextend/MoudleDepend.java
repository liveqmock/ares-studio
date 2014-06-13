/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Moudle Depend</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getModulePath <em>Module Path</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getMoudleDepend()
 * @model
 * @generated
 */
public interface MoudleDepend extends EObject {
	/**
	 * Returns the value of the '<em><b>Module Path</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Path</em>' attribute.
	 * @see #setModulePath(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getMoudleDepend_ModulePath()
	 * @model default=""
	 * @generated
	 */
	String getModulePath();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getModulePath <em>Module Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Path</em>' attribute.
	 * @see #getModulePath()
	 * @generated
	 */
	void setModulePath(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getMoudleDepend_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // MoudleDepend
