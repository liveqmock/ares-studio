/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.util;

import com.hundsun.ares.studio.jres.model.chouse.*;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail;
import com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail;
import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.HisTableColumn;
import com.hundsun.ares.studio.jres.model.chouse.HistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.RemovedIndex;
import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty;
import com.hundsun.ares.studio.jres.model.chouse.StockTableProperty;
import com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty;
import com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty;
import com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseValidator;
import com.hundsun.ares.studio.validate.ValidateUtil;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage
 * @generated
 */
public class ChouseValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ChouseValidator INSTANCE = new ChouseValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.model.chouse";

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
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseValidator databaseValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChouseValidator() {
		super();
		databaseValidator = DatabaseValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return ChousePackage.eINSTANCE;
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
			case ChousePackage.TABLE_SPACE_PROPERTY:
				return validateTableSpaceProperty((TableSpaceProperty)value, diagnostics, context);
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY:
				return validateTableSpaceRelationProperty((TableSpaceRelationProperty)value, diagnostics, context);
			case ChousePackage.TABLE_BASE_PROPERTY:
				return validateTableBaseProperty((TableBaseProperty)value, diagnostics, context);
			case ChousePackage.HISTORY_PROPERTY:
				return validateHistoryProperty((HistoryProperty)value, diagnostics, context);
			case ChousePackage.REVISION_HISTORY_PROPERTY:
				return validateRevisionHistoryProperty((RevisionHistoryProperty)value, diagnostics, context);
			case ChousePackage.MODIFICATION:
				return validateModification((Modification)value, diagnostics, context);
			case ChousePackage.STOCK_TABLE_PROPERTY:
				return validateStockTableProperty((StockTableProperty)value, diagnostics, context);
			case ChousePackage.STOCK_COLUMN_PROPERTY:
				return validateStockColumnProperty((StockColumnProperty)value, diagnostics, context);
			case ChousePackage.STOCK_INDEX_PROPERTY:
				return validateStockIndexProperty((StockIndexProperty)value, diagnostics, context);
			case ChousePackage.STOCK_TS_RELATION_PROPERTY:
				return validateStockTSRelationProperty((StockTSRelationProperty)value, diagnostics, context);
			case ChousePackage.STOCK_PROJECT_PROPERTY:
				return validateStockProjectProperty((StockProjectProperty)value, diagnostics, context);
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY:
				return validateStockDBContextProperty((StockDBContextProperty)value, diagnostics, context);
			case ChousePackage.ADD_TABLE_MODIFICATION:
				return validateAddTableModification((AddTableModification)value, diagnostics, context);
			case ChousePackage.HIS_TABLE_COLUMN:
				return validateHisTableColumn((HisTableColumn)value, diagnostics, context);
			case ChousePackage.ADD_TABLE_COLUMN_MODIFICATION:
				return validateAddTableColumnModification((AddTableColumnModification)value, diagnostics, context);
			case ChousePackage.REMOVE_TABLE_COLUMN_MODIFICATION:
				return validateRemoveTableColumnModification((RemoveTableColumnModification)value, diagnostics, context);
			case ChousePackage.REMOVED_TABLE_COLUMN:
				return validateRemovedTableColumn((RemovedTableColumn)value, diagnostics, context);
			case ChousePackage.RENAME_TABLE_COLUMN_MODIFICATION:
				return validateRenameTableColumnModification((RenameTableColumnModification)value, diagnostics, context);
			case ChousePackage.RTCM_DETAIL:
				return validateRTCMDetail((RTCMDetail)value, diagnostics, context);
			case ChousePackage.COLUMN_CHANGE_DETAIL:
				return validateColumnChangeDetail((ColumnChangeDetail)value, diagnostics, context);
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION:
				return validateChangeTableColumnTypeModification((ChangeTableColumnTypeModification)value, diagnostics, context);
			case ChousePackage.CTCTM_DETAIL:
				return validateCTCTMDetail((CTCTMDetail)value, diagnostics, context);
			case ChousePackage.ADD_INDEX_MODIFICATION:
				return validateAddIndexModification((AddIndexModification)value, diagnostics, context);
			case ChousePackage.REMOVE_INDEX_MODIFICATION:
				return validateRemoveIndexModification((RemoveIndexModification)value, diagnostics, context);
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION:
				return validateAddIndexFieldModification((AddIndexFieldModification)value, diagnostics, context);
			case ChousePackage.REMOVE_INDEX_FIELD_MODIFICATION:
				return validateRemoveIndexFieldModification((RemoveIndexFieldModification)value, diagnostics, context);
			case ChousePackage.ADD_INDEX_FIELD:
				return validateAddIndexField((AddIndexField)value, diagnostics, context);
			case ChousePackage.REMOVE_INDEX_FIELD:
				return validateRemoveIndexField((RemoveIndexField)value, diagnostics, context);
			case ChousePackage.REMOVED_INDEX:
				return validateRemovedIndex((RemovedIndex)value, diagnostics, context);
			case ChousePackage.CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION:
				return validateChangeTableColumnPrimaryKeyModifycation((ChangeTableColumnPrimaryKeyModifycation)value, diagnostics, context);
			case ChousePackage.CTCPM_DETAIL:
				return validateCTCPMDetail((CTCPMDetail)value, diagnostics, context);
			case ChousePackage.CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION:
				return validateChangeTableColumnUniqueModifycation((ChangeTableColumnUniqueModifycation)value, diagnostics, context);
			case ChousePackage.CTCUM_DETAIL:
				return validateCTCUMDetail((CTCUMDetail)value, diagnostics, context);
			case ChousePackage.MODIFY_DETAIL:
				return validateModifyDetail((ModifyDetail)value, diagnostics, context);
			case ChousePackage.CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION:
				return validateChangeTableColumnNullableModifycation((ChangeTableColumnNullableModifycation)value, diagnostics, context);
			case ChousePackage.CTCNM_DETAIL:
				return validateCTCNMDetail((CTCNMDetail)value, diagnostics, context);
			case ChousePackage.ADD_TABLE_COLUMN_PK_MODIFICATION:
				return validateAddTableColumnPKModification((AddTableColumnPKModification)value, diagnostics, context);
			case ChousePackage.REMOVE_TABLE_COLUMN_PK_MODIFICATION:
				return validateRemoveTableColumnPKModification((RemoveTableColumnPKModification)value, diagnostics, context);
			case ChousePackage.ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION:
				return validateAddTableColumnUniqueModifycation((AddTableColumnUniqueModifycation)value, diagnostics, context);
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION:
				return validateRemoveTableColumnUniqueModifycation((RemoveTableColumnUniqueModifycation)value, diagnostics, context);
			case ChousePackage.ADD_CONSTRAINT_MODIFICATION:
				return validateAddConstraintModification((AddConstraintModification)value, diagnostics, context);
			case ChousePackage.REMOVE_CONSTRAINT_MODIFICATION:
				return validateRemoveConstraintModification((RemoveConstraintModification)value, diagnostics, context);
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL:
				return validateConstraintModifyDetail((ConstraintModifyDetail)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceProperty(TableSpaceProperty tableSpaceProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableSpaceProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceProperty_reduTable(tableSpaceProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceProperty_chearTable(tableSpaceProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the reduTable constraint of '<em>Table Space Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceProperty_reduTable(TableSpaceProperty tableSpaceProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "reduTable", getObjectLabel(tableSpaceProperty, context) },
						 new Object[] { tableSpaceProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the chearTable constraint of '<em>Table Space Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceProperty_chearTable(TableSpaceProperty tableSpaceProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "chearTable", getObjectLabel(tableSpaceProperty, context) },
						 new Object[] { tableSpaceProperty },
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
	public boolean validateTableSpaceRelationProperty(TableSpaceRelationProperty tableSpaceRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableSpaceRelationProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelationProperty_hisSpace(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelationProperty_hisIndexSpace(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelationProperty_fileSpace(tableSpaceRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableSpaceRelationProperty_fileIndexSpace(tableSpaceRelationProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hisSpace constraint of '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceRelationProperty_hisSpace(TableSpaceRelationProperty tableSpaceRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisSpace", getObjectLabel(tableSpaceRelationProperty, context) },
						 new Object[] { tableSpaceRelationProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hisIndexSpace constraint of '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceRelationProperty_hisIndexSpace(TableSpaceRelationProperty tableSpaceRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisIndexSpace", getObjectLabel(tableSpaceRelationProperty, context) },
						 new Object[] { tableSpaceRelationProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the fileSpace constraint of '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceRelationProperty_fileSpace(TableSpaceRelationProperty tableSpaceRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "fileSpace", getObjectLabel(tableSpaceRelationProperty, context) },
						 new Object[] { tableSpaceRelationProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the fileIndexSpace constraint of '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSpaceRelationProperty_fileIndexSpace(TableSpaceRelationProperty tableSpaceRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "fileIndexSpace", getObjectLabel(tableSpaceRelationProperty, context) },
						 new Object[] { tableSpaceRelationProperty },
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
	public boolean validateTableBaseProperty(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableBaseProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_clear(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_redu(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_history(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_historySpace(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_historyIndexSpace(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_objectID(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_splitField(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_splitNum(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_startData(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_userSplit(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_fileSpace(tableBaseProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateTableBaseProperty_fileIndexSpace(tableBaseProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the clear constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_clear(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "clear", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the redu constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_redu(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "redu", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the history constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_history(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "history", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the historySpace constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_historySpace(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "historySpace", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the historyIndexSpace constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_historyIndexSpace(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "historyIndexSpace", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the objectID constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_objectID(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "objectID", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the splitField constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableBaseProperty_splitField(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String field = tableBaseProperty.getSplitField();
		if (StringUtils.isNotBlank(field)) {
			IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
			try {
				TableResourceData table = resource.getInfo(TableResourceData.class);
				if (table != null) {
					EList<TableColumn> columns = table.getColumns();
					for (int i = 0;i < columns.size() ; i++) {
						TableColumn column = columns.get(i);
						if (StringUtils.equals(column.getName(), field)) {
							break;
						}
						if (i == columns.size() - 1) {
							if(diagnostics != null){
								diagnostics.add(new BasicDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"分区字段填写错误！分区字段应为当前表中的字段。",
										new Object[] {
												tableBaseProperty,
												ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_FIELD }));
							}
							return false;
						}
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * Validates the splitNum constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableBaseProperty_splitNum(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (StringUtils.isNotBlank(tableBaseProperty.getSplitNum())) {
			int value = -1;
			Exception e1 = null;
			try {
				value = Integer.parseInt(tableBaseProperty.getSplitNum());
			}catch (Exception e) {
				e1 = e;
			}
			if (value <= 0 || e1 != null) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"分区个数填写错误！应为正整数。",
							new Object[] {
									tableBaseProperty,
									ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_NUM }));
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates the startData constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTableBaseProperty_startData(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if(!tableBaseProperty.isUserSplit()){
			return true;
		}
		String startData = tableBaseProperty.getStartData();
		Exception e1 = null;
		if (StringUtils.isBlank(startData)) {
			return true;
		}
//		try{
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//			format.parse(startData).getTime();
//		}catch (Exception e) {
//			e1 = e;
//		}
		try {
			Integer.parseInt(startData);
		} catch (Exception e) {
			e1 = e;
		}
		if (e1 != null || (startData.length() != 6 && startData.length() != 8)) {
			if(diagnostics != null){
				diagnostics.add(new BasicDiagnostic(
						Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"开始日期格式错误！正确格式应为6位月份或8位日期格式",
						new Object[] {
								tableBaseProperty,
								ChousePackage.Literals.TABLE_BASE_PROPERTY__START_DATA }));
			}
			return false;
		}
		if(startData.length() == 6 || startData.length() == 8){
			int startMonth = Integer.parseInt(startData.substring(4,6));
			if(startMonth > 12 || startMonth <= 0) {
				if(diagnostics != null){
					diagnostics.add(new BasicDiagnostic(
							Diagnostic.ERROR,
							DIAGNOSTIC_SOURCE,
							0,
							"开始日期中月份输入错误！",
							new Object[] {
									tableBaseProperty,
									ChousePackage.Literals.TABLE_BASE_PROPERTY__START_DATA }));
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates the userSplit constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_userSplit(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "userSplit", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the fileSpace constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_fileSpace(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "fileSpace", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the fileIndexSpace constraint of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableBaseProperty_fileIndexSpace(TableBaseProperty tableBaseProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "fileIndexSpace", getObjectLabel(tableBaseProperty, context) },
						 new Object[] { tableBaseProperty },
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
	public boolean validateHistoryProperty(HistoryProperty historyProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(historyProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRevisionHistoryProperty(RevisionHistoryProperty revisionHistoryProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(revisionHistoryProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(revisionHistoryProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateRevisionHistoryProperty_action(revisionHistoryProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the action constraint of '<em>Revision History Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRevisionHistoryProperty_action(RevisionHistoryProperty revisionHistoryProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		Modification modify = revisionHistoryProperty.getAction();
//		
//		BasicDiagnostic diagbosticsTemp = new BasicDiagnostic();
//		if (modify instanceof AddTableModification) {
//			for (TableColumn tc : ((AddTableModification) modify).getColumns()){
//				databaseValidator.validateTableColumn(tc, diagbosticsTemp, context);
//			}
//			for (TableIndex ti : ((AddTableModification) modify).getIndexes()){
//				databaseValidator.validateTableIndex(ti, diagbosticsTemp, context);
//			}
//		}else if (modify instanceof AddTableColumnModification){
//			for (TableColumn tc : ((AddTableColumnModification) modify).getColumns()){
//				databaseValidator.validateTableColumn(tc, diagbosticsTemp, context);
//			}
//		}else if (modify instanceof AddIndexModification){
//			for (TableIndex ti : ((AddIndexModification) modify).getIndexs()){
//				databaseValidator.validateTableIndex(ti, diagbosticsTemp, context);
//			}
//		}
//		
//		for(Diagnostic dia : diagbosticsTemp.getChildren()){
//			if (dia.getSeverity() == Diagnostic.ERROR) {
//				EReference errAttribute = ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION;
//				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR,
//						DIAGNOSTIC_SOURCE,
//						0,
//						"修订记录，表修改记录错误！",
//						new Object[]{revisionHistoryProperty,errAttribute}));
//				return false;
//			}
//		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModification(Modification modification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockTableProperty(StockTableProperty stockTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stockTableProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockTableProperty_hisSpace(stockTableProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockTableProperty_hisIndexSpace(stockTableProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hisSpace constraint of '<em>Stock Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockTableProperty_hisSpace(StockTableProperty stockTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisSpace", getObjectLabel(stockTableProperty, context) },
						 new Object[] { stockTableProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hisIndexSpace constraint of '<em>Stock Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockTableProperty_hisIndexSpace(StockTableProperty stockTableProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisIndexSpace", getObjectLabel(stockTableProperty, context) },
						 new Object[] { stockTableProperty },
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
	public boolean validateStockColumnProperty(StockColumnProperty stockColumnProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stockColumnProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stockColumnProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockColumnProperty_flag(stockColumnProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the flag constraint of '<em>Stock Column Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockColumnProperty_flag(StockColumnProperty stockColumnProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "flag", getObjectLabel(stockColumnProperty, context) },
						 new Object[] { stockColumnProperty },
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
	public boolean validateStockIndexProperty(StockIndexProperty stockIndexProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stockIndexProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stockIndexProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockIndexProperty_flag(stockIndexProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the flag constraint of '<em>Stock Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockIndexProperty_flag(StockIndexProperty stockIndexProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "flag", getObjectLabel(stockIndexProperty, context) },
						 new Object[] { stockIndexProperty },
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
	public boolean validateStockTSRelationProperty(StockTSRelationProperty stockTSRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stockTSRelationProperty, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockTSRelationProperty_hisSpace(stockTSRelationProperty, diagnostics, context);
		if (result || diagnostics != null) result &= validateStockTSRelationProperty_hisIndexSpace(stockTSRelationProperty, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hisSpace constraint of '<em>Stock TS Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockTSRelationProperty_hisSpace(StockTSRelationProperty stockTSRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisSpace", getObjectLabel(stockTSRelationProperty, context) },
						 new Object[] { stockTSRelationProperty },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hisIndexSpace constraint of '<em>Stock TS Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockTSRelationProperty_hisIndexSpace(StockTSRelationProperty stockTSRelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "hisIndexSpace", getObjectLabel(stockTSRelationProperty, context) },
						 new Object[] { stockTSRelationProperty },
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
	public boolean validateStockProjectProperty(StockProjectProperty stockProjectProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stockProjectProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStockDBContextProperty(StockDBContextProperty stockDBContextProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stockDBContextProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddTableModification(AddTableModification addTableModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addTableModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHisTableColumn(HisTableColumn hisTableColumn, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(hisTableColumn, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_name(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_columnName(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_fieldName(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_defaultValue(hisTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_dataType(hisTableColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddTableColumnModification(AddTableColumnModification addTableColumnModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addTableColumnModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveTableColumnModification(RemoveTableColumnModification removeTableColumnModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeTableColumnModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemovedTableColumn(RemovedTableColumn removedTableColumn, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(removedTableColumn, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_name(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_columnName(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_fieldName(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_defaultValue(removedTableColumn, diagnostics, context);
		if (result || diagnostics != null) result &= databaseValidator.validateTableColumn_dataType(removedTableColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRenameTableColumnModification(RenameTableColumnModification renameTableColumnModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(renameTableColumnModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRTCMDetail(RTCMDetail rtcmDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(rtcmDetail, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rtcmDetail, diagnostics, context);
		if (result || diagnostics != null) result &= validateRTCMDetail_newName(rtcmDetail, diagnostics, context);
		return result;
	}

	/**
	 * Validates the newName constraint of '<em>RTCM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateRTCMDetail_newName(RTCMDetail rtcmDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String newName = rtcmDetail.getNewName();
		TableColumn c = rtcmDetail.getSnapshot();
		
		// 非标准字段不检查
		if (c != null && c.getColumnType() == ColumnType.NON_STD_FIELD)
			return true;
		
		IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		switch (ValidateUtil.checkReferenceId(resource.getARESProject(), newName, IMetadataRefType.StdField, context)) {
		case ERROR_NOT_EXIST:
			if (diagnostics != null) {
				diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"修订记录中，重命名表字段对应的标准字段不存在:["+newName+"]",
								new Object[] {
										rtcmDetail,
										DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME }));
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
	public boolean validateColumnChangeDetail(ColumnChangeDetail columnChangeDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(columnChangeDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChangeTableColumnTypeModification(ChangeTableColumnTypeModification changeTableColumnTypeModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(changeTableColumnTypeModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCTCTMDetail(CTCTMDetail ctctmDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ctctmDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddIndexModification(AddIndexModification addIndexModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addIndexModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveIndexModification(RemoveIndexModification removeIndexModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeIndexModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddIndexFieldModification(AddIndexFieldModification addIndexFieldModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addIndexFieldModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveIndexFieldModification(RemoveIndexFieldModification removeIndexFieldModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeIndexFieldModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddIndexField(AddIndexField addIndexField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addIndexField, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveIndexField(RemoveIndexField removeIndexField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeIndexField, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemovedIndex(RemovedIndex removedIndex, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removedIndex, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChangeTableColumnPrimaryKeyModifycation(ChangeTableColumnPrimaryKeyModifycation changeTableColumnPrimaryKeyModifycation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(changeTableColumnPrimaryKeyModifycation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCTCPMDetail(CTCPMDetail ctcpmDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ctcpmDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChangeTableColumnUniqueModifycation(ChangeTableColumnUniqueModifycation changeTableColumnUniqueModifycation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(changeTableColumnUniqueModifycation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCTCUMDetail(CTCUMDetail ctcumDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ctcumDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModifyDetail(ModifyDetail modifyDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modifyDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChangeTableColumnNullableModifycation(ChangeTableColumnNullableModifycation changeTableColumnNullableModifycation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(changeTableColumnNullableModifycation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCTCNMDetail(CTCNMDetail ctcnmDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(ctcnmDetail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddTableColumnPKModification(AddTableColumnPKModification addTableColumnPKModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addTableColumnPKModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveTableColumnPKModification(RemoveTableColumnPKModification removeTableColumnPKModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeTableColumnPKModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddTableColumnUniqueModifycation(AddTableColumnUniqueModifycation addTableColumnUniqueModifycation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addTableColumnUniqueModifycation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveTableColumnUniqueModifycation(RemoveTableColumnUniqueModifycation removeTableColumnUniqueModifycation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeTableColumnUniqueModifycation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddConstraintModification(AddConstraintModification addConstraintModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(addConstraintModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemoveConstraintModification(RemoveConstraintModification removeConstraintModification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(removeConstraintModification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintModifyDetail(ConstraintModifyDetail constraintModifyDetail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(constraintModifyDetail, diagnostics, context);
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

} //ChouseValidator
