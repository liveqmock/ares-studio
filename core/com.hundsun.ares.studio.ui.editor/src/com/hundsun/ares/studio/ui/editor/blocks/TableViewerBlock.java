/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.Map;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.FilteredTable;
import com.hundsun.ares.studio.ui.TableViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.decorators.EnterForNewLineTableViewerBlockDecorator;

/**
 * @author gongyf
 *
 */
public abstract class TableViewerBlock extends ColumnViewerBlock<TableViewer> {



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#doCreateColumnViewer(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected TableViewer doCreateColumnViewer(Composite parent, FormToolkit toolkit) {
		FilteredTable table = new FilteredTable(parent, FormWidgetUtils.getDefaultTreeStyles(), new TableViewerPatternFilter(), true) {
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.jres.ui.common.FilteredTable#getRefreshJobDelay()
			 */
			@Override
			protected long getRefreshJobDelay() {
				return getColumnViewer().getTable().getItemCount() / 40 + 100;
			}
		};
		
		toolkit.adapt(table);
		table.getViewer().getTable().setHeaderVisible(true);
		table.getViewer().getTable().setLinesVisible(true);
		
		return table.getViewer();
	}

	@Override
	protected void restoreState(IDialogSettings settings) {
		super.restoreState(settings);
		
		TableViewer viewer = getColumnViewer();
		
		{
			// 恢复列宽设置
			String[] savedWidthes = settings.getArray(SAVED_WIDTHES);
			if (savedWidthes != null) {
				int[] saved = new int[savedWidthes.length];
				for (int i = 0; i < savedWidthes.length; i++) {
					saved[i] = Integer.parseInt(savedWidthes[i]);
				}
				
				if (viewer.getTable().getColumns().length == saved.length) {
					for (int i=0;i<saved.length;i++) {
						viewer.getTable().getColumns()[i].setWidth(saved[i]);
					}
				}
				
			}
		}

		{
			// 恢复列顺序
			String[] savedOrder = settings.getArray(SAVED_COLUMNS);
			if (savedOrder != null) {
				int[] saved = new int[savedOrder.length];
				for (int i = 0; i < savedOrder.length; i++) {
					saved[i] = Integer.parseInt(savedOrder[i]);
				}
				if (saved.length > 0 && saved.length == viewer.getTable().getColumns().length) {
					viewer.getTable().setColumnOrder(saved);
				}
			}
		}
		

	}
	
	@Override
	protected void storeState(IDialogSettings settings) {
		super.storeState(settings);
		
		{
			// 保存宽度
			TableColumn[] columns = getColumnViewer().getTable().getColumns();
			String[] widthes = new String[columns.length];
			for (int i = 0; i < columns.length; i++) {
				widthes[i] = String.valueOf(columns[i].getWidth());
			}
			settings.put(SAVED_WIDTHES, widthes);
		}

		
		{
			// 保存列
			int[] order = getColumnViewer().getTable().getColumnOrder();
			String[] strOrder = new String[order.length];
			for (int i = 0; i < order.length; i++) {
				strOrder[i] = String.valueOf(order[i]);
			}
			settings.put(SAVED_COLUMNS, strOrder);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#configureColumnViewer(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void configureColumnViewer(final TableViewer viewer) {
		// 由于这个关系到celleditor的一些监听，需要首先调用
		TableViewerEditor.create(viewer, createColumnViewerEditorActivationStrategy(viewer), 
				ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR );
		
		
		super.configureColumnViewer(viewer);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#configureDecorators(java.util.Map)
	 */
	@Override
	protected void configureDecorators(
			Map<String, IColumnViewerBlockDecorator<TableViewer>> decorators) {
		super.configureDecorators(decorators);
		decorators.put(EnterForNewLineTableViewerBlockDecorator.ID, new EnterForNewLineTableViewerBlockDecorator());
	}
}
