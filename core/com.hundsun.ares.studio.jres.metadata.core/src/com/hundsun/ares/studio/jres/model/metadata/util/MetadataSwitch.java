/**
 * 源程序名称：MetadataSwitch.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.model.metadata.*;
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
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage
 * @generated
 */
public class MetadataSwitch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MetadataPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataSwitch() {
		if (modelPackage == null) {
			modelPackage = MetadataPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T1 doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY: {
				MDModuleCommonProperty mdModuleCommonProperty = (MDModuleCommonProperty)theEObject;
				T1 result = caseMDModuleCommonProperty(mdModuleCommonProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T1 result = caseOperation(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.METADATA_RESOURCE_DATA: {
				MetadataResourceData<?> metadataResourceData = (MetadataResourceData<?>)theEObject;
				T1 result = caseMetadataResourceData(metadataResourceData);
				if (result == null) result = caseJRESResourceInfo(metadataResourceData);
				if (result == null) result = caseExtensibleModel(metadataResourceData);
				if (result == null) result = caseBasicResourceInfo(metadataResourceData);
				if (result == null) result = caseIReferenceProvider(metadataResourceData);
				if (result == null) result = caseIJSONData(metadataResourceData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T1 result = caseNamedElement(namedElement);
				if (result == null) result = caseExtensibleModel(namedElement);
				if (result == null) result = caseIJSONData(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.METADATA_ITEM: {
				MetadataItem metadataItem = (MetadataItem)theEObject;
				T1 result = caseMetadataItem(metadataItem);
				if (result == null) result = caseNamedElement(metadataItem);
				if (result == null) result = caseExtensibleModel(metadataItem);
				if (result == null) result = caseIJSONData(metadataItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.METADATA_CATEGORY: {
				MetadataCategory metadataCategory = (MetadataCategory)theEObject;
				T1 result = caseMetadataCategory(metadataCategory);
				if (result == null) result = caseNamedElement(metadataCategory);
				if (result == null) result = caseExtensibleModel(metadataCategory);
				if (result == null) result = caseIJSONData(metadataCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.TYPE_DEFAULT_VALUE_LIST: {
				TypeDefaultValueList typeDefaultValueList = (TypeDefaultValueList)theEObject;
				T1 result = caseTypeDefaultValueList(typeDefaultValueList);
				if (result == null) result = caseMetadataResourceData(typeDefaultValueList);
				if (result == null) result = caseJRESResourceInfo(typeDefaultValueList);
				if (result == null) result = caseExtensibleModel(typeDefaultValueList);
				if (result == null) result = caseBasicResourceInfo(typeDefaultValueList);
				if (result == null) result = caseIReferenceProvider(typeDefaultValueList);
				if (result == null) result = caseIJSONData(typeDefaultValueList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.TYPE_DEFAULT_VALUE: {
				TypeDefaultValue typeDefaultValue = (TypeDefaultValue)theEObject;
				T1 result = caseTypeDefaultValue(typeDefaultValue);
				if (result == null) result = caseMetadataItem(typeDefaultValue);
				if (result == null) result = caseNamedElement(typeDefaultValue);
				if (result == null) result = caseExtensibleModel(typeDefaultValue);
				if (result == null) result = caseIJSONData(typeDefaultValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.STANDARD_DATA_TYPE_LIST: {
				StandardDataTypeList standardDataTypeList = (StandardDataTypeList)theEObject;
				T1 result = caseStandardDataTypeList(standardDataTypeList);
				if (result == null) result = caseMetadataResourceData(standardDataTypeList);
				if (result == null) result = caseJRESResourceInfo(standardDataTypeList);
				if (result == null) result = caseExtensibleModel(standardDataTypeList);
				if (result == null) result = caseBasicResourceInfo(standardDataTypeList);
				if (result == null) result = caseIReferenceProvider(standardDataTypeList);
				if (result == null) result = caseIJSONData(standardDataTypeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.STANDARD_DATA_TYPE: {
				StandardDataType standardDataType = (StandardDataType)theEObject;
				T1 result = caseStandardDataType(standardDataType);
				if (result == null) result = caseMetadataItem(standardDataType);
				if (result == null) result = caseNamedElement(standardDataType);
				if (result == null) result = caseExtensibleModel(standardDataType);
				if (result == null) result = caseIJSONData(standardDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.BUSINESS_DATA_TYPE_LIST: {
				BusinessDataTypeList businessDataTypeList = (BusinessDataTypeList)theEObject;
				T1 result = caseBusinessDataTypeList(businessDataTypeList);
				if (result == null) result = caseMetadataResourceData(businessDataTypeList);
				if (result == null) result = caseJRESResourceInfo(businessDataTypeList);
				if (result == null) result = caseExtensibleModel(businessDataTypeList);
				if (result == null) result = caseBasicResourceInfo(businessDataTypeList);
				if (result == null) result = caseIReferenceProvider(businessDataTypeList);
				if (result == null) result = caseIJSONData(businessDataTypeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.BUSINESS_DATA_TYPE: {
				BusinessDataType businessDataType = (BusinessDataType)theEObject;
				T1 result = caseBusinessDataType(businessDataType);
				if (result == null) result = caseMetadataItem(businessDataType);
				if (result == null) result = caseNamedElement(businessDataType);
				if (result == null) result = caseExtensibleModel(businessDataType);
				if (result == null) result = caseIJSONData(businessDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.STANDARD_FIELD_LIST: {
				StandardFieldList standardFieldList = (StandardFieldList)theEObject;
				T1 result = caseStandardFieldList(standardFieldList);
				if (result == null) result = caseMetadataResourceData(standardFieldList);
				if (result == null) result = caseJRESResourceInfo(standardFieldList);
				if (result == null) result = caseExtensibleModel(standardFieldList);
				if (result == null) result = caseBasicResourceInfo(standardFieldList);
				if (result == null) result = caseIReferenceProvider(standardFieldList);
				if (result == null) result = caseIJSONData(standardFieldList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.STANDARD_FIELD: {
				StandardField standardField = (StandardField)theEObject;
				T1 result = caseStandardField(standardField);
				if (result == null) result = caseMetadataItem(standardField);
				if (result == null) result = caseNamedElement(standardField);
				if (result == null) result = caseExtensibleModel(standardField);
				if (result == null) result = caseIJSONData(standardField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.DICTIONARY_LIST: {
				DictionaryList dictionaryList = (DictionaryList)theEObject;
				T1 result = caseDictionaryList(dictionaryList);
				if (result == null) result = caseMetadataResourceData(dictionaryList);
				if (result == null) result = caseJRESResourceInfo(dictionaryList);
				if (result == null) result = caseExtensibleModel(dictionaryList);
				if (result == null) result = caseBasicResourceInfo(dictionaryList);
				if (result == null) result = caseIReferenceProvider(dictionaryList);
				if (result == null) result = caseIJSONData(dictionaryList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.DICTIONARY_TYPE: {
				DictionaryType dictionaryType = (DictionaryType)theEObject;
				T1 result = caseDictionaryType(dictionaryType);
				if (result == null) result = caseMetadataItem(dictionaryType);
				if (result == null) result = caseNamedElement(dictionaryType);
				if (result == null) result = caseExtensibleModel(dictionaryType);
				if (result == null) result = caseIJSONData(dictionaryType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.DICTIONARY_ITEM: {
				DictionaryItem dictionaryItem = (DictionaryItem)theEObject;
				T1 result = caseDictionaryItem(dictionaryItem);
				if (result == null) result = caseExtensibleModel(dictionaryItem);
				if (result == null) result = caseIJSONData(dictionaryItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.CONSTANT_LIST: {
				ConstantList constantList = (ConstantList)theEObject;
				T1 result = caseConstantList(constantList);
				if (result == null) result = caseMetadataResourceData(constantList);
				if (result == null) result = caseJRESResourceInfo(constantList);
				if (result == null) result = caseExtensibleModel(constantList);
				if (result == null) result = caseBasicResourceInfo(constantList);
				if (result == null) result = caseIReferenceProvider(constantList);
				if (result == null) result = caseIJSONData(constantList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.CONSTANT_ITEM: {
				ConstantItem constantItem = (ConstantItem)theEObject;
				T1 result = caseConstantItem(constantItem);
				if (result == null) result = caseMetadataItem(constantItem);
				if (result == null) result = caseNamedElement(constantItem);
				if (result == null) result = caseExtensibleModel(constantItem);
				if (result == null) result = caseIJSONData(constantItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.ERROR_NO_LIST: {
				ErrorNoList errorNoList = (ErrorNoList)theEObject;
				T1 result = caseErrorNoList(errorNoList);
				if (result == null) result = caseMetadataResourceData(errorNoList);
				if (result == null) result = caseJRESResourceInfo(errorNoList);
				if (result == null) result = caseExtensibleModel(errorNoList);
				if (result == null) result = caseBasicResourceInfo(errorNoList);
				if (result == null) result = caseIReferenceProvider(errorNoList);
				if (result == null) result = caseIJSONData(errorNoList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.ERROR_NO_ITEM: {
				ErrorNoItem errorNoItem = (ErrorNoItem)theEObject;
				T1 result = caseErrorNoItem(errorNoItem);
				if (result == null) result = caseMetadataItem(errorNoItem);
				if (result == null) result = caseNamedElement(errorNoItem);
				if (result == null) result = caseExtensibleModel(errorNoItem);
				if (result == null) result = caseIJSONData(errorNoItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.GENERAL_DATA_CONFIG_LIST: {
				GeneralDataConfigList generalDataConfigList = (GeneralDataConfigList)theEObject;
				T1 result = caseGeneralDataConfigList(generalDataConfigList);
				if (result == null) result = caseJRESResourceInfo(generalDataConfigList);
				if (result == null) result = caseExtensibleModel(generalDataConfigList);
				if (result == null) result = caseBasicResourceInfo(generalDataConfigList);
				if (result == null) result = caseIReferenceProvider(generalDataConfigList);
				if (result == null) result = caseIJSONData(generalDataConfigList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM: {
				GeneralDataConfigItem generalDataConfigItem = (GeneralDataConfigItem)theEObject;
				T1 result = caseGeneralDataConfigItem(generalDataConfigItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.MENU_ITEM: {
				MenuItem menuItem = (MenuItem)theEObject;
				T1 result = caseMenuItem(menuItem);
				if (result == null) result = caseMetadataItem(menuItem);
				if (result == null) result = caseNamedElement(menuItem);
				if (result == null) result = caseExtensibleModel(menuItem);
				if (result == null) result = caseIJSONData(menuItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.FUNCTION_PROXY: {
				FunctionProxy functionProxy = (FunctionProxy)theEObject;
				T1 result = caseFunctionProxy(functionProxy);
				if (result == null) result = caseExtensibleModel(functionProxy);
				if (result == null) result = caseIJSONData(functionProxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T1 result = caseFunction(function);
				if (result == null) result = caseNamedElement(function);
				if (result == null) result = caseExtensibleModel(function);
				if (result == null) result = caseIJSONData(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.MENU_LIST: {
				MenuList menuList = (MenuList)theEObject;
				T1 result = caseMenuList(menuList);
				if (result == null) result = caseMetadataResourceData(menuList);
				if (result == null) result = caseJRESResourceInfo(menuList);
				if (result == null) result = caseExtensibleModel(menuList);
				if (result == null) result = caseBasicResourceInfo(menuList);
				if (result == null) result = caseIReferenceProvider(menuList);
				if (result == null) result = caseIJSONData(menuList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.ID_RANGE_ITEM: {
				IDRangeItem idRangeItem = (IDRangeItem)theEObject;
				T1 result = caseIDRangeItem(idRangeItem);
				if (result == null) result = caseMetadataItem(idRangeItem);
				if (result == null) result = caseNamedElement(idRangeItem);
				if (result == null) result = caseExtensibleModel(idRangeItem);
				if (result == null) result = caseIJSONData(idRangeItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.ID_RANGE_LIST: {
				IDRangeList idRangeList = (IDRangeList)theEObject;
				T1 result = caseIDRangeList(idRangeList);
				if (result == null) result = caseMetadataResourceData(idRangeList);
				if (result == null) result = caseJRESResourceInfo(idRangeList);
				if (result == null) result = caseExtensibleModel(idRangeList);
				if (result == null) result = caseBasicResourceInfo(idRangeList);
				if (result == null) result = caseIReferenceProvider(idRangeList);
				if (result == null) result = caseIJSONData(idRangeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.ID_RANGE: {
				IDRange idRange = (IDRange)theEObject;
				T1 result = caseIDRange(idRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.BIZ_PROPERTY_CONFIG_LIST: {
				BizPropertyConfigList bizPropertyConfigList = (BizPropertyConfigList)theEObject;
				T1 result = caseBizPropertyConfigList(bizPropertyConfigList);
				if (result == null) result = caseMetadataResourceData(bizPropertyConfigList);
				if (result == null) result = caseJRESResourceInfo(bizPropertyConfigList);
				if (result == null) result = caseExtensibleModel(bizPropertyConfigList);
				if (result == null) result = caseBasicResourceInfo(bizPropertyConfigList);
				if (result == null) result = caseIReferenceProvider(bizPropertyConfigList);
				if (result == null) result = caseIJSONData(bizPropertyConfigList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MetadataPackage.BIZ_PROPERTY_CONFIG: {
				BizPropertyConfig bizPropertyConfig = (BizPropertyConfig)theEObject;
				T1 result = caseBizPropertyConfig(bizPropertyConfig);
				if (result == null) result = caseMetadataItem(bizPropertyConfig);
				if (result == null) result = caseNamedElement(bizPropertyConfig);
				if (result == null) result = caseExtensibleModel(bizPropertyConfig);
				if (result == null) result = caseIJSONData(bizPropertyConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MD Module Common Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MD Module Common Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMDModuleCommonProperty(MDModuleCommonProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends MetadataItem> T1 caseMetadataResourceData(MetadataResourceData<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMetadataItem(MetadataItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMetadataCategory(MetadataCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Default Value List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Default Value List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypeDefaultValueList(TypeDefaultValueList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Default Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypeDefaultValue(TypeDefaultValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Data Type List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Data Type List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardDataTypeList(StandardDataTypeList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardDataType(StandardDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Data Type List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Data Type List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBusinessDataTypeList(BusinessDataTypeList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBusinessDataType(BusinessDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Field List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Field List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardFieldList(StandardFieldList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStandardField(StandardField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dictionary List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dictionary List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDictionaryList(DictionaryList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dictionary Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDictionaryType(DictionaryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dictionary Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDictionaryItem(DictionaryItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConstantList(ConstantList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConstantItem(ConstantItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Error No List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Error No List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseErrorNoList(ErrorNoList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Error No Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseErrorNoItem(ErrorNoItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>General Data Config List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>General Data Config List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGeneralDataConfigList(GeneralDataConfigList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>General Data Config Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>General Data Config Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGeneralDataConfigItem(GeneralDataConfigItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Menu Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMenuItem(MenuItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFunctionProxy(FunctionProxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Menu List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Menu List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMenuList(MenuList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ID Range Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ID Range Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDRangeItem(IDRangeItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ID Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ID Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDRange(IDRange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Biz Property Config List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Biz Property Config List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBizPropertyConfigList(BizPropertyConfigList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Biz Property Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Biz Property Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBizPropertyConfig(BizPropertyConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ID Range List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ID Range List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDRangeList(IDRangeList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBasicResourceInfo(BasicResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExtensibleModel(ExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IReference Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIReferenceProvider(IReferenceProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJSON Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIJSONData(IJSONData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>JRES Resource Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseJRESResourceInfo(JRESResourceInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T1 defaultCase(EObject object) {
		return null;
	}

} //MetadataSwitch
