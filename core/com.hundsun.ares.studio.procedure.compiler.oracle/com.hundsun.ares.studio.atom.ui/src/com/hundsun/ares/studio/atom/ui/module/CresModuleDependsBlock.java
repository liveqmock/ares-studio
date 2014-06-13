/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.module;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.ui.edit.support.CresExtendEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * 模块依赖区域块
 * @author qinyuan
 *
 */
public class CresModuleDependsBlock extends TreeViewerBlock {
	
	// 编辑的Parameter对应于类的哪个属性； 
	// 比如如果是输入参数，则对应的是输入参数这个Reference；如果是对象属性，则是对象属性这个Reference.
	protected EReference reference;
	private Composite composite;
	private ColumnViewerAddAction addAction;
	
	/**
	 * @param composite 
	 * 
	 */
	public CresModuleDependsBlock(Composite composite, EReference reference, EditingDomain editingDomain, IARESResource resource, IProblemPool problemPool) {
		super();
		this.composite = composite;
		this.reference = reference;
		this.editingDomain = editingDomain;
		this.resource = resource;
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
		
		addAction = new ColumnViewerAddMoudleDependAction(resource, composite, 
				getColumnViewer(), 
				editingDomain,
				null,
				reference,
				CresextendPackage.Literals.MOUDLE_DEPEND);
		
//		addAction = new ColumnViewerAddAction(
//				getColumnViewer(), 
//				editingDomain,
//				null,
//				reference,
//				CresmoudlePackage.Literals.DEPEND);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), editingDomain);
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
		//只读控制
//		getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		if(input instanceof ModuleProperty) {
			ModuleProperty mp = (ModuleProperty)input;
			Object owner = mp.getMap().get(ICresExtendConstants.CRES_EXTEND_MOUDLE_PROPERTY);
			addAction.setOwner((EObject)owner);
			super.setInput(owner);
		}
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
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(viewer);
		{
			// 定义主属性
			EAttribute attribute = CresextendPackage.Literals.MOUDLE_DEPEND__MODULE_PATH;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("模块路径");
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
		
		{
			// 定义主属性
			EAttribute attribute = CresextendPackage.Literals.MOUDLE_DEPEND__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("模块名称");
			column.getColumn().setWidth(100);
			
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
				viewer, resource, CresextendPackage.Literals.MOUDLE_DEPEND, exProblemView);
		
		if (this.problemPool != null) {
			this.problemPool.addView(problemView);
			this.problemPool.addView(exProblemView);
//			getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		}
	}
}
