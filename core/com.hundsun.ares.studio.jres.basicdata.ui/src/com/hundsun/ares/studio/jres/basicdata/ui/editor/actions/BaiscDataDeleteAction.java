package com.hundsun.ares.studio.jres.basicdata.ui.editor.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

public class BaiscDataDeleteAction extends CommandActionHandler implements
		IUpdateAction {
	ColumnViewer viewer;
	EObject owner;
	EStructuralFeature feature;
	public BaiscDataDeleteAction(ColumnViewer viewer,
			EditingDomain domain,
			EObject owner,
			EStructuralFeature feature
			) {
		super(domain, "删除");
		setId(IActionIDConstant.CV_DELETE);
		this.viewer = viewer;
		this.owner = owner;
		this.feature = feature;
		
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
	}

	public void setOwner(EObject owner) {
		this.owner = owner;
		this.command = null;  //从新设置owner时清除commmand
	}

	@Override
	public Command createCommand(Collection<?> selection) {
		return new RemoveCommand(domain, owner, feature, selection);
	}

	/**
	 * @since 2.1.0
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart)
					.getEditingDomain();
		}
	}

	@Override
	public void update() {
		if(!this.viewer.getSelection().isEmpty()){
			IStructuredSelection selection = (IStructuredSelection)this.viewer.getSelection();
			command = createCommand(selection.toList());
		}
		if(null != command && command.canExecute()){
			setEnabled(true);
		}else{
			setEnabled(false);
		}
	}
}