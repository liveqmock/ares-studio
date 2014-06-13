/**
 * <p>Copyright: Copyright   2010</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.celleditor.ISprecialCellEditor;
import com.hundsun.ares.studio.ui.grid.GridViewerExComponent;
import com.hundsun.ares.studio.ui.util.HSColorManager;

/**
 * 基本的表格编辑器 这一层提供了LabelProvider和ContentProvider 包括了一些和展现相关的操作
 * 
 * @author maxh
 * 
 * @param <T>
 */
public abstract class GridTableViewerBasicComponent<T> extends GridViewerExComponent<T> {
	/** 指定的列是否需要唯一性检查 */
	protected HashMap<String, Boolean> checkStatus = new HashMap<String, Boolean>();

	/** 指定列的重复情况 */
	protected HashMap<String, HashMap<Object, Integer>> checkCache = new HashMap<String, HashMap<Object, Integer>>();

	/** 带过滤器的表格 */
	protected FilteredGridTable tbComposite = null;

	/** 预留编辑的对象 */
	protected T lastLine = createBlankData();

	/** 当前控件的表格对象 */
	protected GridTableViewer viewer = null;

	private boolean addOnlyOne = false;

	/**
	 * 判定指定单元格是否能编辑
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected abstract boolean canEdit(T data, String property);

	/**
	 * 新建一个空白数据
	 * 
	 * @return
	 */
	protected abstract T createBlankData();

	public T createNewObject() {
		return createBlankData();
	}

	protected FilteredGridTable createFilteredTable(Composite client, int style) {
		FilteredGridTable table = new FilteredGridTable(client, style, new HSGridTableFilter(), this);
		return table;
	}

	/**
	 * 取得所需的背景色,不过错误的红色优先于这个设定
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	protected Color getBackground(Object data, String property) {
		return null;
	}

	public HashMap<String, HashMap<Object, Integer>> getCheckCache() {
		return checkCache;
	}

	protected Color getForeground(Object data) {
		return null;
	}

	@Override
	protected Grid getGrid() {
		return viewer.getGrid();
	}

	/**
	 * 获得单元格特定的图像
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected Image getImage(T data, String property) {
		return null;
	}

	/**
	 * 获取当前表格数据中参数不合法的行信息列表
	 */
	protected List<T> getInvalidRows() {
		List<T> invalidRows = new ArrayList<T>();
		Iterator<T> rows = input.iterator();
		while (rows.hasNext()) {
			T row = rows.next();
			boolean isValid = GridTableViewerBasicComponent.this.isValidData(row);
			if (!isValid) {
				invalidRows.add(row);
			}
		}

		return invalidRows;
	}

	/**
	 * @return the lastLine
	 */
	public T getLastLine() {
		return lastLine;
	}

	public GridTableViewer getTableViewer() {
		return viewer;
	}

	/**
	 * 获得单元格的显示文本，默认实现是GetValue的toString
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected String getText(T data, String property) {
		Object value = getValue(data, property);
		if (value != null) {
			return value.toString();
		}
		return "";
	}

	/**
	 * 得到toolTip单元格的颜色
	 * 
	 * @return
	 */
	public Color getToolTipColor() {
		return null;
	}

