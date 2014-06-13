/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.model.ICreateInstance;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.grid.table.GridTableDeleteItemOperation;
import com.hundsun.ares.studio.ui.grid.table.GridTableViewerExComponent;

/**
 * ARES复制粘贴操作提供器。
 * 
 * @author mawb
 */
public class AresTableActionHandleProvider<T> implements ITableActionHandleProvider {
	private GridTableViewerExComponent<T> component;
	private T data;
	private boolean isActivate = true;

	public AresTableActionHandleProvider(GridTableViewerExComponent<T> component, T data) {
		this.component = component;
		this.data = data;
		isActivate = !component.isReadOnly();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#canEdit()
	 */
	public boolean isActivate() {
		return isActivate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#setActivate
	 * (java.lang.Boolean)
	 */
	public void setActivate(Boolean isActivate) {
		this.isActivate = isActivate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#canAdd()
	 */
	public boolean canAdd() {
		return isActivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#add()
	 */
	public void add() {
		List adds = new ArrayList();
		adds.add(component.createNewObject());
		component.add(adds, component.getInput().size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#canDelete()
	 */
	public boolean canDelete() {
		if (isActivate()) {
			if (!component.getViewer().getSelection().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#delete()
	 */
	public void delete() {
		if (component.isConfirmDel()) {
			boolean confirmed = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "", "确实要删除吗?");
			if (!confirmed) {
				return;
			}
		}
		deleteWithOutConfirmed();
	}

	protected void delete(List items) {
		if (items.size() > 0) {
			GridTableDeleteItemOperation delete = new GridTableDeleteItemOperation("delete", component, items);
			delete.addContext(component.getUndoContext());
			try {
				AbstractHSFormEditor.getOperationHistory().execute(delete, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 不弹出用户确认菜单就删除
	 */
	public void deleteWithOutConfirmed() {
		StructuredSelection selection = (StructuredSelection) component.getViewer().getSelection();
		if (selection != null) {
			// List needremove = selection.toList();
			// needremove.remove(lastLine);
			int oldsize = component.getInput().size();
			delete(selection.toList());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#canInsert()
	 */
	public boolean canInsert() {
		if (isActivate()) {
			ISelection selection = component.getViewer().getSelection();
			if (!selection.isEmpty()) {
				return ((StructuredSelection) selection).size() == 1;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#insert()
	 */
	public void insert() {
		IStructuredSelection selection = (IStructuredSelection) component.getViewer().getSelection();
		int index = component.getInput().indexOf(selection.getFirstElement());
		List adds = new ArrayList();
		adds.add(component.createNewObject());
		if (index == -1) {
			component.add(adds, component.getInput().size());
		} else {
			component.add(adds, index);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ICopyPasteProvider#canCopy()
	 */
	public boolean canCopy() {
		if (isActivate()) {
			// 只有可以克隆，复制才有意义
			if (data instanceof ICreateInstance) {
				return !component.getViewer().getSelection().isEmpty();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ICopyPasteProvider#copy()
	 */
	public void copy() {
		List<Object> copyList = new ArrayList<Object>();
		for (Object obj : ((StructuredSelection) component.getViewer().getSelection()).toList()) {
			copyList.add(((ICreateInstance) obj).getNewInstance());
		}
		Clipboard.instance.setData(copyList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#canCut()
	 */
	public boolean canCut() {
		if (isActivate()) {
			return canCopy() && canDelete();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ITableActionHandleProvider#cut()
	 */
	public void cut() {
		copy();
		deleteWithOutConfirmed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ICopyPasteProvider#canPaste()
	 */
	public boolean canPaste() {
		if (isActivate()) {
			// 只有可以克隆，粘贴才有意义
			if (data instanceof ICreateInstance) {
				// 需要要相同类型
				Object obj = Clipboard.instance.getData();
				if (obj != null && obj instanceof List) {
					// 空数据无粘贴意义
					if (!((List) obj).isEmpty()) {
						if (((List) obj).get(0).getClass() == data.getClass()) {
							// 只有同类型的可复制，粘贴
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.ui.util.ICopyPasteProvider#paste()
	 */
	public void paste() {
		StructuredSelection selection = (StructuredSelection) component.getViewer().getSelection();
		int index = component.getInput().size();
		if (!selection.isEmpty()) {
			index = component.getInput().indexOf(selection.getFirstElement());
			if (index == -1) {
				index = component.getInput().size();
			}
		}

		List<T> added = new ArrayList<T>();
		for (Object obj : (List) Clipboard.instance.getData()) {
			added.add((T) ((ICreateInstance) obj).getNewInstance());
		}

		component.add(added, index);
	}

}
