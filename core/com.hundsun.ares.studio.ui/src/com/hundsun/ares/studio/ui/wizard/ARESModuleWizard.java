/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 模块创建向导
 * @author sundl
 */
public class ARESModuleWizard extends Wizard implements INewWizard {

	protected IWorkbench workbench;
	protected IARESElement selection;
	protected ARESModuleWizardPage page;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return page.finishPage();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		Object obj = selection.getFirstElement();
		if (obj instanceof IARESElement) {
			this.selection = (IARESElement)obj;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.page = new ARESModuleWizardPage("模块", workbench, selection);
		addPage(page);
	}
	
}
