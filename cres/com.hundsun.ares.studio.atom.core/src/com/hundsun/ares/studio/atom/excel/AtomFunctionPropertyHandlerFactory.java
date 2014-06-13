/**
 * 源程序名称：AtomFunctionPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.atom.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.atom.excel;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.atom.AtomPackage;

/**
 * 原子函数对应的 ProperytyHandlerFactory
 * @author sundl
 *
 */
public class AtomFunctionPropertyHandlerFactory extends AtomPropertyHandlerFactory {

	public static final AtomFunctionPropertyHandlerFactory INSTANCE = new AtomFunctionPropertyHandlerFactory();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return AtomPackage.Literals.ATOM_FUNCTION;
	}

}
