/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.internal.core.ARESProject;

/**
 * CommonNavigator要求的实现了IPipelinedTreeContentProvider的CP。
 * 由于Java无法多继承，所以把getChildren之类的请求委派给代理来完成。
 * @author sundl
 */
public abstract class ARESNavigatorContentProvider implements IPipelinedTreeContentProvider {

	private static Logger logger = Logger.getLogger(ARESNavigatorContentProvider.class);
	
	// 代理 和请求转发===== BEGIN ========
	/**
	 * 返回使用的代理contentProvider
	 * @return
	 */
	protected abstract ITreeContentProvider getDelegateContentProvider();
	
	public Object[] getChildren(Object parentElement) {
		return getDelegateContentProvider().getChildren(parentElement);
	}

	public Object getParent(Object element) {
		return getDelegateContentProvider().getParent(element);
	}

	public boolean hasChildren(Object element) {
		return getDelegateContentProvider().hasChildren(element);
	}

	public Object[] getElements(Object inputElement) {
		return getDelegateContentProvider().getElements(inputElement);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		getDelegateContentProvider().inputChanged(viewer, oldInput, newInput);
	}	
	// 代理 和请求转发===== END ========
	
	
	/* (non-Javadoc)
	 * 子类自己决定是否dispose代理CP
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedChildren(java.lang.Object, java.util.Set)
	 */
	public void getPipelinedChildren(Object aParent, Set theCurrentChildren) {		
		customize(getChildren(aParent), theCurrentChildren);
		
		IARESProject aresProj = null;
		if (aParent instanceof IProject) {
			aresProj = ARESCore.create((IProject) aParent);
		} else if (aParent instanceof IARESProject) {
			aresProj = (IARESProject) aresProj;
		}
		if (aresProj != null) {
			customizeReflibNodes(aresProj, theCurrentChildren);
		}
		
		logger.debug("Parent: " + aParent + "\nChildren: " + theCurrentChildren);
	}

	private void customize(Object[] aresElements, Set proposedChildren) {
		List elementList= Arrays.asList(aresElements);
		for (Iterator iter= proposedChildren.iterator(); iter.hasNext();) {
			Object element= iter.next();
			IResource resource= null;
			if (element instanceof IResource) {
				resource= (IResource)element;
			} else if (element instanceof IAdaptable) {
				resource= (IResource)((IAdaptable)element).getAdapter(IResource.class);
			}
			if (resource != null) {
				int i= elementList.indexOf(resource);
				if (i >= 0) {
					aresElements[i]= null;
				} 
			}
		}
		
		for (int i= 0; i < aresElements.length; i++) {
			Object element= aresElements[i];
			if (element instanceof IARESElement) {
				IARESElement cElement= (IARESElement)element;
				IResource resource= cElement.getResource();
				if (resource != null) {
					proposedChildren.remove(resource);
				}
				proposedChildren.add(element);
			} else if (element != null) {
				proposedChildren.add(element);
			}
		}

	}
	
