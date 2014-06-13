/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.hundsun.ares.studio.biz.BizPackage
 * @generated
 */
public interface BizFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BizFactory eINSTANCE = com.hundsun.ares.studio.biz.impl.BizFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Interface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interface</em>'.
	 * @generated
	 */
	BizInterface createBizInterface();

	/**
	 * Returns a new object of class '<em>ARES Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ARES Object</em>'.
	 * @generated
	 */
	ARESObject createARESObject();

	/**
	 * Returns a new object of class '<em>Standard Obj Field List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Obj Field List</em>'.
	 * @generated
	 */
	StandardObjFieldList createStandardObjFieldList();

	/**
	 * Returns a new object of class '<em>Standard Obj Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Standard Obj Field</em>'.
	 * @generated
	 */
	StandardObjField createStandardObjField();

	/**
	 * Returns a new object of class '<em>Error Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Error Info</em>'.
	 * @generated
	 */
	ErrorInfo createErrorInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BizPackage getBizPackage();

} //BizFactory
