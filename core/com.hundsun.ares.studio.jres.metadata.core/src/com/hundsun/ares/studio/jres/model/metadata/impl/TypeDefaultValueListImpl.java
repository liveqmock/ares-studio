/**
 * 源程序名称：TypeDefaultValueListImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Default Value List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TypeDefaultValueListImpl extends MetadataResourceDataImpl<TypeDefaultValue> implements TypeDefaultValueList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDefaultValueListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.TYPE_DEFAULT_VALUE_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<TypeDefaultValue> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<TypeDefaultValue>(TypeDefaultValue.class, this, MetadataPackage.TYPE_DEFAULT_VALUE_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeDefaultValue find(String name) {
		// TODO#模型设计#龚叶峰#普通#王小恒#已编码 #2011-7-27 #5 #5 #效的查找指定名字的默认值对象
		// Ensure that you remove @generated or mark it @generated NOT
		
		//throw new UnsupportedOperationException();
		for(TypeDefaultValue value:getItems()){
			if(StringUtils.equals(name, value.getName()))
				return value;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-29 #8#10#当前模型的引用信息收集返回
		EList<Reference> references=new BasicEList<Reference>();
		for(TypeDefaultValue value:getItems()){
			if(value.getRefId()!=null && !StringUtils.isBlank(value.getRefId())){
				Reference DefValueRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.DefValue,value, MetadataPackage.Literals.METADATA_ITEM__REF_ID);
				if(DefValueRef!=null){
					references.add(DefValueRef);	
				}
			}
		}
		return references;
	}

} //TypeDefaultValueListImpl
