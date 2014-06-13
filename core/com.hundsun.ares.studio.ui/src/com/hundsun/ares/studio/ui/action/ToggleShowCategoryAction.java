/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.ui.ARESPreferences;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 
 * @author sundl
 */
public class ToggleShowCategoryAction extends Action {

	private StructuredViewer viewer;
	private IExtensionStateModel stateModel;
	private boolean showCategory;
	
	public ToggleShowCategoryAction(IExtensionStateModel model, StructuredViewer viewer) {
		super("显示分类", AS_CHECK_BOX);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESUI.PLUGIN_ID, "/icons/obj16/folder.gif"));
		this.stateModel = model;
		this.viewer = viewer;
	}

	public void setShowCategory(boolean show) {
		showCategory = show;
	}
	
	@Override
	public void run() {
		//boolean curState = stateModel.getBooleanProperty(ARESUI.SHOW_CATEGORY);
		//this.stateModel.setBooleanProperty(ARESUI.SHOW_CATEGORY, !curState);
		IPreferenceStore store = ARESUI.getDefault().getPreferenceStore();
		//if (store.getBoolean(ARESUI.SHOWCATEGORY) != showCategory) {
			store.setValue(ARESPreferences.SHOW_CATEGORY, !showCategory);
			showCategory = !showCategory;
			viewer.getControl().setRedraw(false);
			try {
				viewer.refresh();
			} finally {
				viewer.getControl().setRedraw(true);
			}
		//}
		
	}

}
