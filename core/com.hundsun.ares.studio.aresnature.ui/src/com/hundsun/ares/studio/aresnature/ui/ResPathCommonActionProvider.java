package com.hundsun.ares.studio.aresnature.ui;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import com.hundsun.ares.studio.ui.action.ResPathActionGroup;


public class ResPathCommonActionProvider extends CommonActionProvider {

	private ResPathActionGroup respathGroup;

	public void fillActionBars(IActionBars actionBars) {
		if (respathGroup != null) {
			respathGroup.fillActionBars(actionBars);
		}
	}
	
	public void fillContextMenu(IMenuManager menu) {
		if (respathGroup != null) {
			respathGroup.fillContextMenu(menu);
		}
	}

	public void init(ICommonActionExtensionSite site) {
		ICommonViewerWorkbenchSite workbenchSite= null;
		if (site.getViewSite() instanceof ICommonViewerWorkbenchSite)
			workbenchSite= (ICommonViewerWorkbenchSite) site.getViewSite();

		// we only initialize the refactor group when in a view part
		// (required for the constructor)
		if (workbenchSite != null) {
			if (workbenchSite.getPart() != null && workbenchSite.getPart() instanceof IViewPart) {
				IViewPart viewPart= (IViewPart) workbenchSite.getPart();
				respathGroup= new ResPathActionGroup(viewPart);
			}
		}
	}

	public void setContext(ActionContext context) {
		if (respathGroup != null) {
			respathGroup.setContext(context);
		}
	}

	/*
	 * @see org.eclipse.ui.actions.ActionGroup#dispose()
	 * @since 3.5
	 */
	public void dispose() {
		if (respathGroup != null) 
			respathGroup.dispose();
		super.dispose();
	}
}
