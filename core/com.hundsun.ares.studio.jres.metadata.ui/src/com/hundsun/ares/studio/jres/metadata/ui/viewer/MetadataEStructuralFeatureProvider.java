/**
 * 源程序名称：MetadataEStructuralFeatureProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 能够处理包含分类的情况
 * @author gongyf
 *
 */
public class MetadataEStructuralFeatureProvider implements IEStructuralFeatureProvider {

	EStructuralFeature categoryAttribute;
	EStructuralFeature itemAttribute;
	
	

	/**
	 * @param categoryAttribute
	 * @param itemAttribute
	 */
	public MetadataEStructuralFeatureProvider(EStructuralFeature categoryAttribute,
			EStructuralFeature itemAttribute) {
		super();
		this.categoryAttribute = categoryAttribute;
		this.itemAttribute = itemAttribute;
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.IEAttributeProvider#getAttribute(java.lang.Object)
	 */
	@Override
	public EStructuralFeature getFeature(Object element) {
		if (element instanceof MetadataCategory) {
			return categoryAttribute;
		} else if (element instanceof MetadataItem) {
			return itemAttribute;
		}
		return null;
	}

}
