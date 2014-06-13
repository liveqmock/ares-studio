/**
 * 源程序名称：DeBusinessDataTypeImpl.java
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
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>De Business Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeBusinessDataTypeImpl extends DeMetadataItemImpl<BusinessDataType> implements DeBusinessDataType {
	
	private DeTypeDefaultValue defaultValue;
	private DeStandardDataType stdType;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeBusinessDataTypeImpl() {
		super();
	}

	/**
	 * @param proxyItem
	 * @param resource
	 */
	public DeBusinessDataTypeImpl(BusinessDataType proxyItem,
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
		return DecryptPackage.Literals.DE_BUSINESS_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DeTypeDefaultValue getDefaultValue() {
		if (!checkStatus()) {
			return DeTypeDefaultValue.NULL;
		}
		if (defaultValue == null) {
			Pair<BusinessDataType, IARESResource> item = getResolvedItem();
			
			// 查找引用的元数据
			Pair<MetadataItem, IARESResource> finded = MetadataUtil.findMetadataItem(item.first.getDefaultValue(), 
					IMetadataRefType.DefValue, getResource().getARESProject());
			
			if (finded != null) {
				defaultValue = MetadataUtil.decrypt((TypeDefaultValue) finded.first, finded.second);
			} else {
				defaultValue = DeTypeDefaultValue.NULL;
			}
		}
		
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValueId() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getDefaultValue();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLength() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getLength();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrecision() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getPrecision();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DeStandardDataType getStdType() {
		if (!checkStatus()) {
			return DeStandardDataType.NULL;
		}
		if (stdType == null) {
			Pair<BusinessDataType, IARESResource> item = getResolvedItem();
			
			// 查找引用的元数据
			Pair<MetadataItem, IARESResource> finded = MetadataUtil.findMetadataItem(item.first.getStdType(), 
					IMetadataRefType.StdType, getResource().getARESProject());
			
			if (finded != null) {
				stdType = MetadataUtil.decrypt((StandardDataType) finded.first, finded.second);
			} else {
				stdType = DeStandardDataType.NULL;
			}
		}
		
		return stdType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStdTypeId() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getStdType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealDefaultValue(final String typeId) {
		return getDefaultValue().getValue(typeId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealType(final String typeId) {
		return getStdType().getRealType(typeId, getLength(), getPrecision());
	}

} //DeBusinessDataTypeImpl
