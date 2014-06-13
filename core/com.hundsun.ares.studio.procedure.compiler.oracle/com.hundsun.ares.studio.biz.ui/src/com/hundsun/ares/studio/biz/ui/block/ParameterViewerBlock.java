package com.hundsun.ares.studio.biz.ui.block;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.provider.ParameterColumnLabelProvider;
import com.hundsun.ares.studio.biz.ui.BizUIConstants;
import com.hundsun.ares.studio.biz.ui.StdObjContentPorposalHelper;
import com.hundsun.ares.studio.biz.ui.action.AddParameterAction;
import com.hundsun.ares.studio.biz.ui.action.AddParmaActionGroup;
import com.hundsun.ares.studio.biz.ui.action.AddToStdFieldAction;
import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.biz.ui.action.ParamLinkOpenObjectAction;
import com.hundsun.ares.studio.biz.ui.action.ParameterPasteAction;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.Language;
import com.hundsun.ares.studio.jres.metadata.ui.LanguageRegister;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelperWipeOffRepeatStd;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.ui.assist.CompositeProposalHelper;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveBottomAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveTopAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.EnumEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * 参数对象的Block
 * @author sundl
 *
 */
public abstract class ParameterViewerBlock extends TreeViewerBlock{

	/** 默认允许的添加Action的ID列表，比如添加标准字段参数，添加非标准字段参数，添加对象参数等... 
	 *  默认全部允许
	 */
	public static final String[] DEFAULT_ADD_ACTION_IDS = 
			new String[] {IBizActionIDConstants.CV_ADD,
						   IBizActionIDConstants.ADD_NON_STD_FIELD_PARME,
						   IBizActionIDConstants.ADD_OBJECT_PARAM,
						   IBizActionIDConstants.ADD_PARAM_GROUP
						   };
	
	// 标志位定义，用于确定是否要创建对应的列
	/** 启用“标志位”列 */
	public static final int COLUMN_FLAG = 1 << 1;
	/** 名字 */
	public static final int COLUMN_ID = 1 << 2;
	/** 中文名 */
	public static final int COLUMN_CHINESE_NAME = 1 << 3;
	/** 业务数据类型 */
	public static final int COLUMN_BIZ_TYPE = 1 << 4;
	/** 真实数据类型 */
	public static final int COLUMN_REAL_TYPE = 1 << 5;
	/** 数量 */
	public static final int COLUMN_MULTIPLICITY = 1 << 6;
	/** 默认值 */
	public static final int COLUMN_DEFAULT_VALUE = 1 << 7;
	/** 说明 */
	public static final int COLUMN_DESCRIPTION = 1 << 8;
	/** 备注 */
	public static final int COLUMN_COMMENTS = 1 << 9;
	
	/** 默认是全部列都启用 */
	public static final int DEFAULT_COLUMNS_STYLE = COLUMN_FLAG | COLUMN_ID | COLUMN_CHINESE_NAME | COLUMN_BIZ_TYPE
									| COLUMN_REAL_TYPE | COLUMN_MULTIPLICITY | COLUMN_DEFAULT_VALUE | COLUMN_DESCRIPTION | COLUMN_COMMENTS;
	
	// 编辑的Parameter对应于类的哪个属性； 
	// 比如如果是输入参数，则对应的是输入参数这个Reference；如果是对象属性，则是对象属性这个Reference.
	protected EReference reference;
	
	protected AddParameterAction addAction;
	protected AddParameterAction addObjParamAction;
	protected AddParameterAction addNonStdFieldParamAction;
	protected AddParameterAction addParameterGroupAction;
	protected String[] addActionIds = DEFAULT_ADD_ACTION_IDS;
	protected ColumnViewerInsertAction insertAction;
	protected ColumnViewerMoveUpAction moveUpAction;
	protected ColumnViewerMoveDownAction moveDownAction;
	protected ColumnViewerMoveTopAction moveTopAction;
	protected ColumnViewerMoveBottomAction moveBottomAction;
	protected ColumnViewerPasteAction pasteAction;
	
	private String dataType = MetadataServiceProvider.C_TYPE;
	
	private int columnsStyle = DEFAULT_COLUMNS_STYLE;
	
