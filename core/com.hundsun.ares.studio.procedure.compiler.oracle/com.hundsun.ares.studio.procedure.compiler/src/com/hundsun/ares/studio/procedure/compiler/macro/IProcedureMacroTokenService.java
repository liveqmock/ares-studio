/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.macro;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;


/**
 * @author zhuyf
 *
 */
public interface IProcedureMacroTokenService {
	
	//判断是否是存储过程
	public boolean isProcedure(String procedureName);
	
	public IMacroTokenHandler getHandler(String procedureName);
	
	public IARESResource getProcedure(String procedureName);
	
	

}
