/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getDecription <em>Decription</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOraclePrivilege()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name type'"
 * @generated
 */
public interface OraclePrivilege extends ExtensibleModel {
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
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOraclePrivilege_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOraclePrivilege_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

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
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOraclePrivilege_Decription()
	 * @model default=""
	 * @generated
	 */
	String getDecription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getDecription <em>Decription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decription</em>' attribute.
	 * @see #getDecription()
	 * @generated
	 */
	void setDecription(String value);

} // OraclePrivilege
