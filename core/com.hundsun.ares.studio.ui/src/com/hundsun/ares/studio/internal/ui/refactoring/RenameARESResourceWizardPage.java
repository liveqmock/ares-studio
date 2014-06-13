/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.RefactoringStatus;

/**
 * 重命名资源向导页面
 * @author sundl
 */
public class RenameARESResourceWizardPage extends RenameInputWizardPage {

	public RenameARESResourceWizardPage(String description, String initialValue) {
		super(description, initialValue);
	}

	protected RefactoringStatus validateTextField(String text) {
		INameUpdating updating = (INameUpdating)getRefactoring().getAdapter(INameUpdating.class);
		
		if (updating != null) {
			updating.setNewElementName(text);
			return updating.checkNewElementName(text);
		}
			
		return null;
	}
	
}
