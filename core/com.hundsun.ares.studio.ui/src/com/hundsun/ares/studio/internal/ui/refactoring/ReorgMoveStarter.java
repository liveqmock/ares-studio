package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 启动重构向导。
 * @author sundl
 */
public class ReorgMoveStarter {

	private final ARESMoveProcessor moveProcessor;
	
	private ReorgMoveStarter(ARESMoveProcessor moveProcessor) {
		this.moveProcessor = moveProcessor;
	}
	
	public static ReorgMoveStarter create(IARESElement[] elements, Object destination) {
		ARESMoveProcessor processor = new ARESMoveProcessor(elements);
		if (!processor.verifyDestination(destination).isOK()) {
			return null;
		}
		processor.setDestination(destination);
		return new ReorgMoveStarter(processor);
	}
	
	public void run(Shell parent) {
		MoveRefactoring ref= new MoveRefactoring(moveProcessor);
		RefactoringWizard wizard = new MoveRefactoringWizard(ref);
		moveProcessor.setQueries(new ReorgQueries(wizard));
		RefactoringWizardOpenOperation op= new RefactoringWizardOpenOperation(wizard);
		try {
			op.run(parent, "移动");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
