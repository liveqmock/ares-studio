package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import com.hundsun.ares.studio.core.IARESElement;

public class ARESOpenActionProvider extends CommonActionProvider {

	private OpenAndExpandARESElementAction openAction;
	private ICommonViewerWorkbenchSite viewSite;
	
	public void init(ICommonActionExtensionSite aConfig) {
		if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			viewSite = (ICommonViewerWorkbenchSite) aConfig.getViewSite();
			openAction = new OpenAndExpandARESElementAction(viewSite.getPage(),(TreeViewer) aConfig.getStructuredViewer());
			viewSite.getSelectionProvider().addSelectionChangedListener(openAction);
		}
	}

	@Override
	public void fillContextMenu(IMenuManager aMenu) {
		if(getContext().getSelection().isEmpty()) {
			return;
		}
		
		IStructuredSelection selection = (IStructuredSelection)getContext().getSelection();
		openAction.updateSelection(selection);
//		if(openAction.isEnabled()) {
//			aMenu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openAction);
//		}
		addOpenWithMenu(aMenu);
	}
	
	@Override
	public void fillActionBars(IActionBars theActionBars) {

		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (selection.size() == 1 && selection.getFirstElement() instanceof IARESElement) {
			openAction.updateSelection(selection);
			theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
		}

	}
	
	private void addOpenWithMenu(IMenuManager aMenu) {
		IStructuredSelection ss = (IStructuredSelection) getContext().getSelection();

		if (ss == null || ss.size() != 1) {
			return;
		}

		Object o = ss.getFirstElement();

		IAdaptable openable = null;
		
		if(o instanceof IARESElement) {
			IResource r = ((IARESElement)o).getResource();
			if(r != null && r.getType() == IResource.FILE) {
				openable = (IAdaptable)o;
			}
		}

		if (openable != null) {
			// Create a menu flyout.
			IMenuManager submenu = new MenuManager("打开方式", ICommonMenuConstants.GROUP_OPEN_WITH);
			submenu.add(new GroupMarker(ICommonMenuConstants.GROUP_TOP));
			submenu.add(new OpenWithMenu(viewSite.getPage(), openable));
			submenu.add(new GroupMarker(ICommonMenuConstants.GROUP_ADDITIONS));

			// Add the submenu.
			if (submenu.getItems().length > 2 && submenu.isEnabled()) {
				aMenu.appendToGroup(ICommonMenuConstants.GROUP_OPEN_WITH, submenu);
			}
		}
	}

	
	@Override
	public void dispose() {
		super.dispose();
		viewSite.getSelectionProvider().removeSelectionChangedListener(openAction);
	}
	
}
