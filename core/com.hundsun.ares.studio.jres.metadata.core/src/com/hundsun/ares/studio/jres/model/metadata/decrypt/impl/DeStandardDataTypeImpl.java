/**
 * 源程序名称：DeStandardDataTypeImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>De Standard Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeStandardDataTypeImpl extends DeMetadataItemImpl<StandardDataType> implements DeStandardDataType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeStandardDataTypeImpl() {
		super();
	}

	/**
	 * @param proxyItem
	 * @param resource
	 */
	public DeStandardDataTypeImpl(StandardDataType proxyItem,
			IARESResource resource) {
		super(proxyItem, resource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecryptPackage.Literals.DE_STANDARD_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue(final String typeId) {
		return getDataMapValue(typeId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealType(final String typeId, final String length, final String precision) {
		return StringUtils.replaceEach(getValue(typeId), 
						new String[]{"$L", "$P"}, 
						new String[]{length, precision});
	}

} //DeStandardDataTypeImpl
