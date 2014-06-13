/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
//import org.eclipse.emf.ecore.EStructuralFeature;
//import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.jface.gridviewer.GridTreeViewer;
import org.eclipse.nebula.jface.gridviewer.GridViewerColumn;
import org.eclipse.nebula.jface.gridviewer.GridViewerEditor;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.model.extendable.ExtendFieldsEntity;
import com.hundsun.ares.studio.core.model.extendable.ExtendFieldsHeader;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendFieldManager;
import com.hundsun.ares.studio.ui.grid.renderer.ColumnHeaderRenderer;
import com.hundsun.ares.studio.ui.page.IExtendFieldLoader;
import com.hundsun.ares.studio.ui.userdialog.XmlConfigInterface;
import com.hundsun.ares.studio.ui.userdialog.XmlConfigInterfaceConverter;
//import org.eclipse.core.databinding.observable.map.IObservableMap;
//import org.eclipse.emf.databinding.EMFObservables;

/**
 * grid基础控件
 * @author maxh
 *
 */
public abstract class GridViewerExComponent<T> extends GridViewerStyleControl<T>{

//	protected ObservableListContentProvider tableViewerContentProvider = new ObservableListContentProvider();
	
	/**
	 * 列ID 不包括拓展列
	 */
	protected String[] viewerPropertys;
	
	/**
	 * 列名 不包括拓展列
	 */
	protected String[] viewerTitles;
	
	/** 属性名与列对象映射 */
	protected HashMap<String, GridViewerColumn> columnMap = new HashMap<String, GridViewerColumn>();
	
	/** 特定的单元格编辑器，优先级高于默认 */
	protected HashMap<String, HashMap<Object, CellEditor> > specialEditorMap = new HashMap<String, HashMap<Object,CellEditor>>();
	
	/** 属性名与默认单元格修改器映射 */
	protected HashMap<String, CellEditor> editorMap = new HashMap<String, CellEditor>();
	
	// 缓存的列宽数组。 因为dispose()调用的时候，所有的控件已经被dispose了，故无法再获取宽度。
	// 所以只能加个监听列宽的监听器，更改此缓存。
	protected int[] cachedWidth;
	
	protected static final String SAVED_WIDTHES = "saved_width";
	
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
	
	abstract protected GridViewerColumn createColumn(String property,GridColumn column);
	
	/**
	 * 得到表格区域的布局信息，可用来调节高度
	 * 
	 * @return
	 */
	protected GridData getCompositeLayoutData() {
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 200;
		gd.verticalSpan = 1;
		return gd;
	}
	
	protected CellEditor getCellEditor(Object element,String property) {
		HashMap<Object, CellEditor> map = specialEditorMap.get(property);
		if (map != null && map.containsKey(element)) {
			return map.get(element);
		}
		return editorMap.get(property);
	}
	
	abstract protected String getId();
	
