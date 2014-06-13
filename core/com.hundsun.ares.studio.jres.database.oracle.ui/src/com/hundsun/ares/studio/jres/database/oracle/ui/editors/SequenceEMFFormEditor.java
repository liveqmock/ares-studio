/**
 * 源程序名称：SequenceEMFFormEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.sequence.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;

import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.database.ui.editors.DBSQLPreviewPage;
import com.hundsun.ares.studio.jres.database.ui.editors.DatabaseEMFFormEditor;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;
import com.hundsun.ares.studio.ui.extendpoint.manager.IExtendedPage;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;

/**
 * @author wangbin
 *
 */
public class SequenceEMFFormEditor extends DatabaseEMFFormEditor {

	//序列编辑页面
	private SequenceInfoPage sequenceInfoPage;
	//用户修订信息页面
	protected RevisionHistoryListPage historyPage;


	@Override
	protected EClass getInfoClass() {
		return OraclePackage.Literals.SEQUENCE_RESOURCE_DATA;
	}
	
	@Override
	protected void addPages() {
		
		try {
			
			sequenceInfoPage = new SequenceInfoPage(this, "sequence", "基本信息");
			addPage(sequenceInfoPage);
			
			addPage(new DBSQLPreviewPage(this, "preview", "SQL预览"), new TextEditorInput());
			
			historyPage = new RevisionHistoryListPage(this ,"histroy", "修订信息");
			addPage(historyPage);
			
			createExtendPage();
			// 添加SQL预览的支持
			addPageChangedListener(new SequenceSQLPreviewUpdater());
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	protected void handleBeforeSave() {
		for (IExtendedPage page : extendsPages) {
			if (page instanceof ExtendPageWithMyDirtySystem)
				((ExtendPageWithMyDirtySystem) page).doSave();
		}
		super.handleBeforeSave();
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
						
						if(obj instanceof RevisionHistory) {
							historyPage.getEditor().setActivePage(historyPage.getId());
							historyPage.getColumnViewer().setSelection(new StructuredSelection(obj), true);
						}
						else
						{
							sequenceInfoPage.getEditor().setActivePage(sequenceInfoPage.getId());
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
				}
			};
		}
		return super.getAdapter(adapter);
	}


}
