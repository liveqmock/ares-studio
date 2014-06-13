/**
 * 源程序名称：DeConstantItemImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>De Constant Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeConstantItemImpl extends DeMetadataItemImpl<ConstantItem> implements DeConstantItem {
	
	private DeStandardDataType stdType;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeConstantItemImpl() {
		super();
	}

	/**
	 * @param proxyItem
	 * @param resource
	 */
	public DeConstantItemImpl(ConstantItem proxyItem, IARESResource resource) {
		super(proxyItem, resource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecryptPackage.Literals.DE_CONSTANT_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DeStandardDataType getDataType() {
		if (!checkStatus()) {
			return DeStandardDataType.NULL;
		}
		if (stdType == null) {
			Pair<ConstantItem, IARESResource> item = getResolvedItem();
			
			// 查找引用的元数据
			Pair<MetadataItem, IARESResource> finded = MetadataUtil.findMetadataItem(item.first.getDataType(), 
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
	public String getDataTypeId() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getDataType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLength() {
		if (!checkStatus()) return "";
		return getResolvedItem().first.getLength();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrecision() {
		if (!checkStatus()) return "";
		return getResolvedItem().first.getPrecision();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		if (!checkStatus()) return "";
		return getResolvedItem().first.getValue();
	}

} //DeConstantItemImpl
