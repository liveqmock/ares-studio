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
 * A representation of the model object '<em><b>Database Module Extensible Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getTableType <em>Table Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSpace <em>Space</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitField <em>Split Field</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitNum <em>Split Num</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getBizPkg <em>Biz Pkg</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty()
 * @model
 * @generated
 */
public interface DatabaseModuleExtensibleProperty extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Table Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.jres.model.database.oracle.table_type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.table_type
	 * @see #setTableType(table_type)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_TableType()
	 * @model
	 * @generated
	 */
	table_type getTableType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getTableType <em>Table Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Type</em>' attribute.
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.table_type
	 * @see #getTableType()
	 * @generated
	 */
	void setTableType(table_type value);

	/**
	 * Returns the value of the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space</em>' attribute.
	 * @see #setSpace(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_Space()
	 * @model
	 * @generated
	 */
	String getSpace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSpace <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space</em>' attribute.
	 * @see #getSpace()
	 * @generated
	 */
	void setSpace(String value);

	/**
	 * Returns the value of the '<em><b>Split Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split Field</em>' attribute.
	 * @see #setSplitField(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_SplitField()
	 * @model
	 * @generated
	 */
	String getSplitField();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitField <em>Split Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split Field</em>' attribute.
	 * @see #getSplitField()
	 * @generated
	 */
	void setSplitField(String value);

	/**
	 * Returns the value of the '<em><b>Split Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split Num</em>' attribute.
	 * @see #setSplitNum(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_SplitNum()
	 * @model
	 * @generated
	 */
	String getSplitNum();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getSplitNum <em>Split Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split Num</em>' attribute.
	 * @see #getSplitNum()
	 * @generated
	 */
	void setSplitNum(String value);

	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_StartDate()
	 * @model
	 * @generated
	 */
	String getStartDate();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(String value);

	/**
	 * Returns the value of the '<em><b>Biz Pkg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Biz Pkg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Biz Pkg</em>' attribute.
	 * @see #setBizPkg(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getDatabaseModuleExtensibleProperty_BizPkg()
	 * @model
	 * @generated
	 */
	String getBizPkg();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty#getBizPkg <em>Biz Pkg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Biz Pkg</em>' attribute.
	 * @see #getBizPkg()
	 * @generated
	 */
	void setBizPkg(String value);

} // DatabaseModuleExtensibleProperty
