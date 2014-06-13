/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * 通用的模块根的接口。
 * @author sundl
 */
public interface IARESModuleRoot extends IARESElement, IOpenable, IParent {

	String PROPERTY_FILE = "root.xml";
	
	int KIND_SOURCE = 1;
	
	int KIND_BINARY = 2;
	
	/**
	 * 获取模块根下面的所有模块。
	 * @return 此模块根下的所有的模块。
	 */
	IARESModule[] getModules() throws ARESModelException;
	
	/**
	 * 获取指定名字的模块。
	 * @param name 模块的名字，全名。
	 * @return 指定名字的模块
	 */
	IARESModule getModule(String name);
	
	/**
	 * @param name
	 * @return
	 */
	IARESModule findModule(String name);
	
	/**
	 * 创建指定名字的模块。
	 * @param name 模块的名字，全名。
	 * @return 指定名字的模块
	 * @throws CoreException 
	 */
	IARESModule createModule(String name) throws CoreException;
	
	public IARESModule createModule(String[] name, String[] cName) throws CoreException;
	
	IARESModule createModule(String name, String cName) throws CoreException;
	
	/**
	 * 获取指定类型的资源列表
	 * @param type
	 * @return
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources(String type) throws ARESModelException;
	IARESResource[] getResources(String[] types) throws ARESModelException;
	IARESResource[] getResources() throws ARESModelException;
	
	int getKind();
	
	/**
	 * 类型
	 * @return
	 */
	String getType();
	
	/**
	 * 是否是归档文件（一般情况下，即是否是jar包）
	 * @return 如果是一个归档文件。
	 */
	boolean isArchive();
	
	/**
	 * 所在的包，仅当这个模块根是在引用包中的情况下有意义。其他情况返回null
	 * @return 模块根所在的引用包
	 */
	IReferencedLibrary getLib();
	
	IPath getRootPath();
}
