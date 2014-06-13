/**
 */
package com.hundsun.ares.studio.biz.impl;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ErrorInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl#getErrorNo <em>Error No</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl#getErrorInfo <em>Error Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.ErrorInfoImpl#getLevel <em>Level</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ErrorInfoImpl extends EObjectImpl implements ErrorInfo {
	/**
	 * The default value of the '{@link #getErrorNo() <em>Error No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorNo()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_NO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorNo() <em>Error No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorNo()
	 * @generated
	 * @ordered
	 */
	protected String errorNo = ERROR_NO_EDEFAULT;

	/**
	 * The default value of the '{@link #getErrorInfo() <em>Error Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorInfo() <em>Error Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorInfo()
	 * @generated
	 * @ordered
	 */
	protected String errorInfo = ERROR_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected String level = LEVEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ErrorInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BizPackage.Literals.ERROR_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorNo() {
		return errorNo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorNo(String newErrorNo) {
		String oldErrorNo = errorNo;
		errorNo = newErrorNo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.ERROR_INFO__ERROR_NO, oldErrorNo, errorNo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorInfo(String newErrorInfo) {
		String oldErrorInfo = errorInfo;
		errorInfo = newErrorInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.ERROR_INFO__ERROR_INFO, oldErrorInfo, errorInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.ERROR_INFO__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevel(String newLevel) {
		String oldLevel = level;
		level = newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BizPackage.ERROR_INFO__LEVEL, oldLevel, level));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BizPackage.ERROR_INFO__ERROR_NO:
				return getErrorNo();
			case BizPackage.ERROR_INFO__ERROR_INFO:
				return getErrorInfo();
			case BizPackage.ERROR_INFO__DESCRIPTION:
				return getDescription();
			case BizPackage.ERROR_INFO__LEVEL:
				return getLevel();
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
			case BizPackage.ERROR_INFO__ERROR_NO:
				setErrorNo((String)newValue);
				return;
			case BizPackage.ERROR_INFO__ERROR_INFO:
				setErrorInfo((String)newValue);
				return;
			case BizPackage.ERROR_INFO__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case BizPackage.ERROR_INFO__LEVEL:
				setLevel((String)newValue);
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
			case BizPackage.ERROR_INFO__ERROR_NO:
				setErrorNo(ERROR_NO_EDEFAULT);
				return;
			case BizPackage.ERROR_INFO__ERROR_INFO:
				setErrorInfo(ERROR_INFO_EDEFAULT);
				return;
			case BizPackage.ERROR_INFO__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case BizPackage.ERROR_INFO__LEVEL:
				setLevel(LEVEL_EDEFAULT);
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
			case BizPackage.ERROR_INFO__ERROR_NO:
				return ERROR_NO_EDEFAULT == null ? errorNo != null : !ERROR_NO_EDEFAULT.equals(errorNo);
			case BizPackage.ERROR_INFO__ERROR_INFO:
				return ERROR_INFO_EDEFAULT == null ? errorInfo != null : !ERROR_INFO_EDEFAULT.equals(errorInfo);
			case BizPackage.ERROR_INFO__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case BizPackage.ERROR_INFO__LEVEL:
				return LEVEL_EDEFAULT == null ? level != null : !LEVEL_EDEFAULT.equals(level);
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
		result.append(" (errorNo: ");
		result.append(errorNo);
		result.append(", errorInfo: ");
		result.append(errorInfo);
		result.append(", description: ");
		result.append(description);
		result.append(", level: ");
		result.append(level);
		result.append(')');
		return result.toString();
	}

} //ErrorInfoImpl
