/**
 * 源程序名称：PropertyHandlerFactoryProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;

/**
 * 
 * @author sundl
 */
public interface IPropertyHandlerFactoryProvider {

	IPropertyHandlerFactory getFactory(EClass eClass);
}
