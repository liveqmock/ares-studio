/**
 * 源程序名称：ColumnsViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.stock3.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.ui.page;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.actions.AddNonStdFiledColumnAction;
import com.hundsun.ares.studio.jres.database.ui.editors.blocks.TableColumnViewerBlock;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;

/**
 * 新建表 修订记录对话框中的字段信息
 * @author sundl
 *
 */
public class RevisionHistoryColumnsViewerBlock extends TableColumnViewerBlock {

	public RevisionHistoryColumnsViewerBlock(EditingDomain editingDomain, IARESResource resource) {
		super(editingDomain, resource, null);
	}
	
	protected EReference getEReference() {
		return ChousePackage.Literals.ADD_TABLE_MODIFICATION__COLUMNS;
	}
	
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		IARESResource res = getARESResource();
		if (res != null) {
			if (DatabaseUtil.isNonStdFiledAllowed(res.getARESProject())) {
				action = getActionRegistry().getAction(AddNonStdFiledColumnAction.ID);
				menuManager.add(action);
			}
		}
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		menuManager.add(action);
		
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		buttonManager.add(action);
		
		if (DatabaseUtil.isNonStdFiledAllowed(getARESResource().getARESProject())) {
			if (addNonStdAction != null)
				buttonManager.add(addNonStdAction);
		}
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		buttonManager.add(action);
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		buttonManager.add(action);
	}
}
