/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 修订记录
 * 
 * @author yanwj06282
 *
 */
public interface IRevHistoryScriptWrap extends IScriptModelWrap{

	/**
	 * 单条的修订记录 
	 * 
	 * @return
	 */
	public String getHistoryComment();
	
	/**
	 * 资源对象
	 * 
	 * @return
	 */
	public IDatabaseResScriptWrap getResourceInfo ();
	
	/**
	 * 版本号
	 * 
	 * @return
	 */
	public String getVersion();
	
	/**
	 * 修改日期
	 * 
	 * @return
	 */
	public String getModifiedDate();
	
	/**
	 * 修改原因
	 * 
	 * @return
	 */
	public String getModifiedReason();
	
	/**
	 * 修改内容
	 * 
	 * @return
	 */
	public String getModified();
	
	/**
	 * 修改人
	 * 
	 * @return
	 */
	public String getModifiedBy();
	
	/**
	 * 修改单号
	 * 
	 * @return
	 */
	public String getOrderNumber();
	
	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getComment();
	
	/**
	 * 申请人
	 * 
	 * @return
	 */
	public String getCharger();
	
}
