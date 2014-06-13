/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.jres.model.chouse.*;
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
import com.hundsun.ares.studio.jres.model.database.TableColumn;

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
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage
 * @generated
 */
public class ChouseSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ChousePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChouseSwitch() {
		if (modelPackage == null) {
			modelPackage = ChousePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ChousePackage.TABLE_SPACE_PROPERTY: {
				TableSpaceProperty tableSpaceProperty = (TableSpaceProperty)theEObject;
				T result = caseTableSpaceProperty(tableSpaceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY: {
				TableSpaceRelationProperty tableSpaceRelationProperty = (TableSpaceRelationProperty)theEObject;
				T result = caseTableSpaceRelationProperty(tableSpaceRelationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.TABLE_BASE_PROPERTY: {
				TableBaseProperty tableBaseProperty = (TableBaseProperty)theEObject;
				T result = caseTableBaseProperty(tableBaseProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.HISTORY_PROPERTY: {
				HistoryProperty historyProperty = (HistoryProperty)theEObject;
				T result = caseHistoryProperty(historyProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REVISION_HISTORY_PROPERTY: {
				RevisionHistoryProperty revisionHistoryProperty = (RevisionHistoryProperty)theEObject;
				T result = caseRevisionHistoryProperty(revisionHistoryProperty);
				if (result == null) result = caseHistoryProperty(revisionHistoryProperty);
				if (result == null) result = caseIReferenceProvider(revisionHistoryProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.MODIFICATION: {
				Modification modification = (Modification)theEObject;
				T result = caseModification(modification);
				if (result == null) result = caseExtensibleModel(modification);
				if (result == null) result = caseIReferenceProvider(modification);
				if (result == null) result = caseIJSONData(modification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_TABLE_PROPERTY: {
				StockTableProperty stockTableProperty = (StockTableProperty)theEObject;
				T result = caseStockTableProperty(stockTableProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_COLUMN_PROPERTY: {
				StockColumnProperty stockColumnProperty = (StockColumnProperty)theEObject;
				T result = caseStockColumnProperty(stockColumnProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_INDEX_PROPERTY: {
				StockIndexProperty stockIndexProperty = (StockIndexProperty)theEObject;
				T result = caseStockIndexProperty(stockIndexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_TS_RELATION_PROPERTY: {
				StockTSRelationProperty stockTSRelationProperty = (StockTSRelationProperty)theEObject;
				T result = caseStockTSRelationProperty(stockTSRelationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_PROJECT_PROPERTY: {
				StockProjectProperty stockProjectProperty = (StockProjectProperty)theEObject;
				T result = caseStockProjectProperty(stockProjectProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY: {
				StockDBContextProperty stockDBContextProperty = (StockDBContextProperty)theEObject;
				T result = caseStockDBContextProperty(stockDBContextProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_TABLE_MODIFICATION: {
				AddTableModification addTableModification = (AddTableModification)theEObject;
				T result = caseAddTableModification(addTableModification);
				if (result == null) result = caseModification(addTableModification);
				if (result == null) result = caseExtensibleModel(addTableModification);
				if (result == null) result = caseIReferenceProvider(addTableModification);
				if (result == null) result = caseIJSONData(addTableModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.HIS_TABLE_COLUMN: {
				HisTableColumn hisTableColumn = (HisTableColumn)theEObject;
				T result = caseHisTableColumn(hisTableColumn);
				if (result == null) result = caseTableColumn(hisTableColumn);
				if (result == null) result = caseExtensibleModel(hisTableColumn);
				if (result == null) result = caseIJSONData(hisTableColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_TABLE_COLUMN_MODIFICATION: {
				AddTableColumnModification addTableColumnModification = (AddTableColumnModification)theEObject;
				T result = caseAddTableColumnModification(addTableColumnModification);
				if (result == null) result = caseModification(addTableColumnModification);
				if (result == null) result = caseExtensibleModel(addTableColumnModification);
				if (result == null) result = caseIReferenceProvider(addTableColumnModification);
				if (result == null) result = caseIJSONData(addTableColumnModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_TABLE_COLUMN_MODIFICATION: {
				RemoveTableColumnModification removeTableColumnModification = (RemoveTableColumnModification)theEObject;
				T result = caseRemoveTableColumnModification(removeTableColumnModification);
				if (result == null) result = caseModification(removeTableColumnModification);
				if (result == null) result = caseExtensibleModel(removeTableColumnModification);
				if (result == null) result = caseIReferenceProvider(removeTableColumnModification);
				if (result == null) result = caseIJSONData(removeTableColumnModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVED_TABLE_COLUMN: {
				RemovedTableColumn removedTableColumn = (RemovedTableColumn)theEObject;
				T result = caseRemovedTableColumn(removedTableColumn);
				if (result == null) result = caseTableColumn(removedTableColumn);
				if (result == null) result = caseExtensibleModel(removedTableColumn);
				if (result == null) result = caseIJSONData(removedTableColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.RENAME_TABLE_COLUMN_MODIFICATION: {
				RenameTableColumnModification renameTableColumnModification = (RenameTableColumnModification)theEObject;
				T result = caseRenameTableColumnModification(renameTableColumnModification);
				if (result == null) result = caseModification(renameTableColumnModification);
				if (result == null) result = caseExtensibleModel(renameTableColumnModification);
				if (result == null) result = caseIReferenceProvider(renameTableColumnModification);
				if (result == null) result = caseIJSONData(renameTableColumnModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.RTCM_DETAIL: {
				RTCMDetail rtcmDetail = (RTCMDetail)theEObject;
				T result = caseRTCMDetail(rtcmDetail);
				if (result == null) result = caseColumnChangeDetail(rtcmDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.COLUMN_CHANGE_DETAIL: {
				ColumnChangeDetail columnChangeDetail = (ColumnChangeDetail)theEObject;
				T result = caseColumnChangeDetail(columnChangeDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION: {
				ChangeTableColumnTypeModification changeTableColumnTypeModification = (ChangeTableColumnTypeModification)theEObject;
				T result = caseChangeTableColumnTypeModification(changeTableColumnTypeModification);
				if (result == null) result = caseModification(changeTableColumnTypeModification);
				if (result == null) result = caseExtensibleModel(changeTableColumnTypeModification);
				if (result == null) result = caseIReferenceProvider(changeTableColumnTypeModification);
				if (result == null) result = caseIJSONData(changeTableColumnTypeModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CTCTM_DETAIL: {
				CTCTMDetail ctctmDetail = (CTCTMDetail)theEObject;
				T result = caseCTCTMDetail(ctctmDetail);
				if (result == null) result = caseColumnChangeDetail(ctctmDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_INDEX_MODIFICATION: {
				AddIndexModification addIndexModification = (AddIndexModification)theEObject;
				T result = caseAddIndexModification(addIndexModification);
				if (result == null) result = caseModification(addIndexModification);
				if (result == null) result = caseExtensibleModel(addIndexModification);
				if (result == null) result = caseIReferenceProvider(addIndexModification);
				if (result == null) result = caseIJSONData(addIndexModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_INDEX_MODIFICATION: {
				RemoveIndexModification removeIndexModification = (RemoveIndexModification)theEObject;
				T result = caseRemoveIndexModification(removeIndexModification);
				if (result == null) result = caseModification(removeIndexModification);
				if (result == null) result = caseExtensibleModel(removeIndexModification);
				if (result == null) result = caseIReferenceProvider(removeIndexModification);
				if (result == null) result = caseIJSONData(removeIndexModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION: {
				AddIndexFieldModification addIndexFieldModification = (AddIndexFieldModification)theEObject;
				T result = caseAddIndexFieldModification(addIndexFieldModification);
				if (result == null) result = caseModification(addIndexFieldModification);
				if (result == null) result = caseExtensibleModel(addIndexFieldModification);
				if (result == null) result = caseIReferenceProvider(addIndexFieldModification);
				if (result == null) result = caseIJSONData(addIndexFieldModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_INDEX_FIELD_MODIFICATION: {
				RemoveIndexFieldModification removeIndexFieldModification = (RemoveIndexFieldModification)theEObject;
				T result = caseRemoveIndexFieldModification(removeIndexFieldModification);
				if (result == null) result = caseModification(removeIndexFieldModification);
				if (result == null) result = caseExtensibleModel(removeIndexFieldModification);
				if (result == null) result = caseIReferenceProvider(removeIndexFieldModification);
				if (result == null) result = caseIJSONData(removeIndexFieldModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_INDEX_FIELD: {
				AddIndexField addIndexField = (AddIndexField)theEObject;
				T result = caseAddIndexField(addIndexField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_INDEX_FIELD: {
				RemoveIndexField removeIndexField = (RemoveIndexField)theEObject;
				T result = caseRemoveIndexField(removeIndexField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVED_INDEX: {
				RemovedIndex removedIndex = (RemovedIndex)theEObject;
				T result = caseRemovedIndex(removedIndex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION: {
				ChangeTableColumnPrimaryKeyModifycation changeTableColumnPrimaryKeyModifycation = (ChangeTableColumnPrimaryKeyModifycation)theEObject;
				T result = caseChangeTableColumnPrimaryKeyModifycation(changeTableColumnPrimaryKeyModifycation);
				if (result == null) result = caseModification(changeTableColumnPrimaryKeyModifycation);
				if (result == null) result = caseExtensibleModel(changeTableColumnPrimaryKeyModifycation);
				if (result == null) result = caseIReferenceProvider(changeTableColumnPrimaryKeyModifycation);
				if (result == null) result = caseIJSONData(changeTableColumnPrimaryKeyModifycation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CTCPM_DETAIL: {
				CTCPMDetail ctcpmDetail = (CTCPMDetail)theEObject;
				T result = caseCTCPMDetail(ctcpmDetail);
				if (result == null) result = caseModifyDetail(ctcpmDetail);
				if (result == null) result = caseColumnChangeDetail(ctcpmDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION: {
				ChangeTableColumnUniqueModifycation changeTableColumnUniqueModifycation = (ChangeTableColumnUniqueModifycation)theEObject;
				T result = caseChangeTableColumnUniqueModifycation(changeTableColumnUniqueModifycation);
				if (result == null) result = caseModification(changeTableColumnUniqueModifycation);
				if (result == null) result = caseExtensibleModel(changeTableColumnUniqueModifycation);
				if (result == null) result = caseIReferenceProvider(changeTableColumnUniqueModifycation);
				if (result == null) result = caseIJSONData(changeTableColumnUniqueModifycation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CTCUM_DETAIL: {
				CTCUMDetail ctcumDetail = (CTCUMDetail)theEObject;
				T result = caseCTCUMDetail(ctcumDetail);
				if (result == null) result = caseModifyDetail(ctcumDetail);
				if (result == null) result = caseColumnChangeDetail(ctcumDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.MODIFY_DETAIL: {
				ModifyDetail modifyDetail = (ModifyDetail)theEObject;
				T result = caseModifyDetail(modifyDetail);
				if (result == null) result = caseColumnChangeDetail(modifyDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION: {
				ChangeTableColumnNullableModifycation changeTableColumnNullableModifycation = (ChangeTableColumnNullableModifycation)theEObject;
				T result = caseChangeTableColumnNullableModifycation(changeTableColumnNullableModifycation);
				if (result == null) result = caseModification(changeTableColumnNullableModifycation);
				if (result == null) result = caseExtensibleModel(changeTableColumnNullableModifycation);
				if (result == null) result = caseIReferenceProvider(changeTableColumnNullableModifycation);
				if (result == null) result = caseIJSONData(changeTableColumnNullableModifycation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CTCNM_DETAIL: {
				CTCNMDetail ctcnmDetail = (CTCNMDetail)theEObject;
				T result = caseCTCNMDetail(ctcnmDetail);
				if (result == null) result = caseModifyDetail(ctcnmDetail);
				if (result == null) result = caseColumnChangeDetail(ctcnmDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_TABLE_COLUMN_PK_MODIFICATION: {
				AddTableColumnPKModification addTableColumnPKModification = (AddTableColumnPKModification)theEObject;
				T result = caseAddTableColumnPKModification(addTableColumnPKModification);
				if (result == null) result = caseModification(addTableColumnPKModification);
				if (result == null) result = caseExtensibleModel(addTableColumnPKModification);
				if (result == null) result = caseIReferenceProvider(addTableColumnPKModification);
				if (result == null) result = caseIJSONData(addTableColumnPKModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_TABLE_COLUMN_PK_MODIFICATION: {
				RemoveTableColumnPKModification removeTableColumnPKModification = (RemoveTableColumnPKModification)theEObject;
				T result = caseRemoveTableColumnPKModification(removeTableColumnPKModification);
				if (result == null) result = caseModification(removeTableColumnPKModification);
				if (result == null) result = caseExtensibleModel(removeTableColumnPKModification);
				if (result == null) result = caseIReferenceProvider(removeTableColumnPKModification);
				if (result == null) result = caseIJSONData(removeTableColumnPKModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION: {
				AddTableColumnUniqueModifycation addTableColumnUniqueModifycation = (AddTableColumnUniqueModifycation)theEObject;
				T result = caseAddTableColumnUniqueModifycation(addTableColumnUniqueModifycation);
				if (result == null) result = caseModification(addTableColumnUniqueModifycation);
				if (result == null) result = caseExtensibleModel(addTableColumnUniqueModifycation);
				if (result == null) result = caseIReferenceProvider(addTableColumnUniqueModifycation);
				if (result == null) result = caseIJSONData(addTableColumnUniqueModifycation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION: {
				RemoveTableColumnUniqueModifycation removeTableColumnUniqueModifycation = (RemoveTableColumnUniqueModifycation)theEObject;
				T result = caseRemoveTableColumnUniqueModifycation(removeTableColumnUniqueModifycation);
				if (result == null) result = caseModification(removeTableColumnUniqueModifycation);
				if (result == null) result = caseExtensibleModel(removeTableColumnUniqueModifycation);
				if (result == null) result = caseIReferenceProvider(removeTableColumnUniqueModifycation);
				if (result == null) result = caseIJSONData(removeTableColumnUniqueModifycation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.ADD_CONSTRAINT_MODIFICATION: {
				AddConstraintModification addConstraintModification = (AddConstraintModification)theEObject;
				T result = caseAddConstraintModification(addConstraintModification);
				if (result == null) result = caseModification(addConstraintModification);
				if (result == null) result = caseExtensibleModel(addConstraintModification);
				if (result == null) result = caseIReferenceProvider(addConstraintModification);
				if (result == null) result = caseIJSONData(addConstraintModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.REMOVE_CONSTRAINT_MODIFICATION: {
				RemoveConstraintModification removeConstraintModification = (RemoveConstraintModification)theEObject;
				T result = caseRemoveConstraintModification(removeConstraintModification);
				if (result == null) result = caseModification(removeConstraintModification);
				if (result == null) result = caseExtensibleModel(removeConstraintModification);
				if (result == null) result = caseIReferenceProvider(removeConstraintModification);
				if (result == null) result = caseIJSONData(removeConstraintModification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL: {
				ConstraintModifyDetail constraintModifyDetail = (ConstraintModifyDetail)theEObject;
				T result = caseConstraintModifyDetail(constraintModifyDetail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableSpaceProperty(TableSpaceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space Relation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableSpaceRelationProperty(TableSpaceRelationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Base Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableBaseProperty(TableBaseProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>History Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>History Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHistoryProperty(HistoryProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Revision History Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Revision History Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRevisionHistoryProperty(RevisionHistoryProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModification(Modification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock Table Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockTableProperty(StockTableProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock Column Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock Column Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockColumnProperty(StockColumnProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock Index Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockIndexProperty(StockIndexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock TS Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock TS Relation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockTSRelationProperty(StockTSRelationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock Project Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock Project Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockProjectProperty(StockProjectProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stock DB Context Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stock DB Context Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStockDBContextProperty(StockDBContextProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Table Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Table Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddTableModification(AddTableModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>His Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>His Table Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHisTableColumn(HisTableColumn object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Table Column Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddTableColumnModification(AddTableColumnModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Table Column Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveTableColumnModification(RemoveTableColumnModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Removed Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Removed Table Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemovedTableColumn(RemovedTableColumn object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rename Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rename Table Column Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRenameTableColumnModification(RenameTableColumnModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RTCM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RTCM Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRTCMDetail(RTCMDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column Change Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column Change Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseColumnChangeDetail(ColumnChangeDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Table Column Type Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Table Column Type Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeTableColumnTypeModification(ChangeTableColumnTypeModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CTCTM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CTCTM Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCTCTMDetail(CTCTMDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Index Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Index Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddIndexModification(AddIndexModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Index Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Index Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveIndexModification(RemoveIndexModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Index Field Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Index Field Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddIndexFieldModification(AddIndexFieldModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Index Field Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Index Field Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveIndexFieldModification(RemoveIndexFieldModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Index Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Index Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddIndexField(AddIndexField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Index Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Index Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveIndexField(RemoveIndexField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Removed Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Removed Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemovedIndex(RemovedIndex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Table Column Primary Key Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Table Column Primary Key Modifycation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeTableColumnPrimaryKeyModifycation(ChangeTableColumnPrimaryKeyModifycation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CTCPM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CTCPM Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCTCPMDetail(CTCPMDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Table Column Unique Modifycation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeTableColumnUniqueModifycation(ChangeTableColumnUniqueModifycation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CTCUM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CTCUM Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCTCUMDetail(CTCUMDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modify Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modify Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModifyDetail(ModifyDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Table Column Nullable Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Table Column Nullable Modifycation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeTableColumnNullableModifycation(ChangeTableColumnNullableModifycation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CTCNM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CTCNM Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCTCNMDetail(CTCNMDetail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Table Column PK Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Table Column PK Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddTableColumnPKModification(AddTableColumnPKModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Table Column PK Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Table Column PK Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveTableColumnPKModification(RemoveTableColumnPKModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Table Column Unique Modifycation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddTableColumnUniqueModifycation(AddTableColumnUniqueModifycation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Table Column Unique Modifycation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveTableColumnUniqueModifycation(RemoveTableColumnUniqueModifycation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Constraint Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Constraint Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddConstraintModification(AddConstraintModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Constraint Modification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Constraint Modification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveConstraintModification(RemoveConstraintModification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Modify Detail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Modify Detail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintModifyDetail(ConstraintModifyDetail object) {
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
	public T caseIReferenceProvider(IReferenceProvider object) {
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
	public T caseIJSONData(IJSONData object) {
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
	public T caseExtensibleModel(ExtensibleModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableColumn(TableColumn object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //ChouseSwitch
