/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * 包含ARESResource的容器。
 * @author sundl
 */
public interface IARESResourceContainer extends IARESElement, IOpenable, IParent{

	/**
	 * 根据全名查找项目下的资源。
	 * @param fullyQualifiedName 全名
	 * @return 匹配指定的全名的资源
	 * @throws ARESModelException 
	 */	
	IARESResource findResource(String fullyQualifiedName, String type) throws ARESModelException;
	
	/**
	 * 只有全名查找资源。
	 * @param fullyQualifiedName
	 * @return
	 * @throws ARESModelException
	 */
	IARESResource[] findResource(String fullyQualifiedName) throws ARESModelException;
	
	/**
	 * 根据短名获取资源，结果可能有多个。
	 * @param name 短名(test.action)
	 * @return 
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources(String name) throws ARESModelException;
	
	/**
	 * 根据类型查找，可以指定多个类型。
	 * 指定参数为数组的另外一个原因是，根据短名获取资源也会返回多个资源，造成方法冲突。
	 * @param types 类型数组
	 * @return 指定类型的资源
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources(String[] types) throws ARESModelException;
	
	/**
	 * 获取所有的资源
	 * @return
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources() throws ARESModelException;
	
}
