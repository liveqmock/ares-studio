/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.logic.impl;

import com.hundsun.ares.studio.atom.impl.AtomFunctionImpl;

import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.LogicService;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.logic.impl.LogicServiceImpl#isIsCheckAccess <em>Is Check Access</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.logic.impl.LogicServiceImpl#getTimeOut <em>Time Out</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicServiceImpl extends AtomFunctionImpl implements LogicService {
	/**
	 * The default value of the '{@link #isIsCheckAccess() <em>Is Check Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCheckAccess()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CHECK_ACCESS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsCheckAccess() <em>Is Check Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCheckAccess()
	 * @generated
	 * @ordered
	 */
	protected boolean isCheckAccess = IS_CHECK_ACCESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeOut() <em>Time Out</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeOut()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_OUT_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTimeOut() <em>Time Out</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeOut()
	 * @generated
	 * @ordered
	 */
	protected String timeOut = TIME_OUT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LogicPackage.Literals.LOGIC_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsCheckAccess() {
		return isCheckAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCheckAccess(boolean newIsCheckAccess) {
		boolean oldIsCheckAccess = isCheckAccess;
		isCheckAccess = newIsCheckAccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LogicPackage.LOGIC_SERVICE__IS_CHECK_ACCESS, oldIsCheckAccess, isCheckAccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTimeOut() {
		return timeOut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeOut(String newTimeOut) {
		String oldTimeOut = timeOut;
		timeOut = newTimeOut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LogicPackage.LOGIC_SERVICE__TIME_OUT, oldTimeOut, timeOut));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LogicPackage.LOGIC_SERVICE__IS_CHECK_ACCESS:
				return isIsCheckAccess();
			case LogicPackage.LOGIC_SERVICE__TIME_OUT:
				return getTimeOut();
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
			case LogicPackage.LOGIC_SERVICE__IS_CHECK_ACCESS:
				setIsCheckAccess((Boolean)newValue);
				return;
			case LogicPackage.LOGIC_SERVICE__TIME_OUT:
				setTimeOut((String)newValue);
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
			case LogicPackage.LOGIC_SERVICE__IS_CHECK_ACCESS:
				setIsCheckAccess(IS_CHECK_ACCESS_EDEFAULT);
				return;
			case LogicPackage.LOGIC_SERVICE__TIME_OUT:
				setTimeOut(TIME_OUT_EDEFAULT);
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
			case LogicPackage.LOGIC_SERVICE__IS_CHECK_ACCESS:
				return isCheckAccess != IS_CHECK_ACCESS_EDEFAULT;
			case LogicPackage.LOGIC_SERVICE__TIME_OUT:
				return TIME_OUT_EDEFAULT == null ? timeOut != null : !TIME_OUT_EDEFAULT.equals(timeOut);
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
		result.append(" (isCheckAccess: ");
		result.append(isCheckAccess);
		result.append(", timeOut: ");
		result.append(timeOut);
		result.append(')');
		return result.toString();
	}

} //LogicServiceImpl
