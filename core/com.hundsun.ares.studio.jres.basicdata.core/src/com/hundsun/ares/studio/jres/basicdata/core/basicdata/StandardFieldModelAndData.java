/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import com.hundsun.ares.studio.core.model.IReferenceProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Field Model And Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getModel <em>Model</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldModelAndData()
 * @model
 * @generated
 */
public interface StandardFieldModelAndData extends IReferenceProvider {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(StandardFieldPackageDefine)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldModelAndData_Model()
	 * @model required="true"
	 * @generated
	 */
	StandardFieldPackageDefine getModel();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(StandardFieldPackageDefine value);

	/**
	 * Returns the value of the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' reference.
	 * @see #setData(BasicDataBaseModel)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldModelAndData_Data()
	 * @model required="true"
	 * @generated
	 */
	BasicDataBaseModel getData();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData#getData <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' reference.
	 * @see #getData()
	 * @generated
	 */
	void setData(BasicDataBaseModel value);

} // StandardFieldModelAndData
