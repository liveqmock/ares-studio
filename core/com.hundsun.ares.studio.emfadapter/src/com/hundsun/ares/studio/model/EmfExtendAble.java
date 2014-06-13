/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Emf Extend Able</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.EmfExtendAble#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.ModelPackage#getEmfExtendAble()
 * @model abstract="true" superTypes="com.hundsun.ares.studio.model.IExtendAbleModel"
 * @generated
 */
public interface EmfExtendAble extends EObject, IExtendAbleModel {
	/**
	 * Returns the value of the '<em><b>Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map</em>' attribute.
	 * @see #setMap(Map)
	 * @see com.hundsun.ares.studio.model.ModelPackage#getEmfExtendAble_Map()
	 * @model
	 * @generated
	 */
	Map<String, Object> getMap();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.EmfExtendAble#getMap <em>Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map</em>' attribute.
	 * @see #getMap()
	 * @generated
	 */
	void setMap(Map<String, Object> value);

} // EmfExtendAble
