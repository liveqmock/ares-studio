/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Basic Data Base Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getExtend <em>Extend</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getBasicDataBaseModel()
 * @model
 * @generated
 */
public interface BasicDataBaseModel extends MetadataResourceData<BasicdataItem> {

	/**
	 * Returns the value of the '<em><b>Extend</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extend</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extend</em>' attribute.
	 * @see #setExtend(String)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getBasicDataBaseModel_Extend()
	 * @model default=""
	 * @generated
	 */
	String getExtend();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getExtend <em>Extend</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extend</em>' attribute.
	 * @see #getExtend()
	 * @generated
	 */
	void setExtend(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getBasicDataBaseModel_File()
	 * @model
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);
} // BasicDataBaseModel
