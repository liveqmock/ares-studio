/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.ui.wizard;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.wizard.Wizard;

import com.hundsun.ares.studio.jres.clearinghouse.ui.page.HisModifyTypeChosePage;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author qinyuan
 *
 */
public class HisModifyWizard extends Wizard{
	
	HisModifyTypeChosePage page;
	EMFFormEditor editorPart;

	/**
	 * @param editorPart
	 */
	public HisModifyWizard(EMFFormEditor editorPart) {
		this.editorPart = editorPart;
		setWindowTitle(IStock3Constant.TABLE_CHANGE_TITLE);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			Command command = page.getWizardComposite().getCommand();
			editorPart.getEditingDomain().getCommandStack().execute(command);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new HisModifyTypeChosePage("表修改类型",editorPart);
		addPage(page);
	}
}
