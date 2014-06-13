/**
 * 源程序名称：Operation.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getTitle <em>Title</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFile <em>File</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getOutPath <em>Out Path</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getCode <em>Code</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getUixml <em>Uixml</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.Operation#isAutobuild <em>Autobuild</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_Title()
	 * @model default=""
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

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
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_File()
	 * @model default=""
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Out Path</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Path</em>' attribute.
	 * @see #setOutPath(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_OutPath()
	 * @model default=""
	 * @generated
	 */
	String getOutPath();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getOutPath <em>Out Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Path</em>' attribute.
	 * @see #getOutPath()
	 * @generated
	 */
	void setOutPath(String value);

	/**
	 * Returns the value of the '<em><b>Function Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Name</em>' attribute.
	 * @see #setFunctionName(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_FunctionName()
	 * @model default=""
	 * @generated
	 */
	String getFunctionName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getFunctionName <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Name</em>' attribute.
	 * @see #getFunctionName()
	 * @generated
	 */
	void setFunctionName(String value);

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_Code()
	 * @model default=""
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Uixml</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uixml</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uixml</em>' attribute.
	 * @see #setUixml(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_Uixml()
	 * @model default=""
	 * @generated
	 */
	String getUixml();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#getUixml <em>Uixml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uixml</em>' attribute.
	 * @see #getUixml()
	 * @generated
	 */
	void setUixml(String value);

	/**
	 * Returns the value of the '<em><b>Autobuild</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Autobuild</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Autobuild</em>' attribute.
	 * @see #setAutobuild(boolean)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getOperation_Autobuild()
	 * @model
	 * @generated
	 */
	boolean isAutobuild();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.Operation#isAutobuild <em>Autobuild</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Autobuild</em>' attribute.
	 * @see #isAutobuild()
	 * @generated
	 */
	void setAutobuild(boolean value);

} // Operation
