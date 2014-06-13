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
import com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stock Project Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockProjectPropertyImpl#getBaseVersion <em>Base Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StockProjectPropertyImpl extends EObjectImpl implements StockProjectProperty {
	/**
	 * The default value of the '{@link #getBaseVersion() <em>Base Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseVersion() <em>Base Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseVersion()
	 * @generated
	 * @ordered
	 */
	protected String baseVersion = BASE_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StockProjectPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.STOCK_PROJECT_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseVersion() {
		return baseVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseVersion(String newBaseVersion) {
		String oldBaseVersion = baseVersion;
		baseVersion = newBaseVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.STOCK_PROJECT_PROPERTY__BASE_VERSION, oldBaseVersion, baseVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.STOCK_PROJECT_PROPERTY__BASE_VERSION:
				return getBaseVersion();
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
			case ChousePackage.STOCK_PROJECT_PROPERTY__BASE_VERSION:
				setBaseVersion((String)newValue);
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
			case ChousePackage.STOCK_PROJECT_PROPERTY__BASE_VERSION:
				setBaseVersion(BASE_VERSION_EDEFAULT);
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
			case ChousePackage.STOCK_PROJECT_PROPERTY__BASE_VERSION:
				return BASE_VERSION_EDEFAULT == null ? baseVersion != null : !BASE_VERSION_EDEFAULT.equals(baseVersion);
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
		result.append(" (baseVersion: ");
		result.append(baseVersion);
		result.append(')');
		return result.toString();
	}

} //StockProjectPropertyImpl
