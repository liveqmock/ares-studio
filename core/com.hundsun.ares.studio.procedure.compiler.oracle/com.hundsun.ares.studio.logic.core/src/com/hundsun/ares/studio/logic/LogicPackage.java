/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic;

import com.hundsun.ares.studio.atom.AtomPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see com.hundsun.ares.studio.logic.LogicFactory
 * @model kind="package"
 * @generated
 */
public interface LogicPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "logic";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/cres/logic/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "logic";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LogicPackage eINSTANCE = com.hundsun.ares.studio.logic.impl.LogicPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.logic.impl.LogicFunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.logic.impl.LogicFunctionImpl
	 * @see com.hundsun.ares.studio.logic.impl.LogicPackageImpl#getLogicFunction()
	 * @generated
	 */
	int LOGIC_FUNCTION = 0;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__INPUT_COLLECTION = AtomPackage.ATOM_FUNCTION__INPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__OUTPUT_COLLECTION = AtomPackage.ATOM_FUNCTION__OUTPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__INPUT_PARAMETERS = AtomPackage.ATOM_FUNCTION__INPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__OUTPUT_PARAMETERS = AtomPackage.ATOM_FUNCTION__OUTPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__INTERFACE_FLAG = AtomPackage.ATOM_FUNCTION__INTERFACE_FLAG;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__DATA = AtomPackage.ATOM_FUNCTION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__DATA2 = AtomPackage.ATOM_FUNCTION__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__NAME = AtomPackage.ATOM_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__CHINESE_NAME = AtomPackage.ATOM_FUNCTION__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__DESCRIPTION = AtomPackage.ATOM_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__OBJECT_ID = AtomPackage.ATOM_FUNCTION__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__HISTORIES = AtomPackage.ATOM_FUNCTION__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__FULLY_QUALIFIED_NAME = AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__DATABASE = AtomPackage.ATOM_FUNCTION__DATABASE;

	/**
	 * The feature id for the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__PSEUDO_CODE = AtomPackage.ATOM_FUNCTION__PSEUDO_CODE;

	/**
	 * The feature id for the '<em><b>Internal Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__INTERNAL_VARIABLES = AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Is Trans Func</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION__IS_TRANS_FUNC = AtomPackage.ATOM_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_FUNCTION_FEATURE_COUNT = AtomPackage.ATOM_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.logic.impl.LogicServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.logic.impl.LogicServiceImpl
	 * @see com.hundsun.ares.studio.logic.impl.LogicPackageImpl#getLogicService()
	 * @generated
	 */
	int LOGIC_SERVICE = 1;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__INPUT_COLLECTION = AtomPackage.ATOM_FUNCTION__INPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__OUTPUT_COLLECTION = AtomPackage.ATOM_FUNCTION__OUTPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__INPUT_PARAMETERS = AtomPackage.ATOM_FUNCTION__INPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__OUTPUT_PARAMETERS = AtomPackage.ATOM_FUNCTION__OUTPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__INTERFACE_FLAG = AtomPackage.ATOM_FUNCTION__INTERFACE_FLAG;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__DATA = AtomPackage.ATOM_FUNCTION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__DATA2 = AtomPackage.ATOM_FUNCTION__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__NAME = AtomPackage.ATOM_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__CHINESE_NAME = AtomPackage.ATOM_FUNCTION__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__DESCRIPTION = AtomPackage.ATOM_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__OBJECT_ID = AtomPackage.ATOM_FUNCTION__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__HISTORIES = AtomPackage.ATOM_FUNCTION__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__FULLY_QUALIFIED_NAME = AtomPackage.ATOM_FUNCTION__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__DATABASE = AtomPackage.ATOM_FUNCTION__DATABASE;

	/**
	 * The feature id for the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__PSEUDO_CODE = AtomPackage.ATOM_FUNCTION__PSEUDO_CODE;

	/**
	 * The feature id for the '<em><b>Internal Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__INTERNAL_VARIABLES = AtomPackage.ATOM_FUNCTION__INTERNAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Is Check Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__IS_CHECK_ACCESS = AtomPackage.ATOM_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Time Out</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE__TIME_OUT = AtomPackage.ATOM_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIC_SERVICE_FEATURE_COUNT = AtomPackage.ATOM_FUNCTION_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.logic.LogicFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see com.hundsun.ares.studio.logic.LogicFunction
	 * @generated
	 */
	EClass getLogicFunction();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.logic.LogicFunction#isIsTransFunc <em>Is Trans Func</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Trans Func</em>'.
	 * @see com.hundsun.ares.studio.logic.LogicFunction#isIsTransFunc()
	 * @see #getLogicFunction()
	 * @generated
	 */
	EAttribute getLogicFunction_IsTransFunc();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.logic.LogicService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see com.hundsun.ares.studio.logic.LogicService
	 * @generated
	 */
	EClass getLogicService();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.logic.LogicService#isIsCheckAccess <em>Is Check Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Check Access</em>'.
	 * @see com.hundsun.ares.studio.logic.LogicService#isIsCheckAccess()
	 * @see #getLogicService()
	 * @generated
	 */
	EAttribute getLogicService_IsCheckAccess();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.logic.LogicService#getTimeOut <em>Time Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Out</em>'.
	 * @see com.hundsun.ares.studio.logic.LogicService#getTimeOut()
	 * @see #getLogicService()
	 * @generated
	 */
	EAttribute getLogicService_TimeOut();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LogicFactory getLogicFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.logic.impl.LogicFunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.logic.impl.LogicFunctionImpl
		 * @see com.hundsun.ares.studio.logic.impl.LogicPackageImpl#getLogicFunction()
		 * @generated
		 */
		EClass LOGIC_FUNCTION = eINSTANCE.getLogicFunction();

		/**
		 * The meta object literal for the '<em><b>Is Trans Func</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIC_FUNCTION__IS_TRANS_FUNC = eINSTANCE.getLogicFunction_IsTransFunc();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.logic.impl.LogicServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.logic.impl.LogicServiceImpl
		 * @see com.hundsun.ares.studio.logic.impl.LogicPackageImpl#getLogicService()
		 * @generated
		 */
		EClass LOGIC_SERVICE = eINSTANCE.getLogicService();

		/**
		 * The meta object literal for the '<em><b>Is Check Access</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIC_SERVICE__IS_CHECK_ACCESS = eINSTANCE.getLogicService_IsCheckAccess();

		/**
		 * The meta object literal for the '<em><b>Time Out</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIC_SERVICE__TIME_OUT = eINSTANCE.getLogicService_TimeOut();

	}

} //LogicPackage
