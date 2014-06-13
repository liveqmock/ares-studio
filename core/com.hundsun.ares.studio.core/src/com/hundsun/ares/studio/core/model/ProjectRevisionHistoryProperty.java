/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project Revision History Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.ProjectRevisionHistoryProperty#getHistories <em>Histories</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getProjectRevisionHistoryProperty()
 * @model
 * @generated
 */
public interface ProjectRevisionHistoryProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Histories</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.core.model.RevisionHistory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Histories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Histories</em>' containment reference list.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getProjectRevisionHistoryProperty_Histories()
	 * @model containment="true"
	 * @generated
	 */
	EList<RevisionHistory> getHistories();

} // ProjectRevisionHistoryProperty
