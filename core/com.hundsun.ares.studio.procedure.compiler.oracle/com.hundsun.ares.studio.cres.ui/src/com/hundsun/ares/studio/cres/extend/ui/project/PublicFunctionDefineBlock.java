/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.project;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.ui.edit.support.CresExtendEditingSupportDecorator;
import com.hundsun.ares.studio.cres.text.assistant.CustomFunctionFactory;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author wangxh
 *
 */
public class PublicFunctionDefineBlock extends TreeViewerBlock {

	protected EReference reference;
	protected IARESProject project;
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
	public PublicFunctionDefineBlock(
			EReference reference,
			EditingDomain editingDomain, IARESProject project,
			EClass eclass,
			IProblemPool problemPool) {
		super();
		this.reference = reference;
		this.editingDomain = editingDomain;
		this.project = project;
		this.eclass = eclass;
		this.problemPool = problemPool;
	}

	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(this.reference);
	}
	
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
		
		IAction action = new RefreshAction();
		getActionRegistry().registerAction(action);
	}

	@Override
	protected void createMenus(IMenuManager menuManager) {

		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(RefreshAction.ID);
		menuManager.add(action);
	}
	

	@Override
	protected void createToolbarItems(ToolBarManager manager) {

		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(RefreshAction.ID);
		manager.add(action);
		
	}
	
	@Override
	public void setInput(Object input) {
		if(input instanceof ARESProjectProperty) {
			ARESProjectProperty pp = (ARESProjectProperty)input;
			Object owner = pp.getMap().get(ICresExtendConstants.CRES_EXTEND_PROJECT_PROPERTY);
			addAction.setOwner((EObject)owner);
			super.setInput(owner);
		}
	}

	@Override
	protected void createColumns(TreeViewer viewer) {
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		{
			// 定义是否启用
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__IS_USED;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("是否启用");
			column.getColumn().setWidth(80);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 3. 创建EditingSupport, 
			// 设置编辑支持
			BooleanEditingSupport es = new BooleanEditingSupport(viewer, attribute);
			es.setDecorator(new CresExtendEditingSupportDecorator());
			column.setEditingSupport(es);
		}
		
		{
			// 定义主属性值
			EAttribute attribute = CresextendPackage.Literals.FILE_DEFINE__VALUE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("头文件路径");
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
		
		if (this.problemPool != null) {
			this.problemPool.addView(problemView);
//			getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		}
	}
	
	class RefreshAction extends Action{
		public static final String ID = "REFRESH_PUBLIC_FUNCTIONS";
		public RefreshAction(){
			setText("刷新公共函数");
			setImageDescriptor(ARESEditorPlugin.getImageDescriptor("full/obj16/refresh.gif"));//PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
			setId(ID);
		}
		
		@Override
		public void run() {
			CustomFunctionFactory.eINSTANCE.clear();
			CustomFunctionFactory.eINSTANCE.getAllPublicFunctions(project);
			CustomFunctionFactory.eINSTANCE.getAllStructMap(project);
		}
	}
}
