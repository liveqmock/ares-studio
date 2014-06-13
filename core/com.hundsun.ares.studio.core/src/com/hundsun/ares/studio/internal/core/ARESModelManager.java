/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESElementChangeListener;
import com.hundsun.ares.studio.core.IARESModel;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IParent;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.util.Util;

/**
 * ARESModelManager 管理模型树。
 * @author sundl
 */
public class ARESModelManager extends PlatformObject{
	
	private ARESModelCache cache;
	DeltaProcessor deltaProcessor = new DeltaProcessor();

	private static ARESModelManager manager;
	public static ARESModel model = new ARESModel(null);
	
	public static ARESModelManager getManager() {
		if (manager == null) {
			manager = new ARESModelManager();
		}
		return manager;
	}
	
	public IARESProject create(IProject project) {
		if (project == null)
			return null;
		
		return new ARESProject(getModel(), project);
	}

	public IARESElement create(IResource resource) {
		if (resource == null)
			return null;
		
		switch (resource.getType()) {
		case IResource.PROJECT:
			return create((IProject)resource);
		case IResource.FILE:
			return create((IFile)resource);
		case IResource.FOLDER:
			return create((IFolder)resource);
		}
		return null;
	}
	
	public IARESElement create(IFolder folder) {
		IARESProject cProject = create(folder.getProject());
		if (cProject != null && cProject.exists()) {
			return isOnResPath(folder);
		}
		return null;
	}
	
	public IARESElement create(IFile file) {
		if (file ==null)
			return null;
		
		IARESProject cProject = create(file.getProject());
		if (cProject == null) 
			return null;
		
		if (file.equals(cProject.getProject().getFile(IARESProjectProperty.PRO_FILE))) {
			return new ProjectProperty(cProject, file);
		}
		
		if (file.getFileExtension() != null) {
			String name = file.getName();
			if (!Util.isCommonResourceFileName(name)) {
				return null;
			}
		}
		
		IARESElement element = isOnResPath(file);
		return element;
	}

	protected IARESElement isOnResPath(IResource resource) {
		IARESProject cProject = create(resource.getProject());
		if (cProject != null && cProject.exists()) {
			IPath path = resource.getFullPath();
			for (IResPathEntry entry : cProject.getRawResPath()) {
				if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
					IPath fullEntryPath = cProject.getPath().append(entry.getPath());
					ARESModuleRoot root = null;
					IARESModule module = null;
					// this is a module root.
					if (path.equals(fullEntryPath)) {
						root = (ARESModuleRoot)cProject.getModuleRoot(entry);
						return root;
					} else if (fullEntryPath.isPrefixOf(path)) {
						root = (ARESModuleRoot)cProject.getModuleRoot(entry);
						IPath modulePath = resource.getFullPath().removeFirstSegments(fullEntryPath.segmentCount());
						if (resource instanceof IFile) {
							modulePath = modulePath.removeLastSegments(1);
						}			
						String[] moduleName = modulePath.segments();
						if (root != null) {
							module = root.getModule(moduleName);
						}
						
						// this is a module
						if (resource instanceof IFolder) {
							return module;
						} else if (resource instanceof IFile) {
							return module.getARESResource(resource.getName());
							// return new ARESResource(module, (IFile)resource);
						}
						
					}
				} else if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
					if (resource instanceof IFile) {
						if (entry.getPath().equals(resource.getFullPath())) {
							return cProject.getReferencedLibrary(path);
						}
					}
				}
			}
		}
		return null;
	}
	
//	protected IARESElement createCommonResourceFromFile(IARESModule module, IFile file) {
//		IARESProject cProject = create(file.getProject());
////		if (cProject != null && cProject.exists()) {
////			for (IResPathEntry entry : cProject.getRawResPath()) {
////				if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
////					IPath fullEntryPath = file.getProject().getFullPath().append(entry.getPath());
////					if (fullEntryPath.isPrefixOf(file.getFullPath())) {
////						IPath module = file.getFullPath().removeFirstSegments(fullEntryPath.segmentCount()).removeLastSegments(1);
////						String[] moduleName = module.segments();
////						ARESModuleRoot mRoot = (ARESModuleRoot)cProject.getModuleRoot(entry);
////						if (mRoot != null) {
////							IARESModule cModule = mRoot.getModule(moduleName);
////							return new ARESResource(cModule, file);
////						}
////					}
////				}
////			}
////		}
//		ARESResRegistry.getInstance().getResDescriptor(id);
//		return null;
//	}	
	
	
	public DeltaProcessor getDeltaProcessor() {
		return deltaProcessor;
	}
	
	public IARESElement create(IPath path) {
		return null;
	}

	public IARESElement create(String handleIdentifier) {
		return null;
	}	
	
	public void addElementChangeListener(IARESElementChangeListener listener) {
		deltaProcessor.listeners.add(listener);
	}
	
	public void removeElementChangeListener(IARESElementChangeListener listener) {
		deltaProcessor.listeners.add(listener);
	}
	
	public Object getInfo(IARESElement element) {
		return getCache().getInfo(element);
	}
	
	public synchronized void putInfo(IARESElement element, Object info) {
		getCache().putInfo(element, info);
	}
		
	public synchronized void removeInfoAndChildren(IARESElement element) throws ARESModelException {
		Object info = getCache().getInfo(element);
		if (info != null) {
			// if contains children, close them
			if (element instanceof IParent) {
				if (info instanceof ARESElementInfo) {
					ARESElementInfo elementInfo = (ARESElementInfo)info;
					for (IARESElement child : elementInfo.getChildren()) {
						((ARESElement)child).close();
					}
				}
			}
			// remove the info
			getCache().removeInfo(element);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESModelManager#startUp()
	 */
	public void startUp() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(deltaProcessor);
	}
	
	public void shutDown() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(deltaProcessor);
	}

	public IARESModel getModel() {
		return model;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESModelManager#createDeltaProcessor()
	 */
	protected DeltaProcessor createDeltaProcessor() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESModelManager#getCache()
	 */
	protected ARESModelCache getCache() {
		if (cache == null) {
			cache = new ARESModelCache();
		}
		return cache;
	}
	
}
