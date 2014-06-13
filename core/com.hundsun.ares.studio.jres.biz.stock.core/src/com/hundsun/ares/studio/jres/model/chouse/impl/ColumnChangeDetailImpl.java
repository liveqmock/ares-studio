/**
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ColumnChangeDetail;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Change Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ColumnChangeDetailImpl#getSnapshot <em>Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnChangeDetailImpl extends EObjectImpl implements ColumnChangeDetail {
	/**
	 * The cached value of the '{@link #getSnapshot() <em>Snapshot</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnapshot()
	 * @generated
	 * @ordered
	 */
	protected TableColumn snapshot;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ColumnChangeDetailImpl() {
		super();
		setSnapshot(DatabaseFactory.eINSTANCE.createTableColumn());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.COLUMN_CHANGE_DETAIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableColumn getSnapshot() {
		return snapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSnapshot(TableColumn newSnapshot, NotificationChain msgs) {
		TableColumn oldSnapshot = snapshot;
		snapshot = newSnapshot;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT, oldSnapshot, newSnapshot);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSnapshot(TableColumn newSnapshot) {
		if (newSnapshot != snapshot) {
			NotificationChain msgs = null;
			if (snapshot != null)
				msgs = ((InternalEObject)snapshot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT, null, msgs);
			if (newSnapshot != null)
				msgs = ((InternalEObject)newSnapshot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT, null, msgs);
			msgs = basicSetSnapshot(newSnapshot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT, newSnapshot, newSnapshot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT:
				return basicSetSnapshot(null, msgs);
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
			case ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT:
				return getSnapshot();
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
			case ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT:
				setSnapshot((TableColumn)newValue);
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
			case ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT:
				setSnapshot((TableColumn)null);
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
			case ChousePackage.COLUMN_CHANGE_DETAIL__SNAPSHOT:
				return snapshot != null;
		}
		return super.eIsSet(featureID);
	}

} //ColumnChangeDetailImpl
