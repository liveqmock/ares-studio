/**
 * 源程序名称：ErrorNoListPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.ErrorNoListViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

/**
 * 错误号编辑器的详细界面
 *
 */
public class ErrorNoListPage extends AbstractMetadataFormPage {

	private ErrorNoListViewerBlock errorNoListViewerBlock;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ErrorNoListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		errorNoListViewerBlock = new ErrorNoListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		errorNoListViewerBlock.setEditableControl(getEditableControl());
		errorNoListViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(errorNoListViewerBlock);
		addPropertyListener(errorNoListViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(errorNoListViewerBlock);
	}
	
	@Override
	public void infoChange() {
		errorNoListViewerBlock.setInput(getInfo());
		super.infoChange();
	}

	/**
	 * @return the errorNoListViewerBlock
	 */
	public ErrorNoListViewerBlock getErrorNoListViewerBlock() {
		return errorNoListViewerBlock;
	}
	
	@Override
	public void dispose() {
		removePropertyListener(errorNoListViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(errorNoListViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return errorNoListViewerBlock;
	}
	
}
