/**
 * 源程序名称：IContextUpdateSource.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

/**
 * 上下文更新源
 * @author lvgao
 *
 */
public interface IContextUpdateSource {

	/**
	 * 更新类型
	 * @return
	 */
	public String getType();
	
	/**
	 * 获取更新相关的数据
	 * @return
	 */
	public Object[] getContent();
}
