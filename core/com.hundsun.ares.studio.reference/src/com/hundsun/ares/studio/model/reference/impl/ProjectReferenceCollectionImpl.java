/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.model.reference.ProjectReferenceCollection;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.ReferencePackage;
import com.hundsun.ares.studio.reference.ViewerUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project Reference Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.impl.ProjectReferenceCollectionImpl#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProjectReferenceCollectionImpl extends EObjectImpl implements ProjectReferenceCollection {
	//主要为了缓存项目中只有一个资源且只提供一种引用类型
	Map<String, Map<String,ReferenceInfo>> onlyResourceOnlyRefTypecache = new HashMap<String, Map<String,ReferenceInfo>>();
	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, EList<ReferenceInfo>> references;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProjectReferenceCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReferencePackage.Literals.PROJECT_REFERENCE_COLLECTION;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, EList<ReferenceInfo>> getReferences() {
		if (references == null) {
			references = new EcoreEMap<String,EList<ReferenceInfo>>(ReferencePackage.Literals.REFERENCE_MAP_ENTRY, ReferenceMapEntryImpl.class, this, ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ReferenceInfo> getReferenceInfos(String refType, String refName, String refNamespace) {
		
		EList<ReferenceInfo> result = new BasicEList<ReferenceInfo>();
		//如果是一个资源且只提供一种引用类型
		if(ViewerUtils.isOnlyResourceOnlyRefType(refType)){
			Map<String,ReferenceInfo> refTypeMap = onlyResourceOnlyRefTypecache.get(refType);
			if(refTypeMap!=null){
				ReferenceInfo info = refTypeMap.get(refName);
				if(info!=null && StringUtils.equals(refNamespace,info.getRefNamespace())){
					result.add(info);
					return result;
				}
			}
		}else{
			String[] resTypes = ViewerUtils.getResTypesCanSupport(refType);
			EMap<String, EList<ReferenceInfo>> refMap = getReferences();
			for (String resType : resTypes) {
				EList<ReferenceInfo> resReferenceInfoList = refMap.get(resType);
				if (resReferenceInfoList != null) {
					for (ReferenceInfo info : resReferenceInfoList) {
						if (StringUtils.equals(refName, info.getRefName())
								&& StringUtils.equals(refType, info.getRefType())
								&& StringUtils.equals(refNamespace,
										info.getRefNamespace())) {
							result.add(info);
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ReferenceInfo> getReferenceInfos(String refType, String refName) {
		EList<ReferenceInfo> result = new BasicEList<ReferenceInfo>();
		//如果是一个资源且只提供一种引用类型
		if(ViewerUtils.isOnlyResourceOnlyRefType(refType)){
			Map<String,ReferenceInfo> refTypeMap = onlyResourceOnlyRefTypecache.get(refType);
			if(refTypeMap!=null){
				ReferenceInfo info = refTypeMap.get(refName);
				if(info!=null){
					result.add(info);
					return result;
				}
			}
		}else{
			String[] resTypes = ViewerUtils.getResTypesCanSupport(refType);
			EMap<String, EList<ReferenceInfo>> refMap = getReferences();
			for (String resType : resTypes) {
				EList<ReferenceInfo> resReferenceInfoList = refMap.get(resType);
				if (resReferenceInfoList != null) {
					for (ReferenceInfo info : resReferenceInfoList) {
						if (StringUtils.equals(refName, info.getRefName()) 
								&& StringUtils.equals(refType, info.getRefType()) 
								) {
							result.add(info);
						}
					}
				}
			}
			
		}
		
		return result;
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ReferenceInfo> getReferenceInfos(String refType) {
		EList<ReferenceInfo> result = new BasicEList<ReferenceInfo>();
		String[] resTypes = ViewerUtils.getResTypesCanSupport(refType);
		EMap<String, EList<ReferenceInfo>> refMap = getReferences();
		//如果是一个资源且只提供一种引用类型
		if(ViewerUtils.isOnlyResourceOnlyRefType(refType) && resTypes.length==1 && refMap.get(resTypes[0])!=null){
			result.addAll(refMap.get(resTypes[0]));
			return result;
		}else{
			for(String resType:resTypes){
				EList<ReferenceInfo>  resReferenceInfoList = refMap.get(resType);
				if(resReferenceInfoList!=null){
					for (ReferenceInfo info : resReferenceInfoList) {
						if (StringUtils.equals(refType, info.getRefType())) {
							result.add(info);
						}
					}	
				}
				
			}
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void updateOnlyResourceOnlyRefTypecache(String refType, EList<ReferenceInfo> referenceInfos) {
		 Map<String,ReferenceInfo> refTypeMap = onlyResourceOnlyRefTypecache.get(refType);
		if(refTypeMap!=null){
			onlyResourceOnlyRefTypecache.get(refType).clear();
			for(ReferenceInfo info:referenceInfos){
				refTypeMap.put(info.getRefName(), info);
			}
		}else{
			refTypeMap = new HashMap<String,ReferenceInfo>();
			for(ReferenceInfo info:referenceInfos){
				refTypeMap.put(info.getRefName(), info);
			}
			onlyResourceOnlyRefTypecache.put(refType, refTypeMap);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ReferenceInfo> getFirstReferenceInfos(String refType, String refName, String refNamespace) {
		
		EList<ReferenceInfo> result = new BasicEList<ReferenceInfo>();
		//如果是一个资源且只提供一种引用类型
		if(ViewerUtils.isOnlyResourceOnlyRefType(refType)){
			Map<String,ReferenceInfo> refTypeMap = onlyResourceOnlyRefTypecache.get(refType);
			if(refTypeMap!=null){
				ReferenceInfo info = refTypeMap.get(refName);
				if(info!=null &&(StringUtils.isBlank(refNamespace)||StringUtils.equals(refNamespace,info.getRefNamespace()))){
					result.add(info);
					return result;
				}
			}
		}else{
			String[] resTypes = ViewerUtils.getResTypesCanSupport(refType);
			EMap<String, EList<ReferenceInfo>> refMap = getReferences();
			for (String resType : resTypes) {
				EList<ReferenceInfo> resReferenceInfoList = refMap.get(resType);
				if (resReferenceInfoList != null) {
					for (ReferenceInfo info : resReferenceInfoList) {
						if (StringUtils.equals(refName, info.getRefName())
								&& StringUtils.equals(refType, info.getRefType())
								&& (StringUtils.isBlank(refNamespace)||StringUtils.equals(refNamespace,info.getRefNamespace()))) {
							result.add(info);
							return result;
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
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
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES:
				if (coreType) return getReferences();
				else return getReferences().map();
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
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES:
				((EStructuralFeature.Setting)getReferences()).set(newValue);
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
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES:
				getReferences().clear();
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
			case ReferencePackage.PROJECT_REFERENCE_COLLECTION__REFERENCES:
				return references != null && !references.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProjectReferenceCollectionImpl
