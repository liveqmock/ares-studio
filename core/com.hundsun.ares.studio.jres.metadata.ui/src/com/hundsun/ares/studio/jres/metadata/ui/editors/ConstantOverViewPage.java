/**
 * 源程序名称：ConstantOverViewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.ConstantListOverviewViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *用户常量总览页
 */
public class ConstantOverViewPage extends AbstractMetadataOverviewFormPage {

	private ConstantListOverviewViewerBlock sonstantListOverviewViewerBlock;
	
	public ConstantOverViewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataOverviewFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		sonstantListOverviewViewerBlock = new ConstantListOverviewViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		sonstantListOverviewViewerBlock.setEditableControl(getEditableControl());
		sonstantListOverviewViewerBlock.createControl(body, toolkit);
		addPropertyListener(sonstantListOverviewViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(sonstantListOverviewViewerBlock);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		sonstantListOverviewViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(sonstantListOverviewViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(sonstantListOverviewViewerBlock);
		super.dispose();
	}
	
}
