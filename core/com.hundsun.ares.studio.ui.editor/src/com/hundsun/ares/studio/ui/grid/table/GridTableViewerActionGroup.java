/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.table;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionGroup;

import com.hundsun.ares.studio.ui.util.ITableActionHandleProvider;

/**
 * 表格菜单提供器。
 * 
 * @author mawb
 */
public class GridTableViewerActionGroup extends ActionGroup {
	private ITableActionHandleProvider provider;

	public GridTableViewerActionGroup(ITableActionHandleProvider provider) {
		super();
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.
	 * action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager manager) {
		if (provider.canAdd()) {
			manager.add(new AddAction());
		}

		if (provider.canInsert()) {
			manager.add(new InsertAction());
		}

		if (provider.canCopy()) {
			manager.add(new Separator());
			manager.add(new CopyAction());
		}

		if (provider.canCut()) {
			manager.add(new CutAction());
		}

		if (provider.canPaste()) {
			manager.add(new PasteAction());
		}

		if (provider.canDelete()) {
			manager.add(new Separator());
			manager.add(new DeleteAction());
		}
	}

	protected class AddAction extends Action {

		public AddAction() {
			super(Messages.getString("grid.table.action.add")); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			provider.add();
		}
	}

	protected class CopyAction extends Action {

		public CopyAction() {
			super(Messages.getString("grid.table.action.copy")); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			provider.copy();
		}

	}

	protected class CutAction extends Action {

		public CutAction() {
			super(Messages.getString("grid.table.action.cut")); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			provider.cut();
		}

	}

	protected class PasteAction extends Action {

		public PasteAction() {
			super(Messages.getString("grid.table.action.paste")); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			provider.paste();
		}

	}

	protected class DeleteAction extends Action {

		public DeleteAction() {
			super(Messages.getString("grid.table.action.delete")); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			provider.delete();
		}

	}

	protected class InsertAction extends Action {
		public InsertAction() {
			super(Messages.getString("grid.table.action.insert")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			provider.insert();
		}
	}
}
