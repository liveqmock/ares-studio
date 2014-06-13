package com.hundsun.ares.studio.atom.ui.editor.page;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.model.database.oracle.util.OracleDataBaseUtil;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;

public class AtomFunctionInterfacePage extends InterfacePage {

	private InternalVarParameterBlock varParamBlock;
	/**
	 * 功能号
	 */
	Text objectID;
	/**
	 * 版本号
	 */
	Text txtVersion;
	/**
	 * 更新时间
	 */
	Text txtUpdateTime;
	/**
	 * 名称
	 */
	Text txtName;
	/**
	 *中文名
	 */
	Text txtCName;
	/**
	 * 接口标志
	 */
	Text txtFlag;
	/**
	 * 描述
	 */
	Text txtDescription;
	
	/**
	 * 数据库
	 */
	Combo txtDatabase;
	
	/**
	 * 结果集返回
	 */
	Button btnResultSetReturn;

	private int[] weights = new int[]{15,15,15};
	
	public AtomFunctionInterfacePage(EStructuralFeature interfaceFeature,
			EMFFormEditor editor, String id, String title) {
		super(interfaceFeature, editor, id, title);
	}
	
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		GridLayoutFactory.swtDefaults().applyTo(composite);
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		createBasicInfoSection(composite,toolkit);
		
