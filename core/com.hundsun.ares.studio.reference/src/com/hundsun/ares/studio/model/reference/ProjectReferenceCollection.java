/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project Reference Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ProjectReferenceCollection#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getProjectReferenceCollection()
 * @model
 * @generated
 */
public interface ProjectReferenceCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>References</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.model.reference.ReferenceInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' containment reference list.
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getProjectReferenceCollection_References()
	 * @model containment="true"
	 * @generated
	 */
	//EList<ReferenceInfo> getReferences();

	/**
	 * Returns the value of the '<em><b>References</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type list of {@link com.hundsun.ares.studio.model.reference.ReferenceInfo},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' map.
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getProjectReferenceCollection_References()
	 * @model mapType="com.hundsun.ares.studio.model.reference.ReferenceMapEntry<org.eclipse.emf.ecore.EString, com.hundsun.ares.studio.model.reference.ReferenceInfo>"
	 * @generated
	 */
	EMap<String, EList<ReferenceInfo>> getReferences();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<ReferenceInfo> getReferenceInfos(String refType, String refName, String refNamespace);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<ReferenceInfo> getReferenceInfos(String refType, String refName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<ReferenceInfo> getReferenceInfos(String refType);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model referenceInfosMany="true"
	 * @generated
	 */
	void updateOnlyResourceOnlyRefTypecache(String refType, EList<ReferenceInfo> referenceInfos);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<ReferenceInfo> getFirstReferenceInfos(String refType, String refName, String refNamespace);

} // ProjectReferenceCollection
