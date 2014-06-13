package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public class ActionGroup extends Action {

	protected IAction[] actions;
	private MenuManager manager;
	
	private IMenuCreator menuCreator = new IMenuCreator() {
		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}
		
		@Override
		public Menu getMenu(Control parent) {
			if (manager == null) {
				manager = new MenuManager();
				manager.setRemoveAllWhenShown(true);
				manager.addMenuListener(new IMenuListener() {
					@Override
					public void menuAboutToShow(IMenuManager manager) {
						for (IAction a : actions) {
							manager.add(a);
						}
					}
				});
				manager.createContextMenu(parent);
			}
			return manager.getMenu();
		}
		
		@Override
		public void dispose() {
			if (manager != null) {
				manager.dispose();
			}
		}
	};
	
	/**
	 * 包含的Action，不能为空
	 * @param actions
	 */
	public ActionGroup(IAction[] actions) {
		super("", AS_DROP_DOWN_MENU);
		this.actions = actions;
		if (actions != null && actions.length > 0) {
			setText(actions[0].getText());
			setToolTipText(actions[0].getText());
			setImageDescriptor(actions[0].getImageDescriptor());
		}
	}

	@Override
	public IMenuCreator getMenuCreator() {
		return menuCreator;
	}

	@Override
	public void run() {
		if (actions != null && actions.length > 0) {
			actions[0].run();
		}
	}
	
}
