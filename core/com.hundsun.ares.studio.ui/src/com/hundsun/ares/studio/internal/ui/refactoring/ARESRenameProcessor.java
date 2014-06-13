/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;

public abstract class ARESRenameProcessor extends RenameProcessor implements INameUpdating, IReferenceUpdating{

	protected String newElementName;
	protected IARESElement element;
	protected RenameArguments renameArguments;

	private boolean updateReference = true;
	
	public ARESRenameProcessor(IARESElement element) {
		this.element = element;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm, CheckConditionsContext context) throws CoreException, OperationCanceledException {
		renameArguments = new RenameArguments(newElementName, true);		
		return new RefactoringStatus();
	}

	@Override
	public Object[] getElements() {
		return new Object[] {element};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	@Override
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		if (element != null) {
			IResource resource = element.getResource();
			String[] natures = resource.getProject().getDescription().getNatureIds();
			return ParticipantManager.loadRenameParticipants(status, this, element, renameArguments, natures, sharedParticipants);
		}
	
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getCurrentElementName()
	 */
	public String getCurrentElementName() {
		return element.getElementName();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#chechNewElementName(java.lang.String)
	 */
	public RefactoringStatus checkNewElementName(String name) {
		RefactoringStatus status = new RefactoringStatus();
		boolean match = IARESResource.RES_NAME_PATTERN.matcher(name).matches();
		if (!match) 
			status.addFatalError("名字不合法(允许字符为字母,数字或下划线,必须以字母或下划线开头,长度不超过50)!");
		return status;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getNewElementName()
	 */
	public String getNewElementName() {
		return newElementName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#setNewElementName(java.lang.String)
	 */
	public void setNewElementName(String name) {
		this.newElementName = name;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReferenceUpdating#getUpdateReferences()
	 */
	public boolean getUpdateReferences() {
		return updateReference;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReferenceUpdating#setUpdateReferences(boolean)
	 */
	public void setUpdateReferences(boolean update) {
		this.updateReference = update;
	}
}
