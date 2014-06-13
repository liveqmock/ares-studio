/**
 * 源程序名称：TypeDefaultValueListPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.TypeDefaultValueListViewBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

public class TypeDefaultValueListPage extends AbstractMetadataFormPage {

	private TypeDefaultValueListViewBlock typeDefaultValueListViewBlock;
	
	/**
	 * 默认值编辑器界面
	 * @param editor
	 * @param id
	 * @param title
	 */
	public TypeDefaultValueListPage(EMFFormEditor editor,String id, String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		typeDefaultValueListViewBlock = new TypeDefaultValueListViewBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		typeDefaultValueListViewBlock.setEditableControl(getEditableControl());
		typeDefaultValueListViewBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(typeDefaultValueListViewBlock);
		addPropertyListener(typeDefaultValueListViewBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(typeDefaultValueListViewBlock);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#infoChange()
	 */
	@Override
	public void infoChange() {
		typeDefaultValueListViewBlock.setInput(getInfo());
		super.infoChange();
	}

	/**
	 * @return the typeDefaultValueListViewBlock
	 */
	public TypeDefaultValueListViewBlock getTypeDefaultValueListViewBlock() {
		return typeDefaultValueListViewBlock;
	}
	
	@Override
	public void dispose() {
		removePropertyListener(typeDefaultValueListViewBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(typeDefaultValueListViewBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return typeDefaultValueListViewBlock;
	}
	
}
