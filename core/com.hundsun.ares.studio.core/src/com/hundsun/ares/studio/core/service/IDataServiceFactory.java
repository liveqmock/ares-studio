/**
 * 源程序名称：IDataServiceFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.core.service;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * 数据服务创建工程
 * @author gongyf
 *
 */
public interface IDataServiceFactory {
	
	/**
	 * 给指定的工程创建数据服务
	 * @param project
	 * @return
	 */
	IDataService createService(IARESProject project);
}
