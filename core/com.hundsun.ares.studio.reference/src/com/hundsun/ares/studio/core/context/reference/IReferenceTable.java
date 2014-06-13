/**
 * 源程序名称：IReferenceTable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context.reference;

public interface IReferenceTable {
	/////////////////////////////////key定义/////////////////////////////////////////
	public static final String TARGET_OWNER = "所属引用项";
	public static final String TARGET_RESOURCE = "所属资源";
	
	///////////////////////////////值定义////////////////////////////////////////////
	public static final String VALUE_RES_IS_LIB_TRUE = "1"; 
	public static final String VALUE_RES_IS_LIB_FALSE = "0"; 

	
	//////////////////////////////////字段定义/////////////////////////////////////
	/**主资源对象索引**/
	public static final String MASTER_INDEX = "id";
	/**源ID**/
	public static final String PROVIDER_ID = "providerid";
	/**主资源绝对名称**/
	public static final String MASTER_NAME = "m_name";
	/**主资源类型**/
	public static final String MASTER_TYPE = "m_type";
	/**主资源命名空间**/
	public static final String MASTER_NAMESPACE = "m_namespace";
	/**引用资源名**/
	public static final String SLAVE_NAME = "s_name";
	/**引用类型**/
	public static final String SLAVE_TYPE = "s_type";
	/**引用命名空间**/
	public static final String SLAVE_NAMESPACE = "s_namespace";
	/**是否是引用资源包资源**/
	public static final String RES_IS_LIB = "is_lib";
}
