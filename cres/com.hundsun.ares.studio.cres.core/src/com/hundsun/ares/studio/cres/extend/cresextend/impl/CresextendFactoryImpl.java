/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CresextendFactoryImpl extends EFactoryImpl implements CresextendFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CresextendFactory init() {
		try {
			CresextendFactory theCresextendFactory = (CresextendFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.hundsun.com/ares/studio/cres/extend/1.0.0"); 
			if (theCresextendFactory != null) {
				return theCresextendFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CresextendFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresextendFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CresextendPackage.MOUDLE_DEPEND: return createMoudleDepend();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY: return createCresMoudleExtendProperty();
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY: return createCresProjectExtendProperty();
			case CresextendPackage.PROC_DEFINE: return createProcDefine();
			case CresextendPackage.GCC_DEFINE: return createGccDefine();
			case CresextendPackage.MVC_DEFINE: return createMvcDefine();
			case CresextendPackage.FILE_DEFINE: return createFileDefine();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MoudleDepend createMoudleDepend() {
		MoudleDependImpl moudleDepend = new MoudleDependImpl();
		return moudleDepend;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresMoudleExtendProperty createCresMoudleExtendProperty() {
		CresMoudleExtendPropertyImpl cresMoudleExtendProperty = new CresMoudleExtendPropertyImpl();
		return cresMoudleExtendProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresProjectExtendProperty createCresProjectExtendProperty() {
		CresProjectExtendPropertyImpl cresProjectExtendProperty = new CresProjectExtendPropertyImpl();
		return cresProjectExtendProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcDefine createProcDefine() {
		ProcDefineImpl procDefine = new ProcDefineImpl();
		return procDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GccDefine createGccDefine() {
		GccDefineImpl gccDefine = new GccDefineImpl();
		return gccDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MvcDefine createMvcDefine() {
		MvcDefineImpl mvcDefine = new MvcDefineImpl();
		return mvcDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileDefine createFileDefine() {
		FileDefineImpl fileDefine = new FileDefineImpl();
		return fileDefine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresextendPackage getCresextendPackage() {
		return (CresextendPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CresextendPackage getPackage() {
		return CresextendPackage.eINSTANCE;
	}

} //CresextendFactoryImpl
