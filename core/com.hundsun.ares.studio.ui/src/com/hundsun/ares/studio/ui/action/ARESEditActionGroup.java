/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.navigator.ICommonMenuConstants;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 复制，粘贴，删除
 * @author sundl
 */
public class ARESEditActionGroup extends ActionGroup {

	private Shell shell;

	private ARESDeleteAction deleteAction;
	private ARESCopyAction copyAction;
	private ARESPasteAction pasteAction;
	
	private Clipboard clipboard;

	private TextActionHandler textActionHandler;

	public ARESEditActionGroup(Shell shell) {
		this.shell = shell;
		makeActions();
	}

	private void makeActions() {
		
		clipboard = new Clipboard(shell.getDisplay());
		ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
		deleteAction = new ARESDeleteAction(shell);
		deleteAction.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		deleteAction.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		deleteAction.setActionDefinitionId("org.eclipse.ui.edit.delete");

		copyAction = new ARESCopyAction(shell);
		copyAction.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		copyAction.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		copyAction.setActionDefinitionId("org.eclipse.ui.edit.copy");
		
		pasteAction = new ARESPasteAction(shell, clipboard);
		pasteAction.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		pasteAction.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		pasteAction.setActionDefinitionId("org.eclipse.ui.edit.paste");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars
	 * )
	 */
	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (this.textActionHandler == null)
			textActionHandler = new TextActionHandler(actionBars);

		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		deleteAction.selectionChanged(selection);
		textActionHandler.setDeleteAction(deleteAction);
		actionBars.setGlobalActionHandler("org.eclipse.ui.edit.delete", deleteAction);

		copyAction.selectionChanged(selection);
		textActionHandler.setCopyAction(copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);

		pasteAction.selectionChanged(selection);
		textActionHandler.setPasteAction(pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.
	 * action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager menu) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		Object o = selection.getFirstElement();
		copyAction.selectionChanged(selection);
		if (copyAction.isEnabled() && (o instanceof IARESElement)) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, copyAction);
		}
		
		pasteAction.selectionChanged(selection);
		if (pasteAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, pasteAction);
		}

		deleteAction.selectionChanged(selection);
		if (deleteAction.isEnabled() && (o instanceof IARESElement)) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_EDIT, deleteAction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
	 */
	@Override
	public void updateActionBars() {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		deleteAction.selectionChanged(selection);
		copyAction.selectionChanged(selection);
		pasteAction.selectionChanged(selection);
	}

	@Override
	public void dispose() {
		if (clipboard != null) {
			clipboard.dispose();	
			clipboard = null;
		}
		super.dispose();
	}
	
}
