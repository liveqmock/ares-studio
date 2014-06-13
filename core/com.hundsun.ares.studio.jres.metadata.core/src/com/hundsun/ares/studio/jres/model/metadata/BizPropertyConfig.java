/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Biz Property Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig#getEname <em>Ename</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getBizPropertyConfig()
 * @model
 * @generated
 */
public interface BizPropertyConfig extends MetadataItem {
	/**
	 * Returns the value of the '<em><b>Ename</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ename</em>' attribute.
	 * @see #setEname(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getBizPropertyConfig_Ename()
	 * @model default=""
	 * @generated
	 */
	String getEname();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig#getEname <em>Ename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ename</em>' attribute.
	 * @see #getEname()
	 * @generated
	 */
	void setEname(String value);

} // BizPropertyConfig
