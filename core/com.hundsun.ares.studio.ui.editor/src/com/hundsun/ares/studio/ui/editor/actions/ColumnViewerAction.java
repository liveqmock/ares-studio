/**
 * 源程序名称：ColumnViewerAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;

public abstract class ColumnViewerAction extends Action implements IUpdateAction {

	private EditingDomain editingDomain;
	private ColumnViewer viewer;
	private Command command;
		
	public ColumnViewerAction(ColumnViewer viewer, EditingDomain editingDomain) {
		super();
		this.viewer = viewer;
		this.editingDomain = editingDomain;
		setEnabled(false);
	}
	
	public ColumnViewer getViewer() {
		return viewer;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	@Override
	public void run() {
		Command command = getCommand();
		if (command != null) {
			if (viewer.isCellEditorActive()) {
				viewer.cancelEditing();
			}
			
			getEditingDomain().getCommandStack().execute(command);
			clearCommand();

			// 让表格选择影响操作的对象
			Command mostRecentCommand = getEditingDomain().getCommandStack()
					.getMostRecentCommand();
			if (mostRecentCommand != null) {
//				setSelectionToViewer(mostRecentCommand.getAffectedObjects());
			}
		}
	}
	
	protected void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		// Make sure it's okay.
		//
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable =
				new Runnable() {
					public void run() {
						// Try to select the items in the current content viewer of the editor.
						//
						if (getViewer() != null) {
							getViewer().setSelection(new StructuredSelection(theSelection.toArray()), true);
						}
					}
				};
			Display.getDefault().asyncExec(runnable);
		}
	}
	
	protected void clearCommand() {
		if (command != null) {
			command.dispose();
			command = null;
		}
	}
	
	public Command getCommand() {
		if (command == null) {
			command = createCommand();
		}
		return command;
	}
	
	/**
	 * 不会返回null
	 * @return
	 */
	protected List<Object> getSelectedObjects() {
		ISelection selection = getViewer().getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			return ((IStructuredSelection) selection).toList();
		}
		return Collections.EMPTY_LIST;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.IUpdateAction#update()
	 */
	@Override
	public void update() {
		clearCommand();
		setEnabled(calculateEnabled());
	}
	
	protected abstract Command createCommand();
	
	protected boolean calculateEnabled() {
		return getCommand() != null && getCommand().canExecute();
	}
}
