/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * 
 * @author sundl
 */
public class ARESActionProvider extends CommonActionProvider {

	private static final String ARES_MENU_NAME = "common.ares.aresaction";//$NON-NLS-1$
		
	private IWorkbenchPart part;
	private ARESActionActionGroup actionGroup;
	
	public void init(ICommonActionExtensionSite anExtensionSite) {
		if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			part = ((ICommonViewerWorkbenchSite) anExtensionSite.getViewSite()).getPart();
			actionGroup = new ARESActionActionGroup(part);
		}
	}
	
	public void fillContextMenu(IMenuManager menu) {
		IMenuManager submenu = new MenuManager("ARES", ARES_MENU_NAME);
		submenu.add(new Separator());
		actionGroup.setContext(getContext());
		actionGroup.fillContextMenu(submenu);
		menu.insertAfter(ICommonMenuConstants.GROUP_ADDITIONS, submenu);
	}
}
