/**
 * 源程序名称：TypeDefaultValueImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Default Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TypeDefaultValueImpl extends MetadataItemImpl implements TypeDefaultValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDefaultValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.TYPE_DEFAULT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getValue(String typeId) {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-29 #1 #1 #回map的值
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		return getData().get(typeId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setValue(String typeId, String value) {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码#2011-7-29 #1#1 #值设定到map中去
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		getData().put(typeId, value);
	}

} //TypeDefaultValueImpl
