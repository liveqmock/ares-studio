/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Data Config Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getId <em>Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getDiscription <em>Discription</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem()
 * @model
 * @generated
 */
public interface GeneralDataConfigItem extends EObject {
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
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chinese Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chinese Name</em>' attribute.
	 * @see #setChineseName(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem_ChineseName()
	 * @model
	 * @generated
	 */
	String getChineseName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getChineseName <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chinese Name</em>' attribute.
	 * @see #getChineseName()
	 * @generated
	 */
	void setChineseName(String value);

	/**
	 * Returns the value of the '<em><b>Discription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discription</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Discription</em>' attribute.
	 * @see #setDiscription(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigItem_Discription()
	 * @model
	 * @generated
	 */
	String getDiscription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getDiscription <em>Discription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discription</em>' attribute.
	 * @see #getDiscription()
	 * @generated
	 */
	void setDiscription(String value);

} // GeneralDataConfigItem
