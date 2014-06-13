/**
 * 源程序名称：IContextProviderFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

import java.util.Map;



/**
 * 上下文提供器工厂
 * @author lvgao
 *
 */
public interface IContextProviderFactory {

	/**
	 * 创建资源统计提供器
	 * @param key       上下文ID
	 * @param context   必要参数
	 * @return
	 */
	public Object createContext(String key,Map<Object, Object> context);
	
}
