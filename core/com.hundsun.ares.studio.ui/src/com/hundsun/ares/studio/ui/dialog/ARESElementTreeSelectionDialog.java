/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.dialog;

import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;

/**
 * ARES模型树选择框。
 * 
 * @author mawb
 */
public class ARESElementTreeSelectionDialog {
	private ElementTreeSelectionDialog dialog;
	
	public ARESElementTreeSelectionDialog(Shell parent, String title) {
		init(parent, title);
	}
	
	/**
	 * 初始化模型选择框。
	 * 
	 * @param parent
	 */
	private void init(Shell parent, String title) {
		CommonElementContentProvider ccp = new CommonElementContentProvider();
		CommonElementLabelProvider clp = new CommonElementLabelProvider(ccp);
		dialog = new ElementTreeSelectionDialog(parent, clp, ccp);
		dialog.setTitle(title);
	}
	
	public void setInput(Object input) {
		dialog.setInput(input);
	}
	
	public void addFilter(ViewerFilter filter) {
		dialog.addFilter(filter);
	}
	
	public void setValidator(ISelectionStatusValidator validator) {
		dialog.setValidator(validator);
	}

	public int open() {
		return dialog.open();
	}
	
	public Object[] getResult() {
		return dialog.getResult();
	}
	
	public Object getFirstResult() {
		return dialog.getFirstResult();
	}

}
