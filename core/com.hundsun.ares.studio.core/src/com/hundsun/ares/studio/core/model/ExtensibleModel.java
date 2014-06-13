/**
 * 源程序名称：ExtensibleModel.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extensible Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.hundsun.ares.studio.core.model.ExtensibleModel#getData <em>Data</em>}</li>
 *   <li>{@link com.hundsun.ares.studio.core.model.ExtensibleModel#getData2 <em>Data2</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModel()
 * @model abstract="true"
 * @generated
 */
public interface ExtensibleModel extends IJSONData {
	/**
	 * Returns the value of the '<em><b>Data</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' map.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModel_Data()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getData();

	/**
	 * Returns the value of the '<em><b>Data2</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data2</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data2</em>' map.
	 * @see com.hundsun.ares.studio.core.model.CorePackage#getExtensibleModel_Data2()
	 * @model mapType="com.hundsun.ares.studio.core.model.EStringToEObjectMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
	 * @generated
	 */
	EMap<String, EObject> getData2();

} // ExtensibleModel
