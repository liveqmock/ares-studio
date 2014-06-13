/**
 * 源程序名称：IFormExtendedPropertyDecription.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;

/**
 * @author gongyf
 *
 */
public interface IFormExtendedPropertyDecription {
	
	
	/**
	 * 创建编辑控件
	 * @param parent
	 * @return
	 */
	Control createControl(Composite parent);
	
	/**
	 * 可能被多次调用
	 * @param info
	 */
	void setInfo(DatabaseResourceData info);
	
	/**
	 * 设置数据绑定上下文
	 * @param context
	 */
	void setDataBindingContext(DataBindingContext context);
	
	/**
	 * 设置只读控制器
	 * @param editableControl
	 */
	void setEditableControl(IEditableControl editableControl);
}
