/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import com.hundsun.ares.studio.jres.model.chouse.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ChouseFactoryImpl extends EFactoryImpl implements ChouseFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChouseFactory init() {
		try {
			ChouseFactory theChouseFactory = (ChouseFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/jres/database/chouse/1.0.0"); 
			if (theChouseFactory != null) {
				return theChouseFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ChouseFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChouseFactoryImpl() {
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
			case ChousePackage.TABLE_SPACE_PROPERTY: return createTableSpaceProperty();
			case ChousePackage.TABLE_SPACE_RELATION_PROPERTY: return createTableSpaceRelationProperty();
			case ChousePackage.TABLE_BASE_PROPERTY: return createTableBaseProperty();
			case ChousePackage.HISTORY_PROPERTY: return createHistoryProperty();
			case ChousePackage.REVISION_HISTORY_PROPERTY: return createRevisionHistoryProperty();
			case ChousePackage.MODIFICATION: return createModification();
			case ChousePackage.STOCK_TABLE_PROPERTY: return createStockTableProperty();
			case ChousePackage.STOCK_COLUMN_PROPERTY: return createStockColumnProperty();
			case ChousePackage.STOCK_INDEX_PROPERTY: return createStockIndexProperty();
			case ChousePackage.STOCK_TS_RELATION_PROPERTY: return createStockTSRelationProperty();
			case ChousePackage.STOCK_PROJECT_PROPERTY: return createStockProjectProperty();
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY: return createStockDBContextProperty();
			case ChousePackage.ADD_TABLE_MODIFICATION: return createAddTableModification();
			case ChousePackage.HIS_TABLE_COLUMN: return createHisTableColumn();
			case ChousePackage.ADD_TABLE_COLUMN_MODIFICATION: return createAddTableColumnModification();
			case ChousePackage.REMOVE_TABLE_COLUMN_MODIFICATION: return createRemoveTableColumnModification();
			case ChousePackage.REMOVED_TABLE_COLUMN: return createRemovedTableColumn();
			case ChousePackage.RENAME_TABLE_COLUMN_MODIFICATION: return createRenameTableColumnModification();
			case ChousePackage.RTCM_DETAIL: return createRTCMDetail();
			case ChousePackage.COLUMN_CHANGE_DETAIL: return createColumnChangeDetail();
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION: return createChangeTableColumnTypeModification();
			case ChousePackage.CTCTM_DETAIL: return createCTCTMDetail();
			case ChousePackage.ADD_INDEX_MODIFICATION: return createAddIndexModification();
			case ChousePackage.REMOVE_INDEX_MODIFICATION: return createRemoveIndexModification();
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION: return createAddIndexFieldModification();
			case ChousePackage.REMOVE_INDEX_FIELD_MODIFICATION: return createRemoveIndexFieldModification();
			case ChousePackage.ADD_INDEX_FIELD: return createAddIndexField();
			case ChousePackage.REMOVE_INDEX_FIELD: return createRemoveIndexField();
			case ChousePackage.REMOVED_INDEX: return createRemovedIndex();
			case ChousePackage.CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION: return createChangeTableColumnPrimaryKeyModifycation();
			case ChousePackage.CTCPM_DETAIL: return createCTCPMDetail();
			case ChousePackage.CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION: return createChangeTableColumnUniqueModifycation();
			case ChousePackage.CTCUM_DETAIL: return createCTCUMDetail();
			case ChousePackage.MODIFY_DETAIL: return createModifyDetail();
			case ChousePackage.CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION: return createChangeTableColumnNullableModifycation();
			case ChousePackage.CTCNM_DETAIL: return createCTCNMDetail();
			case ChousePackage.ADD_TABLE_COLUMN_PK_MODIFICATION: return createAddTableColumnPKModification();
			case ChousePackage.REMOVE_TABLE_COLUMN_PK_MODIFICATION: return createRemoveTableColumnPKModification();
			case ChousePackage.ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION: return createAddTableColumnUniqueModifycation();
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION: return createRemoveTableColumnUniqueModifycation();
			case ChousePackage.ADD_CONSTRAINT_MODIFICATION: return createAddConstraintModification();
			case ChousePackage.REMOVE_CONSTRAINT_MODIFICATION: return createRemoveConstraintModification();
			case ChousePackage.CONSTRAINT_MODIFY_DETAIL: return createConstraintModifyDetail();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceProperty createTableSpaceProperty() {
		TableSpacePropertyImpl tableSpaceProperty = new TableSpacePropertyImpl();
		return tableSpaceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceRelationProperty createTableSpaceRelationProperty() {
		TableSpaceRelationPropertyImpl tableSpaceRelationProperty = new TableSpaceRelationPropertyImpl();
		return tableSpaceRelationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBaseProperty createTableBaseProperty() {
		TableBasePropertyImpl tableBaseProperty = new TableBasePropertyImpl();
		return tableBaseProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HistoryProperty createHistoryProperty() {
		HistoryPropertyImpl historyProperty = new HistoryPropertyImpl();
		return historyProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RevisionHistoryProperty createRevisionHistoryProperty() {
		RevisionHistoryPropertyImpl revisionHistoryProperty = new RevisionHistoryPropertyImpl();
		return revisionHistoryProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modification createModification() {
		ModificationImpl modification = new ModificationImpl();
		return modification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockTableProperty createStockTableProperty() {
		StockTablePropertyImpl stockTableProperty = new StockTablePropertyImpl();
		return stockTableProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockColumnProperty createStockColumnProperty() {
		StockColumnPropertyImpl stockColumnProperty = new StockColumnPropertyImpl();
		return stockColumnProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockIndexProperty createStockIndexProperty() {
		StockIndexPropertyImpl stockIndexProperty = new StockIndexPropertyImpl();
		return stockIndexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockTSRelationProperty createStockTSRelationProperty() {
		StockTSRelationPropertyImpl stockTSRelationProperty = new StockTSRelationPropertyImpl();
		return stockTSRelationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockProjectProperty createStockProjectProperty() {
		StockProjectPropertyImpl stockProjectProperty = new StockProjectPropertyImpl();
		return stockProjectProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockDBContextProperty createStockDBContextProperty() {
		StockDBContextPropertyImpl stockDBContextProperty = new StockDBContextPropertyImpl();
		return stockDBContextProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddTableModification createAddTableModification() {
		AddTableModificationImpl addTableModification = new AddTableModificationImpl();
		return addTableModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HisTableColumn createHisTableColumn() {
		HisTableColumnImpl hisTableColumn = new HisTableColumnImpl();
		return hisTableColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddTableColumnModification createAddTableColumnModification() {
		AddTableColumnModificationImpl addTableColumnModification = new AddTableColumnModificationImpl();
		return addTableColumnModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveTableColumnModification createRemoveTableColumnModification() {
		RemoveTableColumnModificationImpl removeTableColumnModification = new RemoveTableColumnModificationImpl();
		return removeTableColumnModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemovedTableColumn createRemovedTableColumn() {
		RemovedTableColumnImpl removedTableColumn = new RemovedTableColumnImpl();
		return removedTableColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenameTableColumnModification createRenameTableColumnModification() {
		RenameTableColumnModificationImpl renameTableColumnModification = new RenameTableColumnModificationImpl();
		return renameTableColumnModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RTCMDetail createRTCMDetail() {
		RTCMDetailImpl rtcmDetail = new RTCMDetailImpl();
		return rtcmDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnChangeDetail createColumnChangeDetail() {
		ColumnChangeDetailImpl columnChangeDetail = new ColumnChangeDetailImpl();
		return columnChangeDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeTableColumnTypeModification createChangeTableColumnTypeModification() {
		ChangeTableColumnTypeModificationImpl changeTableColumnTypeModification = new ChangeTableColumnTypeModificationImpl();
		return changeTableColumnTypeModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CTCTMDetail createCTCTMDetail() {
		CTCTMDetailImpl ctctmDetail = new CTCTMDetailImpl();
		return ctctmDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddIndexModification createAddIndexModification() {
		AddIndexModificationImpl addIndexModification = new AddIndexModificationImpl();
		return addIndexModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveIndexModification createRemoveIndexModification() {
		RemoveIndexModificationImpl removeIndexModification = new RemoveIndexModificationImpl();
		return removeIndexModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddIndexFieldModification createAddIndexFieldModification() {
		AddIndexFieldModificationImpl addIndexFieldModification = new AddIndexFieldModificationImpl();
		return addIndexFieldModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveIndexFieldModification createRemoveIndexFieldModification() {
		RemoveIndexFieldModificationImpl removeIndexFieldModification = new RemoveIndexFieldModificationImpl();
		return removeIndexFieldModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddIndexField createAddIndexField() {
		AddIndexFieldImpl addIndexField = new AddIndexFieldImpl();
		return addIndexField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveIndexField createRemoveIndexField() {
		RemoveIndexFieldImpl removeIndexField = new RemoveIndexFieldImpl();
		return removeIndexField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemovedIndex createRemovedIndex() {
		RemovedIndexImpl removedIndex = new RemovedIndexImpl();
		return removedIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeTableColumnPrimaryKeyModifycation createChangeTableColumnPrimaryKeyModifycation() {
		ChangeTableColumnPrimaryKeyModifycationImpl changeTableColumnPrimaryKeyModifycation = new ChangeTableColumnPrimaryKeyModifycationImpl();
		return changeTableColumnPrimaryKeyModifycation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CTCPMDetail createCTCPMDetail() {
		CTCPMDetailImpl ctcpmDetail = new CTCPMDetailImpl();
		return ctcpmDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeTableColumnUniqueModifycation createChangeTableColumnUniqueModifycation() {
		ChangeTableColumnUniqueModifycationImpl changeTableColumnUniqueModifycation = new ChangeTableColumnUniqueModifycationImpl();
		return changeTableColumnUniqueModifycation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CTCUMDetail createCTCUMDetail() {
		CTCUMDetailImpl ctcumDetail = new CTCUMDetailImpl();
		return ctcumDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModifyDetail createModifyDetail() {
		ModifyDetailImpl modifyDetail = new ModifyDetailImpl();
		return modifyDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeTableColumnNullableModifycation createChangeTableColumnNullableModifycation() {
		ChangeTableColumnNullableModifycationImpl changeTableColumnNullableModifycation = new ChangeTableColumnNullableModifycationImpl();
		return changeTableColumnNullableModifycation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CTCNMDetail createCTCNMDetail() {
		CTCNMDetailImpl ctcnmDetail = new CTCNMDetailImpl();
		return ctcnmDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddTableColumnPKModification createAddTableColumnPKModification() {
		AddTableColumnPKModificationImpl addTableColumnPKModification = new AddTableColumnPKModificationImpl();
		return addTableColumnPKModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveTableColumnPKModification createRemoveTableColumnPKModification() {
		RemoveTableColumnPKModificationImpl removeTableColumnPKModification = new RemoveTableColumnPKModificationImpl();
		return removeTableColumnPKModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddTableColumnUniqueModifycation createAddTableColumnUniqueModifycation() {
		AddTableColumnUniqueModifycationImpl addTableColumnUniqueModifycation = new AddTableColumnUniqueModifycationImpl();
		return addTableColumnUniqueModifycation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveTableColumnUniqueModifycation createRemoveTableColumnUniqueModifycation() {
		RemoveTableColumnUniqueModifycationImpl removeTableColumnUniqueModifycation = new RemoveTableColumnUniqueModifycationImpl();
		return removeTableColumnUniqueModifycation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddConstraintModification createAddConstraintModification() {
		AddConstraintModificationImpl addConstraintModification = new AddConstraintModificationImpl();
		return addConstraintModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoveConstraintModification createRemoveConstraintModification() {
		RemoveConstraintModificationImpl removeConstraintModification = new RemoveConstraintModificationImpl();
		return removeConstraintModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintModifyDetail createConstraintModifyDetail() {
		ConstraintModifyDetailImpl constraintModifyDetail = new ConstraintModifyDetailImpl();
		return constraintModifyDetail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChousePackage getChousePackage() {
		return (ChousePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ChousePackage getPackage() {
		return ChousePackage.eINSTANCE;
	}

} //ChouseFactoryImpl
