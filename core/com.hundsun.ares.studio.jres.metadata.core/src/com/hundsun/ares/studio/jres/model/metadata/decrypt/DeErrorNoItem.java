/**
 * 源程序名称：DeErrorNoItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>De Error No Item</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeErrorNoItem()
 * @model superTypes="com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem<com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem> com.hundsun.ares.studio.jres.model.metadata.decrypt.IErrorNoItem"
 * @generated
 */
public interface DeErrorNoItem extends DeMetadataItem<ErrorNoItem>, IErrorNoItem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getConstantName();'"
	 * @generated
	 */
	String getConstantName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getLevel();'"
	 * @generated
	 */
	String getLevel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getMessage();'"
	 * @generated
	 */
	String getMessage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return \"\";\r\nreturn getResolvedItem().first.getNo();'"
	 * @generated
	 */
	String getNo();

	/**
	 * 一个代表空指针的对象
	 */
	DeErrorNoItem NULL = DecryptFactory.eINSTANCE.createDeErrorNoItem();
} // DeErrorNoItem
