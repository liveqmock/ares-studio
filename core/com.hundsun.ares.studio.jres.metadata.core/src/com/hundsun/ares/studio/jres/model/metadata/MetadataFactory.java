/**
 * 源程序名称：MetadataFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage
 * @generated
 */
public interface MetadataFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetadataFactory eINSTANCE = com.hundsun.ares.studio.jres.model.metadata.impl.MetadataFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>MD Module Common Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MD Module Common Property</em>'.
	 * @generated
	 */
	MDModuleCommonProperty createMDModuleCommonProperty();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Element</em>'.
	 * @generated
	 */
	NamedElement createNamedElement();

	/**
	 * Returns a new object of class '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Item</em>'.
	 * @generated
	 */
	MetadataItem createMetadataItem();

	/**
	 * Returns a new object of class '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Category</em>'.
	 * @generated
	 */
	MetadataCategory createMetadataCategory();

	/**
	 * Returns a new object of class '<em>Type Default Value List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Default Value List</em>'.
	 * @generated
	 */
	TypeDefaultValueList createTypeDefaultValueList();

	/**
	 * Returns a new object of class '<em>Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Default Value</em>'.
	 * @generated
	 */
	TypeDefaultValue createTypeDefaultValue();

	/**
	 * Returns a new object of class '<em>Standard Data Type List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Data Type List</em>'.
	 * @generated
	 */
	StandardDataTypeList createStandardDataTypeList();

	/**
	 * Returns a new object of class '<em>Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Data Type</em>'.
	 * @generated
	 */
	StandardDataType createStandardDataType();

	/**
	 * Returns a new object of class '<em>Business Data Type List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Data Type List</em>'.
	 * @generated
	 */
	BusinessDataTypeList createBusinessDataTypeList();

	/**
	 * Returns a new object of class '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Data Type</em>'.
	 * @generated
	 */
	BusinessDataType createBusinessDataType();

	/**
	 * Returns a new object of class '<em>Standard Field List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Field List</em>'.
	 * @generated
	 */
	StandardFieldList createStandardFieldList();

	/**
	 * Returns a new object of class '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Field</em>'.
	 * @generated
	 */
	StandardField createStandardField();

	/**
	 * Returns a new object of class '<em>Dictionary List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dictionary List</em>'.
	 * @generated
	 */
	DictionaryList createDictionaryList();

	/**
	 * Returns a new object of class '<em>Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dictionary Type</em>'.
	 * @generated
	 */
	DictionaryType createDictionaryType();

	/**
	 * Returns a new object of class '<em>Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dictionary Item</em>'.
	 * @generated
	 */
	DictionaryItem createDictionaryItem();

	/**
	 * Returns a new object of class '<em>Constant List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constant List</em>'.
	 * @generated
	 */
	ConstantList createConstantList();

	/**
	 * Returns a new object of class '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constant Item</em>'.
	 * @generated
	 */
	ConstantItem createConstantItem();

	/**
	 * Returns a new object of class '<em>Error No List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Error No List</em>'.
	 * @generated
	 */
	ErrorNoList createErrorNoList();

	/**
	 * Returns a new object of class '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Error No Item</em>'.
	 * @generated
	 */
	ErrorNoItem createErrorNoItem();

	/**
	 * Returns a new object of class '<em>General Data Config List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>General Data Config List</em>'.
	 * @generated
	 */
	GeneralDataConfigList createGeneralDataConfigList();

	/**
	 * Returns a new object of class '<em>General Data Config Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>General Data Config Item</em>'.
	 * @generated
	 */
	GeneralDataConfigItem createGeneralDataConfigItem();

	/**
	 * Returns a new object of class '<em>Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Menu Item</em>'.
	 * @generated
	 */
	MenuItem createMenuItem();

	/**
	 * Returns a new object of class '<em>Function Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Proxy</em>'.
	 * @generated
	 */
	FunctionProxy createFunctionProxy();

	/**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
	Function createFunction();

	/**
	 * Returns a new object of class '<em>Menu List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Menu List</em>'.
	 * @generated
	 */
	MenuList createMenuList();

	/**
	 * Returns a new object of class '<em>ID Range Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ID Range Item</em>'.
	 * @generated
	 */
	IDRangeItem createIDRangeItem();

	/**
	 * Returns a new object of class '<em>ID Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ID Range</em>'.
	 * @generated
	 */
	IDRange createIDRange();

	/**
	 * Returns a new object of class '<em>Biz Property Config List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Biz Property Config List</em>'.
	 * @generated
	 */
	BizPropertyConfigList createBizPropertyConfigList();

	/**
	 * Returns a new object of class '<em>Biz Property Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Biz Property Config</em>'.
	 * @generated
	 */
	BizPropertyConfig createBizPropertyConfig();

	/**
	 * Returns a new object of class '<em>ID Range List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ID Range List</em>'.
	 * @generated
	 */
	IDRangeList createIDRangeList();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MetadataPackage getMetadataPackage();

} //MetadataFactory
