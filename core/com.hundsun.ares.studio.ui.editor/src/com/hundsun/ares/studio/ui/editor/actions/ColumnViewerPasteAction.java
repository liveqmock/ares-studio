/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;

/**
 * 
 * 需要加入selection Action列表
 * @author gongyf
 *
 */
public class ColumnViewerPasteAction extends ColumnViewerAction {

	protected EObject owner;
	protected EReference reference;
	
	/**
	 * @param viewer
	 * @param editingDomain
	 * @param owner
	 * @param reference
	 */
	public ColumnViewerPasteAction(ColumnViewer viewer,
			EditingDomain editingDomain, EObject owner, EReference reference) {
		super(viewer, editingDomain);
		this.owner = owner;
		this.reference = reference;
		
		setText("粘贴");
		setEnabled(false);
		
		setId(IActionIDConstant.CV_PASTE);
		
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
	}

	public void setOwner(EObject owner) {
		this.owner = owner;
		clearCommand();
	}
	
	public void setReference(EReference reference) {
		this.reference = reference;
		clearCommand();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		if (owner != null && reference != null) {
			IStructuredSelection selection = (IStructuredSelection)getViewer().getSelection();
			return new PasteCommand((TransactionalEditingDomain) getEditingDomain(), owner, reference, selection.getFirstElement());
		}
		return null;
	}

	static protected class PasteCommand extends RecordingCommand {

		protected EObject owner;
		protected TransactionalEditingDomain domain;
		protected EReference reference;
		protected EList<Object> ownerList;
		protected Object position;

		/**
		 * @param domain
		 * @param owner
		 * @param reference
		 */
		public PasteCommand(TransactionalEditingDomain domain, EObject owner,
				EReference reference, Object position) {
			super(domain);
			this.owner = owner;
			this.domain = domain;
			this.reference = reference;
			this.position = position;
			
			if (owner != null || reference != null) {
				ownerList = getOwnerList(owner, reference);
			}
			
		}


		static EList<Object> getOwnerList(EObject owner,
				EStructuralFeature feature) {
			return owner.eClass().getEAllStructuralFeatures().contains(feature)
					&& feature.isMany() ? (EList<Object>) owner.eGet(feature)
					: null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
		 */
		@Override
		public boolean canExecute() {
			if (ownerList == null) {
				return false;
			}
			if (domain.isReadOnly(owner.eResource())) {
				return false;
			}
			List<EObject> eObjects = getCopyedObjects();

			return !eObjects.isEmpty();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			List<EObject> eObjects = getCopyedObjects();
			
			if (position != null && ownerList.contains(position)) {
				ownerList.addAll(ownerList.indexOf(position)+1, eObjects);
			} else {
				ownerList.addAll(eObjects);
			}
			
		}
		
		protected List<EObject> getCopyedObjects() {
			return (List<EObject>) ARESEMFClipboard.getInstance().pasteFromClipboard(reference.getEReferenceType().getInstanceClass());
		}
		
	}
}
