/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.core.model.ModuleRootProperty;
import com.hundsun.ares.studio.ui.page.RootPropertyBasicPage;

/**
 * 
 * @author sundl
 */
public class RootPropertyEditor extends AbstractHSFormEditor<ModuleRootProperty> {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.BasicAresFormEditor#getModelType()
	 */
	@Override
	protected Class getModelType() {
		return ModuleRootProperty.class;
	}
	
	@Override
	protected void addPages() {
		RootPropertyBasicPage basic = new RootPropertyBasicPage(this);
		try {
			addPage(basic);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.addPages();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isReadOnly()) {
			MessageDialog.openInformation(getSite().getShell(), "无法保存", "当前资源是只读状态，无法进行保存");
		} 
		else {
			super.doSave(monitor);
		}
	}

}
