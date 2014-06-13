/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.model.EMapImprove;
import com.hundsun.ares.studio.model.ExtendFieldModel;
import com.hundsun.ares.studio.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extend Field Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.impl.ExtendFieldModelImpl#getExtendStrings <em>Extend Strings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendFieldModelImpl extends EObjectImpl implements ExtendFieldModel {
	/**
	 * The cached value of the '{@link #getExtendStrings() <em>Extend Strings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendStrings()
	 * @generated NOT
	 * @ordered
	 */
	protected EMapImprove extendStrings = new EMapImproveImpl();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendFieldModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EXTEND_FIELD_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMapImprove getExtendStrings() {
		return extendStrings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendStrings(EMapImprove newExtendStrings, NotificationChain msgs) {
		EMapImprove oldExtendStrings = extendStrings;
		extendStrings = newExtendStrings;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS, oldExtendStrings, newExtendStrings);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendStrings(EMapImprove newExtendStrings) {
		if (newExtendStrings != extendStrings) {
			NotificationChain msgs = null;
			if (extendStrings != null)
				msgs = ((InternalEObject)extendStrings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS, null, msgs);
			if (newExtendStrings != null)
				msgs = ((InternalEObject)newExtendStrings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS, null, msgs);
			msgs = basicSetExtendStrings(newExtendStrings, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS, newExtendStrings, newExtendStrings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS:
				return basicSetExtendStrings(null, msgs);
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
			case ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS:
				return getExtendStrings();
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
			case ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS:
				setExtendStrings((EMapImprove)newValue);
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
			case ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS:
				setExtendStrings((EMapImprove)null);
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
			case ModelPackage.EXTEND_FIELD_MODEL__EXTEND_STRINGS:
				return extendStrings != null;
		}
		return super.eIsSet(featureID);
	}

} //ExtendFieldModelImpl
