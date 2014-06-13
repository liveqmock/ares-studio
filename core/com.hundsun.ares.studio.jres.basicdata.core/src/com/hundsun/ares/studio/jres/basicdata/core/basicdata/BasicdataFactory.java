/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage
 * @generated
 */
public interface BasicdataFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasicdataFactory eINSTANCE = com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicdataFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Epacakge Define List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Epacakge Define List</em>'.
	 * @generated
	 */
	EpacakgeDefineList createEpacakgeDefineList();

	/**
	 * Returns a new object of class '<em>Single Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Table</em>'.
	 * @generated
	 */
	SingleTable createSingleTable();

	/**
	 * Returns a new object of class '<em>Master Slave Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Master Slave Table</em>'.
	 * @generated
	 */
	MasterSlaveTable createMasterSlaveTable();

	/**
	 * Returns a new object of class '<em>Master Slave Link Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Master Slave Link Table</em>'.
	 * @generated
	 */
	MasterSlaveLinkTable createMasterSlaveLinkTable();

	/**
	 * Returns a new object of class '<em>Basic Data Base Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Basic Data Base Model</em>'.
	 * @generated
	 */
	BasicDataBaseModel createBasicDataBaseModel();

	/**
	 * Returns a new object of class '<em>Standard Field Package Define</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Field Package Define</em>'.
	 * @generated
	 */
	StandardFieldPackageDefine createStandardFieldPackageDefine();

	/**
	 * Returns a new object of class '<em>Standard Field Model And Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Field Model And Data</em>'.
	 * @generated
	 */
	StandardFieldModelAndData createStandardFieldModelAndData();

	/**
	 * Returns a new object of class '<em>Standard Field Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Field Column</em>'.
	 * @generated
	 */
	StandardFieldColumn createStandardFieldColumn();

	/**
	 * Returns a new object of class '<em>Basic Data Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Basic Data Field</em>'.
	 * @generated
	 */
	BasicDataField createBasicDataField();

	/**
	 * Returns a new object of class '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Item</em>'.
	 * @generated
	 */
	BasicdataItem createBasicdataItem();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BasicdataPackage getBasicdataPackage();

} //BasicdataFactory
