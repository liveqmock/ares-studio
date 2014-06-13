/**
 * 源程序名称：DecryptFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import org.eclipse.emf.ecore.EFactory;

import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage
 * @generated
 */
public interface DecryptFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DecryptFactory eINSTANCE = com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>De Metadata Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Metadata Item</em>'.
	 * @generated
	 */
	<T extends MetadataItem> DeMetadataItem<T> createDeMetadataItem();

	/**
	 * Returns a new object of class '<em>De Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Type Default Value</em>'.
	 * @generated
	 */
	DeTypeDefaultValue createDeTypeDefaultValue();

	/**
	 * Returns a new object of class '<em>De Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Standard Data Type</em>'.
	 * @generated
	 */
	DeStandardDataType createDeStandardDataType();

	/**
	 * Returns a new object of class '<em>De Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Business Data Type</em>'.
	 * @generated
	 */
	DeBusinessDataType createDeBusinessDataType();

	/**
	 * Returns a new object of class '<em>De Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Standard Field</em>'.
	 * @generated
	 */
	DeStandardField createDeStandardField();

	/**
	 * Returns a new object of class '<em>De Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Dictionary Type</em>'.
	 * @generated
	 */
	DeDictionaryType createDeDictionaryType();

	/**
	 * Returns a new object of class '<em>De Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Dictionary Item</em>'.
	 * @generated
	 */
	DeDictionaryItem createDeDictionaryItem();

	/**
	 * Returns a new object of class '<em>De Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Constant Item</em>'.
	 * @generated
	 */
	DeConstantItem createDeConstantItem();

	/**
	 * Returns a new object of class '<em>De Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>De Error No Item</em>'.
	 * @generated
	 */
	DeErrorNoItem createDeErrorNoItem();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DecryptPackage getDecryptPackage();

} //DecryptFactory
