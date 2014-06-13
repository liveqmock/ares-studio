/**
 * 源程序名称：DeConstantItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>De Constant Item</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeConstantItem()
 * @model superTypes="com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem<com.hundsun.ares.studio.jres.model.metadata.ConstantItem> com.hundsun.ares.studio.jres.model.metadata.decrypt.IUserConstantItem"
 * @generated
 */
public interface DeConstantItem extends DeMetadataItem<ConstantItem>, IUserConstantItem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DeStandardDataType getDataType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getDataType();'"
	 * @generated
	 */
	String getDataTypeId();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getLength();'"
	 * @generated
	 */
	String getLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getPrecision();'"
	 * @generated
	 */
	String getPrecision();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getValue();'"
	 * @generated
	 */
	String getValue();

	/**
	 * 一个代表空指针的对象
	 */
	DeConstantItem NULL = DecryptFactory.eINSTANCE.createDeConstantItem();
} // DeConstantItem
