/**
 * 源程序名称：JRESResourceInfo.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JRES Resource Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.JRESResourceInfo#getHistories <em>Histories</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.JRESResourceInfo#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getJRESResourceInfo()
 * @model
 * @generated
 */
public interface JRESResourceInfo extends ExtensibleModel, BasicResourceInfo, IReferenceProvider {
	/**
	 * Returns the value of the '<em><b>Histories</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.core.model.RevisionHistory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Histories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Histories</em>' containment reference list.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getJRESResourceInfo_Histories()
	 * @model containment="true"
	 * @generated
	 */
	EList<RevisionHistory> getHistories();

	/**
	 * Returns the value of the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fully Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #setFullyQualifiedName(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getJRESResourceInfo_FullyQualifiedName()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getFullyQualifiedName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.JRESResourceInfo#getFullyQualifiedName <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #getFullyQualifiedName()
	 * @generated
	 */
	void setFullyQualifiedName(String value);

} // JRESResourceInfo
