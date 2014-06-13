/**
 * 源程序名称：DBTableEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;
import com.hundsun.ares.studio.ui.extendpoint.manager.IExtendedPage;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;

/**
 * @author gongyf
 *
 */
public class DBTableEditor extends DatabaseEMFFormEditor {

	private CommonElementContentProvider cp = new CommonElementContentProvider();
	private CommonElementLabelProvider provider = new CommonElementLabelProvider(cp);
	
	@Override
	protected void addPages() {
		try {
			addPage(new DBTableOverviewPage(this, "overview", "基本信息"));
			addPage(new DBTableColumnAndIndexPage(this, "column_index", "字段和索引"));
			addPage(new TableSQLPreviewPage(this, "preview", "SQL预览"), new TextEditorInput());
			addPage(new RevisionHistoryListPage(this, "history", "修订信息"));
			createExtendPage();
			// 添加SQL预览的支持
			addPageChangedListener(new TableSQLPreviewUpdater());
			
		} catch (PartInitException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void handleBeforeSave() {
		for (IExtendedPage page : extendsPages) {
			if (page instanceof ExtendPageWithMyDirtySystem) {
				((ExtendPageWithMyDirtySystem)page).doSave();
			}
		}
		super.handleBeforeSave();
	}
	
	@Override
	protected EClass getInfoClass() {
		return DatabasePackage.Literals.TABLE_RESOURCE_DATA;
	}
	
	@Override
	protected String getEditingDomainID() {
		return IDBConstant.ID_TABLE_EDITDOMAIN;
	}
	
	@Override
	protected String getEditorTitle() {
		String partName = provider.getText(getARESResource());
		if (isReadOnly()) {
			partName += "(只读)";
		}
		return partName;
	}
	
}
