/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage
 * @generated
 */
public interface ReferenceFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReferenceFactory eINSTANCE = com.hundsun.ares.studio.model.reference.impl.ReferenceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table</em>'.
	 * @generated
	 */
	ReferenceTable createReferenceTable();

	/**
	 * Returns a new object of class '<em>Project Reference Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Project Reference Collection</em>'.
	 * @generated
	 */
	ProjectReferenceCollection createProjectReferenceCollection();

	/**
	 * Returns a new object of class '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Info</em>'.
	 * @generated
	 */
	ReferenceInfo createReferenceInfo();

	/**
	 * Returns a new object of class '<em>Relation Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relation Table</em>'.
	 * @generated
	 */
	RelationTable createRelationTable();

	/**
	 * Returns a new object of class '<em>Project Relation Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Project Relation Collection</em>'.
	 * @generated
	 */
	ProjectRelationCollection createProjectRelationCollection();

	/**
	 * Returns a new object of class '<em>Relation Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relation Info</em>'.
	 * @generated
	 */
	RelationInfo createRelationInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReferencePackage getReferencePackage();

} //ReferenceFactory
