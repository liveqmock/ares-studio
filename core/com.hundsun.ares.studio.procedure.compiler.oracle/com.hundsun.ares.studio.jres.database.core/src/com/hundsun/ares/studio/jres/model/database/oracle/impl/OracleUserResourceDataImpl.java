/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Resource Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserResourceDataImpl#getPrivileges <em>Privileges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OracleUserResourceDataImpl extends DatabaseResourceDataImpl implements OracleUserResourceData {
	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<OracleUser> users;

	/**
	 * The cached value of the '{@link #getPrivileges() <em>Privileges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivileges()
	 * @generated
	 * @ordered
	 */
	protected EList<OraclePrivilege> privileges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleUserResourceDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.ORACLE_USER_RESOURCE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OracleUser> getUsers() {
		if (users == null) {
			users = new EObjectContainmentEList<OracleUser>(OracleUser.class, this, OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OraclePrivilege> getPrivileges() {
		if (privileges == null) {
			privileges = new EObjectContainmentEList<OraclePrivilege>(OraclePrivilege.class, this, OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES);
		}
		return privileges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS:
				return ((InternalEList<?>)getUsers()).basicRemove(otherEnd, msgs);
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES:
				return ((InternalEList<?>)getPrivileges()).basicRemove(otherEnd, msgs);
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
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS:
				return getUsers();
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES:
				return getPrivileges();
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
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS:
				getUsers().clear();
				getUsers().addAll((Collection<? extends OracleUser>)newValue);
				return;
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES:
				getPrivileges().clear();
				getPrivileges().addAll((Collection<? extends OraclePrivilege>)newValue);
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
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS:
				getUsers().clear();
				return;
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES:
				getPrivileges().clear();
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
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__USERS:
				return users != null && !users.isEmpty();
			case OraclePackage.ORACLE_USER_RESOURCE_DATA__PRIVILEGES:
				return privileges != null && !privileges.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OracleUserResourceDataImpl
