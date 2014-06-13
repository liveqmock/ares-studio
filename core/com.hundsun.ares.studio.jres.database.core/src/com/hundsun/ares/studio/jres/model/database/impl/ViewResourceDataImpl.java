/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Resource Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl#getSql <em>Sql</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.ViewResourceDataImpl#isIsHistory <em>Is History</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewResourceDataImpl extends DatabaseResourceDataImpl implements ViewResourceData {
	/**
	 * The default value of the '{@link #getSql() <em>Sql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSql()
	 * @generated
	 * @ordered
	 */
	protected static final String SQL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getSql() <em>Sql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSql()
	 * @generated
	 * @ordered
	 */
	protected String sql = SQL_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsHistory() <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHistory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_HISTORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsHistory() <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHistory()
	 * @generated
	 * @ordered
	 */
	protected boolean isHistory = IS_HISTORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewResourceDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.VIEW_RESOURCE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSql(String newSql) {
		String oldSql = sql;
		sql = newSql;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.VIEW_RESOURCE_DATA__SQL, oldSql, sql));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsHistory() {
		return isHistory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsHistory(boolean newIsHistory) {
		boolean oldIsHistory = isHistory;
		isHistory = newIsHistory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.VIEW_RESOURCE_DATA__IS_HISTORY, oldIsHistory, isHistory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabasePackage.VIEW_RESOURCE_DATA__SQL:
				return getSql();
			case DatabasePackage.VIEW_RESOURCE_DATA__IS_HISTORY:
				return isIsHistory();
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
			case DatabasePackage.VIEW_RESOURCE_DATA__SQL:
				setSql((String)newValue);
				return;
			case DatabasePackage.VIEW_RESOURCE_DATA__IS_HISTORY:
				setIsHistory((Boolean)newValue);
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
			case DatabasePackage.VIEW_RESOURCE_DATA__SQL:
				setSql(SQL_EDEFAULT);
				return;
			case DatabasePackage.VIEW_RESOURCE_DATA__IS_HISTORY:
				setIsHistory(IS_HISTORY_EDEFAULT);
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
			case DatabasePackage.VIEW_RESOURCE_DATA__SQL:
				return SQL_EDEFAULT == null ? sql != null : !SQL_EDEFAULT.equals(sql);
			case DatabasePackage.VIEW_RESOURCE_DATA__IS_HISTORY:
				return isHistory != IS_HISTORY_EDEFAULT;
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
		result.append(" (sql: ");
		result.append(sql);
		result.append(", isHistory: ");
		result.append(isHistory);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO 扩展属性表空间信息引用  /////////////未添加#未添加#
		return super.getReferences();
	}

} //ViewResourceDataImpl
