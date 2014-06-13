/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.util.ArrayUtil;

/**
 * @author lvgao
 *
 */
public class RestypeViewerFilter extends ViewerFilter{

	String resType;
	public RestypeViewerFilter(String resType){
		this.resType = resType;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof IARESModuleRoot){
			if(resType == null || resType.length() == 0){
				return true;
			}
			String[] allowType = ModuleRootType2ResTypeMap.getInstance().getAllowedResTypes(((IARESModuleRoot) element).getType());
			if(ArrayUtil.findInArray(allowType, resType) < 0){
				return false;
			}
		}
		return true;
	}

}
