/**
 * 源程序名称：ColumnViewerAddAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ColumnViewerAddAction extends ColumnViewerAction {

	/**
	 * 可以用来对新建的对象进行初始化
	 * @author sundl
	 */
	public static interface NewObjectInitializer {
		void initialize(EObject newObject);
	}
	
	private EObject owner;
	private EReference reference;
	private EClass eClass;
	private NewObjectInitializer initializer;
	
	public ColumnViewerAddAction(ColumnViewer viewer, EditingDomain editingDomain, EClass clazz) {
		this(viewer, editingDomain, null, null, clazz);
	}

	public ColumnViewerAddAction(ColumnViewer viewer, EditingDomain editingDomain, EObject owner, EReference reference, EClass clazz) {
		super(viewer, editingDomain);
		eClass = clazz;
		
		setText("增加");
		setEnabled(false);
		
		this.owner = owner;
		this.reference = reference;
		
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		setId(IActionIDConstant.CV_ADD);
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(EObject owner) {
		this.owner = owner;
		clearCommand();
	}
	
	/**
	 * @return the owner
	 */
	public EObject getOwner() {
		return owner;
	}
	
	/**
	 * @param reference the reference to set
	 */
	public void setReference(EReference reference) {
		this.reference = reference;
		clearCommand();
	}
	
	/**
	 * @return the reference
	 */
	public EReference getReference() {
		return reference;
	}
	
	public NewObjectInitializer getInitializer() {
		return initializer;
	}

	public void setInitializer(NewObjectInitializer initializer) {
		this.initializer = initializer;
	}

	public EClass getEMFClass() {
		return eClass;
	}
	
	@Override
	protected Command createCommand() {
		if (getOwner() != null && getReference() != null) {
			EObject newObj = eClass.getEPackage().getEFactoryInstance().create(eClass);
			if (initializer != null) {
				initializer.initialize(newObj);
			}
			Command command = AddCommand.create(getEditingDomain(), getOwner(), getReference(), newObj);
			return command;
		}
		return null;
	}

}
