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
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;

/**
 * 重命名ARES资源
 * @author sundl
 */
public class RenameARESResourceProcessor extends ARESRenameProcessor implements IReferenceUpdating{

	public RenameARESResourceProcessor(IARESResource element) {
		super(element);
		if (element != null) 
			this.newElementName = element.getName();
	}
	
	@Override
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm, CheckConditionsContext context) throws CoreException, OperationCanceledException {
		renameArguments = new RenameArguments(newElementName, getUpdateReferences());	
		return new RefactoringStatus();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return new RefactoringStatus();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		IARESResource resource = (IARESResource)element;
		String oldName = resource.getName();
		String newName = getNewElementName();
		return new ARESResourceRenameChange(resource, oldName, newName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#chechNewElementName(java.lang.String)
	 */
	public RefactoringStatus checkNewElementName(String name) {
		RefactoringStatus status = super.checkNewElementName(name);
		if (!status.hasFatalError()) {
			IARESResource res = (IARESResource) element;
			IARESModule module = res.getModule();
			IARESResource exists = module.findResource(name, res.getType());
			if (exists != null && exists.exists()) {
				status.merge(RefactoringStatus.createFatalErrorStatus("资源[" + exists.getName() + "]已经存在!"));
			}
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.stuido.ui.refactor.processor.rename.resource";
	}

	public String getCurrentElementName() {
		return ((IARESResource)element).getName();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "重命名资源";
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
		IARESResource res = (IARESResource) element;
		IARESModule module = res.getModule();
		return module.findResource(newElementName, res.getType());
	}

}
