/**
 * 源程序名称：PopupAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author gongyf
 *
 */
public abstract class PopupAction implements IObjectActionDelegate {

	private ISelection selection;
	private IWorkbenchPart workbenchPart;
	
	/**
	 * @return the selection
	 */
	protected ISelection getSelection() {
		return selection;
	}
	
	/**
	 * @return the workbenchPart
	 */
	protected IWorkbenchPart getWorkbenchPart() {
		return workbenchPart;
	}
	
	/**
	 * 一般在ui线程中调用
	 * @return
	 */
	protected Shell getShell() {
		if (getWorkbenchPart() != null) {
			return getWorkbenchPart().getSite().getShell();
		} else {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.workbenchPart = targetPart;
	}

}
