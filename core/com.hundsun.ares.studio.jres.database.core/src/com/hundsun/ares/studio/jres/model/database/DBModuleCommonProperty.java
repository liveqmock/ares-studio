/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB Module Common Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getSupportDatabases <em>Support Databases</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getDBModuleCommonProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='database supportDatabases'"
 * @generated
 */
public interface DBModuleCommonProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Database</b></em>' attribute.
	 * The default value is <code>"Oracle"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' attribute.
	 * @see #setDatabase(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getDBModuleCommonProperty_Database()
	 * @model default="Oracle"
	 * @generated
	 */
	String getDatabase();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getDatabase <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' attribute.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(String value);

	/**
	 * Returns the value of the '<em><b>Support Databases</b></em>' attribute.
	 * The default value is <code>"Oracle,DB2,MySQL,SQL Sever"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Support Databases</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Support Databases</em>' attribute.
	 * @see #setSupportDatabases(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getDBModuleCommonProperty_SupportDatabases()
	 * @model default="Oracle,DB2,MySQL,SQL Sever"
	 * @generated
	 */
	String getSupportDatabases();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getSupportDatabases <em>Support Databases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Support Databases</em>' attribute.
	 * @see #getSupportDatabases()
	 * @generated
	 */
	void setSupportDatabases(String value);

} // DBModuleCommonProperty
