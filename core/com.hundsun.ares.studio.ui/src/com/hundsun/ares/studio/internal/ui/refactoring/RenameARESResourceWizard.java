/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.Refactoring;

/**
 * 
 * @author sundl
 */
public class RenameARESResourceWizard extends RenameRefactoringWizard{

	public RenameARESResourceWizard(Refactoring refactoring) {
		super(refactoring);
	}

	@Override
	protected void addUserInputPages() {
		INameUpdating updating = (INameUpdating)getRefactoring().getAdapter(INameUpdating.class);
		RenameARESResourceWizardPage page = new RenameARESResourceWizardPage("重命名", updating.getCurrentElementName());
		addPage(page);
	}
	
}
