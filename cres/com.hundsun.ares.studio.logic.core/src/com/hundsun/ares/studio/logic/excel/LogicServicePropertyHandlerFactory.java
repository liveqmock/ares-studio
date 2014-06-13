/**
 * 源程序名称：LogicServicePropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.logic.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.logic.excel;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.logic.LogicPackage;

/**
 * @author sundl
 *
 */
public class LogicServicePropertyHandlerFactory extends LogicPropertyHandlerFactory {

	public static final LogicServicePropertyHandlerFactory INSTANCE = new LogicServicePropertyHandlerFactory();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return LogicPackage.Literals.LOGIC_SERVICE;
	}

}
