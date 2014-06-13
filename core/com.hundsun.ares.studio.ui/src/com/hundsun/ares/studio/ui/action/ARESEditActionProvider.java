/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class ARESEditActionProvider extends CommonActionProvider {

	private ActionGroup editGroup;
	
	public void init(ICommonActionExtensionSite anActionSite) {
		Shell shell = anActionSite.getViewSite().getShell();
		editGroup = new ARESEditActionGroup(shell);
	}
	
	public void fillActionBars(IActionBars actionBars) { 
		editGroup.fillActionBars(actionBars);
	}

	public void fillContextMenu(IMenuManager menu) { 
		editGroup.fillContextMenu(menu);
	}

	public void setContext(ActionContext context) { 
		editGroup.setContext(context);
	}

	public void updateActionBars() { 
		editGroup.updateActionBars();
	}
	
	public void dispose() {
		super.dispose();
	}
	
}
