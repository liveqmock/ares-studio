/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.ui.ARESElementWizard;
import com.hundsun.ares.studio.ui.ARESResourceCategory;


/**
 * ARES资源创建向导的基类。
 * @author sundl
 */
public abstract class ARESResourceWizard extends ARESElementWizard {

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		Object obj = selection.getFirstElement();
		if (obj instanceof ARESResourceCategory) {
			selectedElement = ((ARESResourceCategory)obj).getModule();
		} else if (obj instanceof IARESResource) {
			selectedElement = ((IARESResource) obj).getModule();
		}
		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getResType());		
		setWindowTitle("新建" + resDescriptor.getName());
	}
	
	public void addPages() {
		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getResType());		
		page = new ARESResourceWizardPage("新建一个" + resDescriptor.getName(), workbench, selectedElement, getResType());
		page.setDescription("新建一个" + resDescriptor.getName());
		page.setNewName(initText_Name);
		addPage(page);
	}
	
	protected abstract String getResType();
	
}
