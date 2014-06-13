/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.flow.model.flow.ColorView;
import com.hundsun.ares.studio.flow.model.flow.FigureNode;
import com.hundsun.ares.studio.flow.model.flow.FlowFactory;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Figure Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl#getColorView <em>Color View</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FigureNodeImpl#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FigureNodeImpl extends EObjectImpl implements FigureNode {
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColorView() <em>Color View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorView()
	 * @generated
	 * @ordered
	 */
	protected ColorView colorView;

	/**
	 * The default value of the '{@link #getBounds() <em>Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBounds()
	 * @generated
	 * @ordered
	 */
	protected static final Rectangle BOUNDS_EDEFAULT = (Rectangle)FlowFactory.eINSTANCE.createFromString(FlowPackage.eINSTANCE.getRectangle(), "0,0,0,0");

	/**
	 * The cached value of the '{@link #getBounds() <em>Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBounds()
	 * @generated
	 * @ordered
	 */
	protected Rectangle bounds = BOUNDS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FigureNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FIGURE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FIGURE_NODE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColorView getColorView() {
		return colorView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColorView(ColorView newColorView, NotificationChain msgs) {
		ColorView oldColorView = colorView;
		colorView = newColorView;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.FIGURE_NODE__COLOR_VIEW, oldColorView, newColorView);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorView(ColorView newColorView) {
		if (newColorView != colorView) {
			NotificationChain msgs = null;
			if (colorView != null)
				msgs = ((InternalEObject)colorView).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FIGURE_NODE__COLOR_VIEW, null, msgs);
			if (newColorView != null)
				msgs = ((InternalEObject)newColorView).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FIGURE_NODE__COLOR_VIEW, null, msgs);
			msgs = basicSetColorView(newColorView, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FIGURE_NODE__COLOR_VIEW, newColorView, newColorView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBounds(Rectangle newBounds) {
		Rectangle oldBounds = bounds;
		bounds = newBounds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FIGURE_NODE__BOUNDS, oldBounds, bounds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.FIGURE_NODE__COLOR_VIEW:
				return basicSetColorView(null, msgs);
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
			case FlowPackage.FIGURE_NODE__DESCRIPTION:
				return getDescription();
			case FlowPackage.FIGURE_NODE__COLOR_VIEW:
				return getColorView();
			case FlowPackage.FIGURE_NODE__BOUNDS:
				return getBounds();
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
			case FlowPackage.FIGURE_NODE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FlowPackage.FIGURE_NODE__COLOR_VIEW:
				setColorView((ColorView)newValue);
				return;
			case FlowPackage.FIGURE_NODE__BOUNDS:
				setBounds((Rectangle)newValue);
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
			case FlowPackage.FIGURE_NODE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FlowPackage.FIGURE_NODE__COLOR_VIEW:
				setColorView((ColorView)null);
				return;
			case FlowPackage.FIGURE_NODE__BOUNDS:
				setBounds(BOUNDS_EDEFAULT);
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
			case FlowPackage.FIGURE_NODE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FlowPackage.FIGURE_NODE__COLOR_VIEW:
				return colorView != null;
			case FlowPackage.FIGURE_NODE__BOUNDS:
				return BOUNDS_EDEFAULT == null ? bounds != null : !BOUNDS_EDEFAULT.equals(bounds);
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
		result.append(" (description: ");
		result.append(description);
		result.append(", bounds: ");
		result.append(bounds);
		result.append(')');
		return result.toString();
	}

} //FigureNodeImpl
