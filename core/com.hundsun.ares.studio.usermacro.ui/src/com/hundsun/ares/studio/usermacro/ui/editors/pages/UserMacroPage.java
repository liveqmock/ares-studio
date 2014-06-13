/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.usermacro.ui.editors.pages;

import java.util.EventObject;

import org.apache.log4j.Logger;
import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;

/**
 * @author qinyuan
 *
 */
public class UserMacroPage extends EMFFormPage{

	private static final Logger logger = Logger.getLogger(UserMacroPage.class); 
	private UserMacroMDBlock block;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public UserMacroPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		block = new UserMacroMDBlock(this,getEditableControl());
		block.createContent(managedForm);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		RefreshViewerJob.refresh(block.getViewer());
	}
	
	@Override
	public void infoChange() {
		block.setInput(getInfo());
		super.infoChange();
	}
}
