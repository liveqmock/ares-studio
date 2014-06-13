package com.hundsun.ares.studio.engin.token;

/**
 * 占位token
 *
 */
public interface IPlaceholderToken extends ICodeToken{

	/**
	 * 获取占位的key
	 * @return
	 */
	public String getKey();
	
	/**
	 * 获取参数
	 * @return
	 */
	public String[] getArgs();
}
