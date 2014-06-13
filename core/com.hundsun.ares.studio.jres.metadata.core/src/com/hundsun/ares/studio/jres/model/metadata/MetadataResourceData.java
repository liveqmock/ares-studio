/**
 * 源程序名称：MetadataResourceData.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getRoot <em>Root</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataResourceData()
 * @model abstract="true"
 * @generated
 */
public interface MetadataResourceData<T extends MetadataItem> extends JRESResourceInfo {

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.metadata.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataResourceData_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(MetadataCategory)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataResourceData_Root()
	 * @model containment="true"
	 * @generated
	 */
	MetadataCategory getRoot();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(MetadataCategory value);

	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link T}.
	 * It is bidirectional and its opposite is '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMetadataResourceData_Items()
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<T> getItems();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	T find(String name);
} // MetadataResourceData
