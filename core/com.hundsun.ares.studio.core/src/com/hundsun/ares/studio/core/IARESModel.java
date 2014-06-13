/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 模型的根元素，对应工作区间。
 * 每个基于ARESCore的模型树，都应该有个实现/继承这个接口的实例，作为自己的模型树的单根。
 * 需要提供对多个ARES元素的复制/移动/重命名/删除操作。
 * @author sundl
 */
public interface IARESModel extends IARESElement, IOpenable, IParent{

	/**
	 * 将指定的元素复制到指定的容器。
	 * 如果只指定了一个container，那么就把所有的元素复制到这个container下面。如果指定了多个container，
	 * 元素数组的长度必须和容器数组的长度匹配，这样每个元素复制到对应的container.
	 * <p>
	 * 每个复制的元素都可以选择是否重命名。如果新名字指定为<code>null</code>，则不会重命名。
	 * </p>
	 * <p>
	 * 可以指定如果在目标位置已经存在了相同名字的元素，是否覆盖。如果有同名且不覆盖，则会抛出相应的异常。
	 * </p>
	 * @param elements 需要复制的元素
	 * @param containers 复制到的容器或者容器列表
	 * @param renamings 新名字的列表，任意的元素都可以是<code>null</code>；或者整个是<code>null</code>
	 * @param replace 如果目标位置同名元素已经存在，是否覆盖
	 * @param monitor The progress monitor
	 * @throws ARESModelException 如果某个元素不能被复制，原因可能包括:
	 * <ul>
	 * <li> 没有要处理的元素(NO_ELEMENTS_TO_PROCESS)。elements为null或者长度为0 </li>
	 * <li> 某个元素或者容器不存在 (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> 更新底层资源的时候发生了<code>CoreException</code></li>
	 * <li> 某个容器的类型不支持(INVALID_DESTINATION)</li>
	 * <li> 某个指定的新的名字不合法(INVALID_NAME)</li>
	 * <li> 某个同名元素存在，并且<code>replace</code>指定的为<code>false</code></li>
	 * </ul>
	 */
	void copy(IARESElement[] elements, IARESElement[] containers, /*sibling?*/String[] renamings, boolean replace, IProgressMonitor monitor) throws ARESModelException;

	/**
	 * 删除指定的元素。
	 * @param elements
	 * @param force
	 * @param monitor
	 */
	void delete(IARESElement[] elements, boolean force, IProgressMonitor monitor) throws ARESModelException;
	
	/**
	 * 返回具有指定名字的ARES项目。这个返回的项目可能不存在。
	 * @param name 名字
	 * @return 具有指定名字的ARES项目
	 */
	IARESProject getARESProject(String name);
	
	/**
	 * 返回模型中所有的ARES项目。
	 * @return 模型中所有的ARES项目
	 * @throws ARESModelException
	 */
	IARESProject[] getARESProjects() throws ARESModelException;
	
	IWorkspace getWorkspace();
	
	void move(IARESElement[] elements, IARESElement[] container, String[] renamings, boolean replace, IProgressMonitor monitor) throws ARESModelException;
	
	void rename(IARESElement[] elements, String[] names, boolean replace, IProgressMonitor monitor) throws ARESModelException;
}
