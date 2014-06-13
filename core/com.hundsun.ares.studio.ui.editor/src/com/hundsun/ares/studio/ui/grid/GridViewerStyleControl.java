/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.grid.renderer.EmptyColumnHeaderRenderer;
import com.hundsun.ares.studio.ui.grid.renderer.EmptyRowHeaderRenderer;
import com.hundsun.ares.studio.ui.grid.renderer.RowHeaderRenderer;
import com.hundsun.ares.studio.ui.grid.renderer.TopLeftRenderer;
import com.hundsun.ares.studio.ui.util.HSColorManager;

/**
 * grid样式控制
 * @author maxh
 *
 * @param <T>
 */
public abstract class GridViewerStyleControl<T> extends EditorComponent<List<T>> {
	
	/** 颜色资源统一管理 */ 
	protected HSColorManager colorManager = ARESEditorPlugin.getDefault().getColorManager();
	
	/**
	 * 是否显示表行头
	 */
	protected boolean showRowHeader = true;
	
	/**
	 * 显示表格分割线
	 */
	protected boolean showLine = true;
	
	/**
	 * 是否双击修改
	 */
	protected boolean doubleCheckChange = false;
	
	/**
	 * 是否使用自动行增长
	 */
	protected boolean useAutoGrow = true;
	
	/**
	 * 行高
	 */
	protected int rowHeight = 16;
	
	/**
	 * 行头的颜色
	 */
	protected Color rowHeaderColor = colorManager.getColor(new RGB(236, 233, 216));
	
	/**
	 * 列头的颜色
	 */
	protected Color columnTopColor = colorManager.getColor(new RGB(236, 233, 216));
	
	/**
	 * 列头分割线的颜色
	 */
	protected Color columnTopLineColor = colorManager.getColor(new RGB(113, 111, 100));
	
	/**
	 * TODO
	 * 行头宽度
	 */
	protected int itemHeaderWidth = 40;

	/**
	 * 是否全选中
	 */
	protected boolean isFullSelection = false;

	/**
	 * 是否为只读，只读不可剪切，粘贴，自动行增长与修改单元格
	 */
	protected boolean readOnly = false;

	/**
	 * 按钮
	 */
	protected List<Button> buttons = null;

	/**
	 * 子类可重写该方法调整表格的风格
	 */
	protected void adjustStyle(){
		
	}

	/**
	 * 创建一个Button对象
	 * 
	 * @param toolkit
	 * @param client
	 * @param caption
	 * @return
	 */
	final protected Button createButton(FormToolkit toolkit, Composite client, String caption) {

		Button button = null;
		if (toolkit == null) {
			button = new Button(client, SWT.PUSH);
			button.setText(caption);
		} else {
			button = toolkit.createButton(client, caption, SWT.PUSH);
		}

		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalAlignment = SWT.FILL;
		button.setLayoutData(gd);

		return button;
	}

	protected List<Button> createButtons(FormToolkit toolkit, Composite client) {
		List<Button> buttons = new ArrayList<Button>();
		return buttons;
	}

	public Color getColumnTopColor() {
		return columnTopColor;
	}

	public Color getColumnTopLineColor() {
		return columnTopLineColor;
	}

	public int getItemHeaderWidth() {
		return itemHeaderWidth;
	}

	public Color getRowHeaderColor() {
		return rowHeaderColor;
	}


	public int getRowHeight() {
		return rowHeight;
	}

	protected int getStyle(){
		int style = 0;
		if (isFullSelection)
			style = SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
		else
			style = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
		return style;
	}

