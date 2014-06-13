/**
 * 源程序名称：IValidateControl.java
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
 * 界面错误检查
 * @author lvgao
 *
 */
public interface IValidateControl {

	/**
	 * 获取检查上下文
	 * @return
	 */
	public Map<Object, Object> getContext();
	
	public void setContext(Map<Object, Object> context);
	
	public void setProblemPool(IProblemPool pool);
	
	public IProblemPool getProblemPool();

	/**
	 * 添加检查单元
	 * @param markHelper
	 */
	public void addValidateUnit(IValidateUnit validateUnit);
	
	/**
	 * 移除检查单元
	 * @param markHelper
	 */
	public void removeValidateUnit(IValidateUnit validateUnit);
	
	/**
	 * 触发所有的检查
	 */
	public void refresh();
	
	/**
	 * 触发某一个检查器
	 */
	public void refresh(final IValidateUnit validateUnit);
	
	/**
	 * 清空所有
	 */
	public void destroyAll();
}
