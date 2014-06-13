package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class InfoListContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List){
			return ((List)inputElement).toArray();
		}
		return null;
	}

}
