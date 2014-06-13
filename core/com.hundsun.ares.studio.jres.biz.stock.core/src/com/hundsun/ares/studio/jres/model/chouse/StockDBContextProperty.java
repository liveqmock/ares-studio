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
 * A representation of the model object '<em><b>Stock DB Context Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getStartVersion <em>Start Version</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getEndVersion <em>End Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockDBContextProperty()
 * @model
 * @generated
 */
public interface StockDBContextProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Start Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Version</em>' attribute.
	 * @see #setStartVersion(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockDBContextProperty_StartVersion()
	 * @model default=""
	 * @generated
	 */
	String getStartVersion();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getStartVersion <em>Start Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Version</em>' attribute.
	 * @see #getStartVersion()
	 * @generated
	 */
	void setStartVersion(String value);

	/**
	 * Returns the value of the '<em><b>End Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Version</em>' attribute.
	 * @see #setEndVersion(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getStockDBContextProperty_EndVersion()
	 * @model default=""
	 * @generated
	 */
	String getEndVersion();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.StockDBContextProperty#getEndVersion <em>End Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Version</em>' attribute.
	 * @see #getEndVersion()
	 * @generated
	 */
	void setEndVersion(String value);

} // StockDBContextProperty
