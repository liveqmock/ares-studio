/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 支持复制，移动，重命名和删除操作的元素的统一接口。
 * @author sundl
 */
public interface ISourceManipulation {

	/**
	 * 元素复制到指定的container下。
	 * @param container 复制到的容器
	 * @param rename 复制时指定的新名字，如果不需要，可以为<code>null</code>
	 * @param replace 如果有重名元素的情况下，是否覆盖
	 * @param monitor The progress monitor
	 * @throws ARESModelException 
 	 * <ul>
	 * <li> container,element不存在(ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> 在复制底层文件的时候，发生CoreException</li>
	 * <li> Container类型不对(INVALID_DESTINATION)</li>
	 * <li> 指定的名字不合法(INVALID_NAME)</li>
	 * <li> 指定的Container下已经有同名元素，且replace为<code>false</code></li>
	 * <li> container是只读的 </li>
	 * </ul>
	 */
	void copy(IARESElement container, String rename, boolean replace, IProgressMonitor monitor) throws ARESModelException;
	
	void delete(boolean force, IProgressMonitor monitor) throws ARESModelException;
	
	void move(IARESElement container, String rename, boolean replace, IProgressMonitor monitor) throws ARESModelException;
	
	void rename(String name, boolean replace, IProgressMonitor monitor) throws ARESModelException;
}
