/**
 * 源程序名称：DictionaryEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.sync.JRESDefaultSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESEditorSyncManager;

/**
 * 数据字典
 * @author qinyuan
 *
 */
public class DictionaryEditor extends MetadataEMFFormEditor {
	
	private DictionaryListPage metadataItemPage;

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.DICTIONARY_LIST;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor#addMetadataitemPage()
	 */
	@Override
	protected EMFFormPage addMetadataItemPage() {
		metadataItemPage = new DictionaryListPage(this, "list", "字典项");
		return metadataItemPage;
	}
	@Override
	protected void addPages() {
		
		try {
			addPage(addMetadataItemPage());
			
			operationPage = new OperationEditPage(this, "oparetion", "用户操作");
			addPage(operationPage);
			
			addPage(new DictionaryItemListPage(this,"items"," 字典项汇总"));
			
			addPage(new DictionaryConflictPage(this,"conflict"," 冲突页"));
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息");
			addPage(historyPage);

			// 2013-05-15 sundl 只有无ref nature才添加overview页面
			// 注意这里的逻辑，为保持老工程的兼容性，有ref nature的不显示引用节点；无ref nature的才显示引用节点
			IARESResource res = getARESResource();
			if (res == null) 
				return;
			IARESProject proj = res.getARESProject();
			if (proj != null && !ARESProject.hasRefNature(proj.getProject())) {
				addPage(addMetadataItemOverViewPage());
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor#setSelection(java.lang.Object)
	 */
	@Override
	protected void setSelection(Object element) {
		metadataItemPage.getEditor().setActivePage(metadataItemPage.getId());
		if(element instanceof DictionaryType){
			metadataItemPage.getDictionaryListViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
		}else{
			metadataItemPage.getDictionaryListViewerBlock().getColumnViewer().setSelection(new StructuredSelection(((EObject)element).eContainer()), true);
			metadataItemPage.getDictionaryDetailViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
		}
		
	}

	@Override
	protected EMFFormPage addMetadataItemOverViewPage() {
		return new DictionaryOverViewPage(this,"overview","总览列表");
	}
}
