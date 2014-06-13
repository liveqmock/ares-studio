/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getUrl <em>Url</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getIcon <em>Icon</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getSubItems <em>Sub Items</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getFunctionProxys <em>Function Proxys</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMenuItem()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name refID'"
 * @generated
 */
public interface MenuItem extends MetadataItem {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMenuItem_Url()
	 * @model default=""
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon</em>' attribute.
	 * @see #setIcon(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMenuItem_Icon()
	 * @model
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MenuItem#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

	/**
	 * Returns the value of the '<em><b>Function Proxys</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.metadata.FunctionProxy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Proxys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Proxys</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMenuItem_FunctionProxys()
	 * @model containment="true"
	 * @generated
	 */
	EList<FunctionProxy> getFunctionProxys();

	/**
	 * Returns the value of the '<em><b>Sub Items</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.metadata.MenuItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Items</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMenuItem_SubItems()
	 * @model containment="true"
	 * @generated
	 */
	EList<MenuItem> getSubItems();

} // MenuItem
