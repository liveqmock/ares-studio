package com.hundsun.ares.studio.engin.token;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DomainHandler implements IDomainHandler{

	List<ITokenDomain> domainList = new ArrayList<ITokenDomain>();
	
	/**
	 * 添加域
	 * @param domain
	 */
	public void addDomain(ITokenDomain domain){
		domainList.add(domain);
	}
	
	/**
	 * 获取域参数
	 * @param key
	 * @return
	 */
	public Object[] getDomainArgs(String key){
		for(int index = domainList.size() -1;index > -1;index-- ){
			if(StringUtils.equals(domainList.get(index).getKey(),key)){
				return domainList.get(index).getArgs();
			}
		}
		return null;
	}
	
	/**
	 * 删除域
	 * @param key
	 */
	public void removeDomain(String key){
		for(int index = domainList.size() -1;index > -1;index-- ){
			if(StringUtils.equals(domainList.get(index).getKey(),key)){
				domainList.remove(index);
				break;
			}
		}
	}

	@Override
	public ITokenDomain[] getDomains() {
		return domainList.toArray(new ITokenDomain[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.IDomainHandler#getDomain(java.lang.String)
	 */
	@Override
	public ITokenDomain getDomain(String key) {
		for(int index = domainList.size() -1;index > -1;index-- ){
			if(StringUtils.equals(domainList.get(index).getKey(),key)){
				return domainList.get(index);
			}
		}
		return null;
	}
}
