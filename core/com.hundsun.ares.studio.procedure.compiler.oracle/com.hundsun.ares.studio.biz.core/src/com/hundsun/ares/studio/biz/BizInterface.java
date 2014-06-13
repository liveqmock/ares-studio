/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz;

import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#isInputCollection <em>Input Collection</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#isOutputCollection <em>Output Collection</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#getOutputParameters <em>Output Parameters</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#getInterfaceFlag <em>Interface Flag</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.BizInterface#getErrorInfos <em>Error Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface()
 * @model
 * @generated
 */
public interface BizInterface extends EObject {
	/**
	 * Returns the value of the '<em><b>Input Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Collection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Collection</em>' attribute.
	 * @see #setInputCollection(boolean)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_InputCollection()
	 * @model
	 * @generated
	 */
	boolean isInputCollection();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.BizInterface#isInputCollection <em>Input Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Collection</em>' attribute.
	 * @see #isInputCollection()
	 * @generated
	 */
	void setInputCollection(boolean value);

	/**
	 * Returns the value of the '<em><b>Output Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Collection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Collection</em>' attribute.
	 * @see #setOutputCollection(boolean)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_OutputCollection()
	 * @model
	 * @generated
	 */
	boolean isOutputCollection();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.BizInterface#isOutputCollection <em>Output Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Collection</em>' attribute.
	 * @see #isOutputCollection()
	 * @generated
	 */
	void setOutputCollection(boolean value);

	/**
	 * Returns the value of the '<em><b>Input Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.biz.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameters</em>' containment reference list.
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_InputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getInputParameters();

	/**
	 * Returns the value of the '<em><b>Output Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.biz.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Parameters</em>' containment reference list.
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_OutputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getOutputParameters();

	/**
	 * Returns the value of the '<em><b>Interface Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Flag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface Flag</em>' attribute.
	 * @see #setInterfaceFlag(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_InterfaceFlag()
	 * @model
	 * @generated
	 */
	String getInterfaceFlag();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.BizInterface#getInterfaceFlag <em>Interface Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Flag</em>' attribute.
	 * @see #getInterfaceFlag()
	 * @generated
	 */
	void setInterfaceFlag(String value);

	/**
	 * Returns the value of the '<em><b>Error Infos</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * "³ö´íËµÃ÷"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error Infos</em>' containment reference list.
	 * @see com.hundsun.ares.studio.biz.BizPackage#getBizInterface_ErrorInfos()
	 * @model containment="true"
	 * @generated
	 */
	EList<ErrorNoItem> getErrorInfos();

} // BizInterface
