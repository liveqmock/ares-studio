/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.RelationTable#getProjects <em>Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationTable()
 * @model
 * @generated
 */
public interface RelationTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Projects</b></em>' map.
	 * The key is of type {@link com.hundsun.ares.studio.core.IARESProject},
	 * and the value is of type {@link com.hundsun.ares.studio.model.reference.ProjectRelationCollection},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projects</em>' map.
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationTable_Projects()
	 * @model mapType="com.hundsun.ares.studio.model.reference.ProjectToRelationsMapEntry<com.hundsun.ares.studio.model.reference.IARESProject, com.hundsun.ares.studio.model.reference.ProjectRelationCollection>"
	 * @generated
	 */
	EMap<IARESProject, ProjectRelationCollection> getProjects();

} // RelationTable
