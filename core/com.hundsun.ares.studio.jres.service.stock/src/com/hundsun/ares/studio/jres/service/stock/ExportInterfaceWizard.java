package com.hundsun.ares.studio.jres.service.stock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.ui.wizard.ExportExcelWizard;
import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ResourcesUtil;

public class ExportInterfaceWizard extends ExportExcelWizard implements IExportWizard {

	public static final Logger LOGGER = Logger.getLogger(ExportInterfaceWizard.class);
	
	ExportInterfaceWizardPage page;
	IARESElement selection;
		
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("导出服务接口文档");
		if (!selection.isEmpty()) {
			Object object = selection.getFirstElement();
			if (object instanceof IARESElement) {
				this.selection = (IARESElement) object;
			} else if (object instanceof IResource) {
				this.selection = ARESCore.create((IResource) object);
			}
		}
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public void addPages() {
		IARESModule module = null;
		if (selection instanceof IARESModule) {
			module = (IARESModule) selection;
		}
		page = new ExportInterfaceWizardPage(selection.getARESProject(), module);
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					// 过滤： 过滤逻辑可以考虑后续的分离
					// 
					monitor.beginTask("", 1000 + (1000 * page.services.keySet().size()));
					monitor.subTask("分析资源...");
					page.filter();
					monitor.worked(1000);
					
					for (IARESModule module : page.services.keySet()) {
						monitor.subTask("导出: " + ResourcesUtil.getChineseFileName("_", module) + "...");

						ExcelBuilder builder = new ExcelBuilder(project);
						Multimap<IARESModule, IARESResource> services = ArrayListMultimap.create();
						services.putAll(module, page.services.get(module));
						builder.services = services;
						//builder.project = project;
						builder.bizScopes = page.selectedBizScopes;
						builder.templatePath = page.templatePath;
						builder.build();
						fileNames.add(builder.fileName);

						try {
							builder.writeFile();
						} catch (IOException e) {
							throw new InvocationTargetException(e);
						}
						monitor.worked(1000);
					}
					
					for (String file : fileNames) {
						openFile(file);
					}
					monitor.done();

				}
			});
		} catch (InvocationTargetException e) {
			LOGGER.error(e);
			Throwable t = e.getTargetException();
			if (t instanceof FileNotFoundException) {
				MessageDialog.openError(getShell(), "错误", String.format("写文件失败，可能文件正在被其他程序使用: %s", fileNames.size() == 0 ? "" : fileNames.get(fileNames.size() - 1)));
			}
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		// FIXME: 异常处理

		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.ui.wizard.ExportExcelWizard#createExportOperation(com.hundsun.ares.studio.core.IARESProject, java.util.List, java.lang.String)
	 */
	@Override
	public IWorkspaceRunnable createExportOperation(IARESProject project, List<IARESResource> results, String file) {
		return null;
	}

}
