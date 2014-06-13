/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.jface.gridviewer.GridTreeViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.celleditor.ISprecialCellEditor;
import com.hundsun.ares.studio.ui.grid.GridViewerExComponent;
import com.hundsun.ares.studio.ui.util.HSColorManager;

/**
 * 基本的树编辑器 这一层提供了LabelProvider和ContentProvider
 * 包括了一些和展现相关的操作
 * @author maxh
 *
 * @param <T>
 */
public abstract class GridTreeViewerBasicComponent<T> extends GridViewerExComponent<T>  {

	protected GridTreeViewer viewer = null;

	
	/** 带过滤器的树视图 */
	protected GridFilteredTree filteredTree;
	
	/** 第一层的附加编辑行 */
	protected T lastLine = (T)createBlankData(null);
	
	/** 父节点和附加行的关联map */
	protected HashMap<Object, Object> childrenLastLine = new HashMap<Object, Object>();
	
	/** 父节点和孩子节点的关联map */
	protected HashMap<Object, List<Object>> childrenMap = new HashMap<Object, List<Object>>();
	
	/**
	 * 判定指定单元格是否能编辑
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @return
	 */
	protected abstract boolean canEdit(Object data, String property);
	
	/**
	 * 新建一个空白数据
	 * 
	 * @param parent 属于的父节点，null的时候代表为根结点
	 * @return
	 */
	protected abstract Object createBlankData(Object parent);
	
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

	public HashMap<Object, Object> getChildrenLastLine() {
		return childrenLastLine;
	}

	public HashMap<Object, List<Object>> getChildrenMap() {
		return childrenMap;
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
	protected Image getImage(Object data, String property) {
		return null;
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
	
	/**
	 * 用于过滤器
	 * 
	 * @return
	 */
	protected PatternFilter getPatternFilter() {
		return new TreePatternFilter();
	}
	
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
	 * 得到该单元格toolTip内容,可以返回null
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	public String getToolTipText(Object element, String property) {
		return null;
	}
	
	public GridTreeViewer getTreeViewer() {
		return viewer;
	}
	
	/**
	 * 获得指定单元格的值，用于CellEditor
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @return
	 */
	public abstract Object getValue(Object data, String property);
	
	public GridTreeViewer getViewer() {
		return viewer;
	}
	
	protected void initComposite(Composite client) {
		filteredTree = new GridFilteredTree(client, getStyle(), getPatternFilter(),this);
		filteredTree.setLayoutData(getCompositeLayoutData());
		viewer = filteredTree.getViewer();
		initViewer(viewer);
		viewer.setContentProvider(new TreeViewerContentProvider());
		viewer.setInput(input);
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

//	public String isRequired(Object element, String property) {
//		return null;
//	}

	/**
	 * 将CellEditor的值设置回单元格，是否应该变脏和刷新会在基类中完成，子类无需调用判断
	 * 
	 * @param data 数据模型
	 * @param property 属性
	 * @param value 设置的值
	 */
	public abstract void setValue(Object data, String property, Object value);
	
	protected class DelegateCellLabelProvider extends ColumnLabelProvider {

		protected String property;
		
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
				String errMessage = GridTreeViewerBasicComponent.this.isValid(element, property);
				errMsgMap.put(element, errMessage);
				if (errMessage != null) {
					return colorManager.getColor(HSColorManager.RED);
				}
//				String requiredMessage = GridTreeViewerBasicComponent.this.isRequired(element, property);
//				if(requiredMessage!=null)
//				{
//					errMsgMap.put(element, requiredMessage);
//					return colorManager.getColor(HSColorManager.GREEN);
//				}
			}
			
			return GridTreeViewerBasicComponent.this.getBackground(element, property);
		}
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
		 */
		@Override
		public Color getForeground(Object element) {
			if (!isLastLine(element)) {
				String errMessage = GridTreeViewerBasicComponent.this.isValidForeground(element, property);
				errMsgMap.put(element, errMessage);
				if (errMessage != null) {
					return colorManager.getColor(HSColorManager.RED);
				}
//				String requiredMessage = GridTreeViewerBasicComponent.this.isRequired(element, property);
//				if(requiredMessage!=null)
//				{
//					errMsgMap.put(element, requiredMessage);
//					return colorManager.getColor(HSColorManager.GREEN);
//				}
			}
			
			return GridTreeViewerBasicComponent.this.getForeground(element, property);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			if(GridTreeViewerBasicComponent.this.getCellEditor(element, property) instanceof ISprecialCellEditor){
				ISprecialCellEditor cellEditor = (ISprecialCellEditor)GridTreeViewerBasicComponent.this.getCellEditor(element, property);
				return cellEditor.getImage(GridTreeViewerBasicComponent.this.getValue(element,property));
			}
			return GridTreeViewerBasicComponent.this.getImage(element, property);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			if(GridTreeViewerBasicComponent.this.getCellEditor(element, property) instanceof ISprecialCellEditor){
				ISprecialCellEditor cellEditor = (ISprecialCellEditor)GridTreeViewerBasicComponent.this.getCellEditor(element, property);
				return cellEditor.getText(GridTreeViewerBasicComponent.this.getValue(element,property));
			}
			return GridTreeViewerBasicComponent.this.getText(element, property);
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
			if(errMsgMap.get(element) != null && !StringUtil.isEmpty(errMsgMap.get(element))){
				return errMsgMap.get(element);
			} 
			return GridTreeViewerBasicComponent.this.getToolTipText(element,property);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(java.lang.Object)
		 */
		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return -1;
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
				String label = GridTreeViewerBasicComponent.this.getText(element, property);
				if (label != null && wordMatches(label)) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	/**
	 * 内容提供器
	 * <br>注： jdk6和jdk5对@override定义不一样
	 * @author gongyf
	 *
	 */
	public class TreeViewerContentProvider implements  ITreeContentProvider {
		public void dispose() {
			
		}

		
		public Object[] getChildren(Object parentElement) {
			
			List<Object> children = GridTreeViewerBasicComponent.this.getChildren(parentElement);
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

		
		public Object[] getElements(Object inputElement) {
			
			// 最末行记录清空
			//lastLines.clear();
			
			List<Object> objs = new ArrayList<Object>();
			
			// 先加入数据
			objs.addAll((List)inputElement);
			
			// 加上最末行
			if (useAutoGrow && !readOnly && lastLine != null) {
				objs.add(lastLine);
			}

			return objs.toArray();
		}

		
		public Object getParent(Object element) {
			return GridTreeViewerBasicComponent.this.getParent(element);
		}

		
		public boolean hasChildren(Object element) {
			// 附加编辑行没有孩子节点
			if (isLastLine(element)) {
				return false;
			}
			
			return GridTreeViewerBasicComponent.this.getChildren(element) != null;
		}

		
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		}
		
	}
	
	public void refresh() {
		if(viewer != null && !viewer.getControl().isDisposed()){
			viewer.refresh();
		}
	}

	/**
	 * 取得所需的前景色,不过错误的红色优先于这个设定
	 * 
	 * @param element
	 * @param property
	 * @return
	 */
	public Color getForeground(Object element, String property) {
		return null;
	}

	/**
	 * @param element
	 * @param property
	 * @return
	 */
	public String isValidForeground(Object element, String property) {
		return null;
	}
}
