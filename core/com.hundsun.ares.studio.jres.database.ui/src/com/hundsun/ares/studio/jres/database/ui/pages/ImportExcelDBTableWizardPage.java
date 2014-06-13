/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.database.ui.pages;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage;

/**
 * @author yanwj06282
 *
 */
public class ImportExcelDBTableWizardPage extends SelectProjectAndExcelFileWizardPage {

	private boolean isClearModule;
	
	/**
	 * @param pageName
	 * @param selection
	 */
	public ImportExcelDBTableWizardPage(String pageName,
			IStructuredSelection selection) {
		super(pageName, selection);
		setTitle("导入数据库表");
		setDescription("选择需要导入数据库表的工程和导入的数据库表定义文件");
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
			setErrorMessage("请选择一个JRES模块工程");
			setPageComplete(false);
		} else if (excelFile == null || StringUtils.isEmpty(excelFile.getName()) || !excelFile.exists()) {
			setErrorMessage("请选择要导入的数据库表定义文件");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.wizards.SelectProjectAndExcelFileWizardPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Composite client = (Composite) getControl();
		Composite subClient = new Composite(client, SWT.NONE);
		final Button isClearbtn = new Button(subClient, SWT.CHECK);
		isClearbtn.setText("导入前清空模块");
		isClearbtn.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				isClearModule=isClearbtn.getSelection();
			}
		});
		GridLayoutFactory.swtDefaults().applyTo(subClient);
		GridDataFactory.swtDefaults().applyTo(subClient);
		GridDataFactory.swtDefaults().applyTo(isClearbtn);
	}
	
	public boolean isClearModule(){
		return isClearModule;
	}
	
}
