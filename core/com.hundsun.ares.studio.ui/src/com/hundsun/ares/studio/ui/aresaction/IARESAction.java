/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * ARES通用操作接口
 * @author sundl
 */
public interface IARESAction {

	/**
	 * 初始化操作
	 * @param res 当前执行操作的资源
	 * @param targetPart 发起调用的IWorkbenchPart，帮助Action访问UI
	 */
	public void init(IAresActionExcuteContext context);
	
	/**
	 * 执行操作
	 * @param monitor 进度监视器
	 */
	public void execute(IProgressMonitor monitor);
	
	/**
	 * 是否可用，只会在初始化调用后调用此方法。
	 */
	public boolean isEnabled();
	
}
