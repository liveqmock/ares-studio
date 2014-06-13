/**
 * 源程序名称：StandardFieldEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.provider.BasicdataItemProviderAdapterFactory;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.SingleTableOverviewPage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.SingleTablePage;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

/**
 *标准字典编辑器
 *
 */
public class SingleModelEditor extends BasicDataEMFFormEditor {

	private SingleTablePage singleTableListPage;
	
	@Override
	protected EClass getInfoClass() {
		return null;
	}

	@Override
	protected EMFFormPage addMetadataItemPage() {
		singleTableListPage = new SingleTablePage(this, "list", "二维表",IBasicDataEpacakgeConstant.MasterItem);
		return singleTableListPage;
	}

	@Override
	protected void setSelection(Object element) {
		singleTableListPage.getEditor().setActivePage(singleTableListPage.getId());
		singleTableListPage.getStandardFieldViewBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
	}
	
	@Override
	protected void configureComposedAdapterFactory(
			ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new BasicdataItemProviderAdapterFactory());
	}

	@Override
	protected void addPages() {
		try {
			addPage(new SingleTableOverviewPage(this, "overview", "基本信息"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.addPages();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.EMFFormEditor#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
		IDialogSettings blockSettings = null;
		blockSettings = settings.getSection(this.getClass().getSimpleName());
		if (blockSettings == null) {
			blockSettings = settings.addNewSection(this.getClass().getSimpleName());
			blockSettings.put(ACTIVE_EDITOR_INDEX, 0);
		}
		
		return blockSettings;
	}

}
