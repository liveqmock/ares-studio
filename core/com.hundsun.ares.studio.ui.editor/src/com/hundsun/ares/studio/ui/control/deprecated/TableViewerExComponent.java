/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control.deprecated;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.ICreateInstance;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.grid.EditorComponent;
import com.hundsun.ares.studio.ui.util.Clipboard;
import com.hundsun.ares.studio.ui.util.HSColorManager;
import com.hundsun.ares.studio.ui.util.ReflectInvokeHelper;

/**
 * 利用Eclipse3.3新增的API重写<BR>
 * 
 * 表格类数据编辑组件<BR>
 * 该组件默认实现了一个表格视图和上下移动按钮<BR>
 * 子类的单元格修改方式为双击修改<BR>
 * 默认验证失败的列显示为前景红色<BR>
 * 当数据实现了ICreateInstance接口后，才可以进行复制粘贴
 * 
 * @author gongyf
 * 
 * @param <T>
 * 
 * @deprecated
 */
public abstract class TableViewerExComponent<T> extends EditorComponent<List<T>> {

	private class DelegateCellLabelProvider extends ColumnLabelProvider {

		private String property;
		private boolean isBackground = true;
		/** 保存对应行的错误信息 */
		private Map<Object, String> errMsgMap = new HashMap<Object, String>();

		public DelegateCellLabelProvider(String property) {
			super();
			this.property = property;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.
		 * lang.Object)
		 */
		@Override
		public Color getBackground(Object element) {
			if (property.equals(selectedProperty))
				return colorManager.getColor(HSColorManager.COLUMN);
			
			ErrorMessage requiredMessage = TableViewerExComponent.this.getToolTipTextBackground(element, property);
			if (requiredMessage != null) {
				errMsgMap.put(element, requiredMessage.getMessage());
				Color color = requiredMessage.getColor();
				if (color != null) {
					return color;
				}
			}
			return TableViewerExComponent.this.getBackground(element, property);
		}

		@Override
		public Color getForeground(Object element) {
			if (element == lastLine) {
				return null;
			}

			if (property.equals(selectedProperty))
				return viewer.getControl().getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);
			String errMessage = TableViewerExComponent.this.isValid((T) element, property);
			errMsgMap.put(element, errMessage);
			if (checkStatus.get(property)) {
				if (checkCache.get(property).get(getValue((T) element, property)) > 1) {
					errMessage = "本列有重复内容";
					errMsgMap.put(element, errMessage);
					return colorManager.getColor(HSColorManager.RED);
				}
			}

			if (errMessage != null) {
				return colorManager.getColor(HSColorManager.RED);
			}
			String requiredMessage = TableViewerExComponent.this.getToolTipText(element, property);
			if (requiredMessage != null) {
				errMsgMap.put(element, requiredMessage);
				Color color = TableViewerExComponent.this.getToolTipColor();
				if (color != null) {
					return color;
				}
				return colorManager.getColor(HSColorManager.BLUE);
			}
			
			ErrorMessage errorMessage = TableViewerExComponent.this.getToolTipTextForeground(element, property);
			if (errorMessage != null) {
				errMsgMap.put(element, errorMessage.getMessage());
				Color color = errorMessage.getColor();
				if (color != null) {
					return color;
				}
//				return colorManager.getColor(HSColorManager.BLUE);
			}
			
			return TableViewerExComponent.this.getForeground(element);

		}

