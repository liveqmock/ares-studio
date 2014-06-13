/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Biz Property Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.BizPropertyConfigImpl#getEname <em>Ename</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BizPropertyConfigImpl extends MetadataItemImpl implements BizPropertyConfig {
	/**
	 * The default value of the '{@link #getEname() <em>Ename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEname()
	 * @generated
	 * @ordered
	 */
	protected static final String ENAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEname() <em>Ename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEname()
	 * @generated
	 * @ordered
	 */
	protected String ename = ENAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizPropertyConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.BIZ_PROPERTY_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEname(String newEname) {
		String oldEname = ename;
		ename = newEname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.BIZ_PROPERTY_CONFIG__ENAME, oldEname, ename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.BIZ_PROPERTY_CONFIG__ENAME:
				return getEname();
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
			case MetadataPackage.BIZ_PROPERTY_CONFIG__ENAME:
				setEname((String)newValue);
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
			case MetadataPackage.BIZ_PROPERTY_CONFIG__ENAME:
				setEname(ENAME_EDEFAULT);
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
			case MetadataPackage.BIZ_PROPERTY_CONFIG__ENAME:
				return ENAME_EDEFAULT == null ? ename != null : !ENAME_EDEFAULT.equals(ename);
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
		result.append(" (ename: ");
		result.append(ename);
		result.append(')');
		return result.toString();
	}

} //BizPropertyConfigImpl
