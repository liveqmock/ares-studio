/**
 * 源程序名称：DeBusinessDataType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>De Business Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeBusinessDataType()
 * @model superTypes="com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem<com.hundsun.ares.studio.jres.model.metadata.BusinessDataType> com.hundsun.ares.studio.jres.model.metadata.decrypt.IBusinessDataType"
 * @generated
 */
public interface DeBusinessDataType extends DeMetadataItem<BusinessDataType>, IBusinessDataType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DeTypeDefaultValue getDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getDefaultValue();'"
	 * @generated
	 */
	String getDefaultValueId();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getLength();'"
	 * @generated
	 */
	String getLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getPrecision();'"
	 * @generated
	 */
	String getPrecision();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DeStandardDataType getStdType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getStdType();'"
	 * @generated
	 */
	String getStdTypeId();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return getDefaultValue().getValue(typeId);'"
	 * @generated
	 */
	String getRealDefaultValue(String typeId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return getStdType().getRealType(typeId, getLength(), getPrecision());'"
	 * @generated
	 */
	String getRealType(String typeId);

	/**
	 * 一个代表空指针的对象
	 */
	DeBusinessDataType NULL = DecryptFactory.eINSTANCE.createDeBusinessDataType();
	
} // DeBusinessDataType
