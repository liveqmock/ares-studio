/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.tree;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.ui.actions.ActionGroup;

public class GridTreeViewerActionGroup extends ActionGroup implements ISelectionChangedListener {
	protected GridTreeViewerExComponent<?> component;
	protected Action insert = new InsertAction();
	protected Action copy = new CopyAction();
	protected Action paste = new PasteAction();
	protected Action cut = new CutAction();
	protected Action delete = new DeleteAction();
	protected IMenuManager menu;

	public GridTreeViewerActionGroup(GridTreeViewerExComponent<?> component) {
		super();
		this.component = component;
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

		this.menu = menu;

		addActions();

		updateAction(null);

		final Grid tree = component.getTreeViewer().getGrid();
		component.getTreeViewer().addSelectionChangedListener(this);
		tree.setMenu(((MenuManager) menu).createContextMenu(tree));
	}

	protected void addActions() {
		menu.add(insert);
		menu.add(new Separator());
		menu.add(copy);
		menu.add(cut);
		menu.add(paste);
		menu.add(new Separator());
		menu.add(delete);
	}

	/**
	 * 注意event有可能为null
	 * 
	 * @param event
	 */
	protected void updateAction(SelectionChangedEvent event) {
		insert.setEnabled(component.canInsert());
		copy.setEnabled(component.canCopy());
		paste.setEnabled(component.canPaste());
		cut.setEnabled(component.canCut());
		delete.setEnabled(component.canDelete());
	}

	public void selectionChanged(SelectionChangedEvent event) {
		updateAction(event);
	}

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

}
