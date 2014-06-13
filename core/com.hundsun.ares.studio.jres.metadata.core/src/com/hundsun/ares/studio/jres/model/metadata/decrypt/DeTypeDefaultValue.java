/**
 * 源程序名称：DeTypeDefaultValue.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>De Type Default Value</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeTypeDefaultValue()
 * @model superTypes="com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem<com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue> com.hundsun.ares.studio.jres.model.metadata.decrypt.ITypeDefaultValue"
 * @generated
 */
public interface DeTypeDefaultValue extends DeMetadataItem<TypeDefaultValue>, ITypeDefaultValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return getDataMapValue(typeId);'"
	 * @generated
	 */
	String getValue(String typeId);

	/**
	 * 一个代表空指针的对象
	 */
	DeTypeDefaultValue NULL = DecryptFactory.eINSTANCE.createDeTypeDefaultValue();
} // DeTypeDefaultValue
