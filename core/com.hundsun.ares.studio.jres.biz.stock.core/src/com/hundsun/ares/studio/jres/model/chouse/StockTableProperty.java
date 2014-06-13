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
 * A representation of the model object '<em><b>Stock Table Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockTableProperty#isHistory <em>History</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockTableProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='hisSpace hisIndexSpace'"
 * @generated
 */
public interface StockTableProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>History</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>History</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>History</em>' attribute.
	 * @see #setHistory(boolean)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockTableProperty_History()
	 * @model default="false"
	 * @generated
	 */
	boolean isHistory();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockTableProperty#isHistory <em>History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>History</em>' attribute.
	 * @see #isHistory()
	 * @generated
	 */
	void setHistory(boolean value);

} // StockTableProperty
