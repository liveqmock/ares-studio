package com.hundsun.ares.studio.atom.compiler.mysql.macro.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomSkeletonFactoryConstantMySQL;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenFilter;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.constants.IUserMacroResType;

public class DefaultMacroTokenFilter implements IMacroTokenFilter{
	
	private static String atom = "原子";
	
	Map<String, Set<String>> macroMap = new HashMap<String, Set<String>>();
	public DefaultMacroTokenFilter(IARESProject project){
		// 系统宏
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
		if(IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_FUNCTION_MYSQL.equals(input.getType())){
			return atom;
		}
		
		if(IAtomSkeletonFactoryConstantMySQL.SKELETONTYPE_ATOM_SERVICE_MYSQL.equals(input.getType())){
			return atom;
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
