/**
 * 源程序名称：ExtensibleModelTreeContentProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 
 * input 应该是IARESResource
 * @author gongyf
 *
 */
public class ExtensibleModelTreeContentProvider implements ITreeContentProvider {

	/**
	 * @param model
	 * @param modelId
	 */
	public ExtensibleModelTreeContentProvider() {
		super();
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		Assert.isTrue(inputElement instanceof ExtensibleModelEditingRoot);
		
		ExtensibleModelEditingRoot root = (ExtensibleModelEditingRoot) inputElement;
		return root.getCategories().toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ExtensibleModelEditingCategory) {
			return ((ExtensibleModelEditingCategory) parentElement).getEntries().toArray();
		}
		return ArrayUtils.EMPTY_OBJECT_ARRAY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof ExtensibleModelEditingEntry) {
			return ((ExtensibleModelEditingEntry)element).getCategory();
		} else if (element instanceof ExtensibleModelEditingCategory) {
			return ((ExtensibleModelEditingCategory) element).getRoot();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		return element instanceof ExtensibleModelEditingCategory;
	}


}
