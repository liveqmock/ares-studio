/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.MoveProcessor;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy;

/**
 * 移动的Processor
 * @author sundl
 */
public class ARESMoveProcessor extends MoveProcessor {
	
	private IMovePolicy movePolicy;
	protected Object destination;
	protected ReorgQueries queries;
	protected List<IARESElement> elements;
	protected MoveArguments arguments;
	
	public ARESMoveProcessor (IARESElement[] elements) {
		setElements(elements);
		movePolicy = ReorgPolicyFactory.createMovePolicy(elements);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm,	CheckConditionsContext context) throws CoreException,
			OperationCanceledException {
		arguments = new MoveArguments(movePolicy.getDestination(), movePolicy.getUpdateReferences());
		return movePolicy.checkFinalConditions(pm, context, queries);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm)
			throws CoreException, OperationCanceledException {
		return new RefactoringStatus();
	}
	
	public RefactoringStatus verifyDestination(Object dest) {
		return movePolicy.verifyDestination(dest);
	}

	public ReorgQueries getQueries() {
		return queries;
	}

	public void setQueries(ReorgQueries queries) {
		this.queries = queries;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getElements()
	 */
	@Override
	public Object[] getElements() {
		return elements.toArray();
	}
	
	public void setElements(List<IARESElement> elements) {
		this.elements = elements;
	}
	
	public void setElements(IARESElement[] elements) {
		if (this.elements == null)
			this.elements = new ArrayList<IARESElement>();
		else {
			this.elements.clear();
		}
		this.elements.addAll(Arrays.asList(elements));
	}

	public List<IARESElement> getElementsToMove() {
		return elements;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	@Override
	public boolean isApplicable() throws CoreException {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	@Override
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		List<RefactoringParticipant> results = new ArrayList<RefactoringParticipant>();
		if (getDestination() != null && elements != null) {
			for (IARESElement element : elements) {
				 RefactoringParticipant[] participants = ParticipantManager.loadMoveParticipants(status, this, element, arguments, new String[] {ARESCore.NATURE_ID	}, sharedParticipants);
				 results.addAll(Arrays.asList(participants));
			}
		}
		return results.toArray(new RefactoringParticipant[0]);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.stuido.ui.refactor.processor.move";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "Move";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return movePolicy.createChange(pm);
	}

	protected Object getDestination() {
		return movePolicy.getAresElementDestination();
	}
	
	public void setDestination(Object destination) {
		movePolicy.setDestination(destination);
	}
	
	public void setUpdateReferences(boolean update) {
		movePolicy.setUpdateReferences(update);
	}
	
	public boolean getUpdateReferences() {
		return movePolicy.getUpdateReferences();
	}
	
}
