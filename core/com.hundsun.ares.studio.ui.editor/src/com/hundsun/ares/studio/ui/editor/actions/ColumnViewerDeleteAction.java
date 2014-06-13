/**
 * 源程序名称：ColumnViewerDeleteAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ColumnViewerDeleteAction extends ColumnViewerAction {

	public ColumnViewerDeleteAction(ColumnViewer viewer, EditingDomain editingDomain) {
		super(viewer, editingDomain);
		setText("删除");
		
		setId(IActionIDConstant.CV_DELETE);
		
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
	}

	
	@Override
	protected Command createCommand() {
		List<Object> selectedObjects = getSelectedObjects();
		if (selectedObjects.isEmpty()) {
			return null;
		}
		
		return DeleteCommand.create(getEditingDomain(), selectedObjects);
	}
}
