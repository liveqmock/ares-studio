package com.hundsun.ares.studio.usermacro.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.usermacro.UserMacroPackage;
import com.hundsun.ares.studio.usermacro.ui.editors.pages.UserMacroPage;

/**
 * 用户自定义宏编辑器
 * @author qinyuan
 *
 */
public class UserMacroEditor extends EMFFormEditor{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return UserMacroPackage.Literals.USER_MACRO;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		
		try {
			addPage(new UserMacroPage(this, "usermacro", "宏列表编辑"));
			addPage(new RevisionHistoryListPage(this, "history", "修订信息"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
