/**
 * 源程序名称：IOracleResType.java
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
public interface IOracleResType{
	/**
	 * 触发器
	 */
	String Trigger = "jres_otrigger";
	/**
	 * 序列
	 */
	String Sequence = "jres_osequence";
	/**
	 * 表空间
	 */
	String Space = "dbobject";
	/**
	 * 用户权限
	 */
	String User = "dbuser";
}
