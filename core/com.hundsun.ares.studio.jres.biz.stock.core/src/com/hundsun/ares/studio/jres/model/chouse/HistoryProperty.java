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
 * A representation of the model object '<em><b>History Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.chouse.HistoryProperty#getInternalVersion <em>Internal Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getHistoryProperty()
 * @model
 * @generated
 */
public interface HistoryProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Internal Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Version</em>' attribute.
	 * @see #setInternalVersion(String)
	 * @see com.hundsun.ares.studio.jres.model.chouse.ChousePackage#getHistoryProperty_InternalVersion()
	 * @model default=""
	 * @generated
	 */
	String getInternalVersion();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.chouse.HistoryProperty#getInternalVersion <em>Internal Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Internal Version</em>' attribute.
	 * @see #getInternalVersion()
	 * @generated
	 */
	void setInternalVersion(String value);

} // HistoryProperty
