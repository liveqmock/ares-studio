/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 操作执行上下文
 * @author sundl
 */
public interface IAresActionExcuteContext {

	/**
	 * 入口，即从什么地方执行这个操作的(比如用户右键项目，选择xxxAction，则此方法返回对应的IARESProject)
	 * @return
	 */
	Object getEntryPoint();
	
	/**
	 * 当前正在执行的Resource
	 * @return
	 */
	IARESResource getCurrentResource();
	
	/**
	 * 操作可以自行设置的数据，用户串联多个资源执行过程中的数据交互。
	 * @param key
	 * @return
	 */
	Object getData(String key);
	void setData(String key, Object value);
	
}
