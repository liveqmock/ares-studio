/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.atom.impl;

import com.hundsun.ares.studio.atom.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.AtomService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AtomFactoryImpl extends EFactoryImpl implements AtomFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AtomFactory init() {
		try {
			AtomFactory theAtomFactory = (AtomFactory)EPackage.Registry.INSTANCE.getEFactory(AtomPackage.eNS_URI);
			if (theAtomFactory != null) {
				return theAtomFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AtomFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomFactoryImpl() {
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
			case AtomPackage.ATOM_FUNCTION: return createAtomFunction();
			case AtomPackage.ATOM_SERVICE: return createAtomService();
			case AtomPackage.INTERNAL_PARAM: return createInternalParam();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomFunction createAtomFunction() {
		AtomFunctionImpl atomFunction = new AtomFunctionImpl();
		return atomFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomService createAtomService() {
		AtomServiceImpl atomService = new AtomServiceImpl();
		return atomService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalParam createInternalParam() {
		InternalParamImpl internalParam = new InternalParamImpl();
		return internalParam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomPackage getAtomPackage() {
		return (AtomPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AtomPackage getPackage() {
		return AtomPackage.eINSTANCE;
	}

} //AtomFactoryImpl
