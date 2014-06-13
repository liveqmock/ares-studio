/**
 * 源程序名称：MetadataMapColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectMapColumnLabelProvider;

/**
 * @author gongyf
 *
 */
public class MetadataMapColumnLabelProvider extends
		EObjectMapColumnLabelProvider {

	private IARESResource resource;

	/**
	 * @param reference
	 * @param key
	 * @param resource
	 */
	public MetadataMapColumnLabelProvider(EReference reference, Object key,
			IARESResource resource) {
		super(reference, key);
		this.resource = resource;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object element) {
		if(element instanceof MetadataItem){
			if(MetadataUtil.isUseRefFeature(resource) && MetadataUtil.isReferencingItem((MetadataItem) element)){
				return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
			}
		}
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}

		return super.getBackground(element);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectMapColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {

		EObject owner = super.getOwner(element);
		if ( MetadataUtil.isUseRefFeature(resource) ) {
			// 如果项目启用了引用则处理
			
			if (owner instanceof MetadataItem) {
				// 对于引用的处理
				MetadataItem item = (MetadataItem)owner;
				MetadataItem entity = null;
				if(MetadataUtil.isReferencingItem(item)){
					try {
						entity = MetadataUtil.defaultResolve(item, resource).first;
					} catch (CircularReferenceException e) {
					}
				}
				return entity == null ? item : entity;
			}
		}

		return owner;
	
//		EObject owner = super.getOwner(element);
//		if (owner instanceof MetadataItem) {
//			// 对于引用的处理
//			MetadataItem item = (MetadataItem)owner;
//			MetadataItem entity = null;
//			try {
//				entity = MetadataUtil.defaultResolve(item, resource).first;
//			} catch (CircularReferenceException e) {
//				
//			}
//			return entity == null ? item : entity;
//		}
//		return owner;
	}
}
