/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Additional Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.AdditionalObject#getAdditions <em>Additions</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.core.CorePackage#getAdditionalObject()
 * @model
 * @generated
 */
public interface AdditionalObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Additions</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Object},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additions</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additions</em>' map.
	 * @see com.hundsun.ares.studio.jres.model.core.CorePackage#getAdditionalObject_Additions()
	 * @model mapType="com.hundsun.ares.studio.jres.model.core.EStringToObjectMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject>" transient="true"
	 * @generated
	 */
	EMap<String, Object> getAdditions();

} // AdditionalObject
