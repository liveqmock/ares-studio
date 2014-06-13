/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remove Index Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification#getIndexs <em>Indexs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRemoveIndexModification()
 * @model
 * @generated
 */
public interface RemoveIndexModification extends Modification {
	/**
	 * Returns the value of the '<em><b>Indexs</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.chouse.RemovedIndex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexs</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRemoveIndexModification_Indexs()
	 * @model containment="true"
	 * @generated
	 */
	EList<RemovedIndex> getIndexs();

} // RemoveIndexModification
