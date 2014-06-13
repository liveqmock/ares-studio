package com.hundsun.ares.studio.biz.ui.action;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;

/**
 * "插入"方式添加参数的Aciton
 * @author gongyf
 * @author sundl
 */
public class InsertParameterAction extends ColumnViewerAction {

	private EObject defaultOwner;
	private EReference defaultReference;
	
	private EClass paramClass;
	
	public InsertParameterAction(ColumnViewer viewer, EditingDomain editingDomain, 
			EObject defaultOwner, EReference defaultReference, EClass paramClass) {
		
		super(viewer, editingDomain);
		
		setId(IActionIDConstant.CV_INSERT);
		setText("插入");
		setEnabled(false);
		
		this.paramClass = paramClass;
		this.defaultOwner = defaultOwner;
		this.defaultReference = defaultReference;
		
		setAccelerator(SWT.INSERT);
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
			EObject newObj = paramClass.getEPackage().getEFactoryInstance().create(paramClass);
			
			Object position = ((IStructuredSelection)getViewer().getSelection()).getFirstElement();
			
			Command command = null;
			// 有选中的情况在选中位置插入新对象
			if (position != null) {
				List<Object> list = (List<Object>) owner.eGet(reference);
				int index = list.indexOf(position);
				if (index >= 0) {
					command = AddCommand.create(getEditingDomain(), owner, reference, newObj, index);
				}
			}
			
			if (command == null) {
				command = AddCommand.create(getEditingDomain(), owner, reference, newObj);
			}
			
			return command;
		}
		return null;
	}

}
