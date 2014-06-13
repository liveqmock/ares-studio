/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.ui.editors.blocks;

import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.builder.IAresMarkers;
import com.hundsun.ares.studio.jres.database.ui.actions.AddNonStdFiledColumnAction;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnLabelProvider;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnRefLabelProvider;
import com.hundsun.ares.studio.jres.database.utils.DatabaseUtils;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelper;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelperWipeOffRepeatStd;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveBottomAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveTopAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author wangxh
 *
 */
public class TableColumnViewerBlock extends TreeViewerBlock {
	
	protected ColumnViewerAddAction addAction;
	protected AddNonStdFiledColumnAction addNonStdAction;
	protected ColumnViewerInsertAction insertAction;
	protected ColumnViewerMoveUpAction moveUpAction;
	protected ColumnViewerMoveDownAction moveDownAction;
	protected ColumnViewerMoveTopAction moveTopAction;
	protected ColumnViewerMoveBottomAction moveBottomAction;
	protected ColumnViewerPasteAction pasteAction;
	protected IAction delAction;
	protected IAction copyAction;
	
	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public TableColumnViewerBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider((EReference) getEReference());
	}

	/**
	 * 返回Column列表对应于主模型的哪个属性, 不能为null
	 * @return
	 */
	protected EReference getEReference() {
		return DatabasePackage.Literals.TABLE_RESOURCE_DATA__COLUMNS;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#getHeadColumnFeature()
	 */
	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		IARESResource res = getARESResource();
		if (res != null) {
			if (DatabaseUtil.isNonStdFiledAllowed(res.getARESProject())) {
				action = getActionRegistry().getAction(AddNonStdFiledColumnAction.ID);
				menuManager.add(action);
			}
		}
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyCellAction.ID);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_TOP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		menuManager.add(action);
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_BOTTOM);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
		menuManager.add(action);
		
	}

	@Override
	protected void configureColumnViewer(final TreeViewer viewer) {
		super.configureColumnViewer(viewer);
		viewer.getControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
				if (ss.getFirstElement() instanceof TableColumn) {
					if((e.stateMask & SWT.CTRL) != 0 && e.button == 1){
						TableColumn column = (TableColumn) ss.getFirstElement();
						ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, column.getFieldName(), true);
						Object infoObj = info.getObject();
						if (infoObj instanceof StandardField) {
							String dictType = ((StandardField) infoObj).getDictionaryType();
							if (StringUtils.isNotBlank(dictType)) {
								info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.Dict, dictType, true);
								Object dictInfo = info.getObject();
								if (dictInfo != null) {
									try {
										IARESResource dict = resource.getARESProject().findResource(IMetadataResType.Dict, IMetadataResType.Dict);
										IMarker marker = dict.getResource().createMarker(IAresMarkers.BOOK_MARKER_ID);
										marker.setAttribute(IMarker.LOCATION, ((MetadataItem)dictInfo).eResource().getURIFragment((MetadataItem)dictInfo));
										String editorId = IDE.getEditorDescriptor(dict.getElementName()).getId();
										marker.setAttribute(IDE.EDITOR_ID_ATTR, editorId);
										
										IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), marker);
										marker.delete();
									} catch (PartInitException e1) {
										e1.printStackTrace();
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		});
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		// 用于一般的列
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(treeViewer);
		
		// 标记
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(50);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 引用的标准字段
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
//			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			
			// 2. proposal provider
			MetadataContentProposalHelperWipeOffRepeatStd helper = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.StdField, resource.getARESProject()) {
				@Override
				protected boolean filter(Object inputItem, Object element) {
					if (element instanceof TableColumn) {
						TableColumn c = (TableColumn) element;
						// 非标准字段不提示
						if (c.getColumnType() == ColumnType.NON_STD_FIELD)
							return false;
					}
					return super.filter(inputItem, element);
				}
			};
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			tvColumn.setEditingSupport(es);
			
		}
		
		// 中文名
		{
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.ChineseName){
				@Override
				public Color getBackground(Object element) {
					if (getARESResource().isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			EAttribute attribute =  DatabasePackage.Literals.TABLE_COLUMN__CHINESE_NAME;
			TextEditingSupport es = new TextEditingSupport(treeViewer, attribute);
			es.setDecorator(new TableColumnEditingSupportDecorator(getARESResource().getARESProject(), attribute));
			tvColumn.setEditingSupport(es);
			tvColumn.getColumn().setMoveable(true);
		}
		// 字段类型
		{			
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE;
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段类型");
			
			TableColumnLabelProvider provider = new TableColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			// 1. 获取aresProject
			final IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();

			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(project);
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.BizType, project);

			// 3. 创建EditingSupport,
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(treeViewer, attribute, proposalProvider);
			es.setDecorator(new TableColumnEditingSupportDecorator(getARESResource().getARESProject(), attribute));
			tvColumn.setEditingSupport(es);
			tvColumn.getColumn().setMoveable(true);
		}

		// 字段说明
		{
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("字段说明");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.Desciption){
				@Override
				public String getToolTipText(Object element) {
					return getText(element);
				}
				@Override
				public Color getBackground(Object element) {
					if (getARESResource().isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			
			IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();
			EAttribute attribute =  DatabasePackage.Literals.TABLE_COLUMN__DESCRIPTION;
			TextEditingSupport es = new TextEditingSupport(treeViewer, attribute);
			es.setDecorator(new TableColumnEditingSupportDecorator(project, attribute));
			tvColumn.setEditingSupport(es);
			tvColumn.getColumn().setMoveable(true);
		}

		// 默认值
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__DEFAULT_VALUE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("默认值");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			MetadataContentProposalHelperWipeOffRepeatStd helper = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.DefValue, resource.getARESProject());
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new TableColumnEditingSupportDecorator(getARESResource().getARESProject(), attribute));
			tvColumn.setEditingSupport(es);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 是否为空
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__NULLABLE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("允许空");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , getARESResource()){
				@Override
				public Color getBackground(Object element) {
					TableColumn tc = (TableColumn) element;
					//if (tc.isPrimaryKey()) {
					if(DatabaseUtils.isPrimaryKey(tc)){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
				
				@Override
				public Image getImage(Object element) {
					TableColumn tc = (TableColumn) element;
//					if (tc.isPrimaryKey()) {
					if(DatabaseUtils.isPrimaryKey(tc)){
						return null;
					}
					return super.getImage(element);
				}
				
			};
			tvColumn.setLabelProvider(provider);
			
			BooleanEditingSupport support = new BooleanEditingSupport(treeViewer, attribute);
			support.setDecorator(new DatabaseDefineEditingSupportDecorator());
			tvColumn.setEditingSupport(support);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 备注
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__COMMENTS;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("备注");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource()){

				@Override
				public String getToolTipText(Object element) {
					String text = super.getToolTipText(element);
					if(StringUtils.isBlank(text)){
						return getText(element);
					}
					return text;
				}
			
			};
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new LongTextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, getARESResource(), DatabasePackage.Literals.TABLE_COLUMN, exProblemView);
		
		// 2012-05-15 sundl 在非编辑器中使用的时候，problemPool可能是null
		if (getProblemPool() != null) {
			getProblemPool().addView(problemView);
			getProblemPool().addView(exProblemView);
		}
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		
		createAddAction();
		createAddNonStdAction();
		createInsertAction();
		createDeleteAction();
		createMoveToTopAction();
		createMoveUpAction();
		createMoveDownAction();
		createMoveToBottomAction();
		createCopyAction();
		createPasteAction();
		createCellAction();
	}
	
	/**
	 * 创建默认的新建Action
	 */
	protected void createAddAction() {
		addAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				getEReference(),
				DatabasePackage.Literals.TABLE_COLUMN);
		addAction.setText("增加标准字段");
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
		}
	}
	
	/**
	 * 新建非标准字段的Action
	 */
	protected void createAddNonStdAction() {
		addNonStdAction = new AddNonStdFiledColumnAction(
								getColumnViewer(), 
								getEditingDomain(), 
								null,
								getEReference(),
								DatabasePackage.Literals.TABLE_COLUMN);
		getActionRegistry().registerAction(addNonStdAction);
		getSelectionActions().add(addNonStdAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(addNonStdAction));
		}
	}
	
	protected void createInsertAction() {
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				getEReference(),
				DatabasePackage.Literals.TABLE_COLUMN);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(insertAction));
		}
	}
	
	protected void createDeleteAction() {
		delAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
		}
	}
	
	protected void createMoveToTopAction() {
		moveTopAction = new ColumnViewerMoveTopAction(getColumnViewer(), getEditingDomain());
		moveTopAction.setReference(getEReference());
		getActionRegistry().registerAction(moveTopAction);
		getSelectionActions().add(moveTopAction.getId());
		getStackActions().add(moveTopAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveTopAction));
		}
	}
	
	protected void createMoveUpAction() {
		moveUpAction = new ColumnViewerMoveUpAction(getColumnViewer(), getEditingDomain());
		moveUpAction.setReference(getEReference());
		getActionRegistry().registerAction(moveUpAction);
		getSelectionActions().add(moveUpAction.getId());
		getStackActions().add(moveUpAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveUpAction));
		}
	}
	
	protected void createMoveDownAction() {
		moveDownAction = new ColumnViewerMoveDownAction(getColumnViewer(), getEditingDomain());
		moveDownAction.setReference(getEReference());
		getActionRegistry().registerAction(moveDownAction);
		getSelectionActions().add(moveDownAction.getId());
		getStackActions().add(moveDownAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveDownAction));
		}
	}
	
	protected void createMoveToBottomAction() {
		moveBottomAction = new ColumnViewerMoveBottomAction(getColumnViewer(), getEditingDomain());
		moveBottomAction.setReference(getEReference());
		getActionRegistry().registerAction(moveBottomAction);
		getSelectionActions().add(moveBottomAction.getId());
		getStackActions().add(moveBottomAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveBottomAction));
		}
	}
	
	protected void createCopyAction() {
		copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
	}
	
	protected void createPasteAction() {
		pasteAction =  new ColumnViewerPasteAction(getColumnViewer(), getEditingDomain(), null, null);
		pasteAction.setReference(getEReference());
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(pasteAction));
		}
	}
	
	/**
	 * 复制文本Acation
	 */
	private void createCellAction(){
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		addNonStdAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		moveDownAction.setOwner((EObject) input);
		moveUpAction.setOwner((EObject) input);
		moveTopAction.setOwner((EObject) input);
		moveBottomAction.setOwner((EObject) input);
		pasteAction.setOwner((EObject) input);
		super.setInput(input);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		buttonManager.add(action);
		
		if (DatabaseUtil.isNonStdFiledAllowed(getARESResource().getARESProject())) {
			if (addNonStdAction != null)
				buttonManager.add(addNonStdAction);
		}
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
		if(null != action)
			buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_TOP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_BOTTOM);
		buttonManager.add(action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		RefreshViewerJob.refresh(getColumnViewer(), null, false);
		Command cmd = ((CommandStack)event.getSource()).getMostRecentCommand();
		if (cmd != null) {
			Collection result = cmd.getResult();
			if(result != null){
				Set<TableColumn> affectObjs = new HashSet<TableColumn>();
				for(Object obj : result){
					if(obj instanceof TableKey){
						affectObjs.addAll(((TableKey)obj).getColumns());
					}else if(obj instanceof TableColumn){
						affectObjs.add((TableColumn) obj);
					}
				}
				if(affectObjs.size()>0){
					RefreshViewerJob.refresh(getColumnViewer(), affectObjs.toArray());
				}
			}
			RefreshViewerJob.refresh(getColumnViewer(), cmd.getAffectedObjects().toArray());
		}
		super.commandStackChanged(event);
	}
}
