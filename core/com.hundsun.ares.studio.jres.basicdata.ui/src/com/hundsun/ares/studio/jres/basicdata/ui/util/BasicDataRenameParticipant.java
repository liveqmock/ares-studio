/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.manage.BasicdataDefineManageUtil;

/**
 * @author lvgao
 *
 */
public class BasicDataRenameParticipant  extends RenameParticipant{

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
					||StringUtils.equals(type, IBasicDataRestypes.MasterSlaveLinkTable)
					||StringUtils.equals(type, "table")){
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
			IResource tres = resource.getResource();
			IARESResource defResource = BasicdataDefineManageUtil.getDefineResource(resource.getARESProject(), false);
			if(StringUtils.equals(tres.getFileExtension(), "table")){
				IFile file = (IFile)defResource.getResource();
				if(file.exists()){
					return new EPackageDefineTableChange(defResource, resource.getName(), getArguments().getNewName());
				}
			}else{
				String oldUrl = tres.getProjectRelativePath().toOSString();
				String path =  tres.getParent().getProjectRelativePath().toOSString();
				String newURL = String.format("%s\\%s.%s", path,getArguments().getNewName(),tres.getFileExtension());
				return new EPackageDefineURLChange(defResource, oldUrl, newURL);
			}
		} catch (Exception e) {
		}
		
//		EPackageUtil.get
		return null;
	}

}
