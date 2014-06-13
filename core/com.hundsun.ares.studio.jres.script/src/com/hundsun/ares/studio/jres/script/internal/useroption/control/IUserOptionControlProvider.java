/**
 * 源程序名称：IUserOptionControlProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.internal.useroption.control;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.internal.useroption.IControl;

/**
 * @author sundl
 *
 */
public interface IUserOptionControlProvider {

	IControl createControl();
	
	Control createUIControl(Composite parent, IControl control, IARESProject project);
}
