/**
 * 源程序名称：MetadataItemEditingSupportDecorator.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author gongyf
 *
 */
public class MetadataItemEditingSupportDecorator implements
		IEditingSupportDecorator {

	protected IEStructuralFeatureProvider provider;
	private IARESResource resource;
	
	public MetadataItemEditingSupportDecorator(EStructuralFeature feature,IARESResource resource) {
		this(new IEStructuralFeatureProvider.Impl(feature));
		this.resource = resource;
		
	}
	
	public MetadataItemEditingSupportDecorator(IEStructuralFeatureProvider provider) {
		super();
		this.provider = provider;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator#decorateGetCellEditor(org.eclipse.jface.viewers.CellEditor, java.lang.Object)
	 */
	@Override
	public CellEditor decorateGetCellEditor(CellEditor cellEditor,
			Object element) {
		return cellEditor;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator#decorateCanEdit(boolean, java.lang.Object)
	 */
	@Override
	public boolean decorateCanEdit(boolean canEdit, Object element) {
		if (element instanceof MetadataItem) {
			MetadataItem item = (MetadataItem) element;
			if (MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem(item) ) {
				if (!MetadataUtil.isReferencableFeature(item, provider.getFeature(element)) ) {
					return canEdit;
				}
				return false;
			}
		} else if (element instanceof UncategorizedItemsCategoryImpl) {
			return false;
		}
		return canEdit;
	}

}
