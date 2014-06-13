/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.ui.wizards;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.internal.core.ARESProject;

/**
 * @author gongyf
 *
 */
public class ExportExcelWizardPage extends SelectProjectAndExcelFileWizardPage {

	
	
	/**
	 * @param pageName
	 * @param selection
	 */
	public ExportExcelWizardPage(String pageName,
			IStructuredSelection selection) {
		super(pageName, selection);
		
		setTitle("导出元数据");
		setDescription("选择需要导出元数据的JRES模块工程和导出的元数据定义文件");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage#newFileDialog(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected FileDialog newFileDialog(Shell shell) {
		FileDialog dlg = new FileDialog(shell, SWT.SAVE);
		
		return dlg;
	}
	
	protected void validate() {
		if (project == null || !(ARESProject.hasARESNature(project))) {
			setErrorMessage("请选择一个元数据工程");
			setPageComplete(false);
		} else if (excelFile == null || StringUtils.isEmpty(excelFile.getName())) {
			setErrorMessage("请选择要导出的元数据定义文件");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

}
