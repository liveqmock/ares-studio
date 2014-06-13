/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.manage.BasicdataDefineManageUtil;

/**
 * @author lvgao
 *
 */
public class BasicDataMoveParticipant  extends MoveParticipant{

	IARESResource resource ;
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#initialize(java.lang.Object)
	 */
	@Override
	protected boolean initialize(Object element) {
		if(element instanceof IARESResource){
			resource = (IARESResource)element;
			String type =  resource.getType();
			if(StringUtils.equals(type, IBasicDataRestypes.singleTable) 
					||StringUtils.equals(type, IBasicDataRestypes.MasterSlaveTable)
					||StringUtils.equals(type, IBasicDataRestypes.MasterSlaveLinkTable)){
				
				return true;
			}
			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	@Override
	public String getName() {
		getArguments().getDestination();
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
			IARESResource defResource;
			try {
				defResource = BasicdataDefineManageUtil.getDefineResource(resource.getARESProject(), false);
				IResource tres = resource.getResource();
				String oldUrl = tres.getProjectRelativePath().toOSString();
				String path =  tres.getParent().getProjectRelativePath().toOSString();
				if(getArguments().getDestination() instanceof IARESModule){
					IARESModule module = (IARESModule)getArguments().getDestination();
					String newURL = String.format("%s\\%s",module.getResource().getProjectRelativePath().toOSString() ,tres.getName());
					return new EPackageDefineURLChange(defResource, oldUrl, newURL);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

}
