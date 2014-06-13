/**
 * 源程序名称：StandardField.java
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
 * A representation of the model object '<em><b>Standard Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getLength <em>Length</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getPrecision <em>Precision</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDataType <em>Data Type</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDictionaryType <em>Dictionary Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardField()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='name refId length precision dataType dictionaryType'"
 * @generated
 */
public interface StandardField extends MetadataItem {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardField_Length()
	 * @model default=""
	 * @generated
	 */
	String getLength();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(String value);

	/**
	 * Returns the value of the '<em><b>Precision</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precision</em>' attribute.
	 * @see #setPrecision(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardField_Precision()
	 * @model default=""
	 * @generated
	 */
	String getPrecision();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getPrecision <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision</em>' attribute.
	 * @see #getPrecision()
	 * @generated
	 */
	void setPrecision(String value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see #setDataType(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardField_DataType()
	 * @model default=""
	 * @generated
	 */
	String getDataType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 * Returns the value of the '<em><b>Dictionary Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dictionary Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dictionary Type</em>' attribute.
	 * @see #setDictionaryType(String)
	 * @see com.hundsun.ares.studio.jres.model.metadata.MetadataPackage#getStandardField_DictionaryType()
	 * @model default=""
	 * @generated
	 */
	String getDictionaryType();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.model.metadata.StandardField#getDictionaryType <em>Dictionary Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictionary Type</em>' attribute.
	 * @see #getDictionaryType()
	 * @generated
	 */
	void setDictionaryType(String value);

} // StandardField
