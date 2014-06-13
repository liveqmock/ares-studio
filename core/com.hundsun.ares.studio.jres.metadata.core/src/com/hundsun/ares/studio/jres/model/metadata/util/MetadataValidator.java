/**
 * 源程序名称：MetadataValidator.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.context.INamespaceHelper;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.jres.metadata.MetadataCore;
import com.hundsun.ares.studio.jres.metadata.constant.IMDConstant;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfigList;
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
import com.hundsun.ares.studio.jres.model.metadata.IDRange;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeList;
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
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.validate.ValidateUtil;


/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage
 * @generated
 */
public class MetadataValidator extends EObjectValidator {
	
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final MetadataValidator INSTANCE = new MetadataValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.model.metadata";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return MetadataPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY:
				return validateMDModuleCommonProperty((MDModuleCommonProperty)value, diagnostics, context);
			case MetadataPackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case MetadataPackage.METADATA_RESOURCE_DATA:
				return validateMetadataResourceData((MetadataResourceData<?>)value, diagnostics, context);
			case MetadataPackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case MetadataPackage.METADATA_ITEM:
				return validateMetadataItem((MetadataItem)value, diagnostics, context);
			case MetadataPackage.METADATA_CATEGORY:
				return validateMetadataCategory((MetadataCategory)value, diagnostics, context);
			case MetadataPackage.TYPE_DEFAULT_VALUE_LIST:
				return validateTypeDefaultValueList((TypeDefaultValueList)value, diagnostics, context);
			case MetadataPackage.TYPE_DEFAULT_VALUE:
				return validateTypeDefaultValue((TypeDefaultValue)value, diagnostics, context);
			case MetadataPackage.STANDARD_DATA_TYPE_LIST:
				return validateStandardDataTypeList((StandardDataTypeList)value, diagnostics, context);
			case MetadataPackage.STANDARD_DATA_TYPE:
				return validateStandardDataType((StandardDataType)value, diagnostics, context);
			case MetadataPackage.BUSINESS_DATA_TYPE_LIST:
				return validateBusinessDataTypeList((BusinessDataTypeList)value, diagnostics, context);
			case MetadataPackage.BUSINESS_DATA_TYPE:
				return validateBusinessDataType((BusinessDataType)value, diagnostics, context);
			case MetadataPackage.STANDARD_FIELD_LIST:
				return validateStandardFieldList((StandardFieldList)value, diagnostics, context);
			case MetadataPackage.STANDARD_FIELD:
				return validateStandardField((StandardField)value, diagnostics, context);
			case MetadataPackage.DICTIONARY_LIST:
				return validateDictionaryList((DictionaryList)value, diagnostics, context);
			case MetadataPackage.DICTIONARY_TYPE:
				return validateDictionaryType((DictionaryType)value, diagnostics, context);
			case MetadataPackage.DICTIONARY_ITEM:
				return validateDictionaryItem((DictionaryItem)value, diagnostics, context);
			case MetadataPackage.CONSTANT_LIST:
				return validateConstantList((ConstantList)value, diagnostics, context);
			case MetadataPackage.CONSTANT_ITEM:
				return validateConstantItem((ConstantItem)value, diagnostics, context);
			case MetadataPackage.ERROR_NO_LIST:
				return validateErrorNoList((ErrorNoList)value, diagnostics, context);
			case MetadataPackage.ERROR_NO_ITEM:
				return validateErrorNoItem((ErrorNoItem)value, diagnostics, context);
			case MetadataPackage.GENERAL_DATA_CONFIG_LIST:
				return validateGeneralDataConfigList((GeneralDataConfigList)value, diagnostics, context);
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM:
				return validateGeneralDataConfigItem((GeneralDataConfigItem)value, diagnostics, context);
			case MetadataPackage.MENU_ITEM:
				return validateMenuItem((MenuItem)value, diagnostics, context);
			case MetadataPackage.FUNCTION_PROXY:
				return validateFunctionProxy((FunctionProxy)value, diagnostics, context);
			case MetadataPackage.FUNCTION:
				return validateFunction((Function)value, diagnostics, context);
			case MetadataPackage.MENU_LIST:
				return validateMenuList((MenuList)value, diagnostics, context);
			case MetadataPackage.ID_RANGE_ITEM:
				return validateIDRangeItem((IDRangeItem)value, diagnostics, context);
			case MetadataPackage.ID_RANGE_LIST:
				return validateIDRangeList((IDRangeList)value, diagnostics, context);
			case MetadataPackage.ID_RANGE:
				return validateIDRange((IDRange)value, diagnostics, context);
			case MetadataPackage.BIZ_PROPERTY_CONFIG_LIST:
				return validateBizPropertyConfigList((BizPropertyConfigList)value, diagnostics, context);
			case MetadataPackage.BIZ_PROPERTY_CONFIG:
				return validateBizPropertyConfig((BizPropertyConfig)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMDModuleCommonProperty(MDModuleCommonProperty mdModuleCommonProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(mdModuleCommonProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetadataResourceData(MetadataResourceData<?> metadataResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metadataResourceData, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(namedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetadataItem(MetadataItem metadataItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metadataItem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetadataCategory(MetadataCategory metadataCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(metadataCategory, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(metadataCategory, diagnostics, context);
		if (result || diagnostics != null) result &= validateMetadataCategory_name(metadataCategory, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateMetadataCategory_name(MetadataCategory metadataCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		
		EStructuralFeature feature = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		if (StringUtils.equalsIgnoreCase(resource.getType(), IMetadataResType.ErrNo)) {
			feature = MetadataPackage.Literals.ERROR_NO_ITEM__NO;
		}
		// 进行重名检查
		if (ValidateUtil.isDuplication(metadataCategory, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"分组名重复", 
						new Object[]{metadataCategory, feature}));
				return false;
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeDefaultValueList(TypeDefaultValueList typeDefaultValueList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(typeDefaultValueList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeDefaultValue(TypeDefaultValue typeDefaultValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(typeDefaultValue, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateTypeDefaultValue_name(typeDefaultValue, diagnostics, context);
		if (result || diagnostics != null) result &= validateTypeDefaultValue_refId(typeDefaultValue, diagnostics, context);
		return result;
	}

	private String getARESResourceLocationFullName(IARESResource res) {
		// 2012-04-05 sundl BUG #2779::当前工程与maven依赖包中的元数据冲突时，问题视图中的错误信息不准确
		try {
			IReferencedLibrary lib = res.getLib();
			if (lib != null) {
				return lib.getElementName();
			} else {
				return res.getARESProject().getElementName();
			}
		} catch (Exception e) {
			return res.getFullyQualifiedName();
		}
	}
	
	private String getARESResourceFullName(IARESResource res) {
		IResDescriptor descriptor = ARESResRegistry.getInstance().getResDescriptor(res.getType());
		if (descriptor != null && !StringUtils.isBlank(descriptor.getFileName())) {
			return descriptor.getName();
		} else {
			return res.getName();
		}
	}
	
	/**
	 * Validates the name constraint of '<em>Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTypeDefaultValue_name(TypeDefaultValue typeDefaultValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String defaultValueName = typeDefaultValue.getName();
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(defaultValueName);
		
		//
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		
		if (StringUtils.isBlank(typeDefaultValue.getName())) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "默认值ID不可为空",
						 new Object[] { typeDefaultValue, 
							MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		if(!m1.matches()){
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "默认值ID不符合A-Za-z_0-9的要求",
						 new Object[] { typeDefaultValue, 
							MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		if(typeDefaultValue.getName().length() > IMDConstant.NAME_MAX_LENGTH){
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "默认值ID长度超过" + IMDConstant.NAME_MAX_LENGTH,
						 new Object[] { typeDefaultValue, 
							MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		
		if(ValidateUtil.isDuplication(typeDefaultValue, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "默认值ID与当前资源中的ID重复",
						 new Object[] { typeDefaultValue, 
							MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		
		Pair<EObject, IARESResource> pair;
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(typeDefaultValue,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.DefValue,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			String errorInfo = String.format("默认值ID与引用【%s】的【%s】ID重复",
					getARESResourceLocationFullName(pair.second),
					getARESResourceFullName(pair.second));
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										typeDefaultValue,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Type Default Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTypeDefaultValue_refId(TypeDefaultValue typeDefaultValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #28 #25 #检查引用默认值
		//默认值引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(typeDefaultValue)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(typeDefaultValue, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"默认值引用不存在",
										new Object[] {typeDefaultValue,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"默认值" + e.getMessage(), 
							new Object[]{typeDefaultValue,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}

		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardDataTypeList(StandardDataTypeList standardDataTypeList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(standardDataTypeList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardDataType(StandardDataType standardDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(standardDataType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardDataType_name(standardDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardDataType_refId(standardDataType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardDataType_name(StandardDataType standardDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #22 #25 #查标准数据类型名称是否合法
		String standardDataName = standardDataType.getName();
		
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(standardDataName);

		if(StringUtils.isBlank(standardDataName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准数据类型ID不可为空",
						new Object[]{standardDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准数据类型ID不符合A-Za-z_0-9的要求",
						new Object[]{standardDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(standardDataName.length() > IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准数据类型ID长度超过" + IMDConstant.NAME_MAX_LENGTH,
						new Object[]{standardDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		if(ValidateUtil.isDuplication(standardDataType, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准数据类型ID与当前资源中的ID重复",
						new Object[]{standardDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		//新增引用资源中的重复检查
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		Pair<EObject, IARESResource> pair;
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(standardDataType,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.StdType,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			String errorInfo = String.format("标准数据类型ID与引用【%s】的【%s】ID重复",
					getARESResourceLocationFullName(pair.second),
					getARESResourceFullName(pair.second));
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										standardDataType,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Standard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardDataType_refId(StandardDataType standardDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #28 #20 #检查引用标准数据类型引用
		//标准数据类型引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(standardDataType)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(standardDataType, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"标准数据类型引用不存在",
										new Object[] {standardDataType,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"标准数据类型" + e.getMessage(), 
							new Object[]{standardDataType,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBusinessDataTypeList(BusinessDataTypeList businessDataTypeList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(businessDataTypeList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBusinessDataType(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(businessDataType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_name(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_refId(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_stdType(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_length(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_precision(businessDataType, diagnostics, context);
		if (result || diagnostics != null) result &= validateBusinessDataType_defaultValue(businessDataType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_name(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #21 #20 #查业务数据类型名称是否合法
		//名称不能为空，只能为包含字母下划线数字的组合
		String bussinessDataName = businessDataType.getName();
		
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(bussinessDataName);
		
		if(StringUtils.isBlank(bussinessDataName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"业务数据类型ID不可为空",
						new Object[]{businessDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"业务数据类型ID不符合A-Za-z_0-9的要求",
						new Object[]{businessDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(bussinessDataName.length() > IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"业务数据类型ID长度超过" + IMDConstant.NAME_MAX_LENGTH,
						new Object[]{businessDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		if(ValidateUtil.isDuplication(businessDataType, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"业务数据类型ID与当前资源中的ID重复",
						new Object[]{businessDataType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		Pair<EObject, IARESResource> pair;
		
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(businessDataType,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.BizType,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			String errorInfo = String.format("业务数据类型ID与引用【%s】的【%s】ID重复",
					getARESResourceLocationFullName(pair.second),
					getARESResourceFullName(pair.second));
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										businessDataType,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_refId(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #28 #15 #检查引用业务数据类型
		//业务数据类型引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(businessDataType)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(businessDataType, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"业务数据类型引用不存在",
										new Object[] {businessDataType,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"业务数据类型" + e.getMessage(), 
							new Object[]{businessDataType,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}

		return true;
	}

	/**
	 * Validates the stdType constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_stdType(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-28 #15 #20 #查业务数据类型的标准类型引用是否有效
		//检查业务数据类型引用的标准数据类型是否有效
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if( MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(businessDataType)){
			return true;
		}
		else{
			/*
			 * TODO#错误检查#龚叶峰#简单 #王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #升级引用错误检查方式
			 *
			 * 参考标准字段中检查业务数据类型的方法使用isResourceExist方法进行检查
			 */
//			IResStatisticProvider provider = (IResStatisticProvider) context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
//			IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
//			
//			String name = helper.removeNamespace(businessDataType.getStdType());
//			String namespace = helper.getSlaveNamespace(resource, businessDataType.getStdType());
			
			String stdType = businessDataType.getStdType();
			
			if(StringUtils.isBlank(stdType)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"引用的标准数据类型不可为空",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE}));
				}
				
				return false;
			}
			else{
				switch (ValidateUtil.checkReferenceId(project, stdType, IMetadataRefType.StdType, context)) {
				case ERROR_NOT_EXIST:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"引用的标准数据类型不存在", 
								new Object[]{businessDataType,
								MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE}));
					}
					return false;
				case ERROR_DUPLICATION:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"引用的标准数据类型有重复，存在歧义", 
								new Object[]{businessDataType,
								MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE}));
					}
					return false;
				}
				
//				if( !provider.isResouceExist(name, namespace, IJRESReferenceType.StdType)){
//					
//					if(diagnostics != null){
//						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//								DIAGNOSTIC_SOURCE, 
//								0, 
//								"引用的标准数据类型不存在", 
//								new Object[]{businessDataType,
//								MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE}));
//					}
//					
//					
//					return false;
//				}
			}
		}
		return true;
	}

	/**
	 * Validates the length constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_length(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #13 #20 #查业务数据类型的长度是否合法

		/*
		 * TODO#错误检查#龚叶峰#普通 #王彬#已编码 #2011-09-02 #35 #20 #修正优化代码
		 *
		 * 1、首先检查不为空的情况下是否是数字
		 * 2、获取引用的标准数据类型是否需要长度精度 getStandardDataTypeLPNeed
		 * 3、根据获取的结果判断长度或精度是否符合要求
		 */
		//获取长度值
		String biztlength = businessDataType.getLength();
		//标准数据类型引用
		String stdType = businessDataType.getStdType();
		//判断长度不为空时是否为整数
		if(!StringUtils.isBlank(biztlength)){//精度不为空进行检查是否为整数
			
			try{
				Integer.parseInt(biztlength);
			}
			catch(NumberFormatException e){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"业务数据类型的长度为非整数",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__LENGTH}));
				}
				return false;
			}
			return true;
			
		}else if(!StringUtils.isBlank(stdType)){//获取引用的标准数据类型是否为空
			
			Pair<Boolean, Boolean> pair = getStandardDataTypeLPNeed(stdType,context);//.second;
			if(pair.first){//判断是否存在$L
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"业务数据类型的长度不可为空",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__LENGTH}));
				}
				return false;
			}else{
				return true;
			}
			
		}else{//引用为空，长度值为空，返回true
			return true;
		}
		
	}
		

	/**
	 * Validates the precision constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_precision(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #13 #14 #查业务数据类型的精度是否合法
		
		/*
		 * TODO#错误检查#龚叶峰#普通 #王彬#已编码 #2011-09-02 #35 #20 #修正优化代码
		 *
		 * 1、首先检查不为空的情况下是否是数字
		 * 2、获取引用的标准数据类型是否需要长度精度 getStandardDataTypeLPNeed
		 * 3、根据获取的结果判断长度或精度是否符合要求
		 */
		
		//获取精度值
		String precision = businessDataType.getPrecision();
		//标准数据类型引用
		String stdType = businessDataType.getStdType();
		//判断精度不为空时是否为整数
		if(!StringUtils.isBlank(precision)){//精度不为空进行检查是否为整数
			
			try{
				Integer.parseInt(precision);
			}catch(NumberFormatException e){
				
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"业务数据类型的精度为非整数",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__PRECISION}));
				}
				return false;
			}
			return true;
			
		}else if(!StringUtils.isBlank(stdType)){//获取引用的标准数据类型是否为空
			
			Pair<Boolean, Boolean> pair = getStandardDataTypeLPNeed(stdType,context);//.second;
			if(pair.second){//判断是否存在$P
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"业务数据类型的精度不可为空",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__PRECISION}));
				}
				return false;
			}else{
				return true;
			}
			
		}else{//引用为空，精度值为空，返回true
			return true;
		}
		
	}

	/**
	 * Validates the defaultValue constraint of '<em>Business Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateBusinessDataType_defaultValue(BusinessDataType businessDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-28 #15 #20 #查业务数据类型的默认值引用是否有效
		//检查业务数据类型引用的默认值是否有效
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(businessDataType)){
			return true;
		}
		else{
			/*
			 * TODO#错误检查#龚叶峰#简单 #王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #升级引用错误检查方式
			 *
			 * 参考标准字段中检查业务数据类型的方法使用isResourceExist方法进行检查
			 */
