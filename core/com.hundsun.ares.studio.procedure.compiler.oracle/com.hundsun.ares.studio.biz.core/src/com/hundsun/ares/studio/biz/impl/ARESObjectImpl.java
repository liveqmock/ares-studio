/**
 */
package com.hundsun.ares.studio.biz.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ARES Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.impl.ARESObjectImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ARESObjectImpl extends JRESResourceInfoImpl implements ARESObject {
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ARESObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BizPackage.Literals.ARES_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Parameter>(Parameter.class, this, BizPackage.ARES_OBJECT__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BizPackage.ARES_OBJECT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case BizPackage.ARES_OBJECT__PROPERTIES:
				return getProperties();
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
			case BizPackage.ARES_OBJECT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Parameter>)newValue);
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
			case BizPackage.ARES_OBJECT__PROPERTIES:
				getProperties().clear();
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
			case BizPackage.ARES_OBJECT__PROPERTIES:
				return properties != null && !properties.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		for(Parameter parameter:getProperties()){
			Reference ref = null;
			// 根据参数类型的不同，引用的类型也不一样
			switch (parameter.getParamType()) {
				case STD_FIELD:
					if (!StringUtils.isEmpty(parameter.getId()))
						ref = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, parameter, BizPackage.Literals.PARAMETER__ID);
					break;
				case OBJECT:
					if (!StringUtils.isEmpty(parameter.getType()))
						ref = new TextAttributeReferenceImpl(IBizRefType.Object, parameter, BizPackage.Literals.PARAMETER__TYPE);
					break;
				case PARAM_GROUP:
					if (!StringUtils.isEmpty(parameter.getType()))
						ref = new TextAttributeReferenceImpl(IBizRefType.Object, parameter, BizPackage.Literals.PARAMETER__TYPE);
					break;
				default:
					break;
			}
			
			if (ref != null)
				references.add(ref);
		}
		return references;
	}

} //ARESObjectImpl
