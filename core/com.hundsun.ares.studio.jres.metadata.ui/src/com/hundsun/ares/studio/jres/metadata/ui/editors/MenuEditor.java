package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;

import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.sync.JRESDefaultSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESEditorSyncManager;

public class MenuEditor extends EMFFormEditor {

	protected OperationEditPage operationPage;
	protected RevisionHistoryListPage historyPage;
	private MenuListPage metadataItemPage;
	private FunctionPage functionPage;

	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.MENU_LIST;
	}

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
						}else if(obj instanceof Function) {
							functionPage.getEditor().setActivePage(functionPage.getId());
							functionPage.getColumnViewer().setSelection(new StructuredSelection(obj), true);
						}else {
							setSelection(obj);
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
				}
			};
		}
		return super.getAdapter(adapter);
	}
	
	protected void setSelection(EObject element) {
		if(element instanceof MenuList){
			metadataItemPage.getMenuListViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
		}else{
			metadataItemPage.getMenuListViewerBlock().getColumnViewer().setSelection(new StructuredSelection(((EObject)element).eContainer()), true);
			metadataItemPage.getFunctionProxyViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		
//		//添加文件同步
//		fileSyncUnit = new JRESDefaultSyncnizeUnit(this);
//		JRESEditorSyncManager.getInstance().addSyncUnit(fileSyncUnit);
		
		try {
			metadataItemPage = new MenuListPage(this, "list", "菜单与功能");
			addPage(metadataItemPage);
			
			functionPage = new FunctionPage(this, "function", "功能定义");
			addPage(functionPage);
			
			operationPage = new OperationEditPage(this, "oparetion", "用户操作");
			addPage(operationPage);
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息");
			addPage(historyPage);
			
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @return the functionPage
	 */
	public FunctionPage getFunctionPage() {
		return functionPage;
	}
}
