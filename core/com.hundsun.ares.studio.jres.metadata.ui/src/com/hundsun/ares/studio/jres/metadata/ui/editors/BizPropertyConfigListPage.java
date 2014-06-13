package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.block.BizPropertyConfigListBlock;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.validate.EReferenceValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class BizPropertyConfigListPage extends AbstractMetadataFormPage {

	private BizPropertyConfigListBlock block;
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public BizPropertyConfigListPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		block = new BizPropertyConfigListBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		block.setEditableControl(getEditableControl());
		block.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(block);
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
	}

	@Override
	protected void configureValidateControl() {
		//´íÎó¼ì²é
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ROOT));
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}
	
	@Override
	protected BizPropertyConfigListBlock getViewerBlock() {
		return block;
	}
	
	@Override
	public void infoChange() {
		block.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(block);
		getEditingDomain().getCommandStack().removeCommandStackListener(block);
		super.dispose();
	}

}
