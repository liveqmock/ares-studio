/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Data Config List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigList#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigList()
 * @model
 * @generated
 */
public interface GeneralDataConfigList extends JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.model.metadata.GeneralDataConfigItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getGeneralDataConfigList_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<GeneralDataConfigItem> getItems();

} // GeneralDataConfigList
