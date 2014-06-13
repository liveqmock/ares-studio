/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.model.EMapImprove;
import com.hundsun.ares.studio.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMap Improve</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.impl.EMapImproveImpl#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EMapImproveImpl extends EObjectImpl implements EMapImprove {
	/**
	 * The cached value of the '{@link #getMap() <em>Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> map;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMapImproveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EMAP_IMPROVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getMap() {
		if (map == null) {
			map = new EcoreEMap<String,String>(ModelPackage.Literals.STRING_MAP_ENTRY, StringMapEntryImpl.class, this, ModelPackage.EMAP_IMPROVE__MAP);
		}
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.EMAP_IMPROVE__MAP:
				return ((InternalEList<?>)getMap()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.EMAP_IMPROVE__MAP:
				if (coreType) return getMap();
				else return getMap().map();
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
			case ModelPackage.EMAP_IMPROVE__MAP:
				((EStructuralFeature.Setting)getMap()).set(newValue);
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
			case ModelPackage.EMAP_IMPROVE__MAP:
				getMap().clear();
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
			case ModelPackage.EMAP_IMPROVE__MAP:
				return map != null && !map.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public void clear() {
		getMap().clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return getMap().contains(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return getMap().containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return getMap().entrySet();
	}

	public Object get(Object key) {
		// TODO Auto-generated method stub
		return getMap().get(key);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getMap().isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return getMap().keySet();
	}

	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		return getMap().put(key.toString(), value.toString());
	}

	public void putAll(Map m) {
		getMap().putAll(m);
	}

	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return getMap().remove(key);
	}

	public int size() {
		// TODO Auto-generated method stub
		return getMap().size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return getMap().values();
	}

} //EMapImproveImpl
