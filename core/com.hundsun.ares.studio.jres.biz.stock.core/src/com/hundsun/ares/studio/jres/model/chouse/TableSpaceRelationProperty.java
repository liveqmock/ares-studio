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
 * A representation of the model object '<em><b>Table Space Relation Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisSpace <em>His Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisIndexSpace <em>His Index Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileSpace <em>File Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileIndexSpace <em>File Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceRelationProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='hisSpace hisIndexSpace fileSpace fileIndexSpace'"
 * @generated
 */
public interface TableSpaceRelationProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>His Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>His Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>His Space</em>' attribute.
	 * @see #setHisSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceRelationProperty_HisSpace()
	 * @model
	 * @generated
	 */
	String getHisSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisSpace <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>His Space</em>' attribute.
	 * @see #getHisSpace()
	 * @generated
	 */
	void setHisSpace(String value);

	/**
	 * Returns the value of the '<em><b>His Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>His Index Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>His Index Space</em>' attribute.
	 * @see #setHisIndexSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceRelationProperty_HisIndexSpace()
	 * @model
	 * @generated
	 */
	String getHisIndexSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisIndexSpace <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>His Index Space</em>' attribute.
	 * @see #getHisIndexSpace()
	 * @generated
	 */
	void setHisIndexSpace(String value);

	/**
	 * Returns the value of the '<em><b>File Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Space</em>' attribute.
	 * @see #setFileSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceRelationProperty_FileSpace()
	 * @model
	 * @generated
	 */
	String getFileSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileSpace <em>File Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Space</em>' attribute.
	 * @see #getFileSpace()
	 * @generated
	 */
	void setFileSpace(String value);

	/**
	 * Returns the value of the '<em><b>File Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Index Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Index Space</em>' attribute.
	 * @see #setFileIndexSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getTableSpaceRelationProperty_FileIndexSpace()
	 * @model
	 * @generated
	 */
	String getFileIndexSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileIndexSpace <em>File Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Index Space</em>' attribute.
	 * @see #getFileIndexSpace()
	 * @generated
	 */
	void setFileIndexSpace(String value);

} // TableSpaceRelationProperty
