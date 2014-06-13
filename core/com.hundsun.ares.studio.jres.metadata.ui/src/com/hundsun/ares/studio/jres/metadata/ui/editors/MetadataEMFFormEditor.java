/**
 * 源程序名称：MetadataEMFFormEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

/**
 * 创建编辑器模型，为元数据编辑器增加基本页，用户操作页和修订信息页。
 * @author qinyuan
 *
 */
public abstract class MetadataEMFFormEditor extends EMFFormEditor {
	
	protected OperationEditPage operationPage;
	protected RevisionHistoryListPage historyPage;
	


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IGotoMarker.class) {
			return new IGotoMarker() {
				
				@Override
				public void gotoMarker(IMarker marker) {
					String uri;
					try {
						uri = (String) marker.getAttribute(IMarker.LOCATION);
						EObject obj = getInfo().eResource().getEObject(uri);
						
						if(obj instanceof Operation) {
							operationPage.getEditor().setActivePage(operationPage.getId());
							operationPage.getBlock().getViewer().setSelection(new StructuredSelection(obj), true);
						}else if(obj instanceof RevisionHistory) {
							historyPage.getEditor().setActivePage(historyPage.getId());
							historyPage.getColumnViewer().setSelection(new StructuredSelection(obj), true);
						}else {
							setSelection(obj);
						}
						
//						if(obj instanceof MetadataItem) {
//							getMetaDataItemPage().getEditor().setActivePage(getMetaDataItemPage().getId());
//							getMetaDataItemPage().getColumnViewer().setSelection(new StructuredSelection(obj), true);
//						}else if(obj instanceof Operation) {
//							operationPage.getEditor().setActivePage(operationPage.getId());
//							operationPage.getBlock().getViewer().setSelection(new StructuredSelection(obj), true);
//						}else if(obj instanceof RevisionHistory) {
//							historyPage.getEditor().setActivePage(historyPage.getId());
//							historyPage.getColumnViewer().setSelection(new StructuredSelection(obj), true);
//						}else if(obj instanceof DictionaryItem) {
//							getMetaDataItemPage().getEditor().setActivePage(getMetaDataItemPage().getId());
//							getMetaDataItemPage().getColumnViewer().setSelection(new StructuredSelection(obj.eContainer()), true);
//							((DictionaryListPage)getMetaDataItemPage()).getDetailColumnViewer().setSelection(new StructuredSelection(obj), true);
//						}
						
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
				}
			};
		}
		return super.getAdapter(adapter);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		

		
		try {
			addPage(addMetadataItemPage());
			
			operationPage = new OperationEditPage(this, "oparetion", "用户操作");
			addPage(operationPage);
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息");
			addPage(historyPage);
			
			// 2013-05-15 sundl 只有无ref nature才添加overview页面
			// 注意这里的逻辑，为保持老工程的兼容性，有ref nature的不显示引用节点；无ref nature的才显示引用节点
			IARESResource res = getARESResource();
			if (res == null) 
				return;
			IARESProject proj = res.getARESProject();
			if (proj != null && !ARESProject.hasRefNature(proj.getProject())) {
				EMFFormPage overview = addMetadataItemOverViewPage();
				if (overview != null)
					addPage(overview);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 要添加的元数据基本页
	 * @return
	 */
	protected abstract EMFFormPage addMetadataItemPage();
	
	/**
	 * gotoMarker
	 * @param element
	 */
	protected abstract void setSelection(Object element);
	/**
	 * 要添加的元数据基本页
	 * @return
	 */
	protected abstract EMFFormPage addMetadataItemOverViewPage();
	
}
