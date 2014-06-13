package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.ui.refactoring.IReorgQueries;

public interface IReorgPolicy extends IReorgDestinationValidator {

	/**
	 * @return the unique id of this policy
	 */
	public String getPolicyId();
	
	/**
	 * @return the source java elements to reorg
	 */
	public IARESElement[] getAresElements();

	/**
	 * @return true if this policy can handle the source elements
	 * @throws JavaModelException in unexpected cases
	 */
	public boolean canEnable();

	/**
	 * @return the save mode required for this reorg policy
	 *
	 * @see RefactoringSaveHelper
	 */
	public int getSaveMode();

	/**
	 * Can destination be a target for the given source elements?
	 *
	 * @param destination the destination to verify
	 * @return OK status if valid destination
	 * @throws JavaModelException in unexpected cases
	 */
	public RefactoringStatus verifyDestination(Object destination);

	/**
	 * @param destination the destination for this reorg
	 */
	public void setDestination(Object destination);
	
	public Object getDestination();

	/**
	 * @return the destination of this reorg or null if not a java element
	 */
	public IARESElement getAresElementDestination();

	/**
	 * @return a descriptor describing a reorg from source to target
	 */
	public ChangeDescriptor getDescriptor();

	public RefactoringStatus checkFinalConditions(IProgressMonitor monitor, CheckConditionsContext context, IReorgQueries queries) throws CoreException;

	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, RefactoringProcessor processor, String[] natures, SharableParticipants shared) throws CoreException;

//	public static interface ICopyPolicy extends IReorgPolicy{
//		public Change createChange(IProgressMonitor monitor, INewNameQueries queries);
//		public ReorgExecutionLog getReorgExecutionLog();
//	}

	public static interface IMovePolicy extends IReferenceUpdating, IReorgPolicy{
		public Change createChange(IProgressMonitor monitor);
		public Change postCreateChange(Change[] participantChanges, IProgressMonitor monitor) throws CoreException;
		public void setDestinationCheck(boolean check);
	}
	
}