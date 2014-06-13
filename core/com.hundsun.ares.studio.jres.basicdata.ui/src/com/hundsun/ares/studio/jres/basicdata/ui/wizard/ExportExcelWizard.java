/**
 * 
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.progress.IProgressService;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.ui.util.DialogHelper;

/**
 * @author yanwj06282
 *
 */
public abstract class ExportExcelWizard extends Wizard implements IExportWizard {

	protected IStructuredSelection selection;
	protected ExportWizardPage page;
	protected IARESProject project;
	protected String moduleRootName;
	
	public void setModuleRootName(String moduleRootName){
		this.moduleRootName = moduleRootName;
	}
	
	public ExportExcelWizard() {
		IFolder rootFolder = ARESElementUtil.getModuleRootFolder(this.project,"com.hundsun.ares.studio.jres.moduleroot.database");
		if( rootFolder != null){
			this.moduleRootName = rootFolder.getName();//这里必须通过扩展点得到文件夹名，未必是database
		}
	}
	
	public ExportExcelWizard(String moduleRootName) {
		this.moduleRootName = moduleRootName;
	}

	@Override
	public void addPages() {
		addPage(page = new ExportWizardPage("", selection ,moduleRootName));
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		Object obj = selection.getFirstElement();
		if (obj instanceof IARESElement) {
			project = ((IARESElement) obj).getARESProject();
		} else if (obj instanceof IResource) {
			project = ARESElementUtil.toARESElement(obj).getARESProject();
		}
		setNeedsProgressMonitor(true);
		
		IDialogSettings settings = BasicDataUI.getPlugin().getDialogSettings().getSection("exportwizard");
		if (settings == null)
			settings = BasicDataUI.getPlugin().getDialogSettings().addNewSection("exportwizard");
		setDialogSettings(settings);
	}

	@Override
	public boolean performFinish() {
		if (this.project != null) {
			String file = page.getPath();
			List<IARESResource> results = page.getResult();
			final IWorkspaceRunnable operation = createExportOperation(project , results , file);
			
			{

				IRunnableWithProgress runnable = new IRunnableWithProgress() {
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
							InterruptedException {
						try {
							ResourcesPlugin.getWorkspace().run(operation, monitor);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				};
				try {
					MessageDialog msgdialog = null;
					try {
						IProgressService progress = MetadataUI.getDefault().getWorkbench()
								.getProgressService();
						progress.run(true, false, runnable);
					} catch (InvocationTargetException e) {
						msgdialog = new MessageDialog(null, "导出失败", null,
								e.getTargetException().getMessage(), MessageDialog.WARNING,
								new String[] { "确定" }, 0);
						e.getTargetException().printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (null != msgdialog){
						msgdialog.open();
						return false;
					}else {
						msgdialog = new MessageDialog(null, "导出成功", null,
								String.format("导出路径为%s,是否打开文件？", file), MessageDialog.INFORMATION, new String[] {
										"确定","取消"}, 0);
						if(Window.OK == msgdialog.open()){
							openFile(file);
						}
						return true;
					}
				} catch (Exception e) {
					DialogHelper.showErrorMessage(e.getMessage());
					return false;
				}	
			
			}
		} else {
			MessageDialog.openError(getShell(), "错误", "无法定位项目");
		}
		return true;
	}
	
	private void openFile(final String path) {    
	    Runtime rn = Runtime.getRuntime();    
	    String cmd="cmd.exe /c start \"\" \"" + path + "\"";  
	    try {    
	        rn.exec(cmd);  
	    } catch (Exception e) { 
	    	e.printStackTrace();
	    }    
	}

	/**
	 * 创建线程操作
	 * 
	 * @param project ARES工程对象
	 * @param results 需要导出的模块
	 * @param file 导出路径
	 * @return
	 */
	public abstract IWorkspaceRunnable createExportOperation(IARESProject project ,List<IARESResource> results ,String file);
	
}
