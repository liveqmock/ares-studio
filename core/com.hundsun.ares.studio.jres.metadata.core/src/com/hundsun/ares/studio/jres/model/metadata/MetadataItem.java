/**
 * 源程序名称：MetadataItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getRefId <em>Ref Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataItem()
 * @model
 * @generated
 */
public interface MetadataItem extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Ref Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Id</em>' attribute.
	 * @see #setRefId(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataItem_RefId()
	 * @model default=""
	 * @generated
	 */
	String getRefId();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getRefId <em>Ref Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Id</em>' attribute.
	 * @see #getRefId()
	 * @generated
	 */
	void setRefId(String value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(MetadataResourceData)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataItem_Parent()
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getItems
	 * @model opposite="items" transient="false"
	 * @generated
	 */
	MetadataResourceData<?> getParent();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(MetadataResourceData<?> value);

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return MetadataUtil.getCategories(this);'"
	 * @generated
	 */
	EList<MetadataCategory> getCategories();

} // MetadataItem
