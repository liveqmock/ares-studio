/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.macro.service;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;

/**
 * @author qinyuan
 *
 */
public interface IAtomServiceMacroTonkenService {

	/**
	 * 是否为原子服务
	 * @param serviceName
	 * @return
	 */
	boolean isAtomService(String serviceName);
	
	/**
	 * @param serviceName
	 * @return
	 */
	IMacroTokenHandler getHandler(String serviceName);
	
	/**
	 * 获取资源
	 * @param serviceName
	 * @return
	 */
	IARESResource getAresResource(String serviceName);
}
