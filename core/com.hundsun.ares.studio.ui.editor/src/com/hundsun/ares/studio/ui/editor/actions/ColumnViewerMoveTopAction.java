/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 移最上
 * @author liaogc
 */
public class ColumnViewerMoveTopAction extends ColumnViewerMoveBuestAction{


	private EObject owner;
	private EReference reference;
	
	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public ColumnViewerMoveTopAction(ColumnViewer viewer,
			EditingDomain editingDomain) {
		this(viewer, editingDomain, null, null);
	}
	
	public ColumnViewerMoveTopAction(ColumnViewer viewer,
			EditingDomain editingDomain, EObject owner, EReference reference) {
		super(viewer, editingDomain, true);
		setText("移至最上");
		
		this.owner = owner;
		this.reference = reference;
		
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESEditorPlugin.PLUGIN_ID, "icons/full/obj16/top.gif"));
		setId(IActionIDConstant.CV_MOVE_TOP);
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(EObject owner) {
		this.owner = owner;
		clearCommand();
	}
	
	/**
	 * @param reference the reference to set
	 */
	public void setReference(EReference reference) {
		this.reference = reference;
		clearCommand();
	}
	
	/**
	 * @return the owner
	 */
	public EObject getOwner() {
		return owner;
	}
	
	/**
	 * @return the reference
	 */
	public EReference getReference() {
		return reference;
	}




}
