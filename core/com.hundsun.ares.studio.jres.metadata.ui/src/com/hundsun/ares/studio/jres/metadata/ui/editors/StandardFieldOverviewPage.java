/**
 * 源程序名称：StandardFieldOverviewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.StandardFieldListOverviewViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *标准字段总览页
 */
public class StandardFieldOverviewPage extends AbstractMetadataOverviewFormPage {

	private StandardFieldListOverviewViewerBlock standardFieldListOverviewViewerBlock;
	
	public StandardFieldOverviewPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataOverviewFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		standardFieldListOverviewViewerBlock = new StandardFieldListOverviewViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		standardFieldListOverviewViewerBlock.setEditableControl(getEditableControl());
		standardFieldListOverviewViewerBlock.createControl(body, toolkit);
		addPropertyListener(standardFieldListOverviewViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(standardFieldListOverviewViewerBlock);
	}
	
	@Override
	public void infoChange() {
		standardFieldListOverviewViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(standardFieldListOverviewViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(standardFieldListOverviewViewerBlock);
		super.dispose();
	}
	
}
