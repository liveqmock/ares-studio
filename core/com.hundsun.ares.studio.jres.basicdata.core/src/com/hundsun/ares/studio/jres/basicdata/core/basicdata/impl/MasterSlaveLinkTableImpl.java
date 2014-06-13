/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Master Slave Link Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl#getMaster <em>Master</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl#getSlave <em>Slave</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.MasterSlaveLinkTableImpl#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MasterSlaveLinkTableImpl extends PackageDefineImpl implements MasterSlaveLinkTable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterSlaveLinkTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaster() {
		return (String)eGet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaster(String newMaster) {
		eSet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__MASTER, newMaster);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSlave() {
		return (String)eGet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlave(String newSlave) {
		eSet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__SLAVE, newSlave);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLink() {
		return (String)eGet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLink(String newLink) {
		eSet(BasicdataPackage.Literals.MASTER_SLAVE_LINK_TABLE__LINK, newLink);
	}

} //MasterSlaveLinkTableImpl
