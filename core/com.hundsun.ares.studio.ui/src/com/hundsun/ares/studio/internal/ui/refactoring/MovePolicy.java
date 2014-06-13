/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ChangeDescriptor;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy;
import com.hundsun.ares.studio.ui.refactoring.IReorgQueries;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

/**
 * 
 * @author sundl
 */
public abstract class MovePolicy extends ReorgPolicy implements IMovePolicy {

	public MovePolicy(IARESElement[] elements) {
		super(elements);
	}

	private boolean updateReferences = true;
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReferenceUpdating#getUpdateReferences()
	 */
	public boolean getUpdateReferences() {
		return updateReferences;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReferenceUpdating#setUpdateReferences(boolean)
	 */
	public void setUpdateReferences(boolean update) {
		this.updateReferences = update;
	}
	
	public RefactoringStatus checkFinalConditions(IProgressMonitor monitor,	CheckConditionsContext context, IReorgQueries queries) throws CoreException {
		confirmOverwriting(queries);
		return new RefactoringStatus();
	}
	
	private void confirmOverwriting(IReorgQueries reorgQueries) {
		OverwriteHelper helper= new OverwriteHelper();
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			helper.setAresResources(ARESElementUtil.toARESResource(elements));
			helper.confirmOverwriting(reorgQueries, destination);
			elements = helper.getAresResources();
		} else if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
			helper.setModules(ARESElementUtil.toARESModule(elements));
			helper.confirmOverwriting(reorgQueries, destination);
			elements = helper.getModules();
		}
	}

	
	public static class MoveAresResourcePolicy extends MovePolicy {
		
		public MoveAresResourcePolicy(IARESElement[] elements) {
			super(elements);
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#postCreateChange(org.eclipse.ltk.core.refactoring.Change[], org.eclipse.core.runtime.IProgressMonitor)
		 */
		public Change postCreateChange(Change[] participantChanges, IProgressMonitor monitor) throws CoreException {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#setDestinationCheck(boolean)
		 */
		public void setDestinationCheck(boolean check) {
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getPolicyId()
		 */
		public String getPolicyId() {
			return "com.hundsun.ares.studio.ui.moveresources";
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#verifyDestination(java.lang.Object)
		 */
		public RefactoringStatus verifyDestination(Object destination) {
			RefactoringStatus status = new RefactoringStatus();
			IARESElement aresElementDest = null;
			if (destination instanceof IARESElement) {
				aresElementDest = (IARESElement) destination;
			} 
			
			if (aresElementDest != null) {
				if (!RefactoringUtil.canMoveTo(elements, aresElementDest)) {
					status.addFatalError("无法移动到此位置");
				}
			} else {
				status.addFatalError("无法识别的目标");
			}
			
			return status;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getDescriptor()
		 */
		public ChangeDescriptor getDescriptor() {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext, com.hundsun.ares.studio.ui.refactoring.IReorgQueries)
		 */
		public RefactoringStatus checkFinalConditions(IProgressMonitor monitor,	CheckConditionsContext context, IReorgQueries queries) throws CoreException {
			RefactoringStatus status = new RefactoringStatus();
			status.merge(super.checkFinalConditions(monitor, context, queries));
			return status;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor, java.lang.String[], org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
		 */
		public RefactoringParticipant[] loadParticipants(
				RefactoringStatus status, RefactoringProcessor processor,
				String[] natures, SharableParticipants shared)
				throws CoreException {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#createChange(org.eclipse.core.runtime.IProgressMonitor)
		 */
		public Change createChange(IProgressMonitor monitor) {
			CompositeChange change = new CompositeChange("移动");
			for (IARESElement element : getAresElements()) {
				change.add(new MoveAresElementChange(element, getAresElementDestination()));
			}
			return change;
		}
		
	}

	public static class MoveAresModulePolicy extends MovePolicy {
		
		public MoveAresModulePolicy(IARESElement[] elements) {
			super(elements);
		}
		
		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#postCreateChange(org.eclipse.ltk.core.refactoring.Change[], org.eclipse.core.runtime.IProgressMonitor)
		 */
		public Change postCreateChange(Change[] participantChanges, IProgressMonitor monitor) throws CoreException {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#setDestinationCheck(boolean)
		 */
		public void setDestinationCheck(boolean check) {
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getPolicyId()
		 */
		public String getPolicyId() {
			return "com.hundsun.ares.studio.ui.movemodules";
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#verifyDestination(java.lang.Object)
		 */
		public RefactoringStatus verifyDestination(Object destination) {
			RefactoringStatus status = new RefactoringStatus();
			
			IARESElement aresElementDest = null;
			if (destination instanceof IARESElement) {
				aresElementDest = (IARESElement) destination;
			} 
			
			if (aresElementDest != null) {
				if (!RefactoringUtil.canMoveTo(elements, aresElementDest)) {
					status.addFatalError("无法移动到此位置");
				}
			} else {
				status.addFatalError("无法识别的目标");
			}
			
 			return status;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getDescriptor()
		 */
		public ChangeDescriptor getDescriptor() {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext, com.hundsun.ares.studio.ui.refactoring.IReorgQueries)
		 */
		public RefactoringStatus checkFinalConditions(IProgressMonitor monitor,	CheckConditionsContext context, IReorgQueries queries) throws CoreException {
			RefactoringStatus status = new RefactoringStatus();
			status.merge(super.checkFinalConditions(monitor, context, queries));
			return status;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor, java.lang.String[], org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
		 */
		public RefactoringParticipant[] loadParticipants(RefactoringStatus status, RefactoringProcessor processor,
				String[] natures, SharableParticipants shared)
				throws CoreException {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy#createChange(org.eclipse.core.runtime.IProgressMonitor)
		 */
		public Change createChange(IProgressMonitor monitor) {
			CompositeChange change = new CompositeChange("移动");
			for (IARESElement element : getAresElements()) {
				change.add(new MoveAresElementChange(element, getAresElementDestination()));
			}
			return change;
		}
	}
	
}