	private void customizeReflibNodes(IARESProject project, Set proposedChildren) {
		for (Iterator iter= proposedChildren.iterator(); iter.hasNext();) {
			Object element= iter.next();
			IResource resource= null;
			if (element instanceof IResource) {
				resource= (IResource)element;
			} else if (element instanceof IAdaptable) {
				resource= (IResource)((IAdaptable)element).getAdapter(IResource.class);
			}
			
			if (resource != null && resource.getType() == IResource.FILE) {
				IPath path = resource.getFullPath();
				IReferencedLibrary lib = ((ARESProject) project).getReferencedLibrary(path);
				if (lib != null && lib.exists()) {
					iter.remove();
				}
			}

		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedElements(java.lang.Object, java.util.Set)
	 */
	public void getPipelinedElements(Object anInput, Set theCurrentElements) {
		logger.debug("getPipelinedElements, input is: " + anInput + "\n, Children is: " + theCurrentElements);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedParent(java.lang.Object, java.lang.Object)
	 */
	public Object getPipelinedParent(Object anObject, Object aSuggestedParent) {
		logger.debug("Object: " + anObject + "\n suggestedParent: " + aSuggestedParent);
		Object parent= getDelegateContentProvider().getParent(anObject);
		if (parent instanceof IARESModel) {
			return ((IARESModel)parent).getWorkspace().getRoot();
		}
		if (parent instanceof IARESProject) {
			return ((IARESProject)parent).getProject();
		}
		logger.debug("resultParent: " + parent);
		return parent;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptAdd(org.eclipse.ui.navigator.PipelinedShapeModification)
	 */
	public PipelinedShapeModification interceptAdd(PipelinedShapeModification addModification) {
		Object parent= addModification.getParent();
		
		if (parent instanceof IARESProject) {
			addModification.setParent(((IARESProject)parent).getProject());
		}

		if (parent instanceof IWorkspaceRoot) {
			deconvertAresProjects(addModification);
		}

		convertToAresElements(addModification);
		
		// 新建资源的时候,JDT会调用这个方法把新建的文件节点Add到模块下面,所以这里需要特殊处理一下
		CommonElementContentProvider cp = (CommonElementContentProvider) getDelegateContentProvider();
		parent = addModification.getParent();
		if (parent instanceof IARESElement) {
			for (Iterator childrenItr = addModification.getChildren().iterator(); childrenItr.hasNext();) {
				Object child = childrenItr.next();
				if (child instanceof IARESElement)
					childrenItr.remove();
			}
		}
			
		if (parent instanceof IARESModule && cp.showCategory) {
			// 显示分类的情况下
			for (Iterator childrenItr = addModification.getChildren().iterator(); childrenItr.hasNext();) {
				Object child = childrenItr.next();
				// only convert IFolders and IFiles
				if (child instanceof IARESResource) {
					IARESResource aresRes = (IARESResource) child;
					ARESResourceCategory[] cates = cp.getCategories((IARESModule) parent);
					for (ARESResourceCategory cate : cates) {
						if (cate.isResTypeAllowed(aresRes.getType())) {
							childrenItr.remove();
							break;
						}
					}
				}
			}
		}
		return addModification;
	}
	
	@SuppressWarnings("unchecked")
	private void deconvertAresProjects(PipelinedShapeModification modification) {
		Set convertedChildren = new LinkedHashSet();
		for (Iterator iterator = modification.getChildren().iterator(); iterator.hasNext();) {
			Object added = iterator.next();
			if(added instanceof IARESProject) {
				iterator.remove();
				convertedChildren.add(((IARESProject)added).getProject());
			}
		}
		modification.getChildren().addAll(convertedChildren);
	}
	
	private boolean convertToAresElements(PipelinedShapeModification modification) {
		Object parent = modification.getParent();
		// As of 3.3, we no longer re-parent additions to IProject.
		if (parent instanceof IContainer) {
			IARESElement element = ARESCore.create((IContainer) parent);
			if (element != null && element.exists()) {
				// we don't convert the root
				if( !(element instanceof IARESModel) && !(element instanceof IARESProject))
					modification.setParent(element);
				return convertToAresElements(modification.getChildren());
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private boolean convertToAresElements(Set currentChildren) {
		LinkedHashSet convertedChildren = new LinkedHashSet();
		IARESElement newChild;
		for (Iterator childrenItr = currentChildren.iterator(); childrenItr.hasNext();) {
			Object child = childrenItr.next();
			// only convert IFolders and IFiles
			if (child instanceof IFolder || child instanceof IFile) {
				if ((newChild = ARESCore.create((IResource) child)) != null
						&& newChild.exists()) {
					childrenItr.remove();
					convertedChildren.add(newChild);
				}
			} else if (child instanceof IARESProject) {
				childrenItr.remove();
				convertedChildren.add( ((IARESProject)child).getProject());
			}
		}
		if (!convertedChildren.isEmpty()) {
			currentChildren.addAll(convertedChildren);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptRefresh(org.eclipse.ui.navigator.PipelinedViewerUpdate)
	 */
	public boolean interceptRefresh(PipelinedViewerUpdate aRefreshSynchronization) {
		logger.debug(aRefreshSynchronization.getRefreshTargets());
		return convertToAresElements(aRefreshSynchronization.getRefreshTargets());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptRemove(org.eclipse.ui.navigator.PipelinedShapeModification)
	 */
	public PipelinedShapeModification interceptRemove(PipelinedShapeModification removeModification) {
		logger.debug(removeModification.getChildren());
		deconvertAresProjects(removeModification);
		convertToAresElements(removeModification.getChildren());
		return removeModification;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptUpdate(org.eclipse.ui.navigator.PipelinedViewerUpdate)
	 */
	public boolean interceptUpdate(PipelinedViewerUpdate anUpdateSynchronization) {
		logger.debug(anUpdateSynchronization.getRefreshTargets());
		return convertToAresElements(anUpdateSynchronization.getRefreshTargets());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonContentProvider#init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
	 */
	public void init(ICommonContentExtensionSite aConfig) {
		// System.out.println("init");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState(IMemento aMemento) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento aMemento) {
	}

}
