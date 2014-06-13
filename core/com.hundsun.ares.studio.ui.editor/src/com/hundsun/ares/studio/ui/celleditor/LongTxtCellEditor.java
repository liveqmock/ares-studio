/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.celleditor;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 长文本单元格编辑器。
 * @author mawb
 */

public class LongTxtCellEditor extends DialogCellEditor {
	public LongTxtCellEditor(Composite parent) {
		super(parent);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		LongTxtDialog dialog = new LongTxtDialog(cellEditorWindow.getShell());
		dialog.setText(getValue().toString());
		dialog.setBlockOnOpen(true);
		int returnCode = dialog.open();
		return (returnCode == Window.OK) ? dialog.getResult() : null;
		
	}
	
}
