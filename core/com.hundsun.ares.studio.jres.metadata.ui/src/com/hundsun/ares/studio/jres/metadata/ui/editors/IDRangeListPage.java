package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.block.IDRangeListViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

public class IDRangeListPage extends AbstractMetadataFormPage {

	private IDRangeListViewerBlock block;

	public IDRangeListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		block = new IDRangeListViewerBlock(this, getEditingDomain(), getSite() ,getProblemPool());
		block.setEditableControl(getEditableControl());
		block.createControl(body, toolkit);
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
		block.setInput(getEditor().getARESResource().getARESProject());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(block);
		getEditingDomain().getCommandStack().removeCommandStackListener(block);
		super.dispose();
	}
}