	/**
	 * 创建一个编辑Parameter列表的Block
	 * @param reference 		编辑的Parameter列表对应于哪个EMF属性的ERefence对象;
	 * @param editingDomain		EditingDomain
	 * @param resource			AresResource
	 * @param problemPool
	 */
	public ParameterViewerBlock(EReference reference, EditingDomain editingDomain, IARESResource resource, IProblemPool problemPool) {
		super();
		this.reference = reference;
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected Point getViewerPreferredSize() {
		return new Point(100, 200);
	}
	
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		// 首先检查项目属性中的设置，如果没有设置就采用默认值
		IARESProjectProperty projectPro;
		try {
			projectPro = resource.getARESProject().getProjectProperty();
			if (projectPro != null) {
				String langname = projectPro.getString(BizUIConstants.REAL_TYPE_TO_DISPLAY);
				Language lang = LanguageRegister.getInstance().getLanguageByName(langname);
				if (lang != null)
					return lang.getId();
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	/**
	 * 设置启用那些列的标志位，这个方法目前只能在创建界面之前调用。
	 * @param style
	 */
	public void setColumnsStyle(int style) {
		this.columnsStyle = style;
	}
	
	/**
	 * @param problemPool the problemPool to set
	 */
	public void setProblemPool(IProblemPool problemPool) {
		this.problemPool = problemPool;
	}

	/**
	 * @return the reference
	 */
	public EReference getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(EReference reference) {
		this.reference = reference;
	}
	
	/**
	 * 通过这个函数控制可以添加哪些类型的参数
	 * @param ids
	 */
	public void setAddActionIds(String[] ids) {
		this.addActionIds = ids;
	}

	@Override
	protected TreeViewer doCreateColumnViewer(Composite parent, FormToolkit toolkit) {
		final TreeViewer viewer = super.doCreateColumnViewer(parent, toolkit);
		if (resource  != null) {
			ParamLinkOpenObjectAction action = new ParamLinkOpenObjectAction(resource.getARESProject());
			ARESUIUtil.addLinkSupport(viewer.getTree(), action);
		}
		return viewer;
	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ParameterConentProvider(this.reference, this.resource.getARESProject());
	}

	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return BizPackage.Literals.PARAMETER__ID;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		IARESProject project = resource.getARESProject();
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(viewer);
		//  标志位, "参数名", "中文名", "类型", "java类型", "标准字段", "值", "描述" 
		// 80, 100, 100, 100, 100, 100, 100, 120
		if ((columnsStyle & COLUMN_FLAG) != 0) {
			createFlagsColumn(viewer, project, problemView);
		}
		
		// ID 名字
		if ((columnsStyle & COLUMN_ID) != 0) {
			createParamNameColumn(viewer, project, problemView);
		}
		
		// 中文名
		if ((columnsStyle & COLUMN_CHINESE_NAME) != 0) {
			createChineseNameColumn(viewer, project, problemView);
		}
		
		// 类型
		if ((columnsStyle & COLUMN_BIZ_TYPE) != 0) {
			createBizTypeColumn(viewer, project, problemView);
		}
		
		// "真实类型"
		if ((columnsStyle & COLUMN_REAL_TYPE) != 0) {
			createRealTypeColumn(viewer, project, problemView);
		}

		// 数量
		if ((columnsStyle & COLUMN_MULTIPLICITY) != 0) {
			createMultiplicityColumn(project, viewer, problemView);
		}

		// "默认值"
		if ((columnsStyle & COLUMN_DEFAULT_VALUE) != 0) {
			createColumnDefaultValue(project, viewer, exProblemView);
		}
		
		// "说明"
		if ((columnsStyle & COLUMN_DESCRIPTION) != 0) {
			createDescriptionColumn(viewer, project, problemView);
		}
		
		// "备注"
		if ((columnsStyle & COLUMN_COMMENTS) != 0) {
			createCommentColumn(viewer, project);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				viewer, resource, BizPackage.Literals.PARAMETER, exProblemView);
		
		if (this.problemPool != null) {
			this.problemPool.addView(problemView);
			this.problemPool.addView(exProblemView);
		}
	
	}

	/**
	 * 创建“备注”列的方法
	 * @param viewer
	 * @param project
	 */
	protected void createCommentColumn(TreeViewer viewer, IARESProject project) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__COMMENTS;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("备注");
		column.getColumn().setWidth(120);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute){

			@Override
			public String getToolTipText(Object element) {
				String text = super.getToolTipText(element);
				if(StringUtils.isBlank(text)){
					return getText(element);
				}
				return text;
			}
		
		};
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		TextEditingSupport es =  new LongTextEditingSupport(viewer, attribute);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * 创建“描述”列的方法
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createDescriptionColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__DESCRIPTION;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("描述");
		column.getColumn().setWidth(120);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute){
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
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		TextEditingSupport es = new TextEditingSupport(viewer, attribute);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * 创建“数据类型”，即真实类型列
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createRealTypeColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__REAL_TYPE;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("数据类型");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		ParameterColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
		provider.setDataType(getDataType());
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		TextEditingSupport es = new TextEditingSupport(viewer, attribute);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * 创建业务类型列
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createBizTypeColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__TYPE;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("业务类型");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		ParameDataTypeContentProposalProvider proposalProvider = new ParameDataTypeContentProposalProvider(resource.getARESProject());
		JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
				viewer,
				attribute, 
				proposalProvider);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * 创建“中文名”列
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createChineseNameColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__NAME;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("中文名");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute){
			@Override
			public String getText(Object element) {
				return super.getText(element);
			}
		};
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		TextEditingSupport es = new TextEditingSupport(viewer, attribute);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createParamNameColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__ID;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("参数名");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 设置编辑2支持
		MetadataContentProposalHelperWipeOffRepeatStd helper1 = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
		StdObjContentPorposalHelper heler2 = new StdObjContentPorposalHelper();
		CompositeProposalHelper helper = new CompositeProposalHelper(heler2, helper1);
		
		ParamIdContentProposalProvider proposalProvider = new ParamIdContentProposalProvider(helper, resource.getARESProject());
		
		// 3. 创建EditingSupport, 
		JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(viewer, attribute,proposalProvider);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}

	/**
	 * @param viewer
	 * @param project
	 * @param problemView
	 */
	protected void createFlagsColumn(TreeViewer viewer, IARESProject project, EObjectColumnViewerProblemView problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__FLAGS;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("标志位");
		column.getColumn().setWidth(70);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 3. 创建EditingSupport, 
		// 设置编辑支持
		TextEditingSupport es = new TextEditingSupport(viewer, attribute);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}
	
	/**
	 * 创建“数量关系”列
	 */
	protected void createMultiplicityColumn(IARESProject project, TreeViewer viewer, IDiagnosticProvider problemView) {
		// CRES 不显示数量
		if (!BizUtil.hasCRESNature(project.getProject())) {
			EAttribute attribute = BizPackage.Literals.PARAMETER__MULTIPLICITY;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("数量");
			column.getColumn().setWidth(50);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			EnumEditingSupport es = new EnumEditingSupport(viewer, attribute);
			es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
			column.setEditingSupport(es);
		}
	}
	
	/**
	 * 创建“默认值”列
	 */
	protected void createColumnDefaultValue(IARESProject project, TreeViewer viewer, IDiagnosticProvider problemView) {
		// 定义主属性
		EAttribute attribute = BizPackage.Literals.PARAMETER__DEFAULT_VALUE;
		
		// 创建表格列
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("默认值");
		column.getColumn().setWidth(100);
		column.getColumn().setMoveable(true);
		
		// 设置标签提供器
		EObjectColumnLabelProvider provider = new ParameterColumnLabelProvider(resource,attribute);
		provider.setDiagnosticProvider(problemView);
		column.setLabelProvider(provider);
		
		// 设置编辑支持
		MetadataContentProposalHelperWipeOffRepeatStd helper = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
		MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.DefValue, resource.getARESProject());
		
		JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
				viewer,
				attribute, 
				proposalProvider);
		es.setDecorator(new ParameterDefineEditingSupportDecorator(project, attribute));
		column.setEditingSupport(es);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {

		AddToStdFieldAction addStdFieldAction = 
			new AddToStdFieldAction(this.resource.getARESProject(), getColumnViewer(), editingDomain);	
		
		getActionRegistry().registerAction(addStdFieldAction);
		getSelectionActions().add(addStdFieldAction.getId());	
		
		addAction = new AddParameterAction(
				getColumnViewer(), 
				editingDomain,
				IActionIDConstant.CV_ADD,
				"增加标准字段参数",
				null,
				this.reference,
				getParameterEClass(),//BizPackage.Literals.PARAMETER,
				ParamType.STD_FIELD
				);
		
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		addObjParamAction = new AddParameterAction(
				getColumnViewer(), 
				this.editingDomain,
				IBizActionIDConstants.ADD_OBJECT_PARAM,
				"增加对象类型参数",
				null,
				this.reference,
				getParameterEClass(),//BizPackage.Literals.PARAMETER,
				ParamType.OBJECT
				);
		
		
		getActionRegistry().registerAction(addObjParamAction);
		getSelectionActions().add(addObjParamAction.getId());
		
		addParameterGroupAction = new AddParameterAction(
				getColumnViewer(), 
				this.editingDomain,
				IBizActionIDConstants.ADD_PARAM_GROUP,
				"增加参数组",
				null,
				this.reference,
				getParameterEClass(),//BizPackage.Literals.PARAMETER,
				ParamType.PARAM_GROUP
				);
		
		getActionRegistry().registerAction(addParameterGroupAction);
		getSelectionActions().add(addParameterGroupAction.getId());
		
		addNonStdFieldParamAction = new AddParameterAction(
				getColumnViewer(), 
				this.editingDomain,
				IBizActionIDConstants.ADD_NON_STD_FIELD_PARME,
				"增加非标准字段参数",
				null,
				this.reference,
				getParameterEClass(),//BizPackage.Literals.PARAMETER,
				ParamType.NON_STD_FIELD
				);
		
		getActionRegistry().registerAction(addNonStdFieldParamAction);
		getSelectionActions().add(addNonStdFieldParamAction.getId());
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), this.editingDomain);
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
		moveUpAction = new ColumnViewerMoveUpAction(getColumnViewer(), 
													this.editingDomain,
													null, 
													this.reference);
		getActionRegistry().registerAction(moveUpAction);
		getSelectionActions().add(moveUpAction.getId());
		getStackActions().add(moveUpAction.getId());
		
		moveTopAction = new ColumnViewerMoveTopAction(getColumnViewer(), 
				this.editingDomain,
				null, 
				this.reference);
		getActionRegistry().registerAction(moveTopAction);
		getSelectionActions().add(moveTopAction.getId());
		getStackActions().add(moveTopAction.getId());
		
		
		moveDownAction = new ColumnViewerMoveDownAction(getColumnViewer(), this.editingDomain,
				null, this.reference);
		getActionRegistry().registerAction(moveDownAction);
		getSelectionActions().add(moveDownAction.getId());
		getStackActions().add(moveDownAction.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				this.editingDomain,
				this.getReference(),
				getParameterEClass());
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		moveBottomAction = new ColumnViewerMoveBottomAction(getColumnViewer(), this.editingDomain,
				null, this.reference);
		getActionRegistry().registerAction(moveBottomAction);
		getSelectionActions().add(moveBottomAction.getId());
		getStackActions().add(moveBottomAction.getId());
		
		IAction copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		pasteAction =  createPasteAction();
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
		
		//只读控制
		getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(addNonStdFieldParamAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(addObjParamAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(addParameterGroupAction));

		getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveTopAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveUpAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveDownAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(insertAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveBottomAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(pasteAction));

	}
	
	protected ColumnViewerPasteAction createPasteAction() {
		return new ParameterPasteAction(getColumnViewer(), this.editingDomain, null, this.reference);
	}
	
	protected EClass getParameterEClass(){
		return BizPackage.Literals.PARAMETER;
	}
	
	@Override
	protected void createMenus(IMenuManager menuManager) {
		if (this.addActionIds != null) {
			for (String id : addActionIds) {
				IAction action = getActionRegistry().getAction(id);
				menuManager.add(action);
			}
		}
		
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);

		action = getActionRegistry().getAction(CopyCellAction.ID);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyColumnAction.ID);
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
		
		menuManager.add(new Separator());
		
		// FIXME 添加到标准字段功能移植暂时未完成
		//IAction addStdFieldAction = getActionRegistry().getAction(IBizActionIDConstants.ADD_TO_STD_FIELD);
		//menuManager.add(addStdFieldAction);
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		AddParmaActionGroup addParamActionGroup = new AddParmaActionGroup(getActionRegistry(), this.addActionIds);
		buttonManager.add(addParamActionGroup);
		
		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
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
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setDefaultOwner((EObject) input);
		addObjParamAction.setDefaultOwner((EObject) input);
		addParameterGroupAction.setDefaultOwner((EObject) input);
		addNonStdFieldParamAction.setDefaultOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		moveDownAction.setOwner((EObject) input);
		moveUpAction.setOwner((EObject) input);
		moveBottomAction.setOwner((EObject) input);
		moveTopAction.setOwner((EObject) input);
		pasteAction.setOwner((EObject) input);
		super.setInput(input);
	}
	
}
