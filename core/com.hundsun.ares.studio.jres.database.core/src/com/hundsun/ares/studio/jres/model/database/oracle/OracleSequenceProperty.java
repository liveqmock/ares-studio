/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty#getSpace <em>Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleSequenceProperty()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='space'"
 * @generated
 */
public interface OracleSequenceProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space</em>' attribute.
	 * @see #setSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleSequenceProperty_Space()
	 * @model default=""
	 * @generated
	 */
	String getSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSequenceProperty#getSpace <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space</em>' attribute.
	 * @see #getSpace()
	 * @generated
	 */
	void setSpace(String value);

} // OracleSequenceProperty
