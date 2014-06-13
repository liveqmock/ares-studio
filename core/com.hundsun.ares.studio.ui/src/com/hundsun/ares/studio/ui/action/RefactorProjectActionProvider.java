/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ide.ResourceSelectionUtil;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * 
 * @author liaogc
 */
public class RefactorProjectActionProvider extends CommonActionProvider{
	
	private RefactorProjectAction refactorProjectAction;
	private ICommonViewerWorkbenchSite viewSite;
	private static String  ID="org.eclipse.ui.edit.rename";
	public static final String MENU_ID = "com.hundsun.ares.studio.ui.menu.refactorproject";




	public void init(ICommonActionExtensionSite aConfig) {
		if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			viewSite = (ICommonViewerWorkbenchSite) aConfig.getViewSite();
			refactorProjectAction = new RefactorProjectAction(viewSite);
			viewSite.getSelectionProvider().addSelectionChangedListener(refactorProjectAction);
		}
	}

	@Override
	public void fillContextMenu(IMenuManager aMenu) {
		if(getContext().getSelection().isEmpty()) {
			return;
		}
		
		IStructuredSelection selection = (IStructuredSelection)getContext().getSelection();
		refactorProjectAction.updateSelection(selection);

		boolean anyResourceSelected = !selection.isEmpty()
				&& ResourceSelectionUtil.allResourcesAreOfType(selection, IResource.PROJECT) ;

		if (anyResourceSelected) {
			
			aMenu.appendToGroup(ICommonMenuConstants.GROUP_REORGANIZE, refactorProjectAction);
			refactorProjectAction.selectionChanged(selection);
		}
	}
	
	
	
	
	@Override
	public void fillActionBars(IActionBars theActionBars) {

		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (selection.size() == 1 && selection.getFirstElement() instanceof IProject) {
			refactorProjectAction.updateSelection(selection);
			theActionBars.setGlobalActionHandler(ID, refactorProjectAction);
		}

	}
	

	
	@Override
	public void dispose() {
		super.dispose();
		viewSite.getSelectionProvider().removeSelectionChangedListener(refactorProjectAction);
	}

}
