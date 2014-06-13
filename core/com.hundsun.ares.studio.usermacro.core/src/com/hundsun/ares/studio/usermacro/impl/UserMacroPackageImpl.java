/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.usermacro.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroFactory;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.UserMacroPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UserMacroPackageImpl extends EPackageImpl implements UserMacroPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userMacroEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userMacroItemEClass = null;

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
	 * @see com.hundsun.ares.studio.usermacro.UserMacroPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UserMacroPackageImpl() {
		super(eNS_URI, UserMacroFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UserMacroPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UserMacroPackage init() {
		if (isInited) return (UserMacroPackage)EPackage.Registry.INSTANCE.getEPackage(UserMacroPackage.eNS_URI);

		// Obtain or create and register package
		UserMacroPackageImpl theUserMacroPackage = (UserMacroPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UserMacroPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UserMacroPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		com.hundsun.ares.studio.core.model.CorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUserMacroPackage.createPackageContents();

		// Initialize created meta-data
		theUserMacroPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUserMacroPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UserMacroPackage.eNS_URI, theUserMacroPackage);
		return theUserMacroPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserMacro() {
		return userMacroEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserMacro_MacroItems() {
		return (EReference)userMacroEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserMacroItem() {
		return userMacroItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMacroItem_Name() {
		return (EAttribute)userMacroItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMacroItem_Sequence() {
		return (EAttribute)userMacroItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMacroItem_Content() {
		return (EAttribute)userMacroItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMacroItem_Type() {
		return (EAttribute)userMacroItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMacroItem_Description() {
		return (EAttribute)userMacroItemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserMacroFactory getUserMacroFactory() {
		return (UserMacroFactory)getEFactoryInstance();
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
		userMacroEClass = createEClass(USER_MACRO);
		createEReference(userMacroEClass, USER_MACRO__MACRO_ITEMS);

		userMacroItemEClass = createEClass(USER_MACRO_ITEM);
		createEAttribute(userMacroItemEClass, USER_MACRO_ITEM__NAME);
		createEAttribute(userMacroItemEClass, USER_MACRO_ITEM__SEQUENCE);
		createEAttribute(userMacroItemEClass, USER_MACRO_ITEM__CONTENT);
		createEAttribute(userMacroItemEClass, USER_MACRO_ITEM__TYPE);
		createEAttribute(userMacroItemEClass, USER_MACRO_ITEM__DESCRIPTION);
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
		com.hundsun.ares.studio.core.model.CorePackage theCorePackage = (com.hundsun.ares.studio.core.model.CorePackage)EPackage.Registry.INSTANCE.getEPackage(com.hundsun.ares.studio.core.model.CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		userMacroEClass.getESuperTypes().add(theCorePackage.getJRESResourceInfo());

		// Initialize classes and features; add operations and parameters
		initEClass(userMacroEClass, UserMacro.class, "UserMacro", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserMacro_MacroItems(), this.getUserMacroItem(), null, "macroItems", null, 0, -1, UserMacro.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userMacroItemEClass, UserMacroItem.class, "UserMacroItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserMacroItem_Name(), ecorePackage.getEString(), "name", "", 0, 1, UserMacroItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserMacroItem_Sequence(), ecorePackage.getEString(), "sequence", "", 0, 1, UserMacroItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserMacroItem_Content(), ecorePackage.getEString(), "content", "", 0, 1, UserMacroItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserMacroItem_Type(), ecorePackage.getEString(), "type", "", 0, 1, UserMacroItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserMacroItem_Description(), ecorePackage.getEString(), "description", "", 0, 1, UserMacroItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UserMacroPackageImpl
