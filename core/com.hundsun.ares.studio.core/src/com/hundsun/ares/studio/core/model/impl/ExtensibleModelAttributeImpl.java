/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model.impl;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelAttribute;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extensible Model Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getLable <em>Lable</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelAttributeImpl#getValidate <em>Validate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensibleModelAttributeImpl extends MinimalEObjectImpl.Container implements ExtensibleModelAttribute {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLable() <em>Lable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLable()
	 * @generated
	 * @ordered
	 */
	protected static final String LABLE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLable() <em>Lable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLable()
	 * @generated
	 * @ordered
	 */
	protected String lable = LABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "";

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
	 * The default value of the '{@link #getValidate() <em>Validate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidate()
	 * @generated
	 * @ordered
	 */
	protected static final String VALIDATE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getValidate() <em>Validate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidate()
	 * @generated
	 * @ordered
	 */
	protected String validate = VALIDATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensibleModelAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.EXTENSIBLE_MODEL_ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLable() {
		return lable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLable(String newLable) {
		String oldLable = lable;
		lable = newLable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__LABLE, oldLable, lable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidate() {
		return validate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidate(String newValidate) {
		String oldValidate = validate;
		validate = newValidate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE, oldValidate, validate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__URI:
				return getUri();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME:
				return getClassName();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__KEY:
				return getKey();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__LABLE:
				return getLable();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__TYPE:
				return getType();
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE:
				return getValidate();
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
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__URI:
				setUri((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__KEY:
				setKey((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__LABLE:
				setLable((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__TYPE:
				setType((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE:
				setValidate((String)newValue);
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
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__URI:
				setUri(URI_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__LABLE:
				setLable(LABLE_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE:
				setValidate(VALIDATE_EDEFAULT);
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
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__LABLE:
				return LABLE_EDEFAULT == null ? lable != null : !LABLE_EDEFAULT.equals(lable);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case CorePackage.EXTENSIBLE_MODEL_ATTRIBUTE__VALIDATE:
				return VALIDATE_EDEFAULT == null ? validate != null : !VALIDATE_EDEFAULT.equals(validate);
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
		result.append(" (uri: ");
		result.append(uri);
		result.append(", className: ");
		result.append(className);
		result.append(", key: ");
		result.append(key);
		result.append(", lable: ");
		result.append(lable);
		result.append(", type: ");
		result.append(type);
		result.append(", validate: ");
		result.append(validate);
		result.append(')');
		return result.toString();
	}

} //ExtensibleModelAttributeImpl
