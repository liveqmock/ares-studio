/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.dialog.ARESResourceSelectionDialog;
import com.hundsun.ares.studio.ui.dialog.ConditionalResourceSelectionDialog;

/**
 * 需要从资源选择列表中过滤掉一部分结果时使用的ARES资源智能提示选择编辑域
 * 
 * @see com.hundsun.ares.studio.ui.control.ARESResourceAssistantAndSelectionField
 * @author yanyl
 */
public abstract class ConditionalARESResourceAssistantAndSelectionField extends ARESResourceAssistantAndSelectionField {
	public ConditionalARESResourceAssistantAndSelectionField(Composite parent, String labelStr, int txtStyle,
			String resType, String dialogTitle) {
		super(parent, labelStr, txtStyle, resType, dialogTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.control.ARESResourceAssistantAndSelectionField
	 * #createDialog(com.hundsun.ares.studio.core.IARESProject,
	 * java.lang.String)
	 */
	@Override
	protected ARESResourceSelectionDialog createDialog(IARESProject project, String resType) {
		return new ConditionalResourceSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				project, resType) {
			@Override
			public String[] getFilterExceptResources() {
				return getExceptResources();
			}
		};
	}

	/**
	 * 获取要排除的资源全路径，没有要排除资源的话可以返回null
	 * 
	 */
	public abstract String[] getExceptResources();
}
