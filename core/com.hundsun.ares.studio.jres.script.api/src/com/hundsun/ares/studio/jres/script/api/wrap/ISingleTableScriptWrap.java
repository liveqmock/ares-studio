/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * @author lvgao
 *
 */
public interface ISingleTableScriptWrap extends IScriptModelWrap,IResourceModifyHistory {

	/**
	 * 获取主表字段
	 * @return
	 */
	public IStandardFieldScriptWrap[] getMasterStandardFields();
	
	/**
	 * 获取主表属性
	 * @return
	 */
	public String[] getMasterAttrs();
	
	/**
	 * 获取主表名
	 * @return
	 */
	public String getMasterTableName();
	
}
