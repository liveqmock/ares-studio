/**
 * 源程序名称：DeDictionaryType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.metadata.service.IDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>De Dictionary Type</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeDictionaryType()
 * @model superTypes="com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem<com.hundsun.ares.studio.jres.model.metadata.DictionaryType> com.hundsun.ares.studio.jres.model.metadata.decrypt.IDictionaryType"
 * @generated
 */
public interface DeDictionaryType extends DeMetadataItem<DictionaryType>, IDictionaryType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<DeDictionaryItem> getItems();

	/**
	 * 一个代表空指针的对象
	 */
	DeDictionaryType NULL = DecryptFactory.eINSTANCE.createDeDictionaryType();
	
} // DeDictionaryType
