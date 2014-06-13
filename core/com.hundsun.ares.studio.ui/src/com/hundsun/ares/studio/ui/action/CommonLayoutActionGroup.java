package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.navigator.IExtensionStateModel;

import com.hundsun.ares.studio.ui.ARESPreferences;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 
 * @author sundl
 */
public class CommonLayoutActionGroup extends MultiActionGroup {

	public static final String LAYOUT_GROUP_NAME = "layout"; //$NON-NLS-1$

	private StructuredViewer fStructuredViewer;
	
	private boolean fHasContributedToViewMenu = false;
	private IAction fHierarchicalLayout = null;
	private IAction fFlatLayoutAction = null;
	private IAction[] fActions;
	
	private IMenuManager fLayoutSubMenu;

	private class CommonLayoutAction extends Action implements IAction {

		private final boolean fIsFlatLayout;

		public CommonLayoutAction(boolean flat) {
			super("", AS_RADIO_BUTTON); //$NON-NLS-1$
			fIsFlatLayout = flat;
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		public void run() {
			IPreferenceStore store = ARESUI.getDefault().getPreferenceStore();
			boolean old = store.getBoolean(ARESPreferences.FLATLAYOUT);
			if (old != fIsFlatLayout) {
				store.setValue(ARESPreferences.FLATLAYOUT, fIsFlatLayout);
				
				fStructuredViewer.getControl().setRedraw(false);
				try {
					fStructuredViewer.refresh();
				} finally {
					fStructuredViewer.getControl().setRedraw(true);
				}
			}
		}
	}

	public CommonLayoutActionGroup(StructuredViewer structuredViewer,
			IExtensionStateModel stateModel) {
		super();
		fStructuredViewer = structuredViewer;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ActionGroup#fillActionBars(IActionBars)
	 */
	public void fillActionBars(IActionBars actionBars) {
		if (!fHasContributedToViewMenu) {

			IMenuManager viewMenu = actionBars.getMenuManager();
			// Create layout sub menu
			if (fLayoutSubMenu == null) {
				fLayoutSubMenu = new MenuManager("Module Layout");
				addActions(fLayoutSubMenu);

				viewMenu.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, new Separator(LAYOUT_GROUP_NAME));
			}

			viewMenu.appendToGroup(LAYOUT_GROUP_NAME, fLayoutSubMenu);

			fHasContributedToViewMenu = true;
		}
	}

	public void unfillActionBars(IActionBars actionBars) {
		if (fHasContributedToViewMenu) {
			// Create layout sub menu
			if (fLayoutSubMenu != null) {
				actionBars.getMenuManager().remove(fLayoutSubMenu);
				fLayoutSubMenu.dispose();
				fLayoutSubMenu= null;
			}

			fHasContributedToViewMenu = false;
		}
	}
	private IAction[] createActions() {

		fFlatLayoutAction = new CommonLayoutAction(true);
		fFlatLayoutAction.setText("Flat");

		fHierarchicalLayout = new CommonLayoutAction(false);
		fHierarchicalLayout.setText("Hierarchical");

		return new IAction[] { fFlatLayoutAction, fHierarchicalLayout };
	}

	public void setFlatLayout(boolean flatLayout) {
		if (fActions == null) {
			fActions = createActions();
			
			// indicates check the flat action
			setActions(fActions, flatLayout ? 0	: 1);
		}
		fHierarchicalLayout.setChecked(!flatLayout);
		fFlatLayoutAction.setChecked(flatLayout); 
	}
}
