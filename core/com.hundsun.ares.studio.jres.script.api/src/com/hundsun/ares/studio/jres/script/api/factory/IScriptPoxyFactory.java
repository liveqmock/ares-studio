/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.factory;

import java.util.Map;

/**
 * 用于脚本的模型封装
 * 
 * @author lvgao
 */
public interface IScriptPoxyFactory {

	/**
	 * 包装函数
	 * 
	 * @param input
	 * @param context
	 * @return
	 */
	public Object createPoxy(Object input,Map<Object, Object> context);
	
	/**
	 * 获取模块封装的代理实现
	 * @param input 一般是对象
	 * @return
	 */
	public Object createModuleProxy(Object input);
}
