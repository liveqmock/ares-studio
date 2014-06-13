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

import java.util.EventObject;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldPackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.provider.BasicdataItemProviderAdapterFactory;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.STDModelAndDataDefineTablePage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.STDModelAndDataPage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.SingleTableOverviewPage;
import com.hundsun.ares.studio.jres.metadata.ui.editors.OperationEditPage;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

/**
 *标准字典编辑器
 *
 */
public class STDModelAndDataEditor extends BasicDataEMFFormEditor {

	private STDModelAndDataPage singleTableListPage;
	
	private STDModelAndDataDefineTablePage definePage;
	
	private boolean shouldReloadModel = false;
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor#addMetadataitemPage()
	 */
	@Override
	protected EMFFormPage addMetadataItemPage() {
		return null;
	}

	
	@Override
	protected void configureComposedAdapterFactory(
			ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new BasicdataItemProviderAdapterFactory());
	}

	@Override
	protected void addPages() {
		
		try {
			addPage(new SingleTableOverviewPage(this, "overview", "基本信息"){
				@Override
				protected JRESResourceInfo getInfo() {
					return ((StandardFieldModelAndData) getEditor().getInfo()).getData();
				}
				
			});
			
			singleTableListPage = new STDModelAndDataPage(this, "list", "数据",IBasicDataEpacakgeConstant.MasterItem);
			addPage(singleTableListPage);
			
			definePage = new STDModelAndDataDefineTablePage(this, "info", "模型定义");
			addPage(definePage);
			
			operationPage = new OperationEditPage(this, "oparetion", "用户操作"){
				protected JRESResourceInfo getInfo() {
					StandardFieldModelAndData info = (StandardFieldModelAndData)getEditor().getInfo();
					return  info.getData();
				};
			};
			addPage(operationPage);
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息"){
				protected JRESResourceInfo getInfo() {
					StandardFieldModelAndData info = (StandardFieldModelAndData)getEditor().getInfo();
					return  info.getData();
				};
			};
			addPage(historyPage);
			
			getEditingDomain().getCommandStack().addCommandStackListener(new CommandStackListener() {
				
				@Override
				public void commandStackChanged(EventObject event) {
					Command command = getEditingDomain().getCommandStack().getMostRecentCommand();
					if(command != null){
						for(Object obj:command.getAffectedObjects()){
							if(obj instanceof  StandardFieldPackageDefine){
								shouldReloadModel = true;
							}else if(obj instanceof  StandardFieldColumn){
								shouldReloadModel = true;
							}
						}
					}
				}
			});
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#handleAfterSave()
	 */
	@Override
	protected void handleAfterSave() {
		try {
			if(shouldReloadModel){
				createModel();
				singleTableListPage.recreateViewerBlock();
				//导入基础数据时，有可能还没打开第二个页面，所以block可能会出现null的情况
				if(definePage.getStandardFieldViewBlock() != null){
					definePage.reset();
				}
				shouldReloadModel = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.ui.editor.BasicDataEMFFormEditor#setSelection(java.lang.Object)
	 */
	@Override
	protected void setSelection(Object element) {
	}
	
	//专门用于导入基础数据的模型定义后，对编辑器进行保存，因为此时EMF命令栈监听下AffectedObjects为空，
	//不会对shouldReloadModel进行改变，这里手动置为true，对界面进行刷新
	public void save(){
		shouldReloadModel = true;
		doSave(new NullProgressMonitor());
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
