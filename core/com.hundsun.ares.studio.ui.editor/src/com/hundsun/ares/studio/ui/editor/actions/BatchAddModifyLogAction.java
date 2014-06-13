package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.editor.wizard.BatchAddModifyLogWizard;

public class BatchAddModifyLogAction implements IObjectActionDelegate {

	IARESProject project;
	IWorkbenchPart part;
	@Override
	public void run(IAction action) {
		if(project != null && part != null){
			BatchAddModifyLogWizard wizard = new BatchAddModifyLogWizard(project);
			WizardDialog wd = new WizardDialog(part.getSite().getShell(), wizard);
			Point point = new Point(640, 480);
			wd.setMinimumPageSize(point);
			wd.setPageSize(point);
			wd.create();
			wd.open();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		project = null;
		Object obj = ((IStructuredSelection)selection).getFirstElement();
		if(obj instanceof IProject){
			project = ARESCore.create((IProject)obj);
		}else if(obj instanceof IARESProject){
			project = (IARESProject)obj;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart part) {
		this.part = part;
	}

}
