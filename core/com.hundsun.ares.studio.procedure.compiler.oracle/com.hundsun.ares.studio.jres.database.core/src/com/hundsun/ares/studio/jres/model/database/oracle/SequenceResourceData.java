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
 * A representation of the model object '<em><b>Sequence Resource Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getTableName <em>Table Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getStart <em>Start</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getIncrement <em>Increment</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMinValue <em>Min Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMaxValue <em>Max Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isCycle <em>Cycle</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getCache <em>Cache</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isUseCache <em>Use Cache</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isIsHistory <em>Is History</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name objectId start increment minValue maxValue cycle cache'"
 * @generated
 */
public interface SequenceResourceData extends DatabaseResourceData {
	/**
	 * Returns the value of the '<em><b>Table Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Name</em>' attribute.
	 * @see #setTableName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_TableName()
	 * @model default=""
	 * @generated
	 */
	String getTableName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getTableName <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Name</em>' attribute.
	 * @see #getTableName()
	 * @generated
	 */
	void setTableName(String value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_Start()
	 * @model default="1"
	 * @generated
	 */
	String getStart();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(String value);

	/**
	 * Returns the value of the '<em><b>Increment</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increment</em>' attribute.
	 * @see #setIncrement(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_Increment()
	 * @model default="1"
	 * @generated
	 */
	String getIncrement();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getIncrement <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increment</em>' attribute.
	 * @see #getIncrement()
	 * @generated
	 */
	void setIncrement(String value);

	/**
	 * Returns the value of the '<em><b>Min Value</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Value</em>' attribute.
	 * @see #setMinValue(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_MinValue()
	 * @model default="1"
	 * @generated
	 */
	String getMinValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMinValue <em>Min Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Value</em>' attribute.
	 * @see #getMinValue()
	 * @generated
	 */
	void setMinValue(String value);

	/**
	 * Returns the value of the '<em><b>Max Value</b></em>' attribute.
	 * The default value is <code>"999999999999999999999999999"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Value</em>' attribute.
	 * @see #setMaxValue(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_MaxValue()
	 * @model default="999999999999999999999999999"
	 * @generated
	 */
	String getMaxValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getMaxValue <em>Max Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Value</em>' attribute.
	 * @see #getMaxValue()
	 * @generated
	 */
	void setMaxValue(String value);

	/**
	 * Returns the value of the '<em><b>Cycle</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cycle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cycle</em>' attribute.
	 * @see #setCycle(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_Cycle()
	 * @model default="true"
	 * @generated
	 */
	boolean isCycle();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isCycle <em>Cycle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cycle</em>' attribute.
	 * @see #isCycle()
	 * @generated
	 */
	void setCycle(boolean value);

	/**
	 * Returns the value of the '<em><b>Cache</b></em>' attribute.
	 * The default value is <code>"20"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache</em>' attribute.
	 * @see #setCache(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_Cache()
	 * @model default="20"
	 * @generated
	 */
	String getCache();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#getCache <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache</em>' attribute.
	 * @see #getCache()
	 * @generated
	 */
	void setCache(String value);

	/**
	 * Returns the value of the '<em><b>Use Cache</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Cache</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Cache</em>' attribute.
	 * @see #setUseCache(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_UseCache()
	 * @model default="true"
	 * @generated
	 */
	boolean isUseCache();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isUseCache <em>Use Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Cache</em>' attribute.
	 * @see #isUseCache()
	 * @generated
	 */
	void setUseCache(boolean value);

	/**
	 * Returns the value of the '<em><b>Is History</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is History</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is History</em>' attribute.
	 * @see #setIsHistory(boolean)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getSequenceResourceData_IsHistory()
	 * @model
	 * @generated
	 */
	boolean isIsHistory();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData#isIsHistory <em>Is History</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is History</em>' attribute.
	 * @see #isIsHistory()
	 * @generated
	 */
	void setIsHistory(boolean value);

} // SequenceResourceData