	/**
	 * 取得上次保存的列宽。
	 * @return 上次保存的列宽数组。
	 */
	protected int[] getSavedColumnWidthes() {
		String[] savedWidthes = null;
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings().getSection(getId());
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
	
	protected abstract Grid getGrid();
	
	/**
	 * 初始化标题栏及相关部分<BR>
	 * 使用setColumns方法
	 */
	protected abstract void initViewColumn();
	
	/**
	 * 初始化控件
	 * 主要是设置表格风格
	 * @param viewer
	 */
	protected void initViewer(ColumnViewer viewer) {
		// 开启Tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		//初始化标题栏及相关部分
		initViewColumn();
		//设定修改事件 单击修改还是双击修改
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

		
		if(viewer instanceof GridTableViewer){
			GridViewerEditor.create(((GridTableViewer)viewer), actSupport, ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
		}else if(viewer instanceof GridTreeViewer){
			GridViewerEditor.create(((GridTreeViewer)viewer), actSupport, ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
		}
		
		//设置上下文帮助关联
		if(getHelpContextId() != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getGrid(), getHelpContextId());
		}
	}
	
	/**
	 * 子类重写该方法
	 * 提供上下文帮助菜单
	 * @return
	 */
	protected String getHelpContextId() {
		return null;
	}

	/**
	 * 监听列宽的改变并刷新缓存。
	 * 
	 * @param column
	 */
	private void listenToColumnWidth(final GridColumn column) {
		Grid table = column.getParent();
		final int index = table.indexOf(column);
		column.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				cachedWidth[index] = column.getWidth();
			}
		});
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
	protected void setColumns(String[] captions, int[] widths, int[] styles, String[] propertys, CellEditor[] editors) {
		if (captions == null) {
			return;
		}
		if (propertys == null) {
			propertys = captions;
		}
		if (styles == null) {
			styles = new int[captions.length];
			for (int i = 0; i < styles.length; i++) {
				styles[i] = SWT.NULL;
			}
		}
		//cellEditor默认为全部是普通文本控件
		if (editors == null) {
			editors = new CellEditor[captions.length];
			for (int i = 0; i < editors.length; i++) {
				editors[i] = new TextCellEditor(getGrid());
			}
		}
		
		viewerPropertys = new String[propertys.length];
		viewerTitles = new String[captions.length];
		
		System.arraycopy(propertys, 0, viewerPropertys, 0, propertys.length);
		System.arraycopy(captions, 0, viewerTitles, 0, captions.length);
		
		//加上拓展列的信息
		ExtendFieldsEntity extendEntity = getExtendFields();
		if(extendEntity.getExtendFields().size() != 0){
			ColumnsFormater format = new ColumnsFormater(captions,widths,styles,propertys,editors,extendEntity);
			captions = format.getCaptions();
			widths = format.getWidths();
			styles = format.getStyles();
			propertys = format.getPropertys();
			editors = format.getEditors();
		}
		
		


		// 恢复上次保存的宽度 创建列
		if (captions.length == widths.length 
				&& captions.length == styles.length 
				&& captions.length == propertys.length 
				&& captions.length == editors.length) {
			cachedWidth = new int[captions.length];
			int[] savedWidthes = getSavedColumnWidthes();
			ColumnHeaderRenderer columnHeaderRenderer = new ColumnHeaderRenderer();
			columnHeaderRenderer.setColor(getColumnTopColor());
			columnHeaderRenderer.setLineColor(getColumnTopLineColor());
			for (int i = 0; i < captions.length; i++) {
				final GridColumn column = new GridColumn(getGrid(), styles[i]);
				column.setText(captions[i]);
				column.setHeaderRenderer(columnHeaderRenderer);
				if (savedWidthes.length == captions.length) {
					if(savedWidthes[i] == 0){
						column.setWidth(widths[i]);
					}else {
						column.setWidth(savedWidthes[i]);
					}
				} else {
					column.setWidth(widths[i]);
				}
				// 监听列宽
				listenToColumnWidth(column);
				column.setMoveable(true);

				GridViewerColumn viewercolumn = createColumn(propertys[i],column);
				editorMap.put(propertys[i], editors[i]);
				columnMap.put(propertys[i], viewercolumn);
				specialEditorMap.put(propertys[i], new HashMap<Object, CellEditor>());
			}
		}
	}
	
	/**
	 * 增加对表格的EMF绑定支持
	 * @param captions
	 * @param widths
	 * @param styles
	 * @param propertys
	 * @param editors
	 * @param attr
	 * @author wangdong
	 */
	protected void setColumns(String[] captions, int[] widths, int[] styles,
			String[] propertys, CellEditor[] editors, EAttribute[] attr) {
		if (captions == null) {
			return;
		}
		if (propertys == null) {
			propertys = captions;
		}
		if (styles == null) {
			styles = new int[captions.length];
			for (int i = 0; i < styles.length; i++) {
				styles[i] = SWT.NULL;
			}
		}
		// cellEditor默认为全部是普通文本控件
		if (editors == null) {
			editors = new CellEditor[captions.length];
			for (int i = 0; i < editors.length; i++) {
				editors[i] = new TextCellEditor(getGrid());
			}
		}

		viewerPropertys = new String[propertys.length];
		viewerTitles = new String[captions.length];

		System.arraycopy(propertys, 0, viewerPropertys, 0, propertys.length);
		System.arraycopy(captions, 0, viewerTitles, 0, captions.length);

		// 加上拓展列的信息
		ExtendFieldsEntity extendEntity = getExtendFields();
		if (extendEntity.getExtendFields().size() != 0) {
			ColumnsFormater format = new ColumnsFormater(captions, widths,
					styles, propertys, editors, extendEntity);
			captions = format.getCaptions();
			widths = format.getWidths();
			styles = format.getStyles();
			propertys = format.getPropertys();
			editors = format.getEditors();
		}

		// 恢复上次保存的宽度 创建列
		if (captions.length == widths.length
				&& captions.length == styles.length
				&& captions.length == propertys.length
				&& captions.length == editors.length) {
			cachedWidth = new int[captions.length];
			int[] savedWidthes = getSavedColumnWidthes();
			ColumnHeaderRenderer columnHeaderRenderer = new ColumnHeaderRenderer();
			columnHeaderRenderer.setColor(getColumnTopColor());
			columnHeaderRenderer.setLineColor(getColumnTopLineColor());
			for (int i = 0; i < captions.length; i++) {
				final GridColumn column = new GridColumn(getGrid(), styles[i]);
				column.setText(captions[i]);
				column.setHeaderRenderer(columnHeaderRenderer);
				if (savedWidthes.length == captions.length) {
					column.setWidth(savedWidthes[i]);
				} else {
					column.setWidth(widths[i]);
				}
				// 监听列宽
				listenToColumnWidth(column);
				column.setMoveable(true);

				GridViewerColumn viewercolumn = createColumn(propertys[i],
						column);
				editorMap.put(propertys[i], editors[i]);
				columnMap.put(propertys[i], viewercolumn);
				specialEditorMap.put(propertys[i],
						new HashMap<Object, CellEditor>());
				
//				if(attr!=null){
//					IObservableMap[] attributeMaps = EMFObservables
//							.observeMaps(
//									tableViewerContentProvider.getKnownElements(),
//									new EStructuralFeature[] {attr[i]});
//					viewercolumn.setLabelProvider(new GenericObservableMapCellLabelProvider(
//									attributeMaps, "{0}"));
//				}
			}
		}
	}

	
	/**
	 * 获取拓展列
	 * @return
	 */
	protected ExtendFieldsEntity getExtendFields(){
		ExtendFieldsEntity entity = new ExtendFieldsEntity();
		entity.getExtendFields().addAll(readPluginExtendFields());
		entity.getExtendFields().addAll(readConfigExtendFields());
		return entity;
	}
	
