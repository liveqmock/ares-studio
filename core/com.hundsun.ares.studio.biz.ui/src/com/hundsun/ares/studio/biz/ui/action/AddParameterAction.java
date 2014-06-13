package com.hundsun.ares.studio.biz.ui.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * 添加参数的Action
 * @author sundl
 *
 */
public class AddParameterAction extends ColumnViewerAction {

	private EObject defaultOwner;
	private EReference defaultReference;
	
	private ParamType paramType;
	private EClass paramClass;
	
	public AddParameterAction(ColumnViewer viewer, EditingDomain editingDomain, String id, String label, 
			EObject defaultOwner, EReference defaultReference, EClass paramClass, ParamType paramType) {
		super(viewer, editingDomain);
		
		this.paramType = paramType;
		this.paramClass = paramClass;
		this.defaultOwner = defaultOwner;
		this.defaultReference = defaultReference;
		
		setText(label);
		setToolTipText(label);
		setId(id);
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
	}
	
	/**
	 * @param defaultOwner the defaultOwner to set
	 */
	public void setDefaultOwner(EObject defaultOwner) {
		this.defaultOwner = defaultOwner;
	}
	
	/**
	 * @param defaultReference the defaultReference to set
	 */
	public void setDefaultReference(EReference defaultReference) {
		this.defaultReference = defaultReference;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		EObject owner = null;
		EReference reference = null;
		
		Object sel = ((IStructuredSelection)getViewer().getSelection()).getFirstElement();
		if (sel instanceof Parameter) {
			Parameter p = (Parameter) sel;
			owner = p.eContainer();
			reference = (EReference) p.eContainingFeature();
		}
		
		if (owner == null) {
			owner = defaultOwner;
		}
		
		if (reference == null) {
			reference = defaultReference;
		}
		
		if (owner != null && reference != null) {
			Parameter newObj = (Parameter) paramClass.getEPackage().getEFactoryInstance().create(paramClass);
			newObj.setParamType(paramType);
			return AddCommand.create(getEditingDomain(), owner, reference, newObj);
		}
		
		return null;
	}

}
