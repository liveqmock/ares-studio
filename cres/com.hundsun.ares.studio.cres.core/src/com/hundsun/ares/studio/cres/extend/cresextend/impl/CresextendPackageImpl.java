/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendFactory;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.GccDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;

import com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CresextendPackageImpl extends EPackageImpl implements CresextendPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moudleDependEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cresMoudleExtendPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cresProjectExtendPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procDefineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gccDefineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mvcDefineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileDefineEClass = null;

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
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CresextendPackageImpl() {
		super(eNS_URI, CresextendFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CresextendPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CresextendPackage init() {
		if (isInited) return (CresextendPackage)EPackage.Registry.INSTANCE.getEPackage(CresextendPackage.eNS_URI);

		// Obtain or create and register package
		CresextendPackageImpl theCresextendPackage = (CresextendPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CresextendPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CresextendPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCresextendPackage.createPackageContents();

		// Initialize created meta-data
		theCresextendPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCresextendPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CresextendPackage.eNS_URI, theCresextendPackage);
		return theCresextendPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMoudleDepend() {
		return moudleDependEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMoudleDepend_ModulePath() {
		return (EAttribute)moudleDependEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMoudleDepend_Name() {
		return (EAttribute)moudleDependEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCresMoudleExtendProperty() {
		return cresMoudleExtendPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCresMoudleExtendProperty_Depends() {
		return (EReference)cresMoudleExtendPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresMoudleExtendProperty_SubSysID() {
		return (EAttribute)cresMoudleExtendPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresMoudleExtendProperty_DataBaseName() {
		return (EAttribute)cresMoudleExtendPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresMoudleExtendProperty_DataBaseConn() {
		return (EAttribute)cresMoudleExtendPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresMoudleExtendProperty_BizPropertyConfig() {
		return (EAttribute)cresMoudleExtendPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCresProjectExtendProperty() {
		return cresProjectExtendPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Version() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_CName() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_ShortCName() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Id() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Manager() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Developer() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_User() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Relation() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Name() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Writer() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_Note() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCresProjectExtendProperty_HeadFile() {
		return (EAttribute)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCresProjectExtendProperty_ProcDefine() {
		return (EReference)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCresProjectExtendProperty_GccDefine() {
		return (EReference)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCresProjectExtendProperty_MvcDefine() {
		return (EReference)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCresProjectExtendProperty_FuncDefine() {
		return (EReference)cresProjectExtendPropertyEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcDefine() {
		return procDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGccDefine() {
		return gccDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMvcDefine() {
		return mvcDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileDefine() {
		return fileDefineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileDefine_IsUsed() {
		return (EAttribute)fileDefineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileDefine_Parameter() {
		return (EAttribute)fileDefineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileDefine_Value() {
		return (EAttribute)fileDefineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileDefine_Note() {
		return (EAttribute)fileDefineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresextendFactory getCresextendFactory() {
		return (CresextendFactory)getEFactoryInstance();
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
		moudleDependEClass = createEClass(MOUDLE_DEPEND);
		createEAttribute(moudleDependEClass, MOUDLE_DEPEND__MODULE_PATH);
		createEAttribute(moudleDependEClass, MOUDLE_DEPEND__NAME);

		cresMoudleExtendPropertyEClass = createEClass(CRES_MOUDLE_EXTEND_PROPERTY);
		createEReference(cresMoudleExtendPropertyEClass, CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS);
		createEAttribute(cresMoudleExtendPropertyEClass, CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID);
		createEAttribute(cresMoudleExtendPropertyEClass, CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME);
		createEAttribute(cresMoudleExtendPropertyEClass, CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN);
		createEAttribute(cresMoudleExtendPropertyEClass, CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG);

		cresProjectExtendPropertyEClass = createEClass(CRES_PROJECT_EXTEND_PROPERTY);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__VERSION);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__CNAME);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__ID);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__MANAGER);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__USER);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__RELATION);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__NAME);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__WRITER);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__NOTE);
		createEAttribute(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE);
		createEReference(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE);
		createEReference(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE);
		createEReference(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE);
		createEReference(cresProjectExtendPropertyEClass, CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE);

		procDefineEClass = createEClass(PROC_DEFINE);

		gccDefineEClass = createEClass(GCC_DEFINE);

		mvcDefineEClass = createEClass(MVC_DEFINE);

		fileDefineEClass = createEClass(FILE_DEFINE);
		createEAttribute(fileDefineEClass, FILE_DEFINE__IS_USED);
		createEAttribute(fileDefineEClass, FILE_DEFINE__PARAMETER);
		createEAttribute(fileDefineEClass, FILE_DEFINE__VALUE);
		createEAttribute(fileDefineEClass, FILE_DEFINE__NOTE);
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
		procDefineEClass.getESuperTypes().add(this.getFileDefine());
		gccDefineEClass.getESuperTypes().add(this.getFileDefine());
		mvcDefineEClass.getESuperTypes().add(this.getFileDefine());

		// Initialize classes and features; add operations and parameters
		initEClass(moudleDependEClass, MoudleDepend.class, "MoudleDepend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMoudleDepend_ModulePath(), ecorePackage.getEString(), "modulePath", "", 0, 1, MoudleDepend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoudleDepend_Name(), ecorePackage.getEString(), "name", "", 0, 1, MoudleDepend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cresMoudleExtendPropertyEClass, CresMoudleExtendProperty.class, "CresMoudleExtendProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCresMoudleExtendProperty_Depends(), this.getMoudleDepend(), null, "depends", null, 0, -1, CresMoudleExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresMoudleExtendProperty_SubSysID(), ecorePackage.getEString(), "subSysID", "", 0, 1, CresMoudleExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresMoudleExtendProperty_DataBaseName(), ecorePackage.getEString(), "dataBaseName", "", 0, 1, CresMoudleExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresMoudleExtendProperty_DataBaseConn(), ecorePackage.getEString(), "dataBaseConn", "", 0, 1, CresMoudleExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresMoudleExtendProperty_BizPropertyConfig(), ecorePackage.getEString(), "bizPropertyConfig", "", 0, 1, CresMoudleExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cresProjectExtendPropertyEClass, CresProjectExtendProperty.class, "CresProjectExtendProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCresProjectExtendProperty_Version(), ecorePackage.getEString(), "version", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_CName(), ecorePackage.getEString(), "cName", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_ShortCName(), ecorePackage.getEString(), "shortCName", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Id(), ecorePackage.getEString(), "id", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Manager(), ecorePackage.getEString(), "manager", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Developer(), ecorePackage.getEString(), "developer", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_User(), ecorePackage.getEString(), "user", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Relation(), ecorePackage.getEString(), "relation", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Name(), ecorePackage.getEString(), "name", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Writer(), ecorePackage.getEString(), "writer", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_Note(), ecorePackage.getEString(), "note", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCresProjectExtendProperty_HeadFile(), ecorePackage.getEString(), "headFile", "", 0, 1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCresProjectExtendProperty_ProcDefine(), this.getProcDefine(), null, "procDefine", null, 0, -1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCresProjectExtendProperty_GccDefine(), this.getGccDefine(), null, "gccDefine", null, 0, -1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCresProjectExtendProperty_MvcDefine(), this.getMvcDefine(), null, "mvcDefine", null, 0, -1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCresProjectExtendProperty_FuncDefine(), this.getFileDefine(), null, "funcDefine", null, 0, -1, CresProjectExtendProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(procDefineEClass, ProcDefine.class, "ProcDefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gccDefineEClass, GccDefine.class, "GccDefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mvcDefineEClass, MvcDefine.class, "MvcDefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fileDefineEClass, FileDefine.class, "FileDefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileDefine_IsUsed(), ecorePackage.getEBoolean(), "isUsed", "true", 0, 1, FileDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileDefine_Parameter(), ecorePackage.getEString(), "parameter", "", 0, 1, FileDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileDefine_Value(), ecorePackage.getEString(), "value", "", 0, 1, FileDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileDefine_Note(), ecorePackage.getEString(), "note", "", 0, 1, FileDefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CresextendPackageImpl
