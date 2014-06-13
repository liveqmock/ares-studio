package com.hundsun.ares.studio.engin.macrohandler;

import java.util.Iterator;
import java.util.Map;

import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

public interface IMacroTokenHandler {

	public static final String NL = ITokenConstant.NL;
	
	/**
	 * 获取宏标识
	 * @return
	 */
	public String getKey();
	
	/**
	 * 处理宏token
	 * @param token
	 * @param context
	 * @return
	 */
	public Iterator<ICodeToken> handle(IMacroToken token,Map<Object, Object> context)throws Exception;
	
}
