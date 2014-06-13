/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB Module Common Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.DBModuleCommonPropertyImpl#getSupportDatabases <em>Support Databases</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DBModuleCommonPropertyImpl extends EObjectImpl implements DBModuleCommonProperty {
	/**
	 * The default value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_EDEFAULT = "Oracle";

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected String database = DATABASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSupportDatabases() <em>Support Databases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportDatabases()
	 * @generated
	 * @ordered
	 */
	protected static final String SUPPORT_DATABASES_EDEFAULT = "Oracle,DB2,MySQL,SQL Sever";

	/**
	 * The cached value of the '{@link #getSupportDatabases() <em>Support Databases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportDatabases()
	 * @generated
	 * @ordered
	 */
	protected String supportDatabases = SUPPORT_DATABASES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DBModuleCommonPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(String newDatabase) {
		String oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.DB_MODULE_COMMON_PROPERTY__DATABASE, oldDatabase, database));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSupportDatabases() {
		return supportDatabases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupportDatabases(String newSupportDatabases) {
		String oldSupportDatabases = supportDatabases;
		supportDatabases = newSupportDatabases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES, oldSupportDatabases, supportDatabases));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__DATABASE:
				return getDatabase();
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES:
				return getSupportDatabases();
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
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__DATABASE:
				setDatabase((String)newValue);
				return;
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES:
				setSupportDatabases((String)newValue);
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
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__DATABASE:
				setDatabase(DATABASE_EDEFAULT);
				return;
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES:
				setSupportDatabases(SUPPORT_DATABASES_EDEFAULT);
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
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__DATABASE:
				return DATABASE_EDEFAULT == null ? database != null : !DATABASE_EDEFAULT.equals(database);
			case DatabasePackage.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES:
				return SUPPORT_DATABASES_EDEFAULT == null ? supportDatabases != null : !SUPPORT_DATABASES_EDEFAULT.equals(supportDatabases);
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
		result.append(" (database: ");
		result.append(database);
		result.append(", supportDatabases: ");
		result.append(supportDatabases);
		result.append(')');
		return result.toString();
	}

} //DBModuleCommonPropertyImpl
