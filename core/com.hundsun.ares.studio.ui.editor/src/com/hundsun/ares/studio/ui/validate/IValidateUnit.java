/**
 * 源程序名称：IValidateUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.validate;

import java.util.Map;


/**
 * 错误检查单元
 * @author lvgao
 *
 */
public interface IValidateUnit {
	/**
	 * 更新问题缓存
	 * @param pool      问题缓存
	 * @param context    上下文
	 * @throws Exception
	 */
	public void updateProblemPool(IProblemPool pool, Map<Object, Object> context) throws Exception;

}
