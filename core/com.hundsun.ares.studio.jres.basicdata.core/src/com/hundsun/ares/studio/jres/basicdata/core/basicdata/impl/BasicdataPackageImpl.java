/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataItem;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.util.BasicdataValidator;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BasicdataPackageImpl extends EPackageImpl implements BasicdataPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass epacakgeDefineListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageDefineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass masterSlaveTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass masterSlaveLinkTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicDataBaseModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardFieldPackageDefineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardFieldModelAndDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardFieldColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicDataFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicdataItemEClass = null;

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
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BasicdataPackageImpl() {
		super(eNS_URI, BasicdataFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BasicdataPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BasicdataPackage init() {
		if (isInited) return (BasicdataPackage)EPackage.Registry.INSTANCE.getEPackage(BasicdataPackage.eNS_URI);

		// Obtain or create and register package
		BasicdataPackageImpl theBasicdataPackage = (BasicdataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BasicdataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BasicdataPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MetadataPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBasicdataPackage.createPackageContents();

		// Initialize created meta-data
		theBasicdataPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theBasicdataPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return BasicdataValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theBasicdataPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BasicdataPackage.eNS_URI, theBasicdataPackage);
		return theBasicdataPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEpacakgeDefineList() {
		return epacakgeDefineListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEpacakgeDefineList_Items() {
		return (EReference)epacakgeDefineListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageDefine() {
		return packageDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageDefine_Url() {
		return (EAttribute)packageDefineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageDefine_Type() {
		return (EAttribute)packageDefineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleTable() {
		return singleTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSingleTable_Master() {
		return (EAttribute)singleTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMasterSlaveTable() {
		return masterSlaveTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMasterSlaveTable_Master() {
		return (EAttribute)masterSlaveTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMasterSlaveTable_Slave() {
		return (EAttribute)masterSlaveTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMasterSlaveLinkTable() {
		return masterSlaveLinkTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMasterSlaveLinkTable_Master() {
		return (EAttribute)masterSlaveLinkTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMasterSlaveLinkTable_Slave() {
		return (EAttribute)masterSlaveLinkTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMasterSlaveLinkTable_Link() {
		return (EAttribute)masterSlaveLinkTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicDataBaseModel() {
		return basicDataBaseModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataBaseModel_Extend() {
		return (EAttribute)basicDataBaseModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataBaseModel_File() {
		return (EAttribute)basicDataBaseModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardFieldPackageDefine() {
		return standardFieldPackageDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStandardFieldPackageDefine_Fields() {
		return (EReference)standardFieldPackageDefineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardFieldModelAndData() {
		return standardFieldModelAndDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStandardFieldModelAndData_Model() {
		return (EReference)standardFieldModelAndDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStandardFieldModelAndData_Data() {
		return (EReference)standardFieldModelAndDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStandardFieldColumn() {
		return standardFieldColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStandardFieldColumn_StandardField() {
		return (EAttribute)standardFieldColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicDataField() {
		return basicDataFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_Name() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_ChineseName() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_Description() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_DataType() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_DefaultValue() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_Mark() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_Comments() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicDataField_ColumnType() {
		return (EAttribute)basicDataFieldEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicdataItem() {
		return basicdataItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicdataFactory getBasicdataFactory() {
		return (BasicdataFactory)getEFactoryInstance();
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
		epacakgeDefineListEClass = createEClass(EPACAKGE_DEFINE_LIST);
		createEReference(epacakgeDefineListEClass, EPACAKGE_DEFINE_LIST__ITEMS);

		packageDefineEClass = createEClass(PACKAGE_DEFINE);
		createEAttribute(packageDefineEClass, PACKAGE_DEFINE__URL);
		createEAttribute(packageDefineEClass, PACKAGE_DEFINE__TYPE);

		singleTableEClass = createEClass(SINGLE_TABLE);
		createEAttribute(singleTableEClass, SINGLE_TABLE__MASTER);

		masterSlaveTableEClass = createEClass(MASTER_SLAVE_TABLE);
		createEAttribute(masterSlaveTableEClass, MASTER_SLAVE_TABLE__MASTER);
		createEAttribute(masterSlaveTableEClass, MASTER_SLAVE_TABLE__SLAVE);

		masterSlaveLinkTableEClass = createEClass(MASTER_SLAVE_LINK_TABLE);
		createEAttribute(masterSlaveLinkTableEClass, MASTER_SLAVE_LINK_TABLE__MASTER);
		createEAttribute(masterSlaveLinkTableEClass, MASTER_SLAVE_LINK_TABLE__SLAVE);
		createEAttribute(masterSlaveLinkTableEClass, MASTER_SLAVE_LINK_TABLE__LINK);

		basicDataBaseModelEClass = createEClass(BASIC_DATA_BASE_MODEL);
		createEAttribute(basicDataBaseModelEClass, BASIC_DATA_BASE_MODEL__EXTEND);
		createEAttribute(basicDataBaseModelEClass, BASIC_DATA_BASE_MODEL__FILE);

		standardFieldPackageDefineEClass = createEClass(STANDARD_FIELD_PACKAGE_DEFINE);
		createEReference(standardFieldPackageDefineEClass, STANDARD_FIELD_PACKAGE_DEFINE__FIELDS);

		standardFieldModelAndDataEClass = createEClass(STANDARD_FIELD_MODEL_AND_DATA);
		createEReference(standardFieldModelAndDataEClass, STANDARD_FIELD_MODEL_AND_DATA__MODEL);
		createEReference(standardFieldModelAndDataEClass, STANDARD_FIELD_MODEL_AND_DATA__DATA);

		standardFieldColumnEClass = createEClass(STANDARD_FIELD_COLUMN);
		createEAttribute(standardFieldColumnEClass, STANDARD_FIELD_COLUMN__STANDARD_FIELD);

		basicDataFieldEClass = createEClass(BASIC_DATA_FIELD);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__NAME);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__CHINESE_NAME);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__DESCRIPTION);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__DATA_TYPE);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__DEFAULT_VALUE);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__MARK);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__COMMENTS);
		createEAttribute(basicDataFieldEClass, BASIC_DATA_FIELD__COLUMN_TYPE);

		basicdataItemEClass = createEClass(BASICDATA_ITEM);
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
		MetadataPackage theMetadataPackage = (MetadataPackage)EPackage.Registry.INSTANCE.getEPackage(MetadataPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		epacakgeDefineListEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		singleTableEClass.getESuperTypes().add(this.getPackageDefine());
		masterSlaveTableEClass.getESuperTypes().add(this.getPackageDefine());
		masterSlaveLinkTableEClass.getESuperTypes().add(this.getPackageDefine());
		EGenericType g1 = createEGenericType(theMetadataPackage.getMetadataResourceData());
		EGenericType g2 = createEGenericType(this.getBasicdataItem());
		g1.getETypeArguments().add(g2);
		basicDataBaseModelEClass.getEGenericSuperTypes().add(g1);
		standardFieldPackageDefineEClass.getESuperTypes().add(this.getPackageDefine());
		standardFieldModelAndDataEClass.getESuperTypes().add(theCorePackage.getIReferenceProvider());
		basicdataItemEClass.getESuperTypes().add(theMetadataPackage.getMetadataItem());

		// Initialize classes and features; add operations and parameters
		initEClass(epacakgeDefineListEClass, EpacakgeDefineList.class, "EpacakgeDefineList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEpacakgeDefineList_Items(), this.getPackageDefine(), null, "items", null, 0, -1, EpacakgeDefineList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageDefineEClass, PackageDefine.class, "PackageDefine", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageDefine_Url(), ecorePackage.getEString(), "url", null, 0, 1, PackageDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageDefine_Type(), ecorePackage.getEString(), "type", "", 0, 1, PackageDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleTableEClass, SingleTable.class, "SingleTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSingleTable_Master(), ecorePackage.getEString(), "master", null, 0, 1, SingleTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(masterSlaveTableEClass, MasterSlaveTable.class, "MasterSlaveTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMasterSlaveTable_Master(), ecorePackage.getEString(), "master", null, 0, 1, MasterSlaveTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMasterSlaveTable_Slave(), ecorePackage.getEString(), "slave", null, 0, 1, MasterSlaveTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(masterSlaveLinkTableEClass, MasterSlaveLinkTable.class, "MasterSlaveLinkTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMasterSlaveLinkTable_Master(), ecorePackage.getEString(), "master", null, 0, 1, MasterSlaveLinkTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMasterSlaveLinkTable_Slave(), ecorePackage.getEString(), "slave", null, 0, 1, MasterSlaveLinkTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMasterSlaveLinkTable_Link(), ecorePackage.getEString(), "link", null, 0, 1, MasterSlaveLinkTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(basicDataBaseModelEClass, BasicDataBaseModel.class, "BasicDataBaseModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBasicDataBaseModel_Extend(), ecorePackage.getEString(), "extend", "", 0, 1, BasicDataBaseModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataBaseModel_File(), ecorePackage.getEString(), "file", null, 0, 1, BasicDataBaseModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(standardFieldPackageDefineEClass, StandardFieldPackageDefine.class, "StandardFieldPackageDefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStandardFieldPackageDefine_Fields(), this.getStandardFieldColumn(), null, "fields", null, 0, -1, StandardFieldPackageDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(standardFieldModelAndDataEClass, StandardFieldModelAndData.class, "StandardFieldModelAndData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStandardFieldModelAndData_Model(), this.getStandardFieldPackageDefine(), null, "model", null, 1, 1, StandardFieldModelAndData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStandardFieldModelAndData_Data(), this.getBasicDataBaseModel(), null, "data", null, 1, 1, StandardFieldModelAndData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(standardFieldColumnEClass, StandardFieldColumn.class, "StandardFieldColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStandardFieldColumn_StandardField(), ecorePackage.getEString(), "standardField", null, 0, 1, StandardFieldColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(basicDataFieldEClass, BasicDataField.class, "BasicDataField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBasicDataField_Name(), ecorePackage.getEString(), "name", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_ChineseName(), ecorePackage.getEString(), "chineseName", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_Description(), ecorePackage.getEString(), "description", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_DataType(), ecorePackage.getEString(), "dataType", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_Mark(), ecorePackage.getEString(), "mark", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_Comments(), ecorePackage.getEString(), "comments", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicDataField_ColumnType(), ecorePackage.getEInt(), "columnType", null, 0, 1, BasicDataField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(basicdataItemEClass, BasicdataItem.class, "BasicdataItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		  (epacakgeDefineListEClass, 
		   source, 
		   new String[] {
			 "constraints", "url"
		   });		
		addAnnotation
		  (singleTableEClass, 
		   source, 
		   new String[] {
			 "constraints", "master"
		   });		
		addAnnotation
		  (masterSlaveTableEClass, 
		   source, 
		   new String[] {
			 "constraints", "master slave"
		   });		
		addAnnotation
		  (masterSlaveLinkTableEClass, 
		   source, 
		   new String[] {
			 "constraints", "master slave link"
		   });		
		addAnnotation
		  (standardFieldColumnEClass, 
		   source, 
		   new String[] {
			 "constraints", "standardField"
		   });
	}

} //BasicdataPackageImpl
