/**
 */
package com.hundsun.ares.studio.core.model.impl;

import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.ExtensibleModelAttribute;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.IJSONData;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList;
import com.hundsun.ares.studio.core.model.ProjectExtensibleModel;
import com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.ReferenceWithNamespace;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;

import com.hundsun.ares.studio.core.model.util.CoreValidator;

import java.util.Map;

import org.dom4j.Document;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jresResourceInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicResourceInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass revisionHistoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extensibleModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eStringToEObjectMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceWithNamespaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iReferenceProviderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ijsonDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extensibleModelConfigPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extensibleModelAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userExtensiblePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleExtensibleModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectExtensibleModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectRevisionHistoryPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleRevisionHistoryListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dom4jDocumentEDataType = null;

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
	 * @see com.hundsun.ares.studio.core.model.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CorePackage init() {
		if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Obtain or create and register package
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCorePackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theCorePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return CoreValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJRESResourceInfo() {
		return jresResourceInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJRESResourceInfo_Histories() {
		return (EReference)jresResourceInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJRESResourceInfo_FullyQualifiedName() {
		return (EAttribute)jresResourceInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicResourceInfo() {
		return basicResourceInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicResourceInfo_Name() {
		return (EAttribute)basicResourceInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicResourceInfo_ChineseName() {
		return (EAttribute)basicResourceInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicResourceInfo_Description() {
		return (EAttribute)basicResourceInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicResourceInfo_ObjectId() {
		return (EAttribute)basicResourceInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicResourceInfo_Group() {
		return (EAttribute)basicResourceInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRevisionHistory() {
		return revisionHistoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_ModifiedDate() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_Version() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_OrderNumber() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_ModifiedBy() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_Charger() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_ModifiedReason() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_Modified() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_Comment() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevisionHistory_Location() {
		return (EAttribute)revisionHistoryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtensibleModel() {
		return extensibleModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtensibleModel_Data() {
		return (EReference)extensibleModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtensibleModel_Data2() {
		return (EReference)extensibleModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEStringToEObjectMapEntry() {
		return eStringToEObjectMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEStringToEObjectMapEntry_Key() {
		return (EAttribute)eStringToEObjectMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEStringToEObjectMapEntry_Value() {
		return (EReference)eStringToEObjectMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReference_Type() {
		return (EAttribute)referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceWithNamespace() {
		return referenceWithNamespaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIReferenceProvider() {
		return iReferenceProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJSONData() {
		return ijsonDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtensibleModelConfigProperty() {
		return extensibleModelConfigPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelConfigProperty_Xml() {
		return (EAttribute)extensibleModelConfigPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtensibleModelConfigProperty_Attributes() {
		return (EReference)extensibleModelConfigPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelConfigProperty_XmlCache() {
		return (EAttribute)extensibleModelConfigPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtensibleModelAttribute() {
		return extensibleModelAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_Uri() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_ClassName() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_Key() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_Lable() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_Type() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtensibleModelAttribute_Validate() {
		return (EAttribute)extensibleModelAttributeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserExtensibleProperty() {
		return userExtensiblePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserExtensibleProperty_Map() {
		return (EReference)userExtensiblePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleExtensibleModel() {
		return moduleExtensibleModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProjectExtensibleModel() {
		return projectExtensibleModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProjectRevisionHistoryProperty() {
		return projectRevisionHistoryPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProjectRevisionHistoryProperty_Histories() {
		return (EReference)projectRevisionHistoryPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleRevisionHistoryList() {
		return moduleRevisionHistoryListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDom4jDocument() {
		return dom4jDocumentEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactory getCoreFactory() {
		return (CoreFactory)getEFactoryInstance();
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
		jresResourceInfoEClass = createEClass(JRES_RESOURCE_INFO);
		createEReference(jresResourceInfoEClass, JRES_RESOURCE_INFO__HISTORIES);
		createEAttribute(jresResourceInfoEClass, JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME);

		basicResourceInfoEClass = createEClass(BASIC_RESOURCE_INFO);
		createEAttribute(basicResourceInfoEClass, BASIC_RESOURCE_INFO__NAME);
		createEAttribute(basicResourceInfoEClass, BASIC_RESOURCE_INFO__CHINESE_NAME);
		createEAttribute(basicResourceInfoEClass, BASIC_RESOURCE_INFO__DESCRIPTION);
		createEAttribute(basicResourceInfoEClass, BASIC_RESOURCE_INFO__OBJECT_ID);
		createEAttribute(basicResourceInfoEClass, BASIC_RESOURCE_INFO__GROUP);

		revisionHistoryEClass = createEClass(REVISION_HISTORY);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__MODIFIED_DATE);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__VERSION);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__ORDER_NUMBER);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__MODIFIED_BY);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__CHARGER);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__MODIFIED_REASON);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__MODIFIED);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__COMMENT);
		createEAttribute(revisionHistoryEClass, REVISION_HISTORY__LOCATION);

		extensibleModelEClass = createEClass(EXTENSIBLE_MODEL);
		createEReference(extensibleModelEClass, EXTENSIBLE_MODEL__DATA);
		createEReference(extensibleModelEClass, EXTENSIBLE_MODEL__DATA2);

		eStringToEObjectMapEntryEClass = createEClass(ESTRING_TO_EOBJECT_MAP_ENTRY);
		createEAttribute(eStringToEObjectMapEntryEClass, ESTRING_TO_EOBJECT_MAP_ENTRY__KEY);
		createEReference(eStringToEObjectMapEntryEClass, ESTRING_TO_EOBJECT_MAP_ENTRY__VALUE);

		referenceEClass = createEClass(REFERENCE);
		createEAttribute(referenceEClass, REFERENCE__TYPE);

		referenceWithNamespaceEClass = createEClass(REFERENCE_WITH_NAMESPACE);

		iReferenceProviderEClass = createEClass(IREFERENCE_PROVIDER);

		ijsonDataEClass = createEClass(IJSON_DATA);

		extensibleModelConfigPropertyEClass = createEClass(EXTENSIBLE_MODEL_CONFIG_PROPERTY);
		createEAttribute(extensibleModelConfigPropertyEClass, EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML);
		createEReference(extensibleModelConfigPropertyEClass, EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES);
		createEAttribute(extensibleModelConfigPropertyEClass, EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML_CACHE);

		extensibleModelAttributeEClass = createEClass(EXTENSIBLE_MODEL_ATTRIBUTE);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__URI);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__KEY);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__LABLE);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__TYPE);
		createEAttribute(extensibleModelAttributeEClass, EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE);

		userExtensiblePropertyEClass = createEClass(USER_EXTENSIBLE_PROPERTY);
		createEReference(userExtensiblePropertyEClass, USER_EXTENSIBLE_PROPERTY__MAP);

		moduleExtensibleModelEClass = createEClass(MODULE_EXTENSIBLE_MODEL);

		projectExtensibleModelEClass = createEClass(PROJECT_EXTENSIBLE_MODEL);

		projectRevisionHistoryPropertyEClass = createEClass(PROJECT_REVISION_HISTORY_PROPERTY);
		createEReference(projectRevisionHistoryPropertyEClass, PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES);

		moduleRevisionHistoryListEClass = createEClass(MODULE_REVISION_HISTORY_LIST);

		// Create data types
		dom4jDocumentEDataType = createEDataType(DOM4J_DOCUMENT);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		jresResourceInfoEClass.getESuperTypes().add(this.getExtensibleModel());
		jresResourceInfoEClass.getESuperTypes().add(this.getBasicResourceInfo());
		jresResourceInfoEClass.getESuperTypes().add(this.getIReferenceProvider());
		revisionHistoryEClass.getESuperTypes().add(this.getExtensibleModel());
		extensibleModelEClass.getESuperTypes().add(this.getIJSONData());
		referenceWithNamespaceEClass.getESuperTypes().add(this.getReference());
		moduleExtensibleModelEClass.getESuperTypes().add(this.getExtensibleModel());
		projectExtensibleModelEClass.getESuperTypes().add(this.getExtensibleModel());
		moduleRevisionHistoryListEClass.getESuperTypes().add(this.getJRESResourceInfo());

		// Initialize classes and features; add operations and parameters
		initEClass(jresResourceInfoEClass, JRESResourceInfo.class, "JRESResourceInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJRESResourceInfo_Histories(), this.getRevisionHistory(), null, "histories", null, 0, -1, JRESResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJRESResourceInfo_FullyQualifiedName(), ecorePackage.getEString(), "fullyQualifiedName", "", 0, 1, JRESResourceInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(basicResourceInfoEClass, BasicResourceInfo.class, "BasicResourceInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBasicResourceInfo_Name(), ecorePackage.getEString(), "name", "", 0, 1, BasicResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicResourceInfo_ChineseName(), ecorePackage.getEString(), "chineseName", "", 0, 1, BasicResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicResourceInfo_Description(), ecorePackage.getEString(), "description", "", 0, 1, BasicResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicResourceInfo_ObjectId(), ecorePackage.getEString(), "objectId", "", 0, 1, BasicResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBasicResourceInfo_Group(), ecorePackage.getEString(), "group", null, 0, 1, BasicResourceInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(revisionHistoryEClass, RevisionHistory.class, "RevisionHistory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRevisionHistory_ModifiedDate(), ecorePackage.getEString(), "modifiedDate", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_Version(), ecorePackage.getEString(), "version", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_OrderNumber(), ecorePackage.getEString(), "orderNumber", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_ModifiedBy(), ecorePackage.getEString(), "modifiedBy", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_Charger(), ecorePackage.getEString(), "charger", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_ModifiedReason(), ecorePackage.getEString(), "modifiedReason", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_Modified(), ecorePackage.getEString(), "modified", "", 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevisionHistory_Location(), ecorePackage.getEString(), "location", null, 0, 1, RevisionHistory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extensibleModelEClass, ExtensibleModel.class, "ExtensibleModel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtensibleModel_Data(), ecorePackage.getEStringToStringMapEntry(), null, "data", null, 0, -1, ExtensibleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtensibleModel_Data2(), this.getEStringToEObjectMapEntry(), null, "data2", null, 0, -1, ExtensibleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eStringToEObjectMapEntryEClass, Map.Entry.class, "EStringToEObjectMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEStringToEObjectMapEntry_Key(), ecorePackage.getEString(), "key", "", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEStringToEObjectMapEntry_Value(), ecorePackage.getEObject(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReference_Type(), ecorePackage.getEString(), "type", "", 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(referenceEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(referenceEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(referenceWithNamespaceEClass, ReferenceWithNamespace.class, "ReferenceWithNamespace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(referenceWithNamespaceEClass, null, "setNamespace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "ns", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(referenceWithNamespaceEClass, ecorePackage.getEString(), "getNamespace", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(referenceWithNamespaceEClass, null, "setId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(referenceWithNamespaceEClass, ecorePackage.getEString(), "getId", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iReferenceProviderEClass, IReferenceProvider.class, "IReferenceProvider", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iReferenceProviderEClass, this.getReference(), "getReferences", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(ijsonDataEClass, IJSONData.class, "IJSONData", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(ijsonDataEClass, ecorePackage.getEString(), "toJSON", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(extensibleModelConfigPropertyEClass, ExtensibleModelConfigProperty.class, "ExtensibleModelConfigProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtensibleModelConfigProperty_Xml(), ecorePackage.getEString(), "xml", null, 0, 1, ExtensibleModelConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtensibleModelConfigProperty_Attributes(), this.getExtensibleModelAttribute(), null, "attributes", null, 0, -1, ExtensibleModelConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelConfigProperty_XmlCache(), this.getDom4jDocument(), "xmlCache", null, 0, 1, ExtensibleModelConfigProperty.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(extensibleModelAttributeEClass, ExtensibleModelAttribute.class, "ExtensibleModelAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtensibleModelAttribute_Uri(), ecorePackage.getEString(), "uri", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelAttribute_ClassName(), ecorePackage.getEString(), "className", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelAttribute_Key(), ecorePackage.getEString(), "key", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelAttribute_Lable(), ecorePackage.getEString(), "lable", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelAttribute_Type(), ecorePackage.getEString(), "type", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtensibleModelAttribute_Validate(), ecorePackage.getEString(), "validate", "", 0, 1, ExtensibleModelAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userExtensiblePropertyEClass, UserExtensibleProperty.class, "UserExtensibleProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserExtensibleProperty_Map(), ecorePackage.getEStringToStringMapEntry(), null, "map", null, 0, -1, UserExtensibleProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleExtensibleModelEClass, ModuleExtensibleModel.class, "ModuleExtensibleModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(projectExtensibleModelEClass, ProjectExtensibleModel.class, "ProjectExtensibleModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(projectRevisionHistoryPropertyEClass, ProjectRevisionHistoryProperty.class, "ProjectRevisionHistoryProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProjectRevisionHistoryProperty_Histories(), this.getRevisionHistory(), null, "histories", null, 0, -1, ProjectRevisionHistoryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleRevisionHistoryListEClass, ModuleRevisionHistoryList.class, "ModuleRevisionHistoryList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(dom4jDocumentEDataType, Document.class, "Dom4jDocument", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
		  (revisionHistoryEClass, 
		   source, 
		   new String[] {
			 "constraints", "modifiedDate version modified modifiedBy orderNumber"
		   });					
	}

} //CorePackageImpl
