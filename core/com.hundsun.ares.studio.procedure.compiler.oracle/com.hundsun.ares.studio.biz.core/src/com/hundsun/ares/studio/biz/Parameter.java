/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.biz;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getId <em>Id</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getParamType <em>Param Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getType <em>Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getRealType <em>Real Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getFlags <em>Flags</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.biz.Parameter#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='id type'"
 * @generated
 */
public interface Parameter extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Param Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.biz.ParamType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Param Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Type</em>' attribute.
	 * @see com.hundsun.ares.studio.biz.ParamType
	 * @see #setParamType(ParamType)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_ParamType()
	 * @model
	 * @generated
	 */
	ParamType getParamType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getParamType <em>Param Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Type</em>' attribute.
	 * @see com.hundsun.ares.studio.biz.ParamType
	 * @see #getParamType()
	 * @generated
	 */
	void setParamType(ParamType value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Real Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real Type</em>' attribute.
	 * @see #setRealType(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_RealType()
	 * @model
	 * @generated
	 */
	String getRealType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getRealType <em>Real Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real Type</em>' attribute.
	 * @see #getRealType()
	 * @generated
	 */
	void setRealType(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.hundsun.ares.studio.biz.Multiplicity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see com.hundsun.ares.studio.biz.Multiplicity
	 * @see #setMultiplicity(Multiplicity)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Multiplicity()
	 * @model
	 * @generated
	 */
	Multiplicity getMultiplicity();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see com.hundsun.ares.studio.biz.Multiplicity
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(Multiplicity value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_DefaultValue()
	 * @model
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flags</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Flags()
	 * @model default=""
	 * @generated
	 */
	String getFlags();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(String value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' attribute.
	 * @see #setComments(String)
	 * @see com.hundsun.ares.studio.biz.BizPackage#getParameter_Comments()
	 * @model default=""
	 * @generated
	 */
	String getComments();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.biz.Parameter#getComments <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comments</em>' attribute.
	 * @see #getComments()
	 * @generated
	 */
	void setComments(String value);

} // Parameter
