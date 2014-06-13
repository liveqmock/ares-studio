/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * 
 * @author sundl
 */
public class RenameModuleProcessor extends ARESRenameProcessor implements IReferenceUpdating{
	
	
	/**
	 * @param element
	 */
	public RenameModuleProcessor(IARESElement element) {
		super(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.stuido.ui.refactor.processor.rename.module";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "重命名模块";
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
			IARESModule module = (IARESModule) element;
			IARESModuleRoot root = module.getRoot();
			IARESModule parentModule = module.getParentModule();
			String newModuleName = null;
			if (parentModule == null) {
				newModuleName = name;
			} else {
				newModuleName = parentModule.getElementName() + "." + name;
			}
			
			IARESModule exists = root.findModule(newModuleName);
			if (exists != null && exists.exists()) {
				status.merge(RefactoringStatus.createFatalErrorStatus(exists.getElementName() + "已经存在!"));
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
		return new RenameModuleChange((IARESModule) element, newElementName);
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getCurrentElementName()
	 */
	public String getCurrentElementName() {
		return ((IARESModule) element).getShortName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getNewElement()
	 */
	@Override
	public IARESElement getNewElement() {
		IARESModule module = (IARESModule) element;
		IARESModuleRoot root = module.getRoot();
		IARESModule parentModule = module.getParentModule();
		String newModuleName = null;
		if (parentModule == null) {
			newModuleName = newElementName;
		} else {
			newModuleName = parentModule.getElementName() + "." + newElementName;
		}
		
		return root.findModule(newModuleName);
	}

}
