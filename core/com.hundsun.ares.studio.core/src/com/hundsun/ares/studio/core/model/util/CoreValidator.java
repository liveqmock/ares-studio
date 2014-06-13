/**
 */
package com.hundsun.ares.studio.core.model.util;

import com.hundsun.ares.studio.core.model.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.ExtensibleModelAttribute;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList;
import com.hundsun.ares.studio.core.model.ProjectExtensibleModel;
import com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.ReferenceWithNamespace;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.core.model.CorePackage
 * @generated
 */
public class CoreValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CoreValidator INSTANCE = new CoreValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.core.model";

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
	public CoreValidator() {
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
	  return CorePackage.eINSTANCE;
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
			case CorePackage.JRES_RESOURCE_INFO:
				return validateJRESResourceInfo((JRESResourceInfo)value, diagnostics, context);
			case CorePackage.BASIC_RESOURCE_INFO:
				return validateBasicResourceInfo((BasicResourceInfo)value, diagnostics, context);
			case CorePackage.REVISION_HISTORY:
				return validateRevisionHistory((RevisionHistory)value, diagnostics, context);
			case CorePackage.EXTENSIBLE_MODEL:
				return validateExtensibleModel((ExtensibleModel)value, diagnostics, context);
			case CorePackage.ESTRING_TO_EOBJECT_MAP_ENTRY:
				return validateEStringToEObjectMapEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case CorePackage.REFERENCE:
				return validateReference((Reference)value, diagnostics, context);
			case CorePackage.REFERENCE_WITH_NAMESPACE:
				return validateReferenceWithNamespace((ReferenceWithNamespace)value, diagnostics, context);
			case CorePackage.IREFERENCE_PROVIDER:
				return validateIReferenceProvider((IReferenceProvider)value, diagnostics, context);
			case CorePackage.IJSON_DATA:
				return validateIJSONData((IJSONData)value, diagnostics, context);
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY:
				return validateExtensibleModelConfigProperty((ExtensibleModelConfigProperty)value, diagnostics, context);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE:
				return validateExtensibleModelAttribute((ExtensibleModelAttribute)value, diagnostics, context);
			case CorePackage.USER_EXTENSIBLE_PROPERTY:
				return validateUserExtensibleProperty((UserExtensibleProperty)value, diagnostics, context);
			case CorePackage.MODULE_EXTENSIBLE_MODEL:
				return validateModuleExtensibleModel((ModuleExtensibleModel)value, diagnostics, context);
			case CorePackage.PROJECT_EXTENSIBLE_MODEL:
				return validateProjectExtensibleModel((ProjectExtensibleModel)value, diagnostics, context);
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY:
				return validateProjectRevisionHistoryProperty((ProjectRevisionHistoryProperty)value, diagnostics, context);
			case CorePackage.MODULE_REVISION_HISTORY_LIST:
				return validateModuleRevisionHistoryList((ModuleRevisionHistoryList)value, diagnostics, context);
			case CorePackage.DOM4J_DOCUMENT:
				return validateDom4jDocument((Document)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJRESResourceInfo(JRESResourceInfo jresResourceInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(jresResourceInfo, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBasicResourceInfo(BasicResourceInfo basicResourceInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(basicResourceInfo, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRevisionHistory(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(revisionHistory, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistory_modifiedDate(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistory_version(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistory_modified(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistory_modifiedBy(revisionHistory, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistory_orderNumber(revisionHistory, diagnostics, context);
		return result;
	}

	/**
	 * Validates the modifiedDate constraint of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRevisionHistory_modifiedDate(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "modifiedDate", getObjectLabel(revisionHistory, context) },
						 new Object[] { revisionHistory },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the version constraint of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRevisionHistory_version(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		/*
		 * TODO#错误检查#龚叶峰#简单 #王彬#已编码 #2011-08-04 #16 #20 #修订版本检查
		 *
		 *TODO#代码走查-错误检查#秦元#简单 #王彬#状态 #时间 #用时 #代码行 #“修订版本不符合定义”描述不明确,需说明修订版本具体格式
		 *
		 * 修订版本符合公司要求，为4个整数位 (\\d+\\.){3}\\d+
		 */
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		
		String modVersion = revisionHistory.getVersion();

		// TASK #5117::修订历史标准改造
		// 用户可能在输入版本的时候手动在前面添加了一个v
		if (StringUtils.startsWithIgnoreCase(modVersion, "v")) {
			modVersion = StringUtils.substring(modVersion, 1);
		}
		
		Pattern p1 = Pattern.compile("(\\d+\\.){3}\\d+");
		Matcher m1 = p1.matcher(modVersion);
		
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING,
						DIAGNOSTIC_SOURCE,
						0,
						"修订版本格式应为以“.”分割的4个整数位，例如：“1.0.0.1”",
						new Object[]{revisionHistory,
						CorePackage.Literals.REVISION_HISTORY__VERSION}));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the modified constraint of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRevisionHistory_modified(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		/*
		 * TODO#错误检查#龚叶峰#简单 #王彬#已编码 #2011-08-04 #10 #8 #修改内容检查
		 *
		 * 修改内容不能为空
		 */
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		return true;
	}

	/**
	 * Validates the modifiedBy constraint of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRevisionHistory_modifiedBy(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		/*
		 * TODO#错误检查#龚叶峰#简单 #王彬#已编码 #2011-08-04#10 #8 #修订人检查
		 *
		 * 修订人不能为空
		 */
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		return true;
	}

	/**
	 * Validates the orderNumber constraint of '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRevisionHistory_orderNumber(RevisionHistory revisionHistory, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "orderNumber", getObjectLabel(revisionHistory, context) },
						 new Object[] { revisionHistory },
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
	public boolean validateExtensibleModel(ExtensibleModel extensibleModel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(extensibleModel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEStringToEObjectMapEntry(Map.Entry<?, ?> eStringToEObjectMapEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)eStringToEObjectMapEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReference(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(reference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceWithNamespace(ReferenceWithNamespace referenceWithNamespace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(referenceWithNamespace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIReferenceProvider(IReferenceProvider iReferenceProvider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iReferenceProvider, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIJSONData(IJSONData ijsonData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ijsonData, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtensibleModelConfigProperty(ExtensibleModelConfigProperty extensibleModelConfigProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(extensibleModelConfigProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtensibleModelAttribute(ExtensibleModelAttribute extensibleModelAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(extensibleModelAttribute, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUserExtensibleProperty(UserExtensibleProperty userExtensibleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(userExtensibleProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModuleExtensibleModel(ModuleExtensibleModel moduleExtensibleModel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(moduleExtensibleModel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProjectExtensibleModel(ProjectExtensibleModel projectExtensibleModel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(projectExtensibleModel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProjectRevisionHistoryProperty(ProjectRevisionHistoryProperty projectRevisionHistoryProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(projectRevisionHistoryProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModuleRevisionHistoryList(ModuleRevisionHistoryList moduleRevisionHistoryList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(moduleRevisionHistoryList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDom4jDocument(Document dom4jDocument, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
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

} //CoreValidator
