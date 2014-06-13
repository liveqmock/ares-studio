/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.extendable.IExtendFieldModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extend Field Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.ExtendFieldModel#getExtendStrings <em>Extend Strings</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.ModelPackage#getExtendFieldModel()
 * @model superTypes="com.hundsun.ares.studio.model.IExtendFieldModel"
 * @generated
 */
public interface ExtendFieldModel extends EObject, IExtendFieldModel {
	/**
	 * Returns the value of the '<em><b>Extend Strings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extend Strings</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extend Strings</em>' containment reference.
	 * @see #setExtendStrings(EMapImprove)
	 * @see com.hundsun.ares.studio.model.ModelPackage#getExtendFieldModel_ExtendStrings()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EMapImprove getExtendStrings();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.ExtendFieldModel#getExtendStrings <em>Extend Strings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extend Strings</em>' containment reference.
	 * @see #getExtendStrings()
	 * @generated
	 */
	void setExtendStrings(EMapImprove value);

} // ExtendFieldModel
