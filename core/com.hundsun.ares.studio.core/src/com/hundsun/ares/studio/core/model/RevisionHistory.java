/**
 * 源程序名称：RevisionHistory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Revision History</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedDate <em>Modified Date</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getVersion <em>Version</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getOrderNumber <em>Order Number</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedBy <em>Modified By</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getCharger <em>Charger</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedReason <em>Modified Reason</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModified <em>Modified</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getComment <em>Comment</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.RevisionHistory#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='modifiedDate version modified modifiedBy orderNumber'"
 * @generated
 */
public interface RevisionHistory extends ExtensibleModel {
	/**
	 * Returns the value of the '<em><b>Modified Date</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified Date</em>' attribute.
	 * @see #setModifiedDate(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_ModifiedDate()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getModifiedDate();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedDate <em>Modified Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified Date</em>' attribute.
	 * @see #getModifiedDate()
	 * @generated
	 */
	void setModifiedDate(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_Version()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Order Number</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Number</em>' attribute.
	 * @see #setOrderNumber(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_OrderNumber()
	 * @model default=""
	 * @generated
	 */
	String getOrderNumber();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getOrderNumber <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Number</em>' attribute.
	 * @see #getOrderNumber()
	 * @generated
	 */
	void setOrderNumber(String value);

	/**
	 * Returns the value of the '<em><b>Modified By</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 对应界面上的“申请人”
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modified By</em>' attribute.
	 * @see #setModifiedBy(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_ModifiedBy()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getModifiedBy();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedBy <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified By</em>' attribute.
	 * @see #getModifiedBy()
	 * @generated
	 */
	void setModifiedBy(String value);

	/**
	 * Returns the value of the '<em><b>Charger</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Charger</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 对应界面上的“修改人”
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Charger</em>' attribute.
	 * @see #setCharger(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_Charger()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getCharger();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getCharger <em>Charger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Charger</em>' attribute.
	 * @see #getCharger()
	 * @generated
	 */
	void setCharger(String value);

	/**
	 * Returns the value of the '<em><b>Modified Reason</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified Reason</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified Reason</em>' attribute.
	 * @see #setModifiedReason(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_ModifiedReason()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getModifiedReason();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModifiedReason <em>Modified Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified Reason</em>' attribute.
	 * @see #getModifiedReason()
	 * @generated
	 */
	void setModifiedReason(String value);

	/**
	 * Returns the value of the '<em><b>Modified</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified</em>' attribute.
	 * @see #setModified(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_Modified()
	 * @model default="" unique="false"
	 * @generated
	 */
	String getModified();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getModified <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified</em>' attribute.
	 * @see #getModified()
	 * @generated
	 */
	void setModified(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_Comment()
	 * @model unique="false"
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getRevisionHistory_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.core.model.RevisionHistory#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // RevisionHistory
