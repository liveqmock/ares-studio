package com.hundsun.ares.studio.jres.clearinghouse.ui.page;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.jres.clearinghouse.ui.action.ModifyWizardAction;
import com.hundsun.ares.studio.jres.database.ui.editors.DBTableColumnAndIndexPage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.extendpoint.manager.IExtendedPage;

public class HidenExtendedPage implements IExtendedPage {

	EMFFormEditor editor;
	
	private boolean added = false;
	
	@Override
	public boolean shouldLoad() {
		return false;
	}

	@Override
	public void init(FormEditor editor) {
		this.editor = (EMFFormEditor) editor;
	}

	@Override
	public void onCreate() {
		this.editor.addPageChangedListener(new IPageChangedListener() {
			@Override
			public void pageChanged(PageChangedEvent event) {
				if (added)
					return;
				
				Object page = event.getSelectedPage();
				if (page instanceof DBTableColumnAndIndexPage) {
					FormPage formPage = (FormPage) page;
					formPage.getManagedForm().getForm().getForm().setToolBarVerticalAlignment(SWT.RIGHT);
					IToolBarManager toolbar = formPage.getManagedForm().getForm().getToolBarManager();
					toolbar.add(new ModifyWizardAction(editor));
					toolbar.update(true);
					added = true;
				}
			}
		});
	}

	@Override
	public void beforeSave() {

	}

	@Override
	public void afterSave() {

	}

}
