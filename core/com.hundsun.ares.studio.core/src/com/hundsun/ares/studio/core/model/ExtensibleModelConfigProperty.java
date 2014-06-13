/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * 
 */
package com.hundsun.ares.studio.core.model;

import org.dom4j.Document;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extensible Model Config Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXml <em>Xml</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXmlCache <em>Xml Cache</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModelConfigProperty()
 * @model
 * @generated
 */
public interface ExtensibleModelConfigProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Xml</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xml</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml</em>' attribute.
	 * @see #setXml(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModelConfigProperty_Xml()
	 * @model
	 * @generated
	 */
	String getXml();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty#getXml <em>Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml</em>' attribute.
	 * @see #getXml()
	 * @generated
	 */
	void setXml(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.core.model.ExtensibleModelAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModelConfigProperty_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExtensibleModelAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Xml Cache</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xml Cache</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Cache</em>' attribute.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModelConfigProperty_XmlCache()
	 * @model dataType="com.hundsun.ares.studio.core.model.Dom4jDocument" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	Document getXmlCache();

} // ExtensibleModelConfigProperty
