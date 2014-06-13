/**
 * 源程序名称：StandardFieldListImpl.java
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
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Field List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StandardFieldListImpl extends MetadataResourceDataImpl<StandardField> implements StandardFieldList {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StandardFieldListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.STANDARD_FIELD_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<StandardField> getItems() {
		if (items == null) {
			items = new EObjectContainmentWithInverseEList<StandardField>(StandardField.class, this, MetadataPackage.STANDARD_FIELD_LIST__ITEMS, MetadataPackage.METADATA_ITEM__PARENT);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public StandardField find(String name) {
		// TODO#模型设计#龚叶峰#简单#王小恒#已编码 #2011-7-27 #5 #5 #找指定名称的标准字段，注意缓存
		// Ensure that you remove @generated or mark it @generated NOT
		//throw new UnsupportedOperationException();
		for(StandardField field:getItems()){
			if(StringUtils.equals(name, field.getName()))
				return field;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.JRESResourceInfoImpl#getReferences()
	 */
	@Override
	public EList<Reference> getReferences() {
		EList<Reference> references=new BasicEList<Reference>();
		for (StandardField field : getItems()) {
			// 查找直接引用、业务数据类型引用
			if(field.getRefId()!=null && !StringUtils.isBlank(field.getRefId())){
				Reference StdFieldRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField,field, MetadataPackage.Literals.METADATA_ITEM__REF_ID);
				// TODO#代码走查-模型设计#秦元#简单#王小恒#此方法中StdFieldRef!=null无意义，
					references.add(StdFieldRef);
			}
			if(field.getDataType()!=null && !StringUtils.isBlank(field.getDataType())){
				Reference dataTypeRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.BizType,field, MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE);
					references.add(dataTypeRef);
			}
			if(field.getDataType()!=null && !StringUtils.isBlank(field.getDictionaryType())){
				Reference dictionaryRef=new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.Dict,field, MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE);
					references.add(dictionaryRef);
			}
		}
		return references;
	}

} //StandardFieldListImpl
