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

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.util.ResourcesUtil;

/**
 * 重命名模块根
 * @author sundl
 */
public class RenameModuleRootProcessor extends ARESRenameProcessor {

	public RenameModuleRootProcessor(IARESElement element) {
		super(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#chechNewElementName(java.lang.String)
	 */
	public RefactoringStatus checkNewElementName(String name) {
		RefactoringStatus status = super.checkNewElementName(name);
		if (!status.hasFatalError()) {
			IARESModuleRoot root = (IARESModuleRoot) element;
			IARESProject project = root.getARESProject();
			if (ResourcesUtil.folderExists(project.getProject(), name)) {
				status.addFatalError("项目下已经存在此文件夹，无法重命名");
			}
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return new RenameModuleRootChange(element, element.getElementName(), getNewElementName());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.stuido.ui.refactor.processor.rename.moduleroot";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "重命名";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	@Override
	public boolean isApplicable() throws CoreException {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getNewElement()
	 */
	@Override
	public IARESElement getNewElement() {
		IARESModuleRoot root = (IARESModuleRoot) element;
		IARESProject project = root.getARESProject();
		try {
			return project.getModuleRoot(newElementName);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

}
