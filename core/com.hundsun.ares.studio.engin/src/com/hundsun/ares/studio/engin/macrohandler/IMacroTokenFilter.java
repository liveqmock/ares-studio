package com.hundsun.ares.studio.engin.macrohandler;

import com.hundsun.ares.studio.engin.skeleton.ISkeletonInput;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

public interface IMacroTokenFilter{

	/**
	 * 根据蓝图过滤宏
	 * @param input
	 * @param token
	 * @return
	 */
	public boolean filte(ISkeletonInput input,IMacroToken token);
	
	/**
	 * 根据蓝图获取宏定义
	 * @param input
	 * @return
	 */
	public String[] getMacroDefine(ISkeletonInput input);
}
