/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MD Module Common Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.impl.MDModuleCommonPropertyImpl#isUseRefFeature <em>Use Ref Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MDModuleCommonPropertyImpl extends EObjectImpl implements MDModuleCommonProperty {
	/**
	 * The default value of the '{@link #isUseRefFeature() <em>Use Ref Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseRefFeature()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_REF_FEATURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUseRefFeature() <em>Use Ref Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseRefFeature()
	 * @generated
	 * @ordered
	 */
	protected boolean useRefFeature = USE_REF_FEATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MDModuleCommonPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.MD_MODULE_COMMON_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUseRefFeature() {
		return useRefFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseRefFeature(boolean newUseRefFeature) {
		boolean oldUseRefFeature = useRefFeature;
		useRefFeature = newUseRefFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE, oldUseRefFeature, useRefFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE:
				return isUseRefFeature();
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
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE:
				setUseRefFeature((Boolean)newValue);
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
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE:
				setUseRefFeature(USE_REF_FEATURE_EDEFAULT);
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
			case MetadataPackage.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE:
				return useRefFeature != USE_REF_FEATURE_EDEFAULT;
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
		result.append(" (useRefFeature: ");
		result.append(useRefFeature);
		result.append(')');
		return result.toString();
	}

} //MDModuleCommonPropertyImpl
