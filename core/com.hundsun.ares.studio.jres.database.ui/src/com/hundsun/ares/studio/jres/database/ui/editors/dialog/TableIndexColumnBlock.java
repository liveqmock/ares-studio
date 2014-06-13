/**
 * 源程序名称：TableIndexColumnBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.ui.editor.blocks.TableViewerBlock;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;

/**
 * @author gongyf
 *
 */
public class TableIndexColumnBlock extends TableViewerBlock {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceContentProvider(DatabasePackage.Literals.TABLE_INDEX__COLUMNS);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TableViewer viewer) {
		TableViewer tableViewer = (TableViewer) viewer;
		
		{
			// 定义主属性
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME;
			
			// 创建表格列
			TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.LEFT);
			column.getColumn().setText("字段");
			column.getColumn().setWidth(120);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			column.setEditingSupport(es);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		
	}

}
