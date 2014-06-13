/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.model;

import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleWrap;



/**
 * @author lvgao
 *
 */
public interface IResourceModelWrap extends IScriptModelWrap{

	/**
	 * 资源所属的顶层模块，也叫子系统
	 * @return
	 */
	IARESModuleWrap getTopModule();
	
	/**
	 * 获取资源全名，即带有模块名前缀的，例如
	 * com.hundsun.ares.User
	 * @return
	 */
	public String getFullyQualifiedName();

	/**
	 * 转化成副本可编辑模式，用于写入信息,修改属性之前必须调用
	 * 
	 */
	public void becomeWorkingCopy();
	
	/**
	 * 保存资源
	 * 主要用于，写入资源信息后，保存操作
	 * 
	 */
	public void save();
	
}
