/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.DatabasePackage
 * @generated
 */
public interface DatabaseFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatabaseFactory eINSTANCE = com.hundsun.ares.studio.jres.model.database.impl.DatabaseFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DB Module Common Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Module Common Property</em>'.
	 * @generated
	 */
	DBModuleCommonProperty createDBModuleCommonProperty();

	/**
	 * Returns a new object of class '<em>Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Data</em>'.
	 * @generated
	 */
	DatabaseResourceData createDatabaseResourceData();

	/**
	 * Returns a new object of class '<em>Table Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Resource Data</em>'.
	 * @generated
	 */
	TableResourceData createTableResourceData();

	/**
	 * Returns a new object of class '<em>Table Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Column</em>'.
	 * @generated
	 */
	TableColumn createTableColumn();

	/**
	 * Returns a new object of class '<em>Table Index Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Index Column</em>'.
	 * @generated
	 */
	TableIndexColumn createTableIndexColumn();

	/**
	 * Returns a new object of class '<em>Table Index</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Index</em>'.
	 * @generated
	 */
	TableIndex createTableIndex();

	/**
	 * Returns a new object of class '<em>View Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>View Resource Data</em>'.
	 * @generated
	 */
	ViewResourceData createViewResourceData();

	/**
	 * Returns a new object of class '<em>DB Gen Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Gen Context</em>'.
	 * @generated
	 */
	DBGenContext createDBGenContext();

	/**
	 * Returns a new object of class '<em>Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Foreign Key</em>'.
	 * @generated
	 */
	ForeignKey createForeignKey();

	/**
	 * Returns a new object of class '<em>Table Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Key</em>'.
	 * @generated
	 */
	TableKey createTableKey();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DatabasePackage getDatabasePackage();

} //DatabaseFactory
