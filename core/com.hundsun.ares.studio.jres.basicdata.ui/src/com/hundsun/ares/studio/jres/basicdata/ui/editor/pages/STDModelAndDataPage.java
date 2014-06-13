/**
 * 源程序名称：StandardFieldListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.BasicDataValidateUnit;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.STDModelAndDataListViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class STDModelAndDataPage extends AbstractMetadataFormPage {
	
	private static Logger logger = Logger.getLogger(STDModelAndDataPage.class);
	
	STDModelAndDataListViewerBlock standardFieldViewBlock;
	String className;
	
	/**
	 * 标准字段列表界面
	 * @param editor
	 * @param id
	 * @param title
	 */
	public STDModelAndDataPage(EMFFormEditor editor,
			String id,
			String title,
			String className) {
		super(editor, id, title); 
		this.className = className;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureValidateControl()
	 */
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new BasicDataValidateUnit(getInfo(),IBasicDataEpacakgeConstant.Attr_Master_Items));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}

//	/**
//	 * @return the standardFieldViewBlock
//	 */
	public STDModelAndDataListViewerBlock getStandardFieldViewBlock() {
		return standardFieldViewBlock;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getInfo()
	 */
	@Override
	protected MetadataResourceData<?> getInfo() {
		return ((StandardFieldModelAndData)getEditor().getInfo()).getData();
	}

	@Override
	protected void createMetadataComposite(Composite composite,FormToolkit toolkit){
		
		standardFieldViewBlock = new STDModelAndDataListViewerBlock(this, getEditingDomain(), 
				getSite(), getEditor().getARESResource(), 
				getProblemPool(),
				className);
		
		//设定模型定义
		EPackage epackage = getInfo().eClass().getEPackage();
		standardFieldViewBlock.setEpackage(epackage);
		
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
	
	
	/**
	 * 重新创建表
	 */
	public void recreateViewerBlock(){
		EPackage epackage = getInfo().eClass().getEPackage();
		standardFieldViewBlock.setEpackage(epackage);
		standardFieldViewBlock.recreateColumns();
		standardFieldViewBlock.setInput(getInfo());
	}
	
}
