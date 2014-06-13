/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Master Slave Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl#getMaster <em>Master</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveTableImpl#getSlave <em>Slave</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MasterSlaveTableImpl extends PackageDefineImpl implements MasterSlaveTable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterSlaveTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.MASTER_SLAVE_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaster() {
		return (String)eGet(BasicdataPackage.Literals.MASTER_SLAVE_TABLE__MASTER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaster(String newMaster) {
		eSet(BasicdataPackage.Literals.MASTER_SLAVE_TABLE__MASTER, newMaster);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSlave() {
		return (String)eGet(BasicdataPackage.Literals.MASTER_SLAVE_TABLE__SLAVE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlave(String newSlave) {
		eSet(BasicdataPackage.Literals.MASTER_SLAVE_TABLE__SLAVE, newSlave);
	}

} //MasterSlaveTableImpl
