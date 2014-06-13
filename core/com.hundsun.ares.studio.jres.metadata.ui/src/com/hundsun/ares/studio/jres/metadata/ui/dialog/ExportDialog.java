/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class ExportDialog extends ImportExportDialog {
	
	private Button exportButton;
	private Image image;
	private  String dialogMessage = "";
	
	public ExportDialog(Shell parentShell,String title,Image image,String dialogMessage) {
		super("Export", parentShell);
		this.title = title;
		this.image = image;
		this.dialogMessage = dialogMessage;
	}
	
	public boolean validate(String fileText) {
		 if (filePath == null || (filePath != null && !filePath.equals(fileText))) {
				setErrorMessage("必须选择正确的导出文件的路径才能进行导入操作!");
				exportButton.setEnabled(false);
			return false;
		}
		setErrorMessage(null);
		exportButton.setEnabled(true);
		return true;
	}
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		newShell.setText(title);
		if(image!=null){
			newShell.setImage(image);
		}
	}
	@Override
	protected Composite createExtendContentPane(Composite parent) {
		return null;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		exportButton =createButton(parent, IDialogConstants.OK_ID, "导出", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "取消", false);
		exportButton.setEnabled(false);
	}

	@Override
	protected String getAreaMessage() {
		return dialogMessage;
	}

	@Override
	protected String getAreaTitle() {
		return title;
	}
	
}
