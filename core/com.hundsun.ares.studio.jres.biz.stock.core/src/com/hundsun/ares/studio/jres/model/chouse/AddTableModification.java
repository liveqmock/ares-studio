/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableKey;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Table Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewSelfTable <em>New Self Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewHistoryTable <em>New History Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification()
 * @model
 * @generated
 */
public interface AddTableModification extends Modification {
	/**
	 * Returns the value of the '<em><b>New Self Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Self Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Self Table</em>' attribute.
	 * @see #setNewSelfTable(boolean)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification_NewSelfTable()
	 * @model
	 * @generated
	 */
	boolean isNewSelfTable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewSelfTable <em>New Self Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Self Table</em>' attribute.
	 * @see #isNewSelfTable()
	 * @generated
	 */
	void setNewSelfTable(boolean value);

	/**
	 * Returns the value of the '<em><b>New History Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New History Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New History Table</em>' attribute.
	 * @see #setNewHistoryTable(boolean)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification_NewHistoryTable()
	 * @model
	 * @generated
	 */
	boolean isNewHistoryTable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewHistoryTable <em>New History Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New History Table</em>' attribute.
	 * @see #isNewHistoryTable()
	 * @generated
	 */
	void setNewHistoryTable(boolean value);

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
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification_Columns()
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
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification_Indexes()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableIndex> getIndexes();

	/**
	 * Returns the value of the '<em><b>Keys</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddTableModification_Keys()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableKey> getKeys();

} // AddTableModification
