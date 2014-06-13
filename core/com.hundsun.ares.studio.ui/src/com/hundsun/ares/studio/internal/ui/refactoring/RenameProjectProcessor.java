/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceDescriptor;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 
 * @author liaogc
 */
public class RenameProjectProcessor extends ARESRenameProcessor implements IReferenceUpdating {
	
	
	/**
	 * @param element
	 */
	public RenameProjectProcessor(IARESElement element) {
		super(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.stuido.ui.refactor.processor.rename.project";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "重命名项目";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	@Override
	public boolean isApplicable() throws CoreException {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#chechNewElementName(java.lang.String)
	 */
	public RefactoringStatus checkNewElementName(String name) {
		RefactoringStatus status = super.checkNewElementName(name);
		if (!status.hasFatalError()) {
			IProject[] projects = getEnableProjects();
			for(IProject project :projects){
				if(StringUtils.equals(project.getName(), name)){
					status.addError("项目名已经存在");
					break;
				}
			}
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {

		pm.beginTask("", 1); //$NON-NLS-1$
		try {
			RenamePorjectChange change= new RenamePorjectChange(getResource().getFullPath(),getNewElementName());
			change.setDescriptor(new RefactoringChangeDescriptor(createDescriptor()));
			return change;
		} finally {
			pm.done();
		}
	
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getCurrentElementName()
	 */
	public String getCurrentElementName() {
		return ((IARESProject) element).getElementName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getNewElement()
	 */
	@Override
	public IARESElement getNewElement() {
		return null;
	}
	private RenameResourceDescriptor createDescriptor() {
		IResource resource= getResource();

		RenameResourceDescriptor descriptor= new RenameResourceDescriptor();
		descriptor.setProject(resource instanceof IProject ? null : resource.getProject().getName());
		descriptor.setDescription("重命名项目:"+this.getResource().getName()+"修改为:"+this.getNewElementName());
		descriptor.setComment("重命名项目:"+this.getResource().getName()+"修改为:"+this.getNewElementName());
		descriptor.setFlags(RefactoringDescriptor.STRUCTURAL_CHANGE | RefactoringDescriptor.MULTI_CHANGE | RefactoringDescriptor.BREAKING_CHANGE);
		descriptor.setResourcePath(resource.getFullPath());
		descriptor.setNewName(getNewElementName());
		descriptor.setUpdateReferences(false);
		return descriptor;
	}
	
	private IResource getResource(){
		return ((IARESElement)getElements()[0]).getResource();
	}
	
	private IProject[] getEnableProjects(){
		List<IProject> pros = new ArrayList<IProject>();
		IProject[] iprojects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject p : iprojects){
			pros.add(p);
		}
		return pros.toArray(new IProject[0]);
	}
	
}
