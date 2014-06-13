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

import com.hundsun.ares.studio.flow.model.flow.Begin;
import com.hundsun.ares.studio.flow.model.flow.Connection;
import com.hundsun.ares.studio.flow.model.flow.End;
import com.hundsun.ares.studio.flow.model.flow.FlowModel;
import com.hundsun.ares.studio.flow.model.flow.FlowNode;
import com.hundsun.ares.studio.flow.model.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl#getEnds <em>Ends</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.flow.model.flow.impl.FlowModelImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FlowModelImpl<T extends FlowNode, K extends Begin, V extends End> extends EObjectImpl implements FlowModel<T, K, V> {
	/**
	 * The cached value of the '{@link #getBegin() <em>Begin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBegin()
	 * @generated
	 * @ordered
	 */
	protected K begin;

	/**
	 * The cached value of the '{@link #getEnds() <em>Ends</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnds()
	 * @generated
	 * @ordered
	 */
	protected EList<V> ends;

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<T> nodes;

	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FLOW_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public K getBegin() {
		return begin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBegin(K newBegin, NotificationChain msgs) {
		K oldBegin = begin;
		begin = newBegin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.FLOW_MODEL__BEGIN, oldBegin, newBegin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBegin(K newBegin) {
		if (newBegin != begin) {
			NotificationChain msgs = null;
			if (begin != null)
				msgs = ((InternalEObject)begin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FLOW_MODEL__BEGIN, null, msgs);
			if (newBegin != null)
				msgs = ((InternalEObject)newBegin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FLOW_MODEL__BEGIN, null, msgs);
			msgs = basicSetBegin(newBegin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FLOW_MODEL__BEGIN, newBegin, newBegin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<V> getEnds() {
		if (ends == null) {
			ends = new EObjectContainmentEList<V>(End.class, this, FlowPackage.FLOW_MODEL__ENDS);
		}
		return ends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<T>(FlowNode.class, this, FlowPackage.FLOW_MODEL__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connection> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentEList<Connection>(Connection.class, this, FlowPackage.FLOW_MODEL__CONNECTIONS);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.FLOW_MODEL__BEGIN:
				return basicSetBegin(null, msgs);
			case FlowPackage.FLOW_MODEL__ENDS:
				return ((InternalEList<?>)getEnds()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_MODEL__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_MODEL__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.FLOW_MODEL__BEGIN:
				return getBegin();
			case FlowPackage.FLOW_MODEL__ENDS:
				return getEnds();
			case FlowPackage.FLOW_MODEL__NODES:
				return getNodes();
			case FlowPackage.FLOW_MODEL__CONNECTIONS:
				return getConnections();
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
			case FlowPackage.FLOW_MODEL__BEGIN:
				setBegin((K)newValue);
				return;
			case FlowPackage.FLOW_MODEL__ENDS:
				getEnds().clear();
				getEnds().addAll((Collection<? extends V>)newValue);
				return;
			case FlowPackage.FLOW_MODEL__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends T>)newValue);
				return;
			case FlowPackage.FLOW_MODEL__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends Connection>)newValue);
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
			case FlowPackage.FLOW_MODEL__BEGIN:
				setBegin((K)null);
				return;
			case FlowPackage.FLOW_MODEL__ENDS:
				getEnds().clear();
				return;
			case FlowPackage.FLOW_MODEL__NODES:
				getNodes().clear();
				return;
			case FlowPackage.FLOW_MODEL__CONNECTIONS:
				getConnections().clear();
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
			case FlowPackage.FLOW_MODEL__BEGIN:
				return begin != null;
			case FlowPackage.FLOW_MODEL__ENDS:
				return ends != null && !ends.isEmpty();
			case FlowPackage.FLOW_MODEL__NODES:
				return nodes != null && !nodes.isEmpty();
			case FlowPackage.FLOW_MODEL__CONNECTIONS:
				return connections != null && !connections.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FlowModelImpl
