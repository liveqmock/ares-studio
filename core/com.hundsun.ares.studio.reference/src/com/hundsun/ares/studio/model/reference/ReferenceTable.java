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
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceTable#getProjects <em>Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceTable()
 * @model
 * @generated
 */
public interface ReferenceTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Projects</b></em>' map.
	 * The key is of type {@link com.hundsun.ares.studio.core.IARESProject},
	 * and the value is of type {@link com.hundsun.ares.studio.model.reference.ProjectReferenceCollection},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projects</em>' map.
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceTable_Projects()
	 * @model mapType="com.hundsun.ares.studio.model.reference.ProjectToReferencesMapEntry<com.hundsun.ares.studio.model.reference.IARESProject, com.hundsun.ares.studio.model.reference.ProjectReferenceCollection>"
	 * @generated
	 */
	EMap<IARESProject, ProjectReferenceCollection> getProjects();

} // ReferenceTable
