/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project Revision History Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ProjectRevisionHistoryPropertyImpl#getHistories <em>Histories</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProjectRevisionHistoryPropertyImpl extends MinimalEObjectImpl.Container implements ProjectRevisionHistoryProperty {
	/**
	 * The cached value of the '{@link #getHistories() <em>Histories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistories()
	 * @generated
	 * @ordered
	 */
	protected EList<RevisionHistory> histories;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProjectRevisionHistoryPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.PROJECT_REVISION_HISTORY_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RevisionHistory> getHistories() {
		if (histories == null) {
			histories = new EObjectContainmentEList<RevisionHistory>(RevisionHistory.class, this, CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES);
		}
		return histories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES:
				return ((InternalEList<?>)getHistories()).basicRemove(otherEnd, msgs);
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
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES:
				return getHistories();
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
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES:
				getHistories().clear();
				getHistories().addAll((Collection<? extends RevisionHistory>)newValue);
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
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES:
				getHistories().clear();
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
			case CorePackage.PROJECT_REVISION_HISTORY_PROPERTY__HISTORIES:
				return histories != null && !histories.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProjectRevisionHistoryPropertyImpl
