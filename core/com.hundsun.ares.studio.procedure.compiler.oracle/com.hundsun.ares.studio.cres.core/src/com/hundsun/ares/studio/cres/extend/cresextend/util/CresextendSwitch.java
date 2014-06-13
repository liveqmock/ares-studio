/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.util;

import com.hundsun.ares.studio.cres.extend.cresextend.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage
 * @generated
 */
public class CresextendSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CresextendPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CresextendSwitch() {
		if (modelPackage == null) {
			modelPackage = CresextendPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CresextendPackage.MOUDLE_DEPEND: {
				MoudleDepend moudleDepend = (MoudleDepend)theEObject;
				T result = caseMoudleDepend(moudleDepend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY: {
				CresMoudleExtendProperty cresMoudleExtendProperty = (CresMoudleExtendProperty)theEObject;
				T result = caseCresMoudleExtendProperty(cresMoudleExtendProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.CRES_PROJECT_EXTEND_PROPERTY: {
				CresProjectExtendProperty cresProjectExtendProperty = (CresProjectExtendProperty)theEObject;
				T result = caseCresProjectExtendProperty(cresProjectExtendProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.PROC_DEFINE: {
				ProcDefine procDefine = (ProcDefine)theEObject;
				T result = caseProcDefine(procDefine);
				if (result == null) result = caseFileDefine(procDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.GCC_DEFINE: {
				GccDefine gccDefine = (GccDefine)theEObject;
				T result = caseGccDefine(gccDefine);
				if (result == null) result = caseFileDefine(gccDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.MVC_DEFINE: {
				MvcDefine mvcDefine = (MvcDefine)theEObject;
				T result = caseMvcDefine(mvcDefine);
				if (result == null) result = caseFileDefine(mvcDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CresextendPackage.FILE_DEFINE: {
				FileDefine fileDefine = (FileDefine)theEObject;
				T result = caseFileDefine(fileDefine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Moudle Depend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Moudle Depend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMoudleDepend(MoudleDepend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cres Moudle Extend Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cres Moudle Extend Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCresMoudleExtendProperty(CresMoudleExtendProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cres Project Extend Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cres Project Extend Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCresProjectExtendProperty(CresProjectExtendProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Proc Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Proc Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcDefine(ProcDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gcc Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gcc Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGccDefine(GccDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mvc Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mvc Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMvcDefine(MvcDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Define</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Define</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileDefine(FileDefine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //CresextendSwitch
