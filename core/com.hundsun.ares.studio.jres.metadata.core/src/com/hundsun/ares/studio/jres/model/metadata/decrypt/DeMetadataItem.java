/**
 * 源程序名称：DeMetadataItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * <!-- begin-user-doc -->
 * 作为解密基类，解密类只是元数据当时的快照，解密类构建出来后不会随着元数据条目变化
 * <!-- end-user-doc -->
 *
 *
 * @see com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage#getDeMetadataItem()
 * @model
 * @generated
 */
public interface DeMetadataItem<T extends MetadataItem> extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="com.hundsun.ares.studio.jres.model.metadata.decrypt.IARESResource"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return resource;'"
	 * @generated
	 */
	IARESResource getResource();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Item</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return proxyItem;'"
	 * @generated
	 */
	T getProxyItem();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getProxyItem().getName();'"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getProxyItem().getChineseName();'"
	 * @generated
	 */
	String getChineseName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getProxyItem().getDescription();'"
	 * @generated
	 */
	String getDescription();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return StringUtils.EMPTY;\r\nreturn getResolvedItem().first.getData().get(key);'"
	 * @generated
	 */
	String getDataMapValue(String key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return null;\r\nreturn getResolvedItem().first.getData2().get(key);'"
	 * @generated
	 */
	EObject getData2MapValue(String key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="com.hundsun.ares.studio.jres.model.metadata.decrypt.Pair<T, com.hundsun.ares.studio.jres.model.metadata.decrypt.IARESResource>"
	 * @generated
	 */
	Pair<T, IARESResource> getResolvedItem();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return ECollections.emptyEMap();\r\nreturn getResolvedItem().first.getData();'"
	 * @generated
	 */
	EMap<String, String> getDataMap();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" mapType="com.hundsun.ares.studio.core.model.EStringToEObjectMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (!checkStatus()) return ECollections.emptyEMap();\r\nreturn getResolvedItem().first.getData2();'"
	 * @generated
	 */
	EMap<String, EObject> getData2Map();

} // DeMetadataItem
