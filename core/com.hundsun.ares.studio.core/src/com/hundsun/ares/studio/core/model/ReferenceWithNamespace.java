/**
 * 源程序名称：ReferenceWithNamespace.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference With Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getReferenceWithNamespace()
 * @model abstract="true"
 * @generated
 */
public interface ReferenceWithNamespace extends Reference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setNamespace(String ns);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getNamespace();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setId(String id);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getId();

} // ReferenceWithNamespace
