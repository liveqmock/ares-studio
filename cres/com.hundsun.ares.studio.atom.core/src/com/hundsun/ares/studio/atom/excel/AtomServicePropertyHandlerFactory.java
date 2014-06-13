/**
 * 源程序名称：AtomServicePropertyHandlerFactory.java
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
 * 原子服务对应的PropertyHandlerFactory
 * @author sundl
 *
 */
public class AtomServicePropertyHandlerFactory extends AtomPropertyHandlerFactory {

	public static final AtomServicePropertyHandlerFactory INSTANCE = new  AtomServicePropertyHandlerFactory();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return AtomPackage.Literals.ATOM_SERVICE;
	}

}
