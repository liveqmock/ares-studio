/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control.deprecated;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.ICreateInstance;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.grid.EditorComponent;
import com.hundsun.ares.studio.ui.util.Clipboard;
import com.hundsun.ares.studio.ui.util.HSColorManager;

/**
 * 重写的树组件基类
 * 目前无法没有唯一性检查和上下移动功能
 * 子类的单元格修改方式为双击修改<BR>
 * 默认验证失败的列显示为前景红色<BR>
 * 当数据实现了ICreateInstance接口后，才可以进行复制粘贴<BR>
 * 
 * <BR>
 * <BR>
 * <B>PS:需要注意的是本实现中如果外部直接清空了内部数据的时候（如导入时的清空input操作），附加行的map容器无法更新，会造成无用对象的堆积
 * 不过这些对象会在编辑器关闭时清除，引起内存大量使用的机会并不大 </B>
 * 
 * @author gongyf
 *
 * @param <T>
 * 
 * @deprecated
 */
public abstract class TreeViewerExComponent<T/* extends IHaveChildren*/> extends EditorComponent< List<T> > {

	protected TreeViewer viewer = null;
	
	/** 带过滤器的树视图 */
	protected FilteredTree filteredTree;
	
	/** 包含所有按钮 */
	private List<Button> buttons = null;
	
	/** 是否使用自动行增长 */
	protected boolean useAutoGrow = true;
	
	/** 是否为只读，只读不可剪切，粘贴，自动行增长与修改单元格 */
	protected boolean readOnly = false;
	
	/** 第一层的附加编辑行 */
	protected T lastLine = (T)createBlankData(null);
	
	/** 父节点和附加行的关联map */
	protected HashMap<Object, Object> childrenLastLine = new HashMap<Object, Object>();
	
	/** 父节点和孩子节点的关联map */
	protected HashMap<Object, List<Object>> childrenMap = new HashMap<Object, List<Object>>();
	
	/** 特定的单元格编辑器，优先级高于默认 */
	protected HashMap<String, HashMap<Object, CellEditor> > specialEditorMap = new HashMap<String, HashMap<Object,CellEditor>>();
	
	/** 属性名与列对象映射 */
	protected HashMap<String, TreeViewerColumn> columnMap = new HashMap<String, TreeViewerColumn>();
	
	/** 属性名与默认单元格修改器映射 */
	protected HashMap<String, CellEditor> editorMap = new HashMap<String, CellEditor>();
	
	/** 颜色资源统一管理 */ 
	protected HSColorManager colorManager = ARESEditorPlugin.getDefault().getColorManager();
	
	protected String[] treePropertys;
	protected String[] treeTitles;
	
	/**
	 * 单击修改 还是 双击修改
	 */
	private boolean doubleCheckChange = true;
	
	public void setDoubleCheckChange(boolean doubleCheckChange) {
		this.doubleCheckChange = doubleCheckChange;
	}
	/**
	 * 判断是否是附加行
	 * @param obj
	 * @return
	 */
	final protected boolean isLastLine(Object obj) {
		return obj == lastLine || childrenLastLine.containsValue(obj);
	}
	
	/**
	 * 获得除了附加行以外的行的父节点，根结点的话返回null
	 * @param element
	 * @return
	 */
	protected Object getParent(Object element) {
		// 直接在map中查询
		for (Object parent : childrenMap.keySet()) {
			if (childrenMap.get(parent).indexOf(element) != -1) {
				return parent;
			}
		}
		return null;
	}
	
	public HashMap<Object, Object> getChildrenLastLine() {
		return childrenLastLine;
	}
	
	/**
	 * 获得附加行的父节点
	 * 
	 * @param element
	 * @return
	 */
	final protected Object getLastLineParent(Object element) {
		if (element == lastLine) {
			return null;
		}
		for (Object parent : childrenLastLine.keySet()) {
			if (childrenLastLine.get(parent) == element) {
				return parent;
			}
		}
		return null;
	}
	
