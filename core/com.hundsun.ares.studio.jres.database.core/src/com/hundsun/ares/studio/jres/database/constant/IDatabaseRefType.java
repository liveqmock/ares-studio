/**
 * 源程序名称：IDatabaseRefType.java
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
public interface IDatabaseRefType {
	/**
	 * 数据库表
	 */
	public static final String Table = "jres.db.table";
	
	/**
	 * 数据库表字段
	 */
	public static final String TableField = "jres.db.table.field";
	
	/**
	 * 数据库表索引
	 */
	public static final String TableIndex = "jres.db.table.index";
	
	/**
	 * 数据库视图
	 */
	public static final String View = "jres.db.view";
	
	String Sequence = "jres_osequence";
	String Trigger = "jres_otrigger";
}
