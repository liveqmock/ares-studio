/**
 * 源程序名称：ConstantListPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.ConstantListViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
/**
 * 用户常量编辑器详细界面
 *
 */
public class ConstantListPage extends AbstractMetadataFormPage {

	private ConstantListViewerBlock constantListViewerBlock;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ConstantListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * @return the constantListViewerBlock
	 */
	public ConstantListViewerBlock getConstantListViewerBlock() {
		return constantListViewerBlock;
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		constantListViewerBlock = new ConstantListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		constantListViewerBlock.setEditableControl(getEditableControl());
		constantListViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(constantListViewerBlock);
		addPropertyListener(constantListViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(constantListViewerBlock);
	}

	@Override
	public void infoChange() {
		constantListViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(constantListViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(constantListViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return constantListViewerBlock;
	}
	
}
