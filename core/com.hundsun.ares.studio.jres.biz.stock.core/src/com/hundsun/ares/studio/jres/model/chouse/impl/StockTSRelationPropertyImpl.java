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
import com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stock TS Relation Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl#getHisSpace <em>His Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.StockTSRelationPropertyImpl#getHisIndexSpace <em>His Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StockTSRelationPropertyImpl extends EObjectImpl implements StockTSRelationProperty {
	/**
	 * The default value of the '{@link #getHisSpace() <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HIS_SPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getHisSpace() <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisSpace()
	 * @generated
	 * @ordered
	 */
	protected String hisSpace = HIS_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHisIndexSpace() <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String HIS_INDEX_SPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getHisIndexSpace() <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHisIndexSpace()
	 * @generated
	 * @ordered
	 */
	protected String hisIndexSpace = HIS_INDEX_SPACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StockTSRelationPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.STOCK_TS_RELATION_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHisSpace() {
		return hisSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHisSpace(String newHisSpace) {
		String oldHisSpace = hisSpace;
		hisSpace = newHisSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_SPACE, oldHisSpace, hisSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHisIndexSpace() {
		return hisIndexSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHisIndexSpace(String newHisIndexSpace) {
		String oldHisIndexSpace = hisIndexSpace;
		hisIndexSpace = newHisIndexSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE, oldHisIndexSpace, hisIndexSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_SPACE:
				return getHisSpace();
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE:
				return getHisIndexSpace();
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
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_SPACE:
				setHisSpace((String)newValue);
				return;
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE:
				setHisIndexSpace((String)newValue);
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
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_SPACE:
				setHisSpace(HIS_SPACE_EDEFAULT);
				return;
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE:
				setHisIndexSpace(HIS_INDEX_SPACE_EDEFAULT);
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
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_SPACE:
				return HIS_SPACE_EDEFAULT == null ? hisSpace != null : !HIS_SPACE_EDEFAULT.equals(hisSpace);
			case ChousePackage.STOCK_TS_RELATION_PROPERTY__HIS_INDEX_SPACE:
				return HIS_INDEX_SPACE_EDEFAULT == null ? hisIndexSpace != null : !HIS_INDEX_SPACE_EDEFAULT.equals(hisIndexSpace);
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
		result.append(" (hisSpace: ");
		result.append(hisSpace);
		result.append(", hisIndexSpace: ");
		result.append(hisIndexSpace);
		result.append(')');
		return result.toString();
	}

} //StockTSRelationPropertyImpl
