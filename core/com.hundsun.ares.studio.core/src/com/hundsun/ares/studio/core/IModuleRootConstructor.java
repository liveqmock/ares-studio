/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * 模块根初始化
 * @author sundl
 */
public interface IModuleRootConstructor {

	/**
	 * 初始化构建模块根，如有异常情况则抛出ARESModelException.
	 * @param root 需要初始化的模块根
	 * @throws ARESModelException 如果初始化过程中有异常情况，可以抛出这个异常
	 */
	public void construct(IARESModuleRoot root) throws ARESModelException;
	
}
