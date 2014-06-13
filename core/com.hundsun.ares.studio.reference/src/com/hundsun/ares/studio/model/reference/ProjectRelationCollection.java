/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project Relation Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getProjectRelationCollection()
 * @model
 * @generated
 */
public interface ProjectRelationCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.model.reference.RelationInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getProjectRelationCollection_Relations()
	 * @model containment="true"
	 * @generated
	 */
	//EList<RelationInfo> getRelations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<RelationInfo> getRelationInfos(String refType, String refName, String refNamespace);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<RelationInfo> getRelationInfos(String refType, String refName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="com.hundsun.ares.studio.model.reference.Relations"
	 * @generated
	 */
	IRelations getRelations();

} // ProjectRelationCollection
