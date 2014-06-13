/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Master Slave Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getMaster <em>Master</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getSlave <em>Slave</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getMasterSlaveTable()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='master slave'"
 * @generated
 */
public interface MasterSlaveTable extends PackageDefine {
	/**
	 * Returns the value of the '<em><b>Master</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master</em>' attribute.
	 * @see #setMaster(String)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getMasterSlaveTable_Master()
	 * @model
	 * @generated
	 */
	String getMaster();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getMaster <em>Master</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master</em>' attribute.
	 * @see #getMaster()
	 * @generated
	 */
	void setMaster(String value);

	/**
	 * Returns the value of the '<em><b>Slave</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slave</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slave</em>' attribute.
	 * @see #setSlave(String)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getMasterSlaveTable_Slave()
	 * @model
	 * @generated
	 */
	String getSlave();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable#getSlave <em>Slave</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slave</em>' attribute.
	 * @see #getSlave()
	 * @generated
	 */
	void setSlave(String value);

} // MasterSlaveTable
