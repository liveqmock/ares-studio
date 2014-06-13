/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.IResPathEntryElement;
import com.hundsun.ares.studio.core.util.Util;

/**
 * “引用” 节点。
 * @author sundl
 */
public class RefLibContainer implements IAdaptable{

	private static final String REF_LIB_CONTAINER = "referenced_library";
	
	private IARESProject project;
	
	public RefLibContainer(IARESProject project) {
		this.project = project;
	}
	
	public IReferencedLibrary[] getReferencedLibs() throws ARESModelException {
		return project.getReferencedLibs();
	}

	public IARESProject getProject() {
		return project;
	}

	public void setProject(IARESProject project) {
		this.project = project;
	}
	
	public IAdaptable[] getChildren() {
		List<IAdaptable> children = new ArrayList<IAdaptable>();
		
		for (IResPathEntry entry : project.getRawResPath()) {
			switch (entry.getEntryKind()) {
				case IResPathEntry.RPE_LIBRAY:
					IReferencedLibrary lib = project.getReferencedLibrary(entry);
					if (lib != null && lib.exists())
						children.add(lib);
					break;
				case IResPathEntry.RPE_PROJECT:
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IProject project = (IProject) root.findMember(entry.getPath());
					IARESProject aresProject = ARESCore.create(project);
					if (aresProject != null && aresProject.exists()) {
						children.add(new RequiredProject(this, aresProject, entry));
					}
					break;
			}
		}
		return children.toArray(new IAdaptable[0]);		
	}

	@Override
	public int hashCode() {
		return Util.combineHashCodes(REF_LIB_CONTAINER.hashCode(), project.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RefLibContainer)) 
			return false;
		
		RefLibContainer other = (RefLibContainer) obj;
		return this.project.equals(other.project);
	}
	
	public static class RequiredProject extends PlatformObject implements IAdaptable, IWorkbenchAdapter, IResPathEntryElement {

		private RefLibContainer container;
		// 这个是这个引用工程代表的aresProject
		private IARESProject aresProject;
		// 2012-2-20 sundl 记录类型，用于创建respathentry对象。否则删除的时候会由于equals方法不对而不能删除。
		// 引用工程的类型
		private IResPathEntry entry;
		
		public RequiredProject(RefLibContainer container, IARESProject aresProject, IResPathEntry entry) {
			this.container = container;
			this.aresProject = aresProject;
			this.entry = entry;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getChildren(java.lang.Object)
		 */
		@Override
		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
		 */
		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(IDE.SharedImages.IMG_OBJ_PROJECT);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getLabel(java.lang.Object)
		 */
		@Override
		public String getLabel(Object o) {
			return aresProject.getElementName();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getParent(java.lang.Object)
		 */
		@Override
		public Object getParent(Object o) {
			return container;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
		 */
		@Override
		public Object getAdapter(Class adapter) {
			if (adapter == IWorkbenchAdapter.class) 
				return this;
			return super.getAdapter(adapter);
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.core.IResPathEntryElement#getResPathEntry()
		 */
		@Override
		public IResPathEntry getResPathEntry() {
			return entry;
		}
		
		public IARESProject getHostAresProject() {
			return container.getProject();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((aresProject == null) ? 0 : aresProject.hashCode());
			result = prime * result + ((entry == null) ? 0 : entry.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RequiredProject other = (RequiredProject) obj;
			if (aresProject == null) {
				if (other.aresProject != null)
					return false;
			} else if (!aresProject.equals(other.aresProject))
				return false;
			if (entry == null) {
				if (other.entry != null)
					return false;
			} else if (!entry.equals(other.entry))
				return false;
			return true;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IARESProject.class) {
			return getProject();
		} else if (adapter == IProject.class) {
			return getProject().getProject();
		}
		return null;
	}
	
}
