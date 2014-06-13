/**
 * 源程序名称：MetadataEMFFormEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;

import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.BasicDataExtendPage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.BasicDataSQLPreviewPage;
import com.hundsun.ares.studio.jres.metadata.ui.editors.OperationEditPage;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;


public abstract class BasicDataEMFFormEditor extends EMFFormEditor {
	
	protected OperationEditPage operationPage;
	protected RevisionHistoryListPage historyPage;
	
	/**
	 * 编辑器初始化异常
	 */
	protected PartInitException exception;


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
			
			addPage(new BasicDataSQLPreviewPage(this, "preview", "SQL预览"), new TextEditorInput());
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息");
			addPage(historyPage);
			
			addPage(new BasicDataExtendPage(this, "extend", "扩展属性配置"));
//			addPage(addMetadataItemOverViewPage());
			addPageChangedListener(new BasicDataSQLPreviewUpdater());
			
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
//	/**
//	 * 要添加的元数据基本页
//	 * @return
//	 */
//	protected abstract EMFFormPage addMetadataItemOverViewPage();

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		try {
			super.init(site, input);
		} catch (PartInitException e) {
			throw new PartInitException(
					"\r\n" +
					"打开基础数据编辑器失败，详细信息:" + e.getMessage() + "\r\n" +
					"请按照详细信息中提示的信息进行相应的检查,如果仍不成功,请做以下检查：" + "\r\n" +
					"1、项目或文件是否需要刷新，选中项目右键-》“刷新”。"+ "\r\n" +
					"2、表关联信息中基础数据的配置是否正确。",
					e.getCause()) ;
		}
	}
	
}
