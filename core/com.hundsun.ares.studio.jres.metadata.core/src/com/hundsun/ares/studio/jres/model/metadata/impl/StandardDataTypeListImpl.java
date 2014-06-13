/**
 * 源程序名称：StandardDataTypeListImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Data Type List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StandardDataTypeListImpl extends MetadataResourceDataImpl<StandardDataType> implements StandardDataTypeList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardDataTypeListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.STANDARD_DATA_TYPE_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<StandardDataType> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<StandardDataType>(StandardDataType.class, this, MetadataPackage.STANDARD_DATA_TYPE_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public StandardDataType find(String name) {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-27 #5 #5#过名字，查找标准数据类型，需要缓存
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		for(StandardDataType type:getItems()){
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
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-29 #8 #8 #当前模型的引用信息收集返回
		EList<Reference> references=new BasicEList<Reference>();
		for(StandardDataType type:getItems()){
			if(type.getRefId()!=null && !StringUtils.isBlank(type.getRefId())){
				Reference stdTypeRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdType,type, MetadataPackage.Literals.METADATA_ITEM__REF_ID);
					references.add(stdTypeRef);	
			}
		}
		return references;
	}

} //StandardDataTypeListImpl
