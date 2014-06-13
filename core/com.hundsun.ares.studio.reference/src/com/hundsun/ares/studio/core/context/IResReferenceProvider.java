/**
 * 源程序名称：IResReferenceProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

/**
 * 资源引用信息提供器
 * @author lvgao
 *
 */
public interface IResReferenceProvider {

	/**
	 * 获取主资源引用的资源列表
	 * @param masterUniqueName    主资源绝对名
	 * @param type                主资源类型
	 * @return
	 */
	public Object[] getReferList(String masterUniqueName,String masterNamespace,String masterType);
	
	
	/**
	 * 获取链接了此资源的资源列表
	 * @param uniqueName        资源绝对名
	 * @param type              资源类型
	 * @return
	 */
	public Object[] getLinkList(String uniqueName,String namespace,String type);
}
