/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.model.reference;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefName <em>Ref Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefNamespace <em>Ref Namespace</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefType <em>Ref Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getResource <em>Resource</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getObjectProvider <em>Object Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo()
 * @model
 * @generated
 */
public interface ReferenceInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 本条引用源使用时需要的名称，如标准字段的英文名(ID)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Name</em>' attribute.
	 * @see #setRefName(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo_RefName()
	 * @model
	 * @generated
	 */
	String getRefName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefName <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br/><B>只在提供器中使用，最终使用者不应该使用本方法</B>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Name</em>' attribute.
	 * @see #getRefName()
	 * @generated
	 */
	void setRefName(String value);

	/**
	 * Returns the value of the '<em><b>Ref Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 本条引用源使用时需要的命名空间
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Namespace</em>' attribute.
	 * @see #setRefNamespace(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo_RefNamespace()
	 * @model
	 * @generated
	 */
	String getRefNamespace();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefNamespace <em>Ref Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br/><B>只在提供器中使用，最终使用者不应该使用本方法</B>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Namespace</em>' attribute.
	 * @see #getRefNamespace()
	 * @generated
	 */
	void setRefNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 本条引用源使用时需要的引用类型，注意引用类型不等同于资源类型
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Type</em>' attribute.
	 * @see #setRefType(String)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo_RefType()
	 * @model
	 * @generated
	 */
	String getRefType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getRefType <em>Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br/><B>只在提供器中使用，最终使用者不应该使用本方法</B>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Type</em>' attribute.
	 * @see #getRefType()
	 * @generated
	 */
	void setRefType(String value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 提供本引用源的ARES资源
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' attribute.
	 * @see #setResource(IARESResource)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo_Resource()
	 * @model dataType="com.hundsun.ares.studio.model.reference.IARESResource"
	 * @generated
	 */
	IARESResource getResource();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getResource <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br/><B>只在提供器中使用，最终使用者不应该使用本方法</B>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' attribute.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(IARESResource value);

	/**
	 * Returns the value of the '<em><b>Object Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 用于从资源获取引用对象的提供器，一般不需要调用本方法，而是调用{@link #getObject()}
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Provider</em>' attribute.
	 * @see #setObjectProvider(IObjectProvider)
	 * @see com.hundsun.ares.studio.model.reference.ReferencePackage#getReferenceInfo_ObjectProvider()
	 * @model dataType="com.hundsun.ares.studio.model.reference.IObjectProvider"
	 * @generated
	 */
	IObjectProvider getObjectProvider();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.model.reference.ReferenceInfo#getObjectProvider <em>Object Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br/><B>只在提供器中使用，最终使用者不应该使用本方法</B>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Provider</em>' attribute.
	 * @see #getObjectProvider()
	 * @generated
	 */
	void setObjectProvider(IObjectProvider value);

	/**
	 * <!-- begin-user-doc -->
	 * 可以获取本条引用源信息所使用的具体对象，这个对象没有进行缓存，而是直接从资源获取的，大约单次耗时为 20μs~40μs
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Object getObject();

} // ReferenceInfo
