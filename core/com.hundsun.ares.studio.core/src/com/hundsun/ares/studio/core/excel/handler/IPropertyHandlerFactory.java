/**
 * 源程序名称：IPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * 获取IPropertyHandler的入口。 
 * 需要注意一点：有些handler是和project相关的，所以，不要把获取的handler在不同项目之间传递，
 * 用于不同的project的时候，需要重新调用接口方法获取handler
 * @author sundl
 *
 */
public interface IPropertyHandlerFactory {

	IPropertyHandler getPropertyHandler(String key, IARESProject project);
	
}
