/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.ui.refactoring.ARESMoveProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.MoveRefactoringWizard;
import com.hundsun.ares.studio.internal.ui.refactoring.ReorgQueries;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

/**
 * 
 * @author sundl
 */
public class AresMoveAction extends SelectionListenerAction {

	private IWorkbenchSite site;

	protected AresMoveAction(IWorkbenchSite site) {
		super("移动");
		this.site = site;
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		Object[] resObjects = getSelectedResources().toArray();
		IARESElement[] elements = ARESElementUtil.toARESElement(resObjects);
		// selection must be all IARESElement
		if (resObjects.length != elements.length)
			return false;

		if (!RefactoringUtil.canMove(elements))
			return false;
		
		return true;
	}

	@Override
	public void run() {
		Object[] resObjects = getSelectedResources().toArray();
		IARESElement[] elements = ARESElementUtil.toARESElement(resObjects);
		ARESMoveProcessor processor = new ARESMoveProcessor(elements);
		MoveRefactoring refactoring = new MoveRefactoring(processor);
		MoveRefactoringWizard wizard = new MoveRefactoringWizard(refactoring);
		processor.setQueries(new ReorgQueries(wizard));
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		try {
			op.run(site.getShell(), "重命名");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
