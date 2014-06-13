/**
 * 源程序名称：MetadataPackage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataFactory
 * @model kind="package"
 * @generated
 */
public interface MetadataPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "metadata";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/jres/metadata/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metadata";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetadataPackage eINSTANCE = com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MDModuleCommonPropertyImpl <em>MD Module Common Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MDModuleCommonPropertyImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMDModuleCommonProperty()
	 * @generated
	 */
	int MD_MODULE_COMMON_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Use Ref Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE = 0;

	/**
	 * The number of structural features of the '<em>MD Module Common Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MD_MODULE_COMMON_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TITLE = 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__FILE = 1;

	/**
	 * The feature id for the '<em><b>Out Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OUT_PATH = 2;

	/**
	 * The feature id for the '<em><b>Function Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__FUNCTION_NAME = 3;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__CODE = 4;

	/**
	 * The feature id for the '<em><b>Uixml</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__UIXML = 5;

	/**
	 * The feature id for the '<em><b>Autobuild</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__AUTOBUILD = 6;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl <em>Resource Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataResourceData()
	 * @generated
	 */
	int METADATA_RESOURCE_DATA = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__DATA = CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__DATA2 = CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__NAME = CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__CHINESE_NAME = CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__DESCRIPTION = CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__OBJECT_ID = CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__GROUP = CorePackage.JRES_RESOURCE_INFO__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__HISTORIES = CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME = CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__OPERATIONS = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__ROOT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA__ITEMS = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_RESOURCE_DATA_FEATURE_COUNT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.NamedElementImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__CHINESE_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataItem()
	 * @generated
	 */
	int METADATA_ITEM = 4;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__DATA = NAMED_ELEMENT__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__DATA2 = NAMED_ELEMENT__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__CHINESE_NAME = NAMED_ELEMENT__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__REF_ID = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM__PARENT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ITEM_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataCategory()
	 * @generated
	 */
	int METADATA_CATEGORY = 5;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__DATA = NAMED_ELEMENT__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__DATA2 = NAMED_ELEMENT__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__CHINESE_NAME = NAMED_ELEMENT__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__CHILDREN = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Items</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__ITEMS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY__PARENT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_CATEGORY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueListImpl <em>Type Default Value List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getTypeDefaultValueList()
	 * @generated
	 */
	int TYPE_DEFAULT_VALUE_LIST = 6;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Type Default Value List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueImpl <em>Type Default Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getTypeDefaultValue()
	 * @generated
	 */
	int TYPE_DEFAULT_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The number of structural features of the '<em>Type Default Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEFAULT_VALUE_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeListImpl <em>Standard Data Type List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardDataTypeList()
	 * @generated
	 */
	int STANDARD_DATA_TYPE_LIST = 8;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Standard Data Type List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeImpl <em>Standard Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardDataType()
	 * @generated
	 */
	int STANDARD_DATA_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The number of structural features of the '<em>Standard Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DATA_TYPE_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeListImpl <em>Business Data Type List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBusinessDataTypeList()
	 * @generated
	 */
	int BUSINESS_DATA_TYPE_LIST = 10;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Business Data Type List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeImpl <em>Business Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBusinessDataType()
	 * @generated
	 */
	int BUSINESS_DATA_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Std Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__STD_TYPE = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__LENGTH = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__PRECISION = METADATA_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE__DEFAULT_VALUE = METADATA_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Business Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_DATA_TYPE_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldListImpl <em>Standard Field List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardFieldList()
	 * @generated
	 */
	int STANDARD_FIELD_LIST = 12;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Standard Field List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl <em>Standard Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardField()
	 * @generated
	 */
	int STANDARD_FIELD = 13;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__LENGTH = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__PRECISION = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__DATA_TYPE = METADATA_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dictionary Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD__DICTIONARY_TYPE = METADATA_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Standard Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_FIELD_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryListImpl <em>Dictionary List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryList()
	 * @generated
	 */
	int DICTIONARY_LIST = 14;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Dictionary List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryTypeImpl <em>Dictionary Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryTypeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryType()
	 * @generated
	 */
	int DICTIONARY_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE__ITEMS = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Dictionary Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_TYPE_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl <em>Dictionary Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryItem()
	 * @generated
	 */
	int DICTIONARY_ITEM = 16;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__PARENT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__VALUE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__CHINESE_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Constant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__CONSTANT_NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Dictionary Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICTIONARY_ITEM_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ConstantListImpl <em>Constant List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ConstantListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getConstantList()
	 * @generated
	 */
	int CONSTANT_LIST = 17;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Constant List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ConstantItemImpl <em>Constant Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ConstantItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getConstantItem()
	 * @generated
	 */
	int CONSTANT_ITEM = 18;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__DATA_TYPE = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__VALUE = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__LENGTH = METADATA_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM__PRECISION = METADATA_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Constant Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_ITEM_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoListImpl <em>Error No List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getErrorNoList()
	 * @generated
	 */
	int ERROR_NO_LIST = 19;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Error No List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl <em>Error No Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getErrorNoItem()
	 * @generated
	 */
	int ERROR_NO_ITEM = 20;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>No</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__NO = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__MESSAGE = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__CONSTANT_NAME = METADATA_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM__LEVEL = METADATA_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Error No Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_NO_ITEM_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 4;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigListImpl <em>General Data Config List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getGeneralDataConfigList()
	 * @generated
	 */
	int GENERAL_DATA_CONFIG_LIST = 21;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__DATA = CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__DATA2 = CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__NAME = CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__CHINESE_NAME = CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__DESCRIPTION = CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__OBJECT_ID = CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__GROUP = CorePackage.JRES_RESOURCE_INFO__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__HISTORIES = CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__FULLY_QUALIFIED_NAME = CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST__ITEMS = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>General Data Config List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_LIST_FEATURE_COUNT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl <em>General Data Config Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getGeneralDataConfigItem()
	 * @generated
	 */
	int GENERAL_DATA_CONFIG_ITEM = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM__ID = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Discription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM__DISCRIPTION = 4;

	/**
	 * The number of structural features of the '<em>General Data Config Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_DATA_CONFIG_ITEM_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl <em>Menu Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMenuItem()
	 * @generated
	 */
	int MENU_ITEM = 23;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__URL = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__ICON = METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__SUB_ITEMS = METADATA_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Function Proxys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM__FUNCTION_PROXYS = METADATA_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Menu Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_ITEM_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionProxyImpl <em>Function Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.FunctionProxyImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getFunctionProxy()
	 * @generated
	 */
	int FUNCTION_PROXY = 24;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PROXY__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PROXY__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Fun Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PROXY__FUN_CODE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PROXY__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_PROXY_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 25;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DATA = NAMED_ELEMENT__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DATA2 = NAMED_ELEMENT__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__CHINESE_NAME = NAMED_ELEMENT__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sub Trans Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SUB_TRANS_CODE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Serv ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SERV_ID = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuListImpl <em>Menu List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MenuListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMenuList()
	 * @generated
	 */
	int MENU_LIST = 26;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The feature id for the '<em><b>Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST__FUNCTIONS = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Menu List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeItemImpl <em>ID Range Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeItemImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRangeItem()
	 * @generated
	 */
	int ID_RANGE_ITEM = 27;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The number of structural features of the '<em>ID Range Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_ITEM_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeImpl <em>ID Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRange()
	 * @generated
	 */
	int ID_RANGE = 29;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeListImpl <em>ID Range List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRangeList()
	 * @generated
	 */
	int ID_RANGE_LIST = 28;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>ID Range List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE__RANGE = 0;

	/**
	 * The number of structural features of the '<em>ID Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_RANGE_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigListImpl <em>Biz Property Config List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigListImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBizPropertyConfigList()
	 * @generated
	 */
	int BIZ_PROPERTY_CONFIG_LIST = 30;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__DATA = METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__DATA2 = METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__NAME = METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__CHINESE_NAME = METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__DESCRIPTION = METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__OBJECT_ID = METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__GROUP = METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__HISTORIES = METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__FULLY_QUALIFIED_NAME = METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__OPERATIONS = METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__ROOT = METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST__ITEMS = METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Biz Property Config List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_LIST_FEATURE_COUNT = METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigImpl <em>Biz Property Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigImpl
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBizPropertyConfig()
	 * @generated
	 */
	int BIZ_PROPERTY_CONFIG = 31;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__DATA = METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__DATA2 = METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__NAME = METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__CHINESE_NAME = METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__DESCRIPTION = METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__REF_ID = METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__PARENT = METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Ename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG__ENAME = METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Biz Property Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_PROPERTY_CONFIG_FEATURE_COUNT = METADATA_ITEM_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty <em>MD Module Common Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MD Module Common Property</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty
	 * @generated
	 */
	EClass getMDModuleCommonProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty#isUseRefFeature <em>Use Ref Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Ref Feature</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty#isUseRefFeature()
	 * @see #getMDModuleCommonProperty()
	 * @generated
	 */
	EAttribute getMDModuleCommonProperty_UseRefFeature();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getTitle()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getFile()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_File();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getOutPath <em>Out Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Out Path</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getOutPath()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_OutPath();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFunctionName <em>Function Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getFunctionName()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_FunctionName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getCode()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Code();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getUixml <em>Uixml</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uixml</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#getUixml()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Uixml();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#isAutobuild <em>Autobuild</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Autobuild</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Operation#isAutobuild()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Autobuild();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData <em>Resource Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Data</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData
	 * @generated
	 */
	EClass getMetadataResourceData();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getOperations()
	 * @see #getMetadataResourceData()
	 * @generated
	 */
	EReference getMetadataResourceData_Operations();

	/**
	 * Returns the meta object for the containment reference '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getRoot()
	 * @see #getMetadataResourceData()
	 * @generated
	 */
	EReference getMetadataResourceData_Root();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData#getItems()
	 * @see #getMetadataResourceData()
	 * @generated
	 */
	EReference getMetadataResourceData_Items();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.NamedElement#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.NamedElement#getChineseName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.NamedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.NamedElement#getDescription()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Description();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataItem
	 * @generated
	 */
	EClass getMetadataItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getRefId <em>Ref Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Id</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getRefId()
	 * @see #getMetadataItem()
	 * @generated
	 */
	EAttribute getMetadataItem_RefId();

	/**
	 * Returns the meta object for the container reference '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataItem#getParent()
	 * @see #getMetadataItem()
	 * @generated
	 */
	EReference getMetadataItem_Parent();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataCategory
	 * @generated
	 */
	EClass getMetadataCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getChildren()
	 * @see #getMetadataCategory()
	 * @generated
	 */
	EReference getMetadataCategory_Children();

	/**
	 * Returns the meta object for the reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Items</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getItems()
	 * @see #getMetadataCategory()
	 * @generated
	 */
	EReference getMetadataCategory_Items();

	/**
	 * Returns the meta object for the container reference '{@link com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataCategory#getParent()
	 * @see #getMetadataCategory()
	 * @generated
	 */
	EReference getMetadataCategory_Parent();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList <em>Type Default Value List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Default Value List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList
	 * @generated
	 */
	EClass getTypeDefaultValueList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue <em>Type Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue
	 * @generated
	 */
	EClass getTypeDefaultValue();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList <em>Standard Data Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Data Type List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList
	 * @generated
	 */
	EClass getStandardDataTypeList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.StandardDataType <em>Standard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardDataType
	 * @generated
	 */
	EClass getStandardDataType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList <em>Business Data Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Data Type List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList
	 * @generated
	 */
	EClass getBusinessDataTypeList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataType <em>Business Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataType
	 * @generated
	 */
	EClass getBusinessDataType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getStdType <em>Std Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Std Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getStdType()
	 * @see #getBusinessDataType()
	 * @generated
	 */
	EAttribute getBusinessDataType_StdType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getLength()
	 * @see #getBusinessDataType()
	 * @generated
	 */
	EAttribute getBusinessDataType_Length();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getPrecision <em>Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getPrecision()
	 * @see #getBusinessDataType()
	 * @generated
	 */
	EAttribute getBusinessDataType_Precision();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BusinessDataType#getDefaultValue()
	 * @see #getBusinessDataType()
	 * @generated
	 */
	EAttribute getBusinessDataType_DefaultValue();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.StandardFieldList <em>Standard Field List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Field List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardFieldList
	 * @generated
	 */
	EClass getStandardFieldList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField <em>Standard Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Field</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardField
	 * @generated
	 */
	EClass getStandardField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardField#getLength()
	 * @see #getStandardField()
	 * @generated
	 */
	EAttribute getStandardField_Length();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getPrecision <em>Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardField#getPrecision()
	 * @see #getStandardField()
	 * @generated
	 */
	EAttribute getStandardField_Precision();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardField#getDataType()
	 * @see #getStandardField()
	 * @generated
	 */
	EAttribute getStandardField_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDictionaryType <em>Dictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dictionary Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.StandardField#getDictionaryType()
	 * @see #getStandardField()
	 * @generated
	 */
	EAttribute getStandardField_DictionaryType();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryList <em>Dictionary List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dictionary List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryList
	 * @generated
	 */
	EClass getDictionaryList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryType <em>Dictionary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dictionary Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryType
	 * @generated
	 */
	EClass getDictionaryType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryType#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryType#getItems()
	 * @see #getDictionaryType()
	 * @generated
	 */
	EReference getDictionaryType_Items();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem <em>Dictionary Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dictionary Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem
	 * @generated
	 */
	EClass getDictionaryItem();

	/**
	 * Returns the meta object for the container reference '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getParent()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EReference getDictionaryItem_Parent();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getValue()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EAttribute getDictionaryItem_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getName()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EAttribute getDictionaryItem_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getChineseName()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EAttribute getDictionaryItem_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getConstantName <em>Constant Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getConstantName()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EAttribute getDictionaryItem_ConstantName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.DictionaryItem#getDescription()
	 * @see #getDictionaryItem()
	 * @generated
	 */
	EAttribute getDictionaryItem_Description();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantList <em>Constant List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantList
	 * @generated
	 */
	EClass getConstantList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantItem <em>Constant Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantItem
	 * @generated
	 */
	EClass getConstantItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getDataType()
	 * @see #getConstantItem()
	 * @generated
	 */
	EAttribute getConstantItem_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getValue()
	 * @see #getConstantItem()
	 * @generated
	 */
	EAttribute getConstantItem_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getLength()
	 * @see #getConstantItem()
	 * @generated
	 */
	EAttribute getConstantItem_Length();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getPrecision <em>Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ConstantItem#getPrecision()
	 * @see #getConstantItem()
	 * @generated
	 */
	EAttribute getConstantItem_Precision();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoList <em>Error No List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error No List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoList
	 * @generated
	 */
	EClass getErrorNoList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem <em>Error No Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error No Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem
	 * @generated
	 */
	EClass getErrorNoItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getNo <em>No</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>No</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getNo()
	 * @see #getErrorNoItem()
	 * @generated
	 */
	EAttribute getErrorNoItem_No();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getMessage()
	 * @see #getErrorNoItem()
	 * @generated
	 */
	EAttribute getErrorNoItem_Message();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getConstantName <em>Constant Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getConstantName()
	 * @see #getErrorNoItem()
	 * @generated
	 */
	EAttribute getErrorNoItem_ConstantName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getLevel()
	 * @see #getErrorNoItem()
	 * @generated
	 */
	EAttribute getErrorNoItem_Level();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList <em>General Data Config List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>General Data Config List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList
	 * @generated
	 */
	EClass getGeneralDataConfigList();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList#getItems()
	 * @see #getGeneralDataConfigList()
	 * @generated
	 */
	EReference getGeneralDataConfigList_Items();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem <em>General Data Config Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>General Data Config Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem
	 * @generated
	 */
	EClass getGeneralDataConfigItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getId()
	 * @see #getGeneralDataConfigItem()
	 * @generated
	 */
	EAttribute getGeneralDataConfigItem_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getType()
	 * @see #getGeneralDataConfigItem()
	 * @generated
	 */
	EAttribute getGeneralDataConfigItem_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getValue()
	 * @see #getGeneralDataConfigItem()
	 * @generated
	 */
	EAttribute getGeneralDataConfigItem_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getChineseName <em>Chinese Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chinese Name</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getChineseName()
	 * @see #getGeneralDataConfigItem()
	 * @generated
	 */
	EAttribute getGeneralDataConfigItem_ChineseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getDiscription <em>Discription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Discription</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem#getDiscription()
	 * @see #getGeneralDataConfigItem()
	 * @generated
	 */
	EAttribute getGeneralDataConfigItem_Discription();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem <em>Menu Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuItem
	 * @generated
	 */
	EClass getMenuItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuItem#getUrl()
	 * @see #getMenuItem()
	 * @generated
	 */
	EAttribute getMenuItem_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuItem#getIcon()
	 * @see #getMenuItem()
	 * @generated
	 */
	EAttribute getMenuItem_Icon();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getFunctionProxys <em>Function Proxys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function Proxys</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuItem#getFunctionProxys()
	 * @see #getMenuItem()
	 * @generated
	 */
	EReference getMenuItem_FunctionProxys();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getSubItems <em>Sub Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Items</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuItem#getSubItems()
	 * @see #getMenuItem()
	 * @generated
	 */
	EReference getMenuItem_SubItems();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy <em>Function Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Proxy</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.FunctionProxy
	 * @generated
	 */
	EClass getFunctionProxy();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getFunCode <em>Fun Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fun Code</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getFunCode()
	 * @see #getFunctionProxy()
	 * @generated
	 */
	EAttribute getFunctionProxy_FunCode();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getDescription()
	 * @see #getFunctionProxy()
	 * @generated
	 */
	EAttribute getFunctionProxy_Description();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Function#getSubTransCode <em>Sub Trans Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Trans Code</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Function#getSubTransCode()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_SubTransCode();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.Function#getServID <em>Serv ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Serv ID</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.Function#getServID()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_ServID();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.MenuList <em>Menu List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuList
	 * @generated
	 */
	EClass getMenuList();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.jres.model.metadata.MenuList#getFunctions <em>Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Functions</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MenuList#getFunctions()
	 * @see #getMenuList()
	 * @generated
	 */
	EReference getMenuList_Functions();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.IDRangeItem <em>ID Range Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ID Range Item</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.IDRangeItem
	 * @generated
	 */
	EClass getIDRangeItem();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.IDRange <em>ID Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ID Range</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.IDRange
	 * @generated
	 */
	EClass getIDRange();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.IDRange#getRange <em>Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Range</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.IDRange#getRange()
	 * @see #getIDRange()
	 * @generated
	 */
	EAttribute getIDRange_Range();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfigList <em>Biz Property Config List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Biz Property Config List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfigList
	 * @generated
	 */
	EClass getBizPropertyConfigList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig <em>Biz Property Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Biz Property Config</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig
	 * @generated
	 */
	EClass getBizPropertyConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig#getEname <em>Ename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ename</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig#getEname()
	 * @see #getBizPropertyConfig()
	 * @generated
	 */
	EAttribute getBizPropertyConfig_Ename();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.jres.model.metadata.IDRangeList <em>ID Range List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ID Range List</em>'.
	 * @see com.hundsun.ares.studio.jres.model.metadata.IDRangeList
	 * @generated
	 */
	EClass getIDRangeList();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MetadataFactory getMetadataFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MDModuleCommonPropertyImpl <em>MD Module Common Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MDModuleCommonPropertyImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMDModuleCommonProperty()
		 * @generated
		 */
		EClass MD_MODULE_COMMON_PROPERTY = eINSTANCE.getMDModuleCommonProperty();

		/**
		 * The meta object literal for the '<em><b>Use Ref Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE = eINSTANCE.getMDModuleCommonProperty_UseRefFeature();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.OperationImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__TITLE = eINSTANCE.getOperation_Title();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__FILE = eINSTANCE.getOperation_File();

		/**
		 * The meta object literal for the '<em><b>Out Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__OUT_PATH = eINSTANCE.getOperation_OutPath();

		/**
		 * The meta object literal for the '<em><b>Function Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__FUNCTION_NAME = eINSTANCE.getOperation_FunctionName();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__CODE = eINSTANCE.getOperation_Code();

		/**
		 * The meta object literal for the '<em><b>Uixml</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__UIXML = eINSTANCE.getOperation_Uixml();

		/**
		 * The meta object literal for the '<em><b>Autobuild</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__AUTOBUILD = eINSTANCE.getOperation_Autobuild();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl <em>Resource Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataResourceDataImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataResourceData()
		 * @generated
		 */
		EClass METADATA_RESOURCE_DATA = eINSTANCE.getMetadataResourceData();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_RESOURCE_DATA__OPERATIONS = eINSTANCE.getMetadataResourceData_Operations();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_RESOURCE_DATA__ROOT = eINSTANCE.getMetadataResourceData_Root();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_RESOURCE_DATA__ITEMS = eINSTANCE.getMetadataResourceData_Items();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.NamedElementImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__CHINESE_NAME = eINSTANCE.getNamedElement_ChineseName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__DESCRIPTION = eINSTANCE.getNamedElement_Description();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataItem()
		 * @generated
		 */
		EClass METADATA_ITEM = eINSTANCE.getMetadataItem();

		/**
		 * The meta object literal for the '<em><b>Ref Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA_ITEM__REF_ID = eINSTANCE.getMetadataItem_RefId();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_ITEM__PARENT = eINSTANCE.getMetadataItem_Parent();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMetadataCategory()
		 * @generated
		 */
		EClass METADATA_CATEGORY = eINSTANCE.getMetadataCategory();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_CATEGORY__CHILDREN = eINSTANCE.getMetadataCategory_Children();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_CATEGORY__ITEMS = eINSTANCE.getMetadataCategory_Items();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METADATA_CATEGORY__PARENT = eINSTANCE.getMetadataCategory_Parent();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueListImpl <em>Type Default Value List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getTypeDefaultValueList()
		 * @generated
		 */
		EClass TYPE_DEFAULT_VALUE_LIST = eINSTANCE.getTypeDefaultValueList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueImpl <em>Type Default Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getTypeDefaultValue()
		 * @generated
		 */
		EClass TYPE_DEFAULT_VALUE = eINSTANCE.getTypeDefaultValue();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeListImpl <em>Standard Data Type List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardDataTypeList()
		 * @generated
		 */
		EClass STANDARD_DATA_TYPE_LIST = eINSTANCE.getStandardDataTypeList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeImpl <em>Standard Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardDataTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardDataType()
		 * @generated
		 */
		EClass STANDARD_DATA_TYPE = eINSTANCE.getStandardDataType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeListImpl <em>Business Data Type List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBusinessDataTypeList()
		 * @generated
		 */
		EClass BUSINESS_DATA_TYPE_LIST = eINSTANCE.getBusinessDataTypeList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeImpl <em>Business Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BusinessDataTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBusinessDataType()
		 * @generated
		 */
		EClass BUSINESS_DATA_TYPE = eINSTANCE.getBusinessDataType();

		/**
		 * The meta object literal for the '<em><b>Std Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_DATA_TYPE__STD_TYPE = eINSTANCE.getBusinessDataType_StdType();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_DATA_TYPE__LENGTH = eINSTANCE.getBusinessDataType_Length();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_DATA_TYPE__PRECISION = eINSTANCE.getBusinessDataType_Precision();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_DATA_TYPE__DEFAULT_VALUE = eINSTANCE.getBusinessDataType_DefaultValue();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldListImpl <em>Standard Field List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardFieldList()
		 * @generated
		 */
		EClass STANDARD_FIELD_LIST = eINSTANCE.getStandardFieldList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl <em>Standard Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getStandardField()
		 * @generated
		 */
		EClass STANDARD_FIELD = eINSTANCE.getStandardField();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_FIELD__LENGTH = eINSTANCE.getStandardField_Length();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_FIELD__PRECISION = eINSTANCE.getStandardField_Precision();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_FIELD__DATA_TYPE = eINSTANCE.getStandardField_DataType();

		/**
		 * The meta object literal for the '<em><b>Dictionary Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_FIELD__DICTIONARY_TYPE = eINSTANCE.getStandardField_DictionaryType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryListImpl <em>Dictionary List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryList()
		 * @generated
		 */
		EClass DICTIONARY_LIST = eINSTANCE.getDictionaryList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryTypeImpl <em>Dictionary Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryTypeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryType()
		 * @generated
		 */
		EClass DICTIONARY_TYPE = eINSTANCE.getDictionaryType();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DICTIONARY_TYPE__ITEMS = eINSTANCE.getDictionaryType_Items();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl <em>Dictionary Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getDictionaryItem()
		 * @generated
		 */
		EClass DICTIONARY_ITEM = eINSTANCE.getDictionaryItem();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DICTIONARY_ITEM__PARENT = eINSTANCE.getDictionaryItem_Parent();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DICTIONARY_ITEM__VALUE = eINSTANCE.getDictionaryItem_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DICTIONARY_ITEM__NAME = eINSTANCE.getDictionaryItem_Name();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DICTIONARY_ITEM__CHINESE_NAME = eINSTANCE.getDictionaryItem_ChineseName();

		/**
		 * The meta object literal for the '<em><b>Constant Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DICTIONARY_ITEM__CONSTANT_NAME = eINSTANCE.getDictionaryItem_ConstantName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DICTIONARY_ITEM__DESCRIPTION = eINSTANCE.getDictionaryItem_Description();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ConstantListImpl <em>Constant List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ConstantListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getConstantList()
		 * @generated
		 */
		EClass CONSTANT_LIST = eINSTANCE.getConstantList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ConstantItemImpl <em>Constant Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ConstantItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getConstantItem()
		 * @generated
		 */
		EClass CONSTANT_ITEM = eINSTANCE.getConstantItem();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_ITEM__DATA_TYPE = eINSTANCE.getConstantItem_DataType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_ITEM__VALUE = eINSTANCE.getConstantItem_Value();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_ITEM__LENGTH = eINSTANCE.getConstantItem_Length();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_ITEM__PRECISION = eINSTANCE.getConstantItem_Precision();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoListImpl <em>Error No List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getErrorNoList()
		 * @generated
		 */
		EClass ERROR_NO_LIST = eINSTANCE.getErrorNoList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl <em>Error No Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getErrorNoItem()
		 * @generated
		 */
		EClass ERROR_NO_ITEM = eINSTANCE.getErrorNoItem();

		/**
		 * The meta object literal for the '<em><b>No</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_NO_ITEM__NO = eINSTANCE.getErrorNoItem_No();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_NO_ITEM__MESSAGE = eINSTANCE.getErrorNoItem_Message();

		/**
		 * The meta object literal for the '<em><b>Constant Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_NO_ITEM__CONSTANT_NAME = eINSTANCE.getErrorNoItem_ConstantName();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_NO_ITEM__LEVEL = eINSTANCE.getErrorNoItem_Level();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigListImpl <em>General Data Config List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getGeneralDataConfigList()
		 * @generated
		 */
		EClass GENERAL_DATA_CONFIG_LIST = eINSTANCE.getGeneralDataConfigList();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERAL_DATA_CONFIG_LIST__ITEMS = eINSTANCE.getGeneralDataConfigList_Items();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl <em>General Data Config Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getGeneralDataConfigItem()
		 * @generated
		 */
		EClass GENERAL_DATA_CONFIG_ITEM = eINSTANCE.getGeneralDataConfigItem();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_DATA_CONFIG_ITEM__ID = eINSTANCE.getGeneralDataConfigItem_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_DATA_CONFIG_ITEM__TYPE = eINSTANCE.getGeneralDataConfigItem_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_DATA_CONFIG_ITEM__VALUE = eINSTANCE.getGeneralDataConfigItem_Value();

		/**
		 * The meta object literal for the '<em><b>Chinese Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME = eINSTANCE.getGeneralDataConfigItem_ChineseName();

		/**
		 * The meta object literal for the '<em><b>Discription</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_DATA_CONFIG_ITEM__DISCRIPTION = eINSTANCE.getGeneralDataConfigItem_Discription();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl <em>Menu Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MenuItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMenuItem()
		 * @generated
		 */
		EClass MENU_ITEM = eINSTANCE.getMenuItem();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_ITEM__URL = eINSTANCE.getMenuItem_Url();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_ITEM__ICON = eINSTANCE.getMenuItem_Icon();

		/**
		 * The meta object literal for the '<em><b>Function Proxys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_ITEM__FUNCTION_PROXYS = eINSTANCE.getMenuItem_FunctionProxys();

		/**
		 * The meta object literal for the '<em><b>Sub Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_ITEM__SUB_ITEMS = eINSTANCE.getMenuItem_SubItems();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionProxyImpl <em>Function Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.FunctionProxyImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getFunctionProxy()
		 * @generated
		 */
		EClass FUNCTION_PROXY = eINSTANCE.getFunctionProxy();

		/**
		 * The meta object literal for the '<em><b>Fun Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_PROXY__FUN_CODE = eINSTANCE.getFunctionProxy_FunCode();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_PROXY__DESCRIPTION = eINSTANCE.getFunctionProxy_Description();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.FunctionImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Sub Trans Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__SUB_TRANS_CODE = eINSTANCE.getFunction_SubTransCode();

		/**
		 * The meta object literal for the '<em><b>Serv ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__SERV_ID = eINSTANCE.getFunction_ServID();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.MenuListImpl <em>Menu List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MenuListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getMenuList()
		 * @generated
		 */
		EClass MENU_LIST = eINSTANCE.getMenuList();

		/**
		 * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_LIST__FUNCTIONS = eINSTANCE.getMenuList_Functions();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeItemImpl <em>ID Range Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeItemImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRangeItem()
		 * @generated
		 */
		EClass ID_RANGE_ITEM = eINSTANCE.getIDRangeItem();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeImpl <em>ID Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRange()
		 * @generated
		 */
		EClass ID_RANGE = eINSTANCE.getIDRange();

		/**
		 * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ID_RANGE__RANGE = eINSTANCE.getIDRange_Range();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigListImpl <em>Biz Property Config List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBizPropertyConfigList()
		 * @generated
		 */
		EClass BIZ_PROPERTY_CONFIG_LIST = eINSTANCE.getBizPropertyConfigList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigImpl <em>Biz Property Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getBizPropertyConfig()
		 * @generated
		 */
		EClass BIZ_PROPERTY_CONFIG = eINSTANCE.getBizPropertyConfig();

		/**
		 * The meta object literal for the '<em><b>Ename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BIZ_PROPERTY_CONFIG__ENAME = eINSTANCE.getBizPropertyConfig_Ename();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeListImpl <em>ID Range List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.IDRangeListImpl
		 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataPackageImpl#getIDRangeList()
		 * @generated
		 */
		EClass ID_RANGE_LIST = eINSTANCE.getIDRangeList();

	}

} //MetadataPackage
