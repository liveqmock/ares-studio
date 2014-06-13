/**
 * 源程序名称：IResStatisticProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

public interface IResStatisticProvider {

	/**
	 * 判定资源是否存在
	 * 用处:引用资源判定   新建资源判定
	 * @param uniqueName
	 * @param restype
	 * @return
	 */
	public boolean isResouceExist(String uniqueName,String namespace,String restype);
	
	/**
	 * 判定资源是否唯一
	 * 用处：重名判定
	 * @param uniqueName
	 * @param restype
	 * @return
	 */
	public boolean isResouceUnique(String uniqueName,String namespace,String restype);
	
	/**
	 * 获取资源
	 * 用处:引用资源跳转
	 * @param uniqueName
	 * @param restype
	 * @return
	 */
	public Object[] getResouce(String uniqueName,String namespace,String restype);
	
	/**
	 * 通过资源类型获取资源
	 * @param restype
	 * @return
	 */
	public Object[] getResouceByType(String restype);
	
	/**
	 * 获取多类型的资源
	 * @param restype
	 * @return
	 */
	public Object[] getResouceByTypes(String[] restypes);
	
	/**
	 * 获取资源的数量
	 * @param uniqueName
	 * @param namespace
	 * @param restype
	 * @return
	 */
	public int getResourceCount(String uniqueName, String namespace,String restype);
	
	/**
	 * 通过索引值获取资源
	 * @param index
	 * @return
	 */
	public Object getResouceByIndex(String index);
}
