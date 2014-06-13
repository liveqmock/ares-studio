/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * 通用模块新建向导。
 * 
 * @author mawb
 */
public class CommonModuleWizard extends Wizard implements INewWizard {
	
	protected CommonModuleCreationWizardPage page;
	protected IWorkbench workbench;
	protected Object selcetionElement;
	
	public CommonModuleWizard() {
		setWindowTitle("新建模块");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new CommonModuleCreationWizardPage("module", "新建模块");
		page.initSelection(selcetionElement);
		addPage(page);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		IARESModuleRoot root = page.getModuleRoot();
		try {
			IARESModule module = root.createModule(page.getModuleName());
			selectAndReveal(module.getResource());
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		if (selection != null) {
			selcetionElement = selection.getFirstElement();
		}
	}

	protected void selectAndReveal(IResource newResource) {
		BasicNewResourceWizard.selectAndReveal(newResource, workbench.getActiveWorkbenchWindow());
	}
	
}
