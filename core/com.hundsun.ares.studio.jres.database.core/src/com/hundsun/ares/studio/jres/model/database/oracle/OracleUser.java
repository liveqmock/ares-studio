/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDecription <em>Decription</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#isEnable <em>Enable</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPrivileges <em>Privileges</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPassword <em>Password</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDefaultTableSpace <em>Default Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name attributes password'"
 * @generated
 */
public interface OracleUser extends ExtensibleModel {
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
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Decription</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decription</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decription</em>' attribute.
	 * @see #setDecription(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Decription()
	 * @model default=""
	 * @generated
	 */
	String getDecription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDecription <em>Decription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decription</em>' attribute.
	 * @see #getDecription()
	 * @generated
	 */
	void setDecription(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' attribute.
	 * @see #setAttributes(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Attributes()
	 * @model default=""
	 * @generated
	 */
	String getAttributes();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getAttributes <em>Attributes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attributes</em>' attribute.
	 * @see #getAttributes()
	 * @generated
	 */
	void setAttributes(String value);

	/**
	 * Returns the value of the '<em><b>Enable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable</em>' attribute.
	 * @see #setEnable(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Enable()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#isEnable <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable</em>' attribute.
	 * @see #isEnable()
	 * @generated
	 */
	void setEnable(boolean value);

	/**
	 * Returns the value of the '<em><b>Privileges</b></em>' reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privileges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privileges</em>' reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Privileges()
	 * @model
	 * @generated
	 */
	EList<OraclePrivilege> getPrivileges();

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_Password()
	 * @model default=""
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Default Table Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Table Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Table Space</em>' attribute.
	 * @see #setDefaultTableSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUser_DefaultTableSpace()
	 * @model default=""
	 * @generated
	 */
	String getDefaultTableSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDefaultTableSpace <em>Default Table Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Table Space</em>' attribute.
	 * @see #getDefaultTableSpace()
	 * @generated
	 */
	void setDefaultTableSpace(String value);

} // OracleUser
