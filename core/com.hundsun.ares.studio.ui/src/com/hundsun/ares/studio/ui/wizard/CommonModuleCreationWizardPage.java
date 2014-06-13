/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.Util;
import com.hundsun.ares.studio.internal.core.ARESModule;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;

/**
 * 通用模块新建向导页面。
 * 
 * @author mawb
 */
public class CommonModuleCreationWizardPage extends WizardPage {
	
	private Object selection;
	private IARESModuleRoot currentRoot;
	private IARESModule module;
	
	private Text moduleRootText;
	private Text moduleText;
	
	private IStatus moduleRootStatus = new Status(Status.OK, ARESCore.PLUGIN_ID, Status.OK, "", null);
	private IStatus moduleStatus = new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "", null);
	
	/**
	 * @param pageName
	 */
	protected CommonModuleCreationWizardPage(String pageName, String title) {
		super(pageName);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());
		composite.setLayout(new GridLayout(3, false));
		
		createContainerControls(composite);
		createPackageControls(composite);
		
		setPageComplete(false);
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	}

	/**
	 * 创建模块所在模块根选择框。
	 * 
	 * @param composite
	 */
	private void createContainerControls(Composite composite) {
		new Label(composite, SWT.NULL).setText("模块根：");
		
		moduleRootText = new Text(composite, SWT.BORDER);
		moduleRootText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (currentRoot != null) {
			moduleRootText.setText(currentRoot.getElementName().substring(1));
		}
		moduleRootText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				moduleRootStatus = validateModuleRoot();
				updateStatus();
			}
			
		});
		
		Button button = new Button(composite, SWT.NULL);
		button.setText("选择...");
		
		button.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				ModuleRootContentProvider cp = new ModuleRootContentProvider();
				ElementTreeSelectionDialog dialog = 
					new ElementTreeSelectionDialog(getShell(), new CommonElementLabelProvider(cp), cp);
				dialog.setValidator(new ModuleRootSelectionValidator());
				dialog.setTitle("模块根选择窗口"); 
				dialog.setMessage("请选择一个模块根"); 
				
				dialog.setInput(getARESProjectList());
				dialog.setInitialSelection(selection);
				
				if (dialog.open() == Window.OK) {
					currentRoot = (IARESModuleRoot) dialog.getFirstResult();
					moduleRootText.setText(((IARESModuleRoot)currentRoot).getElementName().substring(1));
				}
			}
		});
	}

	/**
	 * 创建模块名输入框。
	 * 
	 * @param composite
	 */
	private void createPackageControls(Composite composite) {
		new Label(composite, SWT.NULL).setText("模块名：");
		
		moduleText = new Text(composite, SWT.BORDER);
		moduleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (selection instanceof IARESModule) {
			moduleText.setText(((IARESModule) selection).getElementName());
			moduleText.selectAll();
		}
		
		moduleText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				moduleStatus = validateModule();
				updateStatus();
			}
			
		});
	}
	
	/**
	 * 更新页面完成状态。
	 */
	protected void updateStatus() {
		if (moduleRootStatus.getSeverity() == IStatus.OK && moduleStatus.getSeverity() == IStatus.OK) { 
			setPageComplete(true);
	        setErrorMessage(null);
	        setMessage(null);
		} else {
			setPageComplete(false);
			if (moduleRootStatus.getSeverity() == IStatus.ERROR) {
				setErrorMessage(moduleRootStatus.getMessage());
				setMessage(moduleRootStatus.getMessage());
			} else {
				setErrorMessage(moduleStatus.getMessage());
				setMessage(moduleStatus.getMessage());
			}
		}
	}
	
	/**
	 * 校验模块根是否合法。
	 * 
	 * @return
	 */
	private IStatus validateModuleRoot() {
		if (moduleRootText.getText().length() == 0) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "模块根不能为空。", null);
		}
		
		String[] folders = moduleRootText.getText().split("/");
		if (folders.length > 2) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "模块根文件夹不合法。", null);
		}
		
		IARESProject project = ARESCore.getModel().getARESProject(folders[0].trim());
		if (project == null || !project.exists()) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "文件夹\"" + folders[0].trim() + "\"不存在。", null);
		}
		
		currentRoot = project.getModuleRoot(project.getProject().getFolder(folders[1].trim()));
		if (!currentRoot.exists()) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "文件夹\"" + folders[1].trim() + "\"不存在。", null);
		}
		
		if (module != null && !module.exists()) {
			validateModule();
		}
		
		return new Status(Status.OK, ARESCore.PLUGIN_ID, Status.OK, "", null);
		
	}
	
	/**
	 * 校验模块是否合法。
	 * 
	 * @return
	 */
	private IStatus validateModule() {
		if (getModuleName().length() == 0) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "模块名不能为空。", null);
		}
		
		String[] moduleName = Util.getTrimmedSimpleNames(getModuleName());
		if (!Util.isValidNamesForModule(moduleName)) {
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "模块名不合法。", null);
		}
		
		if (currentRoot != null) {
			module = currentRoot.getModule(getModuleName());
			if (module.getResource().exists()) {
				return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "模块已存在。", null);
			}
		}
		
		return new Status(Status.OK, ARESCore.PLUGIN_ID, Status.OK, "", null);
	}
	
	/**
	 * 返回当前模块根。
	 * 
	 * @return
	 */
	public IARESModuleRoot getModuleRoot() {
		return currentRoot;
	}
	
	public String getModuleName() {
		return moduleText.getText();
	}
	
	/**
	 * 获取当前工作空间中的所有ARES工程。
	 * 
	 * @return
	 */
	private List<IARESProject> getARESProjectList() {
		List<IARESProject> projectList = new ArrayList<IARESProject>();
		try {
			IARESProject[] projects = ARESCore.getModel().getARESProjects();
			for (IARESProject project : projects) {
				projectList.add(project);
			}
		} catch (ARESModelException e1) {
			return projectList;
		}
		return projectList;
	}
	
	/**
	 * @param selection the selection to set
	 */
	public void initSelection(Object selection) {
		this.selection = selection;
		if (selection instanceof IARESModuleRoot) {
			currentRoot = (IARESModuleRoot) selection;
		} else if (selection instanceof IARESModule) {
			currentRoot = ((ARESModule)selection).getRoot();
		}
	}
	
	/**
	 * 模块根选择校验器。
	 * 
	 * @author mawb
	 */
	private class ModuleRootSelectionValidator implements ISelectionStatusValidator {
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.dialogs.ISelectionStatusValidator#validate(java.lang.Object[])
		 */
		public IStatus validate(Object[] selection) {
			if (selection.length == 1) {
				if (selection[0] instanceof IARESModuleRoot) {
					return new Status(Status.OK, ARESCore.PLUGIN_ID, Status.OK, "", null);
				}
			}
			return new Status(Status.ERROR, ARESCore.PLUGIN_ID, Status.CANCEL, "", null);
		}
		
	}
	
	/**
	 * 模块根内容提供器。
	 * 
	 * @author mawb
	 */
	private class ModuleRootContentProvider extends CommonElementContentProvider {
		protected final Object[] NO_CHILDREN = new Object[0];
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object element) {
			if (!exists(element))
				return NO_CHILDREN;
			
			try {
				if (element instanceof IARESProject) { 
					return ((IARESProject) element).getModuleRoots();
				}
			} catch (CoreException e) {
				return NO_CHILDREN;
			}
			
			return NO_CHILDREN;	
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			try {
				if (element instanceof IARESProject) {
					return ((IARESProject) element).getModuleRoots().length > 0;
				}
			} catch (CoreException e) {
				return false;
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray(new IARESProject[((List) inputElement).size()]);
			}
			return new Object[] {inputElement};
		}
		
	}
	
	protected boolean exists(Object element) {
		if (element == null) {
			return false;
		}
		if (element instanceof IResource) {
			return ((IResource)element).exists();
		}
		if (element instanceof IARESResource) {
			return ((IARESResource)element).exists();
		}
		return true;
	}
	
}
