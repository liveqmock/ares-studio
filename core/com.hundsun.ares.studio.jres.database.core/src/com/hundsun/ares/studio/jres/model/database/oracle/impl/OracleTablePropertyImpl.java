/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleTablePropertyImpl#getTabletype <em>Tabletype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OracleTablePropertyImpl extends EObjectImpl implements OracleTableProperty {
	/**
	 * The default value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_EDEFAULT = "";

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
	 * The default value of the '{@link #getTabletype() <em>Tabletype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTabletype()
	 * @generated
	 * @ordered
	 */
	protected static final table_type TABLETYPE_EDEFAULT = table_type.COMMON;

	/**
	 * The cached value of the '{@link #getTabletype() <em>Tabletype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTabletype()
	 * @generated
	 * @ordered
	 */
	protected table_type tabletype = TABLETYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleTablePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.ORACLE_TABLE_PROPERTY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_TABLE_PROPERTY__SPACE, oldSpace, space));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public table_type getTabletype() {
		return tabletype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTabletype(table_type newTabletype) {
		table_type oldTabletype = tabletype;
		tabletype = newTabletype == null ? TABLETYPE_EDEFAULT : newTabletype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OraclePackage.ORACLE_TABLE_PROPERTY__TABLETYPE, oldTabletype, tabletype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OraclePackage.ORACLE_TABLE_PROPERTY__SPACE:
				return getSpace();
			case OraclePackage.ORACLE_TABLE_PROPERTY__TABLETYPE:
				return getTabletype();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OraclePackage.ORACLE_TABLE_PROPERTY__SPACE:
				setSpace((String)newValue);
				return;
			case OraclePackage.ORACLE_TABLE_PROPERTY__TABLETYPE:
				if (newValue instanceof table_type) {
					setTabletype((table_type)newValue);
					return;
				}else {
					setTabletype(table_type.get(Integer.parseInt(newValue.toString())));
					return;
				}
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
			case OraclePackage.ORACLE_TABLE_PROPERTY__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case OraclePackage.ORACLE_TABLE_PROPERTY__TABLETYPE:
				setTabletype(TABLETYPE_EDEFAULT);
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
			case OraclePackage.ORACLE_TABLE_PROPERTY__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case OraclePackage.ORACLE_TABLE_PROPERTY__TABLETYPE:
				return tabletype != TABLETYPE_EDEFAULT;
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
		result.append(" (space: ");
		result.append(space);
		result.append(", tabletype: ");
		result.append(tabletype);
		result.append(')');
		return result.toString();
	}

} //OracleTablePropertyImpl
