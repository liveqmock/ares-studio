/**
 * 源程序名称：IDBExtendedPropertyProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui;

/**
 * 数据库扩展属性提供器，可以扩展表基本信息、表字段、表索引和视图基本信息
 * @author gongyf
 *
 */
public interface IDBExtendedPropertyProvider {
	IFormExtendedPropertyDecription[] getTablePropertyDescriptions();
	IFormExtendedPropertyDecription[] getViewPropertyDescriptions();
	IColumnViewerExtendedPropertyDecription[] getIndexPropertyDescriptions();
	IColumnViewerExtendedPropertyDecription[] getColumnPropertyDescriptions();
}
