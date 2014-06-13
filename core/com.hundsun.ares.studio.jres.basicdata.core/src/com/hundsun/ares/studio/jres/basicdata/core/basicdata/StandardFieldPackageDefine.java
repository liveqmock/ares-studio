/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Field Package Define</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldPackageDefine()
 * @model
 * @generated
 */
public interface StandardFieldPackageDefine extends PackageDefine {
	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage#getStandardFieldPackageDefine_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<StandardFieldColumn> getFields();

} // StandardFieldPackageDefine
