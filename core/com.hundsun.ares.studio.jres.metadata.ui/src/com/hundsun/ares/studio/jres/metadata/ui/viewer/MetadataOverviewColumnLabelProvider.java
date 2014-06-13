/**
 * 源程序名称：MetadataOverviewColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 总览页面的标签提供器<BR>
 * 总览页面的条目模型是<code>{@link com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement MetadataOverviewElement}</code>
 * @author gongyf
 *
 */
public class MetadataOverviewColumnLabelProvider extends BaseEObjectColumnLabelProvider {

	/**
	 * @param attribute
	 */
	public MetadataOverviewColumnLabelProvider(EAttribute attribute) {
		super(attribute);
	}

	/**
	 * @param attributeProvider
	 */
	public MetadataOverviewColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		// 对于引用的处理
		MetadataItem item = ((MetadataOverviewElement) element).getItem();
		IARESResource resource = ((MetadataOverviewElement) element).getResource();
		if (MetadataUtil.isReferencableFeature(item, getEStructuralFeature(element))) {
			MetadataItem entity = null;
			try {
				entity = MetadataUtil.defaultResolve(item, resource).first;
			} catch (CircularReferenceException e) {
			}
			return entity == null ? item : entity;
		}
		
		return item;
	}
	
	@Override
	public Color getBackground(Object element) {
		MetadataOverviewElement moe = (MetadataOverviewElement) element;
		if (moe.isConflict()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
		}
		return super.getBackground(element);
	}
}