		@Override
		public Image getImage(Object element) {
			return TableViewerExComponent.this.getImage((T) element, property);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object
		 * )
		 */
		@Override
		public String getText(Object element) {
			return TableViewerExComponent.this.getText((T) element, property);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime
		 * (java.lang.Object)
		 */
		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 300;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.
		 * lang.Object)
		 */
		@Override
		public Point getToolTipShift(Object object) {
			return new Point(5, 5);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang
		 * .Object)
		 */
		@Override
		public String getToolTipText(Object element) {
			if (errMsgMap.get(element) != null) {
				return errMsgMap.get(element);
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed
		 * (java.lang.Object)
		 */
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

	private class DelegateEditingSupport extends EditingSupport {

		private String property;

		public DelegateEditingSupport(String property) {
			super(TableViewerExComponent.this.viewer);
			this.property = property;
		}

		@Override
		protected boolean canEdit(Object element) {
			return !readOnly && TableViewerExComponent.this.canEdit((T) element, property);
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			HashMap<Object, CellEditor> map = specialEditorMap.get(property);
			if (map.containsKey(element)) {
				return map.get(element);
			}
			return TableViewerExComponent.this.editorMap.get(property);
		}

		@Override
		protected Object getValue(Object element) {
			Object value = TableViewerExComponent.this.getValue((T) element, property);
			if(getCellEditor(element) instanceof ComboBoxCellEditor){
				ComboBoxCellEditor editor = (ComboBoxCellEditor)getCellEditor(element);
				if(value instanceof String){
					value = Arrays.asList(editor.getItems()).indexOf(value);
				}
			}
			
			if(getCellEditor(element) instanceof CheckboxCellEditor){
				CheckboxCellEditor editor = (CheckboxCellEditor)getCellEditor(element);
				if(value instanceof String){
					value = Boolean.valueOf((String)value);
				}
			}
			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Object oldValue = getValue(element);
			
			if(getCellEditor(element) instanceof ComboBoxCellEditor){
				ComboBoxCellEditor editor = (ComboBoxCellEditor)getCellEditor(element);
				if(value instanceof Integer){
					if((Integer)value != -1){
						Integer i = (Integer)value;
						value = editor.getItems()[i];
					}
				}
			}
			
			TableChangeValueOperation operation = new TableChangeValueOperation("change",TableViewerExComponent.this,element,property,value);
			operation.addContext(undoContext);
			
			try {
				AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// 正在对最后一行进行操作
			if (element == lastLine) {
				Object newValue = getValue(element);
				// 20081030 sundl 增加(最后一个条件) 判断下子类设置以后的值是否对值进行了改变，如果没有，不提交。
				// 20081101 sundl 原来为空，新值为空字符串，也不要提交
				if ((oldValue == null && value != null && !value.toString().equals("")) || (!oldValue.equals(value) && !newValue.equals(oldValue))) {
					// 进行了修改就把最后一行加入正式的input中
					commit();
				} else {
					// 2008年11月14日16:26:45 龚叶峰
					// 没有操作，不刷新hash表
					return;
				}
			}
		}
	}

	/**
	 * 包含了错误的悬浮提示和颜色
	 * <br>优化原有的 提示信息和颜色不能一一对应的弊端
	 * @author tangjb
	 */
	public static class ErrorMessage{
		String message = "";
		Color color;
//		/** 表示是否 开启背景色(值为空时, 前景色是无法显示的)*/
//		boolean isBackGroundAble = false;
		public ErrorMessage(){}
		
		public ErrorMessage(Color color , String message) {
			this.message = message;
			this.color = color;
		}

		/**
		 * @return the color
		 */
		private Color getColor() {
			return color;
		}
		/**
		 * @return the message
		 */
		private String getMessage() {
			return message;
		}
		/**
		 * @param color the color to set
		 */
		public void setColor(Color color) {
			this.color = color;
		}
		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
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

	/** 指定的列是否需要唯一性检查 */
	private HashMap<String, Boolean> checkStatus = new HashMap<String, Boolean>();

	/** 指定列的重复情况 */
	private HashMap<String, HashMap<Object, Integer>> checkCache = new HashMap<String, HashMap<Object, Integer>>();

	/** 带过滤器的表格 */
	protected FilteredTable tbComposite = null;

	/** 属性名与列对象映射 */
	private HashMap<String, TableViewerColumn> columnMap = new HashMap<String, TableViewerColumn>();

	/** 属性名与默认单元格修改器映射 */
	private HashMap<String, CellEditor> editorMap = new HashMap<String, CellEditor>();

	/** 预留编辑的对象 */
	protected T lastLine = createBlankData();

	/** 是否使用自动行增长 */
	protected boolean useAutoGrow = true;

	/** 是否为只读，只读不可剪切，粘贴，自动行增长与修改单元格 */
	protected boolean readOnly = false;

	/** 当前控件的表格对象 */
	public TableViewer viewer = null;

	/** 颜色资源统一管理 */
	protected HSColorManager colorManager = ARESEditorPlugin.getDefault().getColorManager(); // 20081007
	// sundl修改
	// ColorManager统一由HSUI维护。
	/** 用于类型测试的类 */
	private T testClassInstance = createBlankData();

	/** 包含所有按钮 */
	private List<Button> buttons = null;
	/** 特定的单元格编辑器，优先级高于默认 */
	private HashMap<String, HashMap<Object, CellEditor>> specialEditorMap = new HashMap<String, HashMap<Object, CellEditor>>();

	private static final String SAVED_WIDTHES = "saved_width";

	// 缓存的列宽数组。 因为dispose()调用的时候，所有的控件已经被dispose了，故无法再获取宽度。
	// 所以只能加个监听列宽的监听器，更改此缓存。
	private int[] cachedWidth;
	protected int selectColumnIndex = -1;

	protected String[] tablePropertys;

	protected String[] tableTitles;

	protected boolean commonExport = true;

	protected boolean commonImport = false;
	
	protected String selectedProperty = null;
	
	private boolean isFullSelection = true;

	private boolean addOnlyOne = false;

	/**
	 * 单击修改 还是 双击修改
	 */
	private boolean doubleCheckChange = true;

	protected void add(List items,int startIndex){
		if(items.size() > 0){
			TableAddItemOperation add = new TableAddItemOperation("add",this,items,startIndex);
			add.addContext(undoContext);
			try {
				AbstractHSFormEditor.getOperationHistory().execute(add, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加一个对象的特定单元格编辑器<BR>
	 * 本设置优先于默认的列单元格编辑器的设定<BR>
	 * 
	 * 单元格编辑器会在组件dispose时被dispose
	 * 
	 * @param obj
	 * @param property
	 * @param editor
	 */
	final protected void addSpecialCellEditor(String property, T obj, CellEditor editor) {
		specialEditorMap.get(property).put(obj, editor);
	}

	public boolean canCopy() {
		// 只有可以克隆，复制才有意义
		if (!(testClassInstance instanceof ICreateInstance)) {
			return false;
		}
		return !viewer.getSelection().isEmpty();
	}

	public boolean canCut() {
		return canCopy() && canDelete();
	}

	public boolean canDelete() {
		return !readOnly && !viewer.getSelection().isEmpty();
	}

	/**
	 * 判定指定单元格是否能编辑
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected abstract boolean canEdit(T data, String property);

	public boolean canInsert() {
		return !readOnly && !viewer.getSelection().isEmpty();
	}

	public boolean canPaste() {

		if (readOnly)
			return false;

		// 只有可以克隆，粘贴才有意义
		if (!(testClassInstance instanceof ICreateInstance)) {
			return false;
		}

		// 需要要相同类型
		Object obj = Clipboard.instance.getData();
		if (obj != null && obj instanceof List) {
			// 空数据无粘贴意义
			if (!((List) obj).isEmpty()) {
				if (((List) obj).get(0).getClass() == testClassInstance.getClass()) {
					// 只有同类型的可复制，粘贴
					return true;
				}
			}
		}

		return false;
	}

	protected void clearCheckCache() {
		checkCache.clear();
	}

	public void commit() {
		List list = new ArrayList();
		list.add(lastLine);
		add(list,getInput().size());
		lastLine = createBlankData();
		dirty.setValue(true);
		viewer.refresh();
	}

	public void copy() {
		Clipboard.instance.setData(((StructuredSelection) viewer.getSelection()).toList());
	}

	@Override
	final public Composite create(FormToolkit toolkit, Composite parent) {
		Composite client = null;
		if (toolkit == null) {
			client = new Composite(parent, SWT.NONE);
		} else {
			client = toolkit.createComposite(parent);
		}
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		client.setLayout(layout);
		client.setLayoutData(new GridData(GridData.FILL_BOTH));

		// 初始化控件
		initComposite(client);

		buttons = createButtons(toolkit, client);

		initTableMenu();

		int size = buttons.size();
		if (toolkit != null) {
			toolkit.adapt(tbComposite);
		}
		// 调整表格
		((GridData) tbComposite.getLayoutData()).verticalSpan = size > 0 ? size : 1;
		return client;
	}

	/**
	 * 新建一个空白数据
	 * 
	 * @return
	 */
	protected abstract T createBlankData();

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

	/**
	 * 默认的创建按钮方法，创建了 上下移动 2个按钮
	 * 
	 * @param toolkit
	 * @param client
	 * @return
	 */
	protected List<Button> createButtons(FormToolkit toolkit, Composite client) {
		List<Button> buttons = new ArrayList<Button>();
		return buttons;
	}

	protected FilteredTable createFilteredTable(Composite client, int style) {
		FilteredTable table = new FilteredTable(client, style, new HSTableFilter());
		return table;
	}

	public void cut() {
		copy();
		deleteWithOutConfirmed();
	}

	public void delete() {
		boolean confirmed = MessageDialog.openConfirm(viewer.getTable().getShell(), "", "确实要删除吗");
		if (!confirmed) {
			return;
		}
		deleteWithOutConfirmed();
	}

	protected void delete(List items){
		if(items.size() > 0){
			TableDeleteItemOperation delete = new TableDeleteItemOperation("delete",this,items);
			delete.addContext(undoContext);
			try {
				AbstractHSFormEditor.getOperationHistory().execute(delete, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteWithOutConfirmed() {
		StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		if (selection != null) {
			// List needremove = selection.toList();
			// needremove.remove(lastLine);
			int oldsize = input.size();

			delete(selection.toList());
			
			dirty.setValue(input.size() != oldsize);
			// 2008-09-25 sundl 修改，避免数据量大的时候会卡一下。
			viewer.getTable().setRedraw(false);
			viewer.refresh();
			viewer.getTable().setRedraw(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.hdt.editor.component.EditorComponent#dispose()
	 */
	@Override
	public void dispose() {

		if (cachedWidth != null) {
			// 保存宽度
			String[] widthes = new String[cachedWidth.length];
			for (int i = 0; i < cachedWidth.length; i++) {
				widthes[i] = String.valueOf(cachedWidth[i]);
			}

			IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
			IDialogSettings mySettings = settings.addNewSection(getId());
			mySettings.put(SAVED_WIDTHES, widthes);
		}

		for (CellEditor cell : editorMap.values()) {
			cell.dispose();
		}

		for (HashMap<Object, CellEditor> map : specialEditorMap.values()) {
			for (CellEditor cell : map.values()) {
				cell.dispose();
			}
		}

	}

	/**
	 * 
	 */
	public void generateNoExsitAttribute() {
		// TODO Auto-generated method stub

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

	protected Image getCheckImage(boolean checked){
		if(checked){
			return ARESEditorPlugin.getImage("checked.gif");
		}else{
			return ARESEditorPlugin.getImage("unchecked.gif");
		}
	}
	
	public HashMap<String, TableViewerColumn> getColumnMap() {
		return columnMap;
	}

	protected int getComboValue(String value, String[] sourceProperties) {
		for (int i = 0; i < sourceProperties.length; i++) {
			if (value.equals(sourceProperties[i]))
				return i;
		}
		return -1;
	}

	/**
	 * 得到表格区域的布局信息，可用来调节高度
	 * 
	 * @return
	 */
	protected GridData getCompositeLayoutData() {
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 100;
		gd.verticalSpan = 1;
		return gd;
	}
	
	public HashMap<String, CellEditor> getEditorMap() {
		return editorMap;
	}

	protected Color getForeground(Object data) {
		return null;
	}

	protected abstract String getId();

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
			boolean isValid = TableViewerExComponent.this.isValidData(row);
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

	/**
	 * 取得上次保存的列宽。
	 * 
	 * @return 上次保存的列宽数组。
	 */
	protected int[] getSavedColumnWidthes() {
		String[] savedWidthes = null;
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings().getSection(getId());
		// 注意这里从HSUi取出来的settings是序列化到一个xml中的，不是放到内存中
		// 所以一段出错就必须清空settings，不然始终保存的是原来的错误列宽信息
		settings = null;
		if (settings != null) {
			savedWidthes = settings.getArray(SAVED_WIDTHES);
			if (savedWidthes != null) {
				int[] saved = new int[savedWidthes.length];
				for (int i = 0; i < savedWidthes.length; i++) {
					saved[i] = Integer.parseInt(savedWidthes[i]);
				}
				return saved;
			}
		}
		return new int[0];
	}

	public TableViewer getTableViewer() {
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到该单元格toolTip内容,可以返回null
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	public String getToolTipText(Object element, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到该单元格toolTip内容(包括背景色信息)
	 * <br>同一单元格,如果getToolTipText也有内容, 本内容可能被覆盖
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	public ErrorMessage getToolTipTextBackground(Object element, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到该单元格toolTip内容(包括前景色信息)
	 * <br>
	 * @param element
	 * @param property
	 * @param isBackGroundAble 表示是否在当前文字为空的时候  开启背景色(值为空时, 前景色是无法显示的)
	 * @return
	 */
	public ErrorMessage getToolTipTextForeground(Object element, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获得指定单元格的值，用于CellEditor
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	public Object getValue(T data, String property){
		ReflectInvokeHelper helper;
		try {
			helper = new ReflectInvokeHelper(data,property);
			return helper.invokeGetMothod().toString();
		} catch (Exception e) {
			System.out.println(property);
			e.printStackTrace();
		}
		return null;
	}

	public TableViewer getViewer() {
		return this.viewer;
	}

	/**
	 * 处理表格的选中事件
	 * 
	 * @param event
	 */
	protected void handleSelection(SelectionChangedEvent event) {}

	private void initComposite(Composite client) {
		int style = 0;
		if (isFullSelection)
			style = SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
		else
			style = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
		tbComposite = createFilteredTable(client, style);
		viewer = tbComposite.getViewer();

		// 设置表格风格属性
		final Table table = viewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tbComposite.setLayoutData(getCompositeLayoutData());


		// 开启Tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);

		// 快捷键添加
		// 文档过少，不知道应该怎么比较好的添加快捷键
		table.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 'c' && e.stateMask == SWT.CTRL) {
					if (canCopy()) {
						copy();
					}
				} else if (e.keyCode == 'x' && e.stateMask == SWT.CTRL) {
					if (canCut()) {
						cut();
					}
				} else if (e.keyCode == 'v' && e.stateMask == SWT.CTRL) {
					if (canPaste()) {
						paste();
					}
				} else if (e.keyCode == SWT.DEL) {

					if (canDelete()) {
						delete();
					}
				} else if (e.keyCode == 'a' && e.stateMask == SWT.CTRL) {
					viewer.getTable().selectAll();
				} else if (e.keyCode == SWT.INSERT) {
					if (canInsert()) {
						insert();
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (isFullSelection) {
					handleSelection(event);
				}
				selectColumnIndex = -1;
				selectedProperty = null;
			}
		});

		initViewColumn();

		viewer.setContentProvider(new TableViewerContentProvider());
		viewer.setInput(input);
		
		// 双击才能修改单元格
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				if(doubleCheckChange){
					return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL || 
					event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION || 
					event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
				}else{
					return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL || 
					event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION || 
					event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
				}
			}
		};

		TableViewerEditor.create(viewer, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
	}

	protected void initTableMenu() {
		// 添加复制，粘贴，删除
		TableViewerActionGroup group = new TableViewerActionGroup(this);
		group.fillContextMenu(new MenuManager());

	}

	/**
	 * 初始化标题栏及相关部分<BR>
	 * 使用setColumns方法
	 */
	protected abstract void initViewColumn();

	public void insert() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		int index = input.indexOf(selection.getFirstElement());
		List adds = new ArrayList();
		adds.add(createBlankData());
		if (index == -1) {
			add(adds,input.size());
		} else {
			add(adds,index + 1);
		}
		dirty.setValue(true);
		viewer.getTable().setRedraw(false);
		viewer.refresh();
		viewer.getTable().setRedraw(true);
	}

	/**
	 * @return the addOnlyOne
	 */
	public boolean isAddOnlyOne() {
		return addOnlyOne;
	}

	/**
	 * @return the isFullSelection
	 */
	public boolean isFullSelection() {
		return isFullSelection;
	}

	final protected boolean isLastLine(T data) {
		return data == lastLine;
	}


	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	
	/**
	 * @return the useAutoGrow
	 */
	protected boolean isUseAutoGrow() {
		return useAutoGrow;
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
	 * 监听列宽的改变并刷新缓存。
	 * 
	 * @param column
	 */
	private void listenToColumnWidth(final TableColumn column) {
		Table table = column.getParent();
		final int index = table.indexOf(column);
		column.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				cachedWidth[index] = column.getWidth();
			}

		});
	}

	public void paste() {
		if (testClassInstance instanceof ICreateInstance) {
			StructuredSelection selection = (StructuredSelection) viewer.getSelection();
			int index = input.size();
			if (!selection.isEmpty()) {
				index = input.indexOf(selection.getFirstElement());
				if (index == -1) {
					index = input.size();
				}
			}

			List<T> added = new ArrayList<T>();
			for (Object obj : (List) Clipboard.instance.getData()) {
				added.add((T) ((ICreateInstance) obj).getNewInstance());
			}

			add(added,index);
			dirty.setValue(true);
			viewer.refresh();
		}

	}
	

	/**
	 * 移除之前设置的特定单元格编辑器<BR>
	 * 注意的事，移除的CellEditor应该手工被dispose
	 * 
	 * @param obj
	 */
	final protected void removeSpecialCellEditor(T obj) {
		specialEditorMap.remove(obj);
	}
	
	/**
	 * 只能添加一条记录，不能自动增长
	 * @param addOnlyOne
	 *            the addOnlyOne to set
	 */
	public void setAddOnlyOne(boolean addOnlyOne) {
		this.addOnlyOne = addOnlyOne;
		this.useAutoGrow = false;
	}

	/**
	 * 设置列共同的单元格编辑器<BR>
	 * 本方法会覆盖<CODE>setColumns</CODE>的设置，但是不会覆盖<CODE>addSpecialCellEditor</CODE>
	 * 的设置
	 * 
	 * 
	 * @param property
	 * @param editor
	 */
	final protected void setColumnCellEditor(String property, CellEditor editor) {
		editorMap.put(property, editor);
	}

	/**
	 * 设置初始信息<BR>
	 * 
	 * 
	 * @param captions
	 *            标题
	 * @param widths
	 *            标题宽度
	 * @param styles
	 *            风格，可为null，则全部为默认的SWT.NONE
	 * @param propertys
	 *            属性名，可为null，则以标题名作为属性名
	 * @param editors
	 *            单元格编辑器,可为null，则使用TextCellEditor
	 */
	final protected void setColumns(String[] captions, int[] widths, int[] styles, String[] propertys, CellEditor[] editors) {

		tablePropertys = new String[propertys.length];
		tableTitles = new String[captions.length];

		if (captions == null) {
			return;
		}

		if (styles == null) {
			styles = new int[captions.length];
			for (int i = 0; i < styles.length; i++) {
				styles[i] = SWT.NONE;
			}
		}

		if (propertys == null) {
			propertys = captions;
		}
		System.arraycopy(propertys, 0, tablePropertys, 0, propertys.length);
		System.arraycopy(captions, 0, tableTitles, 0, captions.length);

		if (editors == null) {
			editors = new CellEditor[captions.length];
			for (int i = 0; i < editors.length; i++) {
				editors[i] = new TextCellEditor(viewer.getTable());
			}
		}

		// 恢复上次保存的宽度
		if (captions.length == widths.length && captions.length == styles.length && captions.length == propertys.length && captions.length == editors.length) {

			cachedWidth = new int[captions.length];
			int[] savedWidthes = getSavedColumnWidthes();
			for (int i = 0; i < captions.length; i++) {
				final TableColumn column = new TableColumn(viewer.getTable(), styles[i]);
				column.setText(captions[i]);
				if (savedWidthes.length == captions.length) {
					column.setWidth(savedWidthes[i]);
				} else {
					column.setWidth(widths[i]);
				}
				// 监听列宽
				listenToColumnWidth(column);
				column.setMoveable(true);
				final int j = i;
				final String p = propertys[i];

				column.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						if (column == (TableColumn) e.getSource()) {
							selectColumnIndex = j;
							selectedProperty = p;

							viewer.getTable().setSelection(-1);
						}
					}
				});
				TableViewerColumn viewercolumn = new TableViewerColumn(viewer, column);
				viewercolumn.setEditingSupport(new DelegateEditingSupport(propertys[i]));
				viewercolumn.setLabelProvider(new DelegateCellLabelProvider(propertys[i]));

				editorMap.put(propertys[i], editors[i]);
				columnMap.put(propertys[i], viewercolumn);

				checkCache.put(propertys[i], new HashMap<Object, Integer>());
				checkStatus.put(propertys[i], false);

				specialEditorMap.put(propertys[i], new HashMap<Object, CellEditor>());
			}

		}
	}

	public void setDoubleCheckChange(boolean doubleCheckChange) {
		this.doubleCheckChange = doubleCheckChange;
	}

	public void setEditable(boolean editable) {
		setReadOnly(!editable);
		if (buttons == null)
			return;
	}

	/**
	 * @param isFullSelection
	 *            the isFullSelection to set
	 */
	public void setFullSelection(boolean isFullSelection) {
		this.isFullSelection = isFullSelection;
	}

	public void setImports(String[] imports) {

	}

	/**
	 * @param readOnly
	 *            the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		// setEditable(!readOnly);
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
			viewer.refresh();
		}
	}
	
	/**
	 * @param useAutoGrow
	 *            the useAutoGrow to set
	 */
	protected void setUseAutoGrow(boolean useAutoGrow) {
		this.useAutoGrow = useAutoGrow;
	}
	
	/**
	 * 将CellEditor的值设置回单元格
	 * 
	 * @param data
	 * @param property
	 * @param value
	 */
	public void setValue(T data, String property, Object value, boolean shouldRefresh){
		ReflectInvokeHelper helper;
		if(!getValue(data,property).equals(value) ){
			dirty.setValue(true);
		}
		try {
			helper = new ReflectInvokeHelper(data,property);
			helper.invokeSetMothod(value);
			if(shouldRefresh){
				getViewer().refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
