/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.usermacro.compiler.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.constants.IUserMacroResType;


/**
 * @author zhuyf
 *
 */
public class UserMacroTokenService implements IUserMacroTokenService {
	
	
	Map<String, UserMacroItem> macroMap = null;
	Map<String,IMacroTokenHandler> macroHandlerMap = new HashMap<String,IMacroTokenHandler>();
	Map<String, Object[]> fomateMap = new HashMap<String, Object[]>();
	IARESProject project;
	boolean procedure = false;
	//变量前缀
	
	public UserMacroTokenService(IARESProject project ,boolean procedure){
		this.project = project;
		this.procedure = procedure;
		if(null == macroMap){
			macroMap = new HashMap<String, UserMacroItem>();
			try {
				IARESResource[]  resources = project.getResources(IUserMacroResType.USER_MACRO);
				if(null != resources && resources.length > 0){
					UserMacro userMacro =  resources[0].getInfo(UserMacro.class);
					for(UserMacroItem item:userMacro.getMacroItems()){
						macroMap.put(item.getName(), item);
						macroHandlerMap.put(item.getName(),new UserMacroHandler(item));
					}
				}
			} catch (Exception e) {
			}
		}
	}

	@Override
	public boolean isUserMacro(String macroName) {
		return macroMap.containsKey(macroName);
	}

	@Override
	public IMacroTokenHandler getUserMacroHandler(String macroName, Map<Object, Object> context,Map<String, Object> paramsMap) {
		 IMacroTokenHandler handler = macroHandlerMap.get(macroName);
		if (handler instanceof UserMacroHandler) {
			((UserMacroHandler) handler).setParamsMap(paramsMap);
		}
		return handler;
	}

	@Override
	public Set<IMacroTokenHandler> getUserMacros() {
		Set<IMacroTokenHandler> tset = new HashSet<IMacroTokenHandler>();
		for(IMacroTokenHandler item:macroHandlerMap.values()){
			tset.add(item);
		}
		return tset;
	}


	@Override
	public UserMacroItem getUserMacro(String macroName) {
		return macroMap.get(macroName);
	}
	
}
