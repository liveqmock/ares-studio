/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.flow.model.flow.Bendpoint;
import com.hundsun.ares.studio.flow.model.flow.FlowFactory;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl#getFirstRelative <em>First Relative</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.BendpointImpl#getSecondRelative <em>Second Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BendpointImpl extends EObjectImpl implements Bendpoint {
	/**
	 * The default value of the '{@link #getFirstRelative() <em>First Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstRelative()
	 * @generated
	 * @ordered
	 */
	protected static final Dimension FIRST_RELATIVE_EDEFAULT = (Dimension)FlowFactory.eINSTANCE.createFromString(FlowPackage.eINSTANCE.getDimension(), "0,0");

	/**
	 * The cached value of the '{@link #getFirstRelative() <em>First Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstRelative()
	 * @generated
	 * @ordered
	 */
	protected Dimension firstRelative = FIRST_RELATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecondRelative() <em>Second Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondRelative()
	 * @generated
	 * @ordered
	 */
	protected static final Dimension SECOND_RELATIVE_EDEFAULT = (Dimension)FlowFactory.eINSTANCE.createFromString(FlowPackage.eINSTANCE.getDimension(), "0,0");

	/**
	 * The cached value of the '{@link #getSecondRelative() <em>Second Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondRelative()
	 * @generated
	 * @ordered
	 */
	protected Dimension secondRelative = SECOND_RELATIVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BendpointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.BENDPOINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension getFirstRelative() {
		return firstRelative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstRelative(Dimension newFirstRelative) {
		Dimension oldFirstRelative = firstRelative;
		firstRelative = newFirstRelative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.BENDPOINT__FIRST_RELATIVE, oldFirstRelative, firstRelative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension getSecondRelative() {
		return secondRelative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecondRelative(Dimension newSecondRelative) {
		Dimension oldSecondRelative = secondRelative;
		secondRelative = newSecondRelative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.BENDPOINT__SECOND_RELATIVE, oldSecondRelative, secondRelative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FlowPackage.BENDPOINT__FIRST_RELATIVE:
				return getFirstRelative();
			case FlowPackage.BENDPOINT__SECOND_RELATIVE:
				return getSecondRelative();
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
			case FlowPackage.BENDPOINT__FIRST_RELATIVE:
				setFirstRelative((Dimension)newValue);
				return;
			case FlowPackage.BENDPOINT__SECOND_RELATIVE:
				setSecondRelative((Dimension)newValue);
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
			case FlowPackage.BENDPOINT__FIRST_RELATIVE:
				setFirstRelative(FIRST_RELATIVE_EDEFAULT);
				return;
			case FlowPackage.BENDPOINT__SECOND_RELATIVE:
				setSecondRelative(SECOND_RELATIVE_EDEFAULT);
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
			case FlowPackage.BENDPOINT__FIRST_RELATIVE:
				return FIRST_RELATIVE_EDEFAULT == null ? firstRelative != null : !FIRST_RELATIVE_EDEFAULT.equals(firstRelative);
			case FlowPackage.BENDPOINT__SECOND_RELATIVE:
				return SECOND_RELATIVE_EDEFAULT == null ? secondRelative != null : !SECOND_RELATIVE_EDEFAULT.equals(secondRelative);
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
		result.append(" (firstRelative: ");
		result.append(firstRelative);
		result.append(", secondRelative: ");
		result.append(secondRelative);
		result.append(')');
		return result.toString();
	}

} //BendpointImpl
