/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.ui.ARESResourceCategory;
import com.hundsun.ares.studio.ui.ElementSelectionWizardPageWithNameInput;

/**
 * 模块向导页面
 * @author sundl
 */
public class ARESModuleWizardPage extends ElementSelectionWizardPageWithNameInput {

	protected Text tx_CName;
	protected String cName = StringUtils.EMPTY;
	
	protected IARESModule createdModule;
	
	public ARESModuleWizardPage(String pageName, IWorkbench workbench,
			IARESElement selection) {
		super(pageName, workbench, selection);
	}
	
	@Override
	protected int[] getDisplayedElementTypes() {
		return new int[] {IARESElement.ARES_PROJECT, IARESElement.COMMON_MODULE_ROOT, IARESElement.COMMON_MODULE};
	}

	@Override
	protected String[][] getSelctingElementTypes() {
		return new String[][] {
				{String.valueOf(IARESElement.COMMON_MODULE_ROOT), "模块根"},
				{String.valueOf(IARESElement.COMMON_MODULE), "子系统"}
		};
	}
	
	protected void createText(Composite composite){
		super.createText(composite);
		Label lb_CName = new Label(composite, SWT.NONE);
		lb_CName.setText("中文名:");
		GridDataFactory.fillDefaults().applyTo(lb_CName);
		
		// 中文名
		tx_CName = new Text(composite, SWT.BORDER);
		GridDataFactory.fillDefaults().applyTo(tx_CName);
		tx_CName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				cName = tx_CName.getText();
				updateComplete();
			}
		});
		
	}
	
	public boolean validate() {
		if (!super.validate())
			return false;

		boolean alreadyExsits = false;
		if (selection instanceof IARESModule) {
			IARESModuleRoot root = ((IARESModule) selection).getRoot();
			String fullName = ((IARESModule) selection).getElementName() + "." + getNewName();
			IARESModule newModule = root.findModule(fullName);
			if (newModule != null && newModule.exists()) {
				alreadyExsits = true;
			}
		} else if (selection instanceof IARESModuleRoot) {
			IARESModuleRoot root = (IARESModuleRoot) selection;
			IARESModule newModule = root.findModule(getNewName());
			if (newModule != null && newModule.exists()) {
				alreadyExsits = true;
			}
		}

		if (alreadyExsits) {
			setErrorMessage(getNewName() + "已经存在!");
			return false;
		} 
		
		if (StringUtils.isEmpty(cName)) {
			setErrorMessage("中文名不能为空!");
			return false;
		}
			
		setErrorMessage(null);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.ARESElementSelectionWizardPageWithNameInput#finishPage()
	 */
	@Override
	public boolean finishPage() {
		final IARESModuleRoot[] root = new IARESModuleRoot[1];
		final String[] moduleName = new String[1];
		if (selection instanceof IARESModuleRoot) {
			root[0] = (IARESModuleRoot)selection;
			moduleName[0] = getNewName();
		} else if (selection instanceof IARESModule) {
			IARESModule module = (IARESModule)selection;
			root[0] = module.getRoot();
			moduleName[0] = module.getElementName() + "." + getNewName();
		}
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					createdModule = root[0].createModule(moduleName[0]);
					ModuleProperty property = new ModuleProperty();
					property.setValue(ICommonModel.CNAME, cName);
					createdModule.createResource(IARESModule.MODULE_PROPERTY_FILE, property);
				}
			}, 
			null);
			return true;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.CommonElementSelectionPage#filte(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected boolean filte(Viewer viewer, Object parentElement, Object element) {
		if (!super.filte(viewer, parentElement, element)) {
			return false;
		}
		if (element instanceof ARESResourceCategory) {
			return false;
		}
		return true;
	}
	
}
