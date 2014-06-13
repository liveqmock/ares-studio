/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.navigator.ICommonMenuConstants;

/**
 * 重构。
 * @author sundl
 */
public class RefactorActionGroup extends ActionGroup {

	private static final String RENAME_ID = "com.hundsun.ares.studio.ui.action.rename";
	private static final String GROUP_ID = ICommonMenuConstants.GROUP_REORGANIZE;
	
	public static final String MENU_ID = "com.hundsun.ares.studio.ui.menu.refactor";

	private IWorkbenchSite site;
	private IViewPart part;
	private ISelectionProvider selectionProvider;
	
	private ARESRenameAction renameAction;
	private AresMoveAction moveAction;
	
	public RefactorActionGroup(IViewPart part) {
		this(part.getSite(), null);
	}
	
	public RefactorActionGroup(IWorkbenchSite site, ISelectionProvider provider) {
		this.site = site;
		this.selectionProvider = provider == null ? site.getSelectionProvider() : provider;
		
		IStructuredSelection selection = (IStructuredSelection)selectionProvider.getSelection();
		renameAction = new ARESRenameAction(site);
		initAction(renameAction, selectionProvider, selection, "org.eclipse.ui.edit.rename");
		moveAction = new AresMoveAction(site);
		initAction(moveAction, selectionProvider, selection, "org.eclipse.ui.edit.move");
		
	}
	
	private void initAction(SelectionListenerAction action, ISelectionProvider provider, IStructuredSelection selection, String id) {
		action.setActionDefinitionId(id);
		action.selectionChanged(selection);
		if (provider != null) 
			provider.addSelectionChangedListener(action);
	}
	
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameAction);
		actionBars.setGlobalActionHandler(ActionFactory.MOVE.getId(), moveAction);
	}
	
	/**
	 * Retargets the File actions with the corresponding refactoring actions.
	 *
	 * @param actionBars the action bar to register the move and rename action with
	 */
	public void retargetFileMenuActions(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameAction);
		actionBars.setGlobalActionHandler(ActionFactory.MOVE.getId(), moveAction);
	}
	
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		addRefactorSubmenu(menu);
	}
	
	private void addRefactorSubmenu(IMenuManager menu) {
		MenuManager refactorSubmenu= new MenuManager("重构", MENU_ID);
		ISelection selection = selectionProvider.getSelection();
		renameAction.selectionChanged((IStructuredSelection)selection);
		moveAction.selectionChanged((IStructuredSelection)selection);
		if (fillRefactorMenu(refactorSubmenu) > 0) {
			menu.appendToGroup(GROUP_ID, refactorSubmenu);
		}
	}
	
	private int fillRefactorMenu(IMenuManager refactorSubmenu) {
		int added = 0;
		added+= addAction(refactorSubmenu, renameAction);
		added+= addAction(refactorSubmenu, moveAction);
		return added;
	}
	
	private int addAction(IMenuManager menu, IAction action) {
		if (action != null && action.isEnabled()) {
			menu.add(action);
			return 1;
		}
		return 0;
	}

}
