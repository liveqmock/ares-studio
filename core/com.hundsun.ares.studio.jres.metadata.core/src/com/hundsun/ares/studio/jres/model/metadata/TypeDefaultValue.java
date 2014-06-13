/**
 * 源程序名称：TypeDefaultValue.java
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
 * A representation of the model object '<em><b>Type Default Value</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getTypeDefaultValue()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name refId'"
 *        annotation="http://www.hundsun.com/ares/studio/jres/references refId='com.hundsun.ares.studio.jres.model.metadata.util.MetadataReferenceParser jres.md.typedef'"
 * @generated
 */
public interface TypeDefaultValue extends MetadataItem {
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

} // TypeDefaultValue
