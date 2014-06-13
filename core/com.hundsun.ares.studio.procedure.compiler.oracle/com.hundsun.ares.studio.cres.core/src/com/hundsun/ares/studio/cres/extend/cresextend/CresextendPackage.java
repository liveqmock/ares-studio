/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend;

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
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendFactory
 * @model kind="package"
 * @generated
 */
public interface CresextendPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cresextend";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.hundsun.com/ares/studio/cres/extend/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cresextend";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CresextendPackage eINSTANCE = com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl <em>Moudle Depend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getMoudleDepend()
	 * @generated
	 */
	int MOUDLE_DEPEND = 0;

	/**
	 * The feature id for the '<em><b>Module Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUDLE_DEPEND__MODULE_PATH = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUDLE_DEPEND__NAME = 1;

	/**
	 * The number of structural features of the '<em>Moudle Depend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUDLE_DEPEND_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl <em>Cres Moudle Extend Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getCresMoudleExtendProperty()
	 * @generated
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Depends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS = 0;

	/**
	 * The feature id for the '<em><b>Sub Sys ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID = 1;

	/**
	 * The feature id for the '<em><b>Data Base Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME = 2;

	/**
	 * The feature id for the '<em><b>Data Base Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN = 3;

	/**
	 * The feature id for the '<em><b>Biz Property Config</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG = 4;

	/**
	 * The number of structural features of the '<em>Cres Moudle Extend Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_MOUDLE_EXTEND_PROPERTY_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl <em>Cres Project Extend Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getCresProjectExtendProperty()
	 * @generated
	 */
	int CRES_PROJECT_EXTEND_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__VERSION = 0;

	/**
	 * The feature id for the '<em><b>CName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__CNAME = 1;

	/**
	 * The feature id for the '<em><b>Short CName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__ID = 3;

	/**
	 * The feature id for the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__MANAGER = 4;

	/**
	 * The feature id for the '<em><b>Developer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER = 5;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__USER = 6;

	/**
	 * The feature id for the '<em><b>Relation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__RELATION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__NAME = 8;

	/**
	 * The feature id for the '<em><b>Writer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__WRITER = 9;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__NOTE = 10;

	/**
	 * The feature id for the '<em><b>Head File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE = 11;

	/**
	 * The feature id for the '<em><b>Proc Define</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE = 12;

	/**
	 * The feature id for the '<em><b>Gcc Define</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE = 13;

	/**
	 * The feature id for the '<em><b>Mvc Define</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE = 14;

	/**
	 * The feature id for the '<em><b>Func Define</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE = 15;

	/**
	 * The number of structural features of the '<em>Cres Project Extend Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRES_PROJECT_EXTEND_PROPERTY_FEATURE_COUNT = 16;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl <em>File Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getFileDefine()
	 * @generated
	 */
	int FILE_DEFINE = 6;

	/**
	 * The feature id for the '<em><b>Is Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_DEFINE__IS_USED = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_DEFINE__PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_DEFINE__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_DEFINE__NOTE = 3;

	/**
	 * The number of structural features of the '<em>File Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_DEFINE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.ProcDefineImpl <em>Proc Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.ProcDefineImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getProcDefine()
	 * @generated
	 */
	int PROC_DEFINE = 3;

	/**
	 * The feature id for the '<em><b>Is Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_DEFINE__IS_USED = FILE_DEFINE__IS_USED;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_DEFINE__PARAMETER = FILE_DEFINE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_DEFINE__VALUE = FILE_DEFINE__VALUE;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_DEFINE__NOTE = FILE_DEFINE__NOTE;

	/**
	 * The number of structural features of the '<em>Proc Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_DEFINE_FEATURE_COUNT = FILE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.GccDefineImpl <em>Gcc Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.GccDefineImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getGccDefine()
	 * @generated
	 */
	int GCC_DEFINE = 4;

	/**
	 * The feature id for the '<em><b>Is Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GCC_DEFINE__IS_USED = FILE_DEFINE__IS_USED;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GCC_DEFINE__PARAMETER = FILE_DEFINE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GCC_DEFINE__VALUE = FILE_DEFINE__VALUE;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GCC_DEFINE__NOTE = FILE_DEFINE__NOTE;

	/**
	 * The number of structural features of the '<em>Gcc Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GCC_DEFINE_FEATURE_COUNT = FILE_DEFINE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MvcDefineImpl <em>Mvc Define</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.MvcDefineImpl
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getMvcDefine()
	 * @generated
	 */
	int MVC_DEFINE = 5;

	/**
	 * The feature id for the '<em><b>Is Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MVC_DEFINE__IS_USED = FILE_DEFINE__IS_USED;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MVC_DEFINE__PARAMETER = FILE_DEFINE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MVC_DEFINE__VALUE = FILE_DEFINE__VALUE;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MVC_DEFINE__NOTE = FILE_DEFINE__NOTE;

	/**
	 * The number of structural features of the '<em>Mvc Define</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MVC_DEFINE_FEATURE_COUNT = FILE_DEFINE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend <em>Moudle Depend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Moudle Depend</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend
	 * @generated
	 */
	EClass getMoudleDepend();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getModulePath <em>Module Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Path</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getModulePath()
	 * @see #getMoudleDepend()
	 * @generated
	 */
	EAttribute getMoudleDepend_ModulePath();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend#getName()
	 * @see #getMoudleDepend()
	 * @generated
	 */
	EAttribute getMoudleDepend_Name();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty <em>Cres Moudle Extend Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cres Moudle Extend Property</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty
	 * @generated
	 */
	EClass getCresMoudleExtendProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDepends <em>Depends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Depends</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDepends()
	 * @see #getCresMoudleExtendProperty()
	 * @generated
	 */
	EReference getCresMoudleExtendProperty_Depends();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getSubSysID <em>Sub Sys ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Sys ID</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getSubSysID()
	 * @see #getCresMoudleExtendProperty()
	 * @generated
	 */
	EAttribute getCresMoudleExtendProperty_SubSysID();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseName <em>Data Base Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Base Name</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseName()
	 * @see #getCresMoudleExtendProperty()
	 * @generated
	 */
	EAttribute getCresMoudleExtendProperty_DataBaseName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseConn <em>Data Base Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Base Conn</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseConn()
	 * @see #getCresMoudleExtendProperty()
	 * @generated
	 */
	EAttribute getCresMoudleExtendProperty_DataBaseConn();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getBizPropertyConfig <em>Biz Property Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Biz Property Config</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getBizPropertyConfig()
	 * @see #getCresMoudleExtendProperty()
	 * @generated
	 */
	EAttribute getCresMoudleExtendProperty_BizPropertyConfig();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty <em>Cres Project Extend Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cres Project Extend Property</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty
	 * @generated
	 */
	EClass getCresProjectExtendProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getVersion()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getCName <em>CName</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CName</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getCName()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_CName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getShortCName <em>Short CName</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short CName</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getShortCName()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_ShortCName();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getId()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getManager <em>Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manager</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getManager()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Manager();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getDeveloper <em>Developer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Developer</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getDeveloper()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Developer();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getUser()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_User();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getRelation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relation</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getRelation()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Relation();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getName()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getWriter <em>Writer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Writer</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getWriter()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Writer();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getNote <em>Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Note</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getNote()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_Note();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getHeadFile <em>Head File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Head File</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getHeadFile()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EAttribute getCresProjectExtendProperty_HeadFile();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getProcDefine <em>Proc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Proc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getProcDefine()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EReference getCresProjectExtendProperty_ProcDefine();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getGccDefine <em>Gcc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gcc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getGccDefine()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EReference getCresProjectExtendProperty_GccDefine();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getMvcDefine <em>Mvc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mvc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getMvcDefine()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EReference getCresProjectExtendProperty_MvcDefine();

	/**
	 * Returns the meta object for the containment reference list '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getFuncDefine <em>Func Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Func Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty#getFuncDefine()
	 * @see #getCresProjectExtendProperty()
	 * @generated
	 */
	EReference getCresProjectExtendProperty_FuncDefine();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine <em>Proc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine
	 * @generated
	 */
	EClass getProcDefine();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.GccDefine <em>Gcc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gcc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.GccDefine
	 * @generated
	 */
	EClass getGccDefine();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine <em>Mvc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mvc Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine
	 * @generated
	 */
	EClass getMvcDefine();

	/**
	 * Returns the meta object for class '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine <em>File Define</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Define</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine
	 * @generated
	 */
	EClass getFileDefine();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#isIsUsed <em>Is Used</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Used</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#isIsUsed()
	 * @see #getFileDefine()
	 * @generated
	 */
	EAttribute getFileDefine_IsUsed();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getParameter()
	 * @see #getFileDefine()
	 * @generated
	 */
	EAttribute getFileDefine_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getValue()
	 * @see #getFileDefine()
	 * @generated
	 */
	EAttribute getFileDefine_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getNote <em>Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Note</em>'.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine#getNote()
	 * @see #getFileDefine()
	 * @generated
	 */
	EAttribute getFileDefine_Note();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CresextendFactory getCresextendFactory();

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
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl <em>Moudle Depend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getMoudleDepend()
		 * @generated
		 */
		EClass MOUDLE_DEPEND = eINSTANCE.getMoudleDepend();

		/**
		 * The meta object literal for the '<em><b>Module Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUDLE_DEPEND__MODULE_PATH = eINSTANCE.getMoudleDepend_ModulePath();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUDLE_DEPEND__NAME = eINSTANCE.getMoudleDepend_Name();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl <em>Cres Moudle Extend Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getCresMoudleExtendProperty()
		 * @generated
		 */
		EClass CRES_MOUDLE_EXTEND_PROPERTY = eINSTANCE.getCresMoudleExtendProperty();

		/**
		 * The meta object literal for the '<em><b>Depends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS = eINSTANCE.getCresMoudleExtendProperty_Depends();

		/**
		 * The meta object literal for the '<em><b>Sub Sys ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID = eINSTANCE.getCresMoudleExtendProperty_SubSysID();

		/**
		 * The meta object literal for the '<em><b>Data Base Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME = eINSTANCE.getCresMoudleExtendProperty_DataBaseName();

		/**
		 * The meta object literal for the '<em><b>Data Base Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN = eINSTANCE.getCresMoudleExtendProperty_DataBaseConn();

		/**
		 * The meta object literal for the '<em><b>Biz Property Config</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG = eINSTANCE.getCresMoudleExtendProperty_BizPropertyConfig();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl <em>Cres Project Extend Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresProjectExtendPropertyImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getCresProjectExtendProperty()
		 * @generated
		 */
		EClass CRES_PROJECT_EXTEND_PROPERTY = eINSTANCE.getCresProjectExtendProperty();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__VERSION = eINSTANCE.getCresProjectExtendProperty_Version();

		/**
		 * The meta object literal for the '<em><b>CName</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__CNAME = eINSTANCE.getCresProjectExtendProperty_CName();

		/**
		 * The meta object literal for the '<em><b>Short CName</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME = eINSTANCE.getCresProjectExtendProperty_ShortCName();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__ID = eINSTANCE.getCresProjectExtendProperty_Id();

		/**
		 * The meta object literal for the '<em><b>Manager</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__MANAGER = eINSTANCE.getCresProjectExtendProperty_Manager();

		/**
		 * The meta object literal for the '<em><b>Developer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER = eINSTANCE.getCresProjectExtendProperty_Developer();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__USER = eINSTANCE.getCresProjectExtendProperty_User();

		/**
		 * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__RELATION = eINSTANCE.getCresProjectExtendProperty_Relation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__NAME = eINSTANCE.getCresProjectExtendProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Writer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__WRITER = eINSTANCE.getCresProjectExtendProperty_Writer();

		/**
		 * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__NOTE = eINSTANCE.getCresProjectExtendProperty_Note();

		/**
		 * The meta object literal for the '<em><b>Head File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE = eINSTANCE.getCresProjectExtendProperty_HeadFile();

		/**
		 * The meta object literal for the '<em><b>Proc Define</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE = eINSTANCE.getCresProjectExtendProperty_ProcDefine();

		/**
		 * The meta object literal for the '<em><b>Gcc Define</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE = eINSTANCE.getCresProjectExtendProperty_GccDefine();

		/**
		 * The meta object literal for the '<em><b>Mvc Define</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE = eINSTANCE.getCresProjectExtendProperty_MvcDefine();

		/**
		 * The meta object literal for the '<em><b>Func Define</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE = eINSTANCE.getCresProjectExtendProperty_FuncDefine();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.ProcDefineImpl <em>Proc Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.ProcDefineImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getProcDefine()
		 * @generated
		 */
		EClass PROC_DEFINE = eINSTANCE.getProcDefine();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.GccDefineImpl <em>Gcc Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.GccDefineImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getGccDefine()
		 * @generated
		 */
		EClass GCC_DEFINE = eINSTANCE.getGccDefine();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MvcDefineImpl <em>Mvc Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.MvcDefineImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getMvcDefine()
		 * @generated
		 */
		EClass MVC_DEFINE = eINSTANCE.getMvcDefine();

		/**
		 * The meta object literal for the '{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl <em>File Define</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl
		 * @see com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendPackageImpl#getFileDefine()
		 * @generated
		 */
		EClass FILE_DEFINE = eINSTANCE.getFileDefine();

		/**
		 * The meta object literal for the '<em><b>Is Used</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_DEFINE__IS_USED = eINSTANCE.getFileDefine_IsUsed();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_DEFINE__PARAMETER = eINSTANCE.getFileDefine_Parameter();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_DEFINE__VALUE = eINSTANCE.getFileDefine_Value();

		/**
		 * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_DEFINE__NOTE = eINSTANCE.getFileDefine_Note();

	}

} //CresextendPackage
