/**
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Constraint Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddConstraintModification()
 * @model
 * @generated
 */
public interface AddConstraintModification extends Modification {
	/**
	 * Returns the value of the '<em><b>Details</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Details</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Details</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getAddConstraintModification_Details()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstraintModifyDetail> getDetails();

} // AddConstraintModification
