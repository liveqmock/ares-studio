/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 功能对象
 * 
 * @author yanwj06282
 *
 */
public interface IMenuFunctionScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取功能编号
	 * 
	 * @return
	 */
	public String getFunctionCode();
	
	/**
	 * 获取功能名称
	 * 
	 * @return
	 */
	public String getFunctionName();
	
	/**
	 * 获得服务名称
	 * 
	 * @return
	 */
	public String getServiceName();
	
	/**
	 * 获取交易码
	 * 
	 * @return
	 */
	public String getSubTransCode();
	
	/**
	 * 设置功能编号
	 * 
	 * @param functionCode
	 */
	public void setFunctionCode(String functionCode);
	
	/**
	 * 设置功能名称
	 * 
	 * @param functionName
	 */
	public void setFunctionName(String functionName);
	
	/**
	 * 设置服务名称
	 * 
	 * @param serviceName
	 */
	public void setServiceName(String serviceName);
	
	/**
	 * 设置子交易码
	 * 
	 * @param subTransCode
	 */
	public void setSubTransCode(String subTransCode);
	
}