	/**
	 * 得到该单元格toolTip内容,可以返回null
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	public String getToolTipText(T element, String property) {
		return null;
	}

	abstract public Object getValue(T data, String property);

	// /**
	// * 得到该单元格toolTip内容(包括背景色信息)
	// * <br>同一单元格,如果getToolTipText也有内容, 本内容可能被覆盖
	// *
	// * @param element
	// * @param property
	// * @return
	// */
	// public ErrorMessage getToolTipTextBackground(Object element, String
	// property) {
	// return null;
	// }
	//
	// /**
	// * 得到该单元格toolTip内容(包括前景色信息)
	// * <br>
	// * @param element
	// * @param property
	// * @param isBackGroundAble 表示是否在当前文字为空的时候 开启背景色(值为空时, 前景色是无法显示的)
	// * @return
	// */
	// public ErrorMessage getToolTipTextForeground(Object element, String
	// property) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	public GridTableViewer getViewer() {
		return this.viewer;
	}

	protected void initComposite(Composite client) {
		tbComposite = createFilteredTable(client, getStyle());
		tbComposite.setLayoutData(getCompositeLayoutData());
		viewer = tbComposite.getViewer();
		initViewer(viewer);

		viewer.setContentProvider(new TableViewerContentProvider());
		viewer.setRowHeaderLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell viewerCell) {
				updateRowHeader(viewerCell);
			}

		});
		viewer.setInput(input);
	}

	/**
	 * @return the addOnlyOne
	 */
	public boolean isAddOnlyOne() {
		return addOnlyOne;
	}

	final protected boolean isLastLine(T data) {
		return data == lastLine;
	}

	/**
	 * 数据验证<BR>
	 * 返回错误信息，返回null代表无错误
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected String isValid(T data, String property) {
		return null;
	}

	/**
	 * 数据验证<BR>
	 * 返回是否通过验证，返回true代表数据合法
	 * 
	 * @param data
	 * @return
	 */
	protected boolean isValidData(T data) {
		return true;
	}

	/**
	 * 只能添加一条记录，不能自动增长
	 * 
	 * @param addOnlyOne
	 *            the addOnlyOne to set
	 */
	public void setAddOnlyOne(boolean addOnlyOne) {
		this.addOnlyOne = addOnlyOne;
		this.useAutoGrow = false;
	}

	/**
	 * 设置表格列是否进行唯一性检查<BR>
	 * 唯一性的判定依赖于GetValue方法返回的Object的equals结果
	 * 
	 * @param property
	 * @param docheck
	 */
	final protected void setUniqueCheck(String property, boolean docheck) {
		boolean isCheck = checkStatus.get(property);
		if (isCheck != docheck) {
			checkStatus.put(property, docheck);
			viewer.getGrid().setRedraw(false);
			viewer.refresh();
			viewer.getGrid().setRedraw(true);
		}
	}

	protected void updateRowHeader(ViewerCell viewerCell) {

	}

	protected class DelegateCellLabelProvider extends ColumnLabelProvider {

		protected String property;
		private boolean isBackground = true;
		/** 保存对应行的错误信息 */
		private Map<Object, String> errMsgMap = new HashMap<Object, String>();

		public DelegateCellLabelProvider(String property) {
			super();
			this.property = property;
		}

		@Override
		public Color getBackground(Object element) {
			if (element != lastLine) {
				// 这部分检查关键列是否有重复
				String errMessage = GridTableViewerBasicComponent.this.isValid((T) element, property);
				errMsgMap.put(element, errMessage);
				if (checkStatus.get(property)) {
					Integer times = checkCache.get(property).get(getValue((T) element, property));
					if (times != null && times > 1) {
						errMessage = "本列有重复内容";
						errMsgMap.put(element, errMessage);
						return colorManager.getColor(HSColorManager.RED);
					}
				}
				if (errMessage != null) {
					return colorManager.getColor(HSColorManager.RED);
				}
				// 这部分检查是否有错误
				errMessage = GridTableViewerBasicComponent.this.isValid((T) element, property);
				errMsgMap.put(element, errMessage);
				if (errMessage != null) {
					return colorManager.getColor(HSColorManager.RED);
				}
			}
			return GridTableViewerBasicComponent.this.getBackground(element, property);
		}

		@Override
		public Image getImage(Object element) {
			if (GridTableViewerBasicComponent.this.getCellEditor(element, property) instanceof ISprecialCellEditor) {
				ISprecialCellEditor cellEditor = (ISprecialCellEditor) GridTableViewerBasicComponent.this
						.getCellEditor(element, property);
				return cellEditor.getImage(GridTableViewerBasicComponent.this.getValue((T) element, property));
			}
			return GridTableViewerBasicComponent.this.getImage((T) element, property);
		}

		@Override
		public String getText(Object element) {
			if (GridTableViewerBasicComponent.this.getCellEditor(element, property) instanceof ISprecialCellEditor) {
				ISprecialCellEditor cellEditor = (ISprecialCellEditor) GridTableViewerBasicComponent.this
						.getCellEditor(element, property);
				return cellEditor.getText(GridTableViewerBasicComponent.this.getValue((T) element, property));
			}
			return GridTableViewerBasicComponent.this.getText((T) element, property);
		}

		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 300;
		}

		@Override
		public Point getToolTipShift(Object object) {
			return new Point(5, 5);
		}

		@Override
		public String getToolTipText(Object element) {
			if (errMsgMap.get(element) != null) {
				return errMsgMap.get(element);
			}
			if (GridTableViewerBasicComponent.this.getToolTipText((T) element, property) != null) {
				return GridTableViewerBasicComponent.this.getToolTipText((T) element, property);
			}
			if (getText(element) == null || getText(element).length() < 40) {
				return null;
			}
			StringBuffer buffer = new StringBuffer(getText(element));
			int lenght = buffer.length();
			int i = 39;
			while (i < lenght) {
				buffer.insert(i, '\n');
				i += 40;
			}
			return buffer.toString();
		}

		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return -1;
		}

		/**
		 * @return the isBackground
		 */
		public boolean isBackground() {
			return isBackground;
		}

		/**
		 * @param isBackground
		 *            the isBackground to set
		 */
		public void setBackground(boolean isBackground) {
			this.isBackground = isBackground;
		}
	}

	/**
	 * 提供提供器
	 * 
	 * @author gongyf
	 * 
	 */
	private class TableViewerContentProvider implements IStructuredContentProvider {

		public void dispose() {

		}

		/**
		 * 显示一个空白行
		 */
		public Object[] getElements(Object inputElement) {
			// 先清空
			for (String property : checkCache.keySet()) {
				checkCache.put(property, new HashMap<Object, Integer>());
			}
			for (T obj : input) {
				for (String property : checkCache.keySet()) {
					Map<Object, Integer> objCount = checkCache.get(property);
					Object value = getValue(obj, property);
					if (objCount.containsKey(value)) {
						objCount.put(value, objCount.get(value) + 1);
					} else {
						objCount.put(value, 1);
					}
				}
			}
			List<T> all = new ArrayList<T>();
			all.addAll(input);

			// 不添加默认行
			if (useAutoGrow && !readOnly) {
				all.add(lastLine);
			}
			if (addOnlyOne && input.size() < 1) {
				all.add(lastLine);
			}
			return all.toArray();// input.toArray();

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}
	}

	public void refresh() {
		if (viewer != null) {
			viewer.refresh();
		}
	}
}
