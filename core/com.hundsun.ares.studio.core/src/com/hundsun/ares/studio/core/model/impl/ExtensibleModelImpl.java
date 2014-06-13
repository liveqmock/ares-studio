/**
 * 源程序名称：ExtensibleModelImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.util.EMFJSONUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extensible Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl#getData <em>Data</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.impl.ExtensibleModelImpl#getData2 <em>Data2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExtensibleModelImpl extends MinimalEObjectImpl.Container implements ExtensibleModel {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> data;

	/**
	 * The cached value of the '{@link #getData2() <em>Data2</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData2()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, EObject> data2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensibleModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.EXTENSIBLE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getData() {
		if (data == null) {
			data = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, CorePackage.EXTENSIBLE_MODEL__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, EObject> getData2() {
		if (data2 == null) {
			data2 = new EcoreEMap<String,EObject>(CorePackage.Literals.ESTRING_TO_EOBJECT_MAP_ENTRY, EStringToEObjectMapEntryImpl.class, this, CorePackage.EXTENSIBLE_MODEL__DATA2);
		}
		return data2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toJSON() {
		return EMFJSONUtil.write(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.EXTENSIBLE_MODEL__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case CorePackage.EXTENSIBLE_MODEL__DATA2:
				return ((InternalEList<?>)getData2()).basicRemove(otherEnd, msgs);
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
			case CorePackage.EXTENSIBLE_MODEL__DATA:
				if (coreType) return getData();
				else return getData().map();
			case CorePackage.EXTENSIBLE_MODEL__DATA2:
				if (coreType) return getData2();
				else return getData2().map();
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
			case CorePackage.EXTENSIBLE_MODEL__DATA:
				((EStructuralFeature.Setting)getData()).set(newValue);
				return;
			case CorePackage.EXTENSIBLE_MODEL__DATA2:
				((EStructuralFeature.Setting)getData2()).set(newValue);
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
			case CorePackage.EXTENSIBLE_MODEL__DATA:
				getData().clear();
				return;
			case CorePackage.EXTENSIBLE_MODEL__DATA2:
				getData2().clear();
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
			case CorePackage.EXTENSIBLE_MODEL__DATA:
				return data != null && !data.isEmpty();
			case CorePackage.EXTENSIBLE_MODEL__DATA2:
				return data2 != null && !data2.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExtensibleModelImpl
