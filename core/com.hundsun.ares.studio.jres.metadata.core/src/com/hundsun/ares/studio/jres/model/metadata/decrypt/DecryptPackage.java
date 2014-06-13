/**
 * 源程序名称：DecryptPackage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptFactory
 * @model kind="package"
 * @generated
 */
public interface DecryptPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "decrypt";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/metadata/decrypt/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "decrypt";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DecryptPackage eINSTANCE = com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue <em>IType Default Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getITypeDefaultValue()
	 * @generated
	 */
	int ITYPE_DEFAULT_VALUE = 0;

	/**
	 * The number of structural features of the '<em>IType Default Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_DEFAULT_VALUE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardDataType <em>IStandard Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardDataType
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIStandardDataType()
	 * @generated
	 */
	int ISTANDARD_DATA_TYPE = 1;

	/**
	 * The number of structural features of the '<em>IStandard Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTANDARD_DATA_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType <em>IBusiness Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIBusinessDataType()
	 * @generated
	 */
	int IBUSINESS_DATA_TYPE = 2;

	/**
	 * The number of structural features of the '<em>IBusiness Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBUSINESS_DATA_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardField <em>IStandard Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardField
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIStandardField()
	 * @generated
	 */
	int ISTANDARD_FIELD = 3;

	/**
	 * The number of structural features of the '<em>IStandard Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTANDARD_FIELD_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryType <em>IDictionary Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryType
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIDictionaryType()
	 * @generated
	 */
	int IDICTIONARY_TYPE = 4;

	/**
	 * The number of structural features of the '<em>IDictionary Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDICTIONARY_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem <em>IDictionary Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIDictionaryItem()
	 * @generated
	 */
	int IDICTIONARY_ITEM = 5;

	/**
	 * The number of structural features of the '<em>IDictionary Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDICTIONARY_ITEM_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem <em>IError No Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIErrorNoItem()
	 * @generated
	 */
	int IERROR_NO_ITEM = 6;

	/**
	 * The number of structural features of the '<em>IError No Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IERROR_NO_ITEM_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem <em>IUser Constant Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIUserConstantItem()
	 * @generated
	 */
	int IUSER_CONSTANT_ITEM = 7;

	/**
	 * The number of structural features of the '<em>IUser Constant Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IUSER_CONSTANT_ITEM_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeMetadataItemImpl <em>De Metadata Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeMetadataItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeMetadataItem()
	 * @generated
	 */
	int DE_METADATA_ITEM = 8;

	/**
	 * The number of structural features of the '<em>De Metadata Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_METADATA_ITEM_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeTypeDefaultValueImpl <em>De Type Default Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeTypeDefaultValueImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeTypeDefaultValue()
	 * @generated
	 */
	int DE_TYPE_DEFAULT_VALUE = 9;

	/**
	 * The number of structural features of the '<em>De Type Default Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_TYPE_DEFAULT_VALUE_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardDataTypeImpl <em>De Standard Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardDataTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeStandardDataType()
	 * @generated
	 */
	int DE_STANDARD_DATA_TYPE = 10;

	/**
	 * The number of structural features of the '<em>De Standard Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_STANDARD_DATA_TYPE_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeBusinessDataTypeImpl <em>De Business Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeBusinessDataTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeBusinessDataType()
	 * @generated
	 */
	int DE_BUSINESS_DATA_TYPE = 11;

	/**
	 * The number of structural features of the '<em>De Business Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_BUSINESS_DATA_TYPE_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardFieldImpl <em>De Standard Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardFieldImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeStandardField()
	 * @generated
	 */
	int DE_STANDARD_FIELD = 12;

	/**
	 * The number of structural features of the '<em>De Standard Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_STANDARD_FIELD_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryTypeImpl <em>De Dictionary Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeDictionaryType()
	 * @generated
	 */
	int DE_DICTIONARY_TYPE = 13;

	/**
	 * The number of structural features of the '<em>De Dictionary Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_DICTIONARY_TYPE_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryItemImpl <em>De Dictionary Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeDictionaryItem()
	 * @generated
	 */
	int DE_DICTIONARY_ITEM = 14;

	/**
	 * The number of structural features of the '<em>De Dictionary Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_DICTIONARY_ITEM_FEATURE_COUNT = IDICTIONARY_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeConstantItemImpl <em>De Constant Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeConstantItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeConstantItem()
	 * @generated
	 */
	int DE_CONSTANT_ITEM = 15;

	/**
	 * The number of structural features of the '<em>De Constant Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_CONSTANT_ITEM_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeErrorNoItemImpl <em>De Error No Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeErrorNoItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeErrorNoItem()
	 * @generated
	 */
	int DE_ERROR_NO_ITEM = 16;

	/**
	 * The number of structural features of the '<em>De Error No Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DE_ERROR_NO_ITEM_FEATURE_COUNT = DE_METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '<em>IARES Resource</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.IARESResource
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIARESResource()
	 * @generated
	 */
	int IARES_RESOURCE = 17;


	/**
	 * The meta object id for the '<em>Pair</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.util.Pair
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getPair()
	 * @generated
	 */
	int PAIR = 18;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue <em>IType Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue"
	 * @generated
	 */
	EClass getITypeDefaultValue();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardDataType <em>IStandard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IStandard Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardDataType
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IStandardDataType"
	 * @generated
	 */
	EClass getIStandardDataType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType <em>IBusiness Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBusiness Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType"
	 * @generated
	 */
	EClass getIBusinessDataType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardField <em>IStandard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IStandard Field</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardField
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IStandardField"
	 * @generated
	 */
	EClass getIStandardField();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryType <em>IDictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IDictionary Type</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryType
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IDictionaryType"
	 * @generated
	 */
	EClass getIDictionaryType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem <em>IDictionary Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IDictionary Item</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem"
	 * @generated
	 */
	EClass getIDictionaryItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem <em>IError No Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IError No Item</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem"
	 * @generated
	 */
	EClass getIErrorNoItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem <em>IUser Constant Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IUser Constant Item</em>'.
	 * @see com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem
	 * @model instanceClass="com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem"
	 * @generated
	 */
	EClass getIUserConstantItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem <em>De Metadata Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Metadata Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem
	 * @generated
	 */
	EClass getDeMetadataItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue <em>De Type Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Type Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue
	 * @generated
	 */
	EClass getDeTypeDefaultValue();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType <em>De Standard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Standard Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType
	 * @generated
	 */
	EClass getDeStandardDataType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType <em>De Business Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Business Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType
	 * @generated
	 */
	EClass getDeBusinessDataType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField <em>De Standard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Standard Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField
	 * @generated
	 */
	EClass getDeStandardField();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType <em>De Dictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Dictionary Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType
	 * @generated
	 */
	EClass getDeDictionaryType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem <em>De Dictionary Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Dictionary Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem
	 * @generated
	 */
	EClass getDeDictionaryItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem <em>De Constant Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Constant Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem
	 * @generated
	 */
	EClass getDeConstantItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem <em>De Error No Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>De Error No Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem
	 * @generated
	 */
	EClass getDeErrorNoItem();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.core.IARESResource <em>IARES Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IARES Resource</em>'.
	 * @see com.hundsun.ares.studio.core.IARESResource
	 * @model instanceClass="com.hundsun.ares.studio.core.IARESResource" serializeable="false"
	 * @generated
	 */
	EDataType getIARESResource();

	/**
	 * Returns the meta object for data type '{@link com.hundsun.ares.studio.core.model.util.Pair <em>Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Pair</em>'.
	 * @see com.hundsun.ares.studio.core.model.util.Pair
	 * @model instanceClass="com.hundsun.ares.studio.jres.model.core.util.Pair" typeParameters="F S"
	 * @generated
	 */
	EDataType getPair();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DecryptFactory getDecryptFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue <em>IType Default Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getITypeDefaultValue()
		 * @generated
		 */
		EClass ITYPE_DEFAULT_VALUE = eINSTANCE.getITypeDefaultValue();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardDataType <em>IStandard Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardDataType
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIStandardDataType()
		 * @generated
		 */
		EClass ISTANDARD_DATA_TYPE = eINSTANCE.getIStandardDataType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType <em>IBusiness Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIBusinessDataType()
		 * @generated
		 */
		EClass IBUSINESS_DATA_TYPE = eINSTANCE.getIBusinessDataType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IStandardField <em>IStandard Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IStandardField
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIStandardField()
		 * @generated
		 */
		EClass ISTANDARD_FIELD = eINSTANCE.getIStandardField();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryType <em>IDictionary Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryType
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIDictionaryType()
		 * @generated
		 */
		EClass IDICTIONARY_TYPE = eINSTANCE.getIDictionaryType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem <em>IDictionary Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IDictionaryItem
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIDictionaryItem()
		 * @generated
		 */
		EClass IDICTIONARY_ITEM = eINSTANCE.getIDictionaryItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem <em>IError No Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IErrorNoItem
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIErrorNoItem()
		 * @generated
		 */
		EClass IERROR_NO_ITEM = eINSTANCE.getIErrorNoItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem <em>IUser Constant Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.metadata.service.IUserConstantItem
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIUserConstantItem()
		 * @generated
		 */
		EClass IUSER_CONSTANT_ITEM = eINSTANCE.getIUserConstantItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeMetadataItemImpl <em>De Metadata Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeMetadataItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeMetadataItem()
		 * @generated
		 */
		EClass DE_METADATA_ITEM = eINSTANCE.getDeMetadataItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeTypeDefaultValueImpl <em>De Type Default Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeTypeDefaultValueImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeTypeDefaultValue()
		 * @generated
		 */
		EClass DE_TYPE_DEFAULT_VALUE = eINSTANCE.getDeTypeDefaultValue();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardDataTypeImpl <em>De Standard Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardDataTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeStandardDataType()
		 * @generated
		 */
		EClass DE_STANDARD_DATA_TYPE = eINSTANCE.getDeStandardDataType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeBusinessDataTypeImpl <em>De Business Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeBusinessDataTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeBusinessDataType()
		 * @generated
		 */
		EClass DE_BUSINESS_DATA_TYPE = eINSTANCE.getDeBusinessDataType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardFieldImpl <em>De Standard Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeStandardFieldImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeStandardField()
		 * @generated
		 */
		EClass DE_STANDARD_FIELD = eINSTANCE.getDeStandardField();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryTypeImpl <em>De Dictionary Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeDictionaryType()
		 * @generated
		 */
		EClass DE_DICTIONARY_TYPE = eINSTANCE.getDeDictionaryType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryItemImpl <em>De Dictionary Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeDictionaryItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeDictionaryItem()
		 * @generated
		 */
		EClass DE_DICTIONARY_ITEM = eINSTANCE.getDeDictionaryItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeConstantItemImpl <em>De Constant Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeConstantItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeConstantItem()
		 * @generated
		 */
		EClass DE_CONSTANT_ITEM = eINSTANCE.getDeConstantItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeErrorNoItemImpl <em>De Error No Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DeErrorNoItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getDeErrorNoItem()
		 * @generated
		 */
		EClass DE_ERROR_NO_ITEM = eINSTANCE.getDeErrorNoItem();

		/**
		 * The meta object literal for the '<em>IARES Resource</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.IARESResource
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getIARESResource()
		 * @generated
		 */
		EDataType IARES_RESOURCE = eINSTANCE.getIARESResource();

		/**
		 * The meta object literal for the '<em>Pair</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.util.Pair
		 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.impl.DecryptPackageImpl#getPair()
		 * @generated
		 */
		EDataType PAIR = eINSTANCE.getPair();

	}

} //DecryptPackage
