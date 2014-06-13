/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.ui.action;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;

import com.hundsun.ares.studio.ui.editor.EMFFormActionBarContributor;
import com.hundsun.ares.studio.ui.editor.IActionBarContributorDecorator;

/**
 * @author gongyf
 *
 */
public class ActionBarContributorDecorator implements
		IActionBarContributorDecorator {

	/**
	 * 
	 */
	public ActionBarContributorDecorator() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IActionBarContributorDecorator#contributeToMenu(com.hundsun.ares.studio.jres.ui.form.EMFFormActionBarContributor, org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void contributeToMenu(final EMFFormActionBarContributor contributor,
			IMenuManager menuManager) {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IActionBarContributorDecorator#contributeToStatusLine(com.hundsun.ares.studio.jres.ui.form.EMFFormActionBarContributor, org.eclipse.jface.action.IStatusLineManager)
	 */
	@Override
	public void contributeToStatusLine(EMFFormActionBarContributor contributor,
			IStatusLineManager statusLineManager) {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IActionBarContributorDecorator#contributeToToolBar(com.hundsun.ares.studio.jres.ui.form.EMFFormActionBarContributor, org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void contributeToToolBar(final EMFFormActionBarContributor contributor,
			IToolBarManager toolBarManager) {
		toolBarManager.add(new ModifyWizardAction(contributor.getActiveEditor()));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IActionBarContributorDecorator#contributeToCoolBar(com.hundsun.ares.studio.jres.ui.form.EMFFormActionBarContributor, org.eclipse.jface.action.ICoolBarManager)
	 */
	@Override
	public void contributeToCoolBar(EMFFormActionBarContributor contributor,
			ICoolBarManager coolBarManager) {

	}

}
