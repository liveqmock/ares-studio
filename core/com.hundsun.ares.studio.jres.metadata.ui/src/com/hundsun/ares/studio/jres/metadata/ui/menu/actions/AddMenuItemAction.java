package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddItemAction;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

public class AddMenuItemAction extends AddItemAction {

	private EClass itemClass;
	/**
	 * @param viewer
	 * @param editingDomain
	 * @param itemClass
	 */
	public AddMenuItemAction(ColumnViewer viewer, EditingDomain editingDomain,
			EClass itemClass) {
		super(viewer, editingDomain, itemClass);
		this.itemClass = itemClass;
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/addSiblingMenu.png"));
	}

	@Override
	protected Command createCommand() {
		EObject item = itemClass.getEPackage().getEFactoryInstance().create(itemClass);
		List<Object> objs = getSelectedObjects();
		if(objs.size() >0){
			Object obj = objs.get(0);
			if(obj instanceof MenuItem){
				EObject parent = ((MenuItem)obj).eContainer();
				if(parent instanceof MenuItem){
					return AddCommand.create(getEditingDomain(), parent, MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS, item);
				}
			}
		}
		return super.createCommand();
	}
}
