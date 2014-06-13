/**
 * 源程序名称：ErrorNoListImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error No List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ErrorNoListImpl extends MetadataResourceDataImpl<ErrorNoItem> implements ErrorNoList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorNoListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.ERROR_NO_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<ErrorNoItem> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<ErrorNoItem>(ErrorNoItem.class, this, MetadataPackage.ERROR_NO_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ErrorNoItem find(String name) {
		/*
		 * TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-27 #5 #5 #根据名字查找错误号对象
		 * 
		 * 查找范围是所有分类
		 */
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		for(ErrorNoItem item:getItems()){
			if(StringUtils.equals(name, item.getName()))
				return item;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-29 #9 #10 #当前模型的引用信息收集返回
		EList<Reference> references=new BasicEList<Reference>();
		for(ErrorNoItem item:getItems()){
			if(item.getRefId()!=null && !StringUtils.isBlank(item.getRefId())){
				Reference errorNoRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.ErrNo,item, MetadataPackage.Literals.METADATA_ITEM__REF_ID);
					references.add(errorNoRef);	
			}
		}
		return references;
	}

} //ErrorNoListImpl
