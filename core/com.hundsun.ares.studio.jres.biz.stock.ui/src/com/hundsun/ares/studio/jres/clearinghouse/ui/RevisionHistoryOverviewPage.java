/**
 * 源程序名称：RevisionHistoryOverviewPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.uft.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;

/**
 * 修改记录总览页面
 * @author sundl
 *
 */
public class RevisionHistoryOverviewPage extends ExtendPageWithMyDirtySystem<ModuleProperty> {
	
	private RevisionHistoryOverviewBlock block;

	public RevisionHistoryOverviewPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		block = new RevisionHistoryOverviewBlock(getResource());
		block.createControl(composite, toolkit);
		block.setInput(new Object());
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
	}
	
	@Override
	public boolean shouldLoad() {
		return true;
	}

}
