/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage
 * @generated
 */
public interface OracleFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OracleFactory eINSTANCE = com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Table Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Property</em>'.
	 * @generated
	 */
	OracleTableProperty createOracleTableProperty();

	/**
	 * Returns a new object of class '<em>Index Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Index Property</em>'.
	 * @generated
	 */
	OracleIndexProperty createOracleIndexProperty();

	/**
	 * Returns a new object of class '<em>View Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>View Property</em>'.
	 * @generated
	 */
	OracleViewProperty createOracleViewProperty();

	/**
	 * Returns a new object of class '<em>Module Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module Property</em>'.
	 * @generated
	 */
	OracleModuleProperty createOracleModuleProperty();

	/**
	 * Returns a new object of class '<em>Space Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Resource Data</em>'.
	 * @generated
	 */
	OracleSpaceResourceData createOracleSpaceResourceData();

	/**
	 * Returns a new object of class '<em>Table Space Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Space Relation</em>'.
	 * @generated
	 */
	TableSpaceRelation createTableSpaceRelation();

	/**
	 * Returns a new object of class '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Space</em>'.
	 * @generated
	 */
	TableSpace createTableSpace();

	/**
	 * Returns a new object of class '<em>User Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Resource Data</em>'.
	 * @generated
	 */
	OracleUserResourceData createOracleUserResourceData();

	/**
	 * Returns a new object of class '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User</em>'.
	 * @generated
	 */
	OracleUser createOracleUser();

	/**
	 * Returns a new object of class '<em>Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Privilege</em>'.
	 * @generated
	 */
	OraclePrivilege createOraclePrivilege();

	/**
	 * Returns a new object of class '<em>Trigger Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trigger Resource Data</em>'.
	 * @generated
	 */
	TriggerResourceData createTriggerResourceData();

	/**
	 * Returns a new object of class '<em>Sequence Resource Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Resource Data</em>'.
	 * @generated
	 */
	SequenceResourceData createSequenceResourceData();

	/**
	 * Returns a new object of class '<em>Database Module Extensible Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Database Module Extensible Property</em>'.
	 * @generated
	 */
	DatabaseModuleExtensibleProperty createDatabaseModuleExtensibleProperty();

	/**
	 * Returns a new object of class '<em>Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Property</em>'.
	 * @generated
	 */
	OracleSequenceProperty createOracleSequenceProperty();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OraclePackage getOraclePackage();

} //OracleFactory
