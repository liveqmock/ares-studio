/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend.impl;

import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Moudle Depend</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl#getModulePath <em>Module Path</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.impl.MoudleDependImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MoudleDependImpl extends EObjectImpl implements MoudleDepend {
	/**
	 * The default value of the '{@link #getModulePath() <em>Module Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModulePath()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_PATH_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getModulePath() <em>Module Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModulePath()
	 * @generated
	 * @ordered
	 */
	protected String modulePath = MODULE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MoudleDependImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CresextendPackage.Literals.MOUDLE_DEPEND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModulePath() {
		return modulePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModulePath(String newModulePath) {
		String oldModulePath = modulePath;
		modulePath = newModulePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.MOUDLE_DEPEND__MODULE_PATH, oldModulePath, modulePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CresextendPackage.MOUDLE_DEPEND__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CresextendPackage.MOUDLE_DEPEND__MODULE_PATH:
				return getModulePath();
			case CresextendPackage.MOUDLE_DEPEND__NAME:
				return getName();
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
			case CresextendPackage.MOUDLE_DEPEND__MODULE_PATH:
				setModulePath((String)newValue);
				return;
			case CresextendPackage.MOUDLE_DEPEND__NAME:
				setName((String)newValue);
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
			case CresextendPackage.MOUDLE_DEPEND__MODULE_PATH:
				setModulePath(MODULE_PATH_EDEFAULT);
				return;
			case CresextendPackage.MOUDLE_DEPEND__NAME:
				setName(NAME_EDEFAULT);
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
			case CresextendPackage.MOUDLE_DEPEND__MODULE_PATH:
				return MODULE_PATH_EDEFAULT == null ? modulePath != null : !MODULE_PATH_EDEFAULT.equals(modulePath);
			case CresextendPackage.MOUDLE_DEPEND__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (modulePath: ");
		result.append(modulePath);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //MoudleDependImpl
