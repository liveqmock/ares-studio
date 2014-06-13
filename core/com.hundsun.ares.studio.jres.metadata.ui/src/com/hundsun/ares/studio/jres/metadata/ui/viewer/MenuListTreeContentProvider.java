package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import com.hundsun.ares.studio.jres.model.metadata.MenuItem;

public class MenuListTreeContentProvider extends MetadataTreeContentProvider {
	
	@Override
	public Object[] getChildren(Object element) {
		if(element instanceof MenuItem){
			return ((MenuItem) element).getSubItems().toArray();
		}
		return super.getChildren(element);
	}
	
	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof MenuItem){
			if(((MenuItem) element).getSubItems().size()>0){
				return true;
			}
		}
		return super.hasChildren(element);
	}
	
}
