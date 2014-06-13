/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.procdure;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getDatabase <em>Database</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getPseudoCode <em>Pseudo Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getInternalVariables <em>Internal Variables</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getBeginCode <em>Begin Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getEndCode <em>End Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getDefineType <em>Define Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getBizType <em>Biz Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getCreateDate <em>Create Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getRelatedTableInfo <em>Related Table Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getRelatedBasicDataInfo <em>Related Basic Data Info</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#isAutoTransaction <em>Auto Transaction</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.procdure.Procedure#getProcName <em>Proc Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface Procedure extends BizInterface, JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Database</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' attribute.
	 * @see #setDatabase(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_Database()
	 * @model default=""
	 * @generated
	 */
	String getDatabase();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getDatabase <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' attribute.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(String value);

	/**
	 * Returns the value of the '<em><b>Pseudo Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pseudo Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pseudo Code</em>' attribute.
	 * @see #setPseudoCode(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_PseudoCode()
	 * @model default=""
	 * @generated
	 */
	String getPseudoCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getPseudoCode <em>Pseudo Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pseudo Code</em>' attribute.
	 * @see #getPseudoCode()
	 * @generated
	 */
	void setPseudoCode(String value);

	/**
	 * Returns the value of the '<em><b>Internal Variables</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.procdure.InternalParam}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Variables</em>' containment reference list.
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_InternalVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<InternalParam> getInternalVariables();

	/**
	 * Returns the value of the '<em><b>Begin Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Begin Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Begin Code</em>' attribute.
	 * @see #setBeginCode(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_BeginCode()
	 * @model default=""
	 * @generated
	 */
	String getBeginCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getBeginCode <em>Begin Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Begin Code</em>' attribute.
	 * @see #getBeginCode()
	 * @generated
	 */
	void setBeginCode(String value);

	/**
	 * Returns the value of the '<em><b>End Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Code</em>' attribute.
	 * @see #setEndCode(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_EndCode()
	 * @model default=""
	 * @generated
	 */
	String getEndCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getEndCode <em>End Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Code</em>' attribute.
	 * @see #getEndCode()
	 * @generated
	 */
	void setEndCode(String value);

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute.
	 * The default value is <code>"number"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_ReturnType()
	 * @model default="number"
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getReturnType <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Define Type</b></em>' attribute.
	 * The default value is <code>"AS"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Define Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Define Type</em>' attribute.
	 * @see #setDefineType(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_DefineType()
	 * @model default="AS"
	 * @generated
	 */
	String getDefineType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getDefineType <em>Define Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Define Type</em>' attribute.
	 * @see #getDefineType()
	 * @generated
	 */
	void setDefineType(String value);

	/**
	 * Returns the value of the '<em><b>Biz Type</b></em>' attribute.
	 * The default value is <code>"function"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Biz Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Biz Type</em>' attribute.
	 * @see #setBizType(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_BizType()
	 * @model default="function"
	 * @generated
	 */
	String getBizType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getBizType <em>Biz Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Biz Type</em>' attribute.
	 * @see #getBizType()
	 * @generated
	 */
	void setBizType(String value);

	/**
	 * Returns the value of the '<em><b>Create Date</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Date</em>' attribute.
	 * @see #setCreateDate(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_CreateDate()
	 * @model default=""
	 * @generated
	 */
	String getCreateDate();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getCreateDate <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Date</em>' attribute.
	 * @see #getCreateDate()
	 * @generated
	 */
	void setCreateDate(String value);

	/**
	 * Returns the value of the '<em><b>Related Table Info</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.procdure.RelatedInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Related Table Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related Table Info</em>' containment reference list.
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_RelatedTableInfo()
	 * @model containment="true"
	 * @generated
	 */
	EList<RelatedInfo> getRelatedTableInfo();

	/**
	 * Returns the value of the '<em><b>Related Basic Data Info</b></em>' containment reference list.
	 * The list contents are of type {@link com.hundsun.ares.studio.procdure.RelatedInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Related Basic Data Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related Basic Data Info</em>' containment reference list.
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_RelatedBasicDataInfo()
	 * @model containment="true"
	 * @generated
	 */
	EList<RelatedInfo> getRelatedBasicDataInfo();

	/**
	 * Returns the value of the '<em><b>Auto Transaction</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Transaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Transaction</em>' attribute.
	 * @see #setAutoTransaction(boolean)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_AutoTransaction()
	 * @model default="false"
	 * @generated
	 */
	boolean isAutoTransaction();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#isAutoTransaction <em>Auto Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Transaction</em>' attribute.
	 * @see #isAutoTransaction()
	 * @generated
	 */
	void setAutoTransaction(boolean value);

	/**
	 * Returns the value of the '<em><b>Proc Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proc Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proc Name</em>' attribute.
	 * @see #setProcName(String)
	 * @see com.hundsun.ares.studio.procdure.ProcdurePackage#getProcedure_ProcName()
	 * @model default=""
	 * @generated
	 */
	String getProcName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.procdure.Procedure#getProcName <em>Proc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proc Name</em>' attribute.
	 * @see #getProcName()
	 * @generated
	 */
	void setProcName(String value);

} // Procedure