	/**
	 * 获取用拓展点方式添加的拓展列
	 * @return
	 */
	protected List<ExtendFieldsHeader> readPluginExtendFields(){
		List<ExtendFieldsHeader> result = new ArrayList<ExtendFieldsHeader>();
		IExtendFieldLoader loader = ExtendFieldManager.getDefault().getMap().get(getId());
		if(loader != null && loader.shouldLoad()){
			result.addAll(Arrays.asList(loader.getExtendFields()));
		}
		return result;
	}
	
	/**
	 * FIXME 还没有做完
	 * 获取用配置文件添加的拓展列
	 * @return
	 */
	protected List<ExtendFieldsHeader> readConfigExtendFields(){
		if(getResource() != null){
			XmlConfigInterface config =  XmlConfigInterfaceConverter.getConverter().getConfig(getResource().getARESProject());
			if(config != null){
				List<ExtendFieldsHeader> list = config.getExtendColumns().get(getId());
				if(list != null){
					return list;
				}
			}
		}
		return new ArrayList<ExtendFieldsHeader>();
	}
	
	/* (non-Javadoc)
	 * 在表格关闭的时候记录列宽，便于下次恢复
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
	 * 添加拓展列的工具类
	 * @author maxh
	 */
	class ColumnsFormater{
		List<String> captions = new ArrayList<String>();
		List<Integer> widths = new ArrayList<Integer>();
		List<Integer> styles = new ArrayList<Integer>();
		List<String> propertys = new ArrayList<String>();
		List<CellEditor> editors = new ArrayList<CellEditor>();
		public ColumnsFormater(String[] captions, int[] widths, int[] styles, String[] propertys, CellEditor[] editors,ExtendFieldsEntity extendEntity) {
			this.captions.addAll(Arrays.asList(captions));
			for(int width:widths){
				this.widths.add(width);
			}
			for(int style:styles){
				this.styles.add(style);
			}
			this.propertys.addAll(Arrays.asList(propertys));
			this.editors.addAll(Arrays.asList(editors));
			for(ExtendFieldsHeader header:extendEntity.getExtendFields()){
				this.captions.add(header.getText());
				this.widths.add(header.getWidth());
				this.styles.add(SWT.NULL);
				this.propertys.add(header.getId());
				if(header.getType() == ExtendFieldsHeader.TYPE_COMMON){
					this.editors.add(new TextCellEditor(getGrid()));
				}else{
					try {
						this.editors.add(new ComboBoxCellEditor(getGrid(),header.getValues().split(",")));
					} catch (Exception e) {
						e.printStackTrace();
						this.editors.add(new TextCellEditor(getGrid()));
					}
				}
			}
		}
		public String[] getCaptions() {
			return captions.toArray(new String[captions.size()]);
		}
		public int[] getWidths() {
			int[] result = new int[widths.size()];
			for(int i = 0;i < widths.size();i++){
				result[i] = widths.get(i);
			}
			return result;
		}
		public int[] getStyles() {
			int[] result = new int[styles.size()];
			for(int i = 0;i < styles.size();i++){
				result[i] = styles.get(i);
			}
			return result;
		}
		public String[] getPropertys() {
			return propertys.toArray(new String[propertys.size()]);
		}
		public CellEditor[] getEditors() {
			return editors.toArray(new CellEditor[editors.size()]);
		}
	}
	
}


