/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IParent;

/**
 * 基本的CP.
 * @author sundl
 */
public abstract class ARESElementContentProvider implements ITreeContentProvider{

	protected static final Object[] NO_CHILDREN = new Object[0];
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (!exists(parentElement)) {
			return NO_CHILDREN;
		}
		
		try {
			if (parentElement instanceof IARESModel) {
				return ((IARESModel)parentElement).getARESProjects();
			} else if (parentElement instanceof IARESProject) {
				IARESProject project = (IARESProject)parentElement;
				if (!project.getProject().isOpen()) {
					return NO_CHILDREN;
				}
				return getProjectChildren(project);
			} else if (parentElement instanceof IARESModuleRoot) {
				return getModuleRootChildren((IARESModuleRoot)parentElement);
			} else if (parentElement instanceof IARESModule) {
				return getModuleChildren((IARESModule)parentElement);
			} else if (parentElement instanceof IParent) {
				return ((IParent)parentElement).getChildren();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return NO_CHILDREN;
	}
	
	protected Object[] getModuleChildren(IARESModule module) throws ARESModelException{
		return module.getARESResources();
	}

	protected Object[] getModuleRootChildren(IARESModuleRoot root) throws ARESModelException {
		return root.getModules();
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 * 
	 * @param element the element to test
	 * @return returns <code>true</code> if the element exists
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	protected boolean exists(Object element) {
		if (element == null) {
			return false;
		}
		if (element instanceof IResource) {
			return ((IResource)element).exists();
		}
		if (element instanceof IARESElement) {
			return ((IARESElement)element).exists();
		}
		return true;
	}
	
	protected abstract Object[] getProjectChildren(IARESProject project);
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (!exists(element))
			return null;
		return internalGetParent(element);
	}
	
	protected Object internalGetParent(Object child) {
		if (child instanceof IARESElement) {
			return ((IARESElement)child).getParent();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if(element instanceof IARESProject) {
			IARESProject project = (IARESProject)element;
			if(!project.getProject().isOpen()) {
				return false;
			}
		}
		
		if(element instanceof IParent) {
			try {
				IARESElement aresElement = (IARESElement)element;
				if (aresElement.isStructureKnown()) {
					return ((IParent)element).hasChildren();
				} else {
					return true;
				}				
			} catch (ARESModelException e) {
				e.printStackTrace();
				return true;
			}
		}
		
		Object[] children= getChildren(element);
		return (children != null) && children.length > 0;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