//			IResStatisticProvider provider = (IResStatisticProvider) context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
//			IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
//			
//			String name = helper.removeNamespace(businessDataType.getDefaultValue());
//			String namespace = helper.getSlaveNamespace(resource, businessDataType.getDefaultValue());
			
			String defaultValue = businessDataType.getDefaultValue();
			
			if(StringUtils.isBlank(defaultValue)){
				
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"引用的数据类型默认值不可为空",
							new Object[]{businessDataType,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE}));
				}
				
				return false;
			}
			else{
				switch (ValidateUtil.checkReferenceId(project, defaultValue, IMetadataRefType.DefValue, context)) {
				case ERROR_NOT_EXIST:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"引用的类型默认值不存在", 
								new Object[]{businessDataType,
								MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE}));
					}
					return false;
				case ERROR_DUPLICATION:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"引用的类型默认值有重复，存在歧义", 
								new Object[]{businessDataType,
								MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE}));
					}
					return false;
				}
//				if( !provider.isResouceExist(name, namespace, IJRESReferenceType.DefValue)){
//					if(diagnostics != null){
//						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//								DIAGNOSTIC_SOURCE, 
//								0, 
//								"引用的数据类型默认值不存在", 
//								new Object[]{businessDataType,
//								MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE}));
//					}
//					return false;
//				}
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardFieldList(StandardFieldList standardFieldList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(standardFieldList, diagnostics, context);
	}
	

	/**
	 * 返回指定的标准数据类型是否需要 长度 和 精度信息
	 * @param stdType
	 * @param context
	 * @return
	 */
	protected Pair<Boolean, Boolean> getStandardDataTypeLPNeed(String stdType, Map<Object, Object> context) {
		if (StringUtils.isBlank(stdType)) {
			return new Pair<Boolean, Boolean>(Boolean.FALSE, Boolean.FALSE);
		}
		Object key = "getStandardDataTypeLPNeed";
		Map<String, Pair<Boolean, Boolean>> stdTypeMap = (Map<String, Pair<Boolean, Boolean>>) context.get(key);
		if (stdTypeMap == null) {
			context.put(key, stdTypeMap = new HashMap<String, Pair<Boolean,Boolean>>());
		}
		
		Pair<Boolean, Boolean> result = stdTypeMap.get(stdType);
		if (result == null) {
			boolean needLength = false;
			boolean needPrecision = false;
			
			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
			
			String name = helper.removeNamespace(stdType);
			String namespace = StringUtils.defaultIfBlank(helper.getNamespace(stdType), IResourceTable.Scope_IGNORE_NAMESPACE);
			IARESProject project = (IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			ReferenceInfo referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdType, name, true);
			if (referenceInfo!=null ) {
				StandardDataType stdTypeObj =(StandardDataType)(referenceInfo.getObject());
				IARESResource res = referenceInfo.getResource();
				
				DeStandardDataType dsdt = MetadataUtil.decrypt(stdTypeObj, res);
				// 可能只在注册的语言中查找长度和精度占位符
				for (Entry<String, String> entry : dsdt.getDataMap().entrySet()) {
					// 查找是否有定义需要使用长度和精度
					needLength |= entry.getValue().contains("$L");
					needPrecision |= entry.getValue().contains("$P");
				}
			}
			
			// 进行结果缓存
			stdTypeMap.put(stdType, result = new Pair<Boolean, Boolean>(needLength, needPrecision));
		}
		return result;
	}
	
	
	/**
	 * 检查数据字典中的字典项中的常量是否重复
	 * @param item
	 * @param context
	 * @return
	 */
	protected boolean isDuplicationConstName(DictionaryItem item, Map<Object, Object> context) {
		if (item.eContainer() == null || item.eContainer().eContainer() == null) {
			return false;
		}
		Object key = "isDuplicationConstName";
		Set<DictionaryItem> errors = (Set<DictionaryItem>) context.get(key);
		if (errors == null) {
			context.put(key, errors = new HashSet<DictionaryItem>());
			
			Map<String, DictionaryItem> map = new HashMap<String, DictionaryItem>();
			DictionaryList list = (DictionaryList) item.eContainer().eContainer();
			for (DictionaryType type : list.getItems()) {
				for (DictionaryItem di : type.getItems()) {
					String constName = di.getConstantName();
					if (!StringUtils.isBlank(constName)) {
						DictionaryItem child = map.get(constName);
						if (child != null) {
							// 有重复
							errors.add(di);
							errors.add(child);
						} else {
							map.put(constName, di);
						}
					}
				}
			}
		}
		return errors.contains(item);
	}
	

	
	

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardField(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(standardField, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_name(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_refId(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_length(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_precision(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_dataType(standardField, diagnostics, context);
		if (result || diagnostics != null) result &= validateStandardField_dictionaryType(standardField, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_name(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #22 #25 #查标准字段的名称是否合法
		String standardFieldName = standardField.getName();
		
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(standardFieldName);
		
		if(StringUtils.isBlank(standardFieldName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准字段ID不可为空",
						new Object[]{standardField,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准字段ID不符合A-Za-z_0-9的要求",
						new Object[]{standardField,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(standardFieldName.length() > IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"标准字段ID长度超过"+IMDConstant.NAME_MAX_LENGTH,
						new Object[]{standardField,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		// 进行重名检查
		if (ValidateUtil.isDuplication(standardField, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"标准字段ID与当前资源中ID重复", 
						new Object[]{standardField, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
		}
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		Pair<EObject, IARESResource> pair;
		
		
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(standardField,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.StdField,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			String errorInfo = String.format("标准字段ID与引用【%s】的【%s】ID重复",
					getARESResourceLocationFullName(pair.second),
					getARESResourceFullName(pair.second));
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										standardField,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_refId(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #28 #15 #检查引用标准字段
		// 标准字段引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(standardField)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(standardField, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"标准字段引用不存在",
										new Object[] {standardField,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"标准字段" + e.getMessage(), 
							new Object[]{standardField,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * Validates the length constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_length(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-20 #20 #30 #查标准字段的长度是否合法
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if( MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(standardField)){
			return true;
		}
		else{
			String standardFieldLength = standardField.getLength();
			
			if(StringUtils.isBlank(standardFieldLength)){
				return true;
			}
			else{
				try{
					Integer.parseInt(standardFieldLength);
				}
				catch (NumberFormatException e) {
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段长度为非整数", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__LENGTH
						}));
					}
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validates the precision constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_precision(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-20 #20 #25 #查标准字段的精度是否合法
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(standardField)){
			return true;
		}
		else{
			String standardFieldPrecision = standardField.getPrecision();
			
			if(StringUtils.isBlank(standardFieldPrecision)){
				return true;
			}
			else{
				try{
					Integer.parseInt(standardFieldPrecision);
				}
				catch (NumberFormatException e) {
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段精度为非整数", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__PRECISION
						}));
					}
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validates the dataType constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_dataType(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-28#15 #20 #查标准字段的业务数据引用是否有效
		//检查标准字段引用的业务数据类型是否有效
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(standardField)){
			return true;
		}
		else{

			String dataType = standardField.getDataType();
			
			if(StringUtils.isBlank(dataType)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"标准字段：["+standardField.getName()+"] ,引用的业务数据类型不可为空", 
							new Object[]{standardField,
							MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE}));
				}
				return false;
			}
			else{
				switch (ValidateUtil.checkReferenceId(project, dataType, IMetadataRefType.BizType, context)) {
				case ERROR_NOT_EXIST:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段：["+standardField.getName()+"] ,引用的业务数据类型不存在: [" +dataType+ "]", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE}));
					}
					return false;
				case ERROR_DUPLICATION:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段：["+standardField.getName()+"] ,引用的业务数据类型有重复，存在歧义", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE}));
					}
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Validates the dictionaryType constraint of '<em>Standard Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStandardField_dictionaryType(StandardField standardField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-28 #15 #18 #查标准字段的数据字典引用是否有效
		//检查标准字段引用的数据字典是否有效
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(standardField)){
			return true;
		}
		else{
			/*
			 * TODO#错误检查#龚叶峰#简单 #王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #升级引用错误检查方式
			 *
			 * 参考标准字段中检查业务数据类型的方法使用isResourceExist方法进行检查
			 */
//			IResStatisticProvider provider = (IResStatisticProvider)context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
//			IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
//			
//			String name = helper.removeNamespace(standardField.getDictionaryType());
//			String namespace = helper.getSlaveNamespace(resource, standardField.getDictionaryType());
			
			String dictionaryType = standardField.getDictionaryType();
			
			if(StringUtils.isBlank(dictionaryType)){
				return true;
			}
			else{
				switch (ValidateUtil.checkReferenceId(project, dictionaryType, IMetadataRefType.Dict, context)) {
				case ERROR_NOT_EXIST:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段：["+standardField.getName()+"] ,引用的数据字典条目不存在", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE}));
					}
					return false;
				case ERROR_DUPLICATION:
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"标准字段：["+standardField.getName()+"] ,引用的数据字典条目有重复，存在歧义", 
								new Object[]{standardField,
								MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE}));
					}
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDictionaryList(DictionaryList dictionaryList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dictionaryList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDictionaryType(DictionaryType dictionaryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dictionaryType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validateDictionaryType_name(dictionaryType, diagnostics, context);
		if (result || diagnostics != null) result &= validateDictionaryType_refId(dictionaryType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDictionaryType_name(DictionaryType dictionaryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19 #22 #30 #查数据词典分组或项目的名称是否合法
		String dictionaryEntryName = dictionaryType.getName();
		
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(dictionaryEntryName);
		
		if(StringUtils.isBlank(dictionaryEntryName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"数据字典条目ID不可为空",
						new Object[]{dictionaryType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"数据字典条目ID不符合A-Za-z_0-9的要求",
						new Object[]{dictionaryType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(dictionaryEntryName.length() > IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"数据字典条目ID长度超过"+IMDConstant.NAME_MAX_LENGTH,
						new Object[]{dictionaryType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		if(ValidateUtil.isDuplication(dictionaryType, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"数据字典条目ID与当前资源中的ID重复",
						new Object[]{dictionaryType,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		Pair<EObject, IARESResource> pair;
		
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(dictionaryType,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.Dict,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			String errorInfo = String.format("数据字典条目ID与引用【%s】的【%s】ID重复",
					getARESResourceLocationFullName(pair.second),
					getARESResourceFullName(pair.second));
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										dictionaryType,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}

		//新增引用资源中的重复检查
//		IResStatisticProvider provider = (IResStatisticProvider)context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//		int count = provider.getResourceCount(dictionaryEntryName, IResourceTable.VALUE_IGNORE_NAMESPACE, IJRESReferenceType.Dict);
		
//		if(isDuplicationInReferenceScope(dictionaryType, MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				IJRESReferenceType.Dict, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"数据字典条目ID与引用资源中的ID重复，请检查引用资源包或引用工程",
//						new Object[]{dictionaryType,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//			}
//			return false;
//		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Dictionary Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDictionaryType_refId(DictionaryType dictionaryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03#28 #15 #检查引用数据字典类型
		//数据字典类型引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(dictionaryType)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(dictionaryType, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"数据字典引用不存在",
										new Object[] {dictionaryType,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"数据字典" + e.getMessage(), 
							new Object[]{dictionaryType,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDictionaryItem(DictionaryItem dictionaryItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dictionaryItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateDictionaryItem_value(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateDictionaryItem_name(dictionaryItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateDictionaryItem_constantName(dictionaryItem, diagnostics, context);
		return result;
	}

	/**
	 * Validates the value constraint of '<em>Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDictionaryItem_value(DictionaryItem dictionaryItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-20 #12 #15 #查数据字典条目的值是否合法
		
		String dictionaryItemValue = dictionaryItem.getValue();
		
		// 2013-05-14 sundl 字典项值可以为空
//		if(StringUtils.isEmpty(dictionaryItemValue)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//						DIAGNOSTIC_SOURCE, 
//						0, 
//						"字典项不可为空", 
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__VALUE}));
//			}
//			return false;
//		}
		
		if(ValidateUtil.isDuplication(dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__VALUE, context)){
				if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								"字典项重复", 
								new Object[]{dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__VALUE}));
				}
				return false;
		}
		
		if(dictionaryItemValue.length()>20){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"字典项长度超过20", 
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__VALUE}));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the name constraint of '<em>Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDictionaryItem_name(DictionaryItem dictionaryItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#简单#王彬#已编码 #2011-07-19#22 #25 #查数据字典条目的名称合法性
		String dictionaryItemName = dictionaryItem.getName();
		
	/*	Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(dictionaryItemName);
		
		if(StringUtils.isBlank(dictionaryItemName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"字典项ID不可为空",
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__NAME}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"字典项ID不符合A-Za-z_0-9的要求",
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__NAME}));
			}
			return false;
		}
		if(dictionaryItemName.length()>30){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"字典项ID长度超过30", 
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__NAME}));
			}
			return false;
		}
		
		
		if(ValidateUtil.isDuplication(dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"字典项ID与当前资源中的ID重复", 
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__NAME}));
			}
			return false;
		}*/
		return true;
	}

	// sundl 2013-05-10 去掉长度检查
	private static final String DictionaryItem_constant_regx = "[A-Z_][A-Z_0-9]*";
	/**
	 * Validates the constantName constraint of '<em>Dictionary Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDictionaryItem_constantName(DictionaryItem dictionaryItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		/*
		 * TODO#错误检查#龚叶峰#一般 #王彬#已编码#2011-08-04 #20 #10 #数据字典项常量ID不重名
		 *
		 * 错误号常量名不重名
		 */
		/*
		 * TODO#错误检查#龚叶峰#普通 #王小恒#已编码 #2011-8-31 #35#30 #重名相关检查
		 *
		 * 调整代码结构，先检查常量id的格式是否正确，其次检查常量id是否在自身资源内重名，再检查常量id是否在用户常量和错误号常量中重名
		 * 
		 * 判断自身是否重名方法是
		 * isDuplicationConstName(dictionaryItem, context);
		 * 
		 * 判断是否和用户常量重名
		 * getConstIdSetFromConstList(context).contains(dictionaryItem.getConstantName());
		 * 
		 * 判断是否和错误号的常量重名
		 * getConstIdSetFromErrorNoList(context).contains(dictionaryItem.getConstantName());
		 */
		String constantName = dictionaryItem.getConstantName();
		
		Pattern p1 = Pattern.compile(DictionaryItem_constant_regx);
		Matcher m1 = p1.matcher(constantName);
		//用户常量ID可为空
		if(StringUtils.isBlank(constantName)){
			return true;
		}

		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						String.format("常量ID不符合%s的数据要求",DictionaryItem_constant_regx), 
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME
				}));
			}
			return false;
		}
		// sundl 2013-05-10 去掉常量长度的检查

		if(isDuplicationConstName(dictionaryItem, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"常量ID与当前资源中的ID重名", 
						new Object[]{dictionaryItem,
						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME
				}));
			}
			return false;
		}
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		String errorInfo = new String();
		//用户常量
		Pair<EObject, IARESResource> pair;
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(dictionaryItem,
				MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
				IMetadataRefType.Const,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			//判断本工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("同一命名空间字典项常量ID与【%s】常量ID重复",
						getARESResourceFullName(pair.second));
			}
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										dictionaryItem,
										MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME }));
			}
			return false;
		}
		//数据字典
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(dictionaryItem,
				MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
				IMetadataRefType.Dictionary_Const,
				MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
				Collections.singleton(resource), context)) != null) {
			
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										dictionaryItem,
										MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME }));
			}
			return false;
		}
		//错误号
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(dictionaryItem,
				MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
				IMetadataRefType.ErrNo,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			//判断本工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("同一命名空间字典项常量ID与【%s】常量ID重复",
						getARESResourceFullName(pair.second));
			}
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("字典项常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										dictionaryItem,
										MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME }));
			}
			return false;
		}
		
//		if(getConstIdSetFromConstList(context).contains(constantName)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//						DIAGNOSTIC_SOURCE, 
//						0, 
//						"同一命名空间中常量ID与用户常量ID存在重名", 
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME
//				}));
//			}
//			return false;
//		}
//		if(getConstIdSetFromErrorNoList(context).contains(constantName)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//						DIAGNOSTIC_SOURCE, 
//						0, 
//						"同一命名空间中常量ID与错误号常量ID存在重名", 
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME
//				}));
//			}
//			return false;
//		}
		
		//新增引用资源中的重复检查
//		IResStatisticProvider provider = (IResStatisticProvider)context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//		int count = provider.getResourceCount(constantName, IResourceTable.VALUE_IGNORE_NAMESPACE, IJRESReferenceType.Dictionary_Const);
		
//		if(isDuplicationInReferenceScope(dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
//				IJRESReferenceType.Dictionary_Const, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中数据字典的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME}));
//			}
//			return false;
//		}
//		
//		if(isDuplicationInReferenceScope(dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
//				IJRESReferenceType.Const, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中用户常量的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME}));
//			}
//			return false;
//		}
//		
//		if(isDuplicationInReferenceScope(dictionaryItem, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
//				IJRESReferenceType.ErrNo_Const, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中错误号的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{dictionaryItem,
//						MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME}));
//			}
//			return false;
//		}
		
		return true;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstantList(ConstantList constantList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(constantList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstantItem(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(constantItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_name(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_refId(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_dataType(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_value(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_length(constantItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateConstantItem_precision(constantItem, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_name(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#普通#王小恒#已编码#2011-07-21#26 #25 #查用户常量的分组和项目的名称合法性
		
		/*
		 * TODO#错误检查#龚叶峰#普通 #王小恒#已编码 #2011-8-31 #34 #20 #重名相关检查
		 *
		 * 调整代码结构，先检查常量id的格式是否正确，其次检查常量id是否在自身资源内重名，再检查常量id是否在数据字典和错误号常量中重名
		 * 
		 * 判断自身是否重名方法是
		 * isDuplication(constantItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context);
		 * 
		 * 判断是否和数据字典常量重名
		 * getConstIdSetFromDictList(context).contains(constantItem.getName());
		 * 
		 * 判断是否和错误号的常量重名
		 * getConstIdSetFromErrorNoList(context).contains(constantItem.getName());
		 */
		
		String constantEntryName = constantItem.getName();
		String errorInfo = new String();
		Pattern p1 = Pattern.compile("[A-Za-z_][A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(constantEntryName);
		
		if(StringUtils.isBlank(constantEntryName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"常量ID不可为空", 
						new Object[]{constantItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME
				}));
			}
			return false;
		}
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"常量ID不符合[A-Za-z_][A-Za-z_0-9]的数据要求", 
						new Object[]{constantItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME
				}));
			}
			return false;
		}
		if(constantEntryName.length()>IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"常量ID字符长度大于"+ IMDConstant.NAME_MAX_LENGTH, 
						new Object[]{constantItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME
				}));
			}
			return false;
		}
		
		if(ValidateUtil.isDuplication(constantItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"常量ID与当前资源中的ID重名", 
						new Object[]{constantItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME
				}));
			}
			return false;
		}
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		Pair<EObject, IARESResource> pair;
		
		//用户常量
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(constantItem,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.Const,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {
			
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										constantItem,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		
		//数据字典
		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(constantItem,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.Dictionary_Const,
				MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
				Collections.singleton(resource), context)) != null) {
			
			
			//判断本工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("同一命名空间用户常量ID与【%s】常量ID重复",
						getARESResourceFullName(pair.second));
			}
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										constantItem,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
		//错误号
		if ((pair = ValidateUtil.checkDuplicationInResStatisticProvider(constantItem,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				IMetadataRefType.ErrNo,
				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
				Collections.singleton(resource), context)) != null) {

			//判断本工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("同一命名空间用户常量ID与【%s】常量ID重复",
						getARESResourceFullName(pair.second));
			}
			//判断引用包
			if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			//判断引用工程
			if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
				errorInfo = String.format("用户常量ID与引用【%s】中【%s】常量ID重复",
						getARESResourceLocationFullName(pair.second),
						getARESResourceFullName(pair.second));
			}
			
			if (diagnostics != null) {
				diagnostics
						.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								errorInfo,
								new Object[] {
										constantItem,
										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
			}
			return false;
		}
//		if(getConstIdSetFromDictList(context).contains(constantItem.getName())){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//						DIAGNOSTIC_SOURCE, 
//						0, 
//						"同一命名空间中常量ID与数据字典项的常量ID存在重名", 
//						new Object[]{constantItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME
//				}));
//			}
//			return false;
//		}
//		if(getConstIdSetFromErrorNoList(context).contains(constantItem.getName())){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//						DIAGNOSTIC_SOURCE, 
//						0, 
//						"同一命名空间中常量ID与错误号常量ID存在重名", 
//						new Object[]{constantItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME
//				}));
//			}
//			return false;
//		}
		
		//新增引用资源中的重复检查
		
		
//		if(isDuplicationInReferenceScope(constantItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				IJRESReferenceType.Const, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中用户常量的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{constantItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//			}
//			return false;
//		}
//		
//		if(isDuplicationInReferenceScope(constantItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				IJRESReferenceType.ErrNo_Const, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中错误号的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{constantItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//			}
//			return false;
//		}
//		
//		if(isDuplicationInReferenceScope(constantItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				IJRESReferenceType.Dictionary_Const, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME, context)){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"常量ID与引用资源中数据字典的常量ID重名，请检查引用资源包或引用工程",
//						new Object[]{constantItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//			}
//			return false;
//		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_refId(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码#2011-08-03#18 #20 #检查引用用户常量引用检查
		//用户常量引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		 
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(constantItem)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(constantItem, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"用户常量引用不存在",
										new Object[] {constantItem,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"用户常量" + e.getMessage(), 
							new Object[]{constantItem,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * Validates the dataType constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_dataType(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#普通#王小恒#已编码 #2011-07-28 #15 #20 #查用户常量数据类型引用是否有效
		//检查用户常量引用的标准数据类型是否有效
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(constantItem)){
			return true;
		}else{
			String dateType = constantItem.getDataType();
			if(StringUtils.isBlank(dateType)){
				//由于C环境下是可以为空的，所以为空判定为正确
				//2012-11-20 15:57:07 吕高
				return true;    
			}
			
			switch (ValidateUtil.checkReferenceId(project, dateType, IMetadataRefType.BizType, context)) {
			case ERROR_NOT_EXIST:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"用户常量：["+constantItem.getName()+"] ,引用的业务数据类型不存在: [" +dateType+ "]", 
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__DATA_TYPE}));
				}
				return false;
			case ERROR_DUPLICATION:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"用户常量：["+constantItem.getName()+"] ,引用的业务数据类型有重复，存在歧义", 
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__DATA_TYPE}));
				}
				return false;
			}
			
			/*
			 * TODO#错误检查#龚叶峰#简单 #王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #升级引用错误检查方式
			 *
			 * 参考标准字段中检查业务数据类型的方法使用isResourceExist方法进行检查
			 */
//			IResStatisticProvider provider = (IResStatisticProvider) context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
//			IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
//			
//			String name = helper.removeNamespace(constantItem.getDataType());
//			String namespace = helper.getSlaveNamespace(resource, constantItem.getDataType());
//			
//			if( !provider.isResouceExist(name, namespace, IJRESReferenceType.StdType)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//							DIAGNOSTIC_SOURCE, 
//							0, 
//							"引用的标准数据类型不存在", 
//							new Object[]{constantItem,
//							MetadataPackage.Literals.CONSTANT_ITEM__DATA_TYPE}));
//				}
//				return false;
//			}
		}
		return true;
	}

	/**
	 * Validates the value constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_value(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#普通#王小恒#已编码 #2011-07-20 #12 #15 #查用户常量的值的合法性
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(constantItem)){
			return true;
		}else{
			String constantItemValue = constantItem.getValue();
			if(StringUtils.isBlank(constantItemValue)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"用户常量常量值不可为空", 
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__VALUE}));
				}
				return false;
			}
			
			/*
			 * TODO#错误检查#龚叶峰#简单#龚叶峰#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #修正bug
			 *
			 * 用户常量的值不是默认值的引用，所以没必要检查引用，其次上面的添加错误(diagnostics.add...)后就需要return false了
			 */
