/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.swt.graphics.RGB;

import com.hundsun.ares.studio.flow.model.flow.ColorView;
import com.hundsun.ares.studio.flow.model.flow.FlowFactory;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Color View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl#getFrontground <em>Frontground</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ColorViewImpl#getBackground <em>Background</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColorViewImpl extends EObjectImpl implements ColorView {
	/**
	 * The default value of the '{@link #getFrontground() <em>Frontground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrontground()
	 * @generated
	 * @ordered
	 */
	protected static final RGB FRONTGROUND_EDEFAULT = (RGB)FlowFactory.eINSTANCE.createFromString(FlowPackage.eINSTANCE.getRGB(), "0,0,0");

	/**
	 * The cached value of the '{@link #getFrontground() <em>Frontground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrontground()
	 * @generated
	 * @ordered
	 */
	protected RGB frontground = FRONTGROUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected static final RGB BACKGROUND_EDEFAULT = (RGB)FlowFactory.eINSTANCE.createFromString(FlowPackage.eINSTANCE.getRGB(), "255,255,255");

	/**
	 * The cached value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected RGB background = BACKGROUND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColorViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.COLOR_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGB getFrontground() {
		return frontground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrontground(RGB newFrontground) {
		RGB oldFrontground = frontground;
		frontground = newFrontground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.COLOR_VIEW__FRONTGROUND, oldFrontground, frontground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGB getBackground() {
		return background;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackground(RGB newBackground) {
		RGB oldBackground = background;
		background = newBackground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.COLOR_VIEW__BACKGROUND, oldBackground, background));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FlowPackage.COLOR_VIEW__FRONTGROUND:
				return getFrontground();
			case FlowPackage.COLOR_VIEW__BACKGROUND:
				return getBackground();
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
			case FlowPackage.COLOR_VIEW__FRONTGROUND:
				setFrontground((RGB)newValue);
				return;
			case FlowPackage.COLOR_VIEW__BACKGROUND:
				setBackground((RGB)newValue);
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
			case FlowPackage.COLOR_VIEW__FRONTGROUND:
				setFrontground(FRONTGROUND_EDEFAULT);
				return;
			case FlowPackage.COLOR_VIEW__BACKGROUND:
				setBackground(BACKGROUND_EDEFAULT);
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
			case FlowPackage.COLOR_VIEW__FRONTGROUND:
				return FRONTGROUND_EDEFAULT == null ? frontground != null : !FRONTGROUND_EDEFAULT.equals(frontground);
			case FlowPackage.COLOR_VIEW__BACKGROUND:
				return BACKGROUND_EDEFAULT == null ? background != null : !BACKGROUND_EDEFAULT.equals(background);
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
		result.append(" (frontground: ");
		result.append(frontground);
		result.append(", background: ");
		result.append(background);
		result.append(')');
		return result.toString();
	}

} //ColorViewImpl
