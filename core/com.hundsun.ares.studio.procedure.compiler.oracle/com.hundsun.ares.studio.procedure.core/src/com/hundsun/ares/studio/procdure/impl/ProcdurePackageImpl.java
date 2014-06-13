/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.procdure.BizType;
import com.hundsun.ares.studio.procdure.DefineType;
import com.hundsun.ares.studio.procdure.ProcdureFactory;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.procdure.util.ProcdureValidator;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcdurePackageImpl extends EPackageImpl implements ProcdurePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relatedInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum defineTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bizTypeEEnum = null;

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
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ProcdurePackageImpl() {
		super(eNS_URI, ProcdureFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ProcdurePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ProcdurePackage init() {
		if (isInited) return (ProcdurePackage)EPackage.Registry.INSTANCE.getEPackage(ProcdurePackage.eNS_URI);

		// Obtain or create and register package
		ProcdurePackageImpl theProcdurePackage = (ProcdurePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProcdurePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProcdurePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BizPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theProcdurePackage.createPackageContents();

		// Initialize created meta-data
		theProcdurePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theProcdurePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return ProcdureValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theProcdurePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ProcdurePackage.eNS_URI, theProcdurePackage);
		return theProcdurePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcedure() {
		return procedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_Database() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_PseudoCode() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcedure_InternalVariables() {
		return (EReference)procedureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_BeginCode() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_EndCode() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_ReturnType() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_DefineType() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_BizType() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_CreateDate() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcedure_RelatedTableInfo() {
		return (EReference)procedureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcedure_RelatedBasicDataInfo() {
		return (EReference)procedureEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_AutoTransaction() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelatedInfo() {
		return relatedInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelatedInfo_Id() {
		return (EAttribute)relatedInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelatedInfo_Comment() {
		return (EAttribute)relatedInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelatedInfo_Path() {
		return (EAttribute)relatedInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDefineType() {
		return defineTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBizType() {
		return bizTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcdureFactory getProcdureFactory() {
		return (ProcdureFactory)getEFactoryInstance();
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
		procedureEClass = createEClass(PROCEDURE);
		createEAttribute(procedureEClass, PROCEDURE__DATABASE);
		createEAttribute(procedureEClass, PROCEDURE__PSEUDO_CODE);
		createEReference(procedureEClass, PROCEDURE__INTERNAL_VARIABLES);
		createEAttribute(procedureEClass, PROCEDURE__BEGIN_CODE);
		createEAttribute(procedureEClass, PROCEDURE__END_CODE);
		createEAttribute(procedureEClass, PROCEDURE__RETURN_TYPE);
		createEAttribute(procedureEClass, PROCEDURE__DEFINE_TYPE);
		createEAttribute(procedureEClass, PROCEDURE__BIZ_TYPE);
		createEAttribute(procedureEClass, PROCEDURE__CREATE_DATE);
		createEReference(procedureEClass, PROCEDURE__RELATED_TABLE_INFO);
		createEReference(procedureEClass, PROCEDURE__RELATED_BASIC_DATA_INFO);
		createEAttribute(procedureEClass, PROCEDURE__AUTO_TRANSACTION);

		relatedInfoEClass = createEClass(RELATED_INFO);
		createEAttribute(relatedInfoEClass, RELATED_INFO__ID);
		createEAttribute(relatedInfoEClass, RELATED_INFO__COMMENT);
		createEAttribute(relatedInfoEClass, RELATED_INFO__PATH);

		// Create enums
		defineTypeEEnum = createEEnum(DEFINE_TYPE);
		bizTypeEEnum = createEEnum(BIZ_TYPE);
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
		BizPackage theBizPackage = (BizPackage)EPackage.Registry.INSTANCE.getEPackage(BizPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		procedureEClass.getESuperTypes().add(theBizPackage.getBizInterface());
		procedureEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		relatedInfoEClass.getESuperTypes().add(theCorePackage.getExtensibleModel());

		// Initialize classes and features; add operations and parameters
		initEClass(procedureEClass, Procedure.class, "Procedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcedure_Database(), ecorePackage.getEString(), "database", "", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_PseudoCode(), ecorePackage.getEString(), "pseudoCode", "", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcedure_InternalVariables(), theBizPackage.getParameter(), null, "internalVariables", null, 0, -1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_BeginCode(), ecorePackage.getEString(), "beginCode", "", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_EndCode(), ecorePackage.getEString(), "endCode", "", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_ReturnType(), ecorePackage.getEString(), "returnType", "number", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_DefineType(), ecorePackage.getEString(), "defineType", "AS", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_BizType(), ecorePackage.getEString(), "bizType", "function", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_CreateDate(), ecorePackage.getEString(), "createDate", "", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcedure_RelatedTableInfo(), this.getRelatedInfo(), null, "relatedTableInfo", null, 0, -1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcedure_RelatedBasicDataInfo(), this.getRelatedInfo(), null, "relatedBasicDataInfo", null, 0, -1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcedure_AutoTransaction(), ecorePackage.getEBoolean(), "autoTransaction", "false", 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relatedInfoEClass, RelatedInfo.class, "RelatedInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelatedInfo_Id(), ecorePackage.getEString(), "id", null, 0, 1, RelatedInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelatedInfo_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, RelatedInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelatedInfo_Path(), ecorePackage.getEString(), "path", null, 0, 1, RelatedInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(defineTypeEEnum, DefineType.class, "DefineType");
		addEEnumLiteral(defineTypeEEnum, DefineType.AS);
		addEEnumLiteral(defineTypeEEnum, DefineType.PIPELINED);

		initEEnum(bizTypeEEnum, BizType.class, "BizType");
		addEEnumLiteral(bizTypeEEnum, BizType.FUNCTION);
		addEEnumLiteral(bizTypeEEnum, BizType.PROCEDURE);

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
		  (procedureEClass, 
		   source, 
		   new String[] {
			 "constraints", "objectId"
		   });		
		addAnnotation
		  (relatedInfoEClass, 
		   source, 
		   new String[] {
			 "constraints", "id"
		   });
	}

} //ProcdurePackageImpl
