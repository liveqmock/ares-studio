/**
 * 源程序名称：INamespaceHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author lvgao
 *
 */
public interface INamespaceHelper {

	/**
	 * 获取引用资源的命名空间
	 * @param master
	 * @param referdata
	 * @return
	 */
	public String getSlaveNamespace(IARESResource master, String referdata); 
	
	/**
	 * 获取引用资源的命名空间
	 * @param master
	 * @param referdata
	 * @return
	 */
	public String getSlaveNamespace(String masternamespace, String referdata); 
	
	/**
	 * 获取命名空间，如果字符串中没有命名空间则返回空字符串
	 * @param referdata
	 * @return 保证不返回空指针
	 */
	public String getNamespace(String referdata);
	
	
	public String getResourceNamespace(IARESResource master);
	/**
	 * 去掉引用的命名空间
	 * @param referdata
	 * @return
	 */
	public String removeNamespace(String referdata);
	
	/**
	 *获取项目的命名空间
	 * @param project
	 * @return
	 */
	public String getProjectNamespace(IARESProject project);
	
	/**
	 * 默认的帮助实现
	 */
	public INamespaceHelper INSTANCE = new DefaultNamespaceHelper();
}
