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
 * A representation of the model object '<em><b>Stock Index Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty#getFlag <em>Flag</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockIndexProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='flag'"
 * @generated
 */
public interface StockIndexProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Flag</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flag</em>' attribute.
	 * @see #setFlag(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockIndexProperty_Flag()
	 * @model default=""
	 * @generated
	 */
	String getFlag();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockIndexProperty#getFlag <em>Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flag</em>' attribute.
	 * @see #getFlag()
	 * @generated
	 */
	void setFlag(String value);

} // StockIndexProperty
