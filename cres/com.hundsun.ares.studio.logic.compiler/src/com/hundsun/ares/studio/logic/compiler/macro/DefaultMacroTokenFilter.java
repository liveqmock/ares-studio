/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.macro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenFilter;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicSkeletonFactoryConstant;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.constants.IUserMacroResType;

/**
 * @author qinyuan
 *
 */
public class DefaultMacroTokenFilter implements IMacroTokenFilter{
	
	private static String logic = "逻辑";
	Map<String, Set<String>> macroMap = new HashMap<String, Set<String>>();

	public DefaultMacroTokenFilter(IARESProject project){
		// TODO 系统宏,逻辑层目前只有【手工打包】系统宏
		try {
		IARESResource[] sysMacroRes = project.getResources(IUserMacroResType.SYSTEM_MACRO);
			if (sysMacroRes != null && sysMacroRes.length > 0) {
				UserMacro sysMacro = sysMacroRes[0].getInfo(UserMacro.class);
				addMacros(sysMacro);
			}
			
		} catch (Exception e) {
		}
		try {
			// 用户自定义宏
			IARESResource[] userMacroRes = project.getResources(IUserMacroResType.USER_MACRO);
			if (userMacroRes != null && userMacroRes.length > 0) {
				UserMacro userMacro = userMacroRes[0].getInfo(UserMacro.class);
				addMacros(userMacro);
			}
			
		} catch (Exception e) {
		}
	}
	
	
	private void addMacros(UserMacro sysMacro){
		if ( null == sysMacro) {
			return;
		}
		for(UserMacroItem item:sysMacro.getMacroItems()){
			if(StringUtils.isBlank(item.getType())){
				continue;
			}
			String[] types = StringUtils.split(item.getType(),",");
			for(String type:types){
				if(!macroMap.containsKey(type)){
					macroMap.put(type, new HashSet());
				}
				macroMap.get(type).add(item.getName());
			}
		}
	}
	
	private String getSkeletonInputType(ISkeletonInput input){
		if(ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_FUNCTION.equals(input.getType())){
			return logic;
		}
		
		if(ILogicSkeletonFactoryConstant.SKELETONTYPE_LOGIC_SERVICE.equals(input.getType())){
			return logic;
		}
		
		return null;
	}
	
	@Override
	public boolean filte(ISkeletonInput input, IMacroToken token) {
		String type = getSkeletonInputType(input);
		if(!macroMap.containsKey(type)){
			return false;
		}
		return macroMap.get(type).contains(token.getKeyword());
	}

	@Override
	public String[] getMacroDefine(ISkeletonInput input) {
		String type = getSkeletonInputType(input);
		if(macroMap.containsKey(type)){
			return macroMap.get(getSkeletonInputType(input)).toArray(new String[0]);
		}
		return new String[0];
	}
}
