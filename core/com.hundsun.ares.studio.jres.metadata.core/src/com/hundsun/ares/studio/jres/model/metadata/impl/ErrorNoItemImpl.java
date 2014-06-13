/**
 * 源程序名称：ErrorNoItemImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error No Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl#getNo <em>No</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl#getConstantName <em>Constant Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoItemImpl#getLevel <em>Level</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ErrorNoItemImpl extends MetadataItemImpl implements ErrorNoItem {
	/**
	 * The default value of the '{@link #getNo() <em>No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNo()
	 * @generated
	 * @ordered
	 */
	protected static final String NO_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNo() <em>No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNo()
	 * @generated
	 * @ordered
	 */
	protected String no = NO_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected String message = MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstantName() <em>Constant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstantName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTANT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getConstantName() <em>Constant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstantName()
	 * @generated
	 * @ordered
	 */
	protected String constantName = CONSTANT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String LEVEL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected String level = LEVEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorNoItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.ERROR_NO_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNo() {
		return no;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNo(String newNo) {
		String oldNo = no;
		no = newNo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ERROR_NO_ITEM__NO, oldNo, no));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessage(String newMessage) {
		String oldMessage = message;
		message = newMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ERROR_NO_ITEM__MESSAGE, oldMessage, message));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstantName() {
		return constantName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstantName(String newConstantName) {
		String oldConstantName = constantName;
		constantName = newConstantName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ERROR_NO_ITEM__CONSTANT_NAME, oldConstantName, constantName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevel(String newLevel) {
		String oldLevel = level;
		level = newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ERROR_NO_ITEM__LEVEL, oldLevel, level));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.ERROR_NO_ITEM__NO:
				return getNo();
			case MetadataPackage.ERROR_NO_ITEM__MESSAGE:
				return getMessage();
			case MetadataPackage.ERROR_NO_ITEM__CONSTANT_NAME:
				return getConstantName();
			case MetadataPackage.ERROR_NO_ITEM__LEVEL:
				return getLevel();
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
			case MetadataPackage.ERROR_NO_ITEM__NO:
				setNo((String)newValue);
				return;
			case MetadataPackage.ERROR_NO_ITEM__MESSAGE:
				setMessage((String)newValue);
				return;
			case MetadataPackage.ERROR_NO_ITEM__CONSTANT_NAME:
				setConstantName((String)newValue);
				return;
			case MetadataPackage.ERROR_NO_ITEM__LEVEL:
				setLevel((String)newValue);
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
			case MetadataPackage.ERROR_NO_ITEM__NO:
				setNo(NO_EDEFAULT);
				return;
			case MetadataPackage.ERROR_NO_ITEM__MESSAGE:
				setMessage(MESSAGE_EDEFAULT);
				return;
			case MetadataPackage.ERROR_NO_ITEM__CONSTANT_NAME:
				setConstantName(CONSTANT_NAME_EDEFAULT);
				return;
			case MetadataPackage.ERROR_NO_ITEM__LEVEL:
				setLevel(LEVEL_EDEFAULT);
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
			case MetadataPackage.ERROR_NO_ITEM__NO:
				return NO_EDEFAULT == null ? no != null : !NO_EDEFAULT.equals(no);
			case MetadataPackage.ERROR_NO_ITEM__MESSAGE:
				return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
			case MetadataPackage.ERROR_NO_ITEM__CONSTANT_NAME:
				return CONSTANT_NAME_EDEFAULT == null ? constantName != null : !CONSTANT_NAME_EDEFAULT.equals(constantName);
			case MetadataPackage.ERROR_NO_ITEM__LEVEL:
				return LEVEL_EDEFAULT == null ? level != null : !LEVEL_EDEFAULT.equals(level);
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
		result.append(" (no: ");
		result.append(no);
		result.append(", message: ");
		result.append(message);
		result.append(", constantName: ");
		result.append(constantName);
		result.append(", level: ");
		result.append(level);
		result.append(')');
		return result.toString();
	}

} //ErrorNoItemImpl
