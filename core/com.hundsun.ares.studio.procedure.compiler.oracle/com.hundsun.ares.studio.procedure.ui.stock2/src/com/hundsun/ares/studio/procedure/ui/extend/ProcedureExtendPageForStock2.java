package com.hundsun.ares.studio.procedure.ui.extend;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

public class ProcedureExtendPageForStock2 extends EMFExtendSectionScrolledFormPage<Procedure> {

	public static final String key = "procedureExtendPage";
	
	private int[] weights = new int[]{15,15};

	private Text returnType;
	
	private Text createDate;
	
	private Combo bizType;

	private Combo DefineType;

	private Text beginCode;

	private Text endCode;
	
	//自治事务
	private Button autoTransction;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ProcedureExtendPageForStock2(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	public boolean shouldLoad() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return ProcdurePackage.Literals.PROCEDURE;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(com.hundsun.ares.studio.ui.page.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		createBasicInfoSection(composite,toolkit);
		
		final SashForm sf = new SashForm(composite, SWT.VERTICAL);
		TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB);
		twd.heightHint = 650;
		sf.setLayoutData(twd);
		sf.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		final Section before = createBeforeCodeSection(sf, toolkit);
		final Section after = createAfterCodeSection(sf, toolkit);
		
		//databinding
		databinding();
		
		sf.setWeights(weights);
		final int unit = weights[0];
		
		before.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if( !after.isExpanded()){
						//展开前输入输出内部都为不展开状态，此时内部占用的空间为展开时的大小
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]/unit});
					}else{
						//将输入占用大小置为展开状态
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]});
					}
				}else if(!before.isExpanded() && !after.isExpanded() ){
					//输入输出内部为不展开状态，将内部占用大小设置为展开时的大小
					sf.setWeights(new int[]{1,1,unit});
				}else{
					//将输入占用大小置为非展开状态
					sf.setWeights(new int[]{weights[0]/unit,weights[1],weights[2]});
				}
			}
		});
		
		after.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if(!before.isExpanded() ){
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]/unit});
					}else{
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]});
					}
				}else if(!before.isExpanded() && !after.isExpanded() ){
					sf.setWeights(new int[]{1,1,unit});
				}else{
					sf.setWeights(new int[]{weights[0],weights[1]/unit,weights[2]});
				}
			}
		});
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		composite.getParent().layout();
	}

	//基本信息section
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "返回类型：");
		returnType = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		//创建日期
		Label lbCreateDate = toolkit.createLabel(client, "创建日期：");
		createDate = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.COLOR_GRAY);
		
		//业务类型
		Label lbBizType = toolkit.createLabel(client, "业务类型：");
		bizType = new Combo(client, SWT.BORDER);
		
		//获取业务类型
		EList<EEnumLiteral> bizTypes = ProcdurePackage.Literals.BIZ_TYPE.getELiterals();
		List<String> bizItems = new ArrayList<String>();
		for (EEnumLiteral eEnumLiteral : bizTypes) {
			bizItems.add(eEnumLiteral.getName());
		}
		bizType.setItems(bizItems.toArray(new String[bizItems.size()]));
		
		//定义类型
		Label lbDefineType = toolkit.createLabel(client, "定义类型：");
		DefineType = new Combo(client, SWT.BORDER);
		
		//获取定义类型
		EList<EEnumLiteral> defineTypes = ProcdurePackage.Literals.DEFINE_TYPE.getELiterals();
		List<String> items = new ArrayList<String>();
		for (EEnumLiteral eEnumLiteral : defineTypes) {
			items.add(eEnumLiteral.getName());
		}
		DefineType.setItems(items.toArray(new String[items.size()]));
		
		//自治事务
		autoTransction = toolkit.createButton(client, "自治事务", SWT.CHECK);
		
		// 只读控制
		createDate.setEditable(false);
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(returnType);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbCreateDate);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(createDate);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbBizType);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bizType);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbDefineType);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(DefineType);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(autoTransction);
		
		section.setClient(client);
		return section;
	}
	
	//前置代码section
	protected Section createBeforeCodeSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("前置代码");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "前置代码：");
		beginCode = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultMultiLinesTextStyles() | SWT.V_SCROLL);
		
		// 只读控制
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		
		GridDataFactory.fillDefaults().grab(true, true).hint(10, 50).applyTo(beginCode);
		
		section.setClient(client);
		return section;
	}
	
	//后置代码section
	protected Section createAfterCodeSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("后置代码");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "后置代码：");
		endCode = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultMultiLinesTextStyles() | SWT.V_SCROLL);
		
		
		// 只读控制
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		
		GridDataFactory.fillDefaults().grab(true, true).hint(10, 50).applyTo(endCode);
		
		section.setClient(client);
		return section;
	}
	
	/**
	 * 数据绑定
	 */
	private void databinding() {
		
		//返回类型
		getBindingContext().bindValue(SWTObservables.observeText(returnType, SWT.Modify), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(),
						getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__RETURN_TYPE));
		
		//createDate
		getBindingContext().bindValue(SWTObservables.observeText(createDate, SWT.Modify), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(),
						getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__CREATE_DATE));
		
		//业务类型
		getBindingContext().bindValue(
				SWTObservables.observeSelection(bizType), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(), getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__BIZ_TYPE));

		//定义类型
		getBindingContext().bindValue(
				SWTObservables.observeSelection(DefineType), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(), getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__DEFINE_TYPE));
		
		//自治事务
		getBindingContext().bindValue(SWTObservables.observeSelection(autoTransction), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(), getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__AUTO_TRANSACTION));
		
		//前置代码
		getBindingContext().bindValue(SWTObservables.observeText(beginCode, SWT.Modify), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(), getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__BEGIN_CODE));

		//后置代码
		getBindingContext().bindValue(SWTObservables.observeText(endCode, SWT.Modify), 
				EMFEditObservables.observeValue(((EMFFormEditor)getEditor()).getEditingDomain(), getInfo(), 
						ProcdurePackage.Literals.PROCEDURE__END_CODE));
	}
}
