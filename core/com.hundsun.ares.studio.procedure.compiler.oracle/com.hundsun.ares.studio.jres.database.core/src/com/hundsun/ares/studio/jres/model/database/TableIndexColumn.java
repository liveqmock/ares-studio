/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Index Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#isAscending <em>Ascending</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnType <em>Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableIndexColumn()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='columnName'"
 * @generated
 */
public interface TableIndexColumn extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Name</em>' attribute.
	 * @see #setColumnName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableIndexColumn_ColumnName()
	 * @model
	 * @generated
	 */
	String getColumnName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnName <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Name</em>' attribute.
	 * @see #getColumnName()
	 * @generated
	 */
	void setColumnName(String value);

	/**
	 * Returns the value of the '<em><b>Ascending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ascending</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ascending</em>' attribute.
	 * @see #setAscending(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableIndexColumn_Ascending()
	 * @model
	 * @generated
	 */
	boolean isAscending();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#isAscending <em>Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ascending</em>' attribute.
	 * @see #isAscending()
	 * @generated
	 */
	void setAscending(boolean value);

	/**
	 * Returns the value of the '<em><b>Column Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.jres.model.database.ColumnType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @see #setColumnType(ColumnType)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableIndexColumn_ColumnType()
	 * @model
	 * @generated
	 */
	ColumnType getColumnType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnType <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @see #getColumnType()
	 * @generated
	 */
	void setColumnType(ColumnType value);

} // TableIndexColumn
