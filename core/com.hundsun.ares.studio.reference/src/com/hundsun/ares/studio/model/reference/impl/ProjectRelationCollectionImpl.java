/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.IRelations;
import com.hundsun.ares.studio.model.reference.ProjectRelationCollection;
import com.hundsun.ares.studio.model.reference.ReferencePackage;
import com.hundsun.ares.studio.model.reference.RelationInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project Relation Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ProjectRelationCollectionImpl extends EObjectImpl implements ProjectRelationCollection {
	private IRelations relations = new RelationsImpl();
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProjectRelationCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReferencePackage.Literals.PROJECT_RELATION_COLLECTION;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<RelationInfo> getRelationInfos(String refType, String refName, String refNamespace) {
		return this.getRelations().getRelationOperator().getRelationInfos(refType, refName, refNamespace);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<RelationInfo> getRelationInfos(String refType, String refName) {
		return this.getRelations().getRelationOperator().getRelationInfos(refType, refName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IRelations getRelations() {
		return relations;
	}

} //ProjectRelationCollectionImpl
