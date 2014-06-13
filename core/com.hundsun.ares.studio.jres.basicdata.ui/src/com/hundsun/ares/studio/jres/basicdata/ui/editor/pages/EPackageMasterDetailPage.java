package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.EPackageMasterBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.validate.EMFAllValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class EPackageMasterDetailPage extends DataBindingFormPage {

	private EPackageMasterBlock block ;
	public EPackageMasterDetailPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	protected void doDataBingingOnControls() {
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		block = new EPackageMasterBlock(this);
		block.createContent(managedForm);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
	}

	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EMFAllValidateUnit(getEditor().getInfo()));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		block.setInput(((EpacakgeDefineList)getInfo()).getItems());
		super.infoChange();
	}
	@Override
	public void dispose() {
		getEditingDomain().getCommandStack().removeCommandStackListener(block);
		super.dispose();
	}
}
