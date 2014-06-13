/**
 * 源程序名称：BusinessDataTypeListImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;

import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Business Data Type List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BusinessDataTypeListImpl extends MetadataResourceDataImpl<BusinessDataType> implements BusinessDataTypeList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessDataTypeListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.BUSINESS_DATA_TYPE_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<BusinessDataType> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<BusinessDataType>(BusinessDataType.class, this, MetadataPackage.BUSINESS_DATA_TYPE_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public BusinessDataType find(String name) {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-27 #5 #5 #过名称查找业务数据类型，需要缓存
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		for(BusinessDataType type:getItems()){
			if(StringUtils.equals(name, type.getName()))
				return type;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-29#20 #20 #当前模型的引用信息收集返回
		EList<Reference> references=new BasicEList<Reference>();
		for(BusinessDataType type:getItems()){
			if(type.getRefId()!=null && !StringUtils.isBlank(type.getRefId())){
				Reference bizTypeRef = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.BizType,type, MetadataPackage.Literals.METADATA_ITEM__REF_ID);
					references.add(bizTypeRef);	
			}
			if(type.getStdType()!=null && !StringUtils.isBlank(type.getStdType())){
				Reference  stdTypeRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdType,type, MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE);
					references.add(stdTypeRef);
			}
			if(type.getDefaultValue()!=null && !StringUtils.isBlank(type.getDefaultValue())){
				Reference  defaultTypeRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.DefValue,type, MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE);
					references.add(defaultTypeRef);
			}
		}
		return references;
	}

} //BusinessDataTypeListImpl
