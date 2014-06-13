/**
 * 源程序名称：DictionaryItemImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dictionary Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getConstantName <em>Constant Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.DictionaryItemImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DictionaryItemImpl extends ExtensibleModelImpl implements DictionaryItem {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = "";

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
	 * The default value of the '{@link #getChineseName() <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChineseName()
	 * @generated
	 * @ordered
	 */
	protected static final String CHINESE_NAME_EDEFAULT = "";

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
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DictionaryItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.DICTIONARY_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DictionaryType getParent() {
		if (eContainerFeatureID() != MetadataPackage.DICTIONARY_ITEM__PARENT) return null;
		return (DictionaryType)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(DictionaryType newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, MetadataPackage.DICTIONARY_ITEM__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(DictionaryType newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != MetadataPackage.DICTIONARY_ITEM__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, MetadataPackage.DICTIONARY_TYPE__ITEMS, DictionaryType.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__PARENT, newParent, newParent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__VALUE, oldValue, value));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__CHINESE_NAME, oldChineseName, chineseName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__CONSTANT_NAME, oldConstantName, constantName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.DICTIONARY_ITEM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((DictionaryType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				return eInternalContainer().eInverseRemove(this, MetadataPackage.DICTIONARY_TYPE__ITEMS, DictionaryType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				return getParent();
			case MetadataPackage.DICTIONARY_ITEM__VALUE:
				return getValue();
			case MetadataPackage.DICTIONARY_ITEM__NAME:
				return getName();
			case MetadataPackage.DICTIONARY_ITEM__CHINESE_NAME:
				return getChineseName();
			case MetadataPackage.DICTIONARY_ITEM__CONSTANT_NAME:
				return getConstantName();
			case MetadataPackage.DICTIONARY_ITEM__DESCRIPTION:
				return getDescription();
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
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				setParent((DictionaryType)newValue);
				return;
			case MetadataPackage.DICTIONARY_ITEM__VALUE:
				setValue((String)newValue);
				return;
			case MetadataPackage.DICTIONARY_ITEM__NAME:
				setName((String)newValue);
				return;
			case MetadataPackage.DICTIONARY_ITEM__CHINESE_NAME:
				setChineseName((String)newValue);
				return;
			case MetadataPackage.DICTIONARY_ITEM__CONSTANT_NAME:
				setConstantName((String)newValue);
				return;
			case MetadataPackage.DICTIONARY_ITEM__DESCRIPTION:
				setDescription((String)newValue);
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
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				setParent((DictionaryType)null);
				return;
			case MetadataPackage.DICTIONARY_ITEM__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case MetadataPackage.DICTIONARY_ITEM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MetadataPackage.DICTIONARY_ITEM__CHINESE_NAME:
				setChineseName(CHINESE_NAME_EDEFAULT);
				return;
			case MetadataPackage.DICTIONARY_ITEM__CONSTANT_NAME:
				setConstantName(CONSTANT_NAME_EDEFAULT);
				return;
			case MetadataPackage.DICTIONARY_ITEM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case MetadataPackage.DICTIONARY_ITEM__PARENT:
				return getParent() != null;
			case MetadataPackage.DICTIONARY_ITEM__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case MetadataPackage.DICTIONARY_ITEM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MetadataPackage.DICTIONARY_ITEM__CHINESE_NAME:
				return CHINESE_NAME_EDEFAULT == null ? chineseName != null : !CHINESE_NAME_EDEFAULT.equals(chineseName);
			case MetadataPackage.DICTIONARY_ITEM__CONSTANT_NAME:
				return CONSTANT_NAME_EDEFAULT == null ? constantName != null : !CONSTANT_NAME_EDEFAULT.equals(constantName);
			case MetadataPackage.DICTIONARY_ITEM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
		result.append(" (value: ");
		result.append(value);
		result.append(", name: ");
		result.append(name);
		result.append(", chineseName: ");
		result.append(chineseName);
		result.append(", constantName: ");
		result.append(constantName);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //DictionaryItemImpl
