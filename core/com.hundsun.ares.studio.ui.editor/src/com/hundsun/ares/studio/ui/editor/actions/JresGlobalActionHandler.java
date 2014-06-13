/**
 * 源程序名称：JresGlobalActionHandler
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;

public class JresGlobalActionHandler extends ColumnViewerEditorActivationListener {

	private static final Logger logger = Logger.getLogger(JresGlobalActionHandler.class);
	
	private IAction copyAction;
	private IAction pasteAction;
	private IActionBars actionBars;
	
	public JresGlobalActionHandler(IActionBars actionBars) {
		this.actionBars = actionBars;
	}
	
	@Override
	public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
	}
	
	@Override
	public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
	}
	
	@Override
	public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
		logger.debug("Global action set...");
	}
	
	@Override
	public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
		this.copyAction = actionBars.getGlobalActionHandler(ActionFactory.COPY.getId());
		this.pasteAction = actionBars.getGlobalActionHandler(ActionFactory.PASTE.getId());
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), null);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), null);
		logger.debug("Global action cleared...");
	}

}
