/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * @author lvgao
 *
 */
public class NotCurrentProjectFilter extends ViewerFilter{

	IARESProject currentProject;
	
	public NotCurrentProjectFilter(IARESProject currentProject){
		this.currentProject = currentProject;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		//排除非本工程的显示
		if (element instanceof IARESProject && !currentProject.getElementName().equals(((IARESProject) element).getElementName())) {
			return false;
		}
		return true;
	}

}
