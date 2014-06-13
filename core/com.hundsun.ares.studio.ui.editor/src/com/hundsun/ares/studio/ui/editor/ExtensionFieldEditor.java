/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;


/**
 * 扩展字段
 * @author sundl
 */
public abstract class ExtensionFieldEditor implements IEditable{

	/**
	 * 初始化，框架会传入当前的项目属性对象的副本供编辑。
	 * @param properties 项目属性
	 */
	public abstract void init(IARESProjectProperty properties);
	
	/**
	 * 创建界面控件
	 * @param parent
	 */
	public abstract void createControls(FormToolkit toolkit, ImporveControlWithDitryStateContext context);

	/**
	 * 刷新界面。
	 */
	public abstract void refresh();
	
}
