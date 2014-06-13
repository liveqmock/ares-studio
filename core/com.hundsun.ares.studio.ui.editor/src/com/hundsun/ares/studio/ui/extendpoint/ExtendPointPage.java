/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.ui.page.SectionScrolledFormPage;


public class ExtendPointPage extends SectionScrolledFormPage{

	ExtendPointPageHelper helper;
	
	public ExtendPointPage(FormEditor editor,ExtendPointPageHelper helper) {
		super(editor, helper.getPageId(), helper.getPageName());
		this.helper = helper;
	}

	public ExtendPointPageHelper getHelper() {
		return helper;
	}
	
	@Override
	protected void createSections(IManagedForm managedForm) {
		// TODO Auto-generated method stub
		helper.createSections(this,managedForm);
	}

}
