package com.hundsun.ares.studio.internal.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.ui.ARESResourceCategory;

public class EmptyCateFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof ARESResourceCategory) {
			return ((ARESResourceCategory)element).containsResource();
		}
		return true;
	}

}
