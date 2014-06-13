/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;

import com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Module Extensible Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getTableType <em>Table Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getSplitField <em>Split Field</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getSplitNum <em>Split Num</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.DatabaseModuleExtensiblePropertyImpl#getBizPkg <em>Biz Pkg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatabaseModuleExtensiblePropertyImpl extends ExtensibleModelImpl implements DatabaseModuleExtensibleProperty {
	/**
	 * The default value of the '{@link #getTableType() <em>Table Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableType()
	 * @generated
	 * @ordered
	 */
	protected static final table_type TABLE_TYPE_EDEFAULT = table_type.COMMON;

	/**
	 * The cached value of the '{@link #getTableType() <em>Table Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableType()
	 * @generated
	 * @ordered
	 */
	protected table_type tableType = TABLE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected String space = SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSplitField() <em>Split Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitField()
	 * @generated
	 * @ordered
	 */
	protected static final String SPLIT_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSplitField() <em>Split Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitField()
	 * @generated
	 * @ordered
	 */
	protected String splitField = SPLIT_FIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getSplitNum() <em>Split Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitNum()
	 * @generated
	 * @ordered
	 */
	protected static final String SPLIT_NUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSplitNum() <em>Split Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplitNum()
	 * @generated
	 * @ordered
	 */
	protected String splitNum = SPLIT_NUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final String START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected String startDate = START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBizPkg() <em>Biz Pkg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizPkg()
	 * @generated
	 * @ordered
	 */
	protected static final String BIZ_PKG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBizPkg() <em>Biz Pkg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBizPkg()
	 * @generated
	 * @ordered
	 */
	protected String bizPkg = BIZ_PKG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseModuleExtensiblePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public table_type getTableType() {
		return tableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableType(table_type newTableType) {
		table_type oldTableType = tableType;
		tableType = newTableType == null ? TABLE_TYPE_EDEFAULT : newTableType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE, oldTableType, tableType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpace() {
		return space;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpace(String newSpace) {
		String oldSpace = space;
		space = newSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE, oldSpace, space));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSplitField() {
		return splitField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplitField(String newSplitField) {
		String oldSplitField = splitField;
		splitField = newSplitField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD, oldSplitField, splitField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSplitNum() {
		return splitNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplitNum(String newSplitNum) {
		String oldSplitNum = splitNum;
		splitNum = newSplitNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM, oldSplitNum, splitNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(String newStartDate) {
		String oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE, oldStartDate, startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBizPkg() {
		return bizPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBizPkg(String newBizPkg) {
		String oldBizPkg = bizPkg;
		bizPkg = newBizPkg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG, oldBizPkg, bizPkg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE:
				return getTableType();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE:
				return getSpace();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD:
				return getSplitField();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM:
				return getSplitNum();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE:
				return getStartDate();
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG:
				return getBizPkg();
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
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE:
				setTableType((table_type)newValue);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE:
				setSpace((String)newValue);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD:
				setSplitField((String)newValue);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM:
				setSplitNum((String)newValue);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE:
				setStartDate((String)newValue);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG:
				setBizPkg((String)newValue);
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
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE:
				setTableType(TABLE_TYPE_EDEFAULT);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD:
				setSplitField(SPLIT_FIELD_EDEFAULT);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM:
				setSplitNum(SPLIT_NUM_EDEFAULT);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG:
				setBizPkg(BIZ_PKG_EDEFAULT);
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
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE:
				return tableType != TABLE_TYPE_EDEFAULT;
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD:
				return SPLIT_FIELD_EDEFAULT == null ? splitField != null : !SPLIT_FIELD_EDEFAULT.equals(splitField);
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM:
				return SPLIT_NUM_EDEFAULT == null ? splitNum != null : !SPLIT_NUM_EDEFAULT.equals(splitNum);
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
			case OraclePackage.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG:
				return BIZ_PKG_EDEFAULT == null ? bizPkg != null : !BIZ_PKG_EDEFAULT.equals(bizPkg);
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
		result.append(" (tableType: ");
		result.append(tableType);
		result.append(", space: ");
		result.append(space);
		result.append(", splitField: ");
		result.append(splitField);
		result.append(", splitNum: ");
		result.append(splitNum);
		result.append(", startDate: ");
		result.append(startDate);
		result.append(", bizPkg: ");
		result.append(bizPkg);
		result.append(')');
		return result.toString();
	}

} //DatabaseModuleExtensiblePropertyImpl
