/**
 * 源程序名称：IView.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.service;

/**
 * @author gongyf
 *
 */
public interface IView {
	String getName();
	String getChineseName();
	String getDescription();
	
	String getSQL();
}