//			IResStatisticProvider provider = (IResStatisticProvider) context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
//			IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
//			
//			String name = helper.removeNamespace(constantItem.getValue());
//			String namespace = helper.getSlaveNamespace(resource, constantItem.getValue());
//			
//			if( !provider.isResouceExist(name, namespace, IJRESReferenceType.DefValue)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//							DIAGNOSTIC_SOURCE, 
//							0, 
//							"引用的数据类型默认值不存在", 
//							new Object[]{constantItem,
//							MetadataPackage.Literals.CONSTANT_ITEM__VALUE}));
//				}
//				return false;
//			}
		}
		return true;
	}

	/**
	 * Validates the length constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_length(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #18 #10 #用户常量长度检查
		//必须为数字，只有当引用的标准数据类型中不需要长度信息的时候才可以为空
		
		/*
		 * TODO#错误检查#龚叶峰#普通 #王彬#已编码 #2011-09-02 #35 #20 #修正优化代码
		 *
		 * 1、首先检查不为空的情况下是否是数字
		 * 2、获取引用的标准数据类型是否需要长度精度 getStandardDataTypeLPNeed
		 * 3、根据获取的结果判断长度或精度是否符合要求
		 */
		
		//获取长度值
		String constlength = constantItem.getLength();
		//标准数据类型引用
		String stdType = constantItem.getDataType();
		//判断长度不为空时是否为整数
		if(!StringUtils.isBlank(constlength)){//精度不为空进行检查是否为整数
			
			try{
				Integer.parseInt(constlength);
			}catch(NumberFormatException e){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"用户常量长度为非整数",
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__LENGTH}));
				}
				return false;
			}
			return true;
			
		}else if(!StringUtils.isBlank(stdType)){//获取引用的标准数据类型是否为空
			
			Pair<Boolean, Boolean> pair = getStandardDataTypeLPNeed(stdType,context);//.second;
			if(pair.first){//判断是否存在$L
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"用户常量长度不可为空",
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__LENGTH}));
				}
				return false;
			}else{
				return true;
			}
			
		}else{//引用为空，长度值为空，返回true
			return true;
		}
		
		
	}
		

	/**
	 * Validates the precision constraint of '<em>Constant Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateConstantItem_precision(ConstantItem constantItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		/*
		 * TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #18 #5 #常量精度检查
		 * 必须为整数，只有当引用的标准数据类型没有精度要求的时候可以为空
		 */
		/*
		 * TODO#错误检查#龚叶峰#普通 #王彬#已编码 #2011-09-02 #35 #20 #修正优化代码
		 *
		 * 1、首先检查不为空的情况下是否是数字
		 * 2、获取引用的标准数据类型是否需要长度精度 getStandardDataTypeLPNeed
		 * 3、根据获取的结果判断长度或精度是否符合要求
		 */
		
		//获取精度值
		String constprecision = constantItem.getPrecision();
		//标准数据类型引用
		String stdType = constantItem.getDataType();
		//判断精度不为空时是否为整数
		if(!StringUtils.isBlank(constprecision)){//精度不为空进行检查是否为整数
			
			try{
				Integer.parseInt(constprecision);
			}catch(NumberFormatException e){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"用户常量精度为非整数",
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__PRECISION}));
				}
				return false;
			}
			return true;
			
		}else if(!StringUtils.isBlank(stdType)){//获取引用的标准数据类型是否为空
			
			Pair<Boolean, Boolean> pair = getStandardDataTypeLPNeed(stdType,context);//.second;
			if(pair.second){//判断是否存在$P
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"用户常量精度不可为空",
							new Object[]{constantItem,
							MetadataPackage.Literals.CONSTANT_ITEM__PRECISION}));
				}
				return false;
			}else{
				return true;
			}
			
		}else{//引用为空，精度值为空，返回true
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateErrorNoList(ErrorNoList errorNoList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(errorNoList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateErrorNoItem(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(errorNoItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_name(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_refId(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_constantName(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_message(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_no(errorNoItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateErrorNoItem_level(errorNoItem, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_name(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO#错误检查#龚叶峰#简单#王彬#已编码#2011-07-19 #12 #25 #查错误号名称合法性
		String errorNoEntryName = errorNoItem.getName();
		
		if(StringUtils.isBlank(errorNoEntryName)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"常量定义不可为空",
						new Object[]{errorNoItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		Pattern p1 = Pattern.compile("[A-Z_0-9]*");
		Matcher m1 = p1.matcher(errorNoEntryName);
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING,
						DIAGNOSTIC_SOURCE,
						0,
						"常量定义不符合A-Z_0-9的数据要求",
						new Object[]{errorNoItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
//      2013-05-14 sundl 去掉长度的错误检查		
//		if(errorNoEntryName.length()>IMDConstant.NAME_MAX_LENGTH){
//			if(diagnostics != null){
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						String.format("常量定义字符长度大于%d", IMDConstant.NAME_MAX_LENGTH),
//						new Object[]{errorNoItem,
//						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//			}
//			return false;
//		}
		
		if(ValidateUtil.isDuplication(errorNoItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"常量定义与当前资源中的常量定义重复",
						new Object[]{errorNoItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		
		return true;
	}

	/**
	 * Validates the refId constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_refId(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		//TODO#模块名#龚叶峰#完成难度 #王彬#已编码 #2011-08-03 #28) #15 #检查引用错误号类型
		//错误号类型引用检查，可以为空，但是不为空时，引用的对象必须存在，且不能引用自己
		 
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		
		if(!MetadataUtil.isUseRefFeature(resource) || !MetadataUtil.isReferencingItem(errorNoItem)){
			return true;
		} else {
			try {
				if ( MetadataUtil.resolve(errorNoItem, resource) == null){
					if (diagnostics != null) {
						diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"错误号引用不存在",
										new Object[] {errorNoItem,
										MetadataPackage.Literals.METADATA_ITEM__REF_ID }));
					}
					return false;
				}
			} catch (CircularReferenceException e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"错误号" + e.getMessage(), 
							new Object[]{errorNoItem,
							MetadataPackage.Literals.METADATA_ITEM__REF_ID}));
				}
				return false;
			}
			
		}
		
		return true;
	}
	
	/**
	 * 检查名称和数据字典常量和用户常量是否重复
	 * @param errorNoItem
	 * @param diagnostics
	 * @param context
	 * @return
	 */
	public boolean validateErrorNoItem_constant(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
	
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(errorNoItem)){
			return true;
		}
			Pair<EObject, IARESResource> pair;
			String errorInfo = new String();
			
			//用户常量
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					IMetadataRefType.Const,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断本工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("同一命名空间常量ID与【%s】常量ID重复",
							getARESResourceFullName(pair.second));
				}
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
				}
				return false;
			}
			
			//数据字典
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					IMetadataRefType.Dictionary_Const,
					MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断本工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("同一命名空间常量ID与【%s】常量ID重复",
							getARESResourceFullName(pair.second));
				}
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
				}
				return false;
			}
			//错误号
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					IMetadataRefType.ErrNo_Const,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
				}
				return false;
			}
			return true;
		}

	/**
	 * Validates the constantName constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_constantName(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validateErrorNoItem_constant(errorNoItem, diagnostics, context);
		
		
		/*
		 * TODO#错误检查#龚叶峰#一般 #王彬#已编码 #2011-08-04 #20 #10 #错误号常量ID不重名
		 *
		 * 错误号常量名不重名
		 */
		
		
		/*
		 * TODO#错误检查#龚叶峰#普通 #王小恒#已编码 #2011-8-31 #33 #20 #重名相关检查
		 *
		 * 调整代码结构，先检查常量id的格式是否正确，其次检查常量id是否在自身资源内重名，再检查常量id是否在数据字典和错误号中重名
		 * 
		 * 判断自身是否重名方法是
		 * isDuplication(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME, context);
		 * 
		 * 
		 * 判断是否和用户常量重名
		 * getConstIdSetFromConstList(context).contains(errorNoItem.getConstantName());
		 * 
		 * 判断是否和数据字典的常量重名
		 * getConstIdSetFromDictList(context).contains(errorNoItem.getConstantName());
		 */
		/***
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(errorNoItem)){
			return true;
		}
		else{
			String constantName = errorNoItem.getConstantName();
			
			Pattern p1 = Pattern.compile("[A-Z_0-9]*");
			Matcher m1 = p1.matcher(constantName);
			
			//用户常量ID可为空
			if(StringUtils.isBlank(constantName)){
				return true;
			}
			
			
			if(!m1.matches()){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"常量ID不符合A-Z_0-9的数据要求",
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
				}
				return false;
			}						
			if(constantName.length()>30){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"常量ID字符长度大于30",
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
				}
				return false;
			}
			
			if(ValidateUtil.isDuplication(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME, context)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"常量ID与当前资源中的ID重名",
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
				}
				return false;
			}
			
			Pair<EObject, IARESResource> pair;
			String errorInfo = new String();
			
			//用户常量
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
					IMetadataRefType.Const,
					MetadataPackage.Literals.NAMED_ELEMENT__NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断本工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("同一命名空间常量ID与【%s】常量ID重复",
							getARESResourceFullName(pair.second));
				}
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME }));
				}
				return false;
			}
			
			//数据字典
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
					IMetadataRefType.Dictionary_Const,
					MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断本工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("同一命名空间常量ID与【%s】常量ID重复",
							getARESResourceFullName(pair.second));
				}
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME }));
				}
				return false;
			}
			//错误号
			if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(errorNoItem,
					MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
					IMetadataRefType.ErrNo_Const,
					MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
					Collections.singleton(resource), context)) != null) {
				
				//判断引用包
				if(pair.second.getLib() != null && resource.getARESProject().equals(pair.second.getARESProject())){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				//判断引用工程
				if(pair.second.getLib() == null && resource.getARESProject().equals(pair.second.getARESProject()) == false){
					errorInfo = String.format("错误号常量ID与引用【%s】中【%s】常量ID重复",
							getARESResourceLocationFullName(pair.second),
							getARESResourceFullName(pair.second));
				}
				
				if (diagnostics != null) {
					diagnostics
							.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									errorInfo,
									new Object[] {errorNoItem,
											MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME }));
				}
				return false;
			}
			**/
			
//			if(getConstIdSetFromConstList(context).contains(constantName)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//							DIAGNOSTIC_SOURCE,
//							0,
//							"同一命名空间中常量ID与用户常量ID存在重名",
//							new Object[]{errorNoItem,
//							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
//				}
//				return false;
//			}
//			
//			if(getConstIdSetFromDictList(context).contains(constantName)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//							DIAGNOSTIC_SOURCE,
//							0,
//							"同一命名空间中常量ID与数据字典项常量ID存在重名",
//							new Object[]{errorNoItem,
//							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
//				}
//				return false;
//			}
			
			//新增引用资源中的重复检查
//			IResStatisticProvider provider = (IResStatisticProvider)context.get(IValidateConstant.KEY_STATIC_PROVIDER);
//			int count = provider.getResourceCount(constantName, IResourceTable.VALUE_IGNORE_NAMESPACE, IJRESReferenceType.ErrNo_Const);
			
//			if(isDuplicationInReferenceScope(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
//					IJRESReferenceType.ErrNo_Const, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME, context)){
//			//重复
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//							DIAGNOSTIC_SOURCE,
//							0,
//							"常量ID与引用资源中错误号的常量ID重名，请检查引用资源包或引用工程",
//							new Object[]{errorNoItem,
//							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
//				}
//				return false;
//			}
			
			
//			if(isDuplicationInReferenceScope(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
//					IJRESReferenceType.Const, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
//				//重复
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//							DIAGNOSTIC_SOURCE,
//							0,
//							"常量ID与引用资源中用户常量的常量ID重名，请检查引用资源包或引用工程",
//							new Object[]{errorNoItem,
//							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
//				}
//				return false;
//			}
			
			
//			if(isDuplicationInReferenceScope(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME,
//					IJRESReferenceType.Dictionary_Const, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME, context)){
//				//重复
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//							DIAGNOSTIC_SOURCE,
//							0,
//							"常量ID与引用资源中数据字典的常量ID重名，请检查引用资源包或引用工程",
//							new Object[]{errorNoItem,
//							MetadataPackage.Literals.ERROR_NO_ITEM__CONSTANT_NAME}));
//				}
//				return false;
//			}
//			
//		}
//		return true;
	}

	/**
	 * Validates the message constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_message(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(errorNoItem)){
			return true;
		}
		else{
			if(StringUtils.isBlank(errorNoItem.getMessage())){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"错误信息不可为空", 
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__MESSAGE}));
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates the no constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_no(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		
		IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(errorNoItem)){
			return true;
		}
		else{
			Pattern p1 = Pattern.compile("(\\-?)\\d*");
			Matcher m1 = p1.matcher(errorNoItem.getNo());
			
			if(StringUtils.isBlank(errorNoItem.getNo())){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"错误号编号不可为空", 
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__NO}));
				}
				return false;
			}
			else if(!m1.matches()){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"错误号编号不符合数字要求", 
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__NO}));
				}
				return false;
				
			}
			else if(errorNoItem.getNo().length() > 15){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"错误号编号长度超过15", 
							new Object[]{errorNoItem,
							MetadataPackage.Literals.ERROR_NO_ITEM__NO}));
				}
				return false;
			}
			else{
				
				if (ValidateUtil.isDuplication(errorNoItem, MetadataPackage.Literals.ERROR_NO_ITEM__NO, context)) {
					if(diagnostics != null){
							diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
									DIAGNOSTIC_SOURCE, 
									0, 
									"错误编号与当前资源中的编号重复", 
									new Object[]{errorNoItem, 
									MetadataPackage.Literals.ERROR_NO_ITEM__NO}));
						}
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validates the level constraint of '<em>Error No Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateErrorNoItem_level(ErrorNoItem errorNoItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
				(createDiagnostic
						(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] { "level", getObjectLabel(errorNoItem, context) },
								new Object[] { errorNoItem },
								context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralDataConfigList(GeneralDataConfigList generalDataConfigList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(generalDataConfigList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneralDataConfigItem(GeneralDataConfigItem generalDataConfigItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(generalDataConfigItem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMenuItem(MenuItem menuItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(menuItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateMenuItem_name(menuItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateMenuItem_refID(menuItem, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateMenuItem_name(MenuItem menuItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String name = menuItem.getName();
		IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		if(StringUtils.isBlank(name)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"菜单号不能为空", 
						new Object[]{menuItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
				return false;
			}
		}
		if(name.length() > IMDConstant.NAME_MAX_LENGTH){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"菜单号长度超过" + IMDConstant.NAME_MAX_LENGTH,
						new Object[]{menuItem,
						MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
			}
			return false;
		}
		if(isDuplication(menuItem, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						String.format("菜单号%1$s与当前资源的菜单号重复",name), 
						new Object[]{menuItem, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
				return false;
			}
		}
		
//		Pair<EObject, IARESResource> pair;
//		if ( (pair = ValidateUtil.checkDuplicationInResStatisticProvider(menuItem,
//				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				IMetadataRefType.Menu,
//				MetadataPackage.Literals.NAMED_ELEMENT__NAME,
//				Collections.singleton(resource), context)) != null) {
//			
//			String errorInfo = String.format("菜单号与引用【%s】的【%s】菜单号重复",
//					getARESResourceLocationFullName(pair.second),
//					getARESResourceFullName(pair.second));
//			if (diagnostics != null) {
//				diagnostics
//						.add(new BasicDiagnostic(
//								Diagnostic.ERROR,
//								DIAGNOSTIC_SOURCE,
//								0,
//								errorInfo,
//								new Object[] {
//										menuItem,
//										MetadataPackage.Literals.NAMED_ELEMENT__NAME }));
//			}
//			return false;
//		}
		return true;
	}
	
	/**
	 * Validates the refID constraint of '<em>Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMenuItem_refID(MenuItem menuItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "refID", getObjectLabel(menuItem, context) },
						 new Object[] { menuItem },
						 context));
			}
			return false;
		}
		return true;
	}

	private boolean isDuplication(MenuItem menuItem,Map<Object, Object> context){
		String name = menuItem.getName();
		List<String> names = new ArrayList<String>();
		if(context.containsKey(MetadataPackage.Literals.NAMED_ELEMENT__NAME.getName())){
			names.addAll((List<String>)context.get(MetadataPackage.Literals.NAMED_ELEMENT__NAME.getName()));
		}else{
			MenuList list = MenuUtils.getMenuList(menuItem);
			for(MenuItem item : MenuUtils.getMenuItems(list)){
				names.add(item.getName());
			}
			context.put(MetadataPackage.Literals.NAMED_ELEMENT__NAME.getName(), names);
		}
		return names.indexOf(name) != names.lastIndexOf(name);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunctionProxy(FunctionProxy functionProxy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(functionProxy, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(functionProxy, diagnostics, context);
		if (result || diagnostics != null) result &= validateFunctionProxy_funCode(functionProxy, diagnostics, context);
		return result;
	}

	/**
	 * Validates the funCode constraint of '<em>Function Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateFunctionProxy_funCode(FunctionProxy functionProxy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String funCode = functionProxy.getFunCode();
		String title = "子交易码";
		if(MenuUtils.isStockDepartment()){
			title = "功能号";
		}
		if(StringUtils.isBlank(funCode)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						String.format("%1$s不能为空", title), 
						new Object[]{functionProxy, MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE}));
				return false;
			}
		}
		if(ValidateUtil.isDuplication(functionProxy, MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE, context)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0,
						String.format("%1$s与当前资源中的%1$s重复", title), 
						new Object[]{functionProxy, MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE}));
				return false;
			}
		}
		if(!getFuncNames(functionProxy, context).contains(funCode) && !StringUtils.equals(funCode, "-1")){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0,
						String.format("%1$s未在功能列表中定义", title), 
						new Object[]{functionProxy, MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE}));
				return false;
			}
		}
		
		return true;
	}
	
	private List<String> getFuncNames(FunctionProxy proxy, Map<Object, Object> context){
		String key = MenuUtils.getDepartment();
		if(context.containsKey(key)){
			return (List<String>) context.get(key);
		}
		IARESResource res = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		List<String> names = new ArrayList<String>();
		for(Function func : MenuUtils.getFunctions(res)){
			if(MenuUtils.isStockDepartment()){
				names.add(func.getName());
			}else{
				names.add(func.getSubTransCode());
			}
		}
		context.put(key, names);
		return names;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunction(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(function, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(function, diagnostics, context);
		if (result || diagnostics != null) result &= validateFunction_name(function, diagnostics, context);
		if (result || diagnostics != null) result &= validateFunction_subTransCode(function, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateFunction_name(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		if(MenuUtils.isStockDepartment()){
			String name = function.getName();
			if(StringUtils.isBlank(name)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0,
							"功能号不能为空", 
							new Object[]{function, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
					return false;
				}
			}
//			if(ValidateUtil.isDuplication(function, MetadataPackage.Literals.NAMED_ELEMENT__NAME, context)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//							DIAGNOSTIC_SOURCE, 
//							0,
//							"功能号和当前资源中的功能号重复", 
//							new Object[]{function, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
//					return false;
//				}
//			}
			List<String>names = new ArrayList<String>();
			if(context.containsKey(MenuUtils.getDepartment())){
				names.addAll((List<String>) context.get(MenuUtils.getDepartment()));
			}else{
				for(Function func : getFunctions(resource,(MenuList) function.eContainer())){
					names.add(func.getName());
				}
				context.put(MenuUtils.getDepartment(), names);
			}
			if(names.indexOf(name) != names.lastIndexOf(name)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0,
							"功能号重复", 
							new Object[]{function, MetadataPackage.Literals.NAMED_ELEMENT__NAME}));
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validates the subTransCode constraint of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateFunction_subTransCode(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		if(StringUtils.equals(MenuUtils.getDepartment(), MetadataCore.PRE_APPLICATION_DEPARTMENT_FINANCE)){
			String name = function.getSubTransCode();
			if(StringUtils.isBlank(name)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0,
							"子交易码不能为空", 
							new Object[]{function, MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE}));
					return false;
				}
			}
//			if(ValidateUtil.isDuplication(function, MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE, context)){
//				if(diagnostics != null){
//					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//							DIAGNOSTIC_SOURCE, 
//							0,
//							"子交易号和当前资源中的子交易号重复", 
//							new Object[]{function, MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE}));
//					return false;
//				}
//			}
			if(name.length() > IMDConstant.NAME_MAX_LENGTH){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"子交易码长度超过" + IMDConstant.NAME_MAX_LENGTH,
							new Object[]{function,
							MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE}));
				}
				return false;
			}
			
			List<String>names = new ArrayList<String>();
			if(context.containsKey(MenuUtils.getDepartment())){
				names.addAll((List<String>) context.get(MenuUtils.getDepartment()));
			}else{
				for(Function func : getFunctions(resource,(MenuList) function.eContainer())){
					names.add(func.getSubTransCode());
				}
				context.put(MenuUtils.getDepartment(), names);
			}
			if(names.indexOf(name) != names.lastIndexOf(name)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0,
							"子交易码重复", 
							new Object[]{function, MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE}));
					return false;
				}
			}
		}
		return true;
	}
	
	List<Function> getFunctions(IARESResource res,MenuList list){
		List<Function> funcs = new ArrayList<Function>();
		IARESProject project = res.getARESProject();
		for(IARESBundle bundle : project.getRequiredBundles()){
			try {
				IARESResource resources[] = bundle.getResources(new String[]{"menu"});
				for(IARESResource resource : resources){
					MenuList menuList = resource.getInfo(MenuList.class);
					funcs.addAll(menuList.getFunctions());
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		funcs.addAll(list.getFunctions());
		return funcs;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMenuList(MenuList menuList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(menuList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIDRangeItem(IDRangeItem idRangeItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(idRangeItem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIDRange(IDRange idRange, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(idRange, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBizPropertyConfigList(BizPropertyConfigList bizPropertyConfigList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(bizPropertyConfigList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBizPropertyConfig(BizPropertyConfig bizPropertyConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(bizPropertyConfig, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIDRangeList(IDRangeList idRangeList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(idRangeList, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //MetadataValidator
