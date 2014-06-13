package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;

public class BasicDataExtendPage extends DataBindingFormPage {

	private Text txtExtend;
	
	public BasicDataExtendPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void doDataBingingOnControls() {
		bingText(txtExtend, getInfo(), BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__EXTEND);
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		txtExtend = toolkit.createText(composite, "", SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(txtExtend);
	}

}
