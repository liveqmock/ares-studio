/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MD Module Common Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty#isUseRefFeature <em>Use Ref Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMDModuleCommonProperty()
 * @model
 * @generated
 */
public interface MDModuleCommonProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Use Ref Feature</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Ref Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Ref Feature</em>' attribute.
	 * @see #setUseRefFeature(boolean)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getMDModuleCommonProperty_UseRefFeature()
	 * @model default="false"
	 * @generated
	 */
	boolean isUseRefFeature();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty#isUseRefFeature <em>Use Ref Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Ref Feature</em>' attribute.
	 * @see #isUseRefFeature()
	 * @generated
	 */
	void setUseRefFeature(boolean value);

} // MDModuleCommonProperty
