/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.func;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;


/**
 * @author zhuyf
 *
 */
public interface IFunctionMacroTokenService {
	
	//判断是否是原子函数
	public boolean isAtomFunction(String functionName);
	
	
	public IMacroTokenHandler getHandler(String functionName);
	
	public IARESResource getFunction(String functionName);
	
	

}
