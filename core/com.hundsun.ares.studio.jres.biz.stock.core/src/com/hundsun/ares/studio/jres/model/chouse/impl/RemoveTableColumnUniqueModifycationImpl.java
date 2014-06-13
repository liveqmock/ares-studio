/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Remove Table Column Unique Modifycation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RemoveTableColumnUniqueModifycationImpl#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RemoveTableColumnUniqueModifycationImpl extends ModificationImpl implements RemoveTableColumnUniqueModifycation {
	/**
	 * The cached value of the '{@link #getDetails() <em>Details</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected EList<ModifyDetail> details;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RemoveTableColumnUniqueModifycationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModifyDetail> getDetails() {
		if (details == null) {
			details = new EObjectContainmentEList<ModifyDetail>(ModifyDetail.class, this, ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS);
		}
		return details;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS:
				return ((InternalEList<?>)getDetails()).basicRemove(otherEnd, msgs);
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
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS:
				return getDetails();
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
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection<? extends ModifyDetail>)newValue);
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
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS:
				getDetails().clear();
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
			case ChousePackage.REMOVE_TABLE_COLUMN_UNIQUE_MODIFYCATION__DETAILS:
				return details != null && !details.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RemoveTableColumnUniqueModifycationImpl
