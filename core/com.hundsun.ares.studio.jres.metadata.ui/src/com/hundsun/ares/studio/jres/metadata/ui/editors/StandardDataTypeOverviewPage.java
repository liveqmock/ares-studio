/**
 * 源程序名称：StandardDataTypeOverviewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.StandardDataTypeListOverviewViewerBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *标准数据类型总览页
 */
public class StandardDataTypeOverviewPage extends AbstractMetadataOverviewFormPage {

	private StandardDataTypeListOverviewViewerBlock standardDataTypeListOverviewViewerBlock;
	
	public StandardDataTypeOverviewPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		standardDataTypeListOverviewViewerBlock = new StandardDataTypeListOverviewViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		standardDataTypeListOverviewViewerBlock.setEditableControl(getEditableControl());
		standardDataTypeListOverviewViewerBlock.createControl(body, toolkit);
		addPropertyListener(standardDataTypeListOverviewViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(standardDataTypeListOverviewViewerBlock);
	}
	
	@Override
	public void infoChange() {
		standardDataTypeListOverviewViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(standardDataTypeListOverviewViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(standardDataTypeListOverviewViewerBlock);
		super.dispose();
	}
	
}
