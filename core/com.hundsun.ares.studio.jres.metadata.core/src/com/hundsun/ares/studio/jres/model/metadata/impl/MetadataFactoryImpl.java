/**
 * 源程序名称：MetadataFactoryImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import com.hundsun.ares.studio.jres.model.metadata.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.ConstantList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem;
import com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList;
import com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.NamedElement;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetadataFactoryImpl extends EFactoryImpl implements MetadataFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MetadataFactory init() {
		try {
			MetadataFactory theMetadataFactory = (MetadataFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/jres/metadata/1.0.0"); 
			if (theMetadataFactory != null) {
				return theMetadataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MetadataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY: return createMDModuleCommonProperty();
			case MetadataPackage.OPERATION: return createOperation();
			case MetadataPackage.NAMED_ELEMENT: return createNamedElement();
			case MetadataPackage.METADATA_ITEM: return createMetadataItem();
			case MetadataPackage.METADATA_CATEGORY: return createMetadataCategory();
			case MetadataPackage.TYPE_DEFAULT_VALUE_LIST: return createTypeDefaultValueList();
			case MetadataPackage.TYPE_DEFAULT_VALUE: return createTypeDefaultValue();
			case MetadataPackage.STANDARD_DATA_TYPE_LIST: return createStandardDataTypeList();
			case MetadataPackage.STANDARD_DATA_TYPE: return createStandardDataType();
			case MetadataPackage.BUSINESS_DATA_TYPE_LIST: return createBusinessDataTypeList();
			case MetadataPackage.BUSINESS_DATA_TYPE: return createBusinessDataType();
			case MetadataPackage.STANDARD_FIELD_LIST: return createStandardFieldList();
			case MetadataPackage.STANDARD_FIELD: return createStandardField();
			case MetadataPackage.DICTIONARY_LIST: return createDictionaryList();
			case MetadataPackage.DICTIONARY_TYPE: return createDictionaryType();
			case MetadataPackage.DICTIONARY_ITEM: return createDictionaryItem();
			case MetadataPackage.CONSTANT_LIST: return createConstantList();
			case MetadataPackage.CONSTANT_ITEM: return createConstantItem();
			case MetadataPackage.ERROR_NO_LIST: return createErrorNoList();
			case MetadataPackage.ERROR_NO_ITEM: return createErrorNoItem();
			case MetadataPackage.GENERAL_DATA_CONFIG_LIST: return createGeneralDataConfigList();
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM: return createGeneralDataConfigItem();
			case MetadataPackage.MENU_ITEM: return createMenuItem();
			case MetadataPackage.FUNCTION_PROXY: return createFunctionProxy();
			case MetadataPackage.FUNCTION: return createFunction();
			case MetadataPackage.MENU_LIST: return createMenuList();
			case MetadataPackage.ID_RANGE_ITEM: return createIDRangeItem();
			case MetadataPackage.ID_RANGE_LIST: return createIDRangeList();
			case MetadataPackage.ID_RANGE: return createIDRange();
			case MetadataPackage.BIZ_PROPERTY_CONFIG_LIST: return createBizPropertyConfigList();
			case MetadataPackage.BIZ_PROPERTY_CONFIG: return createBizPropertyConfig();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MDModuleCommonProperty createMDModuleCommonProperty() {
		MDModuleCommonPropertyImpl mdModuleCommonProperty = new MDModuleCommonPropertyImpl();
		return mdModuleCommonProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement createNamedElement() {
		NamedElementImpl namedElement = new NamedElementImpl();
		return namedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataItem createMetadataItem() {
		MetadataItemImpl metadataItem = new MetadataItemImpl();
		return metadataItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataCategory createMetadataCategory() {
		MetadataCategoryImpl metadataCategory = new MetadataCategoryImpl();
		return metadataCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDefaultValueList createTypeDefaultValueList() {
		TypeDefaultValueListImpl typeDefaultValueList = new TypeDefaultValueListImpl();
		return typeDefaultValueList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDefaultValue createTypeDefaultValue() {
		TypeDefaultValueImpl typeDefaultValue = new TypeDefaultValueImpl();
		return typeDefaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardDataTypeList createStandardDataTypeList() {
		StandardDataTypeListImpl standardDataTypeList = new StandardDataTypeListImpl();
		return standardDataTypeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardDataType createStandardDataType() {
		StandardDataTypeImpl standardDataType = new StandardDataTypeImpl();
		return standardDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessDataTypeList createBusinessDataTypeList() {
		BusinessDataTypeListImpl businessDataTypeList = new BusinessDataTypeListImpl();
		return businessDataTypeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessDataType createBusinessDataType() {
		BusinessDataTypeImpl businessDataType = new BusinessDataTypeImpl();
		return businessDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldList createStandardFieldList() {
		StandardFieldListImpl standardFieldList = new StandardFieldListImpl();
		return standardFieldList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardField createStandardField() {
		StandardFieldImpl standardField = new StandardFieldImpl();
		return standardField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DictionaryList createDictionaryList() {
		DictionaryListImpl dictionaryList = new DictionaryListImpl();
		return dictionaryList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DictionaryType createDictionaryType() {
		DictionaryTypeImpl dictionaryType = new DictionaryTypeImpl();
		return dictionaryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DictionaryItem createDictionaryItem() {
		DictionaryItemImpl dictionaryItem = new DictionaryItemImpl();
		return dictionaryItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstantList createConstantList() {
		ConstantListImpl constantList = new ConstantListImpl();
		return constantList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstantItem createConstantItem() {
		ConstantItemImpl constantItem = new ConstantItemImpl();
		return constantItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorNoList createErrorNoList() {
		ErrorNoListImpl errorNoList = new ErrorNoListImpl();
		return errorNoList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorNoItem createErrorNoItem() {
		ErrorNoItemImpl errorNoItem = new ErrorNoItemImpl();
		return errorNoItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralDataConfigList createGeneralDataConfigList() {
		GeneralDataConfigListImpl generalDataConfigList = new GeneralDataConfigListImpl();
		return generalDataConfigList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralDataConfigItem createGeneralDataConfigItem() {
		GeneralDataConfigItemImpl generalDataConfigItem = new GeneralDataConfigItemImpl();
		return generalDataConfigItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuItem createMenuItem() {
		MenuItemImpl menuItem = new MenuItemImpl();
		return menuItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionProxy createFunctionProxy() {
		FunctionProxyImpl functionProxy = new FunctionProxyImpl();
		return functionProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuList createMenuList() {
		MenuListImpl menuList = new MenuListImpl();
		return menuList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDRangeItem createIDRangeItem() {
		IDRangeItemImpl idRangeItem = new IDRangeItemImpl();
		return idRangeItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDRange createIDRange() {
		IDRangeImpl idRange = new IDRangeImpl();
		return idRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizPropertyConfigList createBizPropertyConfigList() {
		BizPropertyConfigListImpl bizPropertyConfigList = new BizPropertyConfigListImpl();
		return bizPropertyConfigList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizPropertyConfig createBizPropertyConfig() {
		BizPropertyConfigImpl bizPropertyConfig = new BizPropertyConfigImpl();
		return bizPropertyConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDRangeList createIDRangeList() {
		IDRangeListImpl idRangeList = new IDRangeListImpl();
		return idRangeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataPackage getMetadataPackage() {
		return (MetadataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MetadataPackage getPackage() {
		return MetadataPackage.eINSTANCE;
	}

} //MetadataFactoryImpl
