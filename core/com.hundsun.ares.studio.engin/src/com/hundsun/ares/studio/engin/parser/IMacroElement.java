package com.hundsun.ares.studio.engin.parser;

public interface IMacroElement {

	/**
	 * 获取关键字
	 * @return
	 */
	public String getKeyword();

	/**
	 * 获取标志位
	 * @return
	 */
	public String getFlag();

	/**
	 * 获取宏参数
	 * @return
	 */
	public String[] getParameters();
	
	/**
	 * 获取行号
	 * @return
	 */
	public int getLineNo();
	
	/**
	 *  获取别名
	 * @return
	 */
	public String getAliasName();
}
