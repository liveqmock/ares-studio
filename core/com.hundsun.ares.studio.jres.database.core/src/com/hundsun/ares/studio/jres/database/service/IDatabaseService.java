/**
 * 源程序名称：IDatabaseService.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.service;

import java.util.List;

import com.hundsun.ares.studio.core.service.IDataService;

/**
 * @author gongyf
 *
 */
public interface IDatabaseService extends IDataService {
	List<? extends ITable> getTableList();
	ITable getTable(String name);
	
	List<? extends IView> getViewList();
	IView getView(String name);
}
