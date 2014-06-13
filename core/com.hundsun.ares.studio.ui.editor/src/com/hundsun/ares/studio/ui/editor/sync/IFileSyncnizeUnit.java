/**
 * 源程序名称：IFileSyncnizeUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.sync;

import org.eclipse.core.resources.IResourceDelta;

/**
 * 文件同步单元
 * @author lvgao
 *
 */
public interface IFileSyncnizeUnit {
	
	/**
	 * 处理非保存动作,外部修改、添加删除等
	 * @param context
	 */
	public void handleAction(IResourceDelta delta);
	
	/**
	 * 保存前动作
	 * 必选在保存前调用
	 */
	public void beforeSave();
}
