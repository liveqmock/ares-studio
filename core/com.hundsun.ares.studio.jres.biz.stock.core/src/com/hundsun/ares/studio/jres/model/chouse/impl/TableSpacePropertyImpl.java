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
import com.hundsun.ares.studio.jres.model.chouse.TableSpaceProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Space Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl#getReduTable <em>Redu Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl#getChearTable <em>Chear Table</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.TableSpacePropertyImpl#getChearTableIndex <em>Chear Table Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableSpacePropertyImpl extends EObjectImpl implements TableSpaceProperty {
	/**
	 * The default value of the '{@link #getReduTable() <em>Redu Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReduTable()
	 * @generated
	 * @ordered
	 */
	protected static final String REDU_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReduTable() <em>Redu Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReduTable()
	 * @generated
	 * @ordered
	 */
	protected String reduTable = REDU_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getChearTable() <em>Chear Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChearTable()
	 * @generated
	 * @ordered
	 */
	protected static final String CHEAR_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChearTable() <em>Chear Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChearTable()
	 * @generated
	 * @ordered
	 */
	protected String chearTable = CHEAR_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getChearTableIndex() <em>Chear Table Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChearTableIndex()
	 * @generated
	 * @ordered
	 */
	protected static final String CHEAR_TABLE_INDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChearTableIndex() <em>Chear Table Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChearTableIndex()
	 * @generated
	 * @ordered
	 */
	protected String chearTableIndex = CHEAR_TABLE_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableSpacePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.TABLE_SPACE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReduTable() {
		return reduTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReduTable(String newReduTable) {
		String oldReduTable = reduTable;
		reduTable = newReduTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_PROPERTY__REDU_TABLE, oldReduTable, reduTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChearTable() {
		return chearTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChearTable(String newChearTable) {
		String oldChearTable = chearTable;
		chearTable = newChearTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE, oldChearTable, chearTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChearTableIndex() {
		return chearTableIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChearTableIndex(String newChearTableIndex) {
		String oldChearTableIndex = chearTableIndex;
		chearTableIndex = newChearTableIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX, oldChearTableIndex, chearTableIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.TABLE_SPACE_PROPERTY__REDU_TABLE:
				return getReduTable();
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE:
				return getChearTable();
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX:
				return getChearTableIndex();
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
			case ChousePackage.TABLE_SPACE_PROPERTY__REDU_TABLE:
				setReduTable((String)newValue);
				return;
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE:
				setChearTable((String)newValue);
				return;
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX:
				setChearTableIndex((String)newValue);
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
			case ChousePackage.TABLE_SPACE_PROPERTY__REDU_TABLE:
				setReduTable(REDU_TABLE_EDEFAULT);
				return;
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE:
				setChearTable(CHEAR_TABLE_EDEFAULT);
				return;
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX:
				setChearTableIndex(CHEAR_TABLE_INDEX_EDEFAULT);
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
			case ChousePackage.TABLE_SPACE_PROPERTY__REDU_TABLE:
				return REDU_TABLE_EDEFAULT == null ? reduTable != null : !REDU_TABLE_EDEFAULT.equals(reduTable);
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE:
				return CHEAR_TABLE_EDEFAULT == null ? chearTable != null : !CHEAR_TABLE_EDEFAULT.equals(chearTable);
			case ChousePackage.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX:
				return CHEAR_TABLE_INDEX_EDEFAULT == null ? chearTableIndex != null : !CHEAR_TABLE_INDEX_EDEFAULT.equals(chearTableIndex);
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
		result.append(" (reduTable: ");
		result.append(reduTable);
		result.append(", chearTable: ");
		result.append(chearTable);
		result.append(", chearTableIndex: ");
		result.append(chearTableIndex);
		result.append(')');
		return result.toString();
	}

} //TableSpacePropertyImpl
