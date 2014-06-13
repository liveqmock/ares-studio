/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableKey#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableKey#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableKey#getForeignKey <em>Foreign Key</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableKey#getColumns <em>Columns</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.TableKey#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name columns'"
 * @generated
 */
public interface TableKey extends ExtensibleModel {
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
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.jres.model.database.key_type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @see #setType(key_type)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey_Type()
	 * @model
	 * @generated
	 */
	key_type getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @see #getType()
	 * @generated
	 */
	void setType(key_type value);

	/**
	 * Returns the value of the '<em><b>Columns</b></em>' reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableColumn}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey_Columns()
	 * @model
	 * @generated
	 */
	EList<TableColumn> getColumns();

	/**
	 * Returns the value of the '<em><b>Mark</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mark</em>' attribute.
	 * @see #setMark(String)
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey_Mark()
	 * @model default=""
	 * @generated
	 */
	String getMark();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getMark <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mark</em>' attribute.
	 * @see #getMark()
	 * @generated
	 */
	void setMark(String value);

	/**
	 * Returns the value of the '<em><b>Foreign Key</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.ForeignKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreign Key</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Key</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage#getTableKey_ForeignKey()
	 * @model containment="true"
	 * @generated
	 */
	EList<ForeignKey> getForeignKey();

} // TableKey
