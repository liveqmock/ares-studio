package com.hundsun.ares.studio.engin.token;

/**
 * token对应的域
 * @author Administrator
 *
 */
public interface ITokenDomain {
	/**全局*/
	public static final String GLOABL = "gloabl";
	
	/**由另一个域结束*/
	public static final String END_BY_OTHER = "end_by_other";

	/**
	 * 获取域类型
	 * @return
	 */
	public String getType();
	
	/**
	 * 获取占位的key
	 * @return
	 */
	public String getKey();
	
	/**
	 * 获取参数
	 * @return
	 */
	public Object[] getArgs();
}
