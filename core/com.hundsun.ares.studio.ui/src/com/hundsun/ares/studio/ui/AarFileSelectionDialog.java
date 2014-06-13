/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

/**
 * 选择Aar文件的对话框。
 * @author sundl
 */
public class AarFileSelectionDialog extends ElementTreeSelectionDialog {

	/**
	 * @param parent
	 * @param labelProvider
	 * @param contentProvider
	 */
	public AarFileSelectionDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
		
	}

}
