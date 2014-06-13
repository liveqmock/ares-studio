package com.hundsun.ares.studio.engin.token;

public interface ITokenEvent {

	/**
	 * 获取内容
	 * @return
	 */
	public String getKey();
	
	/**
	 * 获取数据
	 * @return
	 */
	public Object getData();
}
