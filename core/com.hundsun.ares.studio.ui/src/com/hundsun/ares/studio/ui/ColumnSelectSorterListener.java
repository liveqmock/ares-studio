/**
 * 源程序名称：ColumnSelectSorterListener.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * @author wangxh
 *
 */
public class ColumnSelectSorterListener extends SelectionAdapter {

	/**
	 * 
	 */
	ViewerColumn col;
	ColumnLabelProvider provider;
	boolean ASC = true;
	
	public ColumnSelectSorterListener(ViewerColumn col,ColumnLabelProvider provider) {
		super();
		this.col=col;
		this.provider=provider;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		col.getViewer().setSorter(new columnSorter(ASC));
		ASC = !ASC;
	}

	protected String getObjectText(Object o) {
		return StringUtils.defaultString(provider.getText(o));
	}
	
	private class columnSorter extends ViewerSorter{
		boolean ASC;
		public columnSorter(boolean ASC) {
			super();
			this.ASC = ASC;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			
			String text1 = getObjectText(e1);
			String text2 = getObjectText(e2);
			if(ASC){
				return super.compare(viewer, text1, text2);
			}
			else{
				return super.compare(viewer, text2, text1);
			}
		}
		
	}
}
