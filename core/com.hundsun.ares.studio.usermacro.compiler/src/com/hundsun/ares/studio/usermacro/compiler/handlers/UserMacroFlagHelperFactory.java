/**
 * 
 */
package com.hundsun.ares.studio.usermacro.compiler.handlers;

import org.apache.commons.lang.StringUtils;

/**
 * 标记处理类工厂
 * 
 * @author yanwj06282
 *
 */
public class UserMacroFlagHelperFactory {

	public static IUserMacroFlagHelper getInstance(String flag){
		if (StringUtils.equalsIgnoreCase(flag, "<T>")) {
			return new FlagTHelper();
		}else if (StringUtils.equalsIgnoreCase(flag, "<E>")) {
			return new FlagEHelper();
		}else if (StringUtils.equalsIgnoreCase(flag, "<S>")) {
			return new FlagSHelper();
		}else if (StringUtils.equalsIgnoreCase(flag, "<SP>")) {
			return new FlagSPHelper();
		}
		return null;
	}
	
}
