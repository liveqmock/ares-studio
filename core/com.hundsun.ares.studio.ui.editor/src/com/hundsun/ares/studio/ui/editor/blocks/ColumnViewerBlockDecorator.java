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
 * 
 * 对ColumnViewerBlock进行一些修正，增加一些功能
 * @author gongyf
 *
 */
public abstract class ColumnViewerBlockDecorator<T extends ColumnViewer> implements IColumnViewerBlockDecorator<T> {
	
	private IDialogSettings settings;
	
	public ColumnViewerBlockDecorator() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#initialize(org.eclipse.jface.dialogs.IDialogSettings)
	 */
	@Override
	public void initialize(IDialogSettings settings) {
		this.settings = settings;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#dispose()
	 */
	@Override
	public void dispose() {
		this.settings = null;
	}
	
	/**
	 * @return the settings
	 */
	protected IDialogSettings getSettings() {
		return settings;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#decorateViewer(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock, T)
	 */
	@Override
	public void decorateViewer(ColumnViewerBlock<T> block, T viewer) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#decorateMenu(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock, org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void decorateMenu(ColumnViewerBlock<T> block, IMenuManager menuManager) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#decorateButtons(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock, com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	public void decorateButtons(ColumnViewerBlock<T> block, ButtonGroupManager manager) {
		
	}
	
	public void decorateToolbar(ColumnViewerBlock<T> block, ToolBarManager manager) {}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.IColumnViewerBlockDecorator#inputChanged(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock)
	 */
	@Override
	public void inputChanged(ColumnViewerBlock<T> block) {
		
	}
}
