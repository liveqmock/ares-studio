/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

/**
 * 
 * @author sundl
 */
public class MoveRefactoringWizard extends RefactoringWizard {

	private MoveRefactoring refactoring;
	
	public MoveRefactoringWizard(MoveRefactoring refactoring) {
		super(refactoring, DIALOG_BASED_USER_INTERFACE);
		setDefaultPageTitle("移动");
		this.refactoring = refactoring;
		setWindowTitle("移动");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.ui.refactoring.RefactoringWizard#addUserInputPages()
	 */
	@Override
	protected void addUserInputPages() {
		MoveAresElementWizardPage page = new MoveAresElementWizardPage("Movepage",refactoring);
		addPage(page);
	}

}
