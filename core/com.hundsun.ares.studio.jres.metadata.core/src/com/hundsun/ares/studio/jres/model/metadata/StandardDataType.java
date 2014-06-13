/**
 * 源程序名称：StandardDataType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardDataType()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name refId'"
 * @generated
 */
public interface StandardDataType extends MetadataItem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getValue(String typeId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setValue(String typeId, String value);

} // StandardDataType
