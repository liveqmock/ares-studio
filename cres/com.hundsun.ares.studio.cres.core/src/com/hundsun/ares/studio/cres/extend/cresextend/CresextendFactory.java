/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage
 * @generated
 */
public interface CresextendFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CresextendFactory eINSTANCE = com.hundsun.ares.studio.cres.extend.cresextend.impl.CresextendFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Moudle Depend</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Moudle Depend</em>'.
	 * @generated
	 */
	MoudleDepend createMoudleDepend();

	/**
	 * Returns a new object of class '<em>Cres Moudle Extend Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cres Moudle Extend Property</em>'.
	 * @generated
	 */
	CresMoudleExtendProperty createCresMoudleExtendProperty();

	/**
	 * Returns a new object of class '<em>Cres Project Extend Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cres Project Extend Property</em>'.
	 * @generated
	 */
	CresProjectExtendProperty createCresProjectExtendProperty();

	/**
	 * Returns a new object of class '<em>Proc Define</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Proc Define</em>'.
	 * @generated
	 */
	ProcDefine createProcDefine();

	/**
	 * Returns a new object of class '<em>Gcc Define</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gcc Define</em>'.
	 * @generated
	 */
	GccDefine createGccDefine();

	/**
	 * Returns a new object of class '<em>Mvc Define</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mvc Define</em>'.
	 * @generated
	 */
	MvcDefine createMvcDefine();

	/**
	 * Returns a new object of class '<em>File Define</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Define</em>'.
	 * @generated
	 */
	FileDefine createFileDefine();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CresextendPackage getCresextendPackage();

} //CresextendFactory
