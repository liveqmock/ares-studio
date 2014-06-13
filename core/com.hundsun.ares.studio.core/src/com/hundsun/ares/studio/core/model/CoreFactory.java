/**
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.core.model.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CoreFactory eINSTANCE = com.hundsun.ares.studio.core.model.impl.CoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>JRES Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>JRES Resource Info</em>'.
	 * @generated
	 */
	JRESResourceInfo createJRESResourceInfo();

	/**
	 * Returns a new object of class '<em>Basic Resource Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Basic Resource Info</em>'.
	 * @generated
	 */
	BasicResourceInfo createBasicResourceInfo();

	/**
	 * Returns a new object of class '<em>Revision History</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Revision History</em>'.
	 * @generated
	 */
	RevisionHistory createRevisionHistory();

	/**
	 * Returns a new object of class '<em>Extensible Model Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extensible Model Config Property</em>'.
	 * @generated
	 */
	ExtensibleModelConfigProperty createExtensibleModelConfigProperty();

	/**
	 * Returns a new object of class '<em>Extensible Model Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extensible Model Attribute</em>'.
	 * @generated
	 */
	ExtensibleModelAttribute createExtensibleModelAttribute();

	/**
	 * Returns a new object of class '<em>User Extensible Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Extensible Property</em>'.
	 * @generated
	 */
	UserExtensibleProperty createUserExtensibleProperty();

	/**
	 * Returns a new object of class '<em>Module Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module Extensible Model</em>'.
	 * @generated
	 */
	ModuleExtensibleModel createModuleExtensibleModel();

	/**
	 * Returns a new object of class '<em>Project Extensible Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Project Extensible Model</em>'.
	 * @generated
	 */
	ProjectExtensibleModel createProjectExtensibleModel();

	/**
	 * Returns a new object of class '<em>Project Revision History Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Project Revision History Property</em>'.
	 * @generated
	 */
	ProjectRevisionHistoryProperty createProjectRevisionHistoryProperty();

	/**
	 * Returns a new object of class '<em>Module Revision History List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module Revision History List</em>'.
	 * @generated
	 */
	ModuleRevisionHistoryList createModuleRevisionHistoryList();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CorePackage getCorePackage();

} //CoreFactory
