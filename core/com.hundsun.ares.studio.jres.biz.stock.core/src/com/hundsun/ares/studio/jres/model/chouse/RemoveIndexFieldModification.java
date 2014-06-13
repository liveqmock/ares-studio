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
 * A representation of the model object '<em><b>Remove Index Field Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification#getIndexs <em>Indexs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRemoveIndexFieldModification()
 * @model
 * @generated
 */
public interface RemoveIndexFieldModification extends Modification {

	/**
	 * Returns the value of the '<em><b>Indexs</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexs</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getRemoveIndexFieldModification_Indexs()
	 * @model containment="true"
	 * @generated
	 */
	EList<RemoveIndexField> getIndexs();
} // RemoveIndexFieldModification
