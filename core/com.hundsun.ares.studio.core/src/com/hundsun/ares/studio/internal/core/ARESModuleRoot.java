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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.ModulePropertyConverter;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 通用模块根。
 * @author sundl
 */
public class ARESModuleRoot extends Openable implements IARESModuleRoot{

	protected String type;
	protected IPath path;
	
	public ARESModuleRoot(IARESElement parent, IPath path, String type) {
		super(parent);
		this.path = path;
		this.type = type;
	}

	public ARESModuleRoot() {
		this(null, null, null);
	}
	
	public void init(IARESElement parent, IPath path, String type) {
		this.parent = parent;
		this.path = path;
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		IFolder folder = getFolder();
		computeChildren(info, folder);
		return true;
	}
	
	protected void computeChildren(ARESElementInfo info, IResource resource) {
		List<IARESElement> children = new ArrayList<IARESElement>();
		if(resource.getType() == IResource.FOLDER) {
			computeFolder(children, (IContainer)resource, new String[0]);
			info.setChildren(children);
		}	
	}
	
	private void computeFolder(List<IARESElement> children, IContainer folder, String[] moduleName) {
		// the default module
		IARESModule module = getModule(moduleName);
		children.add(module);
		
		// the normal modules
		try {
			IResource[] members = folder.members();
			int length = members.length;
			for(int i = 0; i < length; i++) {
				IResource member = members[i];
				switch(member.getType()) {
				case IResource.FOLDER:
					String memberName = member.getName();
					if (Util.isValidNameForModule(memberName)) {
						String[] newNames = Util.arrayConcat(moduleName, member.getName().intern());
						computeFolder(children, (IFolder)member, newNames);
					}					
				}
			}
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
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
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getModule(java.lang.String)
	 */
	public IARESModule getModule(String name) {
		if (name == null || name.trim().length() == 0) {
			return getModule(new String[0]);
		}
		
		String[] moduleName = Util.getTrimmedSimpleNames(name);
		if(moduleName != null) {
			return getModule(moduleName);
		}
		return null;
	}

	public IARESModule getModule(String[] name) {
		return new ARESModule(this, name);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getModules()
	 */
	public IARESModule[] getModules() throws ARESModelException{
		IARESElement[] children = getChildren();
		List<IARESModule> modules = new ArrayList<IARESModule>();
		for (IARESElement child : children) {
			if (child.getElementType() == IARESElement.COMMON_MODULE) {
				modules.add((IARESModule)child);
			}
		} 
		return modules.toArray(new IARESModule[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#isArchive()
	 */
	public boolean isArchive() {
		return false;
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
		return path.toString();
	}
	
	public IFolder getFolder() {
		IFolder folder = getARESProject().getProject().getFolder(path);
		return folder;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return COMMON_MODULE_ROOT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return getFolder().getFullPath();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	public IResource getResource() {
		return getFolder();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getType()
	 */
	public String getType() {
//		IARESProject project = (IARESProject)getParent();
//		for (IResPathEntry entry : project.getRawResPath()) {
//			if (entry.getPath().equals(getPath().removeFirstSegments(1))) {
//				return entry.getType();
//			}
//		}
		if (this.type != null) {
			return type;
		}
		return "error";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonModuleRoot#getLib()
	 */
	public IReferencedLibrary getLib() {
		IARESElement parent = getParent();
		if (parent instanceof IReferencedLibrary) 
			return (IReferencedLibrary)parent;
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getResources(java.lang.String)
	 */
	public IARESResource[] getResources(String type) throws ARESModelException {
		IARESModule[] modules = getModules(); 
		List<IARESResource> resources = new ArrayList<IARESResource>();
		for (IARESModule module : modules) {
			resources.addAll(Arrays.asList(module.getARESResources(type)));
		}
		return resources.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getResources(java.lang.String[])
	 */
	public IARESResource[] getResources(String[] types) throws ARESModelException {
		IARESModule[] modules = getModules(); 
		List<IARESResource> resources = new ArrayList<IARESResource>();
		for (IARESModule module : modules) {
			resources.addAll(Arrays.asList(module.getARESResources(types)));
		}
		return resources.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getResources()
	 */
	public IARESResource[] getResources() throws ARESModelException {
		IARESModule[] modules = getModules();
		List<IARESResource> resources = new ArrayList<IARESResource>();
		for (IARESModule module : modules) {
			resources.addAll(Arrays.asList(module.getARESResources()));
		}
		return resources.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#createModule(java.lang.String)
	 */
	public IARESModule createModule(String name) throws CoreException {
		String[] moduleName = Util.getTrimmedSimpleNames(name);
		
		IFolder folder = (IFolder) getResource();
		for (String module : moduleName) {
			folder = folder.getFolder(module);
			if (!folder.exists()) {
				folder.create(true, true, null);
			}
		}
		
		if(moduleName != null) {
			return getModule(moduleName);
		}
		return null;
	}
	
	public IARESModule createModule(String[] name, String[] cName) throws CoreException {
		String[] moduleName = name;
		String[] moduleCName = cName;
		if (moduleCName.length != moduleName.length)
			return null;
		
		IFolder folder = (IFolder) getResource();
		for (int i = 0; i < moduleName.length; i++) {
			String module = moduleName[i];
			folder = folder.getFolder(module);
			if (!folder.exists()) {
				folder.create(true, true, null);
			}
			
			IFile propertyFile = folder.getFile(IARESModule.MODULE_PROPERTY_FILE);
			if (!propertyFile.exists()) {
				ModuleProperty proModel = new ModuleProperty();
				proModel.setValue(ICommonModel.CNAME, moduleCName[i]);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				try {
					ModulePropertyConverter.getInstance().write(os, proModel);
				} catch (Exception e) {
					throw new CoreException(new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "创建模块属性文件时发生错误", e));
				}
				propertyFile.create(new ByteArrayInputStream(os.toByteArray()), true, null);
			}
		}
		
		if(moduleName != null) {
			return getModule(moduleName);
		}
		return null;
	}

	
	public IARESModule createModule(String name, String cName) throws CoreException {
		String[] moduleName = Util.getTrimmedSimpleNames(name);
		String[] moduleCName = Util.getTrimmedSimpleNames(cName);
		if (moduleCName.length != moduleName.length)
			return null;
		
		return createModule(moduleName, moduleCName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getKind()
	 */
	public int getKind() {
		return KIND_SOURCE;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Element Type: MODULE_ROOT\n");
		sb.append("Element Name: " + getElementName() + "\n");
		sb.append("Root Type: " + getType() + "\n");
		sb.append("Modules: \n");
		if (isOpen()) {
			try {
				for (IARESModule module : getModules()) {
					sb.append("\t" + module.getElementName() + "\n");
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		} else {
			sb.append("\t not open\n");
		}

		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#findModule(java.lang.String)
	 */
	public IARESModule findModule(String name) {
		try {
			for (IARESModule module : getModules()) {
				if (module.getElementName().equalsIgnoreCase(name))
					return module;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_MODULEROOT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		switch (token.charAt(0)) {
		case AEM_MODULE:
			String[] pkgName;
			if (memento.hasMoreTokens()) {
				token = memento.nextToken();
				char firstChar = token.charAt(0);
				if (firstChar == AEM_RESOURCE) {
					pkgName = new String[0];
				} else {
					pkgName = token.split("\\.");
					token = null;
				}
			} else {
				pkgName = new String[0];
				token = null;
			}
			ARESElement pkg = (ARESElement) getModule(pkgName);
			if (token == null) {
				return pkg.getHandleFromMemento(memento);
			} else {
				return pkg.getHandleFromMemento(token, memento);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESModuleRoot#getRootPath()
	 */
	public IPath getRootPath() {
		return path;
	}

}
