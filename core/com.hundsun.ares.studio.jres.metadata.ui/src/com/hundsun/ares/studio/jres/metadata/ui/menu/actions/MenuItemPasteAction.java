package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.jres.metadata.ui.actions.PasteAction;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;

public class MenuItemPasteAction extends PasteAction {

	private EClassifier cateClass;
	private EClassifier itemClass;

	/**
	 * @param viewer
	 * @param editingDomain
	 * @param cateClass
	 * @param itemClass
	 */
	public MenuItemPasteAction(ColumnViewer viewer,
			EditingDomain editingDomain, EClass cateClass, EClass itemClass) {
		super(viewer, editingDomain, cateClass, itemClass);
		this.cateClass = cateClass;
		this.itemClass = itemClass;
	}

	@Override
	protected Command createCommand() {
		List<MetadataCategory> copiedCategories = (List<MetadataCategory>) ARESEMFClipboard.getInstance().pasteFromClipboard(cateClass.getInstanceClass());
		List<MetadataItem> copiedItems = (List<MetadataItem>) ARESEMFClipboard.getInstance().pasteFromClipboard(itemClass.getInstanceClass());
		if(copiedCategories.isEmpty() && !copiedItems.isEmpty()){
			List<Object> objs = getSelectedObjects();
			if(objs.size() > 0){
				Object obj = objs.get(0);
				if(obj instanceof MenuItem){
					return AddCommand.create(getEditingDomain(), obj, MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS, copiedItems);
				}
			}
		}
		return super.createCommand();
	}
}
