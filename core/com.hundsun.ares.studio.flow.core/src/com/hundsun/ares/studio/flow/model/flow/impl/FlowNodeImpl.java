/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.flow.model.flow.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.flow.model.flow.FigureNode;
import com.hundsun.ares.studio.flow.model.flow.FlowNode;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowNodeImpl#getFigureNode <em>Figure Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FlowNodeImpl extends IViewProviderImpl implements FlowNode {
	/**
	 * The cached value of the '{@link #getFigureNode() <em>Figure Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFigureNode()
	 * @generated
	 * @ordered
	 */
	protected FigureNode figureNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FLOW_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FigureNode getFigureNode() {
		return figureNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFigureNode(FigureNode newFigureNode, NotificationChain msgs) {
		FigureNode oldFigureNode = figureNode;
		figureNode = newFigureNode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.FLOW_NODE__FIGURE_NODE, oldFigureNode, newFigureNode);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFigureNode(FigureNode newFigureNode) {
		if (newFigureNode != figureNode) {
			NotificationChain msgs = null;
			if (figureNode != null)
				msgs = ((InternalEObject)figureNode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FLOW_NODE__FIGURE_NODE, null, msgs);
			if (newFigureNode != null)
				msgs = ((InternalEObject)newFigureNode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FLOW_NODE__FIGURE_NODE, null, msgs);
			msgs = basicSetFigureNode(newFigureNode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FLOW_NODE__FIGURE_NODE, newFigureNode, newFigureNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.FLOW_NODE__FIGURE_NODE:
				return basicSetFigureNode(null, msgs);
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
			case FlowPackage.FLOW_NODE__FIGURE_NODE:
				return getFigureNode();
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
			case FlowPackage.FLOW_NODE__FIGURE_NODE:
				setFigureNode((FigureNode)newValue);
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
			case FlowPackage.FLOW_NODE__FIGURE_NODE:
				setFigureNode((FigureNode)null);
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
			case FlowPackage.FLOW_NODE__FIGURE_NODE:
				return figureNode != null;
		}
		return super.eIsSet(featureID);
	}

} //FlowNodeImpl
