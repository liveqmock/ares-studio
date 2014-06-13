/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.util;

import com.hundsun.ares.studio.cres.extend.cresextend.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage
 * @generated
 */
public class CresextendAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CresextendPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresextendAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CresextendPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CresextendSwitch<Adapter> modelSwitch =
		new CresextendSwitch<Adapter>() {
			@Override
			public Adapter caseMoudleDepend(MoudleDepend object) {
				return createMoudleDependAdapter();
			}
			@Override
			public Adapter caseCresMoudleExtendProperty(CresMoudleExtendProperty object) {
				return createCresMoudleExtendPropertyAdapter();
			}
			@Override
			public Adapter caseCresProjectExtendProperty(CresProjectExtendProperty object) {
				return createCresProjectExtendPropertyAdapter();
			}
			@Override
			public Adapter caseProcDefine(ProcDefine object) {
				return createProcDefineAdapter();
			}
			@Override
			public Adapter caseGccDefine(GccDefine object) {
				return createGccDefineAdapter();
			}
			@Override
			public Adapter caseMvcDefine(MvcDefine object) {
				return createMvcDefineAdapter();
			}
			@Override
			public Adapter caseFileDefine(FileDefine object) {
				return createFileDefineAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend <em>Moudle Depend</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend
	 * @generated
	 */
	public Adapter createMoudleDependAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty <em>Cres Moudle Extend Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty
	 * @generated
	 */
	public Adapter createCresMoudleExtendPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty <em>Cres Project Extend Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty
	 * @generated
	 */
	public Adapter createCresProjectExtendPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine <em>Proc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine
	 * @generated
	 */
	public Adapter createProcDefineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.GccDefine <em>Gcc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.GccDefine
	 * @generated
	 */
	public Adapter createGccDefineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine <em>Mvc Define</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine
	 * @generated
	 */
	public Adapter createMvcDefineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.hundsun.ares.studio.cres.extend.cresextend.FileDefine <em>File Define</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.FileDefine
	 * @generated
	 */
	public Adapter createFileDefineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //CresextendAdapterFactory
