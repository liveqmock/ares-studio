/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic.impl;

import com.hundsun.ares.studio.atom.AtomPackage;

import com.hundsun.ares.studio.logic.LogicFactory;
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.LogicService;

import com.hundsun.ares.studio.logic.util.LogicValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LogicPackageImpl extends EPackageImpl implements LogicPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicServiceEClass = null;

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
	 * @see com.hundsun.ares.studio.logic.LogicPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LogicPackageImpl() {
		super(eNS_URI, LogicFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LogicPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LogicPackage init() {
		if (isInited) return (LogicPackage)EPackage.Registry.INSTANCE.getEPackage(LogicPackage.eNS_URI);

		// Obtain or create and register package
		LogicPackageImpl theLogicPackage = (LogicPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LogicPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LogicPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		AtomPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theLogicPackage.createPackageContents();

		// Initialize created meta-data
		theLogicPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theLogicPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return LogicValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theLogicPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LogicPackage.eNS_URI, theLogicPackage);
		return theLogicPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicFunction() {
		return logicFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLogicFunction_IsTransFunc() {
		return (EAttribute)logicFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicService() {
		return logicServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLogicService_IsCheckAccess() {
		return (EAttribute)logicServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLogicService_TimeOut() {
		return (EAttribute)logicServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicFactory getLogicFactory() {
		return (LogicFactory)getEFactoryInstance();
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
		logicFunctionEClass = createEClass(LOGIC_FUNCTION);
		createEAttribute(logicFunctionEClass, LOGIC_FUNCTION__IS_TRANS_FUNC);

		logicServiceEClass = createEClass(LOGIC_SERVICE);
		createEAttribute(logicServiceEClass, LOGIC_SERVICE__IS_CHECK_ACCESS);
		createEAttribute(logicServiceEClass, LOGIC_SERVICE__TIME_OUT);
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
		AtomPackage theAtomPackage = (AtomPackage)EPackage.Registry.INSTANCE.getEPackage(AtomPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		logicFunctionEClass.getESuperTypes().add(theAtomPackage.getAtomFunction());
		logicServiceEClass.getESuperTypes().add(theAtomPackage.getAtomFunction());

		// Initialize classes and features; add operations and parameters
		initEClass(logicFunctionEClass, LogicFunction.class, "LogicFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLogicFunction_IsTransFunc(), ecorePackage.getEBoolean(), "isTransFunc", "false", 0, 1, LogicFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logicServiceEClass, LogicService.class, "LogicService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLogicService_IsCheckAccess(), ecorePackage.getEBoolean(), "isCheckAccess", "false", 0, 1, LogicService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogicService_TimeOut(), ecorePackage.getEString(), "timeOut", "", 0, 1, LogicService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (logicFunctionEClass, 
		   source, 
		   new String[] {
			 "constraints", "objectId"
		   });		
		addAnnotation
		  (logicServiceEClass, 
		   source, 
		   new String[] {
			 "constraints", "objectId"
		   });
	}

} //LogicPackageImpl
