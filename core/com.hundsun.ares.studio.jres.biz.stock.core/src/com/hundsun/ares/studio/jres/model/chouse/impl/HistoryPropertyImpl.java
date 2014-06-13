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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.HistoryProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>History Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.HistoryPropertyImpl#getInternalVersion <em>Internal Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HistoryPropertyImpl extends EObjectImpl implements HistoryProperty {
	/**
	 * The default value of the '{@link #getInternalVersion() <em>Internal Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERNAL_VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getInternalVersion() <em>Internal Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalVersion()
	 * @generated
	 * @ordered
	 */
	protected String internalVersion = INTERNAL_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistoryPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.HISTORY_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInternalVersion() {
		return internalVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInternalVersion(String newInternalVersion) {
		String oldInternalVersion = internalVersion;
		internalVersion = newInternalVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.HISTORY_PROPERTY__INTERNAL_VERSION, oldInternalVersion, internalVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.HISTORY_PROPERTY__INTERNAL_VERSION:
				return getInternalVersion();
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
			case ChousePackage.HISTORY_PROPERTY__INTERNAL_VERSION:
				setInternalVersion((String)newValue);
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
			case ChousePackage.HISTORY_PROPERTY__INTERNAL_VERSION:
				setInternalVersion(INTERNAL_VERSION_EDEFAULT);
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
			case ChousePackage.HISTORY_PROPERTY__INTERNAL_VERSION:
				return INTERNAL_VERSION_EDEFAULT == null ? internalVersion != null : !INTERNAL_VERSION_EDEFAULT.equals(internalVersion);
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
		result.append(" (internalVersion: ");
		result.append(internalVersion);
		result.append(')');
		return result.toString();
	}

} //HistoryPropertyImpl
