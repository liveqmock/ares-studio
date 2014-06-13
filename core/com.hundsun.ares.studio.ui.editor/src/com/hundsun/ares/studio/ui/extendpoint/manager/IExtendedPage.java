/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.extendpoint.manager;

import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.ui.page.IExtendItemLoader;

/**
 * 扩展页面接口
 * @author sundl
 */
public interface IExtendedPage extends IExtendItemLoader{

	public void init(FormEditor editor);
	
	public void onCreate();
	public void beforeSave();
	public void afterSave();
	
}
