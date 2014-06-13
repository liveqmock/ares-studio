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
 * A representation of the model object '<em><b>Stock TS Relation Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisSpace <em>His Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisIndexSpace <em>His Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockTSRelationProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='hisSpace hisIndexSpace'"
 * @generated
 */
public interface StockTSRelationProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>His Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>His Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>His Space</em>' attribute.
	 * @see #setHisSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockTSRelationProperty_HisSpace()
	 * @model default=""
	 * @generated
	 */
	String getHisSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisSpace <em>His Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>His Space</em>' attribute.
	 * @see #getHisSpace()
	 * @generated
	 */
	void setHisSpace(String value);

	/**
	 * Returns the value of the '<em><b>His Index Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>His Index Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>His Index Space</em>' attribute.
	 * @see #setHisIndexSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockTSRelationProperty_HisIndexSpace()
	 * @model default=""
	 * @generated
	 */
	String getHisIndexSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockTSRelationProperty#getHisIndexSpace <em>His Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>His Index Space</em>' attribute.
	 * @see #getHisIndexSpace()
	 * @generated
	 */
	void setHisIndexSpace(String value);

} // StockTSRelationProperty
