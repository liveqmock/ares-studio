package com.hundsun.ares.studio.engin.token;

public interface IDomainHandler {

	/**
	 * 获取域，如果不存在此域返回null
	 * @param key
	 * @return
	 */
	public ITokenDomain getDomain(String key);
	
	/**
	 * 添加域
	 * @param domain
	 */
	public void addDomain(ITokenDomain domain);
	
	/**
	 * 获取域参数
	 * @param key
	 * @return
	 */
	public Object[] getDomainArgs(String key);
	
	
	/**
	 * 删除域
	 * @param key
	 */
	public void removeDomain(String key);
	
	/**
	 * 获取域
	 * @return
	 */
	public ITokenDomain[] getDomains();
}
