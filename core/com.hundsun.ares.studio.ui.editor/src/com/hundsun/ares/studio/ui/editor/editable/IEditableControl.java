/**
 * 源程序名称：IEditableControl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：吕高
 */
package com.hundsun.ares.studio.ui.editor.editable;

import java.util.Map;

/**
 * @author lvgao
 *
 */
public interface IEditableControl {

	/**
	 * 获取资源只读状态
	 * @return
	 */
	public boolean getResourceReadonlyStatus();
	
	/**
	 * 刷新资源只读状态
	 * @return
	 */
	public void refreshResourceReadonlyStatus();
	
	/**
	 * 设置用户状态
	 * @param key
	 * @param status
	 */
	public void putUserStatus(String key,Object status);
	
	
	/**
	 * 通知用户字段状态
	 * @param staus
	 */
	public void notifyUserStatus(String key);
	
	/**
	 * 添加只读控制单元
	 * @param unit
	 */
	public void addEditableUnit(IEditableUnit unit);
	
	/**
	 * 刷新所有只读控制单元的只读状态
	 * @param context    上下文
	 */
	public void refreshAllUnit(Map<Object, Object> context);
}
