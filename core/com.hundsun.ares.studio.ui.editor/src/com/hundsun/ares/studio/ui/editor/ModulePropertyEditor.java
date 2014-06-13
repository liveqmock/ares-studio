package com.hundsun.ares.studio.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.ui.editor.blocks.OpenResourcePage;
import com.hundsun.ares.studio.ui.editor.sync.IFileSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESDefaultSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESEditorSyncManager;

/**
 * 2013年3月7日16:41:47 mod 秦元 模块编辑器标题显示中文名与英文名 
 * @author qinyuan
 */
public class ModulePropertyEditor extends AbstractHSFormEditor<ModuleProperty> {
	//文件同步单元
	private IFileSyncnizeUnit fileSyncUnit;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor#getModelType()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Class getModelType() {
		return ModuleProperty.class;
	}
	
	@Override
	protected void addPages() {
		ModulePropertyBasicPage basic = new ModulePropertyBasicPage(this);
		try {
			addPage(basic);
			IARESElement element = getARESElement();
			if (element instanceof IARESResource) {
				element = ((IARESResource) element).getModule();
			}
			addPage(new OpenResourcePage(this, element, "openresource", "资源列表"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.addPages();
		fileSyncUnit = new JRESDefaultSyncnizeUnit(this);
		JRESEditorSyncManager.getInstance().addSyncUnit(fileSyncUnit);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor#getPartTitleName()
	 * 
	 * 2013年3月7日16:41:47 mod 秦元 模块编辑器标题显示中文名与英文名 
	 */
	@Override
	protected String getPartTitleName() {
		String eName = getResource().getModule().getShortName();
		
		return String.format("%s(%s)", eName,info.getValue(info.CNAME));
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.BasicAresFormEditor#dispose()
	 */
	@Override
	public void dispose() {
		JRESEditorSyncManager.getInstance().removeSyncUnit(fileSyncUnit);
		super.dispose();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		 fileSyncUnit.beforeSave();
		if (isReadOnly()) {
			MessageDialog.openInformation(getSite().getShell(), "无法保存",
					"当前资源是只读状态，无法进行保存");
		} else {
			super.doSave(monitor);
		}
	}
}
