/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData#getSql <em>Sql</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTriggerResourceData()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sql name'"
 * @generated
 */
public interface TriggerResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Sql</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql</em>' attribute.
	 * @see #setSql(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTriggerResourceData_Sql()
	 * @model default=""
	 * @generated
	 */
	String getSql();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData#getSql <em>Sql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql</em>' attribute.
	 * @see #getSql()
	 * @generated
	 */
	void setSql(String value);

} // TriggerResourceData
