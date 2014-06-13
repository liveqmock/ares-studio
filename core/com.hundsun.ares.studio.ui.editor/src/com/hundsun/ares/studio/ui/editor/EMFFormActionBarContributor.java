/**
 * 源程序名称：EMFFormActionBarContributor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

public class EMFFormActionBarContributor extends MultiPageEditorActionBarContributor  {
	
	static final Logger logger = Logger.getLogger(EMFFormActionBarContributor.class);
	
	private List<IGlobalActionHandlerProvider> handleProviders = new ArrayList<IGlobalActionHandlerProvider>();
	
	private IGlobalActionHandlerProviderListener listener = new IGlobalActionHandlerProviderListener() {
		
		@Override
		public void deactivated(IGlobalActionHandlerProvider provider) {
			provider.clearGlobalActions(getActionBars());
			
		}
		
		@Override
		public void activated(IGlobalActionHandlerProvider provider) {
			provider.shareGlobalActions(getActionBars());
		}
	};
	
	/**
	 * This keeps track of the current editor part.
	 */
	protected IEditorPart activeEditor;

	/**
	 * This is the action used to implement undo.
	 */
	protected UndoAction undoAction;

	/**
	 * This is the action used to implement redo.
	 */
	protected RedoAction redoAction;

	/**
	 * This creates an instance of the contributor.
	 */
	public EMFFormActionBarContributor() {
		super();
	}

	@Override
	public void init(IActionBars actionBars) {
		super.init(actionBars);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

		undoAction = new UndoAction();
		undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));

		redoAction = new RedoAction();
		redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
	}
	
	public IEditorPart getActiveEditor() {
		return activeEditor;
	}
	
	public void addGlobalActionHandlerProvider(IGlobalActionHandlerProvider provider) {
		handleProviders.add(provider);
		provider.addGlobalActionHandlerProviderListener(listener);
	}
	
	public void removeGlobalActionHandlerProvider(IGlobalActionHandlerProvider provider) {
		handleProviders.remove(provider);
		provider.removeGlobalActionHandlerProviderListener(listener);
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		activeEditor = part;
		super.setActiveEditor(part);
		
		undoAction.setActiveWorkbenchPart(part);
		redoAction.setActiveWorkbenchPart(part);
		
		getActionBars().setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		getActionBars().setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
		
		if (part != null) {
			// 可以对菜单、工具栏等进行装饰
			IActionBarContributorDecorator[] decorators = ActionBarContributorDecoratorManager.getInstance().getDecorators(part.getSite().getId());
			
			getActionBars().getMenuManager().removeAll();
			getActionBars().getToolBarManager().removeAll();
			getActionBars().getStatusLineManager().removeAll();
			
			for (IActionBarContributorDecorator decorator : decorators) {
				decorator.contributeToMenu(this, getActionBars().getMenuManager());
				decorator.contributeToToolBar(this, getActionBars().getToolBarManager());
		        if (getActionBars() instanceof IActionBars2) {
		        	decorator.contributeToCoolBar(this, ((IActionBars2) getActionBars()).getCoolBarManager());
		        }
		        decorator.contributeToStatusLine(this, getActionBars().getStatusLineManager());
			}
			
			getActionBars().updateActionBars();
		}
	}

	@Override
	public void setActivePage(IEditorPart part) {
		
	}
	
}
