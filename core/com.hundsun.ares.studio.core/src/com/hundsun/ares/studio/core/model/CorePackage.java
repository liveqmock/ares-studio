/**
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see com.hundsun.ares.studio.core.model.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/core/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = com.hundsun.ares.studio.core.model.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.IJSONData <em>IJSON Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.IJSONData
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getIJSONData()
	 * @generated
	 */
	int IJSON_DATA = 8;

	/**
	 * The number of structural features of the '<em>IJSON Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJSON_DATA_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl <em>Extensible Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModel()
	 * @generated
	 */
	int EXTENSIBLE_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL__DATA = IJSON_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL__DATA2 = IJSON_DATA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Extensible Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_FEATURE_COUNT = IJSON_DATA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl <em>JRES Resource Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getJRESResourceInfo()
	 * @generated
	 */
	int JRES_RESOURCE_INFO = 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__DATA = EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__DATA2 = EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__NAME = EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__CHINESE_NAME = EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__DESCRIPTION = EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__OBJECT_ID = EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__GROUP = EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__HISTORIES = EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME = EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>JRES Resource Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JRES_RESOURCE_INFO_FEATURE_COUNT = EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.BasicResourceInfoImpl <em>Basic Resource Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.BasicResourceInfoImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getBasicResourceInfo()
	 * @generated
	 */
	int BASIC_RESOURCE_INFO = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO__CHINESE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO__OBJECT_ID = 3;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO__GROUP = 4;

	/**
	 * The number of structural features of the '<em>Basic Resource Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_RESOURCE_INFO_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl <em>Revision History</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getRevisionHistory()
	 * @generated
	 */
	int REVISION_HISTORY = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__DATA = EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__DATA2 = EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Modified Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__MODIFIED_DATE = EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__VERSION = EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Order Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__ORDER_NUMBER = EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Modified By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__MODIFIED_BY = EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Charger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__CHARGER = EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Modified Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__MODIFIED_REASON = EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__MODIFIED = EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__COMMENT = EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY__LOCATION = EXTENSIBLE_MODEL_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Revision History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_HISTORY_FEATURE_COUNT = EXTENSIBLE_MODEL_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.EStringToEObjectMapEntryImpl <em>EString To EObject Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.EStringToEObjectMapEntryImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getEStringToEObjectMapEntry()
	 * @generated
	 */
	int ESTRING_TO_EOBJECT_MAP_ENTRY = 4;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_EOBJECT_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_EOBJECT_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EString To EObject Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_EOBJECT_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ReferenceImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ReferenceWithNamespaceImpl <em>Reference With Namespace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ReferenceWithNamespaceImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getReferenceWithNamespace()
	 * @generated
	 */
	int REFERENCE_WITH_NAMESPACE = 6;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_WITH_NAMESPACE__TYPE = REFERENCE__TYPE;

	/**
	 * The number of structural features of the '<em>Reference With Namespace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_WITH_NAMESPACE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.IReferenceProvider <em>IReference Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.IReferenceProvider
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getIReferenceProvider()
	 * @generated
	 */
	int IREFERENCE_PROVIDER = 7;

	/**
	 * The number of structural features of the '<em>IReference Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREFERENCE_PROVIDER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl <em>Extensible Model Config Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModelConfigProperty()
	 * @generated
	 */
	int EXTENSIBLE_MODEL_CONFIG_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Xml</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML = 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES = 1;

	/**
	 * The feature id for the '<em><b>Xml Cache</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML_CACHE = 2;

	/**
	 * The number of structural features of the '<em>Extensible Model Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_CONFIG_PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl <em>Extensible Model Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModelAttribute()
	 * @generated
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE = 10;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__URI = 0;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__KEY = 2;

	/**
	 * The feature id for the '<em><b>Lable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__LABLE = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Validate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE = 5;

	/**
	 * The number of structural features of the '<em>Extensible Model Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_MODEL_ATTRIBUTE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.UserExtensiblePropertyImpl <em>User Extensible Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.UserExtensiblePropertyImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getUserExtensibleProperty()
	 * @generated
	 */
	int USER_EXTENSIBLE_PROPERTY = 11;

	/**
	 * The feature id for the '<em><b>Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_EXTENSIBLE_PROPERTY__MAP = 0;

	/**
	 * The number of structural features of the '<em>User Extensible Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_EXTENSIBLE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ModuleExtensibleModelImpl <em>Module Extensible Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ModuleExtensibleModelImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getModuleExtensibleModel()
	 * @generated
	 */
	int MODULE_EXTENSIBLE_MODEL = 12;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_EXTENSIBLE_MODEL__DATA = EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_EXTENSIBLE_MODEL__DATA2 = EXTENSIBLE_MODEL__DATA2;

	/**
	 * The number of structural features of the '<em>Module Extensible Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_EXTENSIBLE_MODEL_FEATURE_COUNT = EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ProjectExtensibleModelImpl <em>Project Extensible Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ProjectExtensibleModelImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getProjectExtensibleModel()
	 * @generated
	 */
	int PROJECT_EXTENSIBLE_MODEL = 13;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_EXTENSIBLE_MODEL__DATA = EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_EXTENSIBLE_MODEL__DATA2 = EXTENSIBLE_MODEL__DATA2;

	/**
	 * The number of structural features of the '<em>Project Extensible Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_EXTENSIBLE_MODEL_FEATURE_COUNT = EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ProjectRevisionHistoryPropertyImpl <em>Project Revision History Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ProjectRevisionHistoryPropertyImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getProjectRevisionHistoryProperty()
	 * @generated
	 */
	int PROJECT_REVISION_HISTORY_PROPERTY = 14;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES = 0;

	/**
	 * The number of structural features of the '<em>Project Revision History Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_REVISION_HISTORY_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.core.model.impl.ModuleRevisionHistoryListImpl <em>Module Revision History List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.core.model.impl.ModuleRevisionHistoryListImpl
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getModuleRevisionHistoryList()
	 * @generated
	 */
	int MODULE_REVISION_HISTORY_LIST = 15;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__DATA = JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__DATA2 = JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__NAME = JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__CHINESE_NAME = JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__DESCRIPTION = JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__OBJECT_ID = JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__GROUP = JRES_RESOURCE_INFO__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__HISTORIES = JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST__FULLY_QUALIFIED_NAME = JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The number of structural features of the '<em>Module Revision History List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_REVISION_HISTORY_LIST_FEATURE_COUNT = JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '<em>Dom4j Document</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dom4j.Document
	 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getDom4jDocument()
	 * @generated
	 */
	int DOM4J_DOCUMENT = 16;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.JRESResourceInfo <em>JRES Resource Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JRES Resource Info</em>'.
	 * @see com.hundsun.ares.studio.core.model.JRESResourceInfo
	 * @generated
	 */
	EClass getJRESResourceInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.core.model.JRESResourceInfo#getHistories <em>Histories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Histories</em>'.
	 * @see com.hundsun.ares.studio.core.model.JRESResourceInfo#getHistories()
	 * @see #getJRESResourceInfo()
	 * @generated
	 */
	EReference getJRESResourceInfo_Histories();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.JRESResourceInfo#getFullyQualifiedName <em>Fully Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fully Qualified Name</em>'.
	 * @see com.hundsun.ares.studio.core.model.JRESResourceInfo#getFullyQualifiedName()
	 * @see #getJRESResourceInfo()
	 * @generated
	 */
	EAttribute getJRESResourceInfo_FullyQualifiedName();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo <em>Basic Resource Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Resource Info</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo
	 * @generated
	 */
	EClass getBasicResourceInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo#getName()
	 * @see #getBasicResourceInfo()
	 * @generated
	 */
	EAttribute getBasicResourceInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo#getChineseName()
	 * @see #getBasicResourceInfo()
	 * @generated
	 */
	EAttribute getBasicResourceInfo_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo#getDescription()
	 * @see #getBasicResourceInfo()
	 * @generated
	 */
	EAttribute getBasicResourceInfo_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo#getObjectId <em>Object Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Id</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo#getObjectId()
	 * @see #getBasicResourceInfo()
	 * @generated
	 */
	EAttribute getBasicResourceInfo_ObjectId();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.BasicResourceInfo#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group</em>'.
	 * @see com.hundsun.ares.studio.core.model.BasicResourceInfo#getGroup()
	 * @see #getBasicResourceInfo()
	 * @generated
	 */
	EAttribute getBasicResourceInfo_Group();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.RevisionHistory <em>Revision History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Revision History</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory
	 * @generated
	 */
	EClass getRevisionHistory();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedDate <em>Modified Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified Date</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedDate()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_ModifiedDate();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getVersion()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getOrderNumber <em>Order Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Number</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getOrderNumber()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_OrderNumber();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedBy <em>Modified By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified By</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedBy()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_ModifiedBy();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getCharger <em>Charger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Charger</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getCharger()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_Charger();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedReason <em>Modified Reason</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified Reason</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedReason()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_ModifiedReason();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getModified()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_Modified();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getComment()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_Comment();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see com.hundsun.ares.studio.core.model.RevisionHistory#getLocation()
	 * @see #getRevisionHistory()
	 * @generated
	 */
	EAttribute getRevisionHistory_Location();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ExtensibleModel <em>Extensible Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extensible Model</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModel
	 * @generated
	 */
	EClass getExtensibleModel();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.core.model.ExtensibleModel#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Data</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModel#getData()
	 * @see #getExtensibleModel()
	 * @generated
	 */
	EReference getExtensibleModel_Data();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.core.model.ExtensibleModel#getData2 <em>Data2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Data2</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModel#getData2()
	 * @see #getExtensibleModel()
	 * @generated
	 */
	EReference getExtensibleModel_Data2();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To EObject Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EString To EObject Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDefault="" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.eclipse.emf.ecore.EObject" valueContainment="true"
	 * @generated
	 */
	EClass getEStringToEObjectMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToEObjectMapEntry()
	 * @generated
	 */
	EAttribute getEStringToEObjectMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToEObjectMapEntry()
	 * @generated
	 */
	EReference getEStringToEObjectMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see com.hundsun.ares.studio.core.model.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.Reference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.core.model.Reference#getType()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Type();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ReferenceWithNamespace <em>Reference With Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference With Namespace</em>'.
	 * @see com.hundsun.ares.studio.core.model.ReferenceWithNamespace
	 * @generated
	 */
	EClass getReferenceWithNamespace();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.IReferenceProvider <em>IReference Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IReference Provider</em>'.
	 * @see com.hundsun.ares.studio.core.model.IReferenceProvider
	 * @generated
	 */
	EClass getIReferenceProvider();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.IJSONData <em>IJSON Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJSON Data</em>'.
	 * @see com.hundsun.ares.studio.core.model.IJSONData
	 * @generated
	 */
	EClass getIJSONData();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty <em>Extensible Model Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extensible Model Config Property</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty
	 * @generated
	 */
	EClass getExtensibleModelConfigProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXml <em>Xml</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xml</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXml()
	 * @see #getExtensibleModelConfigProperty()
	 * @generated
	 */
	EAttribute getExtensibleModelConfigProperty_Xml();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getAttributes()
	 * @see #getExtensibleModelConfigProperty()
	 * @generated
	 */
	EReference getExtensibleModelConfigProperty_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXmlCache <em>Xml Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xml Cache</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXmlCache()
	 * @see #getExtensibleModelConfigProperty()
	 * @generated
	 */
	EAttribute getExtensibleModelConfigProperty_XmlCache();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute <em>Extensible Model Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extensible Model Attribute</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute
	 * @generated
	 */
	EClass getExtensibleModelAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getUri()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_Uri();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getClassName()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getKey()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getLable <em>Lable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lable</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getLable()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_Lable();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getType()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getValidate <em>Validate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validate</em>'.
	 * @see com.hundsun.ares.studio.core.model.ExtensibleModelAttribute#getValidate()
	 * @see #getExtensibleModelAttribute()
	 * @generated
	 */
	EAttribute getExtensibleModelAttribute_Validate();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.UserExtensibleProperty <em>User Extensible Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Extensible Property</em>'.
	 * @see com.hundsun.ares.studio.core.model.UserExtensibleProperty
	 * @generated
	 */
	EClass getUserExtensibleProperty();

	/**
	 * Returns the meta object for the map '{@link com.hundsun.ares.studio.core.model.UserExtensibleProperty#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Map</em>'.
	 * @see com.hundsun.ares.studio.core.model.UserExtensibleProperty#getMap()
	 * @see #getUserExtensibleProperty()
	 * @generated
	 */
	EReference getUserExtensibleProperty_Map();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ModuleExtensibleModel <em>Module Extensible Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Extensible Model</em>'.
	 * @see com.hundsun.ares.studio.core.model.ModuleExtensibleModel
	 * @generated
	 */
	EClass getModuleExtensibleModel();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ProjectExtensibleModel <em>Project Extensible Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project Extensible Model</em>'.
	 * @see com.hundsun.ares.studio.core.model.ProjectExtensibleModel
	 * @generated
	 */
	EClass getProjectExtensibleModel();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty <em>Project Revision History Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project Revision History Property</em>'.
	 * @see com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty
	 * @generated
	 */
	EClass getProjectRevisionHistoryProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty#getHistories <em>Histories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Histories</em>'.
	 * @see com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty#getHistories()
	 * @see #getProjectRevisionHistoryProperty()
	 * @generated
	 */
	EReference getProjectRevisionHistoryProperty_Histories();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList <em>Module Revision History List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Revision History List</em>'.
	 * @see com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList
	 * @generated
	 */
	EClass getModuleRevisionHistoryList();

	/**
	 * Returns the meta object for data type '{@link org.dom4j.Document <em>Dom4j Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dom4j Document</em>'.
	 * @see org.dom4j.Document
	 * @model instanceClass="org.dom4j.Document"
	 * @generated
	 */
	EDataType getDom4jDocument();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl <em>JRES Resource Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getJRESResourceInfo()
		 * @generated
		 */
		EClass JRES_RESOURCE_INFO = eINSTANCE.getJRESResourceInfo();

		/**
		 * The meta object literal for the '<em><b>Histories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JRES_RESOURCE_INFO__HISTORIES = eINSTANCE.getJRESResourceInfo_Histories();

		/**
		 * The meta object literal for the '<em><b>Fully Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME = eINSTANCE.getJRESResourceInfo_FullyQualifiedName();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.BasicResourceInfoImpl <em>Basic Resource Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.BasicResourceInfoImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getBasicResourceInfo()
		 * @generated
		 */
		EClass BASIC_RESOURCE_INFO = eINSTANCE.getBasicResourceInfo();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_RESOURCE_INFO__NAME = eINSTANCE.getBasicResourceInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_RESOURCE_INFO__CHINESE_NAME = eINSTANCE.getBasicResourceInfo_ChineseName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_RESOURCE_INFO__DESCRIPTION = eINSTANCE.getBasicResourceInfo_Description();

		/**
		 * The meta object literal for the '<em><b>Object Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_RESOURCE_INFO__OBJECT_ID = eINSTANCE.getBasicResourceInfo_ObjectId();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_RESOURCE_INFO__GROUP = eINSTANCE.getBasicResourceInfo_Group();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl <em>Revision History</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.RevisionHistoryImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getRevisionHistory()
		 * @generated
		 */
		EClass REVISION_HISTORY = eINSTANCE.getRevisionHistory();

		/**
		 * The meta object literal for the '<em><b>Modified Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__MODIFIED_DATE = eINSTANCE.getRevisionHistory_ModifiedDate();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__VERSION = eINSTANCE.getRevisionHistory_Version();

		/**
		 * The meta object literal for the '<em><b>Order Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__ORDER_NUMBER = eINSTANCE.getRevisionHistory_OrderNumber();

		/**
		 * The meta object literal for the '<em><b>Modified By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__MODIFIED_BY = eINSTANCE.getRevisionHistory_ModifiedBy();

		/**
		 * The meta object literal for the '<em><b>Charger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__CHARGER = eINSTANCE.getRevisionHistory_Charger();

		/**
		 * The meta object literal for the '<em><b>Modified Reason</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__MODIFIED_REASON = eINSTANCE.getRevisionHistory_ModifiedReason();

		/**
		 * The meta object literal for the '<em><b>Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__MODIFIED = eINSTANCE.getRevisionHistory_Modified();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__COMMENT = eINSTANCE.getRevisionHistory_Comment();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION_HISTORY__LOCATION = eINSTANCE.getRevisionHistory_Location();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl <em>Extensible Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModel()
		 * @generated
		 */
		EClass EXTENSIBLE_MODEL = eINSTANCE.getExtensibleModel();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENSIBLE_MODEL__DATA = eINSTANCE.getExtensibleModel_Data();

		/**
		 * The meta object literal for the '<em><b>Data2</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENSIBLE_MODEL__DATA2 = eINSTANCE.getExtensibleModel_Data2();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.EStringToEObjectMapEntryImpl <em>EString To EObject Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.EStringToEObjectMapEntryImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getEStringToEObjectMapEntry()
		 * @generated
		 */
		EClass ESTRING_TO_EOBJECT_MAP_ENTRY = eINSTANCE.getEStringToEObjectMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_EOBJECT_MAP_ENTRY__KEY = eINSTANCE.getEStringToEObjectMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ESTRING_TO_EOBJECT_MAP_ENTRY__VALUE = eINSTANCE.getEStringToEObjectMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ReferenceImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__TYPE = eINSTANCE.getReference_Type();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ReferenceWithNamespaceImpl <em>Reference With Namespace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ReferenceWithNamespaceImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getReferenceWithNamespace()
		 * @generated
		 */
		EClass REFERENCE_WITH_NAMESPACE = eINSTANCE.getReferenceWithNamespace();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.IReferenceProvider <em>IReference Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.IReferenceProvider
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getIReferenceProvider()
		 * @generated
		 */
		EClass IREFERENCE_PROVIDER = eINSTANCE.getIReferenceProvider();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.IJSONData <em>IJSON Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.IJSONData
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getIJSONData()
		 * @generated
		 */
		EClass IJSON_DATA = eINSTANCE.getIJSONData();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl <em>Extensible Model Config Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModelConfigProperty()
		 * @generated
		 */
		EClass EXTENSIBLE_MODEL_CONFIG_PROPERTY = eINSTANCE.getExtensibleModelConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Xml</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML = eINSTANCE.getExtensibleModelConfigProperty_Xml();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES = eINSTANCE.getExtensibleModelConfigProperty_Attributes();

		/**
		 * The meta object literal for the '<em><b>Xml Cache</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML_CACHE = eINSTANCE.getExtensibleModelConfigProperty_XmlCache();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl <em>Extensible Model Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getExtensibleModelAttribute()
		 * @generated
		 */
		EClass EXTENSIBLE_MODEL_ATTRIBUTE = eINSTANCE.getExtensibleModelAttribute();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__URI = eINSTANCE.getExtensibleModelAttribute_Uri();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME = eINSTANCE.getExtensibleModelAttribute_ClassName();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__KEY = eINSTANCE.getExtensibleModelAttribute_Key();

		/**
		 * The meta object literal for the '<em><b>Lable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__LABLE = eINSTANCE.getExtensibleModelAttribute_Lable();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__TYPE = eINSTANCE.getExtensibleModelAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE = eINSTANCE.getExtensibleModelAttribute_Validate();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.UserExtensiblePropertyImpl <em>User Extensible Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.UserExtensiblePropertyImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getUserExtensibleProperty()
		 * @generated
		 */
		EClass USER_EXTENSIBLE_PROPERTY = eINSTANCE.getUserExtensibleProperty();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_EXTENSIBLE_PROPERTY__MAP = eINSTANCE.getUserExtensibleProperty_Map();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ModuleExtensibleModelImpl <em>Module Extensible Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ModuleExtensibleModelImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getModuleExtensibleModel()
		 * @generated
		 */
		EClass MODULE_EXTENSIBLE_MODEL = eINSTANCE.getModuleExtensibleModel();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ProjectExtensibleModelImpl <em>Project Extensible Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ProjectExtensibleModelImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getProjectExtensibleModel()
		 * @generated
		 */
		EClass PROJECT_EXTENSIBLE_MODEL = eINSTANCE.getProjectExtensibleModel();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ProjectRevisionHistoryPropertyImpl <em>Project Revision History Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ProjectRevisionHistoryPropertyImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getProjectRevisionHistoryProperty()
		 * @generated
		 */
		EClass PROJECT_REVISION_HISTORY_PROPERTY = eINSTANCE.getProjectRevisionHistoryProperty();

		/**
		 * The meta object literal for the '<em><b>Histories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES = eINSTANCE.getProjectRevisionHistoryProperty_Histories();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.core.model.impl.ModuleRevisionHistoryListImpl <em>Module Revision History List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.core.model.impl.ModuleRevisionHistoryListImpl
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getModuleRevisionHistoryList()
		 * @generated
		 */
		EClass MODULE_REVISION_HISTORY_LIST = eINSTANCE.getModuleRevisionHistoryList();

		/**
		 * The meta object literal for the '<em>Dom4j Document</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dom4j.Document
		 * @see com.hundsun.ares.studio.core.model.impl.CorePackageImpl#getDom4jDocument()
		 * @generated
		 */
		EDataType DOM4J_DOCUMENT = eINSTANCE.getDom4jDocument();

	}

} //CorePackage
