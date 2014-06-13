/**
 * 源程序名称：DefaultColumnViewerActionGroup.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ColumnViewer;

/**
 * 表格添加、删除、上移、下移按钮
 * @author qinyuan
 *
 */
public class DefaultColumnViewerActionGroup {
	
	private ColumnViewer viewer;
	private EditingDomain editingDomain;
	private EObject model;
	private EReference reference;
	private EClass eclass;
	private ButtonGroupManager btnGroupManager;
	/**
	 * 
	 */
	public DefaultColumnViewerActionGroup(ColumnViewer viewer, EditingDomain editingDomain,
			EObject model, EReference reference, EClass eclass,ButtonGroupManager btnGroupManager) {
		this.viewer = viewer;
		this.editingDomain = editingDomain;
		this.model = model;
		this.reference = reference;
		this.eclass = eclass;
		this.btnGroupManager = btnGroupManager;
	}
	
	/**
	 * 创建添加、删除、上移、下移操作
	 * @param parent
	 */
	public void createDefaultButton() {
		// 创建按钮列表
		IAction action = new ColumnViewerAddAction(viewer, 
				editingDomain,eclass);
		((ColumnViewerAddAction) action).setOwner(model);
		((ColumnViewerAddAction) action).setReference(reference);
		
		action.setText("增加");
		btnGroupManager.add(action);
		
		action = new ColumnViewerDeleteAction(viewer, 
				editingDomain);
		action.setText("删除");
		btnGroupManager.add(action);
		
		action = new ColumnViewerMoveUpAction(viewer, editingDomain);
		((ColumnViewerMoveUpAction) action).setOwner(model);
		((ColumnViewerMoveUpAction) action).setReference(reference);
		action.setText("上移");
		btnGroupManager.add(action);
		
		action = new ColumnViewerMoveDownAction(viewer, editingDomain);
		((ColumnViewerMoveDownAction) action).setOwner(model);
		((ColumnViewerMoveDownAction) action).setReference(reference);
		action.setText("下移");
		btnGroupManager.add(action);
		
	}
	
	/**
	 * 
	 */
//	public void dispose() {
//		btnGroupManager.dispose();
//		btnGroupManager = null;
//	}
//	
//	public Composite getControl() {
//		if(null != btnGroupManager) {
//			return btnGroupManager.getControl();
//		}
//		return null;
//	}
}
