/**
 * 源程序名称：TextAttributeReferenceImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.logic.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

import com.hundsun.ares.studio.core.model.impl.ReferenceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;


public class BaiscDataReferenceImpl extends ReferenceImpl {
	
	EAttribute attr;
	/**
	 * @param object
	 * @param attribute
	 */
	public BaiscDataReferenceImpl(EAttribute attr) {
		super();
		this.type = IMetadataRefType.StdField;
		this.attr = attr;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#getValue()
	 */
	@Override
	public String getValue() {
		return attr.getName();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		attr.setName(value);
		if(null != ((BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder)attr).getExtendedMetaData() ){
			((BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder)attr).getExtendedMetaData().setName(value);
		}
	}
	
}
