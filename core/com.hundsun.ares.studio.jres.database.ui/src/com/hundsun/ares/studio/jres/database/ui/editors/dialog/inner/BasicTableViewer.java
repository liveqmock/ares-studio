package com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;

import com.hundsun.ares.studio.core.ConsoleHelper;

public abstract class BasicTableViewer extends TableViewer {
	public static final Logger LOGGER = ConsoleHelper.getLogger();

	public static final int DEFAULT_STYLE = SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL;

	public BasicTableViewer(Composite parent) {
		this(parent, DEFAULT_STYLE);
	}

	public BasicTableViewer(Composite parent, int style) {
		super(parent, style);
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		Layout layout = (Layout) parent.getLayout();
		getTable().setLayoutData(
				getDefaultLayoutData(layout instanceof GridLayout ? ((GridLayout) layout).numColumns : 1));
		getTable().addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = height;
			}
		});
		setContentProvider(createContentProvider());
		
		initColumns();
	}
	
	/**
	 * 默认行高
	 */
	public static int DEFAULT_HEIGHT = 22;

	private int height = DEFAULT_HEIGHT;

	/**
	 * 设置行高
	 * 
	 * @param height
	 */
	public void setRowHeight(int height) {
		this.height = height;
	}

	/**
	 * 获取行高
	 * 
	 * @return
	 */
	public int getRowHeight() {
		return height;
	}

	/**
	 * 表格的默认LayoutData，在初始化时调用
	 * 
	 * @param cols父容器布局列数
	 * @return
	 */
	protected GridData getDefaultLayoutData(int cols) {
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = cols;
		data.heightHint = 220;
		return data;
	}

	private Map<String, TableViewerColumn> colsMap = new HashMap<String, TableViewerColumn>();

	/**
	 * 获取表格中所有的列，Map的Key是表格列的标题
	 * 
	 * @return
	 */
	public Map<String, TableViewerColumn> getColumnsMap() {
		return colsMap;
	}

	protected void initColumns() {
		String[] titles = createTitles();
		int[] titleWidth = createTitlesWidth();
		if (titles.length != titleWidth.length) {
			LOGGER.debug("列数与列宽配置个数不同");
			return;
		}

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(this, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(titleWidth[i]);
			colsMap.put(titles[i], column);
			column.setEditingSupport(createEditingSupport(i));
			ColumnLabelProvider labelProvider = createLabelProvider(i);
			if (labelProvider != null)
				column.setLabelProvider(labelProvider);
		}
	}
	
	protected IContentProvider createContentProvider() {
		return new ArrayContentProvider();
	}

	/**
	 * 表格所有列的标题
	 * 
	 * @return
	 */
	protected abstract String[] createTitles();

	/**
	 * 表格列的宽度
	 * 
	 * @return
	 */
	protected abstract int[] createTitlesWidth();

	/**
	 * 表格所有列的LabelProvider，在表格初始化时指定列EditingSupport创建结束后调用。如果在初始化时数据不足以创建LabelProvider，则这里可以返回null，
	 * 然后通过{@link #getColumnsMap()} 拿到列后再设置
	 * 
	 * @param index
	 *            列序号，即该列标题在{@link #createTitles()}返回的数组中的序号
	 */
	protected abstract ColumnLabelProvider createLabelProvider(int index);

	/**
	 * 表格所有列的EditingSupport，在表格初始化时调用。如果在初始化时数据不足以创建EditingSupport，则这里可以返回null，
	 * 然后通过{@link #getColumnsMap()} 拿到列后再设置
	 * 
	 * @param index
	 *            列序号，即该列标题在{@link #createTitles()}返回的数组中的序号
	 */
	protected abstract EditingSupport createEditingSupport(int index);
}
