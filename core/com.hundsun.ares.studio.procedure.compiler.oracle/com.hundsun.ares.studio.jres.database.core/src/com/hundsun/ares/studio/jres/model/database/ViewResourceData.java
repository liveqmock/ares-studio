/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#getSql <em>Sql</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#isIsHistory <em>Is History</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getViewResourceData()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name sql objectId'"
 * @generated
 */
public interface ViewResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Sql</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql</em>' attribute.
	 * @see #setSql(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getViewResourceData_Sql()
	 * @model default=""
	 * @generated
	 */
	String getSql();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#getSql <em>Sql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql</em>' attribute.
	 * @see #getSql()
	 * @generated
	 */
	void setSql(String value);

	/**
	 * Returns the value of the '<em><b>Is History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is History</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is History</em>' attribute.
	 * @see #setIsHistory(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getViewResourceData_IsHistory()
	 * @model
	 * @generated
	 */
	boolean isIsHistory();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#isIsHistory <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is History</em>' attribute.
	 * @see #isIsHistory()
	 * @generated
	 */
	void setIsHistory(boolean value);

} // ViewResourceData
