/**
 * 源程序名称：TriggerEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.trigger.ui
 * 功能说明：触发器编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ide.IGotoMarker;

import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.database.ui.editors.DBSQLPreviewPage;
import com.hundsun.ares.studio.jres.database.ui.editors.DBSQLPreviewUpdater;
import com.hundsun.ares.studio.jres.database.ui.editors.DatabaseEMFFormEditor;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author wangxh
 *
 */
public class TriggerEditor extends DatabaseEMFFormEditor {

	TriggerBasicPage basicPage;
	RevisionHistoryListPage historyPage;
	public TriggerEditor() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return OraclePackage.Literals.TRIGGER_RESOURCE_DATA;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		try {
			basicPage = new TriggerBasicPage(this, "基本信息", "基本信息");
			addPage(basicPage);
			addPage(new DBSQLPreviewPage(this, "preview", "SQL预览"), new TextEditorInput());
			
			historyPage = new RevisionHistoryListPage(this, "histroy", "修订信息");
			addPage(historyPage);
			
			// 添加SQL预览的支持
			addPageChangedListener(new DBSQLPreviewUpdater());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
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
						
						if(obj instanceof RevisionHistory) {
							historyPage.getEditor().setActivePage(historyPage.getId());
							historyPage.getColumnViewer().setSelection(new StructuredSelection(obj), true);
						}
						else
						{
							basicPage.getEditor().setActivePage(basicPage.getId());
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
