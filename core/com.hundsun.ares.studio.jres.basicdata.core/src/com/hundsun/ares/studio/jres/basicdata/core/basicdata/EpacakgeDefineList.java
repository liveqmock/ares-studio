/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;


import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.model.IReferenceProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Epacakge Define List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getEpacakgeDefineList()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='url'"
 * @generated
 */
public interface EpacakgeDefineList extends JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getEpacakgeDefineList_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<PackageDefine> getItems();

} // EpacakgeDefineList
