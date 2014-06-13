/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.ui.editor.actions.ButtonGroupManager;

/**
 * 装饰器用来以代码的方式动态修改Block中的表格，菜单，工具栏按钮等...
 * @author gongyf
 *
 * @param <T>
 */
public interface IColumnViewerBlockDecorator<T extends ColumnViewer> {

	public abstract void initialize(IDialogSettings settings);

	public abstract void dispose();

	/**
	 * 装饰表格视图本身，此时表格列也已经创建完毕
	 * 
	 * @param block
	 * @param viewer
	 */
	public abstract void decorateViewer(ColumnViewerBlock<T> block, T viewer);

	/**
	 * 
	 * 装饰右键菜单
	 * @param block
	 * @param menuManager
	 */
	public abstract void decorateMenu(ColumnViewerBlock<T> block, IMenuManager menuManager);

	/**
	 * 装饰右侧按钮区
	 * @param block
	 * @param manager
	 */
	public abstract void decorateButtons(ColumnViewerBlock<T> block,	ButtonGroupManager manager);
	
	/**
	 * 修饰Toolbar（替换原来的ButtonManager）
	 * @param block
	 * @param manager
	 */
	void decorateToolbar(ColumnViewerBlock<T> block, ToolBarManager manager);
	
	/**
	 * input变化了
	 * @param block
	 */
	public abstract void inputChanged(ColumnViewerBlock<T> block);

}