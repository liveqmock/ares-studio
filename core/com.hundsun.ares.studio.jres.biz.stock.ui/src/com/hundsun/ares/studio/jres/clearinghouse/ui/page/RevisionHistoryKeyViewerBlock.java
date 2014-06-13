package com.hundsun.ares.studio.jres.clearinghouse.ui.page;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.blocks.TableKeyViewerBlock;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;

public class RevisionHistoryKeyViewerBlock extends TableKeyViewerBlock {

	private Modification action;
	
	public RevisionHistoryKeyViewerBlock(EditingDomain editingDomain, IARESResource resource ,Modification action) {
		super(editingDomain, resource, null);
		this.action = action;
	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS);
	}
	
	@Override
	protected EMFEditingSupport getKeyColEditingSupper(TreeViewer treeViewer,
			EStructuralFeature feature) {
		return new KeyColumnModificationEditingSupport(treeViewer,feature,resource ,action);
	}
	
	protected void createActions() {
		IAction addAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS,
				DatabasePackage.Literals.TABLE_KEY);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
		IAction moveUpAction = new ColumnViewerMoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveUpAction);
		getSelectionActions().add(moveUpAction.getId());
		getStackActions().add(moveUpAction.getId());
		
		IAction moveDownAction = new ColumnViewerMoveDownAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveDownAction);
		getSelectionActions().add(moveDownAction.getId());
		getStackActions().add(moveDownAction.getId());
		
		IAction copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction pasteAction =  new ColumnViewerPasteAction(getColumnViewer(), getEditingDomain(), null, null);
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
	}
	
	@Override
	public void setInput(Object input) {
		ColumnViewerAddAction addAction = (ColumnViewerAddAction) getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		addAction.setOwner((EObject) input);
		ColumnViewerMoveDownAction moveDownAction = (ColumnViewerMoveDownAction) getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		moveDownAction.setOwner((EObject) input);
		moveDownAction.setReference(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS);
		ColumnViewerMoveUpAction moveUpAction = (ColumnViewerMoveUpAction) getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		moveUpAction.setOwner((EObject) input);
		moveUpAction.setReference(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS);
		ColumnViewerPasteAction pasteAction = (ColumnViewerPasteAction) getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(ChousePackage.Literals.ADD_TABLE_MODIFICATION__KEYS);

		if (getColumnViewer() != null) {
			getColumnViewer().setInput(input);
			
			updateActions(getPropertyActions());
			updateActions(getSelectionActions());
			updateActions(getStackActions());
		}
	}
}
