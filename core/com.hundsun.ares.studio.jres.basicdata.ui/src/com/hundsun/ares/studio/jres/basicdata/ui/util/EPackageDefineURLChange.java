/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;

/**
 * @author lvgao
 *
 */
public class EPackageDefineURLChange  extends ResourceChange{

	IARESResource resource;
	String oldName;
	String newName;
	
	public EPackageDefineURLChange(IARESResource resource,String oldName, String newName){
		this.resource = resource;
		this.oldName = oldName;
		this.newName = newName;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return String.format("基础数据配置重构中URL从%s改为%s", oldName,newName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.resource.ResourceChange#getModifiedResource()
	 */
	@Override
	protected IResource getModifiedResource() {
		return resource.getResource();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		try {
			EpacakgeDefineList  defineList= resource.getInfo(EpacakgeDefineList.class);
			
			for(int i = defineList.getItems().size() - 1; i >= 0; i--){
				if(StringUtils.equals(defineList.getItems().get(i).getUrl(), oldName)){
					defineList.getItems().get(i).setUrl(newName);
				}
			}
			
			resource.save(defineList, true, new NullProgressMonitor());
		} catch (Exception e) {
		}
		return new EPackageDefineURLChange(resource, newName, oldName);
	}


}
