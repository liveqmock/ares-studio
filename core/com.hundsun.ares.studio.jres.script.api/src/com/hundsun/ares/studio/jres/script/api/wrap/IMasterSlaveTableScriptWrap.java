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
public interface IMasterSlaveTableScriptWrap extends ISingleTableScriptWrap {

	/**
	 * 获取从表字段
	 * @return
	 */
	public IStandardFieldScriptWrap[] getSlaveStandardFields();
	
	/**
	 * 获取从表属性
	 * @return
	 */
	public String[] getSlaveAttrs();
	
	/**
	 * 获取从表名
	 * @return
	 */
	public String getSlaveTableName();
	
}
