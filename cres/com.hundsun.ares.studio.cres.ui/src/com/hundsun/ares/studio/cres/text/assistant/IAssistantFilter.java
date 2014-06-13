package com.hundsun.ares.studio.cres.text.assistant;


/**
 * @author wangxh
 *	过滤智能提示项，如过滤掉非依赖模块的LF、AF等
 */
public interface IAssistantFilter {
	/**每次提示前有些初始化操作放在这里，比如超找模块依赖项等*/
	void init();
	
	boolean filter(Object obj);
}
