/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Add Index Field Modification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl#getIndexs <em>Indexs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AddIndexFieldModificationImpl extends ModificationImpl implements AddIndexFieldModification {
	/**
	 * The cached value of the '{@link #getIndexs() <em>Indexs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexs()
	 * @generated
	 * @ordered
	 */
	protected EList<AddIndexField> indexs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddIndexFieldModificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.ADD_INDEX_FIELD_MODIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AddIndexField> getIndexs() {
		if (indexs == null) {
			indexs = new EObjectContainmentEList<AddIndexField>(AddIndexField.class, this, ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS);
		}
		return indexs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS:
				return ((InternalEList<?>)getIndexs()).basicRemove(otherEnd, msgs);
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
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS:
				return getIndexs();
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
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS:
				getIndexs().clear();
				getIndexs().addAll((Collection<? extends AddIndexField>)newValue);
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
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS:
				getIndexs().clear();
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
			case ChousePackage.ADD_INDEX_FIELD_MODIFICATION__INDEXS:
				return indexs != null && !indexs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AddIndexFieldModificationImpl
