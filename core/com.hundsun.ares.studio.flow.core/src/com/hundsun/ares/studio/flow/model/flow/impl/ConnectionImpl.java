/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.flow.model.flow.Bendpoint;
import com.hundsun.ares.studio.flow.model.flow.ColorView;
import com.hundsun.ares.studio.flow.model.flow.Connection;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;
import com.hundsun.ares.studio.flow.model.flow.IViewProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl#getColorView <em>Color View</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.ConnectionImpl#getBendpoints <em>Bendpoints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionImpl extends EObjectImpl implements Connection {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected IViewProvider target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected IViewProvider source;

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
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Bendpoint> bendpoints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IViewProvider getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (IViewProvider)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FlowPackage.CONNECTION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IViewProvider basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(IViewProvider newTarget) {
		IViewProvider oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.CONNECTION__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IViewProvider getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (IViewProvider)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FlowPackage.CONNECTION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IViewProvider basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(IViewProvider newSource) {
		IViewProvider oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.CONNECTION__SOURCE, oldSource, source));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.CONNECTION__COLOR_VIEW, oldColorView, newColorView);
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
				msgs = ((InternalEObject)colorView).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.CONNECTION__COLOR_VIEW, null, msgs);
			if (newColorView != null)
				msgs = ((InternalEObject)newColorView).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.CONNECTION__COLOR_VIEW, null, msgs);
			msgs = basicSetColorView(newColorView, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.CONNECTION__COLOR_VIEW, newColorView, newColorView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.CONNECTION__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bendpoint> getBendpoints() {
		if (bendpoints == null) {
			bendpoints = new EObjectContainmentEList<Bendpoint>(Bendpoint.class, this, FlowPackage.CONNECTION__BENDPOINTS);
		}
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.CONNECTION__COLOR_VIEW:
				return basicSetColorView(null, msgs);
			case FlowPackage.CONNECTION__BENDPOINTS:
				return ((InternalEList<?>)getBendpoints()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.CONNECTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case FlowPackage.CONNECTION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case FlowPackage.CONNECTION__COLOR_VIEW:
				return getColorView();
			case FlowPackage.CONNECTION__VALUE:
				return getValue();
			case FlowPackage.CONNECTION__BENDPOINTS:
				return getBendpoints();
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
			case FlowPackage.CONNECTION__TARGET:
				setTarget((IViewProvider)newValue);
				return;
			case FlowPackage.CONNECTION__SOURCE:
				setSource((IViewProvider)newValue);
				return;
			case FlowPackage.CONNECTION__COLOR_VIEW:
				setColorView((ColorView)newValue);
				return;
			case FlowPackage.CONNECTION__VALUE:
				setValue((String)newValue);
				return;
			case FlowPackage.CONNECTION__BENDPOINTS:
				getBendpoints().clear();
				getBendpoints().addAll((Collection<? extends Bendpoint>)newValue);
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
			case FlowPackage.CONNECTION__TARGET:
				setTarget((IViewProvider)null);
				return;
			case FlowPackage.CONNECTION__SOURCE:
				setSource((IViewProvider)null);
				return;
			case FlowPackage.CONNECTION__COLOR_VIEW:
				setColorView((ColorView)null);
				return;
			case FlowPackage.CONNECTION__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case FlowPackage.CONNECTION__BENDPOINTS:
				getBendpoints().clear();
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
			case FlowPackage.CONNECTION__TARGET:
				return target != null;
			case FlowPackage.CONNECTION__SOURCE:
				return source != null;
			case FlowPackage.CONNECTION__COLOR_VIEW:
				return colorView != null;
			case FlowPackage.CONNECTION__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case FlowPackage.CONNECTION__BENDPOINTS:
				return bendpoints != null && !bendpoints.isEmpty();
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
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //ConnectionImpl
