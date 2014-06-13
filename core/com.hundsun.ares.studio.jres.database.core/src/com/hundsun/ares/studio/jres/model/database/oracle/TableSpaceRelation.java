/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Space Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getMainSpace <em>Main Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getIndexSpace <em>Index Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpaceRelation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='mainSpace indexSpace'"
 * @generated
 */
public interface TableSpaceRelation extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Main Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Main Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Main Space</em>' attribute.
	 * @see #setMainSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpaceRelation_MainSpace()
	 * @model default=""
	 * @generated
	 */
	String getMainSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getMainSpace <em>Main Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Main Space</em>' attribute.
	 * @see #getMainSpace()
	 * @generated
	 */
	void setMainSpace(String value);

	/**
	 * Returns the value of the '<em><b>Index Space</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Space</em>' attribute.
	 * @see #setIndexSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpaceRelation_IndexSpace()
	 * @model default=""
	 * @generated
	 */
	String getIndexSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation#getIndexSpace <em>Index Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Space</em>' attribute.
	 * @see #getIndexSpace()
	 * @generated
	 */
	void setIndexSpace(String value);

} // TableSpaceRelation
