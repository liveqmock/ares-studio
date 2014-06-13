package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 
 * @author maxh
 */
public abstract class ARESElementWizard extends Wizard implements INewWizard {

	protected IARESElement selectedElement;
	protected ElementSelectionWizardPageWithNameInput page;
	protected IWorkbench workbench;
	protected String initText_Name = "";
	protected boolean finished = false;

	public ARESElementWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * @return the page
	 */
	public ElementSelectionWizardPageWithNameInput getPage() {
		return page;
	}

	/**
	 * @return the initText_Name
	 */
	public String getInitText_Name() {
		return initText_Name;
	}

	/**
	 * @param initText_Name
	 *            the initText_Name to set
	 */
	public void setInitText_Name(String initText_Name) {
		this.initText_Name = initText_Name;
	}

	public void initByOther(IWorkbench workbench, IARESElement element) {
		selectedElement = element;
		this.workbench = workbench;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null) {
			Object selected = selection.getFirstElement();
			if (selected instanceof IARESElement) {
				selectedElement = (IARESElement) selected;
			} else if (selected instanceof IResource) {
				selectedElement = ARESCore.create((IResource) selected);
				if (selectedElement == null) {
					selectedElement = ARESCore.create(((IResource) selected).getProject());
				}
			} 
		}
		if (selectedElement == null) {
			IARESProject[] projects;
			try {
				projects = ARESCore.getModel().getARESProjects();
				if (projects.length != 0) {
					selectedElement = projects[0];
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			
		}
		this.workbench = workbench;
	}

	@Override
	public boolean performFinish() {
		finished = page.finishPage();
		return finished;
	}

	public boolean isFinished() {
		return finished;
	}
	
	public String getNewElementName() {
		return page.getNewName();
	}
	
}
