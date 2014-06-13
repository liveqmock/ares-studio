/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.usermacro.compiler.handlers;

import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.usermacro.UserMacroItem;


/**
 * @author zhuyf
 *
 */
public interface IUserMacroTokenService {
	
	public boolean isUserMacro(String macroName);
	
	/**
	 * 根据宏名获取用户宏条目
	 * 
	 * @param macroName
	 * @return
	 */
	public UserMacroItem getUserMacro(String macroName);
	
   /**
    * 获得用户宏
    * @param macroName
    * @param context
    * @param paramsMap
    * @return
    */
	public IMacroTokenHandler getUserMacroHandler(String macroName,Map<Object, Object> context,Map<String, Object> paramsMap);
	
	
	/**
	 * 获取所有用户宏
	 * @return
	 */
	public Set<IMacroTokenHandler> getUserMacros();

}
