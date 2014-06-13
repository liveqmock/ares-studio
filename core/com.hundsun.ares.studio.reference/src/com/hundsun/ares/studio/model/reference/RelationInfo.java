/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.RelationInfo#getHostResource <em>Host Resource</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefName <em>Used Ref Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefNamespace <em>Used Ref Namespace</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefType <em>Used Ref Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationInfo()
 * @model
 * @generated
 */
public interface RelationInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Host Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 使用了本条引用信息的ARES资源
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host Resource</em>' attribute.
	 * @see #setHostResource(IARESResource)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationInfo_HostResource()
	 * @model dataType="com.hundsun.ares.studio.model.reference.IARESResource"
	 * @generated
	 */
	IARESResource getHostResource();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getHostResource <em>Host Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host Resource</em>' attribute.
	 * @see #getHostResource()
	 * @generated
	 */
	void setHostResource(IARESResource value);

	/**
	 * Returns the value of the '<em><b>Used Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 所引用的引用名
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Ref Name</em>' attribute.
	 * @see #setUsedRefName(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationInfo_UsedRefName()
	 * @model
	 * @generated
	 */
	String getUsedRefName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefName <em>Used Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Ref Name</em>' attribute.
	 * @see #getUsedRefName()
	 * @generated
	 */
	void setUsedRefName(String value);

	/**
	 * Returns the value of the '<em><b>Used Ref Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 所使用的引用命名空间
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Ref Namespace</em>' attribute.
	 * @see #setUsedRefNamespace(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationInfo_UsedRefNamespace()
	 * @model
	 * @generated
	 */
	String getUsedRefNamespace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefNamespace <em>Used Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Ref Namespace</em>' attribute.
	 * @see #getUsedRefNamespace()
	 * @generated
	 */
	void setUsedRefNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Used Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 所使用的引用类型
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Ref Type</em>' attribute.
	 * @see #setUsedRefType(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getRelationInfo_UsedRefType()
	 * @model
	 * @generated
	 */
	String getUsedRefType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.RelationInfo#getUsedRefType <em>Used Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Ref Type</em>' attribute.
	 * @see #getUsedRefType()
	 * @generated
	 */
	void setUsedRefType(String value);

} // RelationInfo
