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
import com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stock DB Context Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl#getStartVersion <em>Start Version</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockDBContextPropertyImpl#getEndVersion <em>End Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StockDBContextPropertyImpl extends EObjectImpl implements StockDBContextProperty {
	/**
	 * The default value of the '{@link #getStartVersion() <em>Start Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String START_VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getStartVersion() <em>Start Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartVersion()
	 * @generated
	 * @ordered
	 */
	protected String startVersion = START_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndVersion() <em>End Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String END_VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEndVersion() <em>End Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndVersion()
	 * @generated
	 * @ordered
	 */
	protected String endVersion = END_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StockDBContextPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.STOCK_DB_CONTEXT_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartVersion() {
		return startVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartVersion(String newStartVersion) {
		String oldStartVersion = startVersion;
		startVersion = newStartVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.STOCK_DB_CONTEXT_PROPERTY__START_VERSION, oldStartVersion, startVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndVersion() {
		return endVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndVersion(String newEndVersion) {
		String oldEndVersion = endVersion;
		endVersion = newEndVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.STOCK_DB_CONTEXT_PROPERTY__END_VERSION, oldEndVersion, endVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__START_VERSION:
				return getStartVersion();
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__END_VERSION:
				return getEndVersion();
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
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__START_VERSION:
				setStartVersion((String)newValue);
				return;
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__END_VERSION:
				setEndVersion((String)newValue);
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
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__START_VERSION:
				setStartVersion(START_VERSION_EDEFAULT);
				return;
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__END_VERSION:
				setEndVersion(END_VERSION_EDEFAULT);
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
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__START_VERSION:
				return START_VERSION_EDEFAULT == null ? startVersion != null : !START_VERSION_EDEFAULT.equals(startVersion);
			case ChousePackage.STOCK_DB_CONTEXT_PROPERTY__END_VERSION:
				return END_VERSION_EDEFAULT == null ? endVersion != null : !END_VERSION_EDEFAULT.equals(endVersion);
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
		result.append(" (startVersion: ");
		result.append(startVersion);
		result.append(", endVersion: ");
		result.append(endVersion);
		result.append(')');
		return result.toString();
	}

} //StockDBContextPropertyImpl
