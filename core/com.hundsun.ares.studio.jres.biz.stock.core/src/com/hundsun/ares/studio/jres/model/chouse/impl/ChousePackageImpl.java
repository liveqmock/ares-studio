/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

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
import com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.HisTableColumn;
import com.hundsun.ares.studio.jres.model.chouse.HistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification;
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
import com.hundsun.ares.studio.jres.model.chouse.util.ChouseValidator;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ChousePackageImpl extends EPackageImpl implements ChousePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableSpacePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableSpaceRelationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableBasePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass historyPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass revisionHistoryPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockTablePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockColumnPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockIndexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockTSRelationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockProjectPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stockDBContextPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTableModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hisTableColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTableColumnModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeTableColumnModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removedTableColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass renameTableColumnModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rtcmDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnChangeDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeTableColumnTypeModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ctctmDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addIndexModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeIndexModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addIndexFieldModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeIndexFieldModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addIndexFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeIndexFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removedIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeTableColumnPrimaryKeyModifycationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ctcpmDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeTableColumnUniqueModifycationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ctcumDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modifyDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeTableColumnNullableModifycationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ctcnmDetailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTableColumnPKModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeTableColumnPKModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTableColumnUniqueModifycationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeTableColumnUniqueModifycationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addConstraintModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeConstraintModificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintModifyDetailEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ChousePackageImpl() {
		super(eNS_URI, ChouseFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ChousePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ChousePackage init() {
		if (isInited) return (ChousePackage)EPackage.Registry.INSTANCE.getEPackage(ChousePackage.eNS_URI);

		// Obtain or create and register package
		ChousePackageImpl theChousePackage = (ChousePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ChousePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ChousePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DatabasePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theChousePackage.createPackageContents();

		// Initialize created meta-data
		theChousePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theChousePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return ChouseValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theChousePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ChousePackage.eNS_URI, theChousePackage);
		return theChousePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableSpaceProperty() {
		return tableSpacePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceProperty_ReduTable() {
		return (EAttribute)tableSpacePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceProperty_ChearTable() {
		return (EAttribute)tableSpacePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceProperty_ChearTableIndex() {
		return (EAttribute)tableSpacePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableSpaceRelationProperty() {
		return tableSpaceRelationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelationProperty_HisSpace() {
		return (EAttribute)tableSpaceRelationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelationProperty_HisIndexSpace() {
		return (EAttribute)tableSpaceRelationPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelationProperty_FileSpace() {
		return (EAttribute)tableSpaceRelationPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceRelationProperty_FileIndexSpace() {
		return (EAttribute)tableSpaceRelationPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableBaseProperty() {
		return tableBasePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_Chear() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_Redu() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_History() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_HistorySpace() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_HistoryIndexSpace() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_ObjectID() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_SplitField() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_SplitNum() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_StartData() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_UserSplit() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_IsRedu() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_IsClear() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_FileSpace() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_FileIndexSpace() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableBaseProperty_ClearIndexSpace() {
		return (EAttribute)tableBasePropertyEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHistoryProperty() {
		return historyPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistoryProperty_InternalVersion() {
		return (EAttribute)historyPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRevisionHistoryProperty() {
		return revisionHistoryPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRevisionHistoryProperty_Action() {
		return (EReference)revisionHistoryPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistoryProperty_ActionType() {
		return (EAttribute)revisionHistoryPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistoryProperty_ActionDescription() {
		return (EAttribute)revisionHistoryPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModification() {
		return modificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockTableProperty() {
		return stockTablePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockTableProperty_History() {
		return (EAttribute)stockTablePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockColumnProperty() {
		return stockColumnPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockColumnProperty_Flag() {
		return (EAttribute)stockColumnPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockIndexProperty() {
		return stockIndexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockIndexProperty_Flag() {
		return (EAttribute)stockIndexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockTSRelationProperty() {
		return stockTSRelationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockTSRelationProperty_HisSpace() {
		return (EAttribute)stockTSRelationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockTSRelationProperty_HisIndexSpace() {
		return (EAttribute)stockTSRelationPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockProjectProperty() {
		return stockProjectPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockProjectProperty_BaseVersion() {
		return (EAttribute)stockProjectPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStockDBContextProperty() {
		return stockDBContextPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockDBContextProperty_StartVersion() {
		return (EAttribute)stockDBContextPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStockDBContextProperty_EndVersion() {
		return (EAttribute)stockDBContextPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddTableModification() {
		return addTableModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddTableModification_NewSelfTable() {
		return (EAttribute)addTableModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddTableModification_NewHistoryTable() {
		return (EAttribute)addTableModificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableModification_Columns() {
		return (EReference)addTableModificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableModification_Indexes() {
		return (EReference)addTableModificationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableModification_Keys() {
		return (EReference)addTableModificationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHisTableColumn() {
		return hisTableColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddTableColumnModification() {
		return addTableColumnModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableColumnModification_Columns() {
		return (EReference)addTableColumnModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveTableColumnModification() {
		return removeTableColumnModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveTableColumnModification_Columns() {
		return (EReference)removeTableColumnModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemovedTableColumn() {
		return removedTableColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRenameTableColumnModification() {
		return renameTableColumnModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenameTableColumnModification_Details() {
		return (EReference)renameTableColumnModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRTCMDetail() {
		return rtcmDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCMDetail_NewName() {
		return (EAttribute)rtcmDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCMDetail_OldName() {
		return (EAttribute)rtcmDetailEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCMDetail_Mark() {
		return (EAttribute)rtcmDetailEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumnChangeDetail() {
		return columnChangeDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getColumnChangeDetail_Snapshot() {
		return (EReference)columnChangeDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChangeTableColumnTypeModification() {
		return changeTableColumnTypeModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChangeTableColumnTypeModification_Details() {
		return (EReference)changeTableColumnTypeModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCTCTMDetail() {
		return ctctmDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCTMDetail_Name() {
		return (EAttribute)ctctmDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCTMDetail_NewType() {
		return (EAttribute)ctctmDetailEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCTMDetail_Mark() {
		return (EAttribute)ctctmDetailEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddIndexModification() {
		return addIndexModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddIndexModification_Indexs() {
		return (EReference)addIndexModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveIndexModification() {
		return removeIndexModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveIndexModification_Indexs() {
		return (EReference)removeIndexModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddIndexFieldModification() {
		return addIndexFieldModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddIndexFieldModification_Indexs() {
		return (EReference)addIndexFieldModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveIndexFieldModification() {
		return removeIndexFieldModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveIndexFieldModification_Indexs() {
		return (EReference)removeIndexFieldModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddIndexField() {
		return addIndexFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddIndexField_Name() {
		return (EAttribute)addIndexFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddIndexField_IndexFields() {
		return (EReference)addIndexFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddIndexField_Mark() {
		return (EAttribute)addIndexFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveIndexField() {
		return removeIndexFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoveIndexField_Name() {
		return (EAttribute)removeIndexFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoveIndexField_Mark() {
		return (EAttribute)removeIndexFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveIndexField_IndexFields() {
		return (EReference)removeIndexFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemovedIndex() {
		return removedIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemovedIndex_Name() {
		return (EAttribute)removedIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemovedIndex_Mark() {
		return (EAttribute)removedIndexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChangeTableColumnPrimaryKeyModifycation() {
		return changeTableColumnPrimaryKeyModifycationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChangeTableColumnPrimaryKeyModifycation_Details() {
		return (EReference)changeTableColumnPrimaryKeyModifycationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCTCPMDetail() {
		return ctcpmDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCPMDetail_PrimarkKey() {
		return (EAttribute)ctcpmDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChangeTableColumnUniqueModifycation() {
		return changeTableColumnUniqueModifycationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChangeTableColumnUniqueModifycation_Details() {
		return (EReference)changeTableColumnUniqueModifycationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCTCUMDetail() {
		return ctcumDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCUMDetail_Unique() {
		return (EAttribute)ctcumDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModifyDetail() {
		return modifyDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifyDetail_Name() {
		return (EAttribute)modifyDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifyDetail_Mark() {
		return (EAttribute)modifyDetailEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChangeTableColumnNullableModifycation() {
		return changeTableColumnNullableModifycationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChangeTableColumnNullableModifycation_Details() {
		return (EReference)changeTableColumnNullableModifycationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCTCNMDetail() {
		return ctcnmDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCTCNMDetail_Nullable() {
		return (EAttribute)ctcnmDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddTableColumnPKModification() {
		return addTableColumnPKModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableColumnPKModification_Details() {
		return (EReference)addTableColumnPKModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveTableColumnPKModification() {
		return removeTableColumnPKModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveTableColumnPKModification_Details() {
		return (EReference)removeTableColumnPKModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddTableColumnUniqueModifycation() {
		return addTableColumnUniqueModifycationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddTableColumnUniqueModifycation_Details() {
		return (EReference)addTableColumnUniqueModifycationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveTableColumnUniqueModifycation() {
		return removeTableColumnUniqueModifycationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveTableColumnUniqueModifycation_Details() {
		return (EReference)removeTableColumnUniqueModifycationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddConstraintModification() {
		return addConstraintModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddConstraintModification_Details() {
		return (EReference)addConstraintModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveConstraintModification() {
		return removeConstraintModificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveConstraintModification_Details() {
		return (EReference)removeConstraintModificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintModifyDetail() {
		return constraintModifyDetailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintModifyDetail_Mark() {
		return (EAttribute)constraintModifyDetailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintModifyDetail_Name() {
		return (EAttribute)constraintModifyDetailEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintModifyDetail_Columns() {
		return (EReference)constraintModifyDetailEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintModifyDetail_Type() {
		return (EAttribute)constraintModifyDetailEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintModifyDetail_ForeignKey() {
		return (EReference)constraintModifyDetailEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChouseFactory getChouseFactory() {
		return (ChouseFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		tableSpacePropertyEClass = createEClass(TABLE_SPACE_PROPERTY);
		createEAttribute(tableSpacePropertyEClass, TABLE_SPACE_PROPERTY__REDU_TABLE);
		createEAttribute(tableSpacePropertyEClass, TABLE_SPACE_PROPERTY__CHEAR_TABLE);
		createEAttribute(tableSpacePropertyEClass, TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX);

		tableSpaceRelationPropertyEClass = createEClass(TABLE_SPACE_RELATION_PROPERTY);
		createEAttribute(tableSpaceRelationPropertyEClass, TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE);
		createEAttribute(tableSpaceRelationPropertyEClass, TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE);
		createEAttribute(tableSpaceRelationPropertyEClass, TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE);
		createEAttribute(tableSpaceRelationPropertyEClass, TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE);

		tableBasePropertyEClass = createEClass(TABLE_BASE_PROPERTY);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__CHEAR);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__REDU);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__HISTORY);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__HISTORY_SPACE);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__OBJECT_ID);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__SPLIT_FIELD);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__SPLIT_NUM);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__START_DATA);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__USER_SPLIT);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__IS_REDU);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__IS_CLEAR);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__FILE_SPACE);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__FILE_INDEX_SPACE);
		createEAttribute(tableBasePropertyEClass, TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE);

		historyPropertyEClass = createEClass(HISTORY_PROPERTY);
		createEAttribute(historyPropertyEClass, HISTORY_PROPERTY__INTERNAL_VERSION);

		revisionHistoryPropertyEClass = createEClass(REVISION_HISTORY_PROPERTY);
		createEReference(revisionHistoryPropertyEClass, REVISION_HISTORY_PROPERTY__ACTION);
		createEAttribute(revisionHistoryPropertyEClass, REVISION_HISTORY_PROPERTY__ACTION_TYPE);
		createEAttribute(revisionHistoryPropertyEClass, REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION);

		modificationEClass = createEClass(MODIFICATION);

		stockTablePropertyEClass = createEClass(STOCK_TABLE_PROPERTY);
		createEAttribute(stockTablePropertyEClass, STOCK_TABLE_PROPERTY__HISTORY);

		stockColumnPropertyEClass = createEClass(STOCK_COLUMN_PROPERTY);
		createEAttribute(stockColumnPropertyEClass, STOCK_COLUMN_PROPERTY__FLAG);

		stockIndexPropertyEClass = createEClass(STOCK_INDEX_PROPERTY);
		createEAttribute(stockIndexPropertyEClass, STOCK_INDEX_PROPERTY__FLAG);

		stockTSRelationPropertyEClass = createEClass(STOCK_TS_RELATION_PROPERTY);
		createEAttribute(stockTSRelationPropertyEClass, STOCK_TS_RELATION_PROPERTY__HIS_SPACE);
		createEAttribute(stockTSRelationPropertyEClass, STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE);

		stockProjectPropertyEClass = createEClass(STOCK_PROJECT_PROPERTY);
		createEAttribute(stockProjectPropertyEClass, STOCK_PROJECT_PROPERTY__BASE_VERSION);

		stockDBContextPropertyEClass = createEClass(STOCK_DB_CONTEXT_PROPERTY);
		createEAttribute(stockDBContextPropertyEClass, STOCK_DB_CONTEXT_PROPERTY__START_VERSION);
		createEAttribute(stockDBContextPropertyEClass, STOCK_DB_CONTEXT_PROPERTY__END_VERSION);

		addTableModificationEClass = createEClass(ADD_TABLE_MODIFICATION);
		createEAttribute(addTableModificationEClass, ADD_TABLE_MODIFICATION__NEW_SELF_TABLE);
		createEAttribute(addTableModificationEClass, ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE);
		createEReference(addTableModificationEClass, ADD_TABLE_MODIFICATION__COLUMNS);
		createEReference(addTableModificationEClass, ADD_TABLE_MODIFICATION__INDEXES);
		createEReference(addTableModificationEClass, ADD_TABLE_MODIFICATION__KEYS);

		hisTableColumnEClass = createEClass(HIS_TABLE_COLUMN);

		addTableColumnModificationEClass = createEClass(ADD_TABLE_COLUMN_MODIFICATION);
		createEReference(addTableColumnModificationEClass, ADD_TABLE_COLUMN_MODIFICATION__COLUMNS);

		removeTableColumnModificationEClass = createEClass(REMOVE_TABLE_COLUMN_MODIFICATION);
		createEReference(removeTableColumnModificationEClass, REMOVE_TABLE_COLUMN_MODIFICATION__COLUMNS);

		removedTableColumnEClass = createEClass(REMOVED_TABLE_COLUMN);

		renameTableColumnModificationEClass = createEClass(RENAME_TABLE_COLUMN_MODIFICATION);
		createEReference(renameTableColumnModificationEClass, RENAME_TABLE_COLUMN_MODIFICATION__DETAILS);

		rtcmDetailEClass = createEClass(RTCM_DETAIL);
		createEAttribute(rtcmDetailEClass, RTCM_DETAIL__NEW_NAME);
		createEAttribute(rtcmDetailEClass, RTCM_DETAIL__OLD_NAME);
		createEAttribute(rtcmDetailEClass, RTCM_DETAIL__MARK);

		columnChangeDetailEClass = createEClass(COLUMN_CHANGE_DETAIL);
		createEReference(columnChangeDetailEClass, COLUMN_CHANGE_DETAIL__SNAPSHOT);

		changeTableColumnTypeModificationEClass = createEClass(CHANGE_TABLE_COLUMN_TYPE_MODIFICATION);
		createEReference(changeTableColumnTypeModificationEClass, CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS);

		ctctmDetailEClass = createEClass(CTCTM_DETAIL);
		createEAttribute(ctctmDetailEClass, CTCTM_DETAIL__NAME);
		createEAttribute(ctctmDetailEClass, CTCTM_DETAIL__NEW_TYPE);
		createEAttribute(ctctmDetailEClass, CTCTM_DETAIL__MARK);

		addIndexModificationEClass = createEClass(ADD_INDEX_MODIFICATION);
		createEReference(addIndexModificationEClass, ADD_INDEX_MODIFICATION__INDEXS);

		removeIndexModificationEClass = createEClass(REMOVE_INDEX_MODIFICATION);
		createEReference(removeIndexModificationEClass, REMOVE_INDEX_MODIFICATION__INDEXS);

		addIndexFieldModificationEClass = createEClass(ADD_INDEX_FIELD_MODIFICATION);
		createEReference(addIndexFieldModificationEClass, ADD_INDEX_FIELD_MODIFICATION__INDEXS);

		removeIndexFieldModificationEClass = createEClass(REMOVE_INDEX_FIELD_MODIFICATION);
		createEReference(removeIndexFieldModificationEClass, REMOVE_INDEX_FIELD_MODIFICATION__INDEXS);

		addIndexFieldEClass = createEClass(ADD_INDEX_FIELD);
		createEAttribute(addIndexFieldEClass, ADD_INDEX_FIELD__NAME);
		createEReference(addIndexFieldEClass, ADD_INDEX_FIELD__INDEX_FIELDS);
		createEAttribute(addIndexFieldEClass, ADD_INDEX_FIELD__MARK);

		removeIndexFieldEClass = createEClass(REMOVE_INDEX_FIELD);
		createEAttribute(removeIndexFieldEClass, REMOVE_INDEX_FIELD__NAME);
		createEAttribute(removeIndexFieldEClass, REMOVE_INDEX_FIELD__MARK);
		createEReference(removeIndexFieldEClass, REMOVE_INDEX_FIELD__INDEX_FIELDS);

		removedIndexEClass = createEClass(REMOVED_INDEX);
		createEAttribute(removedIndexEClass, REMOVED_INDEX__NAME);
		createEAttribute(removedIndexEClass, REMOVED_INDEX__MARK);

		changeTableColumnPrimaryKeyModifycationEClass = createEClass(CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION);
		createEReference(changeTableColumnPrimaryKeyModifycationEClass, CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DETAILS);

		ctcpmDetailEClass = createEClass(CTCPM_DETAIL);
		createEAttribute(ctcpmDetailEClass, CTCPM_DETAIL__PRIMARK_KEY);

		changeTableColumnUniqueModifycationEClass = createEClass(CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION);
		createEReference(changeTableColumnUniqueModifycationEClass, CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS);

		ctcumDetailEClass = createEClass(CTCUM_DETAIL);
		createEAttribute(ctcumDetailEClass, CTCUM_DETAIL__UNIQUE);

		modifyDetailEClass = createEClass(MODIFY_DETAIL);
		createEAttribute(modifyDetailEClass, MODIFY_DETAIL__NAME);
		createEAttribute(modifyDetailEClass, MODIFY_DETAIL__MARK);

		changeTableColumnNullableModifycationEClass = createEClass(CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION);
		createEReference(changeTableColumnNullableModifycationEClass, CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DETAILS);

		ctcnmDetailEClass = createEClass(CTCNM_DETAIL);
		createEAttribute(ctcnmDetailEClass, CTCNM_DETAIL__NULLABLE);

		addTableColumnPKModificationEClass = createEClass(ADD_TABLE_COLUMN_PK_MODIFICATION);
		createEReference(addTableColumnPKModificationEClass, ADD_TABLE_COLUMN_PK_MODIFICATION__DETAILS);

		removeTableColumnPKModificationEClass = createEClass(REMOVE_TABLE_COLUMN_PK_MODIFICATION);
		createEReference(removeTableColumnPKModificationEClass, REMOVE_TABLE_COLUMN_PK_MODIFICATION__DETAILS);

		addTableColumnUniqueModifycationEClass = createEClass(ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION);
		createEReference(addTableColumnUniqueModifycationEClass, ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS);

		removeTableColumnUniqueModifycationEClass = createEClass(REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION);
		createEReference(removeTableColumnUniqueModifycationEClass, REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS);

		addConstraintModificationEClass = createEClass(ADD_CONSTRAINT_MODIFICATION);
		createEReference(addConstraintModificationEClass, ADD_CONSTRAINT_MODIFICATION__DETAILS);

		removeConstraintModificationEClass = createEClass(REMOVE_CONSTRAINT_MODIFICATION);
		createEReference(removeConstraintModificationEClass, REMOVE_CONSTRAINT_MODIFICATION__DETAILS);

		constraintModifyDetailEClass = createEClass(CONSTRAINT_MODIFY_DETAIL);
		createEAttribute(constraintModifyDetailEClass, CONSTRAINT_MODIFY_DETAIL__MARK);
		createEAttribute(constraintModifyDetailEClass, CONSTRAINT_MODIFY_DETAIL__NAME);
		createEReference(constraintModifyDetailEClass, CONSTRAINT_MODIFY_DETAIL__COLUMNS);
		createEAttribute(constraintModifyDetailEClass, CONSTRAINT_MODIFY_DETAIL__TYPE);
		createEReference(constraintModifyDetailEClass, CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		DatabasePackage theDatabasePackage = (DatabasePackage)EPackage.Registry.INSTANCE.getEPackage(DatabasePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		revisionHistoryPropertyEClass.getESuperTypes().add(this.getHistoryProperty());
		revisionHistoryPropertyEClass.getESuperTypes().add(theCorePackage.getIReferenceProvider());
		modificationEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());
		modificationEClass.getESuperTypes().add(theCorePackage.getIReferenceProvider());
		addTableModificationEClass.getESuperTypes().add(this.getModification());
		hisTableColumnEClass.getESuperTypes().add(theDatabasePackage.getTableColumn());
		addTableColumnModificationEClass.getESuperTypes().add(this.getModification());
		removeTableColumnModificationEClass.getESuperTypes().add(this.getModification());
		removedTableColumnEClass.getESuperTypes().add(theDatabasePackage.getTableColumn());
		renameTableColumnModificationEClass.getESuperTypes().add(this.getModification());
		rtcmDetailEClass.getESuperTypes().add(this.getColumnChangeDetail());
		changeTableColumnTypeModificationEClass.getESuperTypes().add(this.getModification());
		ctctmDetailEClass.getESuperTypes().add(this.getColumnChangeDetail());
		addIndexModificationEClass.getESuperTypes().add(this.getModification());
		removeIndexModificationEClass.getESuperTypes().add(this.getModification());
		addIndexFieldModificationEClass.getESuperTypes().add(this.getModification());
		removeIndexFieldModificationEClass.getESuperTypes().add(this.getModification());
		changeTableColumnPrimaryKeyModifycationEClass.getESuperTypes().add(this.getModification());
		ctcpmDetailEClass.getESuperTypes().add(this.getModifyDetail());
		changeTableColumnUniqueModifycationEClass.getESuperTypes().add(this.getModification());
		ctcumDetailEClass.getESuperTypes().add(this.getModifyDetail());
		modifyDetailEClass.getESuperTypes().add(this.getColumnChangeDetail());
		changeTableColumnNullableModifycationEClass.getESuperTypes().add(this.getModification());
		ctcnmDetailEClass.getESuperTypes().add(this.getModifyDetail());
		addTableColumnPKModificationEClass.getESuperTypes().add(this.getModification());
		removeTableColumnPKModificationEClass.getESuperTypes().add(this.getModification());
		addTableColumnUniqueModifycationEClass.getESuperTypes().add(this.getModification());
		removeTableColumnUniqueModifycationEClass.getESuperTypes().add(this.getModification());
		addConstraintModificationEClass.getESuperTypes().add(this.getModification());
		removeConstraintModificationEClass.getESuperTypes().add(this.getModification());

		// Initialize classes and features; add operations and parameters
		initEClass(tableSpacePropertyEClass, TableSpaceProperty.class, "TableSpaceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableSpaceProperty_ReduTable(), ecorePackage.getEString(), "reduTable", null, 0, 1, TableSpaceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceProperty_ChearTable(), ecorePackage.getEString(), "chearTable", null, 0, 1, TableSpaceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceProperty_ChearTableIndex(), ecorePackage.getEString(), "chearTableIndex", null, 0, 1, TableSpaceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableSpaceRelationPropertyEClass, TableSpaceRelationProperty.class, "TableSpaceRelationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableSpaceRelationProperty_HisSpace(), ecorePackage.getEString(), "hisSpace", null, 0, 1, TableSpaceRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceRelationProperty_HisIndexSpace(), ecorePackage.getEString(), "hisIndexSpace", null, 0, 1, TableSpaceRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceRelationProperty_FileSpace(), ecorePackage.getEString(), "fileSpace", null, 0, 1, TableSpaceRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableSpaceRelationProperty_FileIndexSpace(), ecorePackage.getEString(), "fileIndexSpace", null, 0, 1, TableSpaceRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableBasePropertyEClass, TableBaseProperty.class, "TableBaseProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableBaseProperty_Chear(), ecorePackage.getEString(), "chear", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_Redu(), ecorePackage.getEString(), "redu", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_History(), ecorePackage.getEBoolean(), "history", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_HistorySpace(), ecorePackage.getEString(), "historySpace", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_HistoryIndexSpace(), ecorePackage.getEString(), "historyIndexSpace", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_ObjectID(), ecorePackage.getEString(), "objectID", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_SplitField(), ecorePackage.getEString(), "splitField", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_SplitNum(), ecorePackage.getEString(), "splitNum", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_StartData(), ecorePackage.getEString(), "startData", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_UserSplit(), ecorePackage.getEBoolean(), "userSplit", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_IsRedu(), ecorePackage.getEBoolean(), "isRedu", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_IsClear(), ecorePackage.getEBoolean(), "isClear", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_FileSpace(), ecorePackage.getEString(), "fileSpace", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_FileIndexSpace(), ecorePackage.getEString(), "fileIndexSpace", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableBaseProperty_ClearIndexSpace(), ecorePackage.getEString(), "clearIndexSpace", null, 0, 1, TableBaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(historyPropertyEClass, HistoryProperty.class, "HistoryProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHistoryProperty_InternalVersion(), ecorePackage.getEString(), "internalVersion", "", 0, 1, HistoryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(revisionHistoryPropertyEClass, RevisionHistoryProperty.class, "RevisionHistoryProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRevisionHistoryProperty_Action(), this.getModification(), null, "action", null, 0, 1, RevisionHistoryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistoryProperty_ActionType(), ecorePackage.getEString(), "actionType", null, 0, 1, RevisionHistoryProperty.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistoryProperty_ActionDescription(), ecorePackage.getEString(), "actionDescription", null, 0, 1, RevisionHistoryProperty.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(modificationEClass, Modification.class, "Modification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stockTablePropertyEClass, StockTableProperty.class, "StockTableProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockTableProperty_History(), ecorePackage.getEBoolean(), "history", "false", 0, 1, StockTableProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stockColumnPropertyEClass, StockColumnProperty.class, "StockColumnProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockColumnProperty_Flag(), ecorePackage.getEString(), "flag", "", 0, 1, StockColumnProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stockIndexPropertyEClass, StockIndexProperty.class, "StockIndexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockIndexProperty_Flag(), ecorePackage.getEString(), "flag", "", 0, 1, StockIndexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stockTSRelationPropertyEClass, StockTSRelationProperty.class, "StockTSRelationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockTSRelationProperty_HisSpace(), ecorePackage.getEString(), "hisSpace", "", 0, 1, StockTSRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStockTSRelationProperty_HisIndexSpace(), ecorePackage.getEString(), "hisIndexSpace", "", 0, 1, StockTSRelationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stockProjectPropertyEClass, StockProjectProperty.class, "StockProjectProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockProjectProperty_BaseVersion(), ecorePackage.getEString(), "baseVersion", null, 0, 1, StockProjectProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stockDBContextPropertyEClass, StockDBContextProperty.class, "StockDBContextProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStockDBContextProperty_StartVersion(), ecorePackage.getEString(), "startVersion", "", 0, 1, StockDBContextProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStockDBContextProperty_EndVersion(), ecorePackage.getEString(), "endVersion", "", 0, 1, StockDBContextProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addTableModificationEClass, AddTableModification.class, "AddTableModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddTableModification_NewSelfTable(), ecorePackage.getEBoolean(), "newSelfTable", null, 0, 1, AddTableModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddTableModification_NewHistoryTable(), ecorePackage.getEBoolean(), "newHistoryTable", null, 0, 1, AddTableModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddTableModification_Columns(), theDatabasePackage.getTableColumn(), null, "columns", null, 0, -1, AddTableModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddTableModification_Indexes(), theDatabasePackage.getTableIndex(), null, "indexes", null, 0, -1, AddTableModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddTableModification_Keys(), theDatabasePackage.getTableKey(), null, "keys", null, 0, -1, AddTableModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hisTableColumnEClass, HisTableColumn.class, "HisTableColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(addTableColumnModificationEClass, AddTableColumnModification.class, "AddTableColumnModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddTableColumnModification_Columns(), this.getHisTableColumn(), null, "columns", null, 0, -1, AddTableColumnModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeTableColumnModificationEClass, RemoveTableColumnModification.class, "RemoveTableColumnModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveTableColumnModification_Columns(), this.getRemovedTableColumn(), null, "columns", null, 0, -1, RemoveTableColumnModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removedTableColumnEClass, RemovedTableColumn.class, "RemovedTableColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(renameTableColumnModificationEClass, RenameTableColumnModification.class, "RenameTableColumnModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRenameTableColumnModification_Details(), this.getRTCMDetail(), null, "details", null, 0, -1, RenameTableColumnModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rtcmDetailEClass, RTCMDetail.class, "RTCMDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRTCMDetail_NewName(), ecorePackage.getEString(), "newName", "", 0, 1, RTCMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCMDetail_OldName(), ecorePackage.getEString(), "oldName", "", 0, 1, RTCMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCMDetail_Mark(), ecorePackage.getEString(), "mark", "", 0, 1, RTCMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(columnChangeDetailEClass, ColumnChangeDetail.class, "ColumnChangeDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getColumnChangeDetail_Snapshot(), theDatabasePackage.getTableColumn(), null, "snapshot", null, 0, 1, ColumnChangeDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeTableColumnTypeModificationEClass, ChangeTableColumnTypeModification.class, "ChangeTableColumnTypeModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeTableColumnTypeModification_Details(), this.getCTCTMDetail(), null, "details", null, 0, -1, ChangeTableColumnTypeModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ctctmDetailEClass, CTCTMDetail.class, "CTCTMDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCTCTMDetail_Name(), ecorePackage.getEString(), "name", "", 0, 1, CTCTMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCTCTMDetail_NewType(), ecorePackage.getEString(), "newType", "", 0, 1, CTCTMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCTCTMDetail_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, CTCTMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addIndexModificationEClass, AddIndexModification.class, "AddIndexModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddIndexModification_Indexs(), theDatabasePackage.getTableIndex(), null, "indexs", null, 0, -1, AddIndexModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeIndexModificationEClass, RemoveIndexModification.class, "RemoveIndexModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveIndexModification_Indexs(), this.getRemovedIndex(), null, "indexs", null, 0, -1, RemoveIndexModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addIndexFieldModificationEClass, AddIndexFieldModification.class, "AddIndexFieldModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddIndexFieldModification_Indexs(), this.getAddIndexField(), null, "indexs", null, 0, -1, AddIndexFieldModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeIndexFieldModificationEClass, RemoveIndexFieldModification.class, "RemoveIndexFieldModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveIndexFieldModification_Indexs(), this.getRemoveIndexField(), null, "indexs", null, 0, -1, RemoveIndexFieldModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addIndexFieldEClass, AddIndexField.class, "AddIndexField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddIndexField_Name(), ecorePackage.getEString(), "name", null, 0, 1, AddIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddIndexField_IndexFields(), theDatabasePackage.getTableIndexColumn(), null, "indexFields", null, 0, -1, AddIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddIndexField_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, AddIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeIndexFieldEClass, RemoveIndexField.class, "RemoveIndexField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemoveIndexField_Name(), ecorePackage.getEString(), "name", null, 0, 1, RemoveIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRemoveIndexField_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, RemoveIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRemoveIndexField_IndexFields(), theDatabasePackage.getTableIndexColumn(), null, "indexFields", null, 0, -1, RemoveIndexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removedIndexEClass, RemovedIndex.class, "RemovedIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemovedIndex_Name(), ecorePackage.getEString(), "name", null, 0, 1, RemovedIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRemovedIndex_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, RemovedIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeTableColumnPrimaryKeyModifycationEClass, ChangeTableColumnPrimaryKeyModifycation.class, "ChangeTableColumnPrimaryKeyModifycation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeTableColumnPrimaryKeyModifycation_Details(), this.getCTCPMDetail(), null, "details", null, 0, -1, ChangeTableColumnPrimaryKeyModifycation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ctcpmDetailEClass, CTCPMDetail.class, "CTCPMDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCTCPMDetail_PrimarkKey(), ecorePackage.getEBoolean(), "primarkKey", null, 0, 1, CTCPMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeTableColumnUniqueModifycationEClass, ChangeTableColumnUniqueModifycation.class, "ChangeTableColumnUniqueModifycation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeTableColumnUniqueModifycation_Details(), this.getCTCUMDetail(), null, "details", null, 0, -1, ChangeTableColumnUniqueModifycation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ctcumDetailEClass, CTCUMDetail.class, "CTCUMDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCTCUMDetail_Unique(), ecorePackage.getEBoolean(), "unique", null, 0, 1, CTCUMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modifyDetailEClass, ModifyDetail.class, "ModifyDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModifyDetail_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModifyDetail_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, ModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeTableColumnNullableModifycationEClass, ChangeTableColumnNullableModifycation.class, "ChangeTableColumnNullableModifycation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeTableColumnNullableModifycation_Details(), this.getCTCNMDetail(), null, "details", null, 0, -1, ChangeTableColumnNullableModifycation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ctcnmDetailEClass, CTCNMDetail.class, "CTCNMDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCTCNMDetail_Nullable(), ecorePackage.getEBoolean(), "nullable", null, 0, 1, CTCNMDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addTableColumnPKModificationEClass, AddTableColumnPKModification.class, "AddTableColumnPKModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddTableColumnPKModification_Details(), this.getModifyDetail(), null, "details", null, 0, -1, AddTableColumnPKModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeTableColumnPKModificationEClass, RemoveTableColumnPKModification.class, "RemoveTableColumnPKModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveTableColumnPKModification_Details(), this.getModifyDetail(), null, "details", null, 0, -1, RemoveTableColumnPKModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addTableColumnUniqueModifycationEClass, AddTableColumnUniqueModifycation.class, "AddTableColumnUniqueModifycation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddTableColumnUniqueModifycation_Details(), this.getModifyDetail(), null, "details", null, 0, -1, AddTableColumnUniqueModifycation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeTableColumnUniqueModifycationEClass, RemoveTableColumnUniqueModifycation.class, "RemoveTableColumnUniqueModifycation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveTableColumnUniqueModifycation_Details(), this.getModifyDetail(), null, "details", null, 0, -1, RemoveTableColumnUniqueModifycation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addConstraintModificationEClass, AddConstraintModification.class, "AddConstraintModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddConstraintModification_Details(), this.getConstraintModifyDetail(), null, "details", null, 0, -1, AddConstraintModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeConstraintModificationEClass, RemoveConstraintModification.class, "RemoveConstraintModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRemoveConstraintModification_Details(), this.getConstraintModifyDetail(), null, "details", null, 0, -1, RemoveConstraintModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintModifyDetailEClass, ConstraintModifyDetail.class, "ConstraintModifyDetail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintModifyDetail_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, ConstraintModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintModifyDetail_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConstraintModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintModifyDetail_Columns(), theDatabasePackage.getTableColumn(), null, "columns", null, 0, -1, ConstraintModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintModifyDetail_Type(), theDatabasePackage.getkey_type(), "type", null, 0, 1, ConstraintModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintModifyDetail_ForeignKey(), theDatabasePackage.getForeignKey(), null, "foreignKey", null, 0, -1, ConstraintModifyDetail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";			
		addAnnotation
		  (tableSpacePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "reduTable chearTable"
		   });		
		addAnnotation
		  (tableSpaceRelationPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "hisSpace hisIndexSpace fileSpace fileIndexSpace"
		   });		
		addAnnotation
		  (tableBasePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "clear redu history historySpace historyIndexSpace objectID splitField splitNum startData userSplit fileSpace fileIndexSpace"
		   });		
		addAnnotation
		  (revisionHistoryPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "action"
		   });		
		addAnnotation
		  (stockTablePropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "hisSpace hisIndexSpace"
		   });		
		addAnnotation
		  (stockColumnPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "flag"
		   });		
		addAnnotation
		  (stockIndexPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "flag"
		   });		
		addAnnotation
		  (stockTSRelationPropertyEClass, 
		   source, 
		   new String[] {
			 "constraints", "hisSpace hisIndexSpace"
		   });		
		addAnnotation
		  (rtcmDetailEClass, 
		   source, 
		   new String[] {
			 "constraints", "newName"
		   });										
	}

} //ChousePackageImpl
