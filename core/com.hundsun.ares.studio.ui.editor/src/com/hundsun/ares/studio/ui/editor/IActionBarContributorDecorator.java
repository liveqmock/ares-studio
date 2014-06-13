/**
 * 
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;

/**
 * @author gongyf
 *
 */
public interface IActionBarContributorDecorator {
    public void contributeToMenu(EMFFormActionBarContributor contributor, IMenuManager menuManager);
    public void contributeToStatusLine(EMFFormActionBarContributor contributor, IStatusLineManager statusLineManager);
    public void contributeToToolBar(EMFFormActionBarContributor contributor, IToolBarManager toolBarManager);
    public void contributeToCoolBar(EMFFormActionBarContributor contributor, ICoolBarManager coolBarManager);
}