	/**
	 * 内容提供器
	 * <br>注： jdk6和jdk5对@override定义不一样
	 * @author gongyf
	 *
	 */
	public class TreeViewerContentProvider implements  ITreeContentProvider {
		public Object[] getChildren(Object parentElement) {
			
			List<Object> children = TreeViewerExComponent.this.getChildren(parentElement);
			// 筛选状态下可能会是null
			if (children == null) {
				return new Object[0];
			}
			childrenMap.put(parentElement, children);
			if (useAutoGrow && !readOnly) {

					
				List<Object> lstShowChildren = new ArrayList<Object>();
				lstShowChildren.addAll(children);
				
				Object thisLastLine = childrenLastLine.get(parentElement);
				if (thisLastLine == null) {
					// 第一次需要创建一个
					thisLastLine = createBlankData(parentElement);
					childrenLastLine.put(parentElement, thisLastLine);
				}
				
				lstShowChildren.add(thisLastLine);
				
				return lstShowChildren.toArray();
			}
			return children.toArray();
		}

		
		public Object getParent(Object element) {
			return TreeViewerExComponent.this.getParent(element);
		}

		
		public boolean hasChildren(Object element) {
			// 附加编辑行没有孩子节点
			if (isLastLine(element)) {
				return false;
			}
			
			return TreeViewerExComponent.this.getChildren(element) != null;
		}

		
		public Object[] getElements(Object inputElement) {
			
			// 最末行记录清空
			//lastLines.clear();
			
			List<Object> objs = new ArrayList<Object>();
			
			// 先加入数据
			objs.addAll((List)inputElement);
			
			// 加上最末行
			if (useAutoGrow && !readOnly) {
				objs.add(lastLine);
			}

			return objs.toArray();
		}

		
		public void dispose() {
			
		}

		
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		}
		
	}
	
	protected class DelegateCellLabelProvider extends ColumnLabelProvider {

		private String property;
		
		/** 保存对应行的错误信息 */
		private Map<Object, String> errMsgMap = new HashMap<Object, String>();
		
		public DelegateCellLabelProvider(String property) {
			super();
			this.property = property;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
		 */
		@Override
		public Color getBackground(Object element) {
			if (!isLastLine(element)) {
				String errMessage = TreeViewerExComponent.this.isValid(element, property);
				errMsgMap.put(element, errMessage);
				if (errMessage != null) {
					return colorManager.getColor(HSColorManager.RED);
				}
				String requiredMessage = TreeViewerExComponent.this.isRequired(element, property);
				if(requiredMessage!=null)
				{
					errMsgMap.put(element, requiredMessage);
					return colorManager.getColor(HSColorManager.GREEN);
				}
			}
			
			return TreeViewerExComponent.this.getBackground(element, property);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			return TreeViewerExComponent.this.getImage(element, property);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			return TreeViewerExComponent.this.getText(element, property);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(java.lang.Object)
		 */
		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 300;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.lang.Object)
		 */
		@Override
		public Point getToolTipShift(Object object) {
			return new Point(5, 5);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
		 */
		@Override
		public String getToolTipText(Object element) {
			return errMsgMap.get(element);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(java.lang.Object)
		 */
		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return -1;
		}
		
	}
	
	protected class DelegateEditingSupport extends EditingSupport {

		private String property;
		
		public DelegateEditingSupport(String property) {
			super(TreeViewerExComponent.this.viewer);
			this.property = property;
		}

		@Override
		protected boolean canEdit(Object element) {
			return !readOnly && TreeViewerExComponent.this.canEdit(element, property);
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			HashMap<Object, CellEditor> map = specialEditorMap.get(property);
			if (map.containsKey(element)) {
				return map.get(element);
			}
			return TreeViewerExComponent.this.editorMap.get(property);
		}

		@Override
		protected Object getValue(Object element) {
			return TreeViewerExComponent.this.getValue(element, property);
		}

		@Override
		protected void setValue(Object element, Object value) {
			Object oldValue = getValue(element);
			if (value != null && !value.equals(oldValue)) {
				TreeChangeValueOperation operation = new TreeChangeValueOperation("change",TreeViewerExComponent.this,element,property,value);
				operation.addContext(undoContext);
				try {
					AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				// 判断是否在对最后一行进行写入
				if (isLastLine(element)) {
					if (value != null && !value.equals(oldValue)) {
						commit(TreeViewerExComponent.this.getLastLineParent(element), element);
						
					}
				}
				
				dirty.setValue(true);
			}
		}
		
	}
	
	private class TreePatternFilter extends PatternFilter {
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.dialogs.PatternFilter#isElementVisible(org.eclipse.jface.viewers.Viewer, java.lang.Object)
		 */
		@Override
		public boolean isElementVisible(Viewer viewer, Object element) {
			// 附加行在筛选状态下不显示
			if (isLastLine(element)) {
				return false;
			}
			return super.isElementVisible(viewer, element);
		}

		protected boolean isLeafMatch(Viewer viewer, Object element) {
			for (String property : columnMap.keySet()) {
				String label = TreeViewerExComponent.this.getText(element, property);
				if (label != null && wordMatches(label)) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	/**
	 * 将附加行添加到实际数据中去
	 * 
	 * @param parent
	 * @param child
	 */
	protected void commit(Object parent, Object child) {
		if (parent == null) {
			input.add((T)child);
			lastLine = (T)createBlankData(null);
		} else {
			List<Object> lstChildren = childrenMap.get(parent);
			lstChildren.add(child);
			childrenLastLine.remove(parent);
		}
	}

	public String isRequired(Object element, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 本编辑器是否可写
	 * 
	 * @return
	 */
	public boolean canCopy() {
		
		// 选择的对象需要是同一类型，且实现了ICreateInstance接口
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		
		// 需要有选择的项目
		if (paths.length == 0) {
			return false;
		}
		
		// 需要可以克隆的
		if (!(paths[0].getLastSegment() instanceof ICreateInstance)) {
			return false;
		}
		
		// 需要选择的项目是一种类型
		Class<?> cls = paths[0].getLastSegment().getClass();
		for (TreePath path : paths) {
			if (path.getLastSegment().getClass() != cls) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 是否可剪切
	 * 
	 * @return
	 */
	public boolean canCut() {
		return canCopy() && canDelete();
	}
	
	/**
	 * 是否可粘贴
	 * 
	 * @return
	 */
	public boolean canPaste() {
		
		if (readOnly) return false;
		
		// 必须有个位置被选择
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		if (paths.length == 0) {
			return false;
		}
		
		// 用来判断类型
		Object objTest = paths[0].getLastSegment();
		
		
		// 需要剪贴板不为空，且类型与当前位置的类型一致
		Object obj = Clipboard.instance.getData();
		if (obj != null && obj instanceof List) {
			// 空数据无粘贴意义
			if (!((List) obj).isEmpty()) {
				if (((List) obj).get(0).getClass() == objTest.getClass()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 是否可删除
	 * 
	 * @return
	 */
	public boolean canDelete() {
		
		if (readOnly) {
			return false;
		}    
		
		// 必须有个位置被选择
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		if (paths.length == 0) {
			return false;
		}
		return true;
	}
	   
	/**
	 * 是否可插入
	 * 
	 * @return
	 */
	public boolean canInsert() {
		return !readOnly && ((ITreeSelection)viewer.getSelection()).getPaths().length > 0;
	}
	
	/**
	 * 进行复制操作
	 */
	public void copy() {
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		
		ArrayList<Object> copied = new ArrayList<Object>();
		for (TreePath path : paths) {
			copied.add(path.getLastSegment());
		}
		
		// 放入剪贴板
		Clipboard.instance.setData(copied);
	}
	
	/**
	 * 生成为声明参数
	 */
	 public void generateNoExsitParams(){
		 
	 }
	
	/**
	 * 进行剪切操作
	 */
	public void cut() {
		copy();
		deleteWithOutConfirmed();
	}
	
	/**
	 * 进行粘贴操作
	 */
	public void paste() {
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		
		// 获得需要粘贴的位置和内容，因为经过可否粘贴的判断后，这些必定是正确内容
		TreePath parentPath = paths[0].getParentPath();
		
		List<Object> objs = (List)Clipboard.instance.getData();
		
		if (parentPath == TreePath.EMPTY) {
			int index = input.indexOf(paths[0].getLastSegment());
			if (index == -1) {
				index = input.size();
			}
			for (Object obj : objs) {
				input.add( index, ((ICreateInstance<T>)obj).getNewInstance());
			}
		} else {
			List<Object> lst = childrenMap.get(parentPath.getLastSegment());
			int index = lst.indexOf(paths[0].getLastSegment());
			if (index == -1) {
				index = lst.size();
			}
			for (Object obj : objs) {
				lst.add(index,((ICreateInstance<Object>)obj).getNewInstance());
			}
		}
		
		dirty.setValue(true);
		viewer.refresh();
	}

	/**
	 * 进行删除操作
	 */
	public void delete() {
		boolean confirmed = MessageDialog.openConfirm(viewer.getTree().getShell(),"","确实要删除吗");
		if(!confirmed){
			return ;
		}
		deleteWithOutConfirmed();
	}
	
	public HashMap<Object, List<Object>> getChildrenMap() {
		return childrenMap;
	}
	
	//不经过确认就删除
	public void deleteWithOutConfirmed(){

		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		
		// 可能会删除不同级的内容
		
		// 先排序，路径短的在前面
		Arrays.sort(paths, new Comparator<TreePath>(){

			public int compare(TreePath o1, TreePath o2) {
				return o1.getSegmentCount() - o2.getSegmentCount();
			}});
		
		// 不需要的路径
		ArrayList<TreePath> needlessPaths = new ArrayList<TreePath>();
		
		// 需要用于删除的路径
		ArrayList<TreePath> deletePaths = new ArrayList<TreePath>();
		for (TreePath path : paths) {
			TreePath parentPath = path.getParentPath();
			if (deletePaths.indexOf(parentPath) != -1 || needlessPaths.indexOf(parentPath) != -1) {
				needlessPaths.add(path);
				continue;
			}
			
			deletePaths.add(path);
		}
		// 进行删除操作
		for (TreePath path : deletePaths) {
			TreePath parentPath = path.getParentPath();
			if (parentPath == TreePath.EMPTY) {
				input.remove(path.getLastSegment());
			} else {
				childrenMap.get(parentPath.getLastSegment()).remove(path.getLastSegment());
				childrenLastLine.remove(path.getLastSegment());
			}
		}
		viewer.refresh();
		dirty.setValue(true);
	} 
	
	/**
	 * 进行插入操作
	 */
	public void insert() {
		ITreeSelection sel = (ITreeSelection)viewer.getSelection();
		TreePath[] paths = sel.getPaths();
		
		TreePath parentPath = paths[0].getParentPath();
		List addItems = new ArrayList();
		addItems.add(createBlankData(parentPath.getLastSegment()));
		TreeAddItemOperation operation = new TreeAddItemOperation("add",this,paths,addItems);
		operation.addContext(undoContext);
		try {
			AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		dirty.setValue(true);
		
	}
	
	/**
	 * 取得所需的背景色,不过错误的红色优先于这个设定
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	protected Color getBackground(Object element, String property) {
		return null;
	}
	
	/**
	 * 取得孩子节点列表<BR>
	 * 
	 * <B>注意：视图上的操作是直接反应在这个List上的，所以保存数据的时候请以这个List为准</B><BR>
	 * 
	 * @param parentElement
	 * @return null 表示该节点不会有孩子节点
	 */
	protected abstract List getChildren(Object parentElement);
	
	/**
	 * 创建一个Button对象
	 * 
	 * @param toolkit
	 * @param client
	 * @param caption
	 * @return
	 */
	final protected Button createButton(FormToolkit toolkit, Composite client,
			String caption) {
		
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

		initTreeMenu();

		int size = buttons.size();
		
		// 调整表格
		((GridData) filteredTree.getLayoutData()).verticalSpan = size > 0 ? size : 1;
		return client;
	}

	public TreeViewer getTreeViewer() {
		return viewer;
	}
	
	/**
	 * 用于过滤器
	 * 
	 * @return
	 */
	protected PatternFilter getPatternFilter() {
		return new TreePatternFilter();
	}
	
	protected void initComposite(Composite client) {
		
		int style = SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL
			| SWT.MULTI | SWT.BORDER;
		filteredTree = new FilteredTree(client, style, getPatternFilter());
		
		viewer = filteredTree.getViewer();
		
		final Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 100;
		gd.verticalSpan = 1;
		filteredTree.setLayoutData(gd);
		
		// 双击才能修改单元格
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(
				viewer) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
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

		TreeViewerEditor.create(viewer, actSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL
						| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
						| ColumnViewerEditor.TABBING_VERTICAL
						| ColumnViewerEditor.KEYBOARD_ACTIVATION);

		// 开启Tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		
		tree.addKeyListener(new KeyListener() {

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
					tree.selectAll();
				} else if(e.keyCode == SWT.INSERT) {
					if(canInsert()) {
						insert();
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				
			}
		});
		
		initViewColumn();
		
		viewer.setContentProvider(new TreeViewerContentProvider());
		viewer.setInput(input);
	}
	
	/**
	 * 初始化标题栏及相关部分<BR>
	 * 使用setColumns方法
	 */
	protected abstract void initViewColumn();
	
	/**
	 * 设置初始信息<BR>
	 * 
	 * 
	 * @param captions 标题
	 * @param widths 标题宽度
	 * @param styles 风格，可为null，则全部为默认的SWT.NONE
	 * @param propertys 属性名，可为null，则以标题名作为属性名
	 * @param editors 单元格编辑器,可为null，则使用TextCellEditor
	 */
	 protected void setColumns(String[] captions, int[] widths,
			int[] styles, String[] propertys, CellEditor[] editors) {

		if (captions == null) {
			return;
		}
		
		if( styles == null) {
			styles = new int[captions.length];
			for (int i = 0; i < styles.length; i++) {
				styles[i] = SWT.NONE;
			}
		}
		
		if (propertys == null) {
			propertys = captions;
		}
		this.treeTitles = new String[captions.length];
		this.treePropertys = new String[propertys.length];
		System.arraycopy(propertys, 0, treePropertys, 0, propertys.length);
		System.arraycopy(captions, 0,  treeTitles, 0, captions.length);
		
		if (editors == null) {
			editors = new CellEditor[captions.length];
			for (int i = 0; i < editors.length; i++) {
				editors[i] = new TextCellEditor(viewer.getTree());
			}
		}
		
		if (captions.length == widths.length
				&& captions.length == styles.length
				&& captions.length == propertys.length
				&& captions.length == editors.length) {

			for (int i = 0; i < captions.length; i++) {
				TreeColumn column = new TreeColumn(viewer.getTree(),
						styles[i]);
				column.setText(captions[i]);
				column.setWidth(widths[i]);
				
				TreeViewerColumn viewercolumn = new TreeViewerColumn(viewer, column);
				viewercolumn.setEditingSupport(new DelegateEditingSupport(propertys[i]));
				viewercolumn.setLabelProvider(new DelegateCellLabelProvider(propertys[i]));

				editorMap.put(propertys[i], editors[i]);
				columnMap.put(propertys[i], viewercolumn);
				
				specialEditorMap.put(propertys[i], new HashMap<Object, CellEditor>());
			}

		}
 	}
	
	/**
	 * 初始化菜单
	 */
	protected void initTreeMenu() {
		// 添加复制，粘贴，删除
		TreeViewerActionGroup group = new TreeViewerActionGroup(this);
		group.fillContextMenu(new MenuManager());

	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
	
	/**
	 * 判定指定单元格是否能编辑
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @return
	 */
	protected abstract boolean canEdit(Object data, String property);

	/**
	 * 获得指定单元格的值，用于CellEditor
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @return
	 */
	public abstract Object getValue(Object data, String property);

	/**
	 * 将CellEditor的值设置回单元格，是否应该变脏和刷新会在基类中完成，子类无需调用判断
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @param value 设置的值
	 */
	public abstract void setValue(Object data, String property, Object value,boolean shouldRefresh);

	/**
	 * 新建一个空白数据
	 * 
	 * @param parent 属于的父节点，null的时候代表为根结点
	 * @return
	 */
	protected abstract Object createBlankData(Object parent);

	/**
	 * 获得单元格的显示文本，默认实现是GetValue的toString
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected String getText(Object data, String property) {
		Object value = getValue(data, property);
		if (value != null) {
			return value.toString();
		}
		return "";
		
	}

	/**
	 * 获得单元格特定的图像
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected Image getImage(Object data, String property) {
		return null;
	}
	
	/**
	 * 数据验证<BR>
	 * 返回错误信息，返回null代表无错误
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	protected String isValid(Object data, String property) {
		return null;
	}
	

	public boolean isReadOnly() {
		return readOnly;
	}
	protected int getComboValue(String value,String[] sourceProperties) {
		for (int i = 0; i < sourceProperties.length; i++) {
			if (value.equals(sourceProperties[i]))
				return i;
		}
		return 0;
	}

	
	public TreeViewer getViewer() {
		return viewer;
	}
}
