package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;

import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.ActionGroup;
import com.hundsun.ares.studio.ui.editor.blocks.ActionRegistry;

public class MetadataNewActionGroup extends ActionGroup {

	public IAction newItemAction;
	public IAction newSubCategoryAction;
	public IAction newSiblingCategoryAction;
	
	public MetadataNewActionGroup(ActionRegistry reg) {
		super(createActions(reg));
		newItemAction = actions[0];
		newSubCategoryAction = actions[1];
		newSiblingCategoryAction = actions[2];
	}
	
	private static IAction[] createActions(ActionRegistry reg) {
		List<IAction> actions = new ArrayList<IAction>();
		actions.add(reg.getAction(IMetadataActionIDConstant.CV_ADD_ITEM));
		actions.add(reg.getAction(IMetadataActionIDConstant.CV_ADD_SLIBING_CATEGORY));
		actions.add(reg.getAction(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY));
		return actions.toArray(new IAction[0]);
	}

}
