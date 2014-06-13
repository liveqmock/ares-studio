/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Related Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.procdure.RelatedInfo#getId <em>Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.RelatedInfo#getComment <em>Comment</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.RelatedInfo#getPath <em>Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getRelatedInfo()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='id'"
 * @generated
 */
public interface RelatedInfo extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getRelatedInfo_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getRelatedInfo_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getRelatedInfo_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

} // RelatedInfo
