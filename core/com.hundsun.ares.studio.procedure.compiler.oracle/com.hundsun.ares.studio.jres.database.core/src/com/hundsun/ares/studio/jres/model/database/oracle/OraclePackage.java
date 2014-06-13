/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory
 * @model kind="package"
 * @generated
 */
public interface OraclePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "oracle";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/database/oracle/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "oracle";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OraclePackage eINSTANCE = com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl <em>Table Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleTableProperty()
	 * @generated
	 */
	int ORACLE_TABLE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_TABLE_PROPERTY__SPACE = 0;

	/**
	 * The feature id for the '<em><b>Tabletype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_TABLE_PROPERTY__TABLETYPE = 1;

	/**
	 * The number of structural features of the '<em>Table Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_TABLE_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleIndexPropertyImpl <em>Index Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleIndexPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleIndexProperty()
	 * @generated
	 */
	int ORACLE_INDEX_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_INDEX_PROPERTY__REVERSE = 0;

	/**
	 * The number of structural features of the '<em>Index Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_INDEX_PROPERTY_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleViewPropertyImpl <em>View Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleViewPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleViewProperty()
	 * @generated
	 */
	int ORACLE_VIEW_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_VIEW_PROPERTY__SPACE = 0;

	/**
	 * The number of structural features of the '<em>View Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_VIEW_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleModulePropertyImpl <em>Module Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleModulePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleModuleProperty()
	 * @generated
	 */
	int ORACLE_MODULE_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_MODULE_PROPERTY__SPACE = 0;

	/**
	 * The number of structural features of the '<em>Module Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_MODULE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl <em>Space Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleSpaceResourceData()
	 * @generated
	 */
	int ORACLE_SPACE_RESOURCE_DATA = 4;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__DATA = DatabasePackage.DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__DATA2 = DatabasePackage.DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__NAME = DatabasePackage.DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__CHINESE_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__DESCRIPTION = DatabasePackage.DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__OBJECT_ID = DatabasePackage.DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__HISTORIES = DatabasePackage.DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Spaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__SPACES = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA__RELATIONS = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Space Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SPACE_RESOURCE_DATA_FEATURE_COUNT = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl <em>Table Space Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTableSpaceRelation()
	 * @generated
	 */
	int TABLE_SPACE_RELATION = 5;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Main Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION__MAIN_SPACE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Index Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION__INDEX_SPACE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Table Space Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_RELATION_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceImpl <em>Table Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTableSpace()
	 * @generated
	 */
	int TABLE_SPACE = 6;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__CHINESE_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__USER = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__FILE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__SIZE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Logic Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE__LOGIC_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Table Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl <em>User Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleUserResourceData()
	 * @generated
	 */
	int ORACLE_USER_RESOURCE_DATA = 7;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__DATA = DatabasePackage.DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__DATA2 = DatabasePackage.DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__NAME = DatabasePackage.DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__CHINESE_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__DESCRIPTION = DatabasePackage.DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__OBJECT_ID = DatabasePackage.DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__HISTORIES = DatabasePackage.DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__USERS = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA__PRIVILEGES = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_RESOURCE_DATA_FEATURE_COUNT = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleUser()
	 * @generated
	 */
	int ORACLE_USER = 8;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Decription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__DECRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__ATTRIBUTES = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__ENABLE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__PRIVILEGES = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__PASSWORD = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Default Table Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER__DEFAULT_TABLE_SPACE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_USER_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePrivilegeImpl <em>Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePrivilegeImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOraclePrivilege()
	 * @generated
	 */
	int ORACLE_PRIVILEGE = 9;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE__TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Decription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE__DECRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_PRIVILEGE_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TriggerResourceDataImpl <em>Trigger Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TriggerResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTriggerResourceData()
	 * @generated
	 */
	int TRIGGER_RESOURCE_DATA = 10;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__DATA = DatabasePackage.DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__DATA2 = DatabasePackage.DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__NAME = DatabasePackage.DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__CHINESE_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__DESCRIPTION = DatabasePackage.DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__OBJECT_ID = DatabasePackage.DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__HISTORIES = DatabasePackage.DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Sql</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA__SQL = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trigger Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_RESOURCE_DATA_FEATURE_COUNT = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl <em>Sequence Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getSequenceResourceData()
	 * @generated
	 */
	int SEQUENCE_RESOURCE_DATA = 11;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__DATA = DatabasePackage.DATABASE_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__DATA2 = DatabasePackage.DATABASE_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__NAME = DatabasePackage.DATABASE_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__CHINESE_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__DESCRIPTION = DatabasePackage.DATABASE_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__OBJECT_ID = DatabasePackage.DATABASE_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__HISTORIES = DatabasePackage.DATABASE_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__FULLY_QUALIFIED_NAME = DatabasePackage.DATABASE_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__TABLE_NAME = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__START = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__INCREMENT = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Min Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__MIN_VALUE = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Max Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__MAX_VALUE = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cycle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__CYCLE = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__CACHE = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Use Cache</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__USE_CACHE = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Is History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA__IS_HISTORY = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Sequence Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_RESOURCE_DATA_FEATURE_COUNT = DatabasePackage.DATABASE_RESOURCE_DATA_FEATURE_COUNT + 9;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl <em>Database Module Extensible Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY = 12;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Table Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Split Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Split Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Biz Pkg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Database Module Extensible Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_MODULE_EXTENSIBLE_PROPERTY_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSequencePropertyImpl <em>Sequence Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSequencePropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleSequenceProperty()
	 * @generated
	 */
	int ORACLE_SEQUENCE_PROPERTY = 13;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SEQUENCE_PROPERTY__SPACE = 0;

	/**
	 * The number of structural features of the '<em>Sequence Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORACLE_SEQUENCE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.table_type <em>table type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.table_type
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#gettable_type()
	 * @generated
	 */
	int TABLE_TYPE = 14;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty <em>Table Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty
	 * @generated
	 */
	EClass getOracleTableProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty#getSpace()
	 * @see #getOracleTableProperty()
	 * @generated
	 */
	EAttribute getOracleTableProperty_Space();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty#getTabletype <em>Tabletype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tabletype</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty#getTabletype()
	 * @see #getOracleTableProperty()
	 * @generated
	 */
	EAttribute getOracleTableProperty_Tabletype();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty <em>Index Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty
	 * @generated
	 */
	EClass getOracleIndexProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty#isReverse <em>Reverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reverse</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleIndexProperty#isReverse()
	 * @see #getOracleIndexProperty()
	 * @generated
	 */
	EAttribute getOracleIndexProperty_Reverse();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty <em>View Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty
	 * @generated
	 */
	EClass getOracleViewProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty#getSpace()
	 * @see #getOracleViewProperty()
	 * @generated
	 */
	EAttribute getOracleViewProperty_Space();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty <em>Module Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty
	 * @generated
	 */
	EClass getOracleModuleProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleModuleProperty#getSpace()
	 * @see #getOracleModuleProperty()
	 * @generated
	 */
	EAttribute getOracleModuleProperty_Space();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData <em>Space Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData
	 * @generated
	 */
	EClass getOracleSpaceResourceData();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getSpaces <em>Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spaces</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getSpaces()
	 * @see #getOracleSpaceResourceData()
	 * @generated
	 */
	EReference getOracleSpaceResourceData_Spaces();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getRelations()
	 * @see #getOracleSpaceResourceData()
	 * @generated
	 */
	EReference getOracleSpaceResourceData_Relations();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation <em>Table Space Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space Relation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation
	 * @generated
	 */
	EClass getTableSpaceRelation();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getMainSpace <em>Main Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getMainSpace()
	 * @see #getTableSpaceRelation()
	 * @generated
	 */
	EAttribute getTableSpaceRelation_MainSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getIndexSpace <em>Index Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getIndexSpace()
	 * @see #getTableSpaceRelation()
	 * @generated
	 */
	EAttribute getTableSpaceRelation_IndexSpace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace
	 * @generated
	 */
	EClass getTableSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getName()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getChineseName()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getUser()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_User();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getFile()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_File();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getSize()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_Size();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getDescription()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getLogicName <em>Logic Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Logic Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getLogicName()
	 * @see #getTableSpace()
	 * @generated
	 */
	EAttribute getTableSpace_LogicName();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData <em>User Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData
	 * @generated
	 */
	EClass getOracleUserResourceData();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getUsers()
	 * @see #getOracleUserResourceData()
	 * @generated
	 */
	EReference getOracleUserResourceData_Users();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getPrivileges <em>Privileges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Privileges</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData#getPrivileges()
	 * @see #getOracleUserResourceData()
	 * @generated
	 */
	EReference getOracleUserResourceData_Privileges();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser
	 * @generated
	 */
	EClass getOracleUser();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getName()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDecription <em>Decription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decription</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDecription()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_Decription();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attributes</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getAttributes()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#isEnable <em>Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#isEnable()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_Enable();

	/**
	 * Returns the meta object for the reference list '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPrivileges <em>Privileges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Privileges</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPrivileges()
	 * @see #getOracleUser()
	 * @generated
	 */
	EReference getOracleUser_Privileges();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getPassword()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDefaultTableSpace <em>Default Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Table Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleUser#getDefaultTableSpace()
	 * @see #getOracleUser()
	 * @generated
	 */
	EAttribute getOracleUser_DefaultTableSpace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege <em>Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privilege</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege
	 * @generated
	 */
	EClass getOraclePrivilege();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getName()
	 * @see #getOraclePrivilege()
	 * @generated
	 */
	EAttribute getOraclePrivilege_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getType()
	 * @see #getOraclePrivilege()
	 * @generated
	 */
	EAttribute getOraclePrivilege_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getDecription <em>Decription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decription</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege#getDecription()
	 * @see #getOraclePrivilege()
	 * @generated
	 */
	EAttribute getOraclePrivilege_Decription();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData <em>Trigger Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData
	 * @generated
	 */
	EClass getTriggerResourceData();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData#getSql <em>Sql</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData#getSql()
	 * @see #getTriggerResourceData()
	 * @generated
	 */
	EAttribute getTriggerResourceData_Sql();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData <em>Sequence Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData
	 * @generated
	 */
	EClass getSequenceResourceData();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getTableName <em>Table Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getTableName()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_TableName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getStart()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_Start();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getIncrement <em>Increment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increment</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getIncrement()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_Increment();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMinValue <em>Min Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMinValue()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_MinValue();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMaxValue <em>Max Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMaxValue()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_MaxValue();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isCycle <em>Cycle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cycle</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isCycle()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_Cycle();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getCache()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_Cache();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isUseCache <em>Use Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Cache</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isUseCache()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_UseCache();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isIsHistory <em>Is History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is History</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isIsHistory()
	 * @see #getSequenceResourceData()
	 * @generated
	 */
	EAttribute getSequenceResourceData_IsHistory();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty <em>Database Module Extensible Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Module Extensible Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty
	 * @generated
	 */
	EClass getDatabaseModuleExtensibleProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getTableType <em>Table Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getTableType()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_TableType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSpace()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_Space();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitField <em>Split Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitField()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_SplitField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitNum <em>Split Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split Num</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitNum()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_SplitNum();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getStartDate()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getBizPkg <em>Biz Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Biz Pkg</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getBizPkg()
	 * @see #getDatabaseModuleExtensibleProperty()
	 * @generated
	 */
	EAttribute getDatabaseModuleExtensibleProperty_BizPkg();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty <em>Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty
	 * @generated
	 */
	EClass getOracleSequenceProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty#getSpace()
	 * @see #getOracleSequenceProperty()
	 * @generated
	 */
	EAttribute getOracleSequenceProperty_Space();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.jres.model.database.oracle.table_type <em>table type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>table type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.table_type
	 * @generated
	 */
	EEnum gettable_type();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OracleFactory getOracleFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl <em>Table Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleTableProperty()
		 * @generated
		 */
		EClass ORACLE_TABLE_PROPERTY = eINSTANCE.getOracleTableProperty();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_TABLE_PROPERTY__SPACE = eINSTANCE.getOracleTableProperty_Space();

		/**
		 * The meta object literal for the '<em><b>Tabletype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_TABLE_PROPERTY__TABLETYPE = eINSTANCE.getOracleTableProperty_Tabletype();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleIndexPropertyImpl <em>Index Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleIndexPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleIndexProperty()
		 * @generated
		 */
		EClass ORACLE_INDEX_PROPERTY = eINSTANCE.getOracleIndexProperty();

		/**
		 * The meta object literal for the '<em><b>Reverse</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_INDEX_PROPERTY__REVERSE = eINSTANCE.getOracleIndexProperty_Reverse();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleViewPropertyImpl <em>View Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleViewPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleViewProperty()
		 * @generated
		 */
		EClass ORACLE_VIEW_PROPERTY = eINSTANCE.getOracleViewProperty();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_VIEW_PROPERTY__SPACE = eINSTANCE.getOracleViewProperty_Space();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleModulePropertyImpl <em>Module Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleModulePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleModuleProperty()
		 * @generated
		 */
		EClass ORACLE_MODULE_PROPERTY = eINSTANCE.getOracleModuleProperty();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_MODULE_PROPERTY__SPACE = eINSTANCE.getOracleModuleProperty_Space();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl <em>Space Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleSpaceResourceData()
		 * @generated
		 */
		EClass ORACLE_SPACE_RESOURCE_DATA = eINSTANCE.getOracleSpaceResourceData();

		/**
		 * The meta object literal for the '<em><b>Spaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORACLE_SPACE_RESOURCE_DATA__SPACES = eINSTANCE.getOracleSpaceResourceData_Spaces();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORACLE_SPACE_RESOURCE_DATA__RELATIONS = eINSTANCE.getOracleSpaceResourceData_Relations();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl <em>Table Space Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceRelationImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTableSpaceRelation()
		 * @generated
		 */
		EClass TABLE_SPACE_RELATION = eINSTANCE.getTableSpaceRelation();

		/**
		 * The meta object literal for the '<em><b>Main Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION__MAIN_SPACE = eINSTANCE.getTableSpaceRelation_MainSpace();

		/**
		 * The meta object literal for the '<em><b>Index Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_RELATION__INDEX_SPACE = eINSTANCE.getTableSpaceRelation_IndexSpace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceImpl <em>Table Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TableSpaceImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTableSpace()
		 * @generated
		 */
		EClass TABLE_SPACE = eINSTANCE.getTableSpace();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__NAME = eINSTANCE.getTableSpace_Name();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__CHINESE_NAME = eINSTANCE.getTableSpace_ChineseName();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__USER = eINSTANCE.getTableSpace_User();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__FILE = eINSTANCE.getTableSpace_File();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__SIZE = eINSTANCE.getTableSpace_Size();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__DESCRIPTION = eINSTANCE.getTableSpace_Description();

		/**
		 * The meta object literal for the '<em><b>Logic Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE__LOGIC_NAME = eINSTANCE.getTableSpace_LogicName();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl <em>User Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleUserResourceData()
		 * @generated
		 */
		EClass ORACLE_USER_RESOURCE_DATA = eINSTANCE.getOracleUserResourceData();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORACLE_USER_RESOURCE_DATA__USERS = eINSTANCE.getOracleUserResourceData_Users();

		/**
		 * The meta object literal for the '<em><b>Privileges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORACLE_USER_RESOURCE_DATA__PRIVILEGES = eINSTANCE.getOracleUserResourceData_Privileges();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleUser()
		 * @generated
		 */
		EClass ORACLE_USER = eINSTANCE.getOracleUser();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__NAME = eINSTANCE.getOracleUser_Name();

		/**
		 * The meta object literal for the '<em><b>Decription</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__DECRIPTION = eINSTANCE.getOracleUser_Decription();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__ATTRIBUTES = eINSTANCE.getOracleUser_Attributes();

		/**
		 * The meta object literal for the '<em><b>Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__ENABLE = eINSTANCE.getOracleUser_Enable();

		/**
		 * The meta object literal for the '<em><b>Privileges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORACLE_USER__PRIVILEGES = eINSTANCE.getOracleUser_Privileges();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__PASSWORD = eINSTANCE.getOracleUser_Password();

		/**
		 * The meta object literal for the '<em><b>Default Table Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_USER__DEFAULT_TABLE_SPACE = eINSTANCE.getOracleUser_DefaultTableSpace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePrivilegeImpl <em>Privilege</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePrivilegeImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOraclePrivilege()
		 * @generated
		 */
		EClass ORACLE_PRIVILEGE = eINSTANCE.getOraclePrivilege();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_PRIVILEGE__NAME = eINSTANCE.getOraclePrivilege_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_PRIVILEGE__TYPE = eINSTANCE.getOraclePrivilege_Type();

		/**
		 * The meta object literal for the '<em><b>Decription</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_PRIVILEGE__DECRIPTION = eINSTANCE.getOraclePrivilege_Decription();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.TriggerResourceDataImpl <em>Trigger Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.TriggerResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getTriggerResourceData()
		 * @generated
		 */
		EClass TRIGGER_RESOURCE_DATA = eINSTANCE.getTriggerResourceData();

		/**
		 * The meta object literal for the '<em><b>Sql</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_RESOURCE_DATA__SQL = eINSTANCE.getTriggerResourceData_Sql();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl <em>Sequence Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getSequenceResourceData()
		 * @generated
		 */
		EClass SEQUENCE_RESOURCE_DATA = eINSTANCE.getSequenceResourceData();

		/**
		 * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__TABLE_NAME = eINSTANCE.getSequenceResourceData_TableName();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__START = eINSTANCE.getSequenceResourceData_Start();

		/**
		 * The meta object literal for the '<em><b>Increment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__INCREMENT = eINSTANCE.getSequenceResourceData_Increment();

		/**
		 * The meta object literal for the '<em><b>Min Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__MIN_VALUE = eINSTANCE.getSequenceResourceData_MinValue();

		/**
		 * The meta object literal for the '<em><b>Max Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__MAX_VALUE = eINSTANCE.getSequenceResourceData_MaxValue();

		/**
		 * The meta object literal for the '<em><b>Cycle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__CYCLE = eINSTANCE.getSequenceResourceData_Cycle();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__CACHE = eINSTANCE.getSequenceResourceData_Cache();

		/**
		 * The meta object literal for the '<em><b>Use Cache</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__USE_CACHE = eINSTANCE.getSequenceResourceData_UseCache();

		/**
		 * The meta object literal for the '<em><b>Is History</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_RESOURCE_DATA__IS_HISTORY = eINSTANCE.getSequenceResourceData_IsHistory();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl <em>Database Module Extensible Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getDatabaseModuleExtensibleProperty()
		 * @generated
		 */
		EClass DATABASE_MODULE_EXTENSIBLE_PROPERTY = eINSTANCE.getDatabaseModuleExtensibleProperty();

		/**
		 * The meta object literal for the '<em><b>Table Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE = eINSTANCE.getDatabaseModuleExtensibleProperty_TableType();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE = eINSTANCE.getDatabaseModuleExtensibleProperty_Space();

		/**
		 * The meta object literal for the '<em><b>Split Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD = eINSTANCE.getDatabaseModuleExtensibleProperty_SplitField();

		/**
		 * The meta object literal for the '<em><b>Split Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM = eINSTANCE.getDatabaseModuleExtensibleProperty_SplitNum();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE = eINSTANCE.getDatabaseModuleExtensibleProperty_StartDate();

		/**
		 * The meta object literal for the '<em><b>Biz Pkg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG = eINSTANCE.getDatabaseModuleExtensibleProperty_BizPkg();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSequencePropertyImpl <em>Sequence Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSequencePropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#getOracleSequenceProperty()
		 * @generated
		 */
		EClass ORACLE_SEQUENCE_PROPERTY = eINSTANCE.getOracleSequenceProperty();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORACLE_SEQUENCE_PROPERTY__SPACE = eINSTANCE.getOracleSequenceProperty_Space();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.database.oracle.table_type <em>table type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.table_type
		 * @see com.hundsun.ares.studio.jres.model.database.oracle.impl.OraclePackageImpl#gettable_type()
		 * @generated
		 */
		EEnum TABLE_TYPE = eINSTANCE.gettable_type();

	}

} //OraclePackage
