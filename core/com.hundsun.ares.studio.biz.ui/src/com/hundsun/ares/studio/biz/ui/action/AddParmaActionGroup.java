package com.hundsun.ares.studio.biz.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;

import com.hundsun.ares.studio.ui.editor.actions.ActionGroup;
import com.hundsun.ares.studio.ui.editor.blocks.ActionRegistry;

/**
 * 
 * 
 * @author sundl
 *
 */
public class AddParmaActionGroup extends ActionGroup {

	public AddParmaActionGroup(ActionRegistry reg, String[] actionIds) {
		super(createActions(reg, actionIds));
	}
	
	private static IAction[] createActions(ActionRegistry reg, String[] actionIds) {
		List<IAction> actions = new ArrayList<IAction>();
		if (actionIds != null) {
			for (String id : actionIds) {
				actions.add(reg.getAction(id));
			}
		}
		return actions.toArray(new IAction[0]);
	}

}
