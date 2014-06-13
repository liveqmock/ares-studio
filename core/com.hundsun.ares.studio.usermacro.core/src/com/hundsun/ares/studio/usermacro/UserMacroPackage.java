/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.usermacro;

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
 * @see com.hundsun.ares.studio.usermacro.UserMacroFactory
 * @model kind="package"
 * @generated
 */
public interface UserMacroPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "usermacro";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/usermacro/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "usermacro";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UserMacroPackage eINSTANCE = com.hundsun.ares.studio.usermacro.impl.UserMacroPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.usermacro.impl.UserMacroImpl <em>User Macro</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroImpl
	 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroPackageImpl#getUserMacro()
	 * @generated
	 */
	int USER_MACRO = 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__DATA = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__DATA2 = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__NAME = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__CHINESE_NAME = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__DESCRIPTION = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__OBJECT_ID = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__HISTORIES = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__FULLY_QUALIFIED_NAME = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Macro Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO__MACRO_ITEMS = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Macro</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_FEATURE_COUNT = com.hundsun.ares.studio.core.model.CorePackage.JRES_RESOURCE_INFO_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.usermacro.impl.UserMacroItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroItemImpl
	 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroPackageImpl#getUserMacroItem()
	 * @generated
	 */
	int USER_MACRO_ITEM = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Sequence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM__SEQUENCE = 1;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM__CONTENT = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM__DESCRIPTION = 4;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MACRO_ITEM_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.usermacro.UserMacro <em>User Macro</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Macro</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacro
	 * @generated
	 */
	EClass getUserMacro();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.usermacro.UserMacro#getMacroItems <em>Macro Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Macro Items</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacro#getMacroItems()
	 * @see #getUserMacro()
	 * @generated
	 */
	EReference getUserMacro_MacroItems();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.usermacro.UserMacroItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem
	 * @generated
	 */
	EClass getUserMacroItem();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.usermacro.UserMacroItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem#getName()
	 * @see #getUserMacroItem()
	 * @generated
	 */
	EAttribute getUserMacroItem_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.usermacro.UserMacroItem#getSequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequence</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem#getSequence()
	 * @see #getUserMacroItem()
	 * @generated
	 */
	EAttribute getUserMacroItem_Sequence();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.usermacro.UserMacroItem#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem#getContent()
	 * @see #getUserMacroItem()
	 * @generated
	 */
	EAttribute getUserMacroItem_Content();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.usermacro.UserMacroItem#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem#getType()
	 * @see #getUserMacroItem()
	 * @generated
	 */
	EAttribute getUserMacroItem_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.usermacro.UserMacroItem#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.hundsun.ares.studio.usermacro.UserMacroItem#getDescription()
	 * @see #getUserMacroItem()
	 * @generated
	 */
	EAttribute getUserMacroItem_Description();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UserMacroFactory getUserMacroFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.usermacro.impl.UserMacroImpl <em>User Macro</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroImpl
		 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroPackageImpl#getUserMacro()
		 * @generated
		 */
		EClass USER_MACRO = eINSTANCE.getUserMacro();

		/**
		 * The meta object literal for the '<em><b>Macro Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_MACRO__MACRO_ITEMS = eINSTANCE.getUserMacro_MacroItems();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.usermacro.impl.UserMacroItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroItemImpl
		 * @see com.hundsun.ares.studio.usermacro.impl.UserMacroPackageImpl#getUserMacroItem()
		 * @generated
		 */
		EClass USER_MACRO_ITEM = eINSTANCE.getUserMacroItem();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MACRO_ITEM__NAME = eINSTANCE.getUserMacroItem_Name();

		/**
		 * The meta object literal for the '<em><b>Sequence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MACRO_ITEM__SEQUENCE = eINSTANCE.getUserMacroItem_Sequence();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MACRO_ITEM__CONTENT = eINSTANCE.getUserMacroItem_Content();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MACRO_ITEM__TYPE = eINSTANCE.getUserMacroItem_Type();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MACRO_ITEM__DESCRIPTION = eINSTANCE.getUserMacroItem_Description();

	}

} //UserMacroPackage
