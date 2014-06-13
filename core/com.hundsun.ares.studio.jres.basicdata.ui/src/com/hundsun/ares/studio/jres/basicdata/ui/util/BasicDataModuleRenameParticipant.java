/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.manage.BasicdataDefineManageUtil;

/**
 * @author lvgao
 *
 */
public class BasicDataModuleRenameParticipant  extends RenameParticipant{

	IARESModule module ;
	
	private static final String ROOT_TYPE = "com.hundsun.ares.studio.commondata.root";
	private static final String ROOT_TYPE2 = "com.hundsun.ares.studio.jres.moduleroot.commondata";
	private static final String[] types = new String[]{
		IBasicDataRestypes.singleTable,
		IBasicDataRestypes.MasterSlaveTable,
		IBasicDataRestypes.MasterSlaveLinkTable
	};
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#initialize(java.lang.Object)
	 */
	@Override
	protected boolean initialize(Object element) {
		if(element instanceof IARESModule){
			IARESModule tmpmodule = (IARESModule)element;
			if(StringUtils.equals(ROOT_TYPE, tmpmodule.getRoot().getType())
					||StringUtils.equals(ROOT_TYPE2, tmpmodule.getRoot().getType())){
				//空模块不管
				if(tmpmodule.getARESResources(types,true).length > 0){
					this.module = tmpmodule;
					return true;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	@Override
	public String getName() {
		return "基础数据配置重构";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#checkConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws OperationCanceledException {
		return new RefactoringStatus();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		 try {
			 IARESResource defResource = BasicdataDefineManageUtil.getDefineResource(module.getARESProject(), false);
			 String oldURL = module.getResource().getProjectRelativePath().toOSString();
			 String parent = module.getResource().getParent().getProjectRelativePath().toOSString();
			 String newURL = String.format("%s\\%s", parent,getArguments().getNewName());
			 return new BasicdataModuleRenameChange(defResource, oldURL, newURL);
		} catch (Exception e) {
		}
		return null;
	}

}
