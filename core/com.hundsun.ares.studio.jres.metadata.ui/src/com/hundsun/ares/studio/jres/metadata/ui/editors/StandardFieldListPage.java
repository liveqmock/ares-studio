/**
 * 源程序名称：StandardFieldListPage.java
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

import com.hundsun.ares.studio.jres.metadata.ui.block.StandardFieldListViewerBlock;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.ui.editor.validate.EReferenceValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class StandardFieldListPage extends AbstractMetadataFormPage {
	
	StandardFieldListViewerBlock standardFieldViewBlock;
	
	/**
	 * 标准字段列表界面
	 * @param editor
	 * @param id
	 * @param title
	 */
	public StandardFieldListPage(EMFFormEditor editor,String id, String title) {
		super(editor, id, title); 
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureValidateControl()
	 */
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ROOT));
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}

	/**
	 * @return the standardFieldViewBlock
	 */
	public StandardFieldListViewerBlock getStandardFieldViewBlock() {
		return standardFieldViewBlock;
	}

	@Override
	protected void createMetadataComposite(Composite composite,FormToolkit toolkit){
		standardFieldViewBlock = new StandardFieldListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		standardFieldViewBlock.setEditableControl(getEditableControl());
		standardFieldViewBlock.createControl(composite, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(standardFieldViewBlock);
		addPropertyListener(standardFieldViewBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(standardFieldViewBlock);
	}
	
	@Override
	public void infoChange() {
		standardFieldViewBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(standardFieldViewBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(standardFieldViewBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return standardFieldViewBlock;
	}
	
}
