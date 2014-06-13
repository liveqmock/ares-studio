/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.jres.metadata.ui.block.GeneralDataConfigDefineBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

/**
 * @author yanyl
 * 
 */
public class GeneralDataConfigDefinePage extends EMFFormPage {

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public GeneralDataConfigDefinePage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	GeneralDataConfigDefineBlock block;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(
	 * org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		
		block=new GeneralDataConfigDefineBlock(getEditingDomain(),getEditor().getARESResource(), getProblemPool());
		
		block.setEditableControl(getEditableControl());
		block.createControl(composite, managedForm.getToolkit());
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(block);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
		
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		block.setInput(getInfo());
		super.infoChange();
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.form.EMFFormPage#configureValidateControl
	 * ()
	 */
	@Override
	protected void configureValidateControl() {
	}

}
