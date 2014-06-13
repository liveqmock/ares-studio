package com.hundsun.ares.studio.jres.obj.ui.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.ui.editor.validate.EReferenceValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class StdObjListPage extends AbstractMetadataFormPage {
	
	private StdObjListViewerBlock block;

	public StdObjListPage(EMFFormEditor editor) {
		super(editor, "stdobj", "对象标准字段");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureValidateControl()
	 */
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ROOT));
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}

	@Override
	protected void createMetadataComposite(Composite composite,FormToolkit toolkit){
		block = new StdObjListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		block.setEditableControl(getEditableControl());
		block.createControl(composite, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(block);
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
	}

	@Override
	protected ColumnViewerBlock getViewerBlock() {
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
