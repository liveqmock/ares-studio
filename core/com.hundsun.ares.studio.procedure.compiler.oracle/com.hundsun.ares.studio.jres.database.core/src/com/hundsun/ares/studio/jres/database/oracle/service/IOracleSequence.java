/**
 * 源程序名称：IOrcaleSequence.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.service;

/**
 * @author wangxh
 *
 */
public interface IOracleSequence {
	String getName();
	String getChineseName();
	String getDescription();
	
	
	String getTableName();
	String getStart();
	String getIncrement();
	String getMinValue();
	String getMaxValue();
	boolean isCycle();
	boolean isUseCache();
	String getCache();
}
