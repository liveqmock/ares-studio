/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;

/**
 * @author yanwj06282
 *
 */
public interface IMasterSlaveLinkTableScriptWrap extends IMasterSlaveTableScriptWrap {

	/**
	 * 获取关联表字段
	 * @return
	 */
	public IStandardFieldScriptWrap[] getLinkStandardFields();
	
	/**
	 * 获取关联表属性
	 * @return
	 */
	public String[] getLinkAttrs();
	
	/**
	 * 获取关联表名
	 * @return
	 */
	public String getLinkTableName();
	
}
