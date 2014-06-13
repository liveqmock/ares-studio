/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.hundsun.ares.studio.jres.model.database.DatabaseFactory
 * @model kind="package"
 * @generated
 */
public interface DatabasePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "database";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/database/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "database";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatabasePackage eINSTANCE = com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl <em>DB Module Common Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDBModuleCommonProperty()
	 * @generated
	 */
	int DB_MODULE_COMMON_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE_COMMON_PROPERTY__DATABASE = 0;

	/**
	 * The feature id for the '<em><b>Support Databases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES = 1;

	/**
	 * The number of structural features of the '<em>DB Module Common Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE_COMMON_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl <em>Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDatabaseResourceData()
	 * @generated
	 */
	int DATABASE_RESOURCE_DATA = 1;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__DATA = CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__DATA2 = CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__NAME = CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__CHINESE_NAME = CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__DESCRIPTION = CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__OBJECT_ID = CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__HISTORIES = CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME = CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The number of structural features of the '<em>Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_RESOURCE_DATA_FEATURE_COUNT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl <em>Table Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableResourceData()
	 * @generated
	 */
	int TABLE_RESOURCE_DATA = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__DATA = DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__DATA2 = DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__NAME = DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__CHINESE_NAME = DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__DESCRIPTION = DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__OBJECT_ID = DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__HISTORIES = DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__COLUMNS = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__INDEXES = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Keys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA__KEYS = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Table Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_RESOURCE_DATA_FEATURE_COUNT = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl <em>Table Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableColumn()
	 * @generated
	 */
	int TABLE_COLUMN = 3;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__CHINESE_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__DATA_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__COLUMN_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__FIELD_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__PRIMARY_KEY = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__UNIQUE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__NULLABLE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__DEFAULT_VALUE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Foreignkey</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__FOREIGNKEY = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__MARK = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__COMMENTS = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN__COLUMN_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Table Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_COLUMN_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl <em>Table Index Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableIndexColumn()
	 * @generated
	 */
	int TABLE_INDEX_COLUMN = 4;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Column Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN__COLUMN_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ascending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN__ASCENDING = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN__COLUMN_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Table Index Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_COLUMN_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl <em>Table Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableIndex()
	 * @generated
	 */
	int TABLE_INDEX = 5;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__UNIQUE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cluster</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__CLUSTER = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__COLUMNS = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX__MARK = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Table Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_INDEX_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl <em>View Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getViewResourceData()
	 * @generated
	 */
	int VIEW_RESOURCE_DATA = 6;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__DATA = DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__DATA2 = DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__NAME = DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__CHINESE_NAME = DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__DESCRIPTION = DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__OBJECT_ID = DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__HISTORIES = DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Sql</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__SQL = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA__IS_HISTORY = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>View Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_RESOURCE_DATA_FEATURE_COUNT = DATABASE_RESOURCE_DATA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DBGenContextImpl <em>DB Gen Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DBGenContextImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDBGenContext()
	 * @generated
	 */
	int DB_GEN_CONTEXT = 7;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_GEN_CONTEXT__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_GEN_CONTEXT__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The number of structural features of the '<em>DB Gen Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_GEN_CONTEXT_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.ForeignKeyImpl <em>Foreign Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.ForeignKeyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getForeignKey()
	 * @generated
	 */
	int FOREIGN_KEY = 8;

	/**
	 * The feature id for the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__TABLE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__FIELD_NAME = 1;

	/**
	 * The number of structural features of the '<em>Foreign Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableKeyImpl <em>Table Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.impl.TableKeyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableKey()
	 * @generated
	 */
	int TABLE_KEY = 9;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__FOREIGN_KEY = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__COLUMNS = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY__MARK = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Table Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_KEY_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.key_type <em>key type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getkey_type()
	 * @generated
	 */
	int KEY_TYPE = 10;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.ColumnType <em>Column Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getColumnType()
	 * @generated
	 */
	int COLUMN_TYPE = 11;

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty <em>DB Module Common Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Module Common Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty
	 * @generated
	 */
	EClass getDBModuleCommonProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getDatabase()
	 * @see #getDBModuleCommonProperty()
	 * @generated
	 */
	EAttribute getDBModuleCommonProperty_Database();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getSupportDatabases <em>Support Databases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Support Databases</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty#getSupportDatabases()
	 * @see #getDBModuleCommonProperty()
	 * @generated
	 */
	EAttribute getDBModuleCommonProperty_SupportDatabases();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.DatabaseResourceData <em>Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.DatabaseResourceData
	 * @generated
	 */
	EClass getDatabaseResourceData();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.TableResourceData <em>Table Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableResourceData
	 * @generated
	 */
	EClass getTableResourceData();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableResourceData#getColumns()
	 * @see #getTableResourceData()
	 * @generated
	 */
	EReference getTableResourceData_Columns();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableResourceData#getIndexes()
	 * @see #getTableResourceData()
	 * @generated
	 */
	EReference getTableResourceData_Indexes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableResourceData#getKeys <em>Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Keys</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableResourceData#getKeys()
	 * @see #getTableResourceData()
	 * @generated
	 */
	EReference getTableResourceData_Keys();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.TableColumn <em>Table Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Column</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn
	 * @generated
	 */
	EClass getTableColumn();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getName()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getChineseName()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getDescription()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getDataType()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnName <em>Column Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnName()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_ColumnName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getFieldName()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_FieldName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isPrimaryKey <em>Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#isPrimaryKey()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_PrimaryKey();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#isUnique()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Unique();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#isNullable()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Nullable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getDefaultValue()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_DefaultValue();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getForeignkey <em>Foreignkey</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Foreignkey</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getForeignkey()
	 * @see #getTableColumn()
	 * @generated
	 */
	EReference getTableColumn_Foreignkey();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getMark()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Mark();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comments</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getComments()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_Comments();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnType <em>Column Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableColumn#getColumnType()
	 * @see #getTableColumn()
	 * @generated
	 */
	EAttribute getTableColumn_ColumnType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn <em>Table Index Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Index Column</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndexColumn
	 * @generated
	 */
	EClass getTableIndexColumn();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnName <em>Column Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnName()
	 * @see #getTableIndexColumn()
	 * @generated
	 */
	EAttribute getTableIndexColumn_ColumnName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#isAscending <em>Ascending</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ascending</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndexColumn#isAscending()
	 * @see #getTableIndexColumn()
	 * @generated
	 */
	EAttribute getTableIndexColumn_Ascending();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnType <em>Column Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndexColumn#getColumnType()
	 * @see #getTableIndexColumn()
	 * @generated
	 */
	EAttribute getTableIndexColumn_ColumnType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.TableIndex <em>Table Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Index</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex
	 * @generated
	 */
	EClass getTableIndex();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndex#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex#getName()
	 * @see #getTableIndex()
	 * @generated
	 */
	EAttribute getTableIndex_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndex#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex#isUnique()
	 * @see #getTableIndex()
	 * @generated
	 */
	EAttribute getTableIndex_Unique();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndex#isCluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cluster</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex#isCluster()
	 * @see #getTableIndex()
	 * @generated
	 */
	EAttribute getTableIndex_Cluster();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableIndex#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex#getColumns()
	 * @see #getTableIndex()
	 * @generated
	 */
	EReference getTableIndex_Columns();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableIndex#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableIndex#getMark()
	 * @see #getTableIndex()
	 * @generated
	 */
	EAttribute getTableIndex_Mark();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData <em>View Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ViewResourceData
	 * @generated
	 */
	EClass getViewResourceData();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#getSql <em>Sql</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ViewResourceData#getSql()
	 * @see #getViewResourceData()
	 * @generated
	 */
	EAttribute getViewResourceData_Sql();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.ViewResourceData#isIsHistory <em>Is History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is History</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ViewResourceData#isIsHistory()
	 * @see #getViewResourceData()
	 * @generated
	 */
	EAttribute getViewResourceData_IsHistory();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.DBGenContext <em>DB Gen Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Gen Context</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.DBGenContext
	 * @generated
	 */
	EClass getDBGenContext();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.ForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Foreign Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ForeignKey
	 * @generated
	 */
	EClass getForeignKey();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.ForeignKey#getTableName <em>Table Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ForeignKey#getTableName()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_TableName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.ForeignKey#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ForeignKey#getFieldName()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_FieldName();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.TableKey <em>Table Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey
	 * @generated
	 */
	EClass getTableKey();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey#getName()
	 * @see #getTableKey()
	 * @generated
	 */
	EAttribute getTableKey_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey#getType()
	 * @see #getTableKey()
	 * @generated
	 */
	EAttribute getTableKey_Type();

	/**
	 * Returns the meta object for the reference list '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Columns</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey#getColumns()
	 * @see #getTableKey()
	 * @generated
	 */
	EReference getTableKey_Columns();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey#getMark()
	 * @see #getTableKey()
	 * @generated
	 */
	EAttribute getTableKey_Mark();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.TableKey#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Foreign Key</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.TableKey#getForeignKey()
	 * @see #getTableKey()
	 * @generated
	 */
	EReference getTableKey_ForeignKey();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.jres.model.database.key_type <em>key type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>key type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.key_type
	 * @generated
	 */
	EEnum getkey_type();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.jres.model.database.ColumnType <em>Column Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Column Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
	 * @generated
	 */
	EEnum getColumnType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DatabaseFactory getDatabaseFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl <em>DB Module Common Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDBModuleCommonProperty()
		 * @generated
		 */
		EClass DB_MODULE_COMMON_PROPERTY = eINSTANCE.getDBModuleCommonProperty();
		/**
		 * The meta object literal for the '<em><b>Database</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_MODULE_COMMON_PROPERTY__DATABASE = eINSTANCE.getDBModuleCommonProperty_Database();
		/**
		 * The meta object literal for the '<em><b>Support Databases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES = eINSTANCE.getDBModuleCommonProperty_SupportDatabases();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl <em>Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDatabaseResourceData()
		 * @generated
		 */
		EClass DATABASE_RESOURCE_DATA = eINSTANCE.getDatabaseResourceData();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl <em>Table Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.TableResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableResourceData()
		 * @generated
		 */
		EClass TABLE_RESOURCE_DATA = eINSTANCE.getTableResourceData();
		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_RESOURCE_DATA__COLUMNS = eINSTANCE.getTableResourceData_Columns();
		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_RESOURCE_DATA__INDEXES = eINSTANCE.getTableResourceData_Indexes();
		/**
		 * The meta object literal for the '<em><b>Keys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_RESOURCE_DATA__KEYS = eINSTANCE.getTableResourceData_Keys();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl <em>Table Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.TableColumnImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableColumn()
		 * @generated
		 */
		EClass TABLE_COLUMN = eINSTANCE.getTableColumn();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__NAME = eINSTANCE.getTableColumn_Name();
		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__CHINESE_NAME = eINSTANCE.getTableColumn_ChineseName();
		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__DESCRIPTION = eINSTANCE.getTableColumn_Description();
		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__DATA_TYPE = eINSTANCE.getTableColumn_DataType();
		/**
		 * The meta object literal for the '<em><b>Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__COLUMN_NAME = eINSTANCE.getTableColumn_ColumnName();
		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__FIELD_NAME = eINSTANCE.getTableColumn_FieldName();
		/**
		 * The meta object literal for the '<em><b>Primary Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__PRIMARY_KEY = eINSTANCE.getTableColumn_PrimaryKey();
		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__UNIQUE = eINSTANCE.getTableColumn_Unique();
		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__NULLABLE = eINSTANCE.getTableColumn_Nullable();
		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__DEFAULT_VALUE = eINSTANCE.getTableColumn_DefaultValue();
		/**
		 * The meta object literal for the '<em><b>Foreignkey</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_COLUMN__FOREIGNKEY = eINSTANCE.getTableColumn_Foreignkey();
		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__MARK = eINSTANCE.getTableColumn_Mark();
		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__COMMENTS = eINSTANCE.getTableColumn_Comments();
		/**
		 * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_COLUMN__COLUMN_TYPE = eINSTANCE.getTableColumn_ColumnType();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl <em>Table Index Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableIndexColumn()
		 * @generated
		 */
		EClass TABLE_INDEX_COLUMN = eINSTANCE.getTableIndexColumn();
		/**
		 * The meta object literal for the '<em><b>Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX_COLUMN__COLUMN_NAME = eINSTANCE.getTableIndexColumn_ColumnName();
		/**
		 * The meta object literal for the '<em><b>Ascending</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX_COLUMN__ASCENDING = eINSTANCE.getTableIndexColumn_Ascending();
		/**
		 * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX_COLUMN__COLUMN_TYPE = eINSTANCE.getTableIndexColumn_ColumnType();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl <em>Table Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.TableIndexImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableIndex()
		 * @generated
		 */
		EClass TABLE_INDEX = eINSTANCE.getTableIndex();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX__NAME = eINSTANCE.getTableIndex_Name();
		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX__UNIQUE = eINSTANCE.getTableIndex_Unique();
		/**
		 * The meta object literal for the '<em><b>Cluster</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX__CLUSTER = eINSTANCE.getTableIndex_Cluster();
		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_INDEX__COLUMNS = eINSTANCE.getTableIndex_Columns();
		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_INDEX__MARK = eINSTANCE.getTableIndex_Mark();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl <em>View Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getViewResourceData()
		 * @generated
		 */
		EClass VIEW_RESOURCE_DATA = eINSTANCE.getViewResourceData();
		/**
		 * The meta object literal for the '<em><b>Sql</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_RESOURCE_DATA__SQL = eINSTANCE.getViewResourceData_Sql();
		/**
		 * The meta object literal for the '<em><b>Is History</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_RESOURCE_DATA__IS_HISTORY = eINSTANCE.getViewResourceData_IsHistory();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.DBGenContextImpl <em>DB Gen Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DBGenContextImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getDBGenContext()
		 * @generated
		 */
		EClass DB_GEN_CONTEXT = eINSTANCE.getDBGenContext();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.ForeignKeyImpl <em>Foreign Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.ForeignKeyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getForeignKey()
		 * @generated
		 */
		EClass FOREIGN_KEY = eINSTANCE.getForeignKey();
		/**
		 * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOREIGN_KEY__TABLE_NAME = eINSTANCE.getForeignKey_TableName();
		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOREIGN_KEY__FIELD_NAME = eINSTANCE.getForeignKey_FieldName();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.impl.TableKeyImpl <em>Table Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.impl.TableKeyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getTableKey()
		 * @generated
		 */
		EClass TABLE_KEY = eINSTANCE.getTableKey();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_KEY__NAME = eINSTANCE.getTableKey_Name();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_KEY__TYPE = eINSTANCE.getTableKey_Type();
		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_KEY__COLUMNS = eINSTANCE.getTableKey_Columns();
		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_KEY__MARK = eINSTANCE.getTableKey_Mark();
		/**
		 * The meta object literal for the '<em><b>Foreign Key</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_KEY__FOREIGN_KEY = eINSTANCE.getTableKey_ForeignKey();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.key_type <em>key type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.key_type
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getkey_type()
		 * @generated
		 */
		EEnum KEY_TYPE = eINSTANCE.getkey_type();
		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.ColumnType <em>Column Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.ColumnType
		 * @see com.hundsun.ares.studio.jres.model.database.impl.DatabasePackageImpl#getColumnType()
		 * @generated
		 */
		EEnum COLUMN_TYPE = eINSTANCE.getColumnType();

	}

} //DatabasePackage
