/**
 * 源程序名称：PropertyNameColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author gongyf
 *
 */
public class PropertyNameColumnLabelProvider extends ColumnLabelProvider {
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		
		if (element instanceof ExtensibleModelEditingCategory) {
			return ((ExtensibleModelEditingCategory) element).getName();
		} else if (element instanceof ExtensibleModelEditingEntry) {
			return ((ExtensibleModelEditingEntry) element).getDescriptor().getDisplayName();
		}
		return StringUtils.EMPTY;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
	 */
	@Override
	public String getToolTipText(Object element) {
		if (element instanceof ExtensibleModelEditingGroup) {
			return ((ExtensibleModelEditingGroup) element).getEditingSupport().getName();
		} else if (element instanceof ExtensibleModelEditingEntry) {
			return ((ExtensibleModelEditingEntry) element).getDescriptor().getDescription();
		}
		return StringUtils.EMPTY;
	}
}
