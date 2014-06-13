/**
 * 源程序名称：ErrorNoItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Error No Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getNo <em>No</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getMessage <em>Message</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getConstantName <em>Constant Name</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getLevel <em>Level</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getErrorNoItem()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name refId constantName message no level'"
 * @generated
 */
public interface ErrorNoItem extends MetadataItem {
	/**
	 * Returns the value of the '<em><b>No</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No</em>' attribute.
	 * @see #setNo(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getErrorNoItem_No()
	 * @model default=""
	 * @generated
	 */
	String getNo();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getNo <em>No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No</em>' attribute.
	 * @see #getNo()
	 * @generated
	 */
	void setNo(String value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getErrorNoItem_Message()
	 * @model default=""
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Constant Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant Name</em>' attribute.
	 * @see #setConstantName(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getErrorNoItem_ConstantName()
	 * @model default=""
	 * @generated
	 */
	String getConstantName();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getConstantName <em>Constant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant Name</em>' attribute.
	 * @see #getConstantName()
	 * @generated
	 */
	void setConstantName(String value);

	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see #setLevel(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getErrorNoItem_Level()
	 * @model default=""
	 * @generated
	 */
	String getLevel();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(String value);

} // ErrorNoItem
