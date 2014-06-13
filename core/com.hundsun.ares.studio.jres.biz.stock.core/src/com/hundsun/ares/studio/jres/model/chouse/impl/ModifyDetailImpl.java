/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modify Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ModifyDetailImpl#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModifyDetailImpl extends ColumnChangeDetailImpl implements ModifyDetail {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The default value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected static final String MARK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected String mark = MARK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModifyDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.MODIFY_DETAIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		// 2013.11.18 sundl 保存列模型的快照，列名从快照中获取。
		if (getSnapshot() != null)
			return getSnapshot().getName();
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		// 2013.11.18 sundl 保存快照后，为兼容原来的资源或已存在的代码，修改这个函数，重定向到snapshot
		if (getSnapshot() != null) {
			getSnapshot().setName(newName);
			return;
		}
		
		// TODO 下面这部分代码可以考虑删除了，不应该会执行到这里
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.MODIFY_DETAIL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getMark() {
		if (getSnapshot() != null)
			return getSnapshot().getMark();
		return mark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setMark(String newMark) {
		if (getSnapshot() != null) {
			getSnapshot().setMark(newMark);
			return;
		}
		String oldMark = mark;
		mark = newMark;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.MODIFY_DETAIL__MARK, oldMark, mark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.MODIFY_DETAIL__NAME:
				return getName();
			case ChousePackage.MODIFY_DETAIL__MARK:
				return getMark();
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
			case ChousePackage.MODIFY_DETAIL__NAME:
				setName((String)newValue);
				return;
			case ChousePackage.MODIFY_DETAIL__MARK:
				setMark((String)newValue);
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
			case ChousePackage.MODIFY_DETAIL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ChousePackage.MODIFY_DETAIL__MARK:
				setMark(MARK_EDEFAULT);
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
			case ChousePackage.MODIFY_DETAIL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ChousePackage.MODIFY_DETAIL__MARK:
				return MARK_EDEFAULT == null ? mark != null : !MARK_EDEFAULT.equals(mark);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", mark: ");
		result.append(mark);
		result.append(')');
		return result.toString();
	}

} //ModifyDetailImpl
