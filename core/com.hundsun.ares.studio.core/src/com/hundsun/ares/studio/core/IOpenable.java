/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 使用之前必须先打开的元素的统一接口。
 * @author sundl
 */
public interface IOpenable {

	/**
	 * 是否已经打开。
	 * @return 如果已经打开，则返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean isOpen();
	
	void open(IProgressMonitor monitor) throws ARESModelException;
	
	void close() throws ARESModelException;
	
}
