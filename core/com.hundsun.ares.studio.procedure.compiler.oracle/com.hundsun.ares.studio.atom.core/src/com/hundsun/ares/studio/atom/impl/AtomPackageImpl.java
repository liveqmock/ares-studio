/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.util.AtomValidator;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AtomPackageImpl extends EPackageImpl implements AtomPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalParamEClass = null;

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
	 * @see com.hundsun.ares.studio.atom.AtomPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AtomPackageImpl() {
		super(eNS_URI, AtomFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AtomPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AtomPackage init() {
		if (isInited) return (AtomPackage)EPackage.Registry.INSTANCE.getEPackage(AtomPackage.eNS_URI);

		// Obtain or create and register package
		AtomPackageImpl theAtomPackage = (AtomPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AtomPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AtomPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BizPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theAtomPackage.createPackageContents();

		// Initialize created meta-data
		theAtomPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theAtomPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return AtomValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theAtomPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AtomPackage.eNS_URI, theAtomPackage);
		return theAtomPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomFunction() {
		return atomFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAtomFunction_Database() {
		return (EAttribute)atomFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAtomFunction_PseudoCode() {
		return (EAttribute)atomFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAtomFunction_InternalVariables() {
		return (EReference)atomFunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomService() {
		return atomServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAtomService_Timeout() {
		return (EAttribute)atomServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalParam() {
		return internalParamEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomFactory getAtomFactory() {
		return (AtomFactory)getEFactoryInstance();
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
		atomFunctionEClass = createEClass(ATOM_FUNCTION);
		createEAttribute(atomFunctionEClass, ATOM_FUNCTION__DATABASE);
		createEAttribute(atomFunctionEClass, ATOM_FUNCTION__PSEUDO_CODE);
		createEReference(atomFunctionEClass, ATOM_FUNCTION__INTERNAL_VARIABLES);

		atomServiceEClass = createEClass(ATOM_SERVICE);
		createEAttribute(atomServiceEClass, ATOM_SERVICE__TIMEOUT);

		internalParamEClass = createEClass(INTERNAL_PARAM);
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
		atomFunctionEClass.getESuperTypes().add(theBizPackage.getBizInterface());
		atomFunctionEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());
		atomServiceEClass.getESuperTypes().add(this.getAtomFunction());
		internalParamEClass.getESuperTypes().add(theBizPackage.getParameter());

		// Initialize classes and features; add operations and parameters
		initEClass(atomFunctionEClass, AtomFunction.class, "AtomFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAtomFunction_Database(), ecorePackage.getEString(), "database", null, 0, 1, AtomFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAtomFunction_PseudoCode(), ecorePackage.getEString(), "pseudoCode", null, 0, 1, AtomFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAtomFunction_InternalVariables(), this.getInternalParam(), null, "internalVariables", null, 0, -1, AtomFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(atomServiceEClass, AtomService.class, "AtomService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAtomService_Timeout(), ecorePackage.getEString(), "timeout", null, 0, 1, AtomService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(internalParamEClass, InternalParam.class, "InternalParam", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		  (atomFunctionEClass, 
		   source, 
		   new String[] {
			 "constraints", "objectId"
		   });		
		addAnnotation
		  (atomServiceEClass, 
		   source, 
		   new String[] {
			 "constraints", "objectId"
		   });		
		addAnnotation
		  (internalParamEClass, 
		   source, 
		   new String[] {
			 "constraints", "id type"
		   });
	}

} //AtomPackageImpl
