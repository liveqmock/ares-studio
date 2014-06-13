/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.resources.IFile;


/**
 * 通用的模块。
 * @author sundl
 */
public interface IARESModule extends IARESElement, IParent, IOpenable, ISourceManipulation {

	String DEFAULT_MODULE_NAME = "";
	String MODULE_PROPERTY_FILE = "module.xml";
	
	/**
	 * 所有子模块，包括直接，间接的。
	 * @return 子模块
	 * @throws ARESModelException 
	 */
	IARESModule[] getSubModules() throws ARESModelException;
	
	/**
	 * 计算模块的父模块。
	 * 由于目前所有模块在内存中都是平级结构，所有模块的getParent()都是模块根；
	 * 所以父子关系都必须通过计算才能得出。
	 * 注意这个方法和getParent()的区别。
	 * @return
	 */
	IARESModule getParentModule();
	
	String getShortName();
	
	/**
	 * 获取非Ares资源的文件。
	 * @return
	 */
	IFile[] getNonARESFiles();
	
	/**
	 * 取得这个模块下的所有资源。(不包括子模块)
	 * @return 所有资源列表
	 */
	IARESResource[] getARESResources();
	
	/**
	 * 包括子模块（所有子模块，包括直接和间接的）
	 * @param recursion
	 * @return
	 */
	IARESResource[] getARESResources(boolean recursion);
	
	/**
	 * 返回模块下具有指定的名字的资源; 不存在不会返回null，返回的ares resource需要用exists()方法判断是否存在。
	 * @param name 应该是带扩展名的资源名，例如xxx.action, xxx.table
	 * @return 模块下具有指定的名字的资源
	 */
	IARESResource getARESResource(String name);
	
	/**
	 * 查找资源，没有则返回null
	 * @param name 带扩展名
	 * @return
	 */
	IARESResource findResource(String name);
	
	/**
	 * 返回模块下具有指定的名字的资源; 不存在不会返回null，返回的ares resource需要用exists()方法判断是否存在。
	 * @param name 名字，应该是不带扩展名的短名。
	 * @param type 类型
	 * @return 模块下具有指定的名字的资源
	 */
	IARESResource getARESResource(String name, String type);
	
	/**
	 *  查找资源，没有则返回null
	 * @param name
	 * @param type
	 * @return
	 */
	IARESResource findResource(String name, String type);
	
	IARESResource[] getARESResources(String type);
	
	/**
	 * 获取指定类型的资源，可以指定是否递归子模块
	 * @param type 资源类型
	 * @param recursion 是否递归
	 * @return 模块下所有指定类型的资源
	 */
	IARESResource[] getARESResources(String type, boolean recursion);
	
	/**
	 * 获取指定类型的资源(不会递归子模块)
	 * @param types 
	 * @return
	 */
	IARESResource[] getARESResources(String[] types);
	
	/**
	 * 可以递归的取资源(递归即是否包含子模块里的资源)；没有第二个参数的默认为不递归
	 * @param types
	 * @param recursion 是否递归
	 * @return 模块下所有的指定类型的资源（根据第二个参数决定是否递归子模块）
	 */
	IARESResource[] getARESResources(String[] types, boolean recursion);
	
	/**
	 * 返回模块所在的根
	 * @return 模块所在的模块根
	 */
	IARESModuleRoot getRoot();
	
	/**
	 * 所在的引用包，仅当模块是在引用包里的情况下有意义。否则返回null
	 * @return 模块所在的引用包
	 */
	IReferencedLibrary getLib();
	
	boolean isDefaultModule();
	
	/**
	 * 创建资源,资源类型必须是已经注册过的; 不论创建成功与否都会返回对应的ARESResource对象。
	 * @param name 带扩展名,即文件名全名 
	 * @param info 资源信息
	 * @return
	 */
	IARESResource createResource(String name, Object info) throws ARESModelException;
}
