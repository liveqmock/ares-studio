/**
 * 源程序名称：DatabaseServiceFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.internal.service;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.IDataService;
import com.hundsun.ares.studio.core.service.IDataServiceFactory;

/**
 * @author gongyf
 *
 */
public class DatabaseServiceFactory implements IDataServiceFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.service.IDataServiceFactory#createService(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public IDataService createService(IARESProject project) {
		return new BasicDatabaseService(project);
	}

}
