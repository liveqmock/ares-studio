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
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;

/**
 * @author lvgao
 *
 */
public class EPackageDefineTableChange  extends ResourceChange{

	IARESResource resource;
	String oldName;
	String newName;
	
	public EPackageDefineTableChange(IARESResource resource,String oldName, String newName){
		this.resource = resource;
		this.oldName = oldName;
		this.newName = newName;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return "基础数据数据库表配置重构";
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
			
			for(Reference refer:defineList.getReferences()){
				if(StringUtils.equals(refer.getValue(), oldName) && StringUtils.equals(refer.getType(), IBasicDataEpacakgeConstant.MODLE_TYPE_DATABASE)){
					refer.setValue(newName);
				}
			}
			resource.save(defineList, true, new NullProgressMonitor());
		} catch (Exception e) {
		}
		return new EPackageDefineTableChange(resource, newName, oldName);
	}


}
