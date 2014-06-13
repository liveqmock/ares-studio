/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getDecription <em>Decription</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#isEnable <em>Enable</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getPrivileges <em>Privileges</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl#getDefaultTableSpace <em>Default Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OracleUserImpl extends ExtensibleModelImpl implements OracleUser {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDecription() <em>Decription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecription()
	 * @generated
	 * @ordered
	 */
	protected static final String DECRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDecription() <em>Decription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecription()
	 * @generated
	 * @ordered
	 */
	protected String decription = DECRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttributes() <em>Attributes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTES_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected String attributes = ATTRIBUTES_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnable() <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnable() <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnable()
	 * @generated
	 * @ordered
	 */
	protected boolean enable = ENABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrivileges() <em>Privileges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivileges()
	 * @generated
	 * @ordered
	 */
	protected EList<OraclePrivilege> privileges;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultTableSpace() <em>Default Table Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTableSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_TABLE_SPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDefaultTableSpace() <em>Default Table Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTableSpace()
	 * @generated
	 * @ordered
	 */
	protected String defaultTableSpace = DEFAULT_TABLE_SPACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleUserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.ORACLE_USER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecription(String newDecription) {
		String oldDecription = decription;
		decription = newDecription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__DECRIPTION, oldDecription, decription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttributes() {
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributes(String newAttributes) {
		String oldAttributes = attributes;
		attributes = newAttributes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__ATTRIBUTES, oldAttributes, attributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnable(boolean newEnable) {
		boolean oldEnable = enable;
		enable = newEnable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__ENABLE, oldEnable, enable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OraclePrivilege> getPrivileges() {
		if (privileges == null) {
			privileges = new EObjectResolvingEList<OraclePrivilege>(OraclePrivilege.class, this, OraclePackage.ORACLE_USER__PRIVILEGES);
		}
		return privileges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultTableSpace() {
		return defaultTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultTableSpace(String newDefaultTableSpace) {
		String oldDefaultTableSpace = defaultTableSpace;
		defaultTableSpace = newDefaultTableSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_USER__DEFAULT_TABLE_SPACE, oldDefaultTableSpace, defaultTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OraclePackage.ORACLE_USER__NAME:
				return getName();
			case OraclePackage.ORACLE_USER__DECRIPTION:
				return getDecription();
			case OraclePackage.ORACLE_USER__ATTRIBUTES:
				return getAttributes();
			case OraclePackage.ORACLE_USER__ENABLE:
				return isEnable();
			case OraclePackage.ORACLE_USER__PRIVILEGES:
				return getPrivileges();
			case OraclePackage.ORACLE_USER__PASSWORD:
				return getPassword();
			case OraclePackage.ORACLE_USER__DEFAULT_TABLE_SPACE:
				return getDefaultTableSpace();
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
			case OraclePackage.ORACLE_USER__NAME:
				setName((String)newValue);
				return;
			case OraclePackage.ORACLE_USER__DECRIPTION:
				setDecription((String)newValue);
				return;
			case OraclePackage.ORACLE_USER__ATTRIBUTES:
				setAttributes((String)newValue);
				return;
			case OraclePackage.ORACLE_USER__ENABLE:
				setEnable((Boolean)newValue);
				return;
			case OraclePackage.ORACLE_USER__PRIVILEGES:
				getPrivileges().clear();
				getPrivileges().addAll((Collection<? extends OraclePrivilege>)newValue);
				return;
			case OraclePackage.ORACLE_USER__PASSWORD:
				setPassword((String)newValue);
				return;
			case OraclePackage.ORACLE_USER__DEFAULT_TABLE_SPACE:
				setDefaultTableSpace((String)newValue);
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
			case OraclePackage.ORACLE_USER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OraclePackage.ORACLE_USER__DECRIPTION:
				setDecription(DECRIPTION_EDEFAULT);
				return;
			case OraclePackage.ORACLE_USER__ATTRIBUTES:
				setAttributes(ATTRIBUTES_EDEFAULT);
				return;
			case OraclePackage.ORACLE_USER__ENABLE:
				setEnable(ENABLE_EDEFAULT);
				return;
			case OraclePackage.ORACLE_USER__PRIVILEGES:
				getPrivileges().clear();
				return;
			case OraclePackage.ORACLE_USER__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case OraclePackage.ORACLE_USER__DEFAULT_TABLE_SPACE:
				setDefaultTableSpace(DEFAULT_TABLE_SPACE_EDEFAULT);
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
			case OraclePackage.ORACLE_USER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OraclePackage.ORACLE_USER__DECRIPTION:
				return DECRIPTION_EDEFAULT == null ? decription != null : !DECRIPTION_EDEFAULT.equals(decription);
			case OraclePackage.ORACLE_USER__ATTRIBUTES:
				return ATTRIBUTES_EDEFAULT == null ? attributes != null : !ATTRIBUTES_EDEFAULT.equals(attributes);
			case OraclePackage.ORACLE_USER__ENABLE:
				return enable != ENABLE_EDEFAULT;
			case OraclePackage.ORACLE_USER__PRIVILEGES:
				return privileges != null && !privileges.isEmpty();
			case OraclePackage.ORACLE_USER__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case OraclePackage.ORACLE_USER__DEFAULT_TABLE_SPACE:
				return DEFAULT_TABLE_SPACE_EDEFAULT == null ? defaultTableSpace != null : !DEFAULT_TABLE_SPACE_EDEFAULT.equals(defaultTableSpace);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", decription: ");
		result.append(decription);
		result.append(", attributes: ");
		result.append(attributes);
		result.append(", enable: ");
		result.append(enable);
		result.append(", password: ");
		result.append(password);
		result.append(", defaultTableSpace: ");
		result.append(defaultTableSpace);
		result.append(')');
		return result.toString();
	}

} //OracleUserImpl
