/**
 * 源程序名称：DictionaryItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dictionary Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getParent <em>Parent</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getConstantName <em>Constant Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='value name constantName'"
 * @generated
 */
public interface DictionaryItem extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryType#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(DictionaryType)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_Parent()
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryType#getItems
	 * @model opposite="items" transient="false"
	 * @generated
	 */
	DictionaryType getParent();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(DictionaryType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_Value()
	 * @model default=""
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

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
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Chinese Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chinese Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chinese Name</em>' attribute.
	 * @see #setChineseName(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_ChineseName()
	 * @model default=""
	 * @generated
	 */
	String getChineseName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getChineseName <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chinese Name</em>' attribute.
	 * @see #getChineseName()
	 * @generated
	 */
	void setChineseName(String value);

	/**
	 * Returns the value of the '<em><b>Constant Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant Name</em>' attribute.
	 * @see #setConstantName(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_ConstantName()
	 * @model default=""
	 * @generated
	 */
	String getConstantName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getConstantName <em>Constant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant Name</em>' attribute.
	 * @see #getConstantName()
	 * @generated
	 */
	void setConstantName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getDictionaryItem_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // DictionaryItem
