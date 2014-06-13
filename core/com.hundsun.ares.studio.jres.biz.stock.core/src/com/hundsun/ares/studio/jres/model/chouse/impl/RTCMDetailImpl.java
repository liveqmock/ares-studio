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
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RTCM Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl#getNewName <em>New Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl#getOldName <em>Old Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.RTCMDetailImpl#getMark <em>Mark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RTCMDetailImpl extends ColumnChangeDetailImpl implements RTCMDetail {
	/**
	 * The default value of the '{@link #getNewName() <em>New Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewName()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNewName() <em>New Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewName()
	 * @generated
	 * @ordered
	 */
	protected String newName = NEW_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOldName() <em>Old Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldName()
	 * @generated
	 * @ordered
	 */
	protected static final String OLD_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getOldName() <em>Old Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldName()
	 * @generated
	 * @ordered
	 */
	protected String oldName = OLD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected static final String MARK_EDEFAULT = "";

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
	protected RTCMDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.RTCM_DETAIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewName(String newNewName) {
		String oldNewName = newName;
		newName = newNewName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.RTCM_DETAIL__NEW_NAME, oldNewName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getOldName() {
		// 2013.11.18 sundl 保存列模型的快照，列名从快照中获取。
		if (getSnapshot() != null)
			return getSnapshot().getName();
		return oldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setOldName(String newOldName) {
		// 2013.11.18 sundl 保存快照后，为兼容原来的资源或已存在的代码，修改这个函数，重定向到snapshot
		if (getSnapshot() != null) {
			getSnapshot().setName(newOldName);
			return;
		}
		
		// TODO 下面这部分代码可以考虑删除了，不应该会执行到这里
		String oldOldName = oldName;
		oldName = newOldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.RTCM_DETAIL__OLD_NAME, oldOldName, oldName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ChousePackage.RTCM_DETAIL__MARK, oldMark, mark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChousePackage.RTCM_DETAIL__NEW_NAME:
				return getNewName();
			case ChousePackage.RTCM_DETAIL__OLD_NAME:
				return getOldName();
			case ChousePackage.RTCM_DETAIL__MARK:
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
			case ChousePackage.RTCM_DETAIL__NEW_NAME:
				setNewName((String)newValue);
				return;
			case ChousePackage.RTCM_DETAIL__OLD_NAME:
				setOldName((String)newValue);
				return;
			case ChousePackage.RTCM_DETAIL__MARK:
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
			case ChousePackage.RTCM_DETAIL__NEW_NAME:
				setNewName(NEW_NAME_EDEFAULT);
				return;
			case ChousePackage.RTCM_DETAIL__OLD_NAME:
				setOldName(OLD_NAME_EDEFAULT);
				return;
			case ChousePackage.RTCM_DETAIL__MARK:
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
			case ChousePackage.RTCM_DETAIL__NEW_NAME:
				return NEW_NAME_EDEFAULT == null ? newName != null : !NEW_NAME_EDEFAULT.equals(newName);
			case ChousePackage.RTCM_DETAIL__OLD_NAME:
				return OLD_NAME_EDEFAULT == null ? oldName != null : !OLD_NAME_EDEFAULT.equals(oldName);
			case ChousePackage.RTCM_DETAIL__MARK:
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
		result.append(" (newName: ");
		result.append(newName);
		result.append(", oldName: ");
		result.append(oldName);
		result.append(", mark: ");
		result.append(mark);
		result.append(')');
		return result.toString();
	}

} //RTCMDetailImpl
