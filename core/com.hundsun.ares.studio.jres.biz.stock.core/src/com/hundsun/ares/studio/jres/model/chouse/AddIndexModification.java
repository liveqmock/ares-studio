/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.TableIndex;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Index Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddIndexModification#getIndexs <em>Indexs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddIndexModification()
 * @model
 * @generated
 */
public interface AddIndexModification extends Modification {
	/**
	 * Returns the value of the '<em><b>Indexs</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.TableIndex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexs</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddIndexModification_Indexs()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableIndex> getIndexs();

} // AddIndexModification
