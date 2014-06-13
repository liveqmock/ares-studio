/**
 * 源程序名称：MetadataOverviewProjectColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;

/**
 * 提供总览页面条目的所在工程列
 * @author gongyf
 *
 */
public class MetadataOverviewProjectColumnLabelProvider extends
		ColumnLabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		IARESResource resource = ((MetadataOverviewElement) element).getResource();
		
		if (resource.getLib() != null) {
			// 引用包中
			return resource.getLib().getElementName();
		}
		return resource.getARESProject().getElementName();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		IARESResource resource = ((MetadataOverviewElement) element).getResource();
		if (resource.getLib() != null) {
			// 引用包的图标
			return null;
		}
		
		// 工程的图标
		return null;
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
