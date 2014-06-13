/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 错误号明细对象
 * 
 * @author yanwj06282
 *
 */
public interface IErrorItemScriptWrap extends IMetadataItemScriptWrap{
	
	/**
	 * 获取错误号
	 * 
	 * @return
	 */
	public String getErrorNo();
	
	/**
	 * 获取错误信息
	 * 
	 * @return
	 */
	public String getErrorInfo();
	
	/**
	 * 获取错误号常量
	 * 
	 * @return
	 */
	public String getCnstName();
	
	/**
	 * 获取错误级别
	 * 
	 * @return
	 */
	public String getErrorLevel();
	
}
