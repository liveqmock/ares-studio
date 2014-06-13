package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveDownAction;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

public class MenuItemMoveDownAction extends MoveDownAction {

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public MenuItemMoveDownAction(ColumnViewer viewer,
			EditingDomain editingDomain) {
		super(viewer, editingDomain);
	}
	
	@Override
	protected EObject getOwner() {
		List<Object> objs = getSelectedObjects();
		if(objs.size()>0){
			Object obj = objs.get(0);
			if(obj instanceof MenuItem){
				if(((MenuItem) obj).eContainer() instanceof MenuItem){
					return ((MenuItem) obj).eContainer();
				}
			}
		}
		return super.getOwner();
	};
	
	@Override
	protected EReference getReference() {
		List<Object> objs = getSelectedObjects();
		if(objs.size()>0){
			Object obj = objs.get(0);
			if(obj instanceof MenuItem && getOwner() instanceof MenuItem){
				return MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS;
			}
		}
		return super.getReference();
	}

}
