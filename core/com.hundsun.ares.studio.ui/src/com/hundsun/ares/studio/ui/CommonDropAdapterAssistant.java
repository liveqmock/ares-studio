package com.hundsun.ares.studio.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.ui.refactoring.CopyElementsResourceOperation;
import com.hundsun.ares.studio.internal.ui.refactoring.ReorgMoveStarter;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

public class CommonDropAdapterAssistant extends
		org.eclipse.ui.navigator.CommonDropAdapterAssistant {

	private static IARESElement[] NO_RESOURCES = new IARESElement[0];
	private static Logger logger = Logger.getLogger(CommonDropAdapterAssistant.class.getName());
	
	private IARESElement[] elements;	                      
	
	@Override
	public IStatus handleDrop(CommonDropAdapter dropAdapter, DropTargetEvent dropTargetEvent, Object target) {
		IARESElement realDest = getRealDestination(target);
		if (realDest == null)
			return Status.CANCEL_STATUS;
		
		if (LocalSelectionTransfer.getTransfer().isSupportedType(dropAdapter.getCurrentTransfer())) {
			switch(dropAdapter.getCurrentOperation()) {
			case DND.DROP_COPY:
				handleDropCopy(realDest);
				break;
			case DND.DROP_MOVE:
				handleDropMove(realDest);
				break;
			}
			
			clear();
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}
	
	private void handleDropCopy(IARESElement target) {
   		CopyElementsResourceOperation op = new CopyElementsResourceOperation(getShell());
		op.copyElements(elements, target, new NullProgressMonitor());
	}
	
	private void handleDropMove(Object target) {
		IARESElement[] element = getSelectedARESElements();
		if (element != null && element.length != 0) {
			ReorgMoveStarter starter = ReorgMoveStarter.create(element, target);
			starter.run(getShell());
		}
	}

	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {
		// drag within Eclipse?
		if (logger.isDebugEnabled())
			logger.debug("Validate drop on target: [" + target + "].  TransferData: [" + transferType.toString() + "]"
					/*+ "\n Transfer: ["*/ );
		
		if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {
			if (target == null) {
				return Status.CANCEL_STATUS;
			}
			
			ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
			resetElements();
			if (elements.length == ((IStructuredSelection)selection).size()) {
				if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
					if (target instanceof ARESResourceCategory | target instanceof IARESModule | target instanceof IARESResource) {
						switch (operation) {
						case DND.DROP_COPY:
							return Status.OK_STATUS;
						case DND.DROP_DEFAULT:
							return Status.CANCEL_STATUS;
						case DND.DROP_MOVE:
							IARESElement targetElement = null;
							if (target instanceof IARESElement) {
								targetElement = (IARESElement) target;
							} else if (target instanceof ARESResourceCategory) {
								targetElement = ((ARESResourceCategory) target).getModule();
							}
							if (RefactoringUtil.canMoveTo(elements, targetElement)) {
								return Status.OK_STATUS;
							}
							return Status.CANCEL_STATUS;
						}
					} 
					return Status.CANCEL_STATUS;
				} else if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
					if (target instanceof IARESElement) {
						if(RefactoringUtil.canMoveTo(elements, (IARESElement) target)) {
							return Status.OK_STATUS;
						} else {
							return Status.CANCEL_STATUS;
						}
					}
				} else {
					return Status.CANCEL_STATUS;
				}
			} else {
				return Status.CANCEL_STATUS;
			}
		}
		
		return Status.OK_STATUS;
	}
	
	private IARESElement getRealDestination(Object target) {
		IARESElement dest = null;
		if (target instanceof IARESModule) {
			dest = (IARESModule) target;
		} else if (target instanceof ARESResourceCategory) {
			dest = ((ARESResourceCategory)target).getModule();
		} else if (target instanceof IARESResource) {
			dest = ((IARESResource)target).getModule();
		}
		return dest;
	}
	
	private IContainer getDestination(Object target) {
		IContainer destination = null;
		
		if (target instanceof IARESModule) {
			destination = (IContainer)((IARESModule)target).getResource();
		} else if (target instanceof ARESResourceCategory) {
			destination = (IContainer)((ARESResourceCategory)target).getModule().getResource();
		} else if (target instanceof IARESResource) {
			destination = (IContainer)((IARESResource)target).getParent().getResource();
		}
		
		return destination;
	}
	
	private void resetElements() {
		elements = getSelectedARESElements();
	}
	
	private IARESElement[] getSelectedARESElements() {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (selection instanceof IStructuredSelection) {
			return getSelectedARESElement((IStructuredSelection) selection);
		}
		return NO_RESOURCES;
	}
	
	private IARESElement[] getSelectedARESElement(IStructuredSelection selection) {
		List<IARESElement> resources = new ArrayList<IARESElement>();
		for (Object obj : selection.toArray()) {
			if (obj instanceof IARESElement) {
				resources.add((IARESElement)obj);
			}
		}
		return resources.toArray(new IARESElement[resources.size()]);
	}
	
	private IResource[] getDropResources(IARESElement[] resoruces) {
		List<IResource> resourseList = new ArrayList<IResource>();
		if (resoruces != null) {
			for (IARESElement ares : resoruces) {
				resourseList.add(ares.getResource());
			}
		}
		return resourseList.toArray(new IResource[]{});
	}

	private void clear() {
		this.elements = null;
	}
	
}
