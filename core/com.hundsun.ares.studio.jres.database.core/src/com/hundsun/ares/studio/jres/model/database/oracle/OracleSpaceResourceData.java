/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData#getRelations <em>Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleSpaceResourceData()
 * @model
 * @generated
 */
public interface OracleSpaceResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Spaces</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spaces</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleSpaceResourceData_Spaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableSpace> getSpaces();

	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getOracleSpaceResourceData_Relations()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableSpaceRelation> getRelations();

} // OracleSpaceResourceData
