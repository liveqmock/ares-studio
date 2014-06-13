/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getUsers <em>Users</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getPrivileges <em>Privileges</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUserResourceData()
 * @model
 * @generated
 */
public interface OracleUserResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Users</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUserResourceData_Users()
	 * @model containment="true"
	 * @generated
	 */
	EList<OracleUser> getUsers();

	/**
	 * Returns the value of the '<em><b>Privileges</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privileges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privileges</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleUserResourceData_Privileges()
	 * @model containment="true"
	 * @generated
	 */
	EList<OraclePrivilege> getPrivileges();

} // OracleUserResourceData
