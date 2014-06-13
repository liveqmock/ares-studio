/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

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
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory
 * @model kind="package"
 * @generated
 */
public interface BasicdataPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "basicdata";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/basicdata/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "basicdata";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasicdataPackage eINSTANCE = com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.EpacakgeDefineListImpl <em>Epacakge Define List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.EpacakgeDefineListImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getEpacakgeDefineList()
	 * @generated
	 */
	int EPACAKGE_DEFINE_LIST = 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__DATA = CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__DATA2 = CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__NAME = CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__CHINESE_NAME = CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__DESCRIPTION = CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__OBJECT_ID = CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__GROUP = CorePackage.JRES_RESOURCE_INFO__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__HISTORIES = CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__FULLY_QUALIFIED_NAME = CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST__ITEMS = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Epacakge Define List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACAKGE_DEFINE_LIST_FEATURE_COUNT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.PackageDefineImpl <em>Package Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.PackageDefineImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getPackageDefine()
	 * @generated
	 */
	int PACKAGE_DEFINE = 1;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DEFINE__URL = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DEFINE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Package Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DEFINE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.SingleTableImpl <em>Single Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.SingleTableImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getSingleTable()
	 * @generated
	 */
	int SINGLE_TABLE = 2;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_TABLE__URL = PACKAGE_DEFINE__URL;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_TABLE__TYPE = PACKAGE_DEFINE__TYPE;

	/**
	 * The feature id for the '<em><b>Master</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_TABLE__MASTER = PACKAGE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_TABLE_FEATURE_COUNT = PACKAGE_DEFINE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl <em>Master Slave Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getMasterSlaveTable()
	 * @generated
	 */
	int MASTER_SLAVE_TABLE = 3;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_TABLE__URL = PACKAGE_DEFINE__URL;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_TABLE__TYPE = PACKAGE_DEFINE__TYPE;

	/**
	 * The feature id for the '<em><b>Master</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_TABLE__MASTER = PACKAGE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Slave</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_TABLE__SLAVE = PACKAGE_DEFINE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Master Slave Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_TABLE_FEATURE_COUNT = PACKAGE_DEFINE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl <em>Master Slave Link Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getMasterSlaveLinkTable()
	 * @generated
	 */
	int MASTER_SLAVE_LINK_TABLE = 4;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE__URL = PACKAGE_DEFINE__URL;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE__TYPE = PACKAGE_DEFINE__TYPE;

	/**
	 * The feature id for the '<em><b>Master</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE__MASTER = PACKAGE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Slave</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE__SLAVE = PACKAGE_DEFINE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE__LINK = PACKAGE_DEFINE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Master Slave Link Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_SLAVE_LINK_TABLE_FEATURE_COUNT = PACKAGE_DEFINE_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl <em>Basic Data Base Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicDataBaseModel()
	 * @generated
	 */
	int BASIC_DATA_BASE_MODEL = 5;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__DATA = MetadataPackage.METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__DATA2 = MetadataPackage.METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__NAME = MetadataPackage.METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__CHINESE_NAME = MetadataPackage.METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__DESCRIPTION = MetadataPackage.METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__OBJECT_ID = MetadataPackage.METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__GROUP = MetadataPackage.METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__HISTORIES = MetadataPackage.METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__FULLY_QUALIFIED_NAME = MetadataPackage.METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__OPERATIONS = MetadataPackage.METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__ROOT = MetadataPackage.METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__ITEMS = MetadataPackage.METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The feature id for the '<em><b>Extend</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__EXTEND = MetadataPackage.METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL__FILE = MetadataPackage.METADATA_RESOURCE_DATA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Basic Data Base Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_BASE_MODEL_FEATURE_COUNT = MetadataPackage.METADATA_RESOURCE_DATA_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldPackageDefineImpl <em>Standard Field Package Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldPackageDefineImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldPackageDefine()
	 * @generated
	 */
	int STANDARD_FIELD_PACKAGE_DEFINE = 6;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_PACKAGE_DEFINE__URL = PACKAGE_DEFINE__URL;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_PACKAGE_DEFINE__TYPE = PACKAGE_DEFINE__TYPE;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_PACKAGE_DEFINE__FIELDS = PACKAGE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Standard Field Package Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_PACKAGE_DEFINE_FEATURE_COUNT = PACKAGE_DEFINE_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl <em>Standard Field Model And Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldModelAndData()
	 * @generated
	 */
	int STANDARD_FIELD_MODEL_AND_DATA = 7;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_MODEL_AND_DATA__MODEL = CorePackage.IREFERENCE_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_MODEL_AND_DATA__DATA = CorePackage.IREFERENCE_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Standard Field Model And Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_MODEL_AND_DATA_FEATURE_COUNT = CorePackage.IREFERENCE_PROVIDER_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldColumnImpl <em>Standard Field Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldColumnImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldColumn()
	 * @generated
	 */
	int STANDARD_FIELD_COLUMN = 8;

	/**
	 * The feature id for the '<em><b>Standard Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_COLUMN__STANDARD_FIELD = 0;

	/**
	 * The number of structural features of the '<em>Standard Field Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_COLUMN_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl <em>Basic Data Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicDataField()
	 * @generated
	 */
	int BASIC_DATA_FIELD = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__NAME = 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__CHINESE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__DATA_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__DEFAULT_VALUE = 4;

	/**
	 * The feature id for the '<em><b>Mark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__MARK = 5;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__COMMENTS = 6;

	/**
	 * The feature id for the '<em><b>Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD__COLUMN_TYPE = 7;

	/**
	 * The number of structural features of the '<em>Basic Data Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DATA_FIELD_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataItemImpl
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicdataItem()
	 * @generated
	 */
	int BASICDATA_ITEM = 10;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__DATA = MetadataPackage.METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__DATA2 = MetadataPackage.METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__NAME = MetadataPackage.METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__CHINESE_NAME = MetadataPackage.METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__DESCRIPTION = MetadataPackage.METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__REF_ID = MetadataPackage.METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM__PARENT = MetadataPackage.METADATA_ITEM__PARENT;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASICDATA_ITEM_FEATURE_COUNT = MetadataPackage.METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList <em>Epacakge Define List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Epacakge Define List</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList
	 * @generated
	 */
	EClass getEpacakgeDefineList();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList#getItems()
	 * @see #getEpacakgeDefineList()
	 * @generated
	 */
	EReference getEpacakgeDefineList_Items();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine <em>Package Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Define</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine
	 * @generated
	 */
	EClass getPackageDefine();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine#getUrl()
	 * @see #getPackageDefine()
	 * @generated
	 */
	EAttribute getPackageDefine_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine#getType()
	 * @see #getPackageDefine()
	 * @generated
	 */
	EAttribute getPackageDefine_Type();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable <em>Single Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Table</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable
	 * @generated
	 */
	EClass getSingleTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable#getMaster <em>Master</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Master</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable#getMaster()
	 * @see #getSingleTable()
	 * @generated
	 */
	EAttribute getSingleTable_Master();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable <em>Master Slave Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Slave Table</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable
	 * @generated
	 */
	EClass getMasterSlaveTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getMaster <em>Master</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Master</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getMaster()
	 * @see #getMasterSlaveTable()
	 * @generated
	 */
	EAttribute getMasterSlaveTable_Master();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getSlave <em>Slave</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Slave</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getSlave()
	 * @see #getMasterSlaveTable()
	 * @generated
	 */
	EAttribute getMasterSlaveTable_Slave();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable <em>Master Slave Link Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Slave Link Table</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable
	 * @generated
	 */
	EClass getMasterSlaveLinkTable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getMaster <em>Master</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Master</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getMaster()
	 * @see #getMasterSlaveLinkTable()
	 * @generated
	 */
	EAttribute getMasterSlaveLinkTable_Master();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getSlave <em>Slave</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Slave</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getSlave()
	 * @see #getMasterSlaveLinkTable()
	 * @generated
	 */
	EAttribute getMasterSlaveLinkTable_Slave();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable#getLink()
	 * @see #getMasterSlaveLinkTable()
	 * @generated
	 */
	EAttribute getMasterSlaveLinkTable_Link();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel <em>Basic Data Base Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Data Base Model</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel
	 * @generated
	 */
	EClass getBasicDataBaseModel();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getExtend <em>Extend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extend</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getExtend()
	 * @see #getBasicDataBaseModel()
	 * @generated
	 */
	EAttribute getBasicDataBaseModel_Extend();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel#getFile()
	 * @see #getBasicDataBaseModel()
	 * @generated
	 */
	EAttribute getBasicDataBaseModel_File();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine <em>Standard Field Package Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Field Package Define</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine
	 * @generated
	 */
	EClass getStandardFieldPackageDefine();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine#getFields()
	 * @see #getStandardFieldPackageDefine()
	 * @generated
	 */
	EReference getStandardFieldPackageDefine_Fields();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData <em>Standard Field Model And Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Field Model And Data</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData
	 * @generated
	 */
	EClass getStandardFieldModelAndData();

	/**
	 * Returns the meta object for the reference '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getModel()
	 * @see #getStandardFieldModelAndData()
	 * @generated
	 */
	EReference getStandardFieldModelAndData_Model();

	/**
	 * Returns the meta object for the reference '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getData()
	 * @see #getStandardFieldModelAndData()
	 * @generated
	 */
	EReference getStandardFieldModelAndData_Data();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn <em>Standard Field Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Field Column</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn
	 * @generated
	 */
	EClass getStandardFieldColumn();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn#getStandardField <em>Standard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Field</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn#getStandardField()
	 * @see #getStandardFieldColumn()
	 * @generated
	 */
	EAttribute getStandardFieldColumn_StandardField();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField <em>Basic Data Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Data Field</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField
	 * @generated
	 */
	EClass getBasicDataField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getName()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getChineseName()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDescription()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDataType()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getDefaultValue()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getMark <em>Mark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mark</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getMark()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_Mark();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comments</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getComments()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_Comments();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getColumnType <em>Column Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Type</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField#getColumnType()
	 * @see #getBasicDataField()
	 * @generated
	 */
	EAttribute getBasicDataField_ColumnType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem
	 * @generated
	 */
	EClass getBasicdataItem();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BasicdataFactory getBasicdataFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.EpacakgeDefineListImpl <em>Epacakge Define List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.EpacakgeDefineListImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getEpacakgeDefineList()
		 * @generated
		 */
		EClass EPACAKGE_DEFINE_LIST = eINSTANCE.getEpacakgeDefineList();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPACAKGE_DEFINE_LIST__ITEMS = eINSTANCE.getEpacakgeDefineList_Items();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.PackageDefineImpl <em>Package Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.PackageDefineImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getPackageDefine()
		 * @generated
		 */
		EClass PACKAGE_DEFINE = eINSTANCE.getPackageDefine();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_DEFINE__URL = eINSTANCE.getPackageDefine_Url();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_DEFINE__TYPE = eINSTANCE.getPackageDefine_Type();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.SingleTableImpl <em>Single Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.SingleTableImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getSingleTable()
		 * @generated
		 */
		EClass SINGLE_TABLE = eINSTANCE.getSingleTable();

		/**
		 * The meta object literal for the '<em><b>Master</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINGLE_TABLE__MASTER = eINSTANCE.getSingleTable_Master();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl <em>Master Slave Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getMasterSlaveTable()
		 * @generated
		 */
		EClass MASTER_SLAVE_TABLE = eINSTANCE.getMasterSlaveTable();

		/**
		 * The meta object literal for the '<em><b>Master</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_SLAVE_TABLE__MASTER = eINSTANCE.getMasterSlaveTable_Master();

		/**
		 * The meta object literal for the '<em><b>Slave</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_SLAVE_TABLE__SLAVE = eINSTANCE.getMasterSlaveTable_Slave();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl <em>Master Slave Link Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getMasterSlaveLinkTable()
		 * @generated
		 */
		EClass MASTER_SLAVE_LINK_TABLE = eINSTANCE.getMasterSlaveLinkTable();

		/**
		 * The meta object literal for the '<em><b>Master</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_SLAVE_LINK_TABLE__MASTER = eINSTANCE.getMasterSlaveLinkTable_Master();

		/**
		 * The meta object literal for the '<em><b>Slave</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_SLAVE_LINK_TABLE__SLAVE = eINSTANCE.getMasterSlaveLinkTable_Slave();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_SLAVE_LINK_TABLE__LINK = eINSTANCE.getMasterSlaveLinkTable_Link();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl <em>Basic Data Base Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicDataBaseModel()
		 * @generated
		 */
		EClass BASIC_DATA_BASE_MODEL = eINSTANCE.getBasicDataBaseModel();

		/**
		 * The meta object literal for the '<em><b>Extend</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_BASE_MODEL__EXTEND = eINSTANCE.getBasicDataBaseModel_Extend();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_BASE_MODEL__FILE = eINSTANCE.getBasicDataBaseModel_File();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldPackageDefineImpl <em>Standard Field Package Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldPackageDefineImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldPackageDefine()
		 * @generated
		 */
		EClass STANDARD_FIELD_PACKAGE_DEFINE = eINSTANCE.getStandardFieldPackageDefine();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STANDARD_FIELD_PACKAGE_DEFINE__FIELDS = eINSTANCE.getStandardFieldPackageDefine_Fields();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl <em>Standard Field Model And Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldModelAndDataImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldModelAndData()
		 * @generated
		 */
		EClass STANDARD_FIELD_MODEL_AND_DATA = eINSTANCE.getStandardFieldModelAndData();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STANDARD_FIELD_MODEL_AND_DATA__MODEL = eINSTANCE.getStandardFieldModelAndData_Model();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STANDARD_FIELD_MODEL_AND_DATA__DATA = eINSTANCE.getStandardFieldModelAndData_Data();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldColumnImpl <em>Standard Field Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.StandardFieldColumnImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getStandardFieldColumn()
		 * @generated
		 */
		EClass STANDARD_FIELD_COLUMN = eINSTANCE.getStandardFieldColumn();

		/**
		 * The meta object literal for the '<em><b>Standard Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_FIELD_COLUMN__STANDARD_FIELD = eINSTANCE.getStandardFieldColumn_StandardField();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl <em>Basic Data Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataFieldImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicDataField()
		 * @generated
		 */
		EClass BASIC_DATA_FIELD = eINSTANCE.getBasicDataField();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__NAME = eINSTANCE.getBasicDataField_Name();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__CHINESE_NAME = eINSTANCE.getBasicDataField_ChineseName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__DESCRIPTION = eINSTANCE.getBasicDataField_Description();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__DATA_TYPE = eINSTANCE.getBasicDataField_DataType();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__DEFAULT_VALUE = eINSTANCE.getBasicDataField_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Mark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__MARK = eINSTANCE.getBasicDataField_Mark();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__COMMENTS = eINSTANCE.getBasicDataField_Comments();

		/**
		 * The meta object literal for the '<em><b>Column Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_DATA_FIELD__COLUMN_TYPE = eINSTANCE.getBasicDataField_ColumnType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataItemImpl
		 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataPackageImpl#getBasicdataItem()
		 * @generated
		 */
		EClass BASICDATA_ITEM = eINSTANCE.getBasicdataItem();

	}

} //BasicdataPackage
