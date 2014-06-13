/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.database.ui.pages;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage;

/**
 * @author yanwj06282
 *
 */
public class ImportStdMergeWizardPage extends SelectProjectAndExcelFileWizardPage {

	/**
	 * @param pageName
	 * @param selection
	 */
	public ImportStdMergeWizardPage(String pageName,
			IStructuredSelection selection) {
		super(pageName, selection);
		
		setTitle("导入标准字段评审记录");
		setDescription("选择标准字段评审记录的定义文件");
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
	    getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin("com.hundsun.ares.studio.jres.metadata.resources", "icons/full/obj16/stdFieldFile.png").createImage());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage#newFileDialog(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected FileDialog newFileDialog(Shell shell) {
		FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		return dlg;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage#validate()
	 */
	@Override
	protected void validate() {
		if (project == null || !(ARESProject.hasARESNature(project))) {
			setErrorMessage("请选择一个ARES工程");
			setPageComplete(false);
		} else if (excelFile == null || StringUtils.isEmpty(excelFile.getName()) || !excelFile.exists()) {
			setErrorMessage("请选择要导如的标准字段合并评审记录文件");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

}
