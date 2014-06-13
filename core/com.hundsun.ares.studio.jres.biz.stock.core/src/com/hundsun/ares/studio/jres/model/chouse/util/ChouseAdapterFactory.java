/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage
 * @generated
 */
public class ChouseAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ChousePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChouseAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ChousePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChouseSwitch<Adapter> modelSwitch =
		new ChouseSwitch<Adapter>() {
			@Override
			public Adapter caseTableSpaceProperty(TableSpaceProperty object) {
				return createTableSpacePropertyAdapter();
			}
			@Override
			public Adapter caseTableSpaceRelationProperty(TableSpaceRelationProperty object) {
				return createTableSpaceRelationPropertyAdapter();
			}
			@Override
			public Adapter caseTableBaseProperty(TableBaseProperty object) {
				return createTableBasePropertyAdapter();
			}
			@Override
			public Adapter caseHistoryProperty(HistoryProperty object) {
				return createHistoryPropertyAdapter();
			}
			@Override
			public Adapter caseRevisionHistoryProperty(RevisionHistoryProperty object) {
				return createRevisionHistoryPropertyAdapter();
			}
			@Override
			public Adapter caseModification(Modification object) {
				return createModificationAdapter();
			}
			@Override
			public Adapter caseStockTableProperty(StockTableProperty object) {
				return createStockTablePropertyAdapter();
			}
			@Override
			public Adapter caseStockColumnProperty(StockColumnProperty object) {
				return createStockColumnPropertyAdapter();
			}
			@Override
			public Adapter caseStockIndexProperty(StockIndexProperty object) {
				return createStockIndexPropertyAdapter();
			}
			@Override
			public Adapter caseStockTSRelationProperty(StockTSRelationProperty object) {
				return createStockTSRelationPropertyAdapter();
			}
			@Override
			public Adapter caseStockProjectProperty(StockProjectProperty object) {
				return createStockProjectPropertyAdapter();
			}
			@Override
			public Adapter caseStockDBContextProperty(StockDBContextProperty object) {
				return createStockDBContextPropertyAdapter();
			}
			@Override
			public Adapter caseAddTableModification(AddTableModification object) {
				return createAddTableModificationAdapter();
			}
			@Override
			public Adapter caseHisTableColumn(HisTableColumn object) {
				return createHisTableColumnAdapter();
			}
			@Override
			public Adapter caseAddTableColumnModification(AddTableColumnModification object) {
				return createAddTableColumnModificationAdapter();
			}
			@Override
			public Adapter caseRemoveTableColumnModification(RemoveTableColumnModification object) {
				return createRemoveTableColumnModificationAdapter();
			}
			@Override
			public Adapter caseRemovedTableColumn(RemovedTableColumn object) {
				return createRemovedTableColumnAdapter();
			}
			@Override
			public Adapter caseRenameTableColumnModification(RenameTableColumnModification object) {
				return createRenameTableColumnModificationAdapter();
			}
			@Override
			public Adapter caseRTCMDetail(RTCMDetail object) {
				return createRTCMDetailAdapter();
			}
			@Override
			public Adapter caseColumnChangeDetail(ColumnChangeDetail object) {
				return createColumnChangeDetailAdapter();
			}
			@Override
			public Adapter caseChangeTableColumnTypeModification(ChangeTableColumnTypeModification object) {
				return createChangeTableColumnTypeModificationAdapter();
			}
			@Override
			public Adapter caseCTCTMDetail(CTCTMDetail object) {
				return createCTCTMDetailAdapter();
			}
			@Override
			public Adapter caseAddIndexModification(AddIndexModification object) {
				return createAddIndexModificationAdapter();
			}
			@Override
			public Adapter caseRemoveIndexModification(RemoveIndexModification object) {
				return createRemoveIndexModificationAdapter();
			}
			@Override
			public Adapter caseAddIndexFieldModification(AddIndexFieldModification object) {
				return createAddIndexFieldModificationAdapter();
			}
			@Override
			public Adapter caseRemoveIndexFieldModification(RemoveIndexFieldModification object) {
				return createRemoveIndexFieldModificationAdapter();
			}
			@Override
			public Adapter caseAddIndexField(AddIndexField object) {
				return createAddIndexFieldAdapter();
			}
			@Override
			public Adapter caseRemoveIndexField(RemoveIndexField object) {
				return createRemoveIndexFieldAdapter();
			}
			@Override
			public Adapter caseRemovedIndex(RemovedIndex object) {
				return createRemovedIndexAdapter();
			}
			@Override
			public Adapter caseChangeTableColumnPrimaryKeyModifycation(ChangeTableColumnPrimaryKeyModifycation object) {
				return createChangeTableColumnPrimaryKeyModifycationAdapter();
			}
			@Override
			public Adapter caseCTCPMDetail(CTCPMDetail object) {
				return createCTCPMDetailAdapter();
			}
			@Override
			public Adapter caseChangeTableColumnUniqueModifycation(ChangeTableColumnUniqueModifycation object) {
				return createChangeTableColumnUniqueModifycationAdapter();
			}
			@Override
			public Adapter caseCTCUMDetail(CTCUMDetail object) {
				return createCTCUMDetailAdapter();
			}
			@Override
			public Adapter caseModifyDetail(ModifyDetail object) {
				return createModifyDetailAdapter();
			}
			@Override
			public Adapter caseChangeTableColumnNullableModifycation(ChangeTableColumnNullableModifycation object) {
				return createChangeTableColumnNullableModifycationAdapter();
			}
			@Override
			public Adapter caseCTCNMDetail(CTCNMDetail object) {
				return createCTCNMDetailAdapter();
			}
			@Override
			public Adapter caseAddTableColumnPKModification(AddTableColumnPKModification object) {
				return createAddTableColumnPKModificationAdapter();
			}
			@Override
			public Adapter caseRemoveTableColumnPKModification(RemoveTableColumnPKModification object) {
				return createRemoveTableColumnPKModificationAdapter();
			}
			@Override
			public Adapter caseAddTableColumnUniqueModifycation(AddTableColumnUniqueModifycation object) {
				return createAddTableColumnUniqueModifycationAdapter();
			}
			@Override
			public Adapter caseRemoveTableColumnUniqueModifycation(RemoveTableColumnUniqueModifycation object) {
				return createRemoveTableColumnUniqueModifycationAdapter();
			}
			@Override
			public Adapter caseAddConstraintModification(AddConstraintModification object) {
				return createAddConstraintModificationAdapter();
			}
			@Override
			public Adapter caseRemoveConstraintModification(RemoveConstraintModification object) {
				return createRemoveConstraintModificationAdapter();
			}
			@Override
			public Adapter caseConstraintModifyDetail(ConstraintModifyDetail object) {
				return createConstraintModifyDetailAdapter();
			}
			@Override
			public Adapter caseIReferenceProvider(IReferenceProvider object) {
				return createIReferenceProviderAdapter();
			}
			@Override
			public Adapter caseIJSONData(IJSONData object) {
				return createIJSONDataAdapter();
			}
			@Override
			public Adapter caseExtensibleModel(ExtensibleModel object) {
				return createExtensibleModelAdapter();
			}
			@Override
			public Adapter caseTableColumn(TableColumn object) {
				return createTableColumnAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty <em>Table Space Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty
	 * @generated
	 */
	public Adapter createTableSpacePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty <em>Table Space Relation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty
	 * @generated
	 */
	public Adapter createTableSpaceRelationPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty <em>Table Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty
	 * @generated
	 */
	public Adapter createTableBasePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.HistoryProperty <em>History Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.HistoryProperty
	 * @generated
	 */
	public Adapter createHistoryPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty <em>Revision History Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty
	 * @generated
	 */
	public Adapter createRevisionHistoryPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.Modification <em>Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.Modification
	 * @generated
	 */
	public Adapter createModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockTableProperty <em>Stock Table Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTableProperty
	 * @generated
	 */
	public Adapter createStockTablePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty <em>Stock Column Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty
	 * @generated
	 */
	public Adapter createStockColumnPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty <em>Stock Index Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty
	 * @generated
	 */
	public Adapter createStockIndexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty <em>Stock TS Relation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty
	 * @generated
	 */
	public Adapter createStockTSRelationPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty <em>Stock Project Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty
	 * @generated
	 */
	public Adapter createStockProjectPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty <em>Stock DB Context Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty
	 * @generated
	 */
	public Adapter createStockDBContextPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification <em>Add Table Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification
	 * @generated
	 */
	public Adapter createAddTableModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.HisTableColumn <em>His Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.HisTableColumn
	 * @generated
	 */
	public Adapter createHisTableColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification <em>Add Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification
	 * @generated
	 */
	public Adapter createAddTableColumnModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification <em>Remove Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification
	 * @generated
	 */
	public Adapter createRemoveTableColumnModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn <em>Removed Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn
	 * @generated
	 */
	public Adapter createRemovedTableColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification <em>Rename Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification
	 * @generated
	 */
	public Adapter createRenameTableColumnModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail <em>RTCM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RTCMDetail
	 * @generated
	 */
	public Adapter createRTCMDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail <em>Column Change Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail
	 * @generated
	 */
	public Adapter createColumnChangeDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification <em>Change Table Column Type Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification
	 * @generated
	 */
	public Adapter createChangeTableColumnTypeModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail <em>CTCTM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail
	 * @generated
	 */
	public Adapter createCTCTMDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexModification <em>Add Index Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexModification
	 * @generated
	 */
	public Adapter createAddIndexModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification <em>Remove Index Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification
	 * @generated
	 */
	public Adapter createRemoveIndexModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification <em>Add Index Field Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification
	 * @generated
	 */
	public Adapter createAddIndexFieldModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification <em>Remove Index Field Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification
	 * @generated
	 */
	public Adapter createRemoveIndexFieldModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexField <em>Add Index Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexField
	 * @generated
	 */
	public Adapter createAddIndexFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField <em>Remove Index Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField
	 * @generated
	 */
	public Adapter createRemoveIndexFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedIndex <em>Removed Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedIndex
	 * @generated
	 */
	public Adapter createRemovedIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation <em>Change Table Column Primary Key Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation
	 * @generated
	 */
	public Adapter createChangeTableColumnPrimaryKeyModifycationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail <em>CTCPM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail
	 * @generated
	 */
	public Adapter createCTCPMDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation <em>Change Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation
	 * @generated
	 */
	public Adapter createChangeTableColumnUniqueModifycationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail <em>CTCUM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail
	 * @generated
	 */
	public Adapter createCTCUMDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ModifyDetail <em>Modify Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ModifyDetail
	 * @generated
	 */
	public Adapter createModifyDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation <em>Change Table Column Nullable Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation
	 * @generated
	 */
	public Adapter createChangeTableColumnNullableModifycationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail <em>CTCNM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail
	 * @generated
	 */
	public Adapter createCTCNMDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification <em>Add Table Column PK Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification
	 * @generated
	 */
	public Adapter createAddTableColumnPKModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification <em>Remove Table Column PK Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification
	 * @generated
	 */
	public Adapter createRemoveTableColumnPKModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation <em>Add Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation
	 * @generated
	 */
	public Adapter createAddTableColumnUniqueModifycationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation <em>Remove Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation
	 * @generated
	 */
	public Adapter createRemoveTableColumnUniqueModifycationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification <em>Add Constraint Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification
	 * @generated
	 */
	public Adapter createAddConstraintModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification <em>Remove Constraint Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification
	 * @generated
	 */
	public Adapter createRemoveConstraintModificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail <em>Constraint Modify Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail
	 * @generated
	 */
	public Adapter createConstraintModifyDetailAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.IReferenceProvider <em>IReference Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.IReferenceProvider
	 * @generated
	 */
	public Adapter createIReferenceProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.IJSONData <em>IJSON Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.IJSONData
	 * @generated
	 */
	public Adapter createIJSONDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.core.model.ExtensibleModel <em>Extensible Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModel
	 * @generated
	 */
	public Adapter createExtensibleModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.jres.model.database.TableColumn <em>Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn
	 * @generated
	 */
	public Adapter createTableColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ChouseAdapterFactory
