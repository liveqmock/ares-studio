package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESProject;

public class CommonNavigatorContentProvider extends ARESNavigatorContentProvider {

	private DelegateContentProvider delegateContentProvider;
	
	public CommonNavigatorContentProvider() {
	}
	
	@Override
	public ITreeContentProvider getDelegateContentProvider() {
		if (delegateContentProvider == null) {
			delegateContentProvider = new DelegateContentProvider();
		}
		return delegateContentProvider;
	}
	
}

class DelegateContentProvider extends CommonElementContentProvider {

	private Object input;
	
	public Object getParent(Object element) {
		Object parent= super.getParent(element);
		if (parent instanceof IARESModel) {
			//return parent.equals(getViewerInput()) ? fRealInput : parent;
			return input;
		}
		if (parent instanceof IARESProject) {
			return ((IARESProject)parent).getProject();
		}
		return parent;
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof IWorkspaceRoot) {
			return ((IWorkspaceRoot)inputElement).getProjects();
		} else if(inputElement instanceof IARESModel) {
			return ((IARESModel)inputElement).getWorkspace().getRoot().getProjects();
		} else if(inputElement instanceof IProject) {
			return super.getElements(ARESCore.create((IProject)inputElement));
		}
		return super.getElements(inputElement);
	}
	
	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof IProject) {
			return ((IProject)element).isAccessible();
		}
		return super.hasChildren(element);
	}
	
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IWorkspaceRoot) {
			return ((IWorkspaceRoot)parentElement).getProjects();
		}
		
		if (parentElement instanceof IProject) {
			return super.getChildren(ARESCore.create((IProject)parentElement));
		}
		
		return super.getChildren(parentElement);
	}
	
}