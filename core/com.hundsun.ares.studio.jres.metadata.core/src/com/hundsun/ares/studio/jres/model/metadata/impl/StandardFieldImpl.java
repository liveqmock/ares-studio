/**
 * 源程序名称：StandardFieldImpl.java
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

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl#getLength <em>Length</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.StandardFieldImpl#getDictionaryType <em>Dictionary Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StandardFieldImpl extends MetadataItemImpl implements StandardField {
	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final String LENGTH_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected String length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final String PRECISION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected String precision = PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDictionaryType() <em>Dictionary Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDictionaryType()
	 * @generated
	 * @ordered
	 */
	protected static final String DICTIONARY_TYPE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDictionaryType() <em>Dictionary Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDictionaryType()
	 * @generated
	 * @ordered
	 */
	protected String dictionaryType = DICTIONARY_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.STANDARD_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(String newLength) {
		String oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.STANDARD_FIELD__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrecision() {
		return precision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecision(String newPrecision) {
		String oldPrecision = precision;
		precision = newPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.STANDARD_FIELD__PRECISION, oldPrecision, precision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.STANDARD_FIELD__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDictionaryType() {
		return dictionaryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDictionaryType(String newDictionaryType) {
		String oldDictionaryType = dictionaryType;
		dictionaryType = newDictionaryType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.STANDARD_FIELD__DICTIONARY_TYPE, oldDictionaryType, dictionaryType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.STANDARD_FIELD__LENGTH:
				return getLength();
			case MetadataPackage.STANDARD_FIELD__PRECISION:
				return getPrecision();
			case MetadataPackage.STANDARD_FIELD__DATA_TYPE:
				return getDataType();
			case MetadataPackage.STANDARD_FIELD__DICTIONARY_TYPE:
				return getDictionaryType();
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
			case MetadataPackage.STANDARD_FIELD__LENGTH:
				setLength((String)newValue);
				return;
			case MetadataPackage.STANDARD_FIELD__PRECISION:
				setPrecision((String)newValue);
				return;
			case MetadataPackage.STANDARD_FIELD__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case MetadataPackage.STANDARD_FIELD__DICTIONARY_TYPE:
				setDictionaryType((String)newValue);
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
			case MetadataPackage.STANDARD_FIELD__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case MetadataPackage.STANDARD_FIELD__PRECISION:
				setPrecision(PRECISION_EDEFAULT);
				return;
			case MetadataPackage.STANDARD_FIELD__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case MetadataPackage.STANDARD_FIELD__DICTIONARY_TYPE:
				setDictionaryType(DICTIONARY_TYPE_EDEFAULT);
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
			case MetadataPackage.STANDARD_FIELD__LENGTH:
				return LENGTH_EDEFAULT == null ? length != null : !LENGTH_EDEFAULT.equals(length);
			case MetadataPackage.STANDARD_FIELD__PRECISION:
				return PRECISION_EDEFAULT == null ? precision != null : !PRECISION_EDEFAULT.equals(precision);
			case MetadataPackage.STANDARD_FIELD__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case MetadataPackage.STANDARD_FIELD__DICTIONARY_TYPE:
				return DICTIONARY_TYPE_EDEFAULT == null ? dictionaryType != null : !DICTIONARY_TYPE_EDEFAULT.equals(dictionaryType);
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
		result.append(" (length: ");
		result.append(length);
		result.append(", precision: ");
		result.append(precision);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(", dictionaryType: ");
		result.append(dictionaryType);
		result.append(')');
		return result.toString();
	}

} //StandardFieldImpl
