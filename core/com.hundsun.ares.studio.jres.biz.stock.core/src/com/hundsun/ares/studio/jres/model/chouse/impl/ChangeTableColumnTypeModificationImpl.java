/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.chouse.reference.ChangeTableColumnTypeModificationReference;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change Table Column Type Modification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChangeTableColumnTypeModificationImpl extends ModificationImpl implements ChangeTableColumnTypeModification {
	/**
	 * The cached value of the '{@link #getDetails() <em>Details</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected EList<CTCTMDetail> details;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChangeTableColumnTypeModificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChousePackage.Literals.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CTCTMDetail> getDetails() {
		if (details == null) {
			details = new EObjectContainmentEList<CTCTMDetail>(CTCTMDetail.class, this, ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS);
		}
		return details;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.chouse.impl.ModificationImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		BasicEList<Reference> references = new BasicEList<Reference>();
		RevisionHistory  revisionHistory =  getRevisionHistoryImpl(this);
		String  version  = StringUtils.EMPTY;
		if(revisionHistory!=null){
			version = revisionHistory.getVersion();
		}
		//如果没有字段,或者版本信息为空则不用引用标准字段信息
		if(this.getDetails()!=null  &&  this.getDetails().size()>0 && StringUtils.isNotBlank(version)){
			ChangeTableColumnTypeModificationReference changeTableColumnTypeModificationReference = new ChangeTableColumnTypeModificationReference(IMetadataRefType.StdField,version,getDetails());
			references.add(changeTableColumnTypeModificationReference);
		}
		return references;
	}
	
	/**
	 * 获得eObject对应的RevisionHistory对象
	 * @param eObject
	 * @return
	 */
	private RevisionHistory getRevisionHistoryImpl(EObject eObject){
		if(eObject instanceof RevisionHistory) return (RevisionHistory)eObject;
		while(eObject!=null && eObject.eContainer()!=null){
			eObject = eObject.eContainer();
			if(eObject instanceof RevisionHistory){
				return (RevisionHistory)eObject;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS:
				return ((InternalEList<?>)getDetails()).basicRemove(otherEnd, msgs);
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
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS:
				return getDetails();
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
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection<? extends CTCTMDetail>)newValue);
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
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS:
				getDetails().clear();
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
			case ChousePackage.CHANGE_TABLE_COLUMN_TYPE_MODIFICATION__DETAILS:
				return details != null && !details.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ChangeTableColumnTypeModificationImpl
