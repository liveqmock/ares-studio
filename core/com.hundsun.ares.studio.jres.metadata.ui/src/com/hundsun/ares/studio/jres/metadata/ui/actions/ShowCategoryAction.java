/**
 * 源程序名称：ShowCategoryAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider;

/**
 * 分组显示操作
 * @author gongyf
 *
 */
public class ShowCategoryAction extends Action {
	private ColumnViewer viewer;

	/**
	 * @param viewer
	 */
	public ShowCategoryAction(ColumnViewer viewer) {
		super("Show Category", AS_CHECK_BOX);
		this.viewer = viewer;
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER));
		setId(IMetadataActionIDConstant.CV_SHOW_CATEGORY);
		
		IContentProvider provider = getViewer().getContentProvider();
		if (provider instanceof MetadataTreeContentProvider ) {
			boolean showCategory = ((MetadataTreeContentProvider) provider).isShowCategory();
			setChecked(showCategory);
		}
	}
	
	
	@Override
	public String getText() {
		boolean showCategory = false;
		IContentProvider provider = getViewer().getContentProvider();
		if (provider instanceof MetadataTreeContentProvider ) {
			showCategory = ((MetadataTreeContentProvider) provider).isShowCategory();
		}
		
		return showCategory ? "隐藏分组" : "显示分组";
	}
	
	/**
	 * @return the viewer
	 */
	public ColumnViewer getViewer() {
		return viewer;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		IContentProvider provider = getViewer().getContentProvider();
		if (provider instanceof MetadataTreeContentProvider ) {
			boolean showCategory = ((MetadataTreeContentProvider) provider).isShowCategory();
			if (showCategory) {
				((MetadataTreeContentProvider) provider).setShowCategory(false);
			} else {
				((MetadataTreeContentProvider) provider).setShowCategory(true);
			}
			
		}
		
		getViewer().refresh();
		
		// 2012年2月15日15:59:24 龚叶峰
		// BUG #2449::标准字段等元数据资源右键菜单“显示分组”名称不对
		// 需要显式更新文本
		setText(getText());
	}
}
