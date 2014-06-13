/**
 */
package com.hundsun.ares.studio.jres.model.chouse;

import com.hundsun.ares.studio.jres.model.database.TableColumn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Change Detail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail#getSnapshot <em>Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getColumnChangeDetail()
 * @model
 * @generated
 */
public interface ColumnChangeDetail extends EObject {
	/**
	 * Returns the value of the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 基于列的修改Details描述类，每次修改都把列的当前状态作为一个快照保存
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Snapshot</em>' containment reference.
	 * @see #setSnapshot(TableColumn)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getColumnChangeDetail_Snapshot()
	 * @model containment="true"
	 * @generated
	 */
	TableColumn getSnapshot();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail#getSnapshot <em>Snapshot</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snapshot</em>' containment reference.
	 * @see #getSnapshot()
	 * @generated
	 */
	void setSnapshot(TableColumn value);

} // ColumnChangeDetail
