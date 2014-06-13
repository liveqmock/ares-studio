/**
 */
package com.hundsun.ares.studio.biz;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Error Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 出错信息
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorNo <em>Error No</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorInfo <em>Error Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.ErrorInfo#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.ErrorInfo#getLevel <em>Level</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.biz.BizPackage#getErrorInfo()
 * @model
 * @generated
 */
public interface ErrorInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Error No</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 对应的错误号
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error No</em>' attribute.
	 * @see #setErrorNo(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getErrorInfo_ErrorNo()
	 * @model
	 * @generated
	 */
	String getErrorNo();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorNo <em>Error No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error No</em>' attribute.
	 * @see #getErrorNo()
	 * @generated
	 */
	void setErrorNo(String value);

	/**
	 * Returns the value of the '<em><b>Error Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 出错信息
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error Info</em>' attribute.
	 * @see #setErrorInfo(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getErrorInfo_ErrorInfo()
	 * @model
	 * @generated
	 */
	String getErrorInfo();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.ErrorInfo#getErrorInfo <em>Error Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Info</em>' attribute.
	 * @see #getErrorInfo()
	 * @generated
	 */
	void setErrorInfo(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 说明
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getErrorInfo_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.ErrorInfo#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 错误级别
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see #setLevel(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getErrorInfo_Level()
	 * @model
	 * @generated
	 */
	String getLevel();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.ErrorInfo#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(String value);

} // ErrorInfo
