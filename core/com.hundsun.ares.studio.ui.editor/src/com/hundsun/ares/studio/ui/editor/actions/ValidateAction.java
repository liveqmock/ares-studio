/**
 * 源程序名称：ValidateAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IEMFFormPage;

/**
 * @author wangxh
 *
 */
public class ValidateAction extends Action {
	IFormPage page;
	/**
	 * 
	 */
	public ValidateAction(IFormPage page) {
		super();
		this.page = page;
		setText("错误检查");
		setId(IActionIDConstant.CV_VALIDATE);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESEditorPlugin.PLUGIN_ID, "icons/full/obj16/validate.png"));
	}

	@Override
	public void run() {
		if(page instanceof EMFFormPage){
			((IEMFFormPage)page).validate();
		}
	}
	

}
