/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.CoreException;


/**
 * 项目属性节点
 * @author sundl
 */
public interface IProjectProperty extends IARESElement, IOpenable {
	
	// 由于不是资源，但是还是需要读写信息，所以增加两个信息的读写接口
	public IARESProjectProperty getInfo() throws ARESModelException;
	public void save(IARESProjectProperty property) throws CoreException;
	
}
