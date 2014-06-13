package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.ExcelHandlerException;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;

public class ImportBasicdataExcelWizard extends Wizard implements IImportWizard, IOverwriteQuery {

	private static final Logger logger = Logger.getLogger(ImportBasicdataExcelWizard.class);
	private IWorkbench workbench;
	private IStructuredSelection selection;
	private ImportWizardPage page;
	protected IARESProject project;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		Object obj = selection.getFirstElement();
		if (obj instanceof IARESElement) {
			project = ((IARESElement) obj).getARESProject();
		} else if (obj instanceof IResource) {
			project = ARESElementUtil.toARESElement(obj).getARESProject();
		}
		setNeedsProgressMonitor(true);
		
		IDialogSettings settings = BasicDataUI.getPlugin().getDialogSettings().getSection("importwizard");
		if (settings == null)
			settings = BasicDataUI.getPlugin().getDialogSettings().addNewSection("importwizard");
		setDialogSettings(settings);
	}

	@Override
	public void addPages() {
		page = new ImportWizardPage("选择文件", project);
		addPage(page);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
	    setWindowTitle("导入基础数据");
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin("com.hundsun.ares.studio.jres.basicdata.resources", "icons/data.gif").createImage());
		page.setTitle("导入基础数据");
		page.setDescription("选择需要导入的基础数据.");
		page.setMessage("选择需要导入的基础数据(文件或者目录)");
	}

	@Override
	public boolean performFinish() {
		if (this.project != null) {
			File[] files = page.getFiles();
			boolean needReport = page.btNeedReport.getSelection();
			final ImportBasicdataOperation operation = createImportOperation(files);
			
			try {
				getContainer().run(true, true, new IRunnableWithProgress() {
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						try {
							ResourcesPlugin.getWorkspace().run(operation, monitor);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				Throwable cause = e.getTargetException().getCause();
				if(cause != null && cause instanceof ExcelHandlerException){
					MessageDialog.openError(getShell(), "导入发生异常", cause.getMessage());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (needReport) {
				try {
					File file = File.createTempFile("ares_studio_", ".html");
					operation.log.generateReport(new FileOutputStream(file));
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(file.toURI().toURL());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		} else {
			MessageDialog.openError(getShell(), "错误", "无法定位项目");
		}
		return true;
	}
	
	protected ImportBasicdataOperation createImportOperation(File[] files){
		ImportBasicdataOperation operation = new ImportBasicdataOperation(project ,files);
		return operation;
	}
	
	
	public String queryOverwrite(String pathString) {
		String messageString = String.format("资源\"%s\"已存在，是否覆盖？", pathString);
		final MessageDialog dialog = new MessageDialog(getShell(), 
				"确认", null,
				messageString, MessageDialog.QUESTION, new String[] {
						IDialogConstants.YES_LABEL,
						IDialogConstants.YES_TO_ALL_LABEL,
						IDialogConstants.NO_LABEL,
						IDialogConstants.NO_TO_ALL_LABEL,
						IDialogConstants.CANCEL_LABEL }, 0) {
			protected int getShellStyle() {
				return super.getShellStyle() | SWT.SHEET;
			}
		};
		String[] response = new String[] { YES, ALL, NO, NO_ALL, CANCEL };
		// run in syncExec because callback is from an operation,
		// which is probably not running in the UI thread.
		getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				dialog.open();
			}
		});
		return dialog.getReturnCode() < 0 ? CANCEL : response[dialog.getReturnCode()];
	}
}
