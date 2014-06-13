/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.hundsun.ares.studio.jres.model.database.oracle;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Space</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getName <em>Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getChineseName <em>Chinese Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getUser <em>User</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getFile <em>File</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getSize <em>Size</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getDescription <em>Description</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getLogicName <em>Logic Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name user file size logicName'"
 * @generated
 */
public interface TableSpace extends ExtensibleModel {
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
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Chinese Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chinese Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chinese Name</em>' attribute.
	 * @see #setChineseName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_ChineseName()
	 * @model default=""
	 * @generated
	 */
	String getChineseName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getChineseName <em>Chinese Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chinese Name</em>' attribute.
	 * @see #getChineseName()
	 * @generated
	 */
	void setChineseName(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_User()
	 * @model default=""
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_File()
	 * @model default=""
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_Size()
	 * @model default=""
	 * @generated
	 */
	String getSize();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(String value);

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
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Logic Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logic Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logic Name</em>' attribute.
	 * @see #setLogicName(String)
	 * @see com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage#getTableSpace_LogicName()
	 * @model
	 * @generated
	 */
	String getLogicName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.database.oracle.TableSpace#getLogicName <em>Logic Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Logic Name</em>' attribute.
	 * @see #getLogicName()
	 * @generated
	 */
	void setLogicName(String value);

} // TableSpace
