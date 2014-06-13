/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;


/**
 * 负责对一系列AresResource执行某个操作
 * @author sundl
 */
public class AresActionAdvisor implements IAresActionAdvisor{
	
	protected IAresActionExcuteContext context;
	
	/**
	 * 初始化，传入框架创建用于整个执行过程中数据交互的context.
	 * @param context
	 */
	public void init(IAresActionExcuteContext context) {
		this.context = context;
	}
	
	/**
	 * 执行一些清理工作
	 */
	public void dispose() {
		context = null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionAdvisor#preExcute()
	 */
	public void preExcute() {
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionAdvisor#postExcute()
	 */
	public void postExcute() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionAdvisor#willModifyWorkspace()
	 */
	@Override
	public boolean willModifyWorkspace() {
		return true;
	}
	
}
