/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 项目。
 * @author sundl
 */
public interface IARESProject extends IARESElement, IOpenable, IParent, IARESBundle{

	IARESElement findElement(IPath path);
	
	IProject getProject();
	
	String[] getRequiredProjectNames();
	
	/**
	 * 返回所有的模块根，包括了在引用包里的模块根
	 * @return 所有的根
	 */
	IARESModuleRoot[] getAllModuleRoots();
	
	IARESModuleRoot getModuleRoot(IResource resource);
	IARESModuleRoot getModuleRoot(IResPathEntry entry);
	
	/**
	 * 所有的模块
	 * @return
	 */
	IARESModule[] getModules() throws ARESModelException;
	
	IResPathEntry[] getRawResPath();
	
	void setRawResPath(IResPathEntry[] entries, IProgressMonitor monitor);
	
	IProjectProperty getProperty();
	IARESProjectProperty getProjectProperty() throws ARESModelException;
	
	/**
	 * 创建一个模块根
	 * @param type
	 * @param path
	 * @return
	 */
	IARESModuleRoot createRoot(String type, String path, IProgressMonitor monitor) throws ARESModelException;

	IReferencedLibrary getReferencedLibrary(IResPathEntry entry);
	IReferencedLibrary getReferencedLibrary(IPath path);
	IReferencedLibrary[] getReferencedLibs() throws ARESModelException;
	
	IARESProject[] getRequiredProjects();
	public List<IDependencyUnit> getDependencies();
}
