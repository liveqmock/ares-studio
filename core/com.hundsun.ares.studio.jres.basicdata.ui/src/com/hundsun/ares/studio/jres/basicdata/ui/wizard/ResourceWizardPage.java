/**
 * 源程序名称：NewUFTResourceWizardPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.uft.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.ByteArrayInputStream;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.ui.AresResourceCategoryFilter;
import com.hundsun.ares.studio.ui.AresResourceFilter;
import com.hundsun.ares.studio.ui.RefLibContainer;
import com.hundsun.ares.studio.ui.wizard.ARESResourceWizardPage;

/**
 * @author lvgao
 *
 */
public class ResourceWizardPage extends ARESResourceWizardPage {

	protected IARESProject project;
	protected Text txt_sourceFolder;
	protected Text txt_packageName;
	protected Label moduleNameLabel;
	protected Label packageNameLable;
	
	/**
	 * @param pageName
	 * @param workbench
	 * @param selection
	 * @param resType
	 */
	public ResourceWizardPage(String pageName, IWorkbench workbench, IARESElement selection, String resType) {
		super(pageName, workbench, selection, resType);
		this.project = selection.getARESProject();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.ARESResourceWizardPage#createText(org.eclipse.swt.widgets.Composite, org.eclipse.swt.layout.GridData)
	 */
	@Override
	protected void createText(Composite composite) {
		if (selection == null ) {
			return ;
		}
		IARESElement module = (IARESElement) selection;
		moduleNameLabel = new Label(composite, SWT.NONE);
		moduleNameLabel.setText("模块:");
		GridData gd = new GridData();
		moduleNameLabel.setLayoutData(gd);
		txt_sourceFolder = new Text(composite, SWT.BORDER);
		txt_sourceFolder.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt_sourceFolder.setEditable(false);
		txt_sourceFolder.setText(getSourceFolderText());
		
		packageNameLable = new Label(composite, SWT.NONE);
		packageNameLable.setText("包名:");
		gd = new GridData();
		packageNameLable.setLayoutData(gd);
		txt_packageName = new Text(composite, SWT.BORDER);
		txt_packageName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt_packageName.setText(module.getElementName());
		
		txt_packageName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updateComplete();
			}
		});
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelectionProvider().getSelection();
					if (selection.getFirstElement() == null) {
						selection = null;
						return;
					}
					if (selection.getFirstElement() instanceof IARESModule) {
						txt_packageName.setText(((IARESModule) selection.getFirstElement()).getElementName());
					}
				}
			}
		});
		super.createText(composite);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.wizard.ARESResourceWizardPage#validate()
	 */
	@Override
	public boolean validate() {
		if (!super.validate()) {
			return false;
		}
		IStatus s = checkPackageName(txt_packageName.getText());
		if (!s.isOK() ) {
			setErrorMessage(s.getMessage());
			return false;
		}
		return true;
	}
	

	
	@Override
	protected void addFilter() {
		super.addFilter();
		if(viewer != null){
			viewer.addFilter(new AresResourceCategoryFilter(){
				@Override
				public boolean select(Viewer viewer, Object parentElement,
						Object element) {
					//排除非本工程的显示
					if (element instanceof IARESProject && !project.getElementName().equals(((IARESProject) element).getElementName())) {
						return false;
					}
					if (!isProject(element)) {
						return false;
					}
					return super.select(viewer, parentElement, element);
				}
			});
			viewer.addFilter(new AresResourceFilter(){
				@Override
				public boolean select(Viewer viewer, Object parentElement,
						Object element) {
					//过滤引用
					if (element instanceof RefLibContainer) {
						return false;
					}
					//过滤项目属性
					if (element instanceof IProjectProperty) {
						return false;
					}
					return super.select(viewer, parentElement, element);
				}
			});
			
		}
	}
	
	protected boolean isProject (Object element){
		if (element instanceof IARESProject) {
//			IARESProject aresProject = (IARESProject) element;
//			IProject project = aresProject.getProject();
//			try {
//				if (!project.hasNature(IUFTConstant.NATURE_ID)) 
//					return false;
//			} catch (CoreException e) {
//				e.printStackTrace();
//			}
		}
		return true;
	}
	
	private String getSourceFolderText(){
		String projectName = project.getElementName();
		if (getModuleRoot() == null) {
			return "";
		}
		String moduleRootName = getModuleRoot().getElementName();
		return projectName + "/" + moduleRootName;
	}
	
	/**
	 * 创建模块
	 * 
	 * @param moduleName
	 */
	private void createModule(final String moduleName){
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
					if (getModuleRoot() == null) {
						return;
					}
					IARESModule createdModule = getModuleRoot().createModule(moduleName);
					ModuleProperty property = new ModuleProperty();
					property.setValue(ICommonModel.CNAME, StringUtils.substringAfterLast(moduleName, "."));
					createdModule.createResource(IARESModule.MODULE_PROPERTY_FILE, property);
				}
			}, 
			null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	protected void checkPath(String path) {
		IFolder folder = null;
		String cp = "";
		String[] ps = path.split("/");
		for (String p : ps) {
			cp += "/" + p;
			folder = project.getProject().getFolder("/"+getModuleRoot().getElementName() + cp);
			if (!folder.exists()) {
				createModule(cp.replaceAll("/", "\\."));
			}
		}
	}
	
	public  IStatus checkPackageName(String packageName) {
		if (StringUtils.isEmpty(packageName)) {
			return Status.OK_STATUS;
		}
		
		return validatePackageName(packageName);
	}
	
	public static IStatus validatePackageName(String name) {
		String PLUGIN_ID = "com.hundsun.ares.studio.jres.basicdata.ui";
		
		if (name == null) {
			return new Status(IStatus.ERROR, PLUGIN_ID, -1, "模块名不能为空", null);
		}
		int length;
		if ((length = name.length()) == 0) {
			return new Status(IStatus.ERROR, PLUGIN_ID, -1, "模块名不能为空", null);
		}
		if (name.charAt(0) == '.' || name.charAt(length-1) == '.') {
			return new Status(IStatus.ERROR, PLUGIN_ID, -1, "模块名不能以点开头或结尾", null);
		}

		int dot = 0;
		while (dot != -1 && dot < length-1) {
			if ((dot = name.indexOf('.', dot+1)) != -1 && dot < length-1 && name.charAt(dot+1) == '.') {
				return new Status(IStatus.ERROR, PLUGIN_ID, -1, "模块名不能有连续的点", null);
				}
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		StringTokenizer st = new StringTokenizer(name, "."); //$NON-NLS-1$
		while (st.hasMoreTokens()) {
			String typeName = st.nextToken();
			typeName = typeName.trim(); // grammar allows spaces
			
			IStatus status = workspace.validateName(typeName, IResource.FOLDER);
			if (!status.isOK()) {
				return status;
			}
		}
		return Status.OK_STATUS;
	}
	
	protected IARESModuleRoot getModuleRoot(){
		if (selection instanceof IARESModuleRoot) {
			return (IARESModuleRoot) selection;
		}else if (selection instanceof IARESModule){
			return ((IARESModule)selection).getRoot();
		}else {
			IARESProject project = getARESProject((IARESElement)selection);
			try {
				String[] moduleRoots = ModuleRootType2ResTypeMap.getInstance().getAllowedRootTypes(IBasicDataRestypes.singleTable);
				for (String moduleRoot : moduleRoots) {
					for (IARESModuleRoot root : project.getModuleRoots()) {
						if (moduleRoot.equals(root.getType())) {
							return root;
						}
					}
				}

			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private IARESProject getARESProject(IARESElement element){
		if (element instanceof IARESProject) {
			return (IARESProject)element;
		}
		return getARESProject(element.getParent());
	}
	
	
	@Override
	protected void createResource() {
		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getType());
		if (resDescriptor != null) {
			Object info = resDescriptor.createInfo();
			initNewResourceInfo(info);
			IModelConverter converter = resDescriptor.getConverter();
			IARESElement selection = getSelectedElement();
			if (selection.getResource().getType() == IResource.FOLDER) {
				String name = getNewName();
				name = name + "." + getType();
				IFolder folder = (IFolder)selection.getResource();
				IFile file = folder.getFile(name);
				if (!file.exists()) {
					try {
						IARESResource resource = (IARESResource) ARESCore.create(file);
						file.create(new ByteArrayInputStream(((IModelConverter2) converter).write(resource, info)),true, null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.resource = file;
				}
			}			
		}
	}
}
