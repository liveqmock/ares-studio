/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMap Improve</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.EMapImprove#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.ModelPackage#getEMapImprove()
 * @model superTypes="com.hundsun.ares.studio.model.Map"
 * @generated
 */
public interface EMapImprove extends EObject, Map {
	/**
	 * Returns the value of the '<em><b>Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map</em>' map.
	 * @see com.hundsun.ares.studio.model.ModelPackage#getEMapImprove_Map()
	 * @model mapType="com.hundsun.ares.studio.model.StringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getMap();

} // EMapImprove
