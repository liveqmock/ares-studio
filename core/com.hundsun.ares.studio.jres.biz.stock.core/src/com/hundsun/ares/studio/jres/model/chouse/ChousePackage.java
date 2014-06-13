/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.jres.model.database.DatabasePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 删除的时候信息保存复用ConstraintModifyDetail，但是此时只有name属性有用。
 * <!-- end-model-doc -->
 * @see com.hundsun.ares.studio.jres.model.chouse.ChouseFactory
 * @model kind="package"
 * @generated
 */
public interface ChousePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "chouse";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/database/chouse/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "chouse";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ChousePackage eINSTANCE = com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl <em>Table Space Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableSpaceProperty()
	 * @generated
	 */
	int TABLE_SPACE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Redu Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_PROPERTY__REDU_TABLE = 0;

	/**
	 * The feature id for the '<em><b>Chear Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_PROPERTY__CHEAR_TABLE = 1;

	/**
	 * The feature id for the '<em><b>Chear Table Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX = 2;

	/**
	 * The number of structural features of the '<em>Table Space Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl <em>Table Space Relation Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableSpaceRelationProperty()
	 * @generated
	 */
	int TABLE_SPACE_RELATION_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>His Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE = 0;

	/**
	 * The feature id for the '<em><b>His Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE = 1;

	/**
	 * The feature id for the '<em><b>File Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE = 2;

	/**
	 * The feature id for the '<em><b>File Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE = 3;

	/**
	 * The number of structural features of the '<em>Table Space Relation Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_PROPERTY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl <em>Table Base Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableBaseProperty()
	 * @generated
	 */
	int TABLE_BASE_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Chear</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__CHEAR = 0;

	/**
	 * The feature id for the '<em><b>Redu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__REDU = 1;

	/**
	 * The feature id for the '<em><b>History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__HISTORY = 2;

	/**
	 * The feature id for the '<em><b>History Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__HISTORY_SPACE = 3;

	/**
	 * The feature id for the '<em><b>History Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE = 4;

	/**
	 * The feature id for the '<em><b>Object ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__OBJECT_ID = 5;

	/**
	 * The feature id for the '<em><b>Split Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__SPLIT_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Split Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__SPLIT_NUM = 7;

	/**
	 * The feature id for the '<em><b>Start Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__START_DATA = 8;

	/**
	 * The feature id for the '<em><b>User Split</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__USER_SPLIT = 9;

	/**
	 * The feature id for the '<em><b>Is Redu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__IS_REDU = 10;

	/**
	 * The feature id for the '<em><b>Is Clear</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__IS_CLEAR = 11;

	/**
	 * The feature id for the '<em><b>File Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__FILE_SPACE = 12;

	/**
	 * The feature id for the '<em><b>File Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__FILE_INDEX_SPACE = 13;

	/**
	 * The feature id for the '<em><b>Clear Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE = 14;

	/**
	 * The number of structural features of the '<em>Table Base Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BASE_PROPERTY_FEATURE_COUNT = 15;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.HistoryPropertyImpl <em>History Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.HistoryPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getHistoryProperty()
	 * @generated
	 */
	int HISTORY_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Internal Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_PROPERTY__INTERNAL_VERSION = 0;

	/**
	 * The number of structural features of the '<em>History Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl <em>Revision History Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRevisionHistoryProperty()
	 * @generated
	 */
	int REVISION_HISTORY_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Internal Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_PROPERTY__INTERNAL_VERSION = HISTORY_PROPERTY__INTERNAL_VERSION;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_PROPERTY__ACTION = HISTORY_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_PROPERTY__ACTION_TYPE = HISTORY_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION = HISTORY_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Revision History Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_PROPERTY_FEATURE_COUNT = HISTORY_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl <em>Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getModification()
	 * @generated
	 */
	int MODIFICATION = 5;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFICATION__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFICATION__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The number of structural features of the '<em>Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFICATION_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTablePropertyImpl <em>Stock Table Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockTablePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockTableProperty()
	 * @generated
	 */
	int STOCK_TABLE_PROPERTY = 6;

	/**
	 * The feature id for the '<em><b>History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_TABLE_PROPERTY__HISTORY = 0;

	/**
	 * The number of structural features of the '<em>Stock Table Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_TABLE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockColumnPropertyImpl <em>Stock Column Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockColumnPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockColumnProperty()
	 * @generated
	 */
	int STOCK_COLUMN_PROPERTY = 7;

	/**
	 * The feature id for the '<em><b>Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_COLUMN_PROPERTY__FLAG = 0;

	/**
	 * The number of structural features of the '<em>Stock Column Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_COLUMN_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockIndexPropertyImpl <em>Stock Index Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockIndexPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockIndexProperty()
	 * @generated
	 */
	int STOCK_INDEX_PROPERTY = 8;

	/**
	 * The feature id for the '<em><b>Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_INDEX_PROPERTY__FLAG = 0;

	/**
	 * The number of structural features of the '<em>Stock Index Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_INDEX_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl <em>Stock TS Relation Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockTSRelationProperty()
	 * @generated
	 */
	int STOCK_TS_RELATION_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>His Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_TS_RELATION_PROPERTY__HIS_SPACE = 0;

	/**
	 * The feature id for the '<em><b>His Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE = 1;

	/**
	 * The number of structural features of the '<em>Stock TS Relation Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_TS_RELATION_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockProjectPropertyImpl <em>Stock Project Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockProjectPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockProjectProperty()
	 * @generated
	 */
	int STOCK_PROJECT_PROPERTY = 10;

	/**
	 * The feature id for the '<em><b>Base Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_PROJECT_PROPERTY__BASE_VERSION = 0;

	/**
	 * The number of structural features of the '<em>Stock Project Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_PROJECT_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl <em>Stock DB Context Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockDBContextProperty()
	 * @generated
	 */
	int STOCK_DB_CONTEXT_PROPERTY = 11;

	/**
	 * The feature id for the '<em><b>Start Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_DB_CONTEXT_PROPERTY__START_VERSION = 0;

	/**
	 * The feature id for the '<em><b>End Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_DB_CONTEXT_PROPERTY__END_VERSION = 1;

	/**
	 * The number of structural features of the '<em>Stock DB Context Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCK_DB_CONTEXT_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl <em>Add Table Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableModification()
	 * @generated
	 */
	int ADD_TABLE_MODIFICATION = 12;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>New Self Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__NEW_SELF_TABLE = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>New History Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__COLUMNS = MODIFICATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__INDEXES = MODIFICATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Keys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION__KEYS = MODIFICATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Add Table Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.HisTableColumnImpl <em>His Table Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.HisTableColumnImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getHisTableColumn()
	 * @generated
	 */
	int HIS_TABLE_COLUMN = 13;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__DATA = DatabasePackage.TABLE_COLUMN__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__DATA2 = DatabasePackage.TABLE_COLUMN__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__NAME = DatabasePackage.TABLE_COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__CHINESE_NAME = DatabasePackage.TABLE_COLUMN__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__DESCRIPTION = DatabasePackage.TABLE_COLUMN__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__DATA_TYPE = DatabasePackage.TABLE_COLUMN__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__COLUMN_NAME = DatabasePackage.TABLE_COLUMN__COLUMN_NAME;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__FIELD_NAME = DatabasePackage.TABLE_COLUMN__FIELD_NAME;

	/**
	 * The feature id for the '<em><b>Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__PRIMARY_KEY = DatabasePackage.TABLE_COLUMN__PRIMARY_KEY;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__UNIQUE = DatabasePackage.TABLE_COLUMN__UNIQUE;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__NULLABLE = DatabasePackage.TABLE_COLUMN__NULLABLE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__DEFAULT_VALUE = DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Foreignkey</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__FOREIGNKEY = DatabasePackage.TABLE_COLUMN__FOREIGNKEY;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__MARK = DatabasePackage.TABLE_COLUMN__MARK;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__COMMENTS = DatabasePackage.TABLE_COLUMN__COMMENTS;

	/**
	 * The feature id for the '<em><b>Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN__COLUMN_TYPE = DatabasePackage.TABLE_COLUMN__COLUMN_TYPE;

	/**
	 * The number of structural features of the '<em>His Table Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIS_TABLE_COLUMN_FEATURE_COUNT = DatabasePackage.TABLE_COLUMN_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnModificationImpl <em>Add Table Column Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnModification()
	 * @generated
	 */
	int ADD_TABLE_COLUMN_MODIFICATION = 14;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_MODIFICATION__COLUMNS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Table Column Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnModificationImpl <em>Remove Table Column Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnModification()
	 * @generated
	 */
	int REMOVE_TABLE_COLUMN_MODIFICATION = 15;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_MODIFICATION__COLUMNS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Table Column Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemovedTableColumnImpl <em>Removed Table Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemovedTableColumnImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemovedTableColumn()
	 * @generated
	 */
	int REMOVED_TABLE_COLUMN = 16;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__DATA = DatabasePackage.TABLE_COLUMN__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__DATA2 = DatabasePackage.TABLE_COLUMN__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__NAME = DatabasePackage.TABLE_COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__CHINESE_NAME = DatabasePackage.TABLE_COLUMN__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__DESCRIPTION = DatabasePackage.TABLE_COLUMN__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__DATA_TYPE = DatabasePackage.TABLE_COLUMN__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__COLUMN_NAME = DatabasePackage.TABLE_COLUMN__COLUMN_NAME;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__FIELD_NAME = DatabasePackage.TABLE_COLUMN__FIELD_NAME;

	/**
	 * The feature id for the '<em><b>Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__PRIMARY_KEY = DatabasePackage.TABLE_COLUMN__PRIMARY_KEY;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__UNIQUE = DatabasePackage.TABLE_COLUMN__UNIQUE;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__NULLABLE = DatabasePackage.TABLE_COLUMN__NULLABLE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__DEFAULT_VALUE = DatabasePackage.TABLE_COLUMN__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Foreignkey</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__FOREIGNKEY = DatabasePackage.TABLE_COLUMN__FOREIGNKEY;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__MARK = DatabasePackage.TABLE_COLUMN__MARK;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__COMMENTS = DatabasePackage.TABLE_COLUMN__COMMENTS;

	/**
	 * The feature id for the '<em><b>Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN__COLUMN_TYPE = DatabasePackage.TABLE_COLUMN__COLUMN_TYPE;

	/**
	 * The number of structural features of the '<em>Removed Table Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_TABLE_COLUMN_FEATURE_COUNT = DatabasePackage.TABLE_COLUMN_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RenameTableColumnModificationImpl <em>Rename Table Column Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RenameTableColumnModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRenameTableColumnModification()
	 * @generated
	 */
	int RENAME_TABLE_COLUMN_MODIFICATION = 17;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENAME_TABLE_COLUMN_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENAME_TABLE_COLUMN_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENAME_TABLE_COLUMN_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rename Table Column Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENAME_TABLE_COLUMN_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ColumnChangeDetailImpl <em>Column Change Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ColumnChangeDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getColumnChangeDetail()
	 * @generated
	 */
	int COLUMN_CHANGE_DETAIL = 19;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_CHANGE_DETAIL__SNAPSHOT = 0;

	/**
	 * The number of structural features of the '<em>Column Change Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_CHANGE_DETAIL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl <em>RTCM Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRTCMDetail()
	 * @generated
	 */
	int RTCM_DETAIL = 18;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTCM_DETAIL__SNAPSHOT = COLUMN_CHANGE_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>New Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTCM_DETAIL__NEW_NAME = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Old Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTCM_DETAIL__OLD_NAME = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTCM_DETAIL__MARK = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>RTCM Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTCM_DETAIL_FEATURE_COUNT = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl <em>Change Table Column Type Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnTypeModification()
	 * @generated
	 */
	int CHANGE_TABLE_COLUMN_TYPE_MODIFICATION = 20;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Change Table Column Type Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_TYPE_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl <em>CTCTM Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCTMDetail()
	 * @generated
	 */
	int CTCTM_DETAIL = 21;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCTM_DETAIL__SNAPSHOT = COLUMN_CHANGE_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCTM_DETAIL__NAME = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>New Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCTM_DETAIL__NEW_TYPE = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCTM_DETAIL__MARK = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CTCTM Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCTM_DETAIL_FEATURE_COUNT = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexModificationImpl <em>Add Index Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexModification()
	 * @generated
	 */
	int ADD_INDEX_MODIFICATION = 22;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Indexs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_MODIFICATION__INDEXS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Index Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexModificationImpl <em>Remove Index Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexModification()
	 * @generated
	 */
	int REMOVE_INDEX_MODIFICATION = 23;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Indexs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_MODIFICATION__INDEXS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Index Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl <em>Add Index Field Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexFieldModification()
	 * @generated
	 */
	int ADD_INDEX_FIELD_MODIFICATION = 24;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Indexs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD_MODIFICATION__INDEXS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Index Field Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldModificationImpl <em>Remove Index Field Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexFieldModification()
	 * @generated
	 */
	int REMOVE_INDEX_FIELD_MODIFICATION = 25;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Indexs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD_MODIFICATION__INDEXS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Index Field Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldImpl <em>Add Index Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexField()
	 * @generated
	 */
	int ADD_INDEX_FIELD = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD__NAME = 0;

	/**
	 * The feature id for the '<em><b>Index Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD__INDEX_FIELDS = 1;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD__MARK = 2;

	/**
	 * The number of structural features of the '<em>Add Index Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_INDEX_FIELD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldImpl <em>Remove Index Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexField()
	 * @generated
	 */
	int REMOVE_INDEX_FIELD = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD__NAME = 0;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD__MARK = 1;

	/**
	 * The feature id for the '<em><b>Index Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD__INDEX_FIELDS = 2;

	/**
	 * The number of structural features of the '<em>Remove Index Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_INDEX_FIELD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemovedIndexImpl <em>Removed Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemovedIndexImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemovedIndex()
	 * @generated
	 */
	int REMOVED_INDEX = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_INDEX__NAME = 0;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_INDEX__MARK = 1;

	/**
	 * The number of structural features of the '<em>Removed Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVED_INDEX_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnPrimaryKeyModifycationImpl <em>Change Table Column Primary Key Modifycation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnPrimaryKeyModifycationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnPrimaryKeyModifycation()
	 * @generated
	 */
	int CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION = 29;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Change Table Column Primary Key Modifycation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl <em>Modify Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getModifyDetail()
	 * @generated
	 */
	int MODIFY_DETAIL = 33;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFY_DETAIL__SNAPSHOT = COLUMN_CHANGE_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFY_DETAIL__NAME = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFY_DETAIL__MARK = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Modify Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFY_DETAIL_FEATURE_COUNT = COLUMN_CHANGE_DETAIL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCPMDetailImpl <em>CTCPM Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCPMDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCPMDetail()
	 * @generated
	 */
	int CTCPM_DETAIL = 30;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCPM_DETAIL__SNAPSHOT = MODIFY_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCPM_DETAIL__NAME = MODIFY_DETAIL__NAME;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCPM_DETAIL__MARK = MODIFY_DETAIL__MARK;

	/**
	 * The feature id for the '<em><b>Primark Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCPM_DETAIL__PRIMARK_KEY = MODIFY_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CTCPM Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCPM_DETAIL_FEATURE_COUNT = MODIFY_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnUniqueModifycationImpl <em>Change Table Column Unique Modifycation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnUniqueModifycationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnUniqueModifycation()
	 * @generated
	 */
	int CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION = 31;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Change Table Column Unique Modifycation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCUMDetailImpl <em>CTCUM Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCUMDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCUMDetail()
	 * @generated
	 */
	int CTCUM_DETAIL = 32;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCUM_DETAIL__SNAPSHOT = MODIFY_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCUM_DETAIL__NAME = MODIFY_DETAIL__NAME;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCUM_DETAIL__MARK = MODIFY_DETAIL__MARK;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCUM_DETAIL__UNIQUE = MODIFY_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CTCUM Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCUM_DETAIL_FEATURE_COUNT = MODIFY_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnNullableModifycationImpl <em>Change Table Column Nullable Modifycation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnNullableModifycationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnNullableModifycation()
	 * @generated
	 */
	int CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION = 34;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Change Table Column Nullable Modifycation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCNMDetailImpl <em>CTCNM Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCNMDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCNMDetail()
	 * @generated
	 */
	int CTCNM_DETAIL = 35;

	/**
	 * The feature id for the '<em><b>Snapshot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCNM_DETAIL__SNAPSHOT = MODIFY_DETAIL__SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCNM_DETAIL__NAME = MODIFY_DETAIL__NAME;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCNM_DETAIL__MARK = MODIFY_DETAIL__MARK;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCNM_DETAIL__NULLABLE = MODIFY_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CTCNM Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CTCNM_DETAIL_FEATURE_COUNT = MODIFY_DETAIL_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnPKModificationImpl <em>Add Table Column PK Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnPKModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnPKModification()
	 * @generated
	 */
	int ADD_TABLE_COLUMN_PK_MODIFICATION = 36;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_PK_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_PK_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_PK_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Table Column PK Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_PK_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnPKModificationImpl <em>Remove Table Column PK Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnPKModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnPKModification()
	 * @generated
	 */
	int REMOVE_TABLE_COLUMN_PK_MODIFICATION = 37;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_PK_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_PK_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_PK_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Table Column PK Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_PK_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnUniqueModifycationImpl <em>Add Table Column Unique Modifycation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnUniqueModifycationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnUniqueModifycation()
	 * @generated
	 */
	int ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION = 38;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Table Column Unique Modifycation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnUniqueModifycationImpl <em>Remove Table Column Unique Modifycation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnUniqueModifycationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnUniqueModifycation()
	 * @generated
	 */
	int REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION = 39;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Table Column Unique Modifycation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddConstraintModificationImpl <em>Add Constraint Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddConstraintModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddConstraintModification()
	 * @generated
	 */
	int ADD_CONSTRAINT_MODIFICATION = 40;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_CONSTRAINT_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_CONSTRAINT_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_CONSTRAINT_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Add Constraint Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_CONSTRAINT_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveConstraintModificationImpl <em>Remove Constraint Modification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveConstraintModificationImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveConstraintModification()
	 * @generated
	 */
	int REMOVE_CONSTRAINT_MODIFICATION = 41;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_CONSTRAINT_MODIFICATION__DATA = MODIFICATION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_CONSTRAINT_MODIFICATION__DATA2 = MODIFICATION__DATA2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_CONSTRAINT_MODIFICATION__DETAILS = MODIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remove Constraint Modification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOVE_CONSTRAINT_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl <em>Constraint Modify Detail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getConstraintModifyDetail()
	 * @generated
	 */
	int CONSTRAINT_MODIFY_DETAIL = 42;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL__MARK = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL__COLUMNS = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY = 4;

	/**
	 * The number of structural features of the '<em>Constraint Modify Detail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODIFY_DETAIL_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty <em>Table Space Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty
	 * @generated
	 */
	EClass getTableSpaceProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getReduTable <em>Redu Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Redu Table</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getReduTable()
	 * @see #getTableSpaceProperty()
	 * @generated
	 */
	EAttribute getTableSpaceProperty_ReduTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTable <em>Chear Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chear Table</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTable()
	 * @see #getTableSpaceProperty()
	 * @generated
	 */
	EAttribute getTableSpaceProperty_ChearTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTableIndex <em>Chear Table Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chear Table Index</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty#getChearTableIndex()
	 * @see #getTableSpaceProperty()
	 * @generated
	 */
	EAttribute getTableSpaceProperty_ChearTableIndex();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty <em>Table Space Relation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space Relation Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty
	 * @generated
	 */
	EClass getTableSpaceRelationProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisSpace <em>His Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>His Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisSpace()
	 * @see #getTableSpaceRelationProperty()
	 * @generated
	 */
	EAttribute getTableSpaceRelationProperty_HisSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisIndexSpace <em>His Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>His Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getHisIndexSpace()
	 * @see #getTableSpaceRelationProperty()
	 * @generated
	 */
	EAttribute getTableSpaceRelationProperty_HisIndexSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileSpace <em>File Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileSpace()
	 * @see #getTableSpaceRelationProperty()
	 * @generated
	 */
	EAttribute getTableSpaceRelationProperty_FileSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileIndexSpace <em>File Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableSpaceRelationProperty#getFileIndexSpace()
	 * @see #getTableSpaceRelationProperty()
	 * @generated
	 */
	EAttribute getTableSpaceRelationProperty_FileIndexSpace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty <em>Table Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Base Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty
	 * @generated
	 */
	EClass getTableBaseProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getChear <em>Chear</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chear</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getChear()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_Chear();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getRedu <em>Redu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Redu</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getRedu()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_Redu();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isHistory <em>History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>History</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isHistory()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_History();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getHistorySpace <em>History Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>History Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getHistorySpace()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_HistorySpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getHistoryIndexSpace <em>History Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>History Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getHistoryIndexSpace()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_HistoryIndexSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getObjectID <em>Object ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object ID</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getObjectID()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_ObjectID();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getSplitField <em>Split Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getSplitField()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_SplitField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getSplitNum <em>Split Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split Num</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getSplitNum()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_SplitNum();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getStartData <em>Start Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getStartData()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_StartData();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isUserSplit <em>User Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Split</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isUserSplit()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_UserSplit();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isIsRedu <em>Is Redu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Redu</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isIsRedu()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_IsRedu();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isIsClear <em>Is Clear</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Clear</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#isIsClear()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_IsClear();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getFileSpace <em>File Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getFileSpace()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_FileSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getFileIndexSpace <em>File Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getFileIndexSpace()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_FileIndexSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getClearIndexSpace <em>Clear Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clear Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty#getClearIndexSpace()
	 * @see #getTableBaseProperty()
	 * @generated
	 */
	EAttribute getTableBaseProperty_ClearIndexSpace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.HistoryProperty <em>History Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>History Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.HistoryProperty
	 * @generated
	 */
	EClass getHistoryProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.HistoryProperty#getInternalVersion <em>Internal Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Internal Version</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.HistoryProperty#getInternalVersion()
	 * @see #getHistoryProperty()
	 * @generated
	 */
	EAttribute getHistoryProperty_InternalVersion();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty <em>Revision History Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Revision History Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty
	 * @generated
	 */
	EClass getRevisionHistoryProperty();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getAction()
	 * @see #getRevisionHistoryProperty()
	 * @generated
	 */
	EReference getRevisionHistoryProperty_Action();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionType()
	 * @see #getRevisionHistoryProperty()
	 * @generated
	 */
	EAttribute getRevisionHistoryProperty_ActionType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionDescription <em>Action Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty#getActionDescription()
	 * @see #getRevisionHistoryProperty()
	 * @generated
	 */
	EAttribute getRevisionHistoryProperty_ActionDescription();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.Modification <em>Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.Modification
	 * @generated
	 */
	EClass getModification();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockTableProperty <em>Stock Table Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock Table Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTableProperty
	 * @generated
	 */
	EClass getStockTableProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockTableProperty#isHistory <em>History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>History</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTableProperty#isHistory()
	 * @see #getStockTableProperty()
	 * @generated
	 */
	EAttribute getStockTableProperty_History();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty <em>Stock Column Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock Column Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty
	 * @generated
	 */
	EClass getStockColumnProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty#getFlag <em>Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flag</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockColumnProperty#getFlag()
	 * @see #getStockColumnProperty()
	 * @generated
	 */
	EAttribute getStockColumnProperty_Flag();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty <em>Stock Index Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock Index Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty
	 * @generated
	 */
	EClass getStockIndexProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty#getFlag <em>Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flag</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty#getFlag()
	 * @see #getStockIndexProperty()
	 * @generated
	 */
	EAttribute getStockIndexProperty_Flag();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty <em>Stock TS Relation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock TS Relation Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty
	 * @generated
	 */
	EClass getStockTSRelationProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisSpace <em>His Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>His Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisSpace()
	 * @see #getStockTSRelationProperty()
	 * @generated
	 */
	EAttribute getStockTSRelationProperty_HisSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisIndexSpace <em>His Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>His Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisIndexSpace()
	 * @see #getStockTSRelationProperty()
	 * @generated
	 */
	EAttribute getStockTSRelationProperty_HisIndexSpace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty <em>Stock Project Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock Project Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty
	 * @generated
	 */
	EClass getStockProjectProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty#getBaseVersion <em>Base Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Version</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty#getBaseVersion()
	 * @see #getStockProjectProperty()
	 * @generated
	 */
	EAttribute getStockProjectProperty_BaseVersion();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty <em>Stock DB Context Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stock DB Context Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty
	 * @generated
	 */
	EClass getStockDBContextProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getStartVersion <em>Start Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Version</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getStartVersion()
	 * @see #getStockDBContextProperty()
	 * @generated
	 */
	EAttribute getStockDBContextProperty_StartVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getEndVersion <em>End Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Version</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getEndVersion()
	 * @see #getStockDBContextProperty()
	 * @generated
	 */
	EAttribute getStockDBContextProperty_EndVersion();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification <em>Add Table Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Table Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification
	 * @generated
	 */
	EClass getAddTableModification();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewSelfTable <em>New Self Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Self Table</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewSelfTable()
	 * @see #getAddTableModification()
	 * @generated
	 */
	EAttribute getAddTableModification_NewSelfTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewHistoryTable <em>New History Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New History Table</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification#isNewHistoryTable()
	 * @see #getAddTableModification()
	 * @generated
	 */
	EAttribute getAddTableModification_NewHistoryTable();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getColumns()
	 * @see #getAddTableModification()
	 * @generated
	 */
	EReference getAddTableModification_Columns();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getIndexes()
	 * @see #getAddTableModification()
	 * @generated
	 */
	EReference getAddTableModification_Indexes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getKeys <em>Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Keys</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableModification#getKeys()
	 * @see #getAddTableModification()
	 * @generated
	 */
	EReference getAddTableModification_Keys();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.HisTableColumn <em>His Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>His Table Column</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.HisTableColumn
	 * @generated
	 */
	EClass getHisTableColumn();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification <em>Add Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Table Column Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification
	 * @generated
	 */
	EClass getAddTableColumnModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification#getColumns()
	 * @see #getAddTableColumnModification()
	 * @generated
	 */
	EReference getAddTableColumnModification_Columns();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification <em>Remove Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Table Column Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification
	 * @generated
	 */
	EClass getRemoveTableColumnModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification#getColumns()
	 * @see #getRemoveTableColumnModification()
	 * @generated
	 */
	EReference getRemoveTableColumnModification_Columns();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn <em>Removed Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Removed Table Column</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn
	 * @generated
	 */
	EClass getRemovedTableColumn();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification <em>Rename Table Column Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rename Table Column Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification
	 * @generated
	 */
	EClass getRenameTableColumnModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification#getDetails()
	 * @see #getRenameTableColumnModification()
	 * @generated
	 */
	EReference getRenameTableColumnModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail <em>RTCM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RTCM Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RTCMDetail
	 * @generated
	 */
	EClass getRTCMDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getNewName <em>New Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getNewName()
	 * @see #getRTCMDetail()
	 * @generated
	 */
	EAttribute getRTCMDetail_NewName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getOldName <em>Old Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getOldName()
	 * @see #getRTCMDetail()
	 * @generated
	 */
	EAttribute getRTCMDetail_OldName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RTCMDetail#getMark()
	 * @see #getRTCMDetail()
	 * @generated
	 */
	EAttribute getRTCMDetail_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail <em>Column Change Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column Change Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail
	 * @generated
	 */
	EClass getColumnChangeDetail();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail#getSnapshot <em>Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Snapshot</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail#getSnapshot()
	 * @see #getColumnChangeDetail()
	 * @generated
	 */
	EReference getColumnChangeDetail_Snapshot();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification <em>Change Table Column Type Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Table Column Type Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification
	 * @generated
	 */
	EClass getChangeTableColumnTypeModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification#getDetails()
	 * @see #getChangeTableColumnTypeModification()
	 * @generated
	 */
	EReference getChangeTableColumnTypeModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail <em>CTCTM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CTCTM Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail
	 * @generated
	 */
	EClass getCTCTMDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getName()
	 * @see #getCTCTMDetail()
	 * @generated
	 */
	EAttribute getCTCTMDetail_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getNewType <em>New Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getNewType()
	 * @see #getCTCTMDetail()
	 * @generated
	 */
	EAttribute getCTCTMDetail_NewType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail#getMark()
	 * @see #getCTCTMDetail()
	 * @generated
	 */
	EAttribute getCTCTMDetail_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexModification <em>Add Index Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Index Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexModification
	 * @generated
	 */
	EClass getAddIndexModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexModification#getIndexs <em>Indexs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexs</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexModification#getIndexs()
	 * @see #getAddIndexModification()
	 * @generated
	 */
	EReference getAddIndexModification_Indexs();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification <em>Remove Index Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Index Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification
	 * @generated
	 */
	EClass getRemoveIndexModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification#getIndexs <em>Indexs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexs</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification#getIndexs()
	 * @see #getRemoveIndexModification()
	 * @generated
	 */
	EReference getRemoveIndexModification_Indexs();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification <em>Add Index Field Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Index Field Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification
	 * @generated
	 */
	EClass getAddIndexFieldModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification#getIndexs <em>Indexs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexs</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification#getIndexs()
	 * @see #getAddIndexFieldModification()
	 * @generated
	 */
	EReference getAddIndexFieldModification_Indexs();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification <em>Remove Index Field Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Index Field Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification
	 * @generated
	 */
	EClass getRemoveIndexFieldModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification#getIndexs <em>Indexs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexs</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification#getIndexs()
	 * @see #getRemoveIndexFieldModification()
	 * @generated
	 */
	EReference getRemoveIndexFieldModification_Indexs();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexField <em>Add Index Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Index Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexField
	 * @generated
	 */
	EClass getAddIndexField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getName()
	 * @see #getAddIndexField()
	 * @generated
	 */
	EAttribute getAddIndexField_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getIndexFields <em>Index Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Index Fields</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getIndexFields()
	 * @see #getAddIndexField()
	 * @generated
	 */
	EReference getAddIndexField_IndexFields();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddIndexField#getMark()
	 * @see #getAddIndexField()
	 * @generated
	 */
	EAttribute getAddIndexField_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField <em>Remove Index Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Index Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField
	 * @generated
	 */
	EClass getRemoveIndexField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getName()
	 * @see #getRemoveIndexField()
	 * @generated
	 */
	EAttribute getRemoveIndexField_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getMark()
	 * @see #getRemoveIndexField()
	 * @generated
	 */
	EAttribute getRemoveIndexField_Mark();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getIndexFields <em>Index Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Index Fields</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField#getIndexFields()
	 * @see #getRemoveIndexField()
	 * @generated
	 */
	EReference getRemoveIndexField_IndexFields();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedIndex <em>Removed Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Removed Index</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedIndex
	 * @generated
	 */
	EClass getRemovedIndex();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedIndex#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedIndex#getName()
	 * @see #getRemovedIndex()
	 * @generated
	 */
	EAttribute getRemovedIndex_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.RemovedIndex#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemovedIndex#getMark()
	 * @see #getRemovedIndex()
	 * @generated
	 */
	EAttribute getRemovedIndex_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation <em>Change Table Column Primary Key Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Table Column Primary Key Modifycation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation
	 * @generated
	 */
	EClass getChangeTableColumnPrimaryKeyModifycation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation#getDetails()
	 * @see #getChangeTableColumnPrimaryKeyModifycation()
	 * @generated
	 */
	EReference getChangeTableColumnPrimaryKeyModifycation_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail <em>CTCPM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CTCPM Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail
	 * @generated
	 */
	EClass getCTCPMDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail#isPrimarkKey <em>Primark Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primark Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail#isPrimarkKey()
	 * @see #getCTCPMDetail()
	 * @generated
	 */
	EAttribute getCTCPMDetail_PrimarkKey();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation <em>Change Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Table Column Unique Modifycation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation
	 * @generated
	 */
	EClass getChangeTableColumnUniqueModifycation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation#getDetails()
	 * @see #getChangeTableColumnUniqueModifycation()
	 * @generated
	 */
	EReference getChangeTableColumnUniqueModifycation_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail <em>CTCUM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CTCUM Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail
	 * @generated
	 */
	EClass getCTCUMDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail#isUnique()
	 * @see #getCTCUMDetail()
	 * @generated
	 */
	EAttribute getCTCUMDetail_Unique();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ModifyDetail <em>Modify Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modify Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ModifyDetail
	 * @generated
	 */
	EClass getModifyDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.ModifyDetail#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ModifyDetail#getName()
	 * @see #getModifyDetail()
	 * @generated
	 */
	EAttribute getModifyDetail_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.ModifyDetail#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ModifyDetail#getMark()
	 * @see #getModifyDetail()
	 * @generated
	 */
	EAttribute getModifyDetail_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation <em>Change Table Column Nullable Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Table Column Nullable Modifycation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation
	 * @generated
	 */
	EClass getChangeTableColumnNullableModifycation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation#getDetails()
	 * @see #getChangeTableColumnNullableModifycation()
	 * @generated
	 */
	EReference getChangeTableColumnNullableModifycation_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail <em>CTCNM Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CTCNM Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail
	 * @generated
	 */
	EClass getCTCNMDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail#isNullable()
	 * @see #getCTCNMDetail()
	 * @generated
	 */
	EAttribute getCTCNMDetail_Nullable();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification <em>Add Table Column PK Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Table Column PK Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification
	 * @generated
	 */
	EClass getAddTableColumnPKModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification#getDetails()
	 * @see #getAddTableColumnPKModification()
	 * @generated
	 */
	EReference getAddTableColumnPKModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification <em>Remove Table Column PK Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Table Column PK Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification
	 * @generated
	 */
	EClass getRemoveTableColumnPKModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification#getDetails()
	 * @see #getRemoveTableColumnPKModification()
	 * @generated
	 */
	EReference getRemoveTableColumnPKModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation <em>Add Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Table Column Unique Modifycation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation
	 * @generated
	 */
	EClass getAddTableColumnUniqueModifycation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation#getDetails()
	 * @see #getAddTableColumnUniqueModifycation()
	 * @generated
	 */
	EReference getAddTableColumnUniqueModifycation_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation <em>Remove Table Column Unique Modifycation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Table Column Unique Modifycation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation
	 * @generated
	 */
	EClass getRemoveTableColumnUniqueModifycation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation#getDetails()
	 * @see #getRemoveTableColumnUniqueModifycation()
	 * @generated
	 */
	EReference getRemoveTableColumnUniqueModifycation_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification <em>Add Constraint Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Constraint Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification
	 * @generated
	 */
	EClass getAddConstraintModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification#getDetails()
	 * @see #getAddConstraintModification()
	 * @generated
	 */
	EReference getAddConstraintModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification <em>Remove Constraint Modification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remove Constraint Modification</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification
	 * @generated
	 */
	EClass getRemoveConstraintModification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Details</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification#getDetails()
	 * @see #getRemoveConstraintModification()
	 * @generated
	 */
	EReference getRemoveConstraintModification_Details();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail <em>Constraint Modify Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Modify Detail</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail
	 * @generated
	 */
	EClass getConstraintModifyDetail();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getMark()
	 * @see #getConstraintModifyDetail()
	 * @generated
	 */
	EAttribute getConstraintModifyDetail_Mark();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getName()
	 * @see #getConstraintModifyDetail()
	 * @generated
	 */
	EAttribute getConstraintModifyDetail_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getColumns()
	 * @see #getConstraintModifyDetail()
	 * @generated
	 */
	EReference getConstraintModifyDetail_Columns();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getType()
	 * @see #getConstraintModifyDetail()
	 * @generated
	 */
	EAttribute getConstraintModifyDetail_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Foreign Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail#getForeignKey()
	 * @see #getConstraintModifyDetail()
	 * @generated
	 */
	EReference getConstraintModifyDetail_ForeignKey();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ChouseFactory getChouseFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl <em>Table Space Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableSpaceProperty()
		 * @generated
		 */
		EClass TABLE_SPACE_PROPERTY = eINSTANCE.getTableSpaceProperty();

		/**
		 * The meta object literal for the '<em><b>Redu Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_PROPERTY__REDU_TABLE = eINSTANCE.getTableSpaceProperty_ReduTable();

		/**
		 * The meta object literal for the '<em><b>Chear Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_PROPERTY__CHEAR_TABLE = eINSTANCE.getTableSpaceProperty_ChearTable();

		/**
		 * The meta object literal for the '<em><b>Chear Table Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX = eINSTANCE.getTableSpaceProperty_ChearTableIndex();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl <em>Table Space Relation Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableSpaceRelationPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableSpaceRelationProperty()
		 * @generated
		 */
		EClass TABLE_SPACE_RELATION_PROPERTY = eINSTANCE.getTableSpaceRelationProperty();

		/**
		 * The meta object literal for the '<em><b>His Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE = eINSTANCE.getTableSpaceRelationProperty_HisSpace();

		/**
		 * The meta object literal for the '<em><b>His Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE = eINSTANCE.getTableSpaceRelationProperty_HisIndexSpace();

		/**
		 * The meta object literal for the '<em><b>File Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE = eINSTANCE.getTableSpaceRelationProperty_FileSpace();

		/**
		 * The meta object literal for the '<em><b>File Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE = eINSTANCE.getTableSpaceRelationProperty_FileIndexSpace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl <em>Table Base Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.TableBasePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getTableBaseProperty()
		 * @generated
		 */
		EClass TABLE_BASE_PROPERTY = eINSTANCE.getTableBaseProperty();

		/**
		 * The meta object literal for the '<em><b>Chear</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__CHEAR = eINSTANCE.getTableBaseProperty_Chear();

		/**
		 * The meta object literal for the '<em><b>Redu</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__REDU = eINSTANCE.getTableBaseProperty_Redu();

		/**
		 * The meta object literal for the '<em><b>History</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__HISTORY = eINSTANCE.getTableBaseProperty_History();

		/**
		 * The meta object literal for the '<em><b>History Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__HISTORY_SPACE = eINSTANCE.getTableBaseProperty_HistorySpace();

		/**
		 * The meta object literal for the '<em><b>History Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__HISTORY_INDEX_SPACE = eINSTANCE.getTableBaseProperty_HistoryIndexSpace();

		/**
		 * The meta object literal for the '<em><b>Object ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__OBJECT_ID = eINSTANCE.getTableBaseProperty_ObjectID();

		/**
		 * The meta object literal for the '<em><b>Split Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__SPLIT_FIELD = eINSTANCE.getTableBaseProperty_SplitField();

		/**
		 * The meta object literal for the '<em><b>Split Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__SPLIT_NUM = eINSTANCE.getTableBaseProperty_SplitNum();

		/**
		 * The meta object literal for the '<em><b>Start Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__START_DATA = eINSTANCE.getTableBaseProperty_StartData();

		/**
		 * The meta object literal for the '<em><b>User Split</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__USER_SPLIT = eINSTANCE.getTableBaseProperty_UserSplit();

		/**
		 * The meta object literal for the '<em><b>Is Redu</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__IS_REDU = eINSTANCE.getTableBaseProperty_IsRedu();

		/**
		 * The meta object literal for the '<em><b>Is Clear</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__IS_CLEAR = eINSTANCE.getTableBaseProperty_IsClear();

		/**
		 * The meta object literal for the '<em><b>File Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__FILE_SPACE = eINSTANCE.getTableBaseProperty_FileSpace();

		/**
		 * The meta object literal for the '<em><b>File Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__FILE_INDEX_SPACE = eINSTANCE.getTableBaseProperty_FileIndexSpace();

		/**
		 * The meta object literal for the '<em><b>Clear Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_BASE_PROPERTY__CLEAR_INDEX_SPACE = eINSTANCE.getTableBaseProperty_ClearIndexSpace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.HistoryPropertyImpl <em>History Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.HistoryPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getHistoryProperty()
		 * @generated
		 */
		EClass HISTORY_PROPERTY = eINSTANCE.getHistoryProperty();

		/**
		 * The meta object literal for the '<em><b>Internal Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_PROPERTY__INTERNAL_VERSION = eINSTANCE.getHistoryProperty_InternalVersion();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl <em>Revision History Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RevisionHistoryPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRevisionHistoryProperty()
		 * @generated
		 */
		EClass REVISION_HISTORY_PROPERTY = eINSTANCE.getRevisionHistoryProperty();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REVISION_HISTORY_PROPERTY__ACTION = eINSTANCE.getRevisionHistoryProperty_Action();

		/**
		 * The meta object literal for the '<em><b>Action Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY_PROPERTY__ACTION_TYPE = eINSTANCE.getRevisionHistoryProperty_ActionType();

		/**
		 * The meta object literal for the '<em><b>Action Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY_PROPERTY__ACTION_DESCRIPTION = eINSTANCE.getRevisionHistoryProperty_ActionDescription();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl <em>Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getModification()
		 * @generated
		 */
		EClass MODIFICATION = eINSTANCE.getModification();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTablePropertyImpl <em>Stock Table Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockTablePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockTableProperty()
		 * @generated
		 */
		EClass STOCK_TABLE_PROPERTY = eINSTANCE.getStockTableProperty();

		/**
		 * The meta object literal for the '<em><b>History</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_TABLE_PROPERTY__HISTORY = eINSTANCE.getStockTableProperty_History();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockColumnPropertyImpl <em>Stock Column Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockColumnPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockColumnProperty()
		 * @generated
		 */
		EClass STOCK_COLUMN_PROPERTY = eINSTANCE.getStockColumnProperty();

		/**
		 * The meta object literal for the '<em><b>Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_COLUMN_PROPERTY__FLAG = eINSTANCE.getStockColumnProperty_Flag();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockIndexPropertyImpl <em>Stock Index Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockIndexPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockIndexProperty()
		 * @generated
		 */
		EClass STOCK_INDEX_PROPERTY = eINSTANCE.getStockIndexProperty();

		/**
		 * The meta object literal for the '<em><b>Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_INDEX_PROPERTY__FLAG = eINSTANCE.getStockIndexProperty_Flag();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl <em>Stock TS Relation Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockTSRelationProperty()
		 * @generated
		 */
		EClass STOCK_TS_RELATION_PROPERTY = eINSTANCE.getStockTSRelationProperty();

		/**
		 * The meta object literal for the '<em><b>His Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_TS_RELATION_PROPERTY__HIS_SPACE = eINSTANCE.getStockTSRelationProperty_HisSpace();

		/**
		 * The meta object literal for the '<em><b>His Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE = eINSTANCE.getStockTSRelationProperty_HisIndexSpace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockProjectPropertyImpl <em>Stock Project Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockProjectPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockProjectProperty()
		 * @generated
		 */
		EClass STOCK_PROJECT_PROPERTY = eINSTANCE.getStockProjectProperty();

		/**
		 * The meta object literal for the '<em><b>Base Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_PROJECT_PROPERTY__BASE_VERSION = eINSTANCE.getStockProjectProperty_BaseVersion();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl <em>Stock DB Context Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getStockDBContextProperty()
		 * @generated
		 */
		EClass STOCK_DB_CONTEXT_PROPERTY = eINSTANCE.getStockDBContextProperty();

		/**
		 * The meta object literal for the '<em><b>Start Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_DB_CONTEXT_PROPERTY__START_VERSION = eINSTANCE.getStockDBContextProperty_StartVersion();

		/**
		 * The meta object literal for the '<em><b>End Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STOCK_DB_CONTEXT_PROPERTY__END_VERSION = eINSTANCE.getStockDBContextProperty_EndVersion();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl <em>Add Table Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableModification()
		 * @generated
		 */
		EClass ADD_TABLE_MODIFICATION = eINSTANCE.getAddTableModification();

		/**
		 * The meta object literal for the '<em><b>New Self Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_TABLE_MODIFICATION__NEW_SELF_TABLE = eINSTANCE.getAddTableModification_NewSelfTable();

		/**
		 * The meta object literal for the '<em><b>New History Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE = eINSTANCE.getAddTableModification_NewHistoryTable();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_MODIFICATION__COLUMNS = eINSTANCE.getAddTableModification_Columns();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_MODIFICATION__INDEXES = eINSTANCE.getAddTableModification_Indexes();

		/**
		 * The meta object literal for the '<em><b>Keys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_MODIFICATION__KEYS = eINSTANCE.getAddTableModification_Keys();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.HisTableColumnImpl <em>His Table Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.HisTableColumnImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getHisTableColumn()
		 * @generated
		 */
		EClass HIS_TABLE_COLUMN = eINSTANCE.getHisTableColumn();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnModificationImpl <em>Add Table Column Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnModification()
		 * @generated
		 */
		EClass ADD_TABLE_COLUMN_MODIFICATION = eINSTANCE.getAddTableColumnModification();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_COLUMN_MODIFICATION__COLUMNS = eINSTANCE.getAddTableColumnModification_Columns();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnModificationImpl <em>Remove Table Column Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnModification()
		 * @generated
		 */
		EClass REMOVE_TABLE_COLUMN_MODIFICATION = eINSTANCE.getRemoveTableColumnModification();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_TABLE_COLUMN_MODIFICATION__COLUMNS = eINSTANCE.getRemoveTableColumnModification_Columns();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemovedTableColumnImpl <em>Removed Table Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemovedTableColumnImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemovedTableColumn()
		 * @generated
		 */
		EClass REMOVED_TABLE_COLUMN = eINSTANCE.getRemovedTableColumn();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RenameTableColumnModificationImpl <em>Rename Table Column Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RenameTableColumnModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRenameTableColumnModification()
		 * @generated
		 */
		EClass RENAME_TABLE_COLUMN_MODIFICATION = eINSTANCE.getRenameTableColumnModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENAME_TABLE_COLUMN_MODIFICATION__DETAILS = eINSTANCE.getRenameTableColumnModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl <em>RTCM Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRTCMDetail()
		 * @generated
		 */
		EClass RTCM_DETAIL = eINSTANCE.getRTCMDetail();

		/**
		 * The meta object literal for the '<em><b>New Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTCM_DETAIL__NEW_NAME = eINSTANCE.getRTCMDetail_NewName();

		/**
		 * The meta object literal for the '<em><b>Old Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTCM_DETAIL__OLD_NAME = eINSTANCE.getRTCMDetail_OldName();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTCM_DETAIL__MARK = eINSTANCE.getRTCMDetail_Mark();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ColumnChangeDetailImpl <em>Column Change Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ColumnChangeDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getColumnChangeDetail()
		 * @generated
		 */
		EClass COLUMN_CHANGE_DETAIL = eINSTANCE.getColumnChangeDetail();

		/**
		 * The meta object literal for the '<em><b>Snapshot</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN_CHANGE_DETAIL__SNAPSHOT = eINSTANCE.getColumnChangeDetail_Snapshot();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl <em>Change Table Column Type Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnTypeModification()
		 * @generated
		 */
		EClass CHANGE_TABLE_COLUMN_TYPE_MODIFICATION = eINSTANCE.getChangeTableColumnTypeModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS = eINSTANCE.getChangeTableColumnTypeModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl <em>CTCTM Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCTMDetail()
		 * @generated
		 */
		EClass CTCTM_DETAIL = eINSTANCE.getCTCTMDetail();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCTM_DETAIL__NAME = eINSTANCE.getCTCTMDetail_Name();

		/**
		 * The meta object literal for the '<em><b>New Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCTM_DETAIL__NEW_TYPE = eINSTANCE.getCTCTMDetail_NewType();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCTM_DETAIL__MARK = eINSTANCE.getCTCTMDetail_Mark();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexModificationImpl <em>Add Index Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexModification()
		 * @generated
		 */
		EClass ADD_INDEX_MODIFICATION = eINSTANCE.getAddIndexModification();

		/**
		 * The meta object literal for the '<em><b>Indexs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_INDEX_MODIFICATION__INDEXS = eINSTANCE.getAddIndexModification_Indexs();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexModificationImpl <em>Remove Index Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexModification()
		 * @generated
		 */
		EClass REMOVE_INDEX_MODIFICATION = eINSTANCE.getRemoveIndexModification();

		/**
		 * The meta object literal for the '<em><b>Indexs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_INDEX_MODIFICATION__INDEXS = eINSTANCE.getRemoveIndexModification_Indexs();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl <em>Add Index Field Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexFieldModification()
		 * @generated
		 */
		EClass ADD_INDEX_FIELD_MODIFICATION = eINSTANCE.getAddIndexFieldModification();

		/**
		 * The meta object literal for the '<em><b>Indexs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_INDEX_FIELD_MODIFICATION__INDEXS = eINSTANCE.getAddIndexFieldModification_Indexs();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldModificationImpl <em>Remove Index Field Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexFieldModification()
		 * @generated
		 */
		EClass REMOVE_INDEX_FIELD_MODIFICATION = eINSTANCE.getRemoveIndexFieldModification();

		/**
		 * The meta object literal for the '<em><b>Indexs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_INDEX_FIELD_MODIFICATION__INDEXS = eINSTANCE.getRemoveIndexFieldModification_Indexs();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldImpl <em>Add Index Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddIndexField()
		 * @generated
		 */
		EClass ADD_INDEX_FIELD = eINSTANCE.getAddIndexField();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_INDEX_FIELD__NAME = eINSTANCE.getAddIndexField_Name();

		/**
		 * The meta object literal for the '<em><b>Index Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_INDEX_FIELD__INDEX_FIELDS = eINSTANCE.getAddIndexField_IndexFields();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_INDEX_FIELD__MARK = eINSTANCE.getAddIndexField_Mark();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldImpl <em>Remove Index Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveIndexField()
		 * @generated
		 */
		EClass REMOVE_INDEX_FIELD = eINSTANCE.getRemoveIndexField();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOVE_INDEX_FIELD__NAME = eINSTANCE.getRemoveIndexField_Name();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOVE_INDEX_FIELD__MARK = eINSTANCE.getRemoveIndexField_Mark();

		/**
		 * The meta object literal for the '<em><b>Index Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_INDEX_FIELD__INDEX_FIELDS = eINSTANCE.getRemoveIndexField_IndexFields();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemovedIndexImpl <em>Removed Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemovedIndexImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemovedIndex()
		 * @generated
		 */
		EClass REMOVED_INDEX = eINSTANCE.getRemovedIndex();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOVED_INDEX__NAME = eINSTANCE.getRemovedIndex_Name();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOVED_INDEX__MARK = eINSTANCE.getRemovedIndex_Mark();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnPrimaryKeyModifycationImpl <em>Change Table Column Primary Key Modifycation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnPrimaryKeyModifycationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnPrimaryKeyModifycation()
		 * @generated
		 */
		EClass CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION = eINSTANCE.getChangeTableColumnPrimaryKeyModifycation();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DETAILS = eINSTANCE.getChangeTableColumnPrimaryKeyModifycation_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCPMDetailImpl <em>CTCPM Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCPMDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCPMDetail()
		 * @generated
		 */
		EClass CTCPM_DETAIL = eINSTANCE.getCTCPMDetail();

		/**
		 * The meta object literal for the '<em><b>Primark Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCPM_DETAIL__PRIMARK_KEY = eINSTANCE.getCTCPMDetail_PrimarkKey();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnUniqueModifycationImpl <em>Change Table Column Unique Modifycation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnUniqueModifycationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnUniqueModifycation()
		 * @generated
		 */
		EClass CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION = eINSTANCE.getChangeTableColumnUniqueModifycation();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = eINSTANCE.getChangeTableColumnUniqueModifycation_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCUMDetailImpl <em>CTCUM Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCUMDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCUMDetail()
		 * @generated
		 */
		EClass CTCUM_DETAIL = eINSTANCE.getCTCUMDetail();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCUM_DETAIL__UNIQUE = eINSTANCE.getCTCUMDetail_Unique();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl <em>Modify Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getModifyDetail()
		 * @generated
		 */
		EClass MODIFY_DETAIL = eINSTANCE.getModifyDetail();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFY_DETAIL__NAME = eINSTANCE.getModifyDetail_Name();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFY_DETAIL__MARK = eINSTANCE.getModifyDetail_Mark();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnNullableModifycationImpl <em>Change Table Column Nullable Modifycation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnNullableModifycationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getChangeTableColumnNullableModifycation()
		 * @generated
		 */
		EClass CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION = eINSTANCE.getChangeTableColumnNullableModifycation();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DETAILS = eINSTANCE.getChangeTableColumnNullableModifycation_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCNMDetailImpl <em>CTCNM Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.CTCNMDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getCTCNMDetail()
		 * @generated
		 */
		EClass CTCNM_DETAIL = eINSTANCE.getCTCNMDetail();

		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CTCNM_DETAIL__NULLABLE = eINSTANCE.getCTCNMDetail_Nullable();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnPKModificationImpl <em>Add Table Column PK Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnPKModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnPKModification()
		 * @generated
		 */
		EClass ADD_TABLE_COLUMN_PK_MODIFICATION = eINSTANCE.getAddTableColumnPKModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_COLUMN_PK_MODIFICATION__DETAILS = eINSTANCE.getAddTableColumnPKModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnPKModificationImpl <em>Remove Table Column PK Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnPKModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnPKModification()
		 * @generated
		 */
		EClass REMOVE_TABLE_COLUMN_PK_MODIFICATION = eINSTANCE.getRemoveTableColumnPKModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_TABLE_COLUMN_PK_MODIFICATION__DETAILS = eINSTANCE.getRemoveTableColumnPKModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnUniqueModifycationImpl <em>Add Table Column Unique Modifycation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddTableColumnUniqueModifycationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddTableColumnUniqueModifycation()
		 * @generated
		 */
		EClass ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION = eINSTANCE.getAddTableColumnUniqueModifycation();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = eINSTANCE.getAddTableColumnUniqueModifycation_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnUniqueModifycationImpl <em>Remove Table Column Unique Modifycation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnUniqueModifycationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveTableColumnUniqueModifycation()
		 * @generated
		 */
		EClass REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION = eINSTANCE.getRemoveTableColumnUniqueModifycation();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS = eINSTANCE.getRemoveTableColumnUniqueModifycation_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddConstraintModificationImpl <em>Add Constraint Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.AddConstraintModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getAddConstraintModification()
		 * @generated
		 */
		EClass ADD_CONSTRAINT_MODIFICATION = eINSTANCE.getAddConstraintModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_CONSTRAINT_MODIFICATION__DETAILS = eINSTANCE.getAddConstraintModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveConstraintModificationImpl <em>Remove Constraint Modification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.RemoveConstraintModificationImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getRemoveConstraintModification()
		 * @generated
		 */
		EClass REMOVE_CONSTRAINT_MODIFICATION = eINSTANCE.getRemoveConstraintModification();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOVE_CONSTRAINT_MODIFICATION__DETAILS = eINSTANCE.getRemoveConstraintModification_Details();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl <em>Constraint Modify Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ConstraintModifyDetailImpl
		 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ChousePackageImpl#getConstraintModifyDetail()
		 * @generated
		 */
		EClass CONSTRAINT_MODIFY_DETAIL = eINSTANCE.getConstraintModifyDetail();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_MODIFY_DETAIL__MARK = eINSTANCE.getConstraintModifyDetail_Mark();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_MODIFY_DETAIL__NAME = eINSTANCE.getConstraintModifyDetail_Name();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_MODIFY_DETAIL__COLUMNS = eINSTANCE.getConstraintModifyDetail_Columns();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_MODIFY_DETAIL__TYPE = eINSTANCE.getConstraintModifyDetail_Type();

		/**
		 * The meta object literal for the '<em><b>Foreign Key</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY = eINSTANCE.getConstraintModifyDetail_ForeignKey();

	}

} //ChousePackage
