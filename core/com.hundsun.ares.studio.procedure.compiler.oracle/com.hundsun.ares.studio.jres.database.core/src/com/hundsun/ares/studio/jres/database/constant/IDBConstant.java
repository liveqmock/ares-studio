/**
 * 源程序名称：IDBConstant.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.constant;


/**
 * @author gongyf
 *
 */
public interface IDBConstant {
	
	/**
	 * 首选项配置的key
	 * 
	 */
	public static final String TABLE_NAME_LENGTH = "table_name_length";//数据库表长度
	public static final String TABLE_COLUMN_LENGTH= "table_column_length";//数据库表列长度
	public static final String INDEX_LENGTH = "table_index_length";//数据库索引长度
	public static final String CONSTRAINT_LENGTH = "table_constraint_length";//约束名长度
	
	/**
	 * 模块工程扩展模型的键值
	 */
	public static final String MODULE_DATABASE_EXTEND_PROPERTY_KEY = "DBModuleCommonProperty";
	
	/**
	 * 生成数据库内容脚本文件名样式
	 * @deprecated 将被新版的脚本替换
	 */
	public static final String GEN_JS_FILE_FORMAT = "database_generate_%s.js";
	
	
	/**
	 * 经过优化后，生成数据库内容的脚本
	 */
	public static final String GEN_JS_FILE_DATABASE = "db_gensql_oracle.js";
	
	/**
	 * 生成数据库表空间的脚本
	 */
	public static final String GEN_TABLE_SPACE_JS_FILE_DATABASE = "db_gensql_ts_oracle.js";
	
	/**
	 * 生成数据库用户的脚本
	 */
	public static final String GEN_DATABASE_USER_JS_FILE_DATABASE = "db_gensql_user_oracle.js";
	
	/**
	 * 用于编译数据库创建脚本的类型
	 * 最终用于编译的对象可能是 数据库根、数据库模块、某个数据库资源
	 */
	public static final String COMPILE_DATABASE_FULL = "#database.gensql.full#";
	
	/**
	 * 用于编译数据库升级脚本的类型
	 * 最终用于编译的对象可能是 数据库根、数据库模块、某个数据库资源
	 */
	public static final String COMPILE_DATABASE_PATCH = "#database.gensql.patch#";
	
	/**
	 * 表空间
	 */
	public static final String COMPILE_DATABASE_SPACE = "#database.space.gensql.full#";
	
	/**
	 * 数据库用户
	 */
	public static final String COMPILE_DATABASE_USER = "#database.user.gensql.full#";
	
	/**
	 * 生成脚本内数据库表的主调方法
	 * 
	 */
	public static final String JS_GEN_TABLE_INFO_FUNCTION = "genTableResource";
	
	/**
	 * 生成脚本内视图的主调方法
	 * 
	 */
	public static final String JS_GEN_VIEW_INFO_FUNCTION = "genViewResource";
	
	/**
	 * 生成脚本内序列的主调方法
	 * 
	 */
	public static final String JS_GEN_OSEQUENCE_INFO_FUNCTION = "genSequenceResource";
	
	/**
	 * 生成脚本内触发器的主调方法
	 * 
	 */
	public static final String JS_GEN_OTRIGGER_INFO_FUNCTION = "genTriggerResource";
	
	/**
	 * 生成外键脚本内的主调方法
	 * 
	 */
	public static final String JS_GEN_INFO_FOREIGN_KEY_FUNCTION = "getTableForeignKeySql";
	
	/**
	 * 生成脚本中用于生成补丁的主调方法
	 */
	public static final String JS_GEN_INFO_PATCH_FUNCTION = "genTableResourcePatch";
	
	public static final String JS_GEN_TABLE_SPACE_INFO_FUNCTION = "genTableSpaceResource";
	
	public static final String JS_GEN_DATABASE_USER_INFO_FUNCTION = "genDatabaseUserResource";
	
	/**
	 * js脚本中向导上下文的key
	 */
	public static final String KEY_JS_GEN_CONTEXT = "genContext";
	
	/**
	 * 表编辑器的编辑域ID
	 */
	public static final String ID_TABLE_EDITDOMAIN = "com.hundsun.ares.studio.jres.database.resource.table";
	
	/**
	 * 视图编辑器的编辑域ID
	 */
	public static final String ID_VIEW_EDITDOMAIN = "com.hundsun.ares.studio.jres.database.resource.view";
	
	/**
	 * 数据库类型
	 */
	public static final String DATABASE_TYPE = "";
	
	/**
	 * 数据库模块根类型
	 */
	public static final String ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.database";
	/**表对象号范围扩展key*/
	public static final String TABLE_ID_RANGE_KEY = "tableidrange";
	/**视图对象号范围扩展key*/
	public static final String VIEW_ID_RANGE_KEY = "viewidrange";
	/**视图对象号范围扩展key*/
	public static final String SEQUENCE_ID_RANGE_KEY = "sequenceidrange";
	
	/**项目属性扩展　是否使用非标准字段 */
	public static final String USE_NON_STD_FIELD = "use_non_std_field";
}
