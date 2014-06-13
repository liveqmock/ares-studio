/**
 * 源程序名称：AbstractMetadataFormPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.util.Map;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

/**
 * 预览页基类
 * 
 * @author yanwj06282
 *
 */
public abstract class AbstractMetadataOverviewFormPage extends EMFFormPage {

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AbstractMetadataOverviewFormPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#validate()
	 */
	@Override
	public void validate() {
		
	}
	
	//获取元数据列表
	protected MetadataResourceData<?> getInfo() {
		return (MetadataResourceData<?>) getEditor().getInfo();
	}
	
	//创建脚本上下文
	protected Map<String, Object> createScriptContext() {
		return ScriptUtils.createDefaultScriptContext(ScriptUtils.MODE_EDITOR_BUTTON,null, getEditor().getARESResource(), getInfo(), getClass().getClassLoader());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {

		Composite body = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		createMetadataComposite(body, toolkit);
		body.setLayout(new FillLayout());
		
	}

	/**
	 * 构件编辑区域
	 * 
	 * @param body
	 * @param toolkit
	 */
	protected abstract void createMetadataComposite(Composite body, FormToolkit toolkit);
	
}
