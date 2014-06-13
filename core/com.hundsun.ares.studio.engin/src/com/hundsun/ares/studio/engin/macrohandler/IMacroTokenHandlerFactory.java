package com.hundsun.ares.studio.engin.macrohandler;

import java.util.Set;

public interface IMacroTokenHandlerFactory {

	/**
	 * 是否能处理此宏
	 * @param key
	 * @return
	 */
	public boolean canHandle(String key);
	
	/**
	 * 创建宏节点处理类
	 * @param key
	 * @return
	 */
	public IMacroTokenHandler create(String key);
	
	/**
	 * 获取可以要处理的宏
	 * @return
	 */
	public Set<IMacroTokenHandler> getHandledMacros();
}
