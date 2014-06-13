/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.celleditor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 长文本编辑对话框。
 * 
 * @author mawb
 */
public class LongTxtDialog extends Dialog {
	private StyledText longTxtText;
	private String text = "";
	
	public LongTxtDialog(Shell parent) {
		super(parent);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("长文本编辑");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite content = (Composite) super.createDialogArea(parent);
		Composite comp = new Composite(content, SWT.NONE);
		comp.setLayout(new GridLayout());
		longTxtText = new StyledText(comp, SWT.MULTI | SWT.BORDER | SWT.WRAP |SWT.V_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 400;
		longTxtText.setFont(JFaceResources.getTextFont());//设置成等宽字体
		longTxtText.setLayoutData(gd);
		longTxtText.setText(text);
		longTxtText.setSelection(text.length());
		return parent;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == Window.OK) {
			text = longTxtText.getText(); 
		}
		super.buttonPressed(buttonId);
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Object getResult() {
		return text;
	}

}
