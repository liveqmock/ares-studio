/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.*;
import com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseValidator;
import com.hundsun.ares.studio.jres.model.metadata.util.IDRangeUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage
 * @generated
 */
public class OracleValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final OracleValidator INSTANCE = new OracleValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.model.database.oracle";

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
	public OracleValidator() {
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
	  return OraclePackage.eINSTANCE;
	}
	
	/**
	 * 保存了资源存在性检查结果前缀
	 */
	private static final String KEY_RESOURCE_EXIST_CHECK_RESULT_PREFIX = "KEY_RESOURCE_EXIST_CHECK_RESULT_PREFIX$";
	
	/**
	 * 保存了用户是否启用结果
	 */
	private static final String KEY_USER_ENABLE_CHECK_RESULT_PREFIX = "KEY_USER_ENABLE_CHECK_RESULT_PREFIX$";
	
	/**
	 * 资源检查的结果状态
	 */
	private static enum RESULT_REFID_CHECK {
		/**
		 * 资源存在
		 */
		OK,
		
		/**
		 * 资源不存在
		 */
		ERROR_NOT_EXIST,
		
		/**
		 * 资源重复，不能直接引用
		 */
		ERROR_DUPLICATION
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
			case OraclePackage.ORACLE_TABLE_PROPERTY:
				return validateOracleTableProperty((OracleTableProperty)value, diagnostics, context);
			case OraclePackage.ORACLE_INDEX_PROPERTY:
				return validateOracleIndexProperty((OracleIndexProperty)value, diagnostics, context);
			case OraclePackage.ORACLE_VIEW_PROPERTY:
				return validateOracleViewProperty((OracleViewProperty)value, diagnostics, context);
			case OraclePackage.ORACLE_MODULE_PROPERTY:
				return validateOracleModuleProperty((OracleModuleProperty)value, diagnostics, context);
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA:
				return validateOracleSpaceResourceData((OracleSpaceResourceData)value, diagnostics, context);
			case OraclePackage.TABLE_SPACE_RELATION:
				return validateTableSpaceRelation((TableSpaceRelation)value, diagnostics, context);
			case OraclePackage.TABLE_SPACE:
				return validateTableSpace((TableSpace)value, diagnostics, context);
			case OraclePackage.ORACLE_USER_RESOURCE_DATA:
				return validateOracleUserResourceData((OracleUserResourceData)value, diagnostics, context);
			case OraclePackage.ORACLE_USER:
				return validateOracleUser((OracleUser)value, diagnostics, context);
			case OraclePackage.ORACLE_PRIVILEGE:
				return validateOraclePrivilege((OraclePrivilege)value, diagnostics, context);
			case OraclePackage.TRIGGER_RESOURCE_DATA:
				return validateTriggerResourceData((TriggerResourceData)value, diagnostics, context);
			case OraclePackage.SEQUENCE_RESOURCE_DATA:
				return validateSequenceResourceData((SequenceResourceData)value, diagnostics, context);
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY:
				return validateDatabaseModuleExtensibleProperty((DatabaseModuleExtensibleProperty)value, diagnostics, context);
			case OraclePackage.ORACLE_SEQUENCE_PROPERTY:
				return validateOracleSequenceProperty((OracleSequenceProperty)value, diagnostics, context);
			case OraclePackage.TABLE_TYPE:
				return validatetable_type((table_type)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleTableProperty(OracleTableProperty oracleTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleTableProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleTableProperty_space(oracleTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleTableProperty_indexSpace(oracleTableProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the space constraint of '<em>Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOracleTableProperty_space(OracleTableProperty oracleTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String space = oracleTableProperty.getSpace();
		if(StringUtils.isNotBlank(space)){
			switch (checkReferenceId(space, IOracleRefType.Space, context)) {
			case ERROR_NOT_EXIST:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"引用的表空间["+space+"] 不存在", 
							new Object[]{oracleTableProperty,
							OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE}));
				}
				return false;
			case ERROR_DUPLICATION:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"引用的表空间["+space+"] 存在重复，不能直接引用", 
							new Object[]{oracleTableProperty,
							OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE}));
				}
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Validates the indexSpace constraint of '<em>Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleTableProperty_indexSpace(OracleTableProperty oracleTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "indexSpace", getObjectLabel(oracleTableProperty, context) },
						 new Object[] { oracleTableProperty },
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
	public boolean validateOracleIndexProperty(OracleIndexProperty oracleIndexProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleIndexProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleIndexProperty_reverse(oracleIndexProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the reverse constraint of '<em>Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOracleIndexProperty_reverse(OracleIndexProperty oracleIndexProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
//		if(diagnostics != null){
//			diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
//					DIAGNOSTIC_SOURCE, 
//					0, 
//					"测试错误", 
//					new Object[]{oracleIndexProperty, OraclePackage.Literals.ORACLE_INDEX_PROPERTY__REVERSE}));
//			return false;
//		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleViewProperty(OracleViewProperty oracleViewProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleViewProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleViewProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleViewProperty_space(oracleViewProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the space constraint of '<em>View Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleViewProperty_space(OracleViewProperty oracleViewProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "space", getObjectLabel(oracleViewProperty, context) },
						 new Object[] { oracleViewProperty },
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
	public boolean validateOracleModuleProperty(OracleModuleProperty oracleModuleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleModuleProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleModuleProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleModuleProperty_space(oracleModuleProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the space constraint of '<em>Module Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleModuleProperty_space(OracleModuleProperty oracleModuleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "space", getObjectLabel(oracleModuleProperty, context) },
						 new Object[] { oracleModuleProperty },
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
	public boolean validateOracleSpaceResourceData(OracleSpaceResourceData oracleSpaceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(oracleSpaceResourceData, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceRelation(TableSpaceRelation tableSpaceRelation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableSpaceRelation, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelation_mainSpace(tableSpaceRelation, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelation_indexSpace(tableSpaceRelation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the mainSpace constraint of '<em>Table Space Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableSpaceRelation_mainSpace(TableSpaceRelation tableSpaceRelation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		String mainSpace = tableSpaceRelation.getMainSpace();
		if(StringUtils.isNotBlank(mainSpace)){
			List<String>tableSpaceNames = getTableSpaceNames(tableSpaceRelation, context);
			if(!tableSpaceNames.contains(mainSpace)){
				if (diagnostics != null) {
					diagnostics.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"主表空间不存在",
							new Object[] {
									tableSpaceRelation,
									OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE }));
				}
				return false;
			}
		}
		// 进行重名检查
		if (isDuplication(tableSpaceRelation, OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"主表空间名重复", 
						new Object[]{tableSpaceRelation, OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE}));
			}
		}
		return true;
	}
	private List<String> getTableSpaceNames(TableSpaceRelation tableSpaceRelation,Map<Object, Object> context){
		String TABLE_SPACE_KEY = "oracletablespace";
		List<String> spaces = new ArrayList<String>();
		if(context.containsKey(TABLE_SPACE_KEY)){
			spaces = (List<String>) context.get(TABLE_SPACE_KEY);
		}
		else{
			OracleSpaceResourceData resourceData = (OracleSpaceResourceData) tableSpaceRelation.eContainer();
			for(TableSpace tableSpace : resourceData.getSpaces()){
				spaces.add(tableSpace.getName());
			}
			context.put(TABLE_SPACE_KEY, spaces);
		}
		return spaces ;
		
	}

	/**
	 * Validates the indexSpace constraint of '<em>Table Space Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableSpaceRelation_indexSpace(TableSpaceRelation tableSpaceRelation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		String indexSpace = tableSpaceRelation.getIndexSpace();
		if(StringUtils.isNotBlank(indexSpace)){
			List<String>tableSpaceNames = getTableSpaceNames(tableSpaceRelation, context);
			if(!tableSpaceNames.contains(indexSpace)){
				if (diagnostics != null) {
					diagnostics.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"索引表空间不存在",
							new Object[] {
									tableSpaceRelation,
									OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE }));
				}
				return false;
			}
		}
		// 进行重名检查
		if (!StringUtils.isBlank(tableSpaceRelation.getIndexSpace()) && isDuplication(tableSpaceRelation, OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"索引表空间名重复", 
						new Object[]{tableSpaceRelation, OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE}));
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
	public boolean validateTableSpace(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableSpace, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpace_name(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpace_user(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpace_file(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpace_size(tableSpace, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpace_logicName(tableSpace, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableSpace_name(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		String tableSpaceName = tableSpace.getName();
		
		Pattern p1 = Pattern.compile("[A-Z_0-9]*");
		Matcher m1 = p1.matcher(tableSpaceName);
		
		if(StringUtils.isBlank(tableSpaceName)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"表空间名不能为空",
								new Object[] {
										tableSpace,
										OraclePackage.Literals.TABLE_SPACE__NAME }));
			}
			return false;
			
		}else if(!m1.matches()){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"表空间名不符合A-Z_0-9的数据要求",
								new Object[] {
										tableSpace,
										OraclePackage.Literals.TABLE_SPACE__NAME }));
			}
			return false;
			
		}else if(tableSpaceName.length() > 30){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"表空间名字符数大于30",
								new Object[] {
										tableSpace,
										OraclePackage.Literals.TABLE_SPACE__NAME }));
			}
			return false;
			
		}
		// 进行重名检查
		if (isDuplication(tableSpace, OraclePackage.Literals.TABLE_SPACE__NAME, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"表空间名重复", 
						new Object[]{tableSpace, OraclePackage.Literals.TABLE_SPACE__NAME}));
			}
			return false;
		}
		return true;
	}

	/**
	 * 检查指定对象根据指定的字段是否在所在容器中重名
	 * 
	 * @param object 必须在容器中
	 * @param idFeature 用于检查重名的字段
	 * @param context
	 * @return
	 */
	protected boolean isDuplication(EObject object, EStructuralFeature idFeature, Map<Object, Object> context) {
		
		Assert.isNotNull(object);
		Assert.isNotNull(object.eContainer());
		
		EObject owner = object.eContainer();
		EReference reference = object.eContainmentFeature();
		if (!reference.isMany()) {
			// 只有一个对象不可能重名
			return false;
		}
		Object key = new Pair<EObject, Pair<EReference, EStructuralFeature>>(owner, new Pair<EReference, EStructuralFeature>(reference, idFeature)) ;
		
		// 缓存的内容是检查出有错误的条目
		Set<EObject> errors = (Set<EObject>) context.get(key);
		if (errors == null) {
			// 没有进行过重名检查，需要进行检查
			errors = new HashSet<EObject>();
			
			Map<Object, EObject> map = new HashMap<Object, EObject>();
			
			for (EObject child : (List<EObject>)owner.eGet(reference)) {
				Object id = child.eGet(idFeature);
				if (id == null) {
					continue;
				}
				
				EObject find = map.get(id);
				if (find != null) {
					// 能够找到则说明存在重名
					errors.add(child);
					errors.add(find);
				} else {
					// 如果没有找到重名的，则添加自己到列表中，用于检查剩下的是否存在重名
					map.put(id, child);
				}
			}
			
			context.put(key, errors);
		}
		
		return errors.contains(object);
	}
	
	/**
	 * Validates the user constraint of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableSpace_user(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		String user = tableSpace.getUser();
		if (StringUtils.isNotBlank(user)) {
			switch (checkReferenceId(user, IOracleRefType.User, context)) {
			case ERROR_NOT_EXIST:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"数据库用户不存在", 
							new Object[]{tableSpace,
							OraclePackage.Literals.TABLE_SPACE__USER}));
				}
				return false;
			case ERROR_DUPLICATION:
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"数据库用户名存在重复，不能直接引用", 
							new Object[]{tableSpace,
							OraclePackage.Literals.TABLE_SPACE__USER}));
				}
				return false;
			}
			//数据库用户未启用
			if(!checkUserEnable(user, context)){
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
							DIAGNOSTIC_SOURCE, 
							0, 
							"数据库用户名未启用", 
							new Object[]{tableSpace,
							OraclePackage.Literals.TABLE_SPACE__USER}));
				}
				return false;
			}
		}
		
		return true;
	}

	private boolean checkUserEnable(String user, Map<Object, Object> context){
		Map<String, Boolean> checkResult = (Map<String, Boolean>) context.get(KEY_USER_ENABLE_CHECK_RESULT_PREFIX);
		if (checkResult == null) {
			context.put(KEY_USER_ENABLE_CHECK_RESULT_PREFIX, checkResult = new HashMap<String, Boolean>());
		}
		Boolean result = checkResult.get(user);
		if (result != null) {
			return result;
		}
		IARESProject project = (IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IOracleRefType.User, user, true);
		OracleUser oracleUser = (OracleUser) info.getObject();
		result = oracleUser.isEnable();
		checkResult.put(user, result);
		return result;
	}
	
	/**
	 * Validates the file constraint of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpace_file(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "file", getObjectLabel(tableSpace, context) },
						 new Object[] { tableSpace },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the size constraint of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpace_size(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "size", getObjectLabel(tableSpace, context) },
						 new Object[] { tableSpace },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the logicName constraint of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpace_logicName(TableSpace tableSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "logicName", getObjectLabel(tableSpace, context) },
						 new Object[] { tableSpace },
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
	public boolean validateOracleUserResourceData(OracleUserResourceData oracleUserResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(oracleUserResourceData, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleUser(OracleUser oracleUser, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleUser, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleUser_name(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleUser_attributes(oracleUser, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleUser_password(oracleUser, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOracleUser_name(OracleUser oracleUser, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 不能为空、a-z_0-9、不超过30
		
		String oracleUserName = oracleUser.getName();
		if(StringUtils.isBlank(oracleUserName)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"Oracle用户名不可为空",
								new Object[] {
										oracleUser,
										OraclePackage.Literals.ORACLE_USER__NAME }));
			}
			return false;
			
		}
		
		
		Pattern p1 = Pattern.compile("[a-z_0-9]*");
		Matcher m1 = p1.matcher(oracleUserName);
		
		if(!m1.matches()){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"Oracle用户名不符合a-z_0-9的数据要求",
								new Object[] {
										oracleUser,
										OraclePackage.Literals.ORACLE_USER__NAME }));
			}
			return false;
			
		}
		
		if(oracleUserName.length() > 30){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"Oracle用户名长度不超过30字符",
								new Object[] {
										oracleUser,
										OraclePackage.Literals.ORACLE_USER__NAME }));
			}
			return false;
			
		}
		// 进行重名检查
		if (isDuplication(oracleUser, OraclePackage.Literals.ORACLE_USER__NAME, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"Oracle用户名重复", 
						new Object[]{oracleUser, OraclePackage.Literals.ORACLE_USER__NAME}));
			}
		}
		return true;
	}

	/**
	 * Validates the attributes constraint of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOracleUser_attributes(OracleUser oracleUser, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 文件属性不可为空
		String attributes = oracleUser.getAttributes();
		
		if(StringUtils.isBlank(attributes)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"文件属性不可为空",
								new Object[] {
										oracleUser,
										OraclePackage.Literals.ORACLE_USER__ATTRIBUTES }));
			}
			return false;
		}
		return true;
	}

	/**
	 * 密码属性不可为空
	 * Validates the password constraint of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOracleUser_password(OracleUser oracleUser, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String password = oracleUser.getPassword();
		
		if(StringUtils.isBlank(password)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"密码属性不可为空,用户:["+oracleUser.getName()+"]",
								new Object[] {
										oracleUser,
										OraclePackage.Literals.ORACLE_USER__PASSWORD }));
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
	public boolean validateOraclePrivilege(OraclePrivilege oraclePrivilege, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oraclePrivilege, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validateOraclePrivilege_name(oraclePrivilege, diagnostics, context);
		if (result || diagnostics != null) result &= validateOraclePrivilege_type(oraclePrivilege, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateOraclePrivilege_name(OraclePrivilege oraclePrivilege, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 权限名称不可为空
		
		String privilegeName = oraclePrivilege.getName();
		if(StringUtils.isBlank(privilegeName)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"权限名称不可为空",
								new Object[] {
										oraclePrivilege,
										OraclePackage.Literals.ORACLE_PRIVILEGE__NAME }));
			}
			return false;
		}
		// 进行重名检查
		if (isDuplication(oraclePrivilege, OraclePackage.Literals.ORACLE_PRIVILEGE__NAME, context)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"Oracle用户权限名重复", 
						new Object[]{oraclePrivilege, OraclePackage.Literals.ORACLE_PRIVILEGE__NAME}));
			}
		}
		return true;
	}

	/**
	 * Validates the type constraint of '<em>Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOraclePrivilege_type(OraclePrivilege oraclePrivilege, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "type", getObjectLabel(oraclePrivilege, context) },
						 new Object[] { oraclePrivilege },
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
	public boolean validateTriggerResourceData(TriggerResourceData triggerResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(triggerResourceData, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateTriggerResourceData_sql(triggerResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateTriggerResourceData_name(triggerResourceData, diagnostics, context);
		return result;
	}

	/**
	 * Validates the sql constraint of '<em>Trigger Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTriggerResourceData_sql(TriggerResourceData triggerResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 触发器SQL语句不能为空
		
		String triggerSQL = triggerResourceData.getSql();
		
		if(StringUtils.isBlank(triggerSQL)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"触发器语句不能为空",
								new Object[] {
										triggerResourceData,
										OraclePackage.Literals.TRIGGER_RESOURCE_DATA__SQL }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the name constraint of '<em>Trigger Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTriggerResourceData_name(TriggerResourceData triggerResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 触发器名称不能为空，名称符合A-Za-z_0－9的数据要求

		String triggerName = triggerResourceData.getName();
		
		if(StringUtils.isBlank(triggerName)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"触发器名称不能为空",
								new Object[] {
										triggerResourceData,
										CorePackage.Literals.BASIC_RESOURCE_INFO__NAME }));
			}
			return false;
		}
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(triggerName);
		if(!m1.matches()){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
						DIAGNOSTIC_SOURCE, 
						0, 
						"触发器名称不符合A-Za-z_0-9的数据要求", 
						new Object[]{triggerResourceData,
						CorePackage.Literals.BASIC_RESOURCE_INFO__NAME}));
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
	public boolean validateSequenceResourceData(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(sequenceResourceData, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_name(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_objectId(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_start(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_increment(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_minValue(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_maxValue(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_cycle(sequenceResourceData, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequenceResourceData_cache(sequenceResourceData, diagnostics, context);
		return result;
	}

	/**
	 * Validates the name constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_name(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {

		// TODO 视图名不能为空，A-Za-z_0-9数据要求，不能重名
		
		String enName = sequenceResourceData.getName();
		
		String tableSpace = DatabaseValidator.getTableSpace(sequenceResourceData);
		
		if(StringUtils.isBlank(enName)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"序列名不能为空",
								new Object[] {
										sequenceResourceData,
										CorePackage.Literals.BASIC_RESOURCE_INFO__NAME}));
			}
			return false;
		}
		
		Pattern p1 = Pattern.compile("[A-Za-z_0-9]*");
		Matcher m1 = p1.matcher(enName);
		if(!m1.matches()){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"序列名不符合A-Za-z_0-9的要求:["+enName+"]",
								new Object[] {
										sequenceResourceData,
										CorePackage.Literals.BASIC_RESOURCE_INFO__NAME}));
			}
			return false;
		}

		if(enName.length()>50){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"序列名长度超过50字符:["+enName+"]",
								new Object[] {
										sequenceResourceData,
										CorePackage.Literals.BASIC_RESOURCE_INFO__NAME}));
			}
			return false;
		}
		List<String> seqames = new ArrayList<String>();
		IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Sequence, true);
		for(ReferenceInfo referenceInfo : infoList){
			IARESResource res = referenceInfo.getResource();
			SequenceResourceData seq;
			try {
				seq = res.getInfo(SequenceResourceData.class);
				if (seq == null) {
					continue;
				}
				//表空间
				String space = DatabaseValidator.getTableSpace(seq);
				String sn = seq.getName();
				if (StringUtils.isNotBlank(seq.getTableName())) {
					sn += "_"+seq.getTableName();
				}
				seqames.add(sn + space);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		String tempEnname = enName;
		if (StringUtils.isNotBlank(sequenceResourceData.getTableName())) {
			tempEnname += "_"+sequenceResourceData.getTableName();
		}
		if(seqames.indexOf(tempEnname+tableSpace) != seqames.lastIndexOf(tempEnname+tableSpace)){
			IARESResource res = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
			if (res != null) {
				try {
					IARESResource[] reses = res.getARESProject().getResources(enName+"."+IDatabaseResType.Sequence);
					StringBuffer sb = new StringBuffer();
					sb.append("序列名重复：");
					for(IARESResource resTemp : reses){
						DatabaseResourceData trd = resTemp.getInfo(DatabaseResourceData.class);
						sb.append(ResourcesUtil.getChineseFileName("/", resTemp.getModule())+"/"+trd.getChineseName()+"["+enName+"] ,");
					}
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, 
								DIAGNOSTIC_SOURCE, 
								0, 
								sb.substring(0, sb.length()-1), 
								new Object[]{sequenceResourceData, CorePackage.Literals.BASIC_RESOURCE_INFO__NAME}));
						return false;
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * Validates the objectId constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_objectId(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String objID = sequenceResourceData.getObjectId();
		if (StringUtils.isNotBlank(objID)) {
			IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			try {
				int value = Integer.parseInt(objID);
				IARESResource resource = (IARESResource)context.get(IValidateConstant.KEY_RESOUCE);
				String key = IDBConstant.SEQUENCE_ID_RANGE_KEY;
				String range = IDRangeUtil.getIDRange(resource,key);
				if(!IDRangeUtil.isInRange(value, range)){
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								String.format("对象号不在%s范围内",range), 
								new Object[]{sequenceResourceData,
								CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
					}
					return false;
				}
				
			} catch (Exception e) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"对象号不是数字", 
							new Object[]{sequenceResourceData,
							CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
				}
				return false;
			}
			List<ReferenceInfo> referInfos = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Sequence, false);
			for(ReferenceInfo info : referInfos){
				Object obj = info.getObject();
				if (obj instanceof SequenceResourceData) {
					if (!StringUtils.equals(sequenceResourceData.getFullyQualifiedName(), ((SequenceResourceData) obj).getFullyQualifiedName()) &&
							StringUtils.equals(((SequenceResourceData) obj).getObjectId(), sequenceResourceData.getObjectId())) {
						if(diagnostics != null){
							StringBuffer sb = new StringBuffer();
							sb.append("对象号重复：");
							sb.append(ResourcesUtil.getChineseFileName("/", info.getResource().getModule())+"/"+((SequenceResourceData) obj).getChineseName()+"["+sequenceResourceData.getName()+"] ,");
							diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									sb.substring(0, sb.length()-1), 
									new Object[]{sequenceResourceData,
									CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID}));
						}
						return false;
					}
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	protected RESULT_REFID_CHECK checkReferenceId(String refId, String refType, Map<Object, Object> context) {

		String key = KEY_RESOURCE_EXIST_CHECK_RESULT_PREFIX + refType;
		Map<String, RESULT_REFID_CHECK> checkResult = (Map<String, RESULT_REFID_CHECK>) context.get(key);
		if (checkResult == null) {
			context.put(key, checkResult = new HashMap<String, RESULT_REFID_CHECK>());
		}
		
		RESULT_REFID_CHECK result = checkResult.get(refId);
		if (result != null) {
			return result;
		}
		
		String name = refId;
		String namespace = IResourceTable.Scope_IGNORE_NAMESPACE;
		int count = 0;
		IARESProject project = (IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
		List<ReferenceInfo>	infoList = ReferenceManager.getInstance().getReferenceInfos(project, refType, name, true);
        if(infoList==null){
        	count =  0;
        }
        count = infoList.size();

		if (count == 0) {
			result = RESULT_REFID_CHECK.ERROR_NOT_EXIST;
		} else if (count == 1) {
			result = RESULT_REFID_CHECK.OK;
		} else {
			result = RESULT_REFID_CHECK.ERROR_DUPLICATION;
		}

		// 进行缓存
		checkResult.put(refId, result);
		
		return result;
	}
	
	/**
	 * 检查输入数据是否为整数的检查方法
	 */
	public boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * Validates the start constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_start(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 起始值必须为整数
		String startNum = sequenceResourceData.getStart();
		
		if(StringUtils.isBlank(startNum)){
			return true;
		}else if(!isInteger(startNum) || Integer.parseInt(startNum) <= 0){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"起始值为正整数",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__START }));
			}
			return false;
		}else if(!validateSequenceResourceData_min2start(sequenceResourceData)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"起始值必须大于等于最小值",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__START }));
			}
			return false;
		}else if(!validateSequenceResourceData_max2start(sequenceResourceData)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"起始值必须小于等于最大值",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__START }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the increment constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_increment(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 步长必须为不为0的整数
		
		String inCrement = sequenceResourceData.getIncrement();
		if(StringUtils.isBlank(inCrement)){
			return true;
		}else if(!isInteger(inCrement)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING,
						DIAGNOSTIC_SOURCE,
						0,
						"步长为非整数",
						new Object[]{sequenceResourceData,
						OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__INCREMENT}));
			}
			return false;
		}else if(0 == Integer.parseInt(inCrement)){
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING,
						DIAGNOSTIC_SOURCE,
						0,
						"步长不能为0",
						new Object[]{sequenceResourceData,
						OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__INCREMENT}));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the minValue constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_minValue(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 最小值为不大于最大值的整数
		
		String minValue = sequenceResourceData.getMinValue();
		
		if(StringUtils.isBlank(minValue)){
			return true;
		}else if (!isInteger(minValue) || Integer.parseInt(minValue) <= 0) {
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"最小值为正整数",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MIN_VALUE }));
			}
			return false;
		}else if (!validateSequenceResourceData_min2max(sequenceResourceData)) {
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"最小值不能大于最大值",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MIN_VALUE }));
			}
			return false;

		}
		return true;
	}
	
	public boolean validateSequenceResourceData_min2start (SequenceResourceData sequenceResourceData){
		String minValue = sequenceResourceData.getMinValue();
		String start = sequenceResourceData.getStart();
		if (isInteger(minValue) && isInteger(start)) {
			if (Integer.parseInt(start) - Integer.parseInt(minValue) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validateSequenceResourceData_max2start (SequenceResourceData sequenceResourceData){
		String maxValue = sequenceResourceData.getMaxValue();
		String start = sequenceResourceData.getStart();
		if (isInteger(maxValue) && isInteger(start)) {
			if (Integer.parseInt(maxValue) - Integer.parseInt(start) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 最大值和最小值的比较方法
	 * @param sequenceResourceData
	 * @return
	 */
	public boolean validateSequenceResourceData_min2max(SequenceResourceData sequenceResourceData) {
		// TODO 最小值和最大值进行比较
		String minValue = sequenceResourceData.getMinValue();
		String maxValue = sequenceResourceData.getMaxValue();
		String increment = sequenceResourceData.getIncrement();
		
		if(isInteger(maxValue) && isInteger(minValue) && isInteger(increment)){
			
			int minVal = Integer.parseInt(minValue);
			int maxVal = Integer.parseInt(maxValue);
			int incVal = Integer.parseInt(increment);
			
			//判断步长是否为负数
			if(0 > incVal){
				int val1 = -1 * minVal;
				int val2 = -1 * maxVal;
				if(val1 > val2){
					return false;
				}else{
					return true;
				}
			}else if(0 == incVal){
				return true;
			}else{
				if(minVal >= maxVal){
					return false;
				}else{
					return true;
				}
			}
		}
		if(!isInteger(increment)&&isInteger(maxValue)&&isInteger(minValue)){
			int minVal = Integer.parseInt(minValue);
			int maxVal = Integer.parseInt(maxValue);
			if(minVal >= maxVal){
				return false;
			}else{
				return true;
			}
			
		}
		return true;
	}
	

	/**
	 * Validates the maxValue constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_maxValue(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 最大值是否为整数、最大值不小于最小值
		String maxValue = sequenceResourceData.getMaxValue();
		if(StringUtils.isBlank(maxValue)){
			return true;
		}
		else if(!isInteger(maxValue) || Integer.parseInt(maxValue) <= 0){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"最大值为正整数",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MAX_VALUE }));
			}
			return false;
		}else if(!validateSequenceResourceData_min2max(sequenceResourceData)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"最大值不能小于最小值",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MAX_VALUE }));
			}
			return false;
		}
		return true;
		
	}

	/**
	 * Validates the cycle constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSequenceResourceData_cycle(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "cycle", getObjectLabel(sequenceResourceData, context) },
						 new Object[] { sequenceResourceData },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the cache constraint of '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSequenceResourceData_cache(SequenceResourceData sequenceResourceData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO 缓存为不小于0的整数
		String dataCache = sequenceResourceData.getCache();
		if(StringUtils.isBlank(dataCache)){
			return true;
		}else if (!isInteger(dataCache)) {
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"缓存为不小于0的整数",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CACHE }));
			}
			return false;
		}else if(0 > Integer.parseInt(dataCache)){
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.WARNING,
								DIAGNOSTIC_SOURCE,
								0,
								"缓存为不小于0的整数",
								new Object[] {
										sequenceResourceData,
										OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CACHE }));
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
	public boolean validateDatabaseModuleExtensibleProperty(DatabaseModuleExtensibleProperty databaseModuleExtensibleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(databaseModuleExtensibleProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleSequenceProperty(OracleSequenceProperty oracleSequenceProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oracleSequenceProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(oracleSequenceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateOracleSequenceProperty_space(oracleSequenceProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the space constraint of '<em>Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOracleSequenceProperty_space(OracleSequenceProperty oracleSequenceProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "space", getObjectLabel(oracleSequenceProperty, context) },
						 new Object[] { oracleSequenceProperty },
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
	public boolean validatetable_type(table_type table_type, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //OracleValidator
