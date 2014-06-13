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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CTCTM Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl#getNewType <em>New Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.CTCTMDetailImpl#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CTCTMDetailImpl extends ColumnChangeDetailImpl implements CTCTMDetail {
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
	 * The default value of the '{@link #getNewType() <em>New Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewType()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_TYPE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNewType() <em>New Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewType()
	 * @generated
	 * @ordered
	 */
	protected String newType = NEW_TYPE_EDEFAULT;

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
	protected CTCTMDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.CTCTM_DETAIL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CTCTM_DETAIL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNewType() {
		return newType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewType(String newNewType) {
		String oldNewType = newType;
		newType = newNewType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CTCTM_DETAIL__NEW_TYPE, oldNewType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getMark() {
		if (getSnapshot() != null) {
			return getSnapshot().getMark();
		}
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.CTCTM_DETAIL__MARK, oldMark, mark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.CTCTM_DETAIL__NAME:
				return getName();
			case ChousePackage.CTCTM_DETAIL__NEW_TYPE:
				return getNewType();
			case ChousePackage.CTCTM_DETAIL__MARK:
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
			case ChousePackage.CTCTM_DETAIL__NAME:
				setName((String)newValue);
				return;
			case ChousePackage.CTCTM_DETAIL__NEW_TYPE:
				setNewType((String)newValue);
				return;
			case ChousePackage.CTCTM_DETAIL__MARK:
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
			case ChousePackage.CTCTM_DETAIL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ChousePackage.CTCTM_DETAIL__NEW_TYPE:
				setNewType(NEW_TYPE_EDEFAULT);
				return;
			case ChousePackage.CTCTM_DETAIL__MARK:
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
			case ChousePackage.CTCTM_DETAIL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ChousePackage.CTCTM_DETAIL__NEW_TYPE:
				return NEW_TYPE_EDEFAULT == null ? newType != null : !NEW_TYPE_EDEFAULT.equals(newType);
			case ChousePackage.CTCTM_DETAIL__MARK:
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
		result.append(", newType: ");
		result.append(newType);
		result.append(", mark: ");
		result.append(mark);
		result.append(')');
		return result.toString();
	}

} //CTCTMDetailImpl
