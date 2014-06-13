/**
 * 源程序名称：StandardDataTypeListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.block.StandardDataTypeListViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

public class StandardDataTypeListPage extends AbstractMetadataFormPage {
	
	private StandardDataTypeListViewerBlock standardDataTypeViewerBlock;	
	/**
	 * 标准数据类型编辑器标准数据类型界面
	 * @param editor
	 * @param id
	 * @param title
	 */
	public StandardDataTypeListPage(EMFFormEditor editor,String id,
			String title) {
		super(editor, id, title);
	}

	/**
	 * 
	 * @param body
	 * @param toolkit
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		standardDataTypeViewerBlock = new StandardDataTypeListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		standardDataTypeViewerBlock.setEditableControl(getEditableControl());
		standardDataTypeViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(standardDataTypeViewerBlock);
		addPropertyListener(standardDataTypeViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(standardDataTypeViewerBlock);
	}

	/**
	 * @return the standardDataTypeViewerBlock
	 */
	public StandardDataTypeListViewerBlock getStandardDataTypeViewerBlock() {
		return standardDataTypeViewerBlock;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#infoChange()
	 */
	@Override
	public void infoChange() {
		standardDataTypeViewerBlock.setInput(getInfo());
		super.infoChange();
	}

	@Override
	public void dispose() {
		removePropertyListener(standardDataTypeViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(standardDataTypeViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return standardDataTypeViewerBlock;
	}
	
}
