/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage
 * @generated
 */
public interface ChouseFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ChouseFactory eINSTANCE = com.hundsun.ares.studio.jres.model.chouse.impl.ChouseFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Table Space Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Space Property</em>'.
	 * @generated
	 */
	TableSpaceProperty createTableSpaceProperty();

	/**
	 * Returns a new object of class '<em>Table Space Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Space Relation Property</em>'.
	 * @generated
	 */
	TableSpaceRelationProperty createTableSpaceRelationProperty();

	/**
	 * Returns a new object of class '<em>Table Base Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Base Property</em>'.
	 * @generated
	 */
	TableBaseProperty createTableBaseProperty();

	/**
	 * Returns a new object of class '<em>History Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>History Property</em>'.
	 * @generated
	 */
	HistoryProperty createHistoryProperty();

	/**
	 * Returns a new object of class '<em>Revision History Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Revision History Property</em>'.
	 * @generated
	 */
	RevisionHistoryProperty createRevisionHistoryProperty();

	/**
	 * Returns a new object of class '<em>Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modification</em>'.
	 * @generated
	 */
	Modification createModification();

	/**
	 * Returns a new object of class '<em>Stock Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock Table Property</em>'.
	 * @generated
	 */
	StockTableProperty createStockTableProperty();

	/**
	 * Returns a new object of class '<em>Stock Column Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock Column Property</em>'.
	 * @generated
	 */
	StockColumnProperty createStockColumnProperty();

	/**
	 * Returns a new object of class '<em>Stock Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock Index Property</em>'.
	 * @generated
	 */
	StockIndexProperty createStockIndexProperty();

	/**
	 * Returns a new object of class '<em>Stock TS Relation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock TS Relation Property</em>'.
	 * @generated
	 */
	StockTSRelationProperty createStockTSRelationProperty();

	/**
	 * Returns a new object of class '<em>Stock Project Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock Project Property</em>'.
	 * @generated
	 */
	StockProjectProperty createStockProjectProperty();

	/**
	 * Returns a new object of class '<em>Stock DB Context Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stock DB Context Property</em>'.
	 * @generated
	 */
	StockDBContextProperty createStockDBContextProperty();

	/**
	 * Returns a new object of class '<em>Add Table Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Table Modification</em>'.
	 * @generated
	 */
	AddTableModification createAddTableModification();

	/**
	 * Returns a new object of class '<em>His Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>His Table Column</em>'.
	 * @generated
	 */
	HisTableColumn createHisTableColumn();

	/**
	 * Returns a new object of class '<em>Add Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Table Column Modification</em>'.
	 * @generated
	 */
	AddTableColumnModification createAddTableColumnModification();

	/**
	 * Returns a new object of class '<em>Remove Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Table Column Modification</em>'.
	 * @generated
	 */
	RemoveTableColumnModification createRemoveTableColumnModification();

	/**
	 * Returns a new object of class '<em>Removed Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Removed Table Column</em>'.
	 * @generated
	 */
	RemovedTableColumn createRemovedTableColumn();

	/**
	 * Returns a new object of class '<em>Rename Table Column Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rename Table Column Modification</em>'.
	 * @generated
	 */
	RenameTableColumnModification createRenameTableColumnModification();

	/**
	 * Returns a new object of class '<em>RTCM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RTCM Detail</em>'.
	 * @generated
	 */
	RTCMDetail createRTCMDetail();

	/**
	 * Returns a new object of class '<em>Column Change Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Column Change Detail</em>'.
	 * @generated
	 */
	ColumnChangeDetail createColumnChangeDetail();

	/**
	 * Returns a new object of class '<em>Change Table Column Type Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Table Column Type Modification</em>'.
	 * @generated
	 */
	ChangeTableColumnTypeModification createChangeTableColumnTypeModification();

	/**
	 * Returns a new object of class '<em>CTCTM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CTCTM Detail</em>'.
	 * @generated
	 */
	CTCTMDetail createCTCTMDetail();

	/**
	 * Returns a new object of class '<em>Add Index Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Index Modification</em>'.
	 * @generated
	 */
	AddIndexModification createAddIndexModification();

	/**
	 * Returns a new object of class '<em>Remove Index Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Index Modification</em>'.
	 * @generated
	 */
	RemoveIndexModification createRemoveIndexModification();

	/**
	 * Returns a new object of class '<em>Add Index Field Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Index Field Modification</em>'.
	 * @generated
	 */
	AddIndexFieldModification createAddIndexFieldModification();

	/**
	 * Returns a new object of class '<em>Remove Index Field Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Index Field Modification</em>'.
	 * @generated
	 */
	RemoveIndexFieldModification createRemoveIndexFieldModification();

	/**
	 * Returns a new object of class '<em>Add Index Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Index Field</em>'.
	 * @generated
	 */
	AddIndexField createAddIndexField();

	/**
	 * Returns a new object of class '<em>Remove Index Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Index Field</em>'.
	 * @generated
	 */
	RemoveIndexField createRemoveIndexField();

	/**
	 * Returns a new object of class '<em>Removed Index</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Removed Index</em>'.
	 * @generated
	 */
	RemovedIndex createRemovedIndex();

	/**
	 * Returns a new object of class '<em>Change Table Column Primary Key Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Table Column Primary Key Modifycation</em>'.
	 * @generated
	 */
	ChangeTableColumnPrimaryKeyModifycation createChangeTableColumnPrimaryKeyModifycation();

	/**
	 * Returns a new object of class '<em>CTCPM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CTCPM Detail</em>'.
	 * @generated
	 */
	CTCPMDetail createCTCPMDetail();

	/**
	 * Returns a new object of class '<em>Change Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Table Column Unique Modifycation</em>'.
	 * @generated
	 */
	ChangeTableColumnUniqueModifycation createChangeTableColumnUniqueModifycation();

	/**
	 * Returns a new object of class '<em>CTCUM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CTCUM Detail</em>'.
	 * @generated
	 */
	CTCUMDetail createCTCUMDetail();

	/**
	 * Returns a new object of class '<em>Modify Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modify Detail</em>'.
	 * @generated
	 */
	ModifyDetail createModifyDetail();

	/**
	 * Returns a new object of class '<em>Change Table Column Nullable Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Table Column Nullable Modifycation</em>'.
	 * @generated
	 */
	ChangeTableColumnNullableModifycation createChangeTableColumnNullableModifycation();

	/**
	 * Returns a new object of class '<em>CTCNM Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CTCNM Detail</em>'.
	 * @generated
	 */
	CTCNMDetail createCTCNMDetail();

	/**
	 * Returns a new object of class '<em>Add Table Column PK Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Table Column PK Modification</em>'.
	 * @generated
	 */
	AddTableColumnPKModification createAddTableColumnPKModification();

	/**
	 * Returns a new object of class '<em>Remove Table Column PK Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Table Column PK Modification</em>'.
	 * @generated
	 */
	RemoveTableColumnPKModification createRemoveTableColumnPKModification();

	/**
	 * Returns a new object of class '<em>Add Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Table Column Unique Modifycation</em>'.
	 * @generated
	 */
	AddTableColumnUniqueModifycation createAddTableColumnUniqueModifycation();

	/**
	 * Returns a new object of class '<em>Remove Table Column Unique Modifycation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Table Column Unique Modifycation</em>'.
	 * @generated
	 */
	RemoveTableColumnUniqueModifycation createRemoveTableColumnUniqueModifycation();

	/**
	 * Returns a new object of class '<em>Add Constraint Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Constraint Modification</em>'.
	 * @generated
	 */
	AddConstraintModification createAddConstraintModification();

	/**
	 * Returns a new object of class '<em>Remove Constraint Modification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Constraint Modification</em>'.
	 * @generated
	 */
	RemoveConstraintModification createRemoveConstraintModification();

	/**
	 * Returns a new object of class '<em>Constraint Modify Detail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Modify Detail</em>'.
	 * @generated
	 */
	ConstraintModifyDetail createConstraintModifyDetail();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ChousePackage getChousePackage();

} //ChouseFactory
