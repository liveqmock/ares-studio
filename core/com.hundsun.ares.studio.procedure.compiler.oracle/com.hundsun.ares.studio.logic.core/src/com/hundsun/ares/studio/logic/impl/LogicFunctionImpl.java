/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic.impl;

import com.hundsun.ares.studio.atom.impl.AtomFunctionImpl;

import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.logic.impl.LogicFunctionImpl#isIsTransFunc <em>Is Trans Func</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicFunctionImpl extends AtomFunctionImpl implements LogicFunction {
	/**
	 * The default value of the '{@link #isIsTransFunc() <em>Is Trans Func</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTransFunc()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TRANS_FUNC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsTransFunc() <em>Is Trans Func</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTransFunc()
	 * @generated
	 * @ordered
	 */
	protected boolean isTransFunc = IS_TRANS_FUNC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LogicPackage.Literals.LOGIC_FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsTransFunc() {
		return isTransFunc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsTransFunc(boolean newIsTransFunc) {
		boolean oldIsTransFunc = isTransFunc;
		isTransFunc = newIsTransFunc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LogicPackage.LOGIC_FUNCTION__IS_TRANS_FUNC, oldIsTransFunc, isTransFunc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LogicPackage.LOGIC_FUNCTION__IS_TRANS_FUNC:
				return isIsTransFunc();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LogicPackage.LOGIC_FUNCTION__IS_TRANS_FUNC:
				setIsTransFunc((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LogicPackage.LOGIC_FUNCTION__IS_TRANS_FUNC:
				setIsTransFunc(IS_TRANS_FUNC_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LogicPackage.LOGIC_FUNCTION__IS_TRANS_FUNC:
				return isTransFunc != IS_TRANS_FUNC_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isTransFunc: ");
		result.append(isTransFunc);
		result.append(')');
		return result.toString();
	}

} //LogicFunctionImpl