	public boolean isDoubleCheckChange() {
		return doubleCheckChange;
	}

	
	public boolean isFullSelection() {
		return isFullSelection;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isShowRowHeader() {
		return showRowHeader;
	}

	public boolean isUseAutoGrow() {
		return useAutoGrow;
	}

	public void setColumnTopColor(Color columnTopColor) {
		this.columnTopColor = columnTopColor;
	}

	public void setColumnTopLineColor(Color columnTopLineColor) {
		this.columnTopLineColor = columnTopLineColor;
	}

	public void setDoubleCheckChange(boolean doubleCheckChange) {
		this.doubleCheckChange = doubleCheckChange;
	}

	public void setEditable(boolean editable) {
		readOnly = !editable;
		if(buttons == null){
			return;
		}
		for (Button btn : buttons) {
			btn.setEnabled(editable);
		}
	}
	
	public void setEditable(String buttonText, boolean editable) {
		for (Button btn : buttons) {
			if (btn.getText().equals(buttonText)) {
				btn.setEnabled(editable);
				break;
			}
		}
	}

	public void setFullSelection(boolean isFullSelection) {
		this.isFullSelection = isFullSelection;
	}

	public void setItemHeaderWidth(int itemHeaderWidth) {
		this.itemHeaderWidth = itemHeaderWidth;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setRowHeaderColor(Color rowHeaderColor) {
		this.rowHeaderColor = rowHeaderColor;
	}

	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	public void setShowRowHeader(boolean showRowHeader) {
		this.showRowHeader = showRowHeader;
	}

	public void setUseAutoGrow(boolean useAutoGrow) {
		this.useAutoGrow = useAutoGrow;
	}

	public boolean isShowLine() {
		return showLine;
	}

	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}
	
	public TopLeftRenderer createTopLeftRenderer(){
		TopLeftRenderer topLeftRenderer = new TopLeftRenderer();
		topLeftRenderer.setColor(getColumnTopColor());
		topLeftRenderer.setLineColor(getColumnTopLineColor());
		return topLeftRenderer;
	}

	public RowHeaderRenderer createRowHeaderRenderer(){
		RowHeaderRenderer rowHeaderRenderer = new RowHeaderRenderer();
		rowHeaderRenderer.setColor(getRowHeaderColor());
		rowHeaderRenderer.setLineColor(getColumnTopLineColor());
		return rowHeaderRenderer;
	}

	public EmptyRowHeaderRenderer createEmptyRowHeaderRenderer(){
		EmptyRowHeaderRenderer emptyRowHeaderRenderer = new EmptyRowHeaderRenderer();
		emptyRowHeaderRenderer.setColor(getRowHeaderColor());
		emptyRowHeaderRenderer.setLineColor(getColumnTopLineColor());
		return emptyRowHeaderRenderer;
	}
	
	public EmptyColumnHeaderRenderer createEmptyColumnHeaderRenderer(){
		EmptyColumnHeaderRenderer emptyColumnHeaderRenderer = new EmptyColumnHeaderRenderer();
		emptyColumnHeaderRenderer.setColor(getColumnTopColor());
		emptyColumnHeaderRenderer.setLineColor(getColumnTopLineColor());
		return emptyColumnHeaderRenderer;
	}
	
	public Grid createGrid(Composite parent, int style){
		GridEx grid = new GridEx(parent,style);
		grid.setHeaderVisible(true);
		grid.setLinesVisible(isShowLine());
		grid.setRowHeaderVisible(isShowRowHeader());
		grid.setItemHeight(getRowHeight());
		grid.setItemHeaderWidth(getItemHeaderWidth());
		grid.setTopLeftRenderer(createTopLeftRenderer());
		grid.setRowHeaderRenderer(createRowHeaderRenderer());
		grid.setEmptyRowHeaderRenderer(createEmptyRowHeaderRenderer());
		grid.setEmptyColumnHeaderRenderer(createEmptyColumnHeaderRenderer());
		return grid;
	}
	
//	/**
//	 * 包含了错误的悬浮提示和颜色
//	 * <br>优化原有的 提示信息和颜色不能一一对应的弊端
//	 * @author tangjb
//	 */
//	public static class ErrorMessage{
//		String message = "";
//		Color color;
//		/** 表示是否 开启背景色(值为空时, 前景色是无法显示的)*/
//		public ErrorMessage(){}
//		
//		public ErrorMessage(Color color , String message) {
//			this.message = message;
//			this.color = color;
//		}
//
//		/**
//		 * @return the color
//		 */
//		public Color getColor() {
//			return color;
//		}
//		/**
//		 * @return the message
//		 */
//		public String getMessage() {
//			return message;
//		}
//		/**
//		 * @param color the color to set
//		 */
//		public void setColor(Color color) {
//			this.color = color;
//		}
//		/**
//		 * @param message the message to set
//		 */
//		public void setMessage(String message) {
//			this.message = message;
//		}
//	}
}
