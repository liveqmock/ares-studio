/**
 * 源程序名称：IOracleRefType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.oracle.constant;


/**
 * @author gongyf
 *
 */
public interface IOracleRefType {
	/**
	 * 触发器
	 */
	String Trigger = "jres.db.oracle.trigger";
	
	/**
	 * 序列
	 */
	String Sequence = "jres.db.oracle.sequence";
	
	/**
	 * 表空间
	 */
	String Space = "jres.db.oracle.space";
	
	/**
	 * 表空间关联
	 */
	String SpaceRelation = "jres.db.oracle.space_relation";
	
	/**
	 * 用户权限
	 */
	String User = "jres.db.oracle.user";
	
	
}
