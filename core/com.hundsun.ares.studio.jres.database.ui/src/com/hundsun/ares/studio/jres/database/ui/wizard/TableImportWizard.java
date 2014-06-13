package com.hundsun.ares.studio.jres.database.ui.wizard;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.util.ArrayUtil;
import com.hundsun.ares.studio.internal.core.ProjectProperty;
import com.hundsun.ares.studio.jres.database.pdm.PDMImport;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.pages.TableImportWizardTwo;
import com.hundsun.ares.studio.ui.ARESElementWizard;
import com.hundsun.ares.studio.ui.AresResourceCategoryFilter;
import com.hundsun.ares.studio.ui.AresResourceFilter;
import com.hundsun.ares.studio.ui.LibFilter;
import com.hundsun.ares.studio.ui.wizard.ARESResourceWizardPage;

/**
 * 导入PDM文件
 * 
 * @author maxh
 * 
 */
public class TableImportWizard extends ARESElementWizard {

	private static Logger logger = Logger.getLogger(TableImportWizard.class);
	
	public static String DATABASE_MODULE_ROOT = "database";
	ARESResourceWizardPage page;
	TableImportWizardTwo page2;
	IARESModule module ;
	String resType = "table";
	
	/**
	 * 
	 */
	public TableImportWizard() {
		setWindowTitle("导入PDM");
	}
	
	@Override
	public void addPages() {
		page = new ARESResourceWizardPage("导入PDM", workbench, selectedElement, resType) {
			
			@Override
			public boolean canFlipToNextPage() {
				if (selection instanceof IARESModule) {
					return true;
				}
				return false;
			}

			@Override
			protected void createText(Composite composite) {
				//不需要默认的文本输入框
			}
			
			@Override
			public boolean validate() {
				if (!(selection instanceof IARESModule)) {
					setErrorMessage("请选择一个子系统");
					return false;
				}
				boolean status = super.validate();
				if (status) {
					setErrorMessage(null);
				}
				return status;
			}
			
			
			@Override
			protected void addFilter() {
				if(viewer != null){
					viewer.addFilter(new ViewerFilter(){

						@Override
						public boolean select(Viewer viewer, Object parentElement,
								Object element) {
							if(element instanceof IARESModuleRoot){
								if(resType == null || resType.length() == 0){
									return true;
								}
								String[] allowType = ModuleRootType2ResTypeMap.getInstance().getAllowedResTypes(((IARESModuleRoot) element).getType());
								if(ArrayUtil.findInArray(allowType, resType) < 0){
									return false;
								}
							}
							//过滤工程配置文件
							if(element instanceof ProjectProperty){
								return false;
							}
							//过滤模块,因为模块下不能创建文件夹，所以只留下子系统
							if(element instanceof IARESModule){
								IARESModule module = (IARESModule)element;
								IResource resource = module.getResource();
								if (resource.getType() == IResource.FOLDER) {
									IFolder folder = (IFolder)module.getResource();
									IFile file = folder.getFile(".bm");
									return !(file.exists());
								}
							}
							return true;
						}
						
					});
					
					viewer.addFilter(new LibFilter());
					viewer.addFilter(new AresResourceCategoryFilter());
					viewer.addFilter(new AresResourceFilter());
				}
			}
		};
		page.setNewName("notUsed");
		page.setTitle("导入文件");
		page.setDescription("将PDM文件中的表,视图导入到项目中,并生成相关的数据库表,视图,标准字段,业务数据类型.");
		addPage(page);
		
		IARESElement element = page.getSelectedElement();
		//在弹出框之前，取得默认选中的节点
		module = getModuleFromElement(element);
		page2 = new TableImportWizardTwo("导入PDM",module);
		page2.setDescription("选择需要导入PDM文件");
		addPage(page2);
 		super.addPages();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/pdm.gif").createImage());
	}
	
	@Override
	public boolean canFinish() {
		if (page2 == null) {
			return false;
		}
		if(StringUtils.isBlank(page2.getFile())){//如果没有选择pdm文件
			return false;
		}
		File file = new File(page2.getFile());
		if (!file.exists()) {
			return false;
		}
		return super.canFinish();
	}

	@Override
	public boolean performFinish() {
		Job job = new Job("导入") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				try {
					IARESModule module = (IARESModule) page.getSelectedElement();
					PDMImport pdmImport = new PDMImport();
					pdmImport.importPDM(page2.getFile(),page2.getTargetDir() , monitor,module);
				} catch (final RuntimeException e) {
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							MessageDialog.openError(null, "PDM导入异常", e.getMessage());
						}
					});
					logger.error(e);
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		return true;
	}
	
	private IARESModule getModuleFromElement(IARESElement element){
		try{
			if (element instanceof IARESResource) {
				module = (IARESModule)element.getParent();
			}else if(element instanceof IARESModule){
				module = (IARESModule)element;
			}else{
				IARESProject project =  null;
				if (element instanceof IARESProject) {
					project = (IARESProject)element;
				}else{
					IFolder folder = (IFolder) element.getResource();
					project = ARESCore.create(folder.getProject());
				}
				IARESModuleRoot[] roots = null;
				IARESModule[] modules = null;
				roots = project.getModuleRoots();
				IARESModuleRoot root = null;
				for(IARESModuleRoot r : roots){
					String modulePath = r.getPath().lastSegment().toString();
					if(DATABASE_MODULE_ROOT.equals(modulePath)){
						root = r;
						break;
					}
				}
				if (root == null) {
					return null;
				}
				modules = root.getModules();
				for (IARESModule md:modules) {
					module = md;
					break;
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		return module;
	}
	
}
