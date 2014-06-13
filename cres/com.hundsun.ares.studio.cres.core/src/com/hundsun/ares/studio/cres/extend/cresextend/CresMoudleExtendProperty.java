/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.cres.extend.cresextend;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cres Moudle Extend Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDepends <em>Depends</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getSubSysID <em>Sub Sys ID</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseName <em>Data Base Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseConn <em>Data Base Conn</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getBizPropertyConfig <em>Biz Property Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty()
 * @model
 * @generated
 */
public interface CresMoudleExtendProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Depends</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depends</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends</em>' containment reference list.
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty_Depends()
	 * @model containment="true"
	 * @generated
	 */
	EList<MoudleDepend> getDepends();

	/**
	 * Returns the value of the '<em><b>Sub Sys ID</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Sys ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Sys ID</em>' attribute.
	 * @see #setSubSysID(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty_SubSysID()
	 * @model default=""
	 * @generated
	 */
	String getSubSysID();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getSubSysID <em>Sub Sys ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Sys ID</em>' attribute.
	 * @see #getSubSysID()
	 * @generated
	 */
	void setSubSysID(String value);

	/**
	 * Returns the value of the '<em><b>Data Base Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base Name</em>' attribute.
	 * @see #setDataBaseName(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty_DataBaseName()
	 * @model default=""
	 * @generated
	 */
	String getDataBaseName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseName <em>Data Base Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Base Name</em>' attribute.
	 * @see #getDataBaseName()
	 * @generated
	 */
	void setDataBaseName(String value);

	/**
	 * Returns the value of the '<em><b>Data Base Conn</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base Conn</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base Conn</em>' attribute.
	 * @see #setDataBaseConn(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty_DataBaseConn()
	 * @model default=""
	 * @generated
	 */
	String getDataBaseConn();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getDataBaseConn <em>Data Base Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Base Conn</em>' attribute.
	 * @see #getDataBaseConn()
	 * @generated
	 */
	void setDataBaseConn(String value);

	/**
	 * Returns the value of the '<em><b>Biz Property Config</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Biz Property Config</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Biz Property Config</em>' attribute.
	 * @see #setBizPropertyConfig(String)
	 * @see com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage#getCresMoudleExtendProperty_BizPropertyConfig()
	 * @model default=""
	 * @generated
	 */
	String getBizPropertyConfig();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty#getBizPropertyConfig <em>Biz Property Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Biz Property Config</em>' attribute.
	 * @see #getBizPropertyConfig()
	 * @generated
	 */
	void setBizPropertyConfig(String value);

} // CresMoudleExtendProperty
