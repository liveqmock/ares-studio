/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferencePackage;
import com.hundsun.ares.studio.model.reference.RelationInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl#getHostResource <em>Host Resource</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl#getUsedRefName <em>Used Ref Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl#getUsedRefNamespace <em>Used Ref Namespace</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.RelationInfoImpl#getUsedRefType <em>Used Ref Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationInfoImpl extends EObjectImpl implements RelationInfo {
	/**
	 * The default value of the '{@link #getHostResource() <em>Host Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostResource()
	 * @generated
	 * @ordered
	 */
	protected static final IARESResource HOST_RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHostResource() <em>Host Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostResource()
	 * @generated
	 * @ordered
	 */
	protected IARESResource hostResource = HOST_RESOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsedRefName() <em>Used Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String USED_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsedRefName() <em>Used Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefName()
	 * @generated
	 * @ordered
	 */
	protected String usedRefName = USED_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsedRefNamespace() <em>Used Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String USED_REF_NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsedRefNamespace() <em>Used Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefNamespace()
	 * @generated
	 * @ordered
	 */
	protected String usedRefNamespace = USED_REF_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsedRefType() <em>Used Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefType()
	 * @generated
	 * @ordered
	 */
	protected static final String USED_REF_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsedRefType() <em>Used Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedRefType()
	 * @generated
	 * @ordered
	 */
	protected String usedRefType = USED_REF_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReferencePackage.Literals.RELATION_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IARESResource getHostResource() {
		return hostResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostResource(IARESResource newHostResource) {
		IARESResource oldHostResource = hostResource;
		hostResource = newHostResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.RELATION_INFO__HOST_RESOURCE, oldHostResource, hostResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsedRefName() {
		return usedRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedRefName(String newUsedRefName) {
		String oldUsedRefName = usedRefName;
		usedRefName = newUsedRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.RELATION_INFO__USED_REF_NAME, oldUsedRefName, usedRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsedRefNamespace() {
		return usedRefNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedRefNamespace(String newUsedRefNamespace) {
		String oldUsedRefNamespace = usedRefNamespace;
		usedRefNamespace = newUsedRefNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.RELATION_INFO__USED_REF_NAMESPACE, oldUsedRefNamespace, usedRefNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsedRefType() {
		return usedRefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedRefType(String newUsedRefType) {
		String oldUsedRefType = usedRefType;
		usedRefType = newUsedRefType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.RELATION_INFO__USED_REF_TYPE, oldUsedRefType, usedRefType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReferencePackage.RELATION_INFO__HOST_RESOURCE:
				return getHostResource();
			case ReferencePackage.RELATION_INFO__USED_REF_NAME:
				return getUsedRefName();
			case ReferencePackage.RELATION_INFO__USED_REF_NAMESPACE:
				return getUsedRefNamespace();
			case ReferencePackage.RELATION_INFO__USED_REF_TYPE:
				return getUsedRefType();
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
			case ReferencePackage.RELATION_INFO__HOST_RESOURCE:
				setHostResource((IARESResource)newValue);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_NAME:
				setUsedRefName((String)newValue);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_NAMESPACE:
				setUsedRefNamespace((String)newValue);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_TYPE:
				setUsedRefType((String)newValue);
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
			case ReferencePackage.RELATION_INFO__HOST_RESOURCE:
				setHostResource(HOST_RESOURCE_EDEFAULT);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_NAME:
				setUsedRefName(USED_REF_NAME_EDEFAULT);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_NAMESPACE:
				setUsedRefNamespace(USED_REF_NAMESPACE_EDEFAULT);
				return;
			case ReferencePackage.RELATION_INFO__USED_REF_TYPE:
				setUsedRefType(USED_REF_TYPE_EDEFAULT);
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
			case ReferencePackage.RELATION_INFO__HOST_RESOURCE:
				return HOST_RESOURCE_EDEFAULT == null ? hostResource != null : !HOST_RESOURCE_EDEFAULT.equals(hostResource);
			case ReferencePackage.RELATION_INFO__USED_REF_NAME:
				return USED_REF_NAME_EDEFAULT == null ? usedRefName != null : !USED_REF_NAME_EDEFAULT.equals(usedRefName);
			case ReferencePackage.RELATION_INFO__USED_REF_NAMESPACE:
				return USED_REF_NAMESPACE_EDEFAULT == null ? usedRefNamespace != null : !USED_REF_NAMESPACE_EDEFAULT.equals(usedRefNamespace);
			case ReferencePackage.RELATION_INFO__USED_REF_TYPE:
				return USED_REF_TYPE_EDEFAULT == null ? usedRefType != null : !USED_REF_TYPE_EDEFAULT.equals(usedRefType);
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
		result.append(" (hostResource: ");
		result.append(hostResource);
		result.append(", usedRefName: ");
		result.append(usedRefName);
		result.append(", usedRefNamespace: ");
		result.append(usedRefNamespace);
		result.append(", usedRefType: ");
		result.append(usedRefType);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(hostResource).append(usedRefName).append(usedRefType).append(usedRefNamespace).toHashCode();
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
			RelationInfoImpl other = (RelationInfoImpl)obj;
			return ObjectUtils.equals(hostResource, other.hostResource) 
				&& ObjectUtils.equals(usedRefName, other.usedRefName)
				&& ObjectUtils.equals(usedRefType, other.usedRefType)
				&& ObjectUtils.equals(usedRefNamespace, other.usedRefNamespace);
			
		}
		return super.equals(obj);
	}
} //RelationInfoImpl
