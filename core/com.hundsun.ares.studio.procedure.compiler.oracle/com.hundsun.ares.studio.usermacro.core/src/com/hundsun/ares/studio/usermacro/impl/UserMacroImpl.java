/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.usermacro.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.UserMacroPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Macro</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.usermacro.impl.UserMacroImpl#getMacroItems <em>Macro Items</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserMacroImpl extends com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl implements UserMacro {
	/**
	 * The cached value of the '{@link #getMacroItems() <em>Macro Items</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMacroItems()
	 * @generated
	 * @ordered
	 */
	protected EList<UserMacroItem> macroItems;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserMacroImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UserMacroPackage.Literals.USER_MACRO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserMacroItem> getMacroItems() {
		if (macroItems == null) {
			macroItems = new EObjectContainmentEList<UserMacroItem>(UserMacroItem.class, this, UserMacroPackage.USER_MACRO__MACRO_ITEMS);
		}
		return macroItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UserMacroPackage.USER_MACRO__MACRO_ITEMS:
				return ((InternalEList<?>)getMacroItems()).basicRemove(otherEnd, msgs);
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
			case UserMacroPackage.USER_MACRO__MACRO_ITEMS:
				return getMacroItems();
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
			case UserMacroPackage.USER_MACRO__MACRO_ITEMS:
				getMacroItems().clear();
				getMacroItems().addAll((Collection<? extends UserMacroItem>)newValue);
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
			case UserMacroPackage.USER_MACRO__MACRO_ITEMS:
				getMacroItems().clear();
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
			case UserMacroPackage.USER_MACRO__MACRO_ITEMS:
				return macroItems != null && !macroItems.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UserMacroImpl
