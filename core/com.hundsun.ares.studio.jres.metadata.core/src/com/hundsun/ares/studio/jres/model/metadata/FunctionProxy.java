/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getFunCode <em>Fun Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunctionProxy()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='funCode'"
 * @generated
 */
public interface FunctionProxy extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Fun Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fun Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fun Code</em>' attribute.
	 * @see #setFunCode(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunctionProxy_FunCode()
	 * @model default=""
	 * @generated
	 */
	String getFunCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getFunCode <em>Fun Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fun Code</em>' attribute.
	 * @see #getFunCode()
	 * @generated
	 */
	void setFunCode(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunctionProxy_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // FunctionProxy
