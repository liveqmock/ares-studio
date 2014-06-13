/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.project;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;
import com.hundsun.ares.studio.cres.extend.ui.edit.support.CresExtendEditingSupportDecorator;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author qinyuan
 *
 */
public class CresProjectFileDefineBlock extends TreeViewerBlock {

	protected EReference reference;
	protected IARESResource resource;
	protected EditingDomain editingDomain;
	protected IProblemPool problemPool;
	protected ColumnViewerAddAction addAction;
	protected EClass eclass;

	/**
	 * @param cresProjectExtendPropertyProcDefine
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public CresProjectFileDefineBlock(
			EReference reference,
			EditingDomain editingDomain, IARESResource resource,
			EClass eclass,
			IProblemPool problemPool) {
		super();
		this.reference = reference;
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.eclass = eclass;
		this.problemPool = problemPool;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(this.reference);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		
		addAction = new ColumnViewerAddAction(getColumnViewer(), editingDomain,
				null, reference, eclass);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());

		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(),
				editingDomain);
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {

		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
	}
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#createToolbarItems(org.eclipse.jface.action.ToolBarManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {

		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		if(input instanceof ARESProjectProperty) {
			ARESProjectProperty pp = (ARESProjectProperty)input;
			Object owner = pp.getMap().get(ICresExtendConstants.CRES_EXTEND_PROJECT_PROPERTY);
			addAction.setOwner((EObject)owner);
			super.setInput(owner);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(viewer);
		{
			// 定义是否启用
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__IS_USED;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("启用");
			column.getColumn().setWidth(80);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute){
				@Override
				public String getText(Object element) {
					if(element instanceof FileDefine){
						FileDefine define = (FileDefine)element;
						if(define.isIsUsed()){
							return "是";
						}else {
							return "否";
						}
					}
					return super.getText(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 3. 创建EditingSupport, 
			// 设置编辑支持
			BooleanEditingSupport es = new BooleanEditingSupport(viewer, attribute);
			es.setDecorator(new CresExtendEditingSupportDecorator());
			column.setEditingSupport(es);
		}
		
		{
			// 定义主属性
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__PARAMETER;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("参数");
			column.getColumn().setWidth(120);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 3. 创建EditingSupport, 
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new CresExtendEditingSupportDecorator());
			column.setEditingSupport(es);
		}
		
		{
			// 定义主属性值
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__VALUE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("值");
			column.getColumn().setWidth(250);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 3. 创建EditingSupport, 
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new CresExtendEditingSupportDecorator());
			column.setEditingSupport(es);
		}
		
		{
			// 定义主属性说明
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__NOTE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(350);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 3. 创建EditingSupport, 
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new CresExtendEditingSupportDecorator());
			column.setEditingSupport(es);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				viewer, resource, eclass, exProblemView);
		
		if (this.problemPool != null) {
			this.problemPool.addView(problemView);
			this.problemPool.addView(exProblemView);
//			getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		}
	}
}
