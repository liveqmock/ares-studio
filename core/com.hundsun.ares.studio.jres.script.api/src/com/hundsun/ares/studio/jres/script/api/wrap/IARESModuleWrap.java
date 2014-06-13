/**
 * 源程序名称：IARESModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicModuleWrap;


/**
 * @author sundl
 *
 */
public interface IARESModuleWrap  extends IObjectModuleWrap, IBizModuleWrap, ILogicModuleWrap{

	/**
	 * 短名，英文名
	 */
	public String getName();
	
	/**
	 * 长名，英文名
	 */
	public String getFullyQualifiedName();
	
	/**
	 * 获取中文名（短名）
	 */
	public String getCName();
	
	/**
	 * 获取父模块，如果已经是顶层模块，则返回null
	 * @return
	 */
	IARESModuleWrap getParent();
	
//	/**
//	 * 获取子模块（仅直接子模块）
//	 * @return
//	 */
//	IARESModuleWrap[] getSubModules();
	
	/**
	 * 返回所属的顶层模块（一般也叫子系统），如果这个模块已经是顶层了，返回它自己。
	 * @return
	 */
	IARESModuleWrap getTopModule();
	
}
