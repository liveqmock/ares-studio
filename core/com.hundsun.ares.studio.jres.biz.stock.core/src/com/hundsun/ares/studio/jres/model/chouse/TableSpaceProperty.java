/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Space Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getReduTable <em>Redu Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTable <em>Chear Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTableIndex <em>Chear Table Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='reduTable chearTable'"
 * @generated
 */
public interface TableSpaceProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Redu Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redu Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redu Table</em>' attribute.
	 * @see #setReduTable(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceProperty_ReduTable()
	 * @model
	 * @generated
	 */
	String getReduTable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getReduTable <em>Redu Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redu Table</em>' attribute.
	 * @see #getReduTable()
	 * @generated
	 */
	void setReduTable(String value);

	/**
	 * Returns the value of the '<em><b>Chear Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chear Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chear Table</em>' attribute.
	 * @see #setChearTable(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceProperty_ChearTable()
	 * @model
	 * @generated
	 */
	String getChearTable();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTable <em>Chear Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chear Table</em>' attribute.
	 * @see #getChearTable()
	 * @generated
	 */
	void setChearTable(String value);

	/**
	 * Returns the value of the '<em><b>Chear Table Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chear Table Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chear Table Index</em>' attribute.
	 * @see #setChearTableIndex(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceProperty_ChearTableIndex()
	 * @model
	 * @generated
	 */
	String getChearTableIndex();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTableIndex <em>Chear Table Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chear Table Index</em>' attribute.
	 * @see #getChearTableIndex()
	 * @generated
	 */
	void setChearTableIndex(String value);

} // TableSpaceProperty
