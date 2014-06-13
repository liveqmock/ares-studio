/**
 * 源程序名称：DictionaryOverViewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.DictionaryListOverviewViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *数据字典总览页
 */
public class DictionaryOverViewPage extends AbstractMetadataOverviewFormPage {

	private DictionaryListOverviewViewerBlock dictionaryListOverviewViewerBlock;
	
	public DictionaryOverViewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataOverviewFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		dictionaryListOverviewViewerBlock = new DictionaryListOverviewViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		dictionaryListOverviewViewerBlock.setEditableControl(getEditableControl());
		dictionaryListOverviewViewerBlock.createControl(body, toolkit);
		addPropertyListener(dictionaryListOverviewViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(dictionaryListOverviewViewerBlock);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		dictionaryListOverviewViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(dictionaryListOverviewViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(dictionaryListOverviewViewerBlock);
		super.dispose();
	}
	
}
