/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RefactoringSaveHelper;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameARESResourceWizard;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameProjectProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameSelectionState;

/**
 * 
 * @author liaogc
 */
public class RefactorProjectAction extends SelectionListenerAction{

	private ICommonViewerWorkbenchSite site;
	
	public RefactorProjectAction(ICommonViewerWorkbenchSite site) {
		super("重命名");	
		this.site = site;
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		if (selection.size() == 1) {
			Object obj = selection.getFirstElement();
			
		
			if (obj instanceof IProject){
				return ARESProject.hasARESNature((IProject)obj);
			}
			
		}
		return false;
	}

	@Override
	public void run() {
		Object obj = getStructuredSelection().getFirstElement();
		ARESRenameProcessor processor = null;
		
		if (obj instanceof IProject){
			 obj= ARESCore.create((IProject)obj);
			processor = new RenameProjectProcessor((IARESElement) obj);
		}
		RenameSelectionState state = new RenameSelectionState(obj);
		RenameRefactoring refactoring = new RenameRefactoring(processor);
		RenameARESResourceWizard wizard = new RenameARESResourceWizard(refactoring);
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		RefactoringSaveHelper refactoringSaveHelper = new RefactoringSaveHelper();
		
		
		try {
			if(refactoringSaveHelper.saveEditors(site.getShell())){
				op.run(site.getShell(), "重命名");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object newElement = processor.getNewElement();
		if (newElement != null)
			state.restore(newElement);
	}

}
