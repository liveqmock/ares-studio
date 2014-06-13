/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionGroup;

/**
 * 配置ResPath相关的ActionGroup
 * @author sundl
 */
public class ResPathActionGroup extends ActionGroup {
	
	public static final String MENU_ID = "com.hundsun.ares.stuido.ui.menu.referencedlib";
	
	protected AddSelectedFileToResPathAction addToResPathAction;
	protected RemoveFromResPathAction removeFromRespathAction;
	protected AddWorkspaceFileToResPathAction addWorkspaceFileAction;
	protected AddExternalFileToResPathAction addExternalFileAction;
	protected AddProjectToResPathAction addProjectAction;
	
	private List<IAction> actions = new ArrayList<IAction>();
	
	private IViewPart part;
	
	public ResPathActionGroup(IViewPart part) {
		this.part = part;
		addToResPathAction = new AddSelectedFileToResPathAction();
		removeFromRespathAction = new RemoveFromResPathAction();
		addWorkspaceFileAction = new AddWorkspaceFileToResPathAction(part.getSite().getShell());
		addExternalFileAction = new AddExternalFileToResPathAction(part.getSite().getShell());
		addProjectAction = new AddProjectToResPathAction(part.getSite().getShell());
	}
	
	@Override
    public void fillContextMenu(final IMenuManager menu) {
		super.fillContextMenu(menu);
		
    	final IStructuredSelection selection = (IStructuredSelection)getContext().getSelection();
    	addToResPathAction.selectionChanged(selection);
    	removeFromRespathAction.selectionChanged(selection);    	
    	addExternalFileAction.selectionChanged(selection);
    	addProjectAction.selectionChanged(selection);
    	addWorkspaceFileAction.selectionChanged(selection);
    	
    	if (addWorkspaceFileAction.isEnabled() && !selectionIsProject(selection))
    		menu.add(addWorkspaceFileAction);
    	if (addExternalFileAction.isEnabled() && !selectionIsProject(selection))
    		menu.add(addExternalFileAction);
    	if (addProjectAction.isEnabled() && !selectionIsProject(selection))
    		menu.add(addProjectAction);
    	
    	final IMenuManager subMenu = new MenuManager("引用", MENU_ID);
    	subMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillSubMenu(manager, selection);
			}
		});
    	subMenu.setRemoveAllWhenShown(true);
    	int added = fillSubMenu(subMenu, selection);
    	
    	if (added > 0) 
    		menu.appendToGroup("group.reorganize", subMenu); 
    }
	
	protected boolean selectionIsProject(IStructuredSelection selection) {
		return selection.getFirstElement() instanceof IProject;
	}
	
	protected int fillSubMenu(IMenuManager menu, IStructuredSelection selection) {
		int added = 0;
		if (addToResPathAction.isEnabled()) {
			menu.add(addToResPathAction);
			added++;
		}
		if (removeFromRespathAction.isEnabled()) {
			menu.add(removeFromRespathAction);
			added++;
		}
		if (addWorkspaceFileAction.isEnabled() && selectionIsProject(selection)) {
			menu.add(addWorkspaceFileAction);
			added++;
		}
		if (addExternalFileAction.isEnabled() && selectionIsProject(selection)) {
			menu.add(addExternalFileAction);
			added++;
		}
		if (addProjectAction.isEnabled() && selectionIsProject(selection)) {
			menu.add(addProjectAction);
			added++;
		}
			
		return added;
	}
	
}
