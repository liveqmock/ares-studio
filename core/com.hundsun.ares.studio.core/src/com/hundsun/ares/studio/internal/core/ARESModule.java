/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 模块
 * @author sundl
 */
public class ARESModule extends Openable implements IARESModule {

	private static Logger logger = Logger.getLogger(ARESModule.class.getName());
	public String[] names;
	
	public ARESModule(IARESElement parent, String[] names) {
		super(parent);		
		this.names = names;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		List<IARESResource> children = new ArrayList<IARESResource>();
		IResource resource =  getResource();
		try {
			IResource[] members = ((IContainer)resource).members();
			for (IResource res : members) {
				if (res.getType() == IResource.FILE) {
					IFile file = (IFile) res;
					IARESResource ares = getARESResource(file);
					if (ares != null)
						children.add(ares);
				}
			}
			info.setChildren(children.toArray(new IARESElement[children.size()]));
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		if (!underlyingResource.exists()) {
			return newDoesNotExistStatus();
		}
		return ARESModelStatus.VERIFIED_OK;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getCommonResource(java.lang.String)
	 */
	public IARESResource getARESResource(String name) {
		IFile file = ((IFolder)getResource()).getFile(name);
		return getARESResource(file);
	}
	
	private IARESResource getARESResource(IFile file) {
		IResDescriptor desc = getResDescriptor(file.getName());
		if (desc != null) {
			ARESResource cReource = (ARESResource)desc.createCommonResource();												
			cReource.init(this, file);
			return cReource;
		} else {
			logger.warn("No res-type extension registed for resource: " + file.getName());
			return null;
		}
	}
	
	private IResDescriptor getResDescriptor(String filename) {
		// 全名优先
		// 应该先检查文件全名，再检查扩展名
		IResDescriptor desc = ARESResRegistry.getInstance().getResDescriptor(filename);
		if (desc == null) {
			String ext = StringUtils.substringAfterLast(filename, ".");
			desc = ARESResRegistry.getInstance().getResDescriptor(ext);
		}
		return desc;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getCommonResource(java.lang.String, java.lang.String)
	 */
	public IARESResource getARESResource(String name, String type) {
		// type里如果有点的话，说明是固定文件名做为资源类型的情况
		if (StringUtils.contains(type, '.')) {
			return getARESResource(type);
		}
		return getARESResource(name + "." + type);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getResources()
	 */
	public IARESResource[] getARESResources() {
		List<IARESResource> resList = new ArrayList<IARESResource>();
		try {
			for (IARESElement element : getChildren()) {
				if (element instanceof IARESResource) {
					resList.add((IARESResource)element);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return resList.toArray(new IARESResource[resList.size()]);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getARESResources(boolean)
	 */
	public IARESResource[] getARESResources(boolean recursion) {
		List<IARESResource> resList = new ArrayList<IARESResource>();
		try {
			for (IARESElement element : getChildren()) {
				if (element instanceof IARESResource) {
					resList.add((IARESResource)element);
				}
			}
			
			if (recursion) {
				for (IARESModule subModule : getSubModules()) {
					resList.addAll(Arrays.asList(subModule.getARESResources()));
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return resList.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getSubModules()
	 */
	public IARESModule[] getSubModules() throws ARESModelException {
		// default module
		if (isDefaultModule()) {
			return new IARESModule[0];
		}
		
		IARESModuleRoot root = getRoot();
		IARESModule[] all = root.getModules();
		List<IARESModule> modules = new ArrayList<IARESModule>();
		for (IARESModule module : all) {
			if (module.getElementName().startsWith(getElementName() + "."))
				modules.add(module);
		}
		return modules.toArray(new IARESModule[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	public IResource getCorrespondingResource() throws ARESModelException {
		return getResource();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	public String getElementName() {
		if (this.names.length == 0)
			return DEFAULT_MODULE_NAME;
		return Util.concatWith(this.names, '.');
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return COMMON_MODULE;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return getResource().getFullPath();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		IARESModuleRoot root = (IARESModuleRoot)getParent();
		int length = this.names.length;
		if (length == 0) {
			return root.getResource();
		} else {
			IPath path = new Path(this.names[0]);
			for (int i = 1; i < length; i++)
				path = path.append(this.names[i]);
			return ((IContainer)root.getResource()).getFolder(path);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#copy(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IARESElement container, String rename, boolean replace, IProgressMonitor monitor) throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		IARESElement[] containers = new IARESElement[] {container};
		String[] renamings = null;
		if (rename != null) 
			renamings = new String[] {rename};
		
		getARESModel().copy(elements, containers, renamings, replace, null);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#delete(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, IProgressMonitor monitor)	throws ARESModelException {
		getARESModel().delete(new IARESElement[] {this}, force, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#move(com.hundsun.ares.studio.core.IARESElement, java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IARESElement container, String rename, boolean replace,
			IProgressMonitor monitor) throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		IARESElement[] containers = new IARESElement[] {container};
		String[] renamings = null;
		if (rename != null) 
			renamings = new String[] {rename};
		
		getARESModel().move(elements, containers, renamings, replace, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.ISourceManipulation#rename(java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void rename(String name, boolean replace, IProgressMonitor monitor) throws ARESModelException {
		IARESElement[] elements = new IARESElement[] {this};
		String[] names = new String[] {name};
		getARESModel().rename(elements, names, replace, monitor);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getLib()
	 */
	public IReferencedLibrary getLib() {
		return getRoot().getLib();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModule#getRoot()
	 */
	public IARESModuleRoot getRoot() {
		return (IARESModuleRoot)parent;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#isDefaultModule()
	 */
	public boolean isDefaultModule() {
		return names.length == 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getARESResources(java.lang.String)
	 */
	public IARESResource[] getARESResources(String type) {
		return getARESResources(type, false);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getARESResources(java.lang.String[])
	 */
	public IARESResource[] getARESResources(String[] types) {
		return getARESResources(types, false);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getARESResources(java.lang.String, boolean)
	 */
	public IARESResource[] getARESResources(String type, boolean recursion) {
		List<IARESResource> result = new ArrayList<IARESResource>();
		for (IARESResource element : getARESResources()) {
			if (element.getType().equals(type)) {
				result.add(element);
			}
		}
		if (recursion) {
			try {
				for (IARESModule module : getSubModules()) {
					result.addAll(Arrays.asList(module.getARESResources(type)));
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return result.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getARESResources(java.lang.String[], boolean)
	 */
	public IARESResource[] getARESResources(String[] types, boolean recursion) {
		List<IARESResource> result = new ArrayList<IARESResource>();
		for (IARESResource element : getARESResources()) {
			if (isInArray(element.getType(), types)) 
				result.add(element);
		}
		
		if (recursion) {
			try {
				for (IARESModule module : getSubModules()) {
					result.addAll(Arrays.asList(module.getARESResources(types)));
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return result.toArray(new IARESResource[0]);
	}
	
	/**
	 * 计算指定的字符串是否在字符串数组里，不支持null
	 * @param str 字符串
	 * @param array 数组
	 * @return 
	 */
	private boolean isInArray(String str, String[] array) {
		for (String item : array) {
			if (item.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#createResource(java.lang.String, java.lang.Object)
	 */
	public IARESResource createResource(String name, Object info) throws ARESModelException{
		IResDescriptor desc = getResDescriptor(name);
		if (desc != null) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			IModelConverter converter = desc.getConverter();
			if (converter != null) {
				if (converter instanceof IModelConverter2) {
					IARESResource aresResource = getARESResource(name);
					try {
						byte[] bytes = ((IModelConverter2) converter).write(aresResource, info);
						bos .write(bytes);
					} catch (Exception e) {
						logger.error("", e);
					}
				} else {
					try {
						converter.write(bos, info);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}
		
			try {	
				IFile file = ((IFolder)getResource()).getFile(name);
				if (file.exists()) {
					file.setContents(new ByteArrayInputStream(bos.toByteArray()), true, true, null);
				} else {
					file.create(new ByteArrayInputStream(bos.toByteArray()), true, null);
				}
			} catch (CoreException e) {
				throw new ARESModelException(e);
			}
		} // TODO  throw an exception
		return getARESResource(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getParentModule()
	 */
	public IARESModule getParentModule() {
		// 默认模块或第一级模块视为没有父模块
		if (isDefaultModule() || getElementName().indexOf('.') == -1)
			return null;
		
		IARESModuleRoot root = getRoot();
		String name =  getElementName();
		String parentModuleName = StringUtils.substringBeforeLast(name, ".");
		return root.getModule(parentModuleName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getShortName()
	 */
	public String getShortName() {
		if (names.length > 0) {
			return names[names.length - 1];
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#getNoneARESFiles()
	 */
	public IFile[] getNonARESFiles() {
		List<IFile> result = new ArrayList<IFile>();
		IFolder folder = (IFolder) getResource();
		try {
			for (IResource res : folder.members()) {
				if (res.getType() == IResource.FILE) {
					IFile file = (IFile) res;
					IARESResource ares = getARESResource(file);
					if (ares == null || !ares.exists())
						result.add((IFile) res);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return result.toArray(new IFile[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#findResource(java.lang.String)
	 */
	public IARESResource findResource(String name) {
		for (IARESResource res : getARESResources()) {
			if (res.getElementName().equalsIgnoreCase(name)) {
				return res;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModule#findResource(java.lang.String, java.lang.String)
	 */
	public IARESResource findResource(String name, String type) {
		for (IARESResource res : getARESResources()) {
			if (res.getName().equalsIgnoreCase(name) && res.getType().equalsIgnoreCase(type)) {
				return res;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_MODULE;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		switch (token.charAt(0)) {
		case AEM_RESOURCE:
			if (!memento.hasMoreTokens()) return this;
			String cuName = memento.nextToken();
			ARESElement aresResource = (ARESElement) getARESResource(cuName);
			return aresResource.getHandleFromMemento(memento);
		}
		return null;
	}

}
