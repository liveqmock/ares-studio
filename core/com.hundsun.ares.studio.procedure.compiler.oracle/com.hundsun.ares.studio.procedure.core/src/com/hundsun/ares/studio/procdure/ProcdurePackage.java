/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.model.CorePackage;

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
 * @see com.hundsun.ares.studio.procdure.ProcdureFactory
 * @model kind="package"
 * @generated
 */
public interface ProcdurePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "procdure";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/procedure/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "procedure";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProcdurePackage eINSTANCE = com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl <em>Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.procdure.impl.ProcedureImpl
	 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getProcedure()
	 * @generated
	 */
	int PROCEDURE = 0;

	/**
	 * The feature id for the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__INPUT_COLLECTION = BizPackage.BIZ_INTERFACE__INPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__OUTPUT_COLLECTION = BizPackage.BIZ_INTERFACE__OUTPUT_COLLECTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__INPUT_PARAMETERS = BizPackage.BIZ_INTERFACE__INPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__OUTPUT_PARAMETERS = BizPackage.BIZ_INTERFACE__OUTPUT_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__INTERFACE_FLAG = BizPackage.BIZ_INTERFACE__INTERFACE_FLAG;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DATA = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DATA2 = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Chinese Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__CHINESE_NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DESCRIPTION = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Object Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__OBJECT_ID = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__GROUP = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__HISTORIES = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__FULLY_QUALIFIED_NAME = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DATABASE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Pseudo Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__PSEUDO_CODE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Internal Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__INTERNAL_VARIABLES = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Begin Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__BEGIN_CODE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>End Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__END_CODE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__RETURN_TYPE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Define Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DEFINE_TYPE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Biz Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__BIZ_TYPE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Create Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__CREATE_DATE = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Related Table Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__RELATED_TABLE_INFO = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Related Basic Data Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__RELATED_BASIC_DATA_INFO = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Auto Transaction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__AUTO_TRANSACTION = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 20;

	/**
	 * The number of structural features of the '<em>Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE_FEATURE_COUNT = BizPackage.BIZ_INTERFACE_FEATURE_COUNT + 21;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.procdure.impl.RelatedInfoImpl <em>Related Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.procdure.impl.RelatedInfoImpl
	 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getRelatedInfo()
	 * @generated
	 */
	int RELATED_INFO = 1;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO__DATA = CorePackage.EXTENSIBLE_MODEL__DATA;

	/**
	 * The feature id for the '<em><b>Data2</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO__DATA2 = CorePackage.EXTENSIBLE_MODEL__DATA2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO__ID = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO__COMMENT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO__PATH = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Related Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_INFO_FEATURE_COUNT = CorePackage.EXTENSIBLE_MODEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.procdure.DefineType <em>Define Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.procdure.DefineType
	 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getDefineType()
	 * @generated
	 */
	int DEFINE_TYPE = 2;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.procdure.BizType <em>Biz Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.procdure.BizType
	 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getBizType()
	 * @generated
	 */
	int BIZ_TYPE = 3;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.procdure.Procedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Procedure</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure
	 * @generated
	 */
	EClass getProcedure();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getDatabase()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_Database();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getPseudoCode <em>Pseudo Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pseudo Code</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getPseudoCode()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_PseudoCode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.procdure.Procedure#getInternalVariables <em>Internal Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Variables</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getInternalVariables()
	 * @see #getProcedure()
	 * @generated
	 */
	EReference getProcedure_InternalVariables();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getBeginCode <em>Begin Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Begin Code</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getBeginCode()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_BeginCode();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getEndCode <em>End Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Code</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getEndCode()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_EndCode();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getReturnType()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_ReturnType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getDefineType <em>Define Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Define Type</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getDefineType()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_DefineType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getBizType <em>Biz Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Biz Type</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getBizType()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_BizType();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#getCreateDate <em>Create Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create Date</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getCreateDate()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_CreateDate();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.procdure.Procedure#getRelatedTableInfo <em>Related Table Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Related Table Info</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getRelatedTableInfo()
	 * @see #getProcedure()
	 * @generated
	 */
	EReference getProcedure_RelatedTableInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.procdure.Procedure#getRelatedBasicDataInfo <em>Related Basic Data Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Related Basic Data Info</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#getRelatedBasicDataInfo()
	 * @see #getProcedure()
	 * @generated
	 */
	EReference getProcedure_RelatedBasicDataInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.Procedure#isAutoTransaction <em>Auto Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Transaction</em>'.
	 * @see com.hundsun.ares.studio.procdure.Procedure#isAutoTransaction()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_AutoTransaction();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.procdure.RelatedInfo <em>Related Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Related Info</em>'.
	 * @see com.hundsun.ares.studio.procdure.RelatedInfo
	 * @generated
	 */
	EClass getRelatedInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.hundsun.ares.studio.procdure.RelatedInfo#getId()
	 * @see #getRelatedInfo()
	 * @generated
	 */
	EAttribute getRelatedInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see com.hundsun.ares.studio.procdure.RelatedInfo#getComment()
	 * @see #getRelatedInfo()
	 * @generated
	 */
	EAttribute getRelatedInfo_Comment();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.procdure.RelatedInfo#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.hundsun.ares.studio.procdure.RelatedInfo#getPath()
	 * @see #getRelatedInfo()
	 * @generated
	 */
	EAttribute getRelatedInfo_Path();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.procdure.DefineType <em>Define Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Define Type</em>'.
	 * @see com.hundsun.ares.studio.procdure.DefineType
	 * @generated
	 */
	EEnum getDefineType();

	/**
	 * Returns the meta object for enum '{@link com.hundsun.ares.studio.procdure.BizType <em>Biz Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Biz Type</em>'.
	 * @see com.hundsun.ares.studio.procdure.BizType
	 * @generated
	 */
	EEnum getBizType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProcdureFactory getProcdureFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.procdure.impl.ProcedureImpl <em>Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.procdure.impl.ProcedureImpl
		 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getProcedure()
		 * @generated
		 */
		EClass PROCEDURE = eINSTANCE.getProcedure();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__DATABASE = eINSTANCE.getProcedure_Database();

		/**
		 * The meta object literal for the '<em><b>Pseudo Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__PSEUDO_CODE = eINSTANCE.getProcedure_PseudoCode();

		/**
		 * The meta object literal for the '<em><b>Internal Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCEDURE__INTERNAL_VARIABLES = eINSTANCE.getProcedure_InternalVariables();

		/**
		 * The meta object literal for the '<em><b>Begin Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__BEGIN_CODE = eINSTANCE.getProcedure_BeginCode();

		/**
		 * The meta object literal for the '<em><b>End Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__END_CODE = eINSTANCE.getProcedure_EndCode();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__RETURN_TYPE = eINSTANCE.getProcedure_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Define Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__DEFINE_TYPE = eINSTANCE.getProcedure_DefineType();

		/**
		 * The meta object literal for the '<em><b>Biz Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__BIZ_TYPE = eINSTANCE.getProcedure_BizType();

		/**
		 * The meta object literal for the '<em><b>Create Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__CREATE_DATE = eINSTANCE.getProcedure_CreateDate();

		/**
		 * The meta object literal for the '<em><b>Related Table Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCEDURE__RELATED_TABLE_INFO = eINSTANCE.getProcedure_RelatedTableInfo();

		/**
		 * The meta object literal for the '<em><b>Related Basic Data Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCEDURE__RELATED_BASIC_DATA_INFO = eINSTANCE.getProcedure_RelatedBasicDataInfo();

		/**
		 * The meta object literal for the '<em><b>Auto Transaction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__AUTO_TRANSACTION = eINSTANCE.getProcedure_AutoTransaction();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.procdure.impl.RelatedInfoImpl <em>Related Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.procdure.impl.RelatedInfoImpl
		 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getRelatedInfo()
		 * @generated
		 */
		EClass RELATED_INFO = eINSTANCE.getRelatedInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATED_INFO__ID = eINSTANCE.getRelatedInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATED_INFO__COMMENT = eINSTANCE.getRelatedInfo_Comment();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATED_INFO__PATH = eINSTANCE.getRelatedInfo_Path();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.procdure.DefineType <em>Define Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.procdure.DefineType
		 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getDefineType()
		 * @generated
		 */
		EEnum DEFINE_TYPE = eINSTANCE.getDefineType();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.procdure.BizType <em>Biz Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.procdure.BizType
		 * @see com.hundsun.ares.studio.procdure.impl.ProcdurePackageImpl#getBizType()
		 * @generated
		 */
		EEnum BIZ_TYPE = eINSTANCE.getBizType();

	}

} //ProcdurePackage
