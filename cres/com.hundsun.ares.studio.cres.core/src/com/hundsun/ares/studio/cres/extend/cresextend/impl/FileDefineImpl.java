/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Define</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl#isIsUsed <em>Is Used</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.FileDefineImpl#getNote <em>Note</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileDefineImpl extends EObjectImpl implements FileDefine {
	/**
	 * The default value of the '{@link #isIsUsed() <em>Is Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_USED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIsUsed() <em>Is Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUsed()
	 * @generated
	 * @ordered
	 */
	protected boolean isUsed = IS_USED_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected String parameter = PARAMETER_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNote() <em>Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNote()
	 * @generated
	 * @ordered
	 */
	protected String note = NOTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileDefineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CresextendPackage.Literals.FILE_DEFINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsUsed() {
		return isUsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUsed(boolean newIsUsed) {
		boolean oldIsUsed = isUsed;
		isUsed = newIsUsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.FILE_DEFINE__IS_USED, oldIsUsed, isUsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(String newParameter) {
		String oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.FILE_DEFINE__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.FILE_DEFINE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNote(String newNote) {
		String oldNote = note;
		note = newNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.FILE_DEFINE__NOTE, oldNote, note));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CresextendPackage.FILE_DEFINE__IS_USED:
				return isIsUsed();
			case CresextendPackage.FILE_DEFINE__PARAMETER:
				return getParameter();
			case CresextendPackage.FILE_DEFINE__VALUE:
				return getValue();
			case CresextendPackage.FILE_DEFINE__NOTE:
				return getNote();
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
			case CresextendPackage.FILE_DEFINE__IS_USED:
				setIsUsed((Boolean)newValue);
				return;
			case CresextendPackage.FILE_DEFINE__PARAMETER:
				setParameter((String)newValue);
				return;
			case CresextendPackage.FILE_DEFINE__VALUE:
				setValue((String)newValue);
				return;
			case CresextendPackage.FILE_DEFINE__NOTE:
				setNote((String)newValue);
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
			case CresextendPackage.FILE_DEFINE__IS_USED:
				setIsUsed(IS_USED_EDEFAULT);
				return;
			case CresextendPackage.FILE_DEFINE__PARAMETER:
				setParameter(PARAMETER_EDEFAULT);
				return;
			case CresextendPackage.FILE_DEFINE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case CresextendPackage.FILE_DEFINE__NOTE:
				setNote(NOTE_EDEFAULT);
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
			case CresextendPackage.FILE_DEFINE__IS_USED:
				return isUsed != IS_USED_EDEFAULT;
			case CresextendPackage.FILE_DEFINE__PARAMETER:
				return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
			case CresextendPackage.FILE_DEFINE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case CresextendPackage.FILE_DEFINE__NOTE:
				return NOTE_EDEFAULT == null ? note != null : !NOTE_EDEFAULT.equals(note);
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
		result.append(" (isUsed: ");
		result.append(isUsed);
		result.append(", parameter: ");
		result.append(parameter);
		result.append(", value: ");
		result.append(value);
		result.append(", note: ");
		result.append(note);
		result.append(')');
		return result.toString();
	}

} //FileDefineImpl
