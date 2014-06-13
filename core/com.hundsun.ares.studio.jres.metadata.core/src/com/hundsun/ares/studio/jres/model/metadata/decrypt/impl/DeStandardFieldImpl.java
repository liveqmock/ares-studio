/**
 * 源程序名称：DeStandardFieldImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DecryptPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>De Standard Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeStandardFieldImpl extends DeMetadataItemImpl<StandardField> implements DeStandardField {
	
	private DeBusinessDataType dataType;
	private DeDictionaryType dictionaryType;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeStandardFieldImpl() {
		super();
	}

	/**
	 * @param proxyItem
	 * @param resource
	 */
	public DeStandardFieldImpl(StandardField proxyItem, IARESResource resource) {
		super(proxyItem, resource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecryptPackage.Literals.DE_STANDARD_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DeBusinessDataType getDataType() {
		if (!checkStatus()) {
			return DeBusinessDataType.NULL;
		}
		if (dataType == null) {
			Pair<StandardField, IARESResource> item = getResolvedItem();
			
			// 查找引用的元数据
			Pair<MetadataItem, IARESResource> finded = MetadataUtil.findMetadataItem(item.first.getDataType(), 
					IMetadataRefType.BizType, getResource().getARESProject());
			
			if (finded != null) {
				dataType = MetadataUtil.decrypt((BusinessDataType) finded.first, finded.second);
			} else {
				dataType = DeBusinessDataType.NULL;
			}
		}

		return dataType;
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
	 * @generated NOT
	 */
	public DeDictionaryType getDictionaryType() {
		if (!checkStatus()) {
			return DeDictionaryType.NULL;
		}
		if (dictionaryType == null) {
			Pair<StandardField, IARESResource> item = getResolvedItem();
			
			// 查找引用的元数据
			Pair<MetadataItem, IARESResource> finded = MetadataUtil.findMetadataItem(item.first.getDictionaryType(), 
					IMetadataRefType.Dict, getResource().getARESProject());
			
			if (finded != null) {
				dictionaryType = MetadataUtil.decrypt((DictionaryType) finded.first, finded.second);
			} else {
				dictionaryType = DeDictionaryType.NULL;
			}
		}

		return dictionaryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDictionaryTypeId() {
		if (!checkStatus()) return StringUtils.EMPTY;
		return getResolvedItem().first.getDictionaryType();
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
	public String getRealType(final String typeId) {
		return getDataType().getStdType().getRealType(typeId, getLength(), getPrecision());
	}

} //DeStandardFieldImpl
