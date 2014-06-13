/**
 * 源程序名称：ErrorOverviewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.ErrorNoListOverviewViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *错误号总览页
 */
public class ErrorOverviewPage extends AbstractMetadataOverviewFormPage {

	private ErrorNoListOverviewViewerBlock errorNoListOverviewViewerBlock;
	
	public ErrorOverviewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		errorNoListOverviewViewerBlock = new ErrorNoListOverviewViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		errorNoListOverviewViewerBlock.setEditableControl(getEditableControl());
		errorNoListOverviewViewerBlock.createControl(body, toolkit);
		addPropertyListener(errorNoListOverviewViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(errorNoListOverviewViewerBlock);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		errorNoListOverviewViewerBlock.setInput(getInfo());
		super.infoChange();
		
	}
	
	@Override
	public void dispose() {
		removePropertyListener(errorNoListOverviewViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(errorNoListOverviewViewerBlock);
		super.dispose();
	}
	
}
