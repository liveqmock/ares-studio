/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>General Data Config Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.GeneralDataConfigItemImpl#getDiscription <em>Discription</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneralDataConfigItemImpl extends EObjectImpl implements GeneralDataConfigItem {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

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
	 * The default value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected static final String CHINESE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected String chineseName = CHINESE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDiscription() <em>Discription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscription()
	 * @generated
	 * @ordered
	 */
	protected static final String DISCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiscription() <em>Discription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscription()
	 * @generated
	 * @ordered
	 */
	protected String discription = DISCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralDataConfigItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.GENERAL_DATA_CONFIG_ITEM__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.GENERAL_DATA_CONFIG_ITEM__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.GENERAL_DATA_CONFIG_ITEM__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChineseName(String newChineseName) {
		String oldChineseName = chineseName;
		chineseName = newChineseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME, oldChineseName, chineseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDiscription() {
		return discription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiscription(String newDiscription) {
		String oldDiscription = discription;
		discription = newDiscription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION, oldDiscription, discription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__ID:
				return getId();
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__TYPE:
				return getType();
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__VALUE:
				return getValue();
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME:
				return getChineseName();
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION:
				return getDiscription();
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
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__ID:
				setId((String)newValue);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__TYPE:
				setType((String)newValue);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__VALUE:
				setValue((String)newValue);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION:
				setDiscription((String)newValue);
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
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__ID:
				setId(ID_EDEFAULT);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION:
				setDiscription(DISCRIPTION_EDEFAULT);
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
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case MetadataPackage.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION:
				return DISCRIPTION_EDEFAULT == null ? discription != null : !DISCRIPTION_EDEFAULT.equals(discription);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", type: ");
		result.append(type);
		result.append(", value: ");
		result.append(value);
		result.append(", chineseName: ");
		result.append(chineseName);
		result.append(", discription: ");
		result.append(discription);
		result.append(')');
		return result.toString();
	}

} //GeneralDataConfigItemImpl
