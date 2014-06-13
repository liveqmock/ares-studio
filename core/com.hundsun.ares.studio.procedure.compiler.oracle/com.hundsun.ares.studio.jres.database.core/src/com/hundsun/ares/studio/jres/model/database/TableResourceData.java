/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * 数据库表对象模型，版本历史获取需要使用{@link #getModifyHistories()}
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableResourceData()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name objectId'"
 * @generated
 */
public interface TableResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableColumn}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableResourceData_Columns()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableColumn> getColumns();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableIndex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableResourceData_Indexes()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableIndex> getIndexes();

	/**
	 * Returns the value of the '<em><b>Keys</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableResourceData_Keys()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableKey> getKeys();

} // TableResourceData
