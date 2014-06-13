package com.hundsun.ares.studio.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class ARESElementSorter extends ViewerSorter {

	private final AresElementComparater comparator;
	
	public ARESElementSorter() {
		comparator = new AresElementComparater();
	}
	
	@Override
	public int category(Object element) {
		return comparator.category(element);
	}
	
    public int compare(Viewer viewer, Object e1, Object e2) {
    	return comparator.compare(viewer, e1, e2);
    }
}
