/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CTCPM Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCPMDetailImpl#isPrimarkKey <em>Primark Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CTCPMDetailImpl extends ModifyDetailImpl implements CTCPMDetail {
	/**
	 * The default value of the '{@link #isPrimarkKey() <em>Primark Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimarkKey()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIMARK_KEY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrimarkKey() <em>Primark Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimarkKey()
	 * @generated
	 * @ordered
	 */
	protected boolean primarkKey = PRIMARK_KEY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CTCPMDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.CTCPM_DETAIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrimarkKey() {
		return primarkKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimarkKey(boolean newPrimarkKey) {
		boolean oldPrimarkKey = primarkKey;
		primarkKey = newPrimarkKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CTCPM_DETAIL__PRIMARK_KEY, oldPrimarkKey, primarkKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.CTCPM_DETAIL__PRIMARK_KEY:
				return isPrimarkKey();
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
			case ChousePackage.CTCPM_DETAIL__PRIMARK_KEY:
				setPrimarkKey((Boolean)newValue);
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
			case ChousePackage.CTCPM_DETAIL__PRIMARK_KEY:
				setPrimarkKey(PRIMARK_KEY_EDEFAULT);
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
			case ChousePackage.CTCPM_DETAIL__PRIMARK_KEY:
				return primarkKey != PRIMARK_KEY_EDEFAULT;
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
		result.append(" (primarkKey: ");
		result.append(primarkKey);
		result.append(')');
		return result.toString();
	}
	
} //CTCPMDetailImpl
