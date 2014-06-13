/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model.impl;

import java.io.StringReader;
import java.util.Collection;

import org.dom4j.Document;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelAttribute;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.util.PersistentUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extensible Model Config Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl#getXml <em>Xml</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelConfigPropertyImpl#getXmlCache <em>Xml Cache</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensibleModelConfigPropertyImpl extends MinimalEObjectImpl.Container implements ExtensibleModelConfigProperty {
	/**
	 * The default value of the '{@link #getXml() <em>Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXml()
	 * @generated
	 * @ordered
	 */
	protected static final String XML_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getXml() <em>Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXml()
	 * @generated
	 * @ordered
	 */
	protected String xml = XML_EDEFAULT;
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensibleModelAttribute> attributes;

	/**
	 * The default value of the '{@link #getXmlCache() <em>Xml Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlCache()
	 * @generated
	 * @ordered
	 */
	protected static final Document XML_CACHE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getXmlCache() <em>Xml Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlCache()
	 * @generated
	 * @ordered
	 */
	protected Document xmlCache = XML_CACHE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensibleModelConfigPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.EXTENSIBLE_MODEL_CONFIG_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setXml(String newXml) {
		String oldXml = xml;
		xml = newXml;
		
		// 清空缓存
		xmlCache = null;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML, oldXml, xml));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtensibleModelAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<ExtensibleModelAttribute>(ExtensibleModelAttribute.class, this, CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Document getXmlCache() {
		if (xmlCache == null) {
			xmlCache = PersistentUtil.readDocument(new StringReader(xml));
		}
		return xmlCache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML:
				return getXml();
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES:
				return getAttributes();
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML_CACHE:
				return getXmlCache();
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
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML:
				setXml((String)newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends ExtensibleModelAttribute>)newValue);
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
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML:
				setXml(XML_EDEFAULT);
				return;
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES:
				getAttributes().clear();
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
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML:
				return XML_EDEFAULT == null ? xml != null : !XML_EDEFAULT.equals(xml);
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case CorePackage.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML_CACHE:
				return XML_CACHE_EDEFAULT == null ? xmlCache != null : !XML_CACHE_EDEFAULT.equals(xmlCache);
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
		result.append(" (xml: ");
		result.append(xml);
		result.append(", xmlCache: ");
		result.append(xmlCache);
		result.append(')');
		return result.toString();
	}

} //ExtensibleModelConfigPropertyImpl
