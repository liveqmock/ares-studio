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
public interface ILogicFunctionMacroTokenService {
	
	/**
	 * 是否为逻辑服务
	 * @param functionName
	 * @return
	 */
	boolean isLogicFunction(String functionName);

	
	/**
	 * @param functionName
	 * @return
	 */
	IMacroTokenHandler getHandler(String functionName);
	
	/**
	 * 获取资源
	 * @param functionName
	 * @return
	 */
	IARESResource getARESResource(String functionName);
}
