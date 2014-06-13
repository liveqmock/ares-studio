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

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Index Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl#isAscending <em>Ascending</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.impl.TableIndexColumnImpl#getColumnType <em>Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableIndexColumnImpl extends ExtensibleModelImpl implements TableIndexColumn {
	/**
	 * The default value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnName()
	 * @generated
	 * @ordered
	 */
	protected static final String COLUMN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnName()
	 * @generated
	 * @ordered
	 */
	protected String columnName = COLUMN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isAscending() <em>Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAscending()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASCENDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAscending() <em>Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAscending()
	 * @generated
	 * @ordered
	 */
	protected boolean ascending = ASCENDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnType()
	 * @generated
	 * @ordered
	 */
	protected static final ColumnType COLUMN_TYPE_EDEFAULT = ColumnType.STD_FIELD;

	/**
	 * The cached value of the '{@link #getColumnType() <em>Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnType()
	 * @generated
	 * @ordered
	 */
	protected ColumnType columnType = COLUMN_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableIndexColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatabasePackage.Literals.TABLE_INDEX_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnName(String newColumnName) {
		String oldColumnName = columnName;
		columnName = newColumnName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_NAME, oldColumnName, columnName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAscending() {
		return ascending;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAscending(boolean newAscending) {
		boolean oldAscending = ascending;
		ascending = newAscending;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX_COLUMN__ASCENDING, oldAscending, ascending));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnType getColumnType() {
		return columnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnType(ColumnType newColumnType) {
		ColumnType oldColumnType = columnType;
		columnType = newColumnType == null ? COLUMN_TYPE_EDEFAULT : newColumnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_TYPE, oldColumnType, columnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_NAME:
				return getColumnName();
			case DatabasePackage.TABLE_INDEX_COLUMN__ASCENDING:
				return isAscending();
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_TYPE:
				return getColumnType();
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
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_NAME:
				setColumnName((String)newValue);
				return;
			case DatabasePackage.TABLE_INDEX_COLUMN__ASCENDING:
				setAscending((Boolean)newValue);
				return;
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_TYPE:
				setColumnType((ColumnType)newValue);
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
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_NAME:
				setColumnName(COLUMN_NAME_EDEFAULT);
				return;
			case DatabasePackage.TABLE_INDEX_COLUMN__ASCENDING:
				setAscending(ASCENDING_EDEFAULT);
				return;
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_TYPE:
				setColumnType(COLUMN_TYPE_EDEFAULT);
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
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_NAME:
				return COLUMN_NAME_EDEFAULT == null ? columnName != null : !COLUMN_NAME_EDEFAULT.equals(columnName);
			case DatabasePackage.TABLE_INDEX_COLUMN__ASCENDING:
				return ascending != ASCENDING_EDEFAULT;
			case DatabasePackage.TABLE_INDEX_COLUMN__COLUMN_TYPE:
				return columnType != COLUMN_TYPE_EDEFAULT;
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
		result.append(" (columnName: ");
		result.append(columnName);
		result.append(", ascending: ");
		result.append(ascending);
		result.append(", columnType: ");
		result.append(columnType);
		result.append(')');
		return result.toString();
	}

} //TableIndexColumnImpl
