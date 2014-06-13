/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.jres.metadata.ui.block.DictionaryItemListBlock;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

/**
 * @author liaogc
 *
 */
public class DictionaryItemListPage extends AbstractMetadataFormPage{

	
	private DictionaryItemListBlock dictionaryDetailViewerBlock;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DictionaryItemListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
/*	*//**
	 * @return the dictionaryListViewerBlock
	 *//*
	public DictionaryListViewerBlock getDictionaryListViewerBlock() {
		return dictionaryListViewerBlock;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {

		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		dictionaryDetailViewerBlock = new DictionaryItemListBlock(getEditingDomain(), getEditor().getARESResource(),(DictionaryList) getEditor().getInfo(), getProblemPool());
		dictionaryDetailViewerBlock.setEditableControl(getEditableControl());
		dictionaryDetailViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(dictionaryDetailViewerBlock);
		
		addPropertyListener(dictionaryDetailViewerBlock);
	    getEditingDomain().getCommandStack().addCommandStackListener(dictionaryDetailViewerBlock);
	}

	@Override
	public void infoChange() {
		dictionaryDetailViewerBlock.setInput(getEditor().getARESResource());
		super.infoChange();
	}
	

	@Override
	public void dispose() {
		
		removePropertyListener(dictionaryDetailViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(dictionaryDetailViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return dictionaryDetailViewerBlock;
	}
	


}
