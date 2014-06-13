/**
 * 源程序名称：MetadataTreeContentProvider.java
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * 因为可能有相同对象出现在不同位置，所以被适用的{@link TreeViewer}必须设置<code>setUseHashlookup(false)</code>
 * @author gongyf
 *
 */
public class MetadataTreeContentProvider implements ITreeContentProvider {
	
	private List<CategoryListener> categoryListeners = new ArrayList<MetadataTreeContentProvider.CategoryListener>();
	
	public interface CategoryListener {
		void showCategoryStateChanged();
	}

	private boolean showCategory = false;
	/**
	 * 未分组的分组
	 */
	private UncategorizedItemsCategoryImpl other;
	
	/**
	 * 获取未分组对象
	 * @return the other
	 */
	public UncategorizedItemsCategoryImpl getUncategorizedCategory() {
		return other;
	}
	
	/**
	 * @param showCategory the showCategory to set
	 */
	public void setShowCategory(boolean showCategory) {
		this.showCategory = showCategory;
		fireStateChangeEvent();
	}
	
	public void addShowCategoryStateChangeListener(CategoryListener listener) {
		categoryListeners.add(listener);
	}
	
	private void fireStateChangeEvent() {
		for (CategoryListener listener : categoryListeners) {
			listener.showCategoryStateChanged();
		}
	}
	
	/**
	 * @return the showCategory
	 */
	public boolean isShowCategory() {
		return showCategory;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		other = new UncategorizedItemsCategoryImpl((MetadataResourceData<?>) newInput);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof MetadataResourceData) {
			MetadataResourceData<?> data = (MetadataResourceData<?>) inputElement;
			if (isShowCategory()) {
				// 需要将没有被分组的对象放到未分组的分组下面去
				List<MetadataCategory> categories = new ArrayList<MetadataCategory>();
				categories.addAll(data.getRoot().getChildren());
				categories.add(other);
				return categories.toArray();
			} else {
				return ((MetadataResourceData) inputElement).getItems().toArray();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MetadataCategory) {
			List<Object> children = new ArrayList<Object>();
			children.addAll(((MetadataCategory) parentElement).getChildren());
			children.addAll(((MetadataCategory) parentElement).getItems());
			return children.toArray();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof MetadataCategory) {
			return ((MetadataCategory) element).eContainer();
		} else if (element instanceof MetadataItem) {
			if (isShowCategory()) {
				EList<MetadataCategory> categories = ((MetadataItem) element).getCategories();
				if (categories.isEmpty()) {
					// 应该在未分组下
					return other;
				} else {
					return categories.get(0);
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof MetadataCategory) {
			MetadataCategory category = (MetadataCategory) element;
			return !category.getChildren().isEmpty() || !category.getItems().isEmpty();
		}
		return false;
	}

}
