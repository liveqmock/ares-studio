/**
 * 源程序名称：MetadataViewerUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * @author gongyf
 *
 */
public class MetadataViewerUtil {
	
	/**
	 * 元数据视图中的表格是否使用分组显示
	 * @param viewer
	 * @return
	 */
	static public boolean isShowCategory(ColumnViewer viewer) {
		IContentProvider provider = viewer.getContentProvider();
		if (provider instanceof MetadataTreeContentProvider ) {
			return ((MetadataTreeContentProvider) provider).isShowCategory();
		}
		
		return false;
	}
	
	/**
	 * 获取未分组的分组模型
	 * @param viewer
	 * @return
	 */
	static public MetadataCategory getUncategorizedCategory(ColumnViewer viewer) {
		IContentProvider provider = viewer.getContentProvider();
		if (provider instanceof MetadataTreeContentProvider ) {
			return ((MetadataTreeContentProvider) provider).getUncategorizedCategory();
		}
		
		return null;
	}
	
	/**
	 * 元数据表格中当前选中的分组，可能是分组被直接选中了，也可能是分组下的条目被选中了<BR>
	 * 多行选中的情况下，只看第一个被选中的行
	 * @param viewer
	 * @param excludeSelf 当选中一个分组时，是否排除自己，即返回这个分组所在的分组
	 * @return
	 */
	static public MetadataCategory getSelectedCategory(ColumnViewer viewer, boolean excludeSelf) {
		ITreeSelection selection = (ITreeSelection) viewer.getSelection();
		if (selection != null && !selection.isEmpty()) {
			TreePath path = selection.getPaths()[0];
			if (!excludeSelf && path.getLastSegment() instanceof MetadataCategory) {
				return (MetadataCategory) path.getLastSegment();
			} else {
				if (path.getSegmentCount() >= 2) {
					for(int i = path.getSegmentCount()-1; i >0 ; i--){
						Object obj = path.getSegment(i-1);
						if(obj instanceof MetadataCategory){
							return (MetadataCategory)obj;
						}
					}
				}
			}

		}
		if (viewer.getInput() == null) {
			return null;
		}
		
		return ((MetadataResourceData<?>) viewer.getInput()).getRoot();
	}
	
//	static public EClass getEClassForFirstSelectedObject(ColumnViewer viewer) {
//		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
//		if (selection != null && !selection.isEmpty()) {
//			return ((EObject)selection.getFirstElement()).eClass();
//		}
//		return null;
//	}
	
	static public MetadataResourceData<?> getMetadataModel(ColumnViewer viewer) {
		return (MetadataResourceData<?>) viewer.getInput();
	}
	
	
	static int getItemIndex(Object parent, TreeItem item) {
		if (parent instanceof Tree) {
			return ((Tree) parent).indexOf(item);
		} else {
			return ((TreeItem) parent).indexOf(item);
		}
	}
	
	static int getItemCount(Object parent) {
		if (parent instanceof Tree) {
			return ((Tree) parent).getItemCount();
		} else {
			return ((TreeItem) parent).getItemCount();
		}
	}
	
	static TreeItem getItem(Object parent, int index) {
		if (parent instanceof Tree) {
			return ((Tree) parent).getItem(index);
		} else {
			return ((TreeItem) parent).getItem(index);
		}
	}
	
	static Object getParentItem(TreeItem item) {
		Object parentItem = item.getParentItem();
		if (parentItem == null) {
			parentItem = item.getParent();
		}
		return parentItem;
	}
	
	static public EObject[] getVisualItems(final ColumnViewer viewer) {
		final Object[] result = new Object[1];
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				try {
					if (viewer instanceof TreeViewer) {
						List<TreeItem> itemList = new ArrayList<TreeItem>();
						Tree tree = ((TreeViewer) viewer).getTree();
						TreeItem topItem = tree.getTopItem();
						// 界面上显示的条目数量
						int itemCount = (int) ((tree.getClientArea().height / tree.getItemHeight()) * 1.2);
						
						int curIndex = 0;
						Object parentItem = getParentItem(topItem);
						
						curIndex = getItemIndex(parentItem, topItem);
						
						while (itemCount > 0) {
							if (curIndex >= getItemCount(parentItem)) {
								if (parentItem instanceof TreeItem) {
									// 如果超出返回了,则返回上一级
									curIndex = getItemIndex( getParentItem((TreeItem) parentItem), (TreeItem) parentItem) + 1;
									parentItem = getParentItem((TreeItem) parentItem);
								} else {
									break;
								}

							} else {
								TreeItem curItem =  getItem(parentItem, curIndex);
								itemList.add(curItem);
								itemCount--;
								if (curItem.getExpanded()) {
									parentItem = curItem;
									curIndex = 0;
								} else {
									curIndex++;
								}
							}
						}
						
						EObject[] objects = new EObject[itemList.size()];
						
						
						for (int i = 0; i < objects.length; i++) {
							objects[i] = (EObject) itemList.get(i).getData();
						}
						
						result[0] = objects;
					}
				} catch (Exception e) {
					result[0] = new EObject[0];
				}
				
			}
		});
		
		return (EObject[]) result[0];
	}
}
