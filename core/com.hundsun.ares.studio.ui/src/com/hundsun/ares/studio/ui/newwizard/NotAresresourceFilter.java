/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.ui.RefLibContainer;

/**
 * @author lvgao
 *
 */
public class NotAresresourceFilter extends ViewerFilter{

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		//过滤引用
		if (element instanceof RefLibContainer) {
			return false;
		}
		//过滤项目属性
		if (element instanceof IProjectProperty) {
			return false;
		}
		return true;
	}

}
