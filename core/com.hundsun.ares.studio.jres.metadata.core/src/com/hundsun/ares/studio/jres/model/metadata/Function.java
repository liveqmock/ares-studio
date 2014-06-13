/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Function#getSubTransCode <em>Sub Trans Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Function#getServID <em>Serv ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunction()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name subTransCode'"
 * @generated
 */
public interface Function extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Sub Trans Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Trans Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Trans Code</em>' attribute.
	 * @see #setSubTransCode(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunction_SubTransCode()
	 * @model default=""
	 * @generated
	 */
	String getSubTransCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Function#getSubTransCode <em>Sub Trans Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Trans Code</em>' attribute.
	 * @see #getSubTransCode()
	 * @generated
	 */
	void setSubTransCode(String value);

	/**
	 * Returns the value of the '<em><b>Serv ID</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Serv ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Serv ID</em>' attribute.
	 * @see #setServID(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getFunction_ServID()
	 * @model default=""
	 * @generated
	 */
	String getServID();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Function#getServID <em>Serv ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Serv ID</em>' attribute.
	 * @see #getServID()
	 * @generated
	 */
	void setServID(String value);

} // Function
