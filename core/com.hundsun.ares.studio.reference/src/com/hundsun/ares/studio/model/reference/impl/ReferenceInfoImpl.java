/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.ReferencePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl#getRefName <em>Ref Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl#getRefNamespace <em>Ref Namespace</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl#getRefType <em>Ref Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ReferenceInfoImpl#getObjectProvider <em>Object Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceInfoImpl extends EObjectImpl implements ReferenceInfo {
	
	private static final Logger logger = Logger.getLogger(ReferenceInfoImpl.class);
	
	/**
	 * The default value of the '{@link #getRefName() <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRefName() <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefName()
	 * @generated
	 * @ordered
	 */
	protected String refName = REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRefNamespace() <em>Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRefNamespace() <em>Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefNamespace()
	 * @generated
	 * @ordered
	 */
	protected String refNamespace = REF_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRefType() <em>Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefType()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRefType() <em>Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefType()
	 * @generated
	 * @ordered
	 */
	protected String refType = REF_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected static final IARESResource RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected IARESResource resource = RESOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getObjectProvider() <em>Object Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectProvider()
	 * @generated
	 * @ordered
	 */
	protected static final IObjectProvider OBJECT_PROVIDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getObjectProvider() <em>Object Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectProvider()
	 * @generated
	 * @ordered
	 */
	protected IObjectProvider objectProvider = OBJECT_PROVIDER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReferencePackage.Literals.REFERENCE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRefName() {
		return refName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefName(String newRefName) {
		String oldRefName = refName;
		refName = newRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_INFO__REF_NAME, oldRefName, refName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRefNamespace() {
		return refNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefNamespace(String newRefNamespace) {
		String oldRefNamespace = refNamespace;
		refNamespace = newRefNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_INFO__REF_NAMESPACE, oldRefNamespace, refNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRefType() {
		return refType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefType(String newRefType) {
		String oldRefType = refType;
		refType = newRefType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_INFO__REF_TYPE, oldRefType, refType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESResource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(IARESResource newResource) {
		IARESResource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_INFO__RESOURCE, oldResource, resource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IObjectProvider getObjectProvider() {
		return objectProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectProvider(IObjectProvider newObjectProvider) {
		IObjectProvider oldObjectProvider = objectProvider;
		objectProvider = newObjectProvider;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_INFO__OBJECT_PROVIDER, oldObjectProvider, objectProvider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object getObject() {
		if (getObjectProvider() != null) {
			return getObjectProvider().getObject(getResource());
		}
		logger.error(String.format("资源%s的信息读取失败，原因是objectProvider为空", getResource().getElementName()));
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReferencePackage.REFERENCE_INFO__REF_NAME:
				return getRefName();
			case ReferencePackage.REFERENCE_INFO__REF_NAMESPACE:
				return getRefNamespace();
			case ReferencePackage.REFERENCE_INFO__REF_TYPE:
				return getRefType();
			case ReferencePackage.REFERENCE_INFO__RESOURCE:
				return getResource();
			case ReferencePackage.REFERENCE_INFO__OBJECT_PROVIDER:
				return getObjectProvider();
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
			case ReferencePackage.REFERENCE_INFO__REF_NAME:
				setRefName((String)newValue);
				return;
			case ReferencePackage.REFERENCE_INFO__REF_NAMESPACE:
				setRefNamespace((String)newValue);
				return;
			case ReferencePackage.REFERENCE_INFO__REF_TYPE:
				setRefType((String)newValue);
				return;
			case ReferencePackage.REFERENCE_INFO__RESOURCE:
				setResource((IARESResource)newValue);
				return;
			case ReferencePackage.REFERENCE_INFO__OBJECT_PROVIDER:
				setObjectProvider((IObjectProvider)newValue);
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
			case ReferencePackage.REFERENCE_INFO__REF_NAME:
				setRefName(REF_NAME_EDEFAULT);
				return;
			case ReferencePackage.REFERENCE_INFO__REF_NAMESPACE:
				setRefNamespace(REF_NAMESPACE_EDEFAULT);
				return;
			case ReferencePackage.REFERENCE_INFO__REF_TYPE:
				setRefType(REF_TYPE_EDEFAULT);
				return;
			case ReferencePackage.REFERENCE_INFO__RESOURCE:
				setResource(RESOURCE_EDEFAULT);
				return;
			case ReferencePackage.REFERENCE_INFO__OBJECT_PROVIDER:
				setObjectProvider(OBJECT_PROVIDER_EDEFAULT);
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
			case ReferencePackage.REFERENCE_INFO__REF_NAME:
				return REF_NAME_EDEFAULT == null ? refName != null : !REF_NAME_EDEFAULT.equals(refName);
			case ReferencePackage.REFERENCE_INFO__REF_NAMESPACE:
				return REF_NAMESPACE_EDEFAULT == null ? refNamespace != null : !REF_NAMESPACE_EDEFAULT.equals(refNamespace);
			case ReferencePackage.REFERENCE_INFO__REF_TYPE:
				return REF_TYPE_EDEFAULT == null ? refType != null : !REF_TYPE_EDEFAULT.equals(refType);
			case ReferencePackage.REFERENCE_INFO__RESOURCE:
				return RESOURCE_EDEFAULT == null ? resource != null : !RESOURCE_EDEFAULT.equals(resource);
			case ReferencePackage.REFERENCE_INFO__OBJECT_PROVIDER:
				return OBJECT_PROVIDER_EDEFAULT == null ? objectProvider != null : !OBJECT_PROVIDER_EDEFAULT.equals(objectProvider);
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj != null && obj.getClass().equals(getClass())) {
			ReferenceInfoImpl other = (ReferenceInfoImpl)obj;
			return ObjectUtils.equals(refName, other.refName) 
				&& ObjectUtils.equals(refType, other.refType)
				&& ObjectUtils.equals(refNamespace, other.refNamespace)
				&& ObjectUtils.equals(resource, other.resource)
				&& ObjectUtils.equals(objectProvider, other.objectProvider);
			
		}
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(refName).append(refType).append(refNamespace).append(resource).append(objectProvider).toHashCode();
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
		result.append(" (refName: ");
		result.append(refName);
		result.append(", refNamespace: ");
		result.append(refNamespace);
		result.append(", refType: ");
		result.append(refType);
		result.append(", resource: ");
		result.append(resource);
		result.append(", objectProvider: ");
		result.append(objectProvider);
		result.append(')');
		return result.toString();
	}

} //ReferenceInfoImpl
