/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control.deprecated;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;




public class TableViewerActionGroup extends ActionGroup implements ISelectionChangedListener {

	private class CopyAction extends Action {

		public CopyAction() {
			super("复制\tCtrl+C");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			component.copy();
		}

	}

	private class CutAction extends Action {

		public CutAction() {
			super("剪切\tCtrl+X");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			component.cut();
		}

	}
	private class DeleteAction extends Action {

		public DeleteAction() {
			super("删除\tDel");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			component.delete();
		}

	}
	private class InsertAction extends Action {
		public InsertAction() {
			super("插入\tInsert");
		}

		@Override
		public void run() {
			component.insert();
		}
	}
	private class PasteAction extends Action {

		public PasteAction() {
			super("粘贴\tCtrl+V");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			component.paste();
		}

	}
	private TableViewerExComponent<?> component;
	private InsertAction insert = new InsertAction();

	private CopyAction copy = new CopyAction();

	private PasteAction paste = new PasteAction();

	private CutAction cut = new CutAction();

	private DeleteAction delete = new DeleteAction();

	public TableViewerActionGroup(TableViewerExComponent<?> component) {
		super();
		this.component = component;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager menu) {

		menu.add(insert);
		menu.add(copy);
		menu.add(cut);
		menu.add(paste);
		menu.add(delete);
		updateAction();

		final TableViewer viewer = component.getTableViewer();
		final Table table = viewer.getTable();
		component.getTableViewer().addSelectionChangedListener(this);
		table.setMenu(((MenuManager) menu).createContextMenu(table));

	}

	public void selectionChanged(SelectionChangedEvent event) {
		updateAction();
	}
	private void updateAction() {
		insert.setEnabled(component.canInsert());
		copy.setEnabled(component.canCopy());
		paste.setEnabled(component.canPaste());
		cut.setEnabled(component.canCut());
		delete.setEnabled(component.canDelete());
	}
}
