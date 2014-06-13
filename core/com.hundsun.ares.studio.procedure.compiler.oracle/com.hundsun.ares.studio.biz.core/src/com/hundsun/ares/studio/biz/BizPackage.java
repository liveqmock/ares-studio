/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
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
 * @see com.hundsun.ares.studio.biz.BizFactory
 * @model kind="package"
 * @generated
 */
public interface BizPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "biz";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/biz/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "biz";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BizPackage eINSTANCE = com.hundsun.ares.studio.biz.impl.BizPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.ParameterImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ID = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Param Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__PARAM_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Real Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__REAL_TYPE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DESCRIPTION = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__MULTIPLICITY = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DEFAULT_VALUE = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__FLAGS = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__COMMENTS = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl <em>Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.BizInterfaceImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getBizInterface()
	 * @generated
	 */
	int BIZ_INTERFACE = 1;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__INPUT_COLLECTION = 0;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__OUTPUT_COLLECTION = 1;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__INPUT_PARAMETERS = 2;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__OUTPUT_PARAMETERS = 3;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__INTERFACE_FLAG = 4;

	/**
	 * The feature id for the '<em><b>Error Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE__ERROR_INFOS = 5;

	/**
	 * The number of structural features of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_INTERFACE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.ARESObjectImpl <em>ARES Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.ARESObjectImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getARESObject()
	 * @generated
	 */
	int ARES_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__DATA = CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__DATA2 = CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__NAME = CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__CHINESE_NAME = CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__DESCRIPTION = CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__OBJECT_ID = CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__GROUP = CorePackage.JRES_RESOURCE_INFO__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__HISTORIES = CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__FULLY_QUALIFIED_NAME = CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT__PROPERTIES = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>ARES Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARES_OBJECT_FEATURE_COUNT = CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.StandardObjFieldListImpl <em>Standard Obj Field List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.StandardObjFieldListImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getStandardObjFieldList()
	 * @generated
	 */
	int STANDARD_OBJ_FIELD_LIST = 3;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__DATA = MetadataPackage.METADATA_RESOURCE_DATA__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__DATA2 = MetadataPackage.METADATA_RESOURCE_DATA__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__NAME = MetadataPackage.METADATA_RESOURCE_DATA__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__CHINESE_NAME = MetadataPackage.METADATA_RESOURCE_DATA__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__DESCRIPTION = MetadataPackage.METADATA_RESOURCE_DATA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__OBJECT_ID = MetadataPackage.METADATA_RESOURCE_DATA__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__GROUP = MetadataPackage.METADATA_RESOURCE_DATA__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__HISTORIES = MetadataPackage.METADATA_RESOURCE_DATA__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__FULLY_QUALIFIED_NAME = MetadataPackage.METADATA_RESOURCE_DATA__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__OPERATIONS = MetadataPackage.METADATA_RESOURCE_DATA__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__ROOT = MetadataPackage.METADATA_RESOURCE_DATA__ROOT;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST__ITEMS = MetadataPackage.METADATA_RESOURCE_DATA__ITEMS;

	/**
	 * The number of structural features of the '<em>Standard Obj Field List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_LIST_FEATURE_COUNT = MetadataPackage.METADATA_RESOURCE_DATA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.StandardObjFieldImpl <em>Standard Obj Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.StandardObjFieldImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getStandardObjField()
	 * @generated
	 */
	int STANDARD_OBJ_FIELD = 4;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__DATA = MetadataPackage.METADATA_ITEM__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__DATA2 = MetadataPackage.METADATA_ITEM__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__NAME = MetadataPackage.METADATA_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__CHINESE_NAME = MetadataPackage.METADATA_ITEM__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__DESCRIPTION = MetadataPackage.METADATA_ITEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Ref Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__REF_ID = MetadataPackage.METADATA_ITEM__REF_ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__PARENT = MetadataPackage.METADATA_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD__TYPE = MetadataPackage.METADATA_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Standard Obj Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_OBJ_FIELD_FEATURE_COUNT = MetadataPackage.METADATA_ITEM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl <em>Error Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.impl.ErrorInfoImpl
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getErrorInfo()
	 * @generated
	 */
	int ERROR_INFO = 5;

	/**
	 * The feature id for the '<em><b>Error No</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_INFO__ERROR_NO = 0;

	/**
	 * The feature id for the '<em><b>Error Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_INFO__ERROR_INFO = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_INFO__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_INFO__LEVEL = 3;

	/**
	 * The number of structural features of the '<em>Error Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_INFO_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.ParamType <em>Param Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.ParamType
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getParamType()
	 * @generated
	 */
	int PARAM_TYPE = 6;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.biz.Multiplicity <em>Multiplicity</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.biz.Multiplicity
	 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getMultiplicity()
	 * @generated
	 */
	int MULTIPLICITY = 7;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getId()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getParamType <em>Param Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Param Type</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getParamType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_ParamType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getRealType <em>Real Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Type</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getRealType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_RealType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getDescription()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getMultiplicity()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Multiplicity();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getDefaultValue()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getFlags()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Flags();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.Parameter#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comments</em>'.
	 * @see com.hundsun.ares.studio.biz.Parameter#getComments()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Comments();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.BizInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface
	 * @generated
	 */
	EClass getBizInterface();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.BizInterface#isInputCollection <em>Input Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Collection</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#isInputCollection()
	 * @see #getBizInterface()
	 * @generated
	 */
	EAttribute getBizInterface_InputCollection();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.BizInterface#isOutputCollection <em>Output Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Collection</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#isOutputCollection()
	 * @see #getBizInterface()
	 * @generated
	 */
	EAttribute getBizInterface_OutputCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.biz.BizInterface#getInputParameters <em>Input Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Parameters</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#getInputParameters()
	 * @see #getBizInterface()
	 * @generated
	 */
	EReference getBizInterface_InputParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.biz.BizInterface#getOutputParameters <em>Output Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Parameters</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#getOutputParameters()
	 * @see #getBizInterface()
	 * @generated
	 */
	EReference getBizInterface_OutputParameters();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.BizInterface#getInterfaceFlag <em>Interface Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface Flag</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#getInterfaceFlag()
	 * @see #getBizInterface()
	 * @generated
	 */
	EAttribute getBizInterface_InterfaceFlag();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.biz.BizInterface#getErrorInfos <em>Error Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Error Infos</em>'.
	 * @see com.hundsun.ares.studio.biz.BizInterface#getErrorInfos()
	 * @see #getBizInterface()
	 * @generated
	 */
	EReference getBizInterface_ErrorInfos();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.ARESObject <em>ARES Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ARES Object</em>'.
	 * @see com.hundsun.ares.studio.biz.ARESObject
	 * @generated
	 */
	EClass getARESObject();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.biz.ARESObject#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see com.hundsun.ares.studio.biz.ARESObject#getProperties()
	 * @see #getARESObject()
	 * @generated
	 */
	EReference getARESObject_Properties();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.StandardObjFieldList <em>Standard Obj Field List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Obj Field List</em>'.
	 * @see com.hundsun.ares.studio.biz.StandardObjFieldList
	 * @generated
	 */
	EClass getStandardObjFieldList();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.StandardObjField <em>Standard Obj Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Obj Field</em>'.
	 * @see com.hundsun.ares.studio.biz.StandardObjField
	 * @generated
	 */
	EClass getStandardObjField();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.StandardObjField#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.biz.StandardObjField#getType()
	 * @see #getStandardObjField()
	 * @generated
	 */
	EAttribute getStandardObjField_Type();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.biz.ErrorInfo <em>Error Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error Info</em>'.
	 * @see com.hundsun.ares.studio.biz.ErrorInfo
	 * @generated
	 */
	EClass getErrorInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorNo <em>Error No</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error No</em>'.
	 * @see com.hundsun.ares.studio.biz.ErrorInfo#getErrorNo()
	 * @see #getErrorInfo()
	 * @generated
	 */
	EAttribute getErrorInfo_ErrorNo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorInfo <em>Error Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Info</em>'.
	 * @see com.hundsun.ares.studio.biz.ErrorInfo#getErrorInfo()
	 * @see #getErrorInfo()
	 * @generated
	 */
	EAttribute getErrorInfo_ErrorInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.ErrorInfo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.biz.ErrorInfo#getDescription()
	 * @see #getErrorInfo()
	 * @generated
	 */
	EAttribute getErrorInfo_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.biz.ErrorInfo#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see com.hundsun.ares.studio.biz.ErrorInfo#getLevel()
	 * @see #getErrorInfo()
	 * @generated
	 */
	EAttribute getErrorInfo_Level();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.biz.ParamType <em>Param Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Param Type</em>'.
	 * @see com.hundsun.ares.studio.biz.ParamType
	 * @generated
	 */
	EEnum getParamType();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.biz.Multiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Multiplicity</em>'.
	 * @see com.hundsun.ares.studio.biz.Multiplicity
	 * @generated
	 */
	EEnum getMultiplicity();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BizFactory getBizFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.ParameterImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__ID = eINSTANCE.getParameter_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Param Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__PARAM_TYPE = eINSTANCE.getParameter_ParamType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '<em><b>Real Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__REAL_TYPE = eINSTANCE.getParameter_RealType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DESCRIPTION = eINSTANCE.getParameter_Description();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__MULTIPLICITY = eINSTANCE.getParameter_Multiplicity();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DEFAULT_VALUE = eINSTANCE.getParameter_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__FLAGS = eINSTANCE.getParameter_Flags();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__COMMENTS = eINSTANCE.getParameter_Comments();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.BizInterfaceImpl <em>Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.BizInterfaceImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getBizInterface()
		 * @generated
		 */
		EClass BIZ_INTERFACE = eINSTANCE.getBizInterface();

		/**
		 * The meta object literal for the '<em><b>Input Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BIZ_INTERFACE__INPUT_COLLECTION = eINSTANCE.getBizInterface_InputCollection();

		/**
		 * The meta object literal for the '<em><b>Output Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BIZ_INTERFACE__OUTPUT_COLLECTION = eINSTANCE.getBizInterface_OutputCollection();

		/**
		 * The meta object literal for the '<em><b>Input Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BIZ_INTERFACE__INPUT_PARAMETERS = eINSTANCE.getBizInterface_InputParameters();

		/**
		 * The meta object literal for the '<em><b>Output Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BIZ_INTERFACE__OUTPUT_PARAMETERS = eINSTANCE.getBizInterface_OutputParameters();

		/**
		 * The meta object literal for the '<em><b>Interface Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BIZ_INTERFACE__INTERFACE_FLAG = eINSTANCE.getBizInterface_InterfaceFlag();

		/**
		 * The meta object literal for the '<em><b>Error Infos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BIZ_INTERFACE__ERROR_INFOS = eINSTANCE.getBizInterface_ErrorInfos();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.ARESObjectImpl <em>ARES Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.ARESObjectImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getARESObject()
		 * @generated
		 */
		EClass ARES_OBJECT = eINSTANCE.getARESObject();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARES_OBJECT__PROPERTIES = eINSTANCE.getARESObject_Properties();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.StandardObjFieldListImpl <em>Standard Obj Field List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.StandardObjFieldListImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getStandardObjFieldList()
		 * @generated
		 */
		EClass STANDARD_OBJ_FIELD_LIST = eINSTANCE.getStandardObjFieldList();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.StandardObjFieldImpl <em>Standard Obj Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.StandardObjFieldImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getStandardObjField()
		 * @generated
		 */
		EClass STANDARD_OBJ_FIELD = eINSTANCE.getStandardObjField();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_OBJ_FIELD__TYPE = eINSTANCE.getStandardObjField_Type();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl <em>Error Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.impl.ErrorInfoImpl
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getErrorInfo()
		 * @generated
		 */
		EClass ERROR_INFO = eINSTANCE.getErrorInfo();

		/**
		 * The meta object literal for the '<em><b>Error No</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_INFO__ERROR_NO = eINSTANCE.getErrorInfo_ErrorNo();

		/**
		 * The meta object literal for the '<em><b>Error Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_INFO__ERROR_INFO = eINSTANCE.getErrorInfo_ErrorInfo();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_INFO__DESCRIPTION = eINSTANCE.getErrorInfo_Description();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_INFO__LEVEL = eINSTANCE.getErrorInfo_Level();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.ParamType <em>Param Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.ParamType
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getParamType()
		 * @generated
		 */
		EEnum PARAM_TYPE = eINSTANCE.getParamType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.biz.Multiplicity <em>Multiplicity</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.biz.Multiplicity
		 * @see com.hundsun.ares.studio.biz.impl.BizPackageImpl#getMultiplicity()
		 * @generated
		 */
		EEnum MULTIPLICITY = eINSTANCE.getMultiplicity();

	}

} //BizPackage
