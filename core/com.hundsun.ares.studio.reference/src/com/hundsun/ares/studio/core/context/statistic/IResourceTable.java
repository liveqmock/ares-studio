/**
 * 源程序名称：IResourceTable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context.statistic;

public interface IResourceTable {
	
////////////////////////////////目标相关///////////////////////////////
	public static final String TARGET_OWNER = "所属引用项";
	public static final String TARGET_RESOURCE = "所属资源";
	
	
	

////////////////////////常量定义///////////////////////////////////
	public static final String VALUE_RES_IS_LIB_TRUE = "1"; 
	public static final String VALUE_RES_IS_LIB_FALSE = "0"; 
	
	public static final String TREECAHCE_ROOT = "引用信息表树形全局缓存根节点";
	
	/**
	 * 忽略命名空间
	 */
	public static final String Scope_IGNORE_NAMESPACE = "#ignore.namespace#"; 
	
	/**
	 * 当前项目
	 */
	public static final String Scope_CURRENT = "#current#"; 
	
	
	/**
	 * 引用
	 */
	public static final String Scope_REFERENCE = "#reference#"; 
	
	/**
	 * 去掉重名
	 */
	public static final String Scope_UNIQUE = "#unique#"; 
	
	
////////////////////////////字段定义////////////////////////
	/**资源对象索引**/
	public static final String RES_INDEX = "id";
	/**源ID**/
	public static final String PROVIDER_ID = "providerid";
	/**资源名**/
	public static final String RES_NAME = "resname";
//	/**资源长名**/
//	public static final String RES_LONGNAME = "reslongname";
	/**资源类型**/
	public static final String RES_TYPE = "restype";
	/**命名空间**/
	public static final String RES_NAMESPACE = "namespace";
	/**是否是公有资源**/
	public static final String RES_IS_PUBLIC = "is_public";
	/**是否是引用包**/
	public static final String RES_IS_LIB = "is_lib";
}
