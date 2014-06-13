/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * ARES项目、引用包的统一接口
 * @author sundl
 */
public interface IARESBundle {

	/**
	 * 获取信息，从这里可以获得如ID，name等属性
	 * @return
	 */
	IARESBundleInfo getInfo();
	
	/**
	 * 等同于getInfo().getId(); 方便使用
	 * @return
	 */
	String getId();
	
	/**
	 * 获取<b>本Bundle中</b>全部的模块根
	 * @return
	 * @throws ARESModelException
	 */
	IARESModuleRoot[] getModuleRoots() throws ARESModelException;
	
	/**
	 * 根据全名查找项目下的资源。
	 * @param fullyQualifiedName 全名
	 * @param type 资源类型
	 * @return 匹配指定的全名的资源
	 * @throws ARESModelException
	 */
	IARESResource findResource(String fullyQualifiedName, String type) throws ARESModelException;
	
	/**
	 * 在<b>*本Bundle*</b>中根据全名查找资源。
	 * @param fullyQualifiedName 全名（模块名.资源短名）: a.b.xxx
	 * @return 由于资源可能重名，所以返回数组
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
	 * 根据类型查找在<b>*本Bundle*</b>中指定类型的资源，可以指定多个类型。
	 * 指定参数为数组的另外一个原因是，根据短名获取资源也会返回多个资源，造成方法冲突。
	 * @param types 类型数组
	 * @return 指定类型的资源
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources(String[] types) throws ARESModelException;
	
	/**
	 * 获取<b>本Bundle中</b>所有的资源
	 * @return
	 * @throws ARESModelException 
	 */
	IARESResource[] getResources() throws ARESModelException;
	
	/**
	 * 获取指定的模块根，只返回在<b>*本Bundle*</b>中的
	 * @param path
	 * @return
	 * @throws ARESModelException
	 */
	IARESModuleRoot getModuleRoot(String path) throws ARESModelException;
	
	/**
	 * 获取依赖的Bundle列表；
	 * 目前仅在本Bundle为Project的时候有效；
	 * 另外为以后支持引用资源包，这个方法执行可能需要经过比较复杂的运算，所以不要假设这个方法的效率会比较高
	 * @return
	 */
	public IARESBundle[] getRequiredBundles();
}