		final SashForm sf = new SashForm(composite, SWT.VERTICAL);
		GridLayoutFactory.swtDefaults().applyTo(sf);
		GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 650).applyTo(sf);
		
		final Section input = createInputSection(sf, toolkit);
		final Section output = createOuputSection(sf, toolkit);
		final Section internal = createInternalSection(sf,toolkit); 
		
		sf.setWeights(weights);
		final int unit = weights[0];
		
		input.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if(!internal.isExpanded() && !output.isExpanded()){
						//展开前输入输出内部都为不展开状态，此时内部占用的空间为展开时的大小
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]/unit});
					}else{
						//将输入占用大小置为展开状态
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]});
					}
				}else if(!input.isExpanded() && !output.isExpanded() && !internal.isExpanded()){
					//输入输出内部为不展开状态，将内部占用大小设置为展开时的大小
					sf.setWeights(new int[]{1,1,unit});
				}else{
					//将输入占用大小置为非展开状态
					sf.setWeights(new int[]{weights[0]/unit,weights[1],weights[2]});
				}
			}
		});
		
		output.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if(!input.isExpanded() && !internal.isExpanded()){
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]/unit});
					}else{
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]});
					}
				}else if(!input.isExpanded() && !output.isExpanded() && !internal.isExpanded()){
					sf.setWeights(new int[]{1,1,unit});
				}else{
					sf.setWeights(new int[]{weights[0],weights[1]/unit,weights[2]});
				}
			}
		});
		
		internal.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if(!input.isExpanded() && !output.isExpanded()){
						sf.setWeights(weights);
					}else{
						sf.setWeights(new int[]{weights[0],weights[1],weights[2]*unit});
					}
				}else if(!input.isExpanded() && !output.isExpanded() && !internal.isExpanded()){
					sf.setWeights(new int[]{1,1,unit});
				}else{
					sf.setWeights(new int[]{weights[0],weights[1],weights[2]/unit});
				}
			}
		});
		
		
		
		composite.getParent().layout();
	}
	
	@Override
	protected void customizeInputParamBlock() {
		inputParamBlock.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
				IBizActionIDConstants.ADD_OBJECT_PARAM, IBizActionIDConstants.ADD_PARAM_GROUP});
	}
	@Override
	protected void customizeOutputParamBlock() {
		outputParamBlock.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
				IBizActionIDConstants.ADD_OBJECT_PARAM, IBizActionIDConstants.ADD_PARAM_GROUP});
	}
	

	//基本信息section
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		
		Composite item = createGridComposite(client,toolkit);
		Label lbID = toolkit.createLabel(item, "功能号：");
		objectID = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		item = createGridComposite(client,toolkit);
		Label lbName = toolkit.createLabel(item, "名称：");
		txtName = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		item = createGridComposite(client,toolkit);
		Label lbCName = toolkit.createLabel(item, "中文名：");
		txtCName = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());

		item = createGridComposite(client,toolkit);
		Label lbVersion = toolkit.createLabel(item, "版本号：");
		txtVersion = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		item = createGridComposite(client,toolkit);
		Label lbUpdateTome = toolkit.createLabel(item, "更新时间：");
		txtUpdateTime = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		{
			//找出最新的版本号
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule(),IAtomResType.ATOM_FUNCTION);
			if (his != null) {
				txtVersion.setText(his.getVersion());
				txtUpdateTime.setText(his.getModifiedDate());
			}else {
				his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule());
				if (his != null) {
					txtVersion.setText(his.getVersion());
					txtUpdateTime.setText(his.getModifiedDate());
				}else {
					String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(getEditor().getARESResource().getARESProject());
					if (StringUtils.isBlank(projectVersion)) {
						projectVersion = "1.0.0.1";
					}
					txtVersion.setText(projectVersion);
				}
			}
		}
		
		item = createGridComposite(client,toolkit);
		Label lbDatabase = toolkit.createLabel(item, "数据库：");
		txtDatabase = new Combo(item, SWT.BORDER);
		{
			txtDatabase.setItems(OracleDataBaseUtil.getDataBaseName(getEditor().getARESResource().getARESProject(),true));
		}
		
		Composite flagItem = createGridComposite(client,toolkit);
		Label lbFlag = toolkit.createLabel(flagItem, "接口标志：");
		txtFlag = toolkit.createText(flagItem, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Composite descriptionItem = createGridComposite(client,toolkit);
		Label lbDescription = toolkit.createLabel(descriptionItem, "说明： ");
		txtDescription = toolkit.createText(descriptionItem, StringUtils.EMPTY, FormWidgetUtils.getDefaultMultiLinesTextStyles());
		
		item = createGridComposite(client,toolkit);
		Label lbResultSetReturn = toolkit.createLabel(item, "结果集返回：");
		btnResultSetReturn = toolkit.createButton(item, "", SWT.CHECK);
		
		// 只读控制
		txtName.setEditable(false);
		txtUpdateTime.setEditable(false);
		txtVersion.setEditable(false);
		txtName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtUpdateTime.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtVersion.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtCName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDescription));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(objectID));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtFlag));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDatabase));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(btnResultSetReturn));
		
		// 布局
		GridLayoutFactory.swtDefaults().equalWidth(true).numColumns(1).extendedMargins(0, 0, 0, 0).applyTo(section);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(section);
		GridLayoutFactory.swtDefaults().equalWidth(true).numColumns(2).extendedMargins(0, 0, 0, 0).applyTo(client);
		GridDataFactory.fillDefaults().grab(true, false).span(1, 1).applyTo(flagItem);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(descriptionItem);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbVersion);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbFlag);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbResultSetReturn);
		
		
		GridDataFactory.fillDefaults().grab(true, false).indent(11, 0).applyTo(objectID);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtVersion);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtFlag);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(btnResultSetReturn);
		
	   GridDataFactory.fillDefaults().grab(false, false).applyTo(lbName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbCName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbDescription);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbUpdateTome);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbDatabase);
		
	
		
		GridDataFactory.fillDefaults().grab(true, false).indent(12, 0).applyTo(txtName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).indent(11, 0).applyTo(txtCName);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtUpdateTime);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtDatabase);
		GridDataFactory.fillDefaults().grab(true, true).hint(10, 50).indent(18, 0).applyTo(txtDescription);
		
		section.setClient(client);
		return section;
	}
	/**
	 * 创建两列不相同的表格布局容器
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Composite createGridComposite(Composite parent,FormToolkit toolkit){
		Composite composite = toolkit.createComposite(parent);
		GridLayoutFactory.swtDefaults().numColumns(2).extendedMargins(0, 0, 0, 0).applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(composite);
		return composite;
	}
	//内部变量section
	protected Section createInternalSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("内部变量");
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite block = toolkit.createComposite(content);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());
		
		// 列表
		varParamBlock = new InternalVarParameterBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		varParamBlock.setEditableControl(getEditableControl());
		varParamBlock.setDataType(getDataType());
		//设置添加按钮
		varParamBlock.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
				   IBizActionIDConstants.ADD_NON_STD_FIELD_PARME});
		varParamBlock.createControl(block, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(varParamBlock);
		
		addPropertyListener(varParamBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(varParamBlock);
		
		section.setClient(content);
		return section;
	}
	
	@Override
	public void infoChange() {
		
		if ( varParamBlock != null ) {
			varParamBlock.setInput(getInfo());
		}
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(varParamBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(varParamBlock);
		
		super.dispose();
	}
	
	@Override
	protected void doDataBingingOnControls() {
		super.doDataBingingOnControls();
		bingText(objectID, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingText(txtCName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtFlag, getInfo(), BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG);
		bingText(txtDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingSelection(txtDatabase, getInfo(), AtomPackage.Literals.ATOM_FUNCTION__DATABASE);

		bingSelection(btnResultSetReturn, getInfo(), BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION);
	}
	

}
