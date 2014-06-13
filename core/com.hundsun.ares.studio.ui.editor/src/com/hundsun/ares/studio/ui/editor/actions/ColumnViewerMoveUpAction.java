/**
 * 源程序名称：ColumnViewerMoveUpAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

public class ColumnViewerMoveUpAction extends ColumnViewerMoveAction {

	private EObject owner;
	private EReference reference;
	
	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public ColumnViewerMoveUpAction(ColumnViewer viewer,
			EditingDomain editingDomain) {
		this(viewer, editingDomain, null, null);
	}
	
	public ColumnViewerMoveUpAction(ColumnViewer viewer,
			EditingDomain editingDomain, EObject owner, EReference reference) {
		super(viewer, editingDomain, true);
		setText("上移");
		
		this.owner = owner;
		this.reference = reference;
		
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESEditorPlugin.PLUGIN_ID, "icons/full/obj16/up.gif"));
		setId(IActionIDConstant.CV_MOVE_UP);
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
