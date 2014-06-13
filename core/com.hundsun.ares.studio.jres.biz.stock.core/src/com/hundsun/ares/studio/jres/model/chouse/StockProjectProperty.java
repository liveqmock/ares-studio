/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.chouse;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stock Project Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty#getBaseVersion <em>Base Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockProjectProperty()
 * @model
 * @generated
 */
public interface StockProjectProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Version</em>' attribute.
	 * @see #setBaseVersion(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockProjectProperty_BaseVersion()
	 * @model
	 * @generated
	 */
	String getBaseVersion();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockProjectProperty#getBaseVersion <em>Base Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Version</em>' attribute.
	 * @see #getBaseVersion()
	 * @generated
	 */
	void setBaseVersion(String value);

} // StockProjectProperty
