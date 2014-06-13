/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * 重构
 * @author sundl
 */
public class RefactorActionProvider extends CommonActionProvider{

	private RefactorActionGroup refactorGroup;
	
	public void fillActionBars(IActionBars actionBars) {
		if (refactorGroup != null) {
			refactorGroup.fillActionBars(actionBars);
			refactorGroup.retargetFileMenuActions(actionBars);
		}
	}

	public void fillContextMenu(IMenuManager menu) {
		if (refactorGroup != null) {
			refactorGroup.fillContextMenu(menu);
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
				refactorGroup= new RefactorActionGroup(viewPart);
			}
		}
	}

	public void setContext(ActionContext context) {
		if (refactorGroup != null) {
			refactorGroup.setContext(context);
		}
	}

	/*
	 * @see org.eclipse.ui.actions.ActionGroup#dispose()
	 * @since 3.5
	 */
	public void dispose() {
		if (refactorGroup != null)
			refactorGroup.dispose();
		super.dispose();
	}
}
