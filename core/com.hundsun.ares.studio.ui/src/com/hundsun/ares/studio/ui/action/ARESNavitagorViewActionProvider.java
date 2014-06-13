package com.hundsun.ares.studio.ui.action;

import java.util.Arrays;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.IExtensionActivationListener;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorActivationService;

import com.hundsun.ares.studio.ui.ARESPreferences;
import com.hundsun.ares.studio.ui.ARESUI;

public class ARESNavitagorViewActionProvider extends CommonActionProvider {
	
	private IActionBars actionBars;
	private ICommonActionExtensionSite extensionSite;
	private IExtensionStateModel stateModel;
	private CommonLayoutActionGroup layoutActionGroup;
	private ToggleShowCategoryAction showCategoryAction;
	private boolean hasAddedShowCateMenu;
	private String extensionId;
	private boolean enabled;

	private IExtensionActivationListener fMenuUpdater= new IExtensionActivationListener() {

		public void onExtensionActivation(String viewerId, String[] theNavigatorExtensionIds, boolean isCurrentlyActive) {

			if (extensionSite != null && actionBars != null) {

				int search= Arrays.binarySearch(theNavigatorExtensionIds, extensionId);
				if (search > -1) {
					if (isMyViewer(viewerId)) {
						if (wasEnabled(isCurrentlyActive))
							layoutActionGroup.fillActionBars(actionBars);

						else
							if (wasDisabled(isCurrentlyActive))
								layoutActionGroup.unfillActionBars(actionBars);
						// else no change 
					}
					enabled= isCurrentlyActive;
				}
			}

		}

		private boolean isMyViewer(String viewerId) {
			String myViewerId= extensionSite.getViewSite().getId();
			return myViewerId != null && myViewerId.equals(viewerId);
		}

		private boolean wasDisabled(boolean isActive) {
			return enabled && !isActive;
		}

		private boolean wasEnabled(boolean isActive) {
			return !enabled && isActive;
		}
	};
	
	public ARESNavitagorViewActionProvider() {
	}

	public void init(ICommonActionExtensionSite site) {
		this.extensionSite = site;
		this.stateModel = site.getExtensionStateModel();
		layoutActionGroup = new CommonLayoutActionGroup(site.getStructuredViewer(), stateModel);
		extensionId = site.getExtensionId();
		INavigatorActivationService activationService= extensionSite.getContentService().getActivationService();
		activationService.addExtensionActivationListener(fMenuUpdater);
//		enabled= true;
		showCategoryAction = new ToggleShowCategoryAction(stateModel, site.getStructuredViewer());
	}
	
	public void fillActionBars(IActionBars actionBars) {
		this.actionBars = actionBars;
		layoutActionGroup.fillActionBars(actionBars);
		if (!hasAddedShowCateMenu) {
			this.actionBars.getMenuManager().add(showCategoryAction);
			//this.actionBars.getToolBarManager().add(showCategoryAction);
			hasAddedShowCateMenu = true;
		}
	}
	
	public void restoreState(IMemento memento) {
		IPreferenceStore store = ARESUI.getDefault().getPreferenceStore();
		layoutActionGroup.setFlatLayout(store.getBoolean(ARESPreferences.FLATLAYOUT));
		
		boolean showCategory = store.getBoolean(ARESPreferences.SHOW_CATEGORY);
		showCategoryAction.setShowCategory(showCategory);
		showCategoryAction.setChecked(showCategory);
	}
	
	public void dispose() {
		super.dispose();
		extensionSite.getContentService().getActivationService().removeExtensionActivationListener(fMenuUpdater);
	}
	
}
