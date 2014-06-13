package com.hundsun.ares.studio.ui.editor.actions;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;

public class CopyColumnAction extends Action{

public static final String ID = "copy.Column";
	
	private ColumnViewer viewer;
	private String textToBeCopyed;
	
	public CopyColumnAction(ColumnViewer viewer) {
		this.viewer = viewer;
		setId(ID);
		setText(StringUtils.EMPTY);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESEditorPlugin.PLUGIN_ID, "icons/full/obj16/copyColumn.png"));
		viewer.getControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				update(e);
			}
		});
	}
	
	public void update(MouseEvent event) {
		Point pt = new Point(event.x, event.y);
		ViewerCell cell = viewer.getCell(pt);
		// 2012-09-28 sundl 点击空白处的地方，cell是null
		if (cell == null) {
			textToBeCopyed = StringUtils.EMPTY;
			updateEnablement();
			setText("复制列");
			return;
		}
		
		int selectColumnIndex = cell.getColumnIndex();
		
		StringBuffer buffer = new StringBuffer();
		if(viewer instanceof TableViewer){
			TableViewer tableViewer = (TableViewer)viewer;
			int lineCount = tableViewer.getTable().getItemCount();
			String columnName =  tableViewer.getTable().getColumns()[selectColumnIndex].getText();
			setText(String.format("复制列:%s", columnName));
			for (int k = 0; k < lineCount; k++) {
				TableItem item = tableViewer.getTable().getItem(k);
				if (k != lineCount - 1)
					buffer.append(item.getText(selectColumnIndex) + "\n");
				else
					buffer.append(item.getText(selectColumnIndex));
			}
		}
		
		if(viewer instanceof TreeViewer){
			TreeViewer treeViewer = (TreeViewer)viewer;
			int lineCount = treeViewer.getTree().getItemCount();
			String columnName =  treeViewer.getTree().getColumns()[selectColumnIndex].getText();
			setText(String.format("复制列:%s", columnName));
			for (int k = 0; k < lineCount; k++) {
				TreeItem item = treeViewer.getTree().getItem(k);
				if (k != lineCount - 1)
					buffer.append(item.getText(selectColumnIndex) + "\n");
				else
					buffer.append(item.getText(selectColumnIndex));
			}
		}
		textToBeCopyed = buffer.toString();
		
		
		updateEnablement();
//		updateText();
	}
	
	/**
	 * 更新可用状态
	 */
	public void updateEnablement() {
		if (StringUtils.isEmpty(textToBeCopyed)) {
			setEnabled(false);
		} else {
			setEnabled(true);
		}
	}
	
	@Override
	public void run() {
		ARESEMFClipboard.getInstance().copyToClipboard(null, textToBeCopyed, null);
	}
	
}
