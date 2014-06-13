/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author sundl
 */
public class DialogHelper {
	
	private static final String[] LABELS = new String[] {
									IDialogConstants.YES_LABEL,
									IDialogConstants.YES_TO_ALL_LABEL,
									IDialogConstants.NO_LABEL,
									IDialogConstants.NO_TO_ALL_LABEL,
									IDialogConstants.CANCEL_LABEL };;
									
	private static final int[] RESULT_IDS = new int[] {
									IDialogConstants.YES_ID,
									IDialogConstants.YES_TO_ALL_ID,
									IDialogConstants.NO_ID,
									IDialogConstants.NO_TO_ALL_ID};

	public static int YESYESTOALLNONOTOALLConfirm(Shell shell, String title, String question) {
		int result = 0;
		MessageDialog dialog= new MessageDialog(
				shell,
				title,
				null,
				question,
				MessageDialog.QUESTION,
				LABELS,
				0);
		
		dialog.open();
		
		if (dialog.getReturnCode() == -1) { 
			result = IDialogConstants.CANCEL_ID ;
		} else {
			result = RESULT_IDS[dialog.getReturnCode()];
		}
		
		return result;
	}

	public static void showMessage(String title, String message) {
		MessageBox mb = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		mb.setText(title);
		mb.setMessage(message);
		mb.open();
	}

	public static void showErrorMessage(String message) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog dialog = new MessageDialog(shell, "错误", null, message, MessageDialog.ERROR, new String[]{"OK"}, 0);
		dialog.open();
	}
	
	public static void showWaringMessage(String message) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog dialog = new MessageDialog(shell, "警告", null, message, MessageDialog.WARNING, new String[]{"OK"}, 0);
		dialog.open();
	}

	/**
	 * 打开一个错误提示信息框
	 * 
	 * @param shell
	 * @param e
	 */
	public static void openErrorDialog(Shell shell, Throwable e) {
		while (e.getCause() != null && e.getCause() != e) {
			e = e.getCause();
		}
		String message = e.getLocalizedMessage();
		if (StringUtils.isBlank(message)) {
			message = e.getMessage();
		}
		if (StringUtils.isBlank(message)) {
			message = e.toString();
		}
	
		MessageDialog.openError(shell, "错误", message);
	}

	
}
