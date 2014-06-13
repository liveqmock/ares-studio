/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.biz.BizPackage;

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
 * @see com.hundsun.ares.studio.atom.AtomFactory
 * @model kind="package"
 * @generated
 */
public interface AtomPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "atom";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/cres/atom/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atom";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AtomPackage eINSTANCE = com.hundsun.ares.studio.atom.impl.AtomPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.atom.impl.AtomFunctionImpl
	 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getAtomFunction()
	 * @generated
	 */
	int ATOM_FUNCTION = 0;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__INPUT_COLLECTION = BizPackage.BIZ_INTERFACE__INPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__OUTPUT_COLLECTION = BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__INPUT_PARAMETERS = BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__OUTPUT_PARAMETERS = BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__INTERFACE_FLAG = BizPackage.BIZ_INTERFACE__INTERFACE_FLAG;

	/**
	 * The feature id for the '<em><b>Error Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__ERROR_INFOS = BizPackage.BIZ_INTERFACE__ERROR_INFOS;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__DATA = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__DATA2 = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__CHINESE_NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__DESCRIPTION = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__OBJECT_ID = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__GROUP = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__HISTORIES = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__FULLY_QUALIFIED_NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__DATABASE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__PSEUDO_CODE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Internal Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION__INTERNAL_VARIABLES = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FUNCTION_FEATURE_COUNT = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.atom.impl.AtomServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.atom.impl.AtomServiceImpl
	 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getAtomService()
	 * @generated
	 */
	int ATOM_SERVICE = 1;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__INPUT_COLLECTION = ATOM_FUNCTION__INPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__OUTPUT_COLLECTION = ATOM_FUNCTION__OUTPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__INPUT_PARAMETERS = ATOM_FUNCTION__INPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__OUTPUT_PARAMETERS = ATOM_FUNCTION__OUTPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__INTERFACE_FLAG = ATOM_FUNCTION__INTERFACE_FLAG;

	/**
	 * The feature id for the '<em><b>Error Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__ERROR_INFOS = ATOM_FUNCTION__ERROR_INFOS;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__DATA = ATOM_FUNCTION__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__DATA2 = ATOM_FUNCTION__DATA2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__NAME = ATOM_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__CHINESE_NAME = ATOM_FUNCTION__CHINESE_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__DESCRIPTION = ATOM_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__OBJECT_ID = ATOM_FUNCTION__OBJECT_ID;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__GROUP = ATOM_FUNCTION__GROUP;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__HISTORIES = ATOM_FUNCTION__HISTORIES;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__FULLY_QUALIFIED_NAME = ATOM_FUNCTION__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__DATABASE = ATOM_FUNCTION__DATABASE;

	/**
	 * The feature id for the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__PSEUDO_CODE = ATOM_FUNCTION__PSEUDO_CODE;

	/**
	 * The feature id for the '<em><b>Internal Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__INTERNAL_VARIABLES = ATOM_FUNCTION__INTERNAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE__TIMEOUT = ATOM_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_SERVICE_FEATURE_COUNT = ATOM_FUNCTION_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.atom.impl.InternalParamImpl <em>Internal Param</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.atom.impl.InternalParamImpl
	 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getInternalParam()
	 * @generated
	 */
	int INTERNAL_PARAM = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__DATA = BizPackage.PARAMETER__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__DATA2 = BizPackage.PARAMETER__DATA2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__ID = BizPackage.PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__NAME = BizPackage.PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Param Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__PARAM_TYPE = BizPackage.PARAMETER__PARAM_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__TYPE = BizPackage.PARAMETER__TYPE;

	/**
	 * The feature id for the '<em><b>Real Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__REAL_TYPE = BizPackage.PARAMETER__REAL_TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__DESCRIPTION = BizPackage.PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__MULTIPLICITY = BizPackage.PARAMETER__MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__DEFAULT_VALUE = BizPackage.PARAMETER__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__FLAGS = BizPackage.PARAMETER__FLAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM__COMMENTS = BizPackage.PARAMETER__COMMENTS;

	/**
	 * The number of structural features of the '<em>Internal Param</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PARAM_FEATURE_COUNT = BizPackage.PARAMETER_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.atom.AtomFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomFunction
	 * @generated
	 */
	EClass getAtomFunction();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.atom.AtomFunction#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomFunction#getDatabase()
	 * @see #getAtomFunction()
	 * @generated
	 */
	EAttribute getAtomFunction_Database();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.atom.AtomFunction#getPseudoCode <em>Pseudo Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pseudo Code</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomFunction#getPseudoCode()
	 * @see #getAtomFunction()
	 * @generated
	 */
	EAttribute getAtomFunction_PseudoCode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.atom.AtomFunction#getInternalVariables <em>Internal Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Variables</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomFunction#getInternalVariables()
	 * @see #getAtomFunction()
	 * @generated
	 */
	EReference getAtomFunction_InternalVariables();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.atom.AtomService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomService
	 * @generated
	 */
	EClass getAtomService();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.atom.AtomService#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.hundsun.ares.studio.atom.AtomService#getTimeout()
	 * @see #getAtomService()
	 * @generated
	 */
	EAttribute getAtomService_Timeout();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.atom.InternalParam <em>Internal Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Param</em>'.
	 * @see com.hundsun.ares.studio.atom.InternalParam
	 * @generated
	 */
	EClass getInternalParam();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AtomFactory getAtomFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.atom.impl.AtomFunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.atom.impl.AtomFunctionImpl
		 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getAtomFunction()
		 * @generated
		 */
		EClass ATOM_FUNCTION = eINSTANCE.getAtomFunction();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATOM_FUNCTION__DATABASE = eINSTANCE.getAtomFunction_Database();

		/**
		 * The meta object literal for the '<em><b>Pseudo Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATOM_FUNCTION__PSEUDO_CODE = eINSTANCE.getAtomFunction_PseudoCode();

		/**
		 * The meta object literal for the '<em><b>Internal Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATOM_FUNCTION__INTERNAL_VARIABLES = eINSTANCE.getAtomFunction_InternalVariables();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.atom.impl.AtomServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.atom.impl.AtomServiceImpl
		 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getAtomService()
		 * @generated
		 */
		EClass ATOM_SERVICE = eINSTANCE.getAtomService();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATOM_SERVICE__TIMEOUT = eINSTANCE.getAtomService_Timeout();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.atom.impl.InternalParamImpl <em>Internal Param</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.atom.impl.InternalParamImpl
		 * @see com.hundsun.ares.studio.atom.impl.AtomPackageImpl#getInternalParam()
		 * @generated
		 */
		EClass INTERNAL_PARAM = eINSTANCE.getInternalParam();

	}

} //AtomPackage
