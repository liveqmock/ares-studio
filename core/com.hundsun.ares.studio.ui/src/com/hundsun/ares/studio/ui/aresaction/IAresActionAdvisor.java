/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;


/**
 * 用于执行某个Ares Action的Advisor类，参与整个Action的调用过程。
 * @author sundl
 */
public interface IAresActionAdvisor {

	/**
	 * 初始化，传入框架创建用于整个执行过程中数据交互的context.
	 * @param context
	 */
	void init(IAresActionExcuteContext context);
	
	/**
	 * 2012-03-12 sundl 增加这个方法判断是否会修改工作区间，如果是，则这个action会放在
	 * IWorkspaceRunnable中执行。
	 * @return
	 */
	boolean willModifyWorkspace();
	
	void preExcute();
	
	/**
	 * 执行完成以后调用
	 */
	void postExcute();
	
	/**
	 * 执行一些清理工作
	 */
	void dispose();
	
}
