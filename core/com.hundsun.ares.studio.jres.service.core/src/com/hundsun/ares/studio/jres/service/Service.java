/**
 * 源程序名称：${file_name}
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：${project_name}
 * 功能说明：$$desc
 * 相关文档：
 * 作者：${user}
 */
package com.hundsun.ares.studio.jres.service;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.jres.service.Service#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.jres.service.ServicePackage#getService()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='objectId'"
 * @generated
 */
public interface Service extends JRESResourceInfo {
	/**
	 * Returns the value of the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' containment reference.
	 * @see #setInterface(BizInterface)
	 * @see com.hundsun.ares.studio.jres.service.ServicePackage#getService_Interface()
	 * @model containment="true"
	 * @generated
	 */
	BizInterface getInterface();

	/**
	 * Sets the value of the '{@link com.hundsun.ares.studio.jres.service.Service#getInterface <em>Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' containment reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(BizInterface value);

} // Service
