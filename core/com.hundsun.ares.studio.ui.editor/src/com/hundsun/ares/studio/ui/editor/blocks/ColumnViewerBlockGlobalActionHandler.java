/**
 * 源程序名称：ColumnViewerBlockGlobalActionHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.ui.editor.blocks;


import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;

import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;

/**
 * Global Action
 * @author sundl
 *
 */
public class ColumnViewerBlockGlobalActionHandler {

	private static Logger logger = Logger.getLogger(ColumnViewerBlockGlobalActionHandler.class);
	
	private IActionBars actionBars;
	private ColumnViewerBlock block;
	
	// 有一种特殊情况： 激活cell editor, 复制里面的文本，然后焦点直接切换到过滤框；
	// 在这种情况下，先收到control的deactived事件，然后，会受到editor deactived事件
	// 所以需要记录control的active状态，在editor deactive事件的处理的时候，如果控件不是actived，就不set global action
	private boolean controlActive = false;
	
	public ColumnViewerBlockGlobalActionHandler(ColumnViewerBlock block, IActionBars actionBars) {
		this.block = block;
		this.actionBars = actionBars;
		hook();
	}
	
	private void hook() {
		// active的时候，激活对应的GlobalAction
		block.getColumnViewer().getControl().addListener(SWT.Activate, new Listener() {
			@Override
			public void handleEvent(Event event) {
				logger.debug("tree/table activated");
				controlActive = true;
				setupGlobalActions();
			}
		});
		
		// deactive的时候，清空GlobalAction
		block.getColumnViewer().getControl().addListener(SWT.Deactivate, new Listener() {
			@Override
			public void handleEvent(Event event) {
				logger.debug("tree/table Deactivate");
				controlActive = false;
				clearGlobalActions();
			}
		});
		
		// 监听cell editor的激活状态，激活的时候清空GLobalAction
		block.getColumnViewer().getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {
			@Override
			public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
			}
			@Override
			public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
			}
			@Override
			public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
				logger.debug("Editor deactived...");
				boolean focus = block.getColumnViewer().getControl().isFocusControl();
				logger.debug("control active : " + focus);
				if (controlActive)
					setupGlobalActions();
			}
			@Override
			public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
				logger.debug("Editor actived...");
				if (controlActive)
					clearGlobalActions();
			}
		});
	}
	
	// 子类可以重写这个方法，添加其他的Action
	protected void setupGlobalActions() {
		IAction copyAction = block.getActionRegistry().getAction(ActionFactory.COPY.getId());
		IAction pasteAction = block.getActionRegistry().getAction(ActionFactory.PASTE.getId());
		IAction deleteAction = block.getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
		logger.debug("Global action set to copy/paste");
	}
	
	protected void clearGlobalActions() {
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), null);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), null);
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), null);
		logger.debug("Global action set to null");
	}
	
}
