/**
 * 源程序名称：DBTableImportWizard.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.ExcelHandlerException;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.pages.ImportExcelDBTableWizardPage;
import com.hundsun.ares.studio.jres.database.utils.DatabaseUtils;

/**
 * @author yanwj06282
 *
 */
public class DBTableImportWizard extends Wizard implements IImportWizard {

	private Logger logger = Logger.getLogger(DBTableImportWizard.class);
	
	private IStructuredSelection selection;
	private ImportExcelDBTableWizardPage page;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		setWindowTitle("导入数据库");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	}
	
	@Override
	public void addPages() {
		addPage(page = new ImportExcelDBTableWizardPage("", selection));
	}
	
	@Override
	public boolean performFinish() {
		Job job = new Job("数据库导入"){
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				File file = page.getExcelFile();
				FileInputStream is = null;
				if (file.exists()) {
					try {
						Map<String, List<Map<String, Object>>>  exMaps = DatabaseUtils.readTableExcel(is = new FileInputStream(file), 1, 1);
						IARESProject project = ARESCore.create(page.getSelectedProject());
						final List<String> result = DatabaseUtils.importDatabaseTableData(exMaps, project ,page.isClearModule(), monitor);
						if (result.size() > 0) {
							final StringBuffer sb = new StringBuffer();
							for (String re : result) {
								if (StringUtils.isNotBlank(sb.toString())) {
									sb.append(",");
								}
								sb.append(re);
							}
							Display.getDefault().syncExec(new Runnable() {
								@Override
								public void run() {
									MessageDialog.openWarning(getShell(), "数据库表导入", "由于目录页信息缺失，部分Sheet页信息未导入: ["+sb+"]");
								}
							});
							logger.warn("[数据库表导入]:  由于目录页信息缺失，部分Sheet页信息未导入: ["+sb+"]");
						}
					} catch (final Exception e1) {
						Display.getDefault().syncExec(new Runnable() {
							@Override
							public void run() {
								if(e1 instanceof ExcelHandlerException){
									MessageDialog.openError(getShell(), "数据库表导入", e1.getMessage());
								}else{
									String message = "选择的数据库表Excel格式不正确,请选择正确的数据库表Excel";
									MessageDialog.openError(getShell(), "数据库表导入", message);
								}
							}
						});
						e1.printStackTrace();
					}finally {
						if (is != null) {
							try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		
	
		return true;
	}

}
