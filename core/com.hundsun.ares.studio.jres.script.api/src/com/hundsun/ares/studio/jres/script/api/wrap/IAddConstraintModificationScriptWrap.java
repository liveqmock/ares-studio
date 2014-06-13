/**
 * 源程序名称：IAddConstraintModificationScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：Dollyn
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableKeyScriptWrap;

/**
 * 增加约束
 * @author sundl
 *
 */
public interface IAddConstraintModificationScriptWrap extends IModificationScriptWrap{
	
	/**
	 * 获取增加的约束列表
	 * @return
	 */
	ITableKeyScriptWrap[] getDetails();
}
