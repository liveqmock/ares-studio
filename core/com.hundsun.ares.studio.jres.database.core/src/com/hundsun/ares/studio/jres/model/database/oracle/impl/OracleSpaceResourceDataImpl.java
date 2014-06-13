/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.model.database.impl.DatabaseResourceDataImpl;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space Resource Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleSpaceResourceDataImpl#getRelations <em>Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OracleSpaceResourceDataImpl extends DatabaseResourceDataImpl implements OracleSpaceResourceData {
	/**
	 * The cached value of the '{@link #getSpaces() <em>Spaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaces()
	 * @generated
	 * @ordered
	 */
	protected EList<TableSpace> spaces;

	/**
	 * The cached value of the '{@link #getRelations() <em>Relations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations()
	 * @generated
	 * @ordered
	 */
	protected EList<TableSpaceRelation> relations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OracleSpaceResourceDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableSpace> getSpaces() {
		if (spaces == null) {
			spaces = new EObjectContainmentEList<TableSpace>(TableSpace.class, this, OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES);
		}
		return spaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableSpaceRelation> getRelations() {
		if (relations == null) {
			relations = new EObjectContainmentEList<TableSpaceRelation>(TableSpaceRelation.class, this, OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS);
		}
		return relations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES:
				return ((InternalEList<?>)getSpaces()).basicRemove(otherEnd, msgs);
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS:
				return ((InternalEList<?>)getRelations()).basicRemove(otherEnd, msgs);
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
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES:
				return getSpaces();
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS:
				return getRelations();
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
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES:
				getSpaces().clear();
				getSpaces().addAll((Collection<? extends TableSpace>)newValue);
				return;
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS:
				getRelations().clear();
				getRelations().addAll((Collection<? extends TableSpaceRelation>)newValue);
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
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES:
				getSpaces().clear();
				return;
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS:
				getRelations().clear();
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
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__SPACES:
				return spaces != null && !spaces.isEmpty();
			case OraclePackage.ORACLE_SPACE_RESOURCE_DATA__RELATIONS:
				return relations != null && !relations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO 表空间：1、引用Oracle用户  2、表空间名称
		
		EList<Reference> references=new BasicEList<Reference>();
		for(TableSpace data : getSpaces()){
			if(data.getUser() != null && StringUtils.isNotBlank(data.getUser())){
				Reference oracleUser = new TextAttributeReferenceWithNamespaceImpl(IOracleRefType.User, 
						data, 
						OraclePackage.Literals.TABLE_SPACE__USER);
				references.add(oracleUser);
			}
			if(data.getName() != null && StringUtils.isNotBlank(data.getName())){
				Reference tableSpace = new TextAttributeReferenceWithNamespaceImpl(IOracleRefType.Space, 
						data, 
						OraclePackage.Literals.TABLE_SPACE__NAME);
				references.add(tableSpace);
			}
		}
		
		return references;
	}

} //OracleSpaceResourceDataImpl
