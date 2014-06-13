/**
 * 源程序名称：FormProblemPool.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IMessageManager;

import com.hundsun.ares.studio.ui.editor.validate.DefaultProblemPool;

/**
 * @author gongyf
 *
 */
public class FormProblemPool extends DefaultProblemPool {
	
	IMessageManager manager;
	
	public FormProblemPool(IMessageManager manager) {
		this.manager = manager;
		this.context.put(FormControlProblemView.KEY_MESSAGEMANAGER, manager);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.DefaultProblemPool#notifyViews()
	 */
	@Override
	protected void notifyViews() {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				boolean isAutoUpdate = manager.isAutoUpdate();
				manager.setAutoUpdate(false);
				FormProblemPool.super.notifyViews();
				manager.setAutoUpdate(isAutoUpdate);
			}
		});
	}
}
