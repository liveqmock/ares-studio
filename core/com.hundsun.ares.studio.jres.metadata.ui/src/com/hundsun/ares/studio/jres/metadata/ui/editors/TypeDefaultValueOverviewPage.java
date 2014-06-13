/**
 * 源程序名称：TypeDefaultValueOverviewPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.TypeDefaultValueListOverviewViewBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author wangxh
 *	默认值总览页
 */
public class TypeDefaultValueOverviewPage extends AbstractMetadataOverviewFormPage {

	private TypeDefaultValueListOverviewViewBlock typeDefaultValueListOverviewViewBlock;
	
	public TypeDefaultValueOverviewPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataOverviewFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		typeDefaultValueListOverviewViewBlock = new TypeDefaultValueListOverviewViewBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		typeDefaultValueListOverviewViewBlock.setEditableControl(getEditableControl());
		typeDefaultValueListOverviewViewBlock.createControl(body, toolkit);
		addPropertyListener(typeDefaultValueListOverviewViewBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(typeDefaultValueListOverviewViewBlock);
	}
	
	@Override
	public void infoChange() {
		typeDefaultValueListOverviewViewBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(typeDefaultValueListOverviewViewBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(typeDefaultValueListOverviewViewBlock);
		super.dispose();
	}

}
