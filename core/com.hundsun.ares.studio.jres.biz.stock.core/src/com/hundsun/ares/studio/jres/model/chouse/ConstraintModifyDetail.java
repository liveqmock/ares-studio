/**
 */
package com.hundsun.ares.studio.jres.model.chouse;

import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.key_type;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Modify Detail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getMark <em>Mark</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail()
 * @model
 * @generated
 */
public interface ConstraintModifyDetail extends EObject {
	/**
	 * Returns the value of the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 标志位
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mark</em>' attribute.
	 * @see #setMark(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail_Mark()
	 * @model
	 * @generated
	 */
	String getMark();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getMark <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mark</em>' attribute.
	 * @see #getMark()
	 * @generated
	 */
	void setMark(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 约束的名字
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableColumn}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 添加约束的时候指定的列的快照、副本
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail_Columns()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableColumn> getColumns();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.jres.model.database.key_type}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 类型，比如主键，外键，唯一
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @see #setType(key_type)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail_Type()
	 * @model
	 * @generated
	 */
	key_type getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @see #getType()
	 * @generated
	 */
	void setType(key_type value);

	/**
	 * Returns the value of the '<em><b>Foreign Key</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.ForeignKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getConstraintModifyDetail_ForeignKey()
	 * @model containment="true"
	 * @generated
	 */
	EList<ForeignKey> getForeignKey();

} // ConstraintModifyDetail
