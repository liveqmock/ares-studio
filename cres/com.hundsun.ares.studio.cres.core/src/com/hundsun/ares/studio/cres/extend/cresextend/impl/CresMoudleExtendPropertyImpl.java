/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cres Moudle Extend Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl#getDepends <em>Depends</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl#getSubSysID <em>Sub Sys ID</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl#getDataBaseName <em>Data Base Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl#getDataBaseConn <em>Data Base Conn</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.CresMoudleExtendPropertyImpl#getBizPropertyConfig <em>Biz Property Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CresMoudleExtendPropertyImpl extends EObjectImpl implements CresMoudleExtendProperty {
	/**
	 * The cached value of the '{@link #getDepends() <em>Depends</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepends()
	 * @generated
	 * @ordered
	 */
	protected EList<MoudleDepend> depends;

	/**
	 * The default value of the '{@link #getSubSysID() <em>Sub Sys ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubSysID()
	 * @generated
	 * @ordered
	 */
	protected static final String SUB_SYS_ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getSubSysID() <em>Sub Sys ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubSysID()
	 * @generated
	 * @ordered
	 */
	protected String subSysID = SUB_SYS_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataBaseName() <em>Data Base Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseName()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_BASE_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDataBaseName() <em>Data Base Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseName()
	 * @generated
	 * @ordered
	 */
	protected String dataBaseName = DATA_BASE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataBaseConn() <em>Data Base Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseConn()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_BASE_CONN_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDataBaseConn() <em>Data Base Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataBaseConn()
	 * @generated
	 * @ordered
	 */
	protected String dataBaseConn = DATA_BASE_CONN_EDEFAULT;

	/**
	 * The default value of the '{@link #getBizPropertyConfig() <em>Biz Property Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizPropertyConfig()
	 * @generated
	 * @ordered
	 */
	protected static final String BIZ_PROPERTY_CONFIG_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getBizPropertyConfig() <em>Biz Property Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizPropertyConfig()
	 * @generated
	 * @ordered
	 */
	protected String bizPropertyConfig = BIZ_PROPERTY_CONFIG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CresMoudleExtendPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CresextendPackage.Literals.CRES_MOUDLE_EXTEND_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MoudleDepend> getDepends() {
		if (depends == null) {
			depends = new EObjectContainmentEList<MoudleDepend>(MoudleDepend.class, this, CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS);
		}
		return depends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubSysID() {
		return subSysID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubSysID(String newSubSysID) {
		String oldSubSysID = subSysID;
		subSysID = newSubSysID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID, oldSubSysID, subSysID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataBaseName() {
		return dataBaseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataBaseName(String newDataBaseName) {
		String oldDataBaseName = dataBaseName;
		dataBaseName = newDataBaseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME, oldDataBaseName, dataBaseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataBaseConn() {
		return dataBaseConn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataBaseConn(String newDataBaseConn) {
		String oldDataBaseConn = dataBaseConn;
		dataBaseConn = newDataBaseConn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN, oldDataBaseConn, dataBaseConn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBizPropertyConfig() {
		return bizPropertyConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBizPropertyConfig(String newBizPropertyConfig) {
		String oldBizPropertyConfig = bizPropertyConfig;
		bizPropertyConfig = newBizPropertyConfig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG, oldBizPropertyConfig, bizPropertyConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS:
				return ((InternalEList<?>)getDepends()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS:
				return getDepends();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID:
				return getSubSysID();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME:
				return getDataBaseName();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN:
				return getDataBaseConn();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG:
				return getBizPropertyConfig();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS:
				getDepends().clear();
				getDepends().addAll((Collection<? extends MoudleDepend>)newValue);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID:
				setSubSysID((String)newValue);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME:
				setDataBaseName((String)newValue);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN:
				setDataBaseConn((String)newValue);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG:
				setBizPropertyConfig((String)newValue);
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
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS:
				getDepends().clear();
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID:
				setSubSysID(SUB_SYS_ID_EDEFAULT);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME:
				setDataBaseName(DATA_BASE_NAME_EDEFAULT);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN:
				setDataBaseConn(DATA_BASE_CONN_EDEFAULT);
				return;
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG:
				setBizPropertyConfig(BIZ_PROPERTY_CONFIG_EDEFAULT);
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
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DEPENDS:
				return depends != null && !depends.isEmpty();
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__SUB_SYS_ID:
				return SUB_SYS_ID_EDEFAULT == null ? subSysID != null : !SUB_SYS_ID_EDEFAULT.equals(subSysID);
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_NAME:
				return DATA_BASE_NAME_EDEFAULT == null ? dataBaseName != null : !DATA_BASE_NAME_EDEFAULT.equals(dataBaseName);
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__DATA_BASE_CONN:
				return DATA_BASE_CONN_EDEFAULT == null ? dataBaseConn != null : !DATA_BASE_CONN_EDEFAULT.equals(dataBaseConn);
			case CresextendPackage.CRES_MOUDLE_EXTEND_PROPERTY__BIZ_PROPERTY_CONFIG:
				return BIZ_PROPERTY_CONFIG_EDEFAULT == null ? bizPropertyConfig != null : !BIZ_PROPERTY_CONFIG_EDEFAULT.equals(bizPropertyConfig);
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
		result.append(" (subSysID: ");
		result.append(subSysID);
		result.append(", dataBaseName: ");
		result.append(dataBaseName);
		result.append(", dataBaseConn: ");
		result.append(dataBaseConn);
		result.append(", bizPropertyConfig: ");
		result.append(bizPropertyConfig);
		result.append(')');
		return result.toString();
	}

} //CresMoudleExtendPropertyImpl
