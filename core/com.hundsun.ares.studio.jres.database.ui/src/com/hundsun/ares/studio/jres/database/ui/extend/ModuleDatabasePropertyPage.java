package com.hundsun.ares.studio.jres.database.ui.extend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.jres.model.database.oracle.util.OracleDataBaseUtil;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.databinding.IndexToItemsConverter;
import com.hundsun.ares.studio.ui.databinding.ItemsToIndexConverter;
import com.hundsun.ares.studio.ui.databinding.LabelProviderConverter;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;

public class ModuleDatabasePropertyPage extends EMFExtendSectionScrolledFormPage<ModuleProperty> {

	public static final String KEY = "DatabaseModuleExtensibleProperty";
	private Combo txtTableType;
	private Combo txtDatabase;
	private Text txtIndexTS;
	private Text txthis;
	private Text txthisindex;
	private Text txtfile;
	private Text txtfileindex;
	private Text txtre;
	private Text txtcl;
	private Text txtclindex;
	private Text txtsf;
	private Text txtsn;
	private Text txtsd;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ModuleDatabasePropertyPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected EClass getEClass() {
		return OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY;
	}

	@Override
	protected String getMapKey() {
		return KEY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		
		createBaseInfoSection(managedForm, toolkit);
		createBizPropertyConfigSection(managedForm, toolkit);
	}

	/**
	 * 创建基本信息区域
	 * @param managedForm
	 * @param toolkit
	 */
	private void createBaseInfoSection(IManagedForm managedForm,
			FormToolkit toolkit) {
		Section sectionBaseInfo = createSectionWithTitle(managedForm.getForm()
				.getBody(), toolkit, "模块信息", true);
		final Composite baseInfo = toolkit.createComposite(sectionBaseInfo, SWT.NONE);
		
		Label lbSubSysID = toolkit.createLabel(baseInfo, "表类型：", SWT.NONE);
		txtTableType = new Combo(baseInfo, SWT.BORDER | SWT.READ_ONLY);
		{
			txtTableType.setItems(OracleDataBaseUtil.getDataBaseName(getARESProject(),true));
		}
		
		Label lbDBName = toolkit.createLabel(baseInfo, "所在数据库：", SWT.NONE);
		txtDatabase = new Combo(baseInfo, SWT.BORDER);
		{
			txtDatabase.setItems(OracleDataBaseUtil.getDataBaseName(getARESProject(),true));
		}
		
		txtDatabase.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(getARESProject(), IOracleRefType.Space, txtDatabase.getText(), true);
				if (info != null) {
					txtIndexTS.setText(getSpaceText(OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE));
					txthis.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_SPACE));
					txthisindex.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__HIS_INDEX_SPACE));
					txtfile.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_SPACE));
					txtfileindex.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_RELATION_PROPERTY__FILE_INDEX_SPACE));
					txtre.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_PROPERTY__REDU_TABLE));
					txtcl.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_PROPERTY__CHEAR_TABLE));
					txtclindex.setText(getSpaceText(ChousePackage.Literals.TABLE_SPACE_PROPERTY__CHEAR_TABLE_INDEX));
				}else {
					txtIndexTS.setText(StringUtils.EMPTY);
					txthis.setText(StringUtils.EMPTY);
					txthisindex.setText(StringUtils.EMPTY);
					txtfile.setText(StringUtils.EMPTY);
					txtfileindex.setText(StringUtils.EMPTY);
					txtre.setText(StringUtils.EMPTY);
					txtcl.setText(StringUtils.EMPTY);
					txtclindex.setText(StringUtils.EMPTY);
				}
			}
		});
		
		Label lbDBConn = toolkit.createLabel(baseInfo, "索引表空间：", SWT.NONE);
		txtIndexTS = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbhis = toolkit.createLabel(baseInfo, "历史表空间：", SWT.NONE);
		txthis = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbhisindex = toolkit.createLabel(baseInfo, "历史索引表空间：", SWT.NONE);
		txthisindex = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbfile = toolkit.createLabel(baseInfo, "归档表空间：", SWT.NONE);
		txtfile = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbfileindex = toolkit.createLabel(baseInfo, "归档索引表空间：", SWT.NONE );
		txtfileindex = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbre = toolkit.createLabel(baseInfo, "冗余表空间：", SWT.NONE);
		txtre = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbcl = toolkit.createLabel(baseInfo, "清算表空间：", SWT.NONE);
		txtcl = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbclindex = toolkit.createLabel(baseInfo, "清算索引表空间：", SWT.NONE);
		txtclindex = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.READ_ONLY);
		
		Label lbsf = toolkit.createLabel(baseInfo, "表分区字段：", SWT.NONE);
		txtsf = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbsn = toolkit.createLabel(baseInfo, "分区个数：", SWT.NONE);
		txtsn = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbsd = toolkit.createLabel(baseInfo, "分区开始日期：", SWT.NONE);
		txtsd = toolkit.createText(baseInfo, "", SWT.BORDER );
		
		//布局
		baseInfo.setLayout(new GridLayout(2, false));
//		GridDataFactory.fillDefaults().grab(true, true).applyTo(baseInfo);
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbSubSysID);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtTableType);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbDBName);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtDatabase);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbDBConn);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtIndexTS);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbhis);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txthis);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbhisindex);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txthisindex);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbfile);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtfile);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbfileindex);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtfileindex);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbre);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtre);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbcl);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtcl);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbclindex);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtclindex);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbsf);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtsf);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbsn);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtsn);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbsd);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtsd);
		
		sectionBaseInfo.setClient(baseInfo);
		toolkit.paintBordersFor(baseInfo);
		
		getEditControls().add(txtTableType);
		getEditControls().add(txtDatabase);
		getEditControls().add(txtIndexTS);
		getEditControls().add(txthis);
		getEditControls().add(txthisindex);
		getEditControls().add(txtfile);
		getEditControls().add(txtfileindex);
		getEditControls().add(txtre);
		getEditControls().add(txtcl);
		getEditControls().add(txtclindex);
		getEditControls().add(txtsf);
		getEditControls().add(txtsn);
		getEditControls().add(txtsd);
		
		databinding();
	}
	
	private String getSpaceText( EStructuralFeature feature) {
		String space = StringUtils.defaultString(txtDatabase.getText());
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(getARESProject(),IOracleRefType.SpaceRelation ,space, true);
		if(info != null){
			TableSpaceRelation obj = (TableSpaceRelation) info.getObject();
			if (feature == OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE) {
				return obj.getIndexSpace();
			}
			if (feature == OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE) {
				return obj.getMainSpace();
			}
			if (obj != null) {
				for(Iterator<String> it = obj.getData2().keySet().iterator();it.hasNext();){
					String key = it.next();
					if (obj.getData2().get(key).eClass().getEAllStructuralFeatures().contains(feature)) {
						Object o = obj.getData2().get(key).eGet(feature);
						return o == null ? StringUtils.EMPTY : o.toString();
					}
				}
			}
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 业务类别
	 * @param managedForm
	 * @param toolkit
	 */
	private void createBizPropertyConfigSection(IManagedForm managedForm,	FormToolkit toolkit) {
		Section sectionBizConfig = createSectionWithTitle(managedForm.getForm()
				.getBody(), toolkit, "业务类别", true);
		final Composite client = toolkit.createComposite(sectionBizConfig, SWT.NONE);
		client.setLayout(new GridLayout(10, false));
		//获取业务包配置信息
		List<ReferenceInfo> infos = ReferenceManager.getInstance().getReferenceInfos(getARESProject(), IMetadataRefType.BizPropertyConfig, true);
		//无类别，对应的值为空值""
		Button btnNoConfig = toolkit.createButton(client, "无类别", SWT.RADIO);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(btnNoConfig);
		//绑定
		getBindingContext().bindValue(SWTObservables.observeSelection(btnNoConfig), 
				new BizPropertyConfigObservableValue(getEditingDomain(), getModel(), OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG,""));
		for(ReferenceInfo info : infos){
			//每一个业务配置对应一个radioButton
			BizPropertyConfig config = (BizPropertyConfig) info.getObject();
			//界面上显示的是业务配置的中文名
			Button btnConfig = toolkit.createButton(client, config.getChineseName(), SWT.RADIO);
			GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(btnConfig);
			//绑定值为业务配置的编号
			getBindingContext().bindValue(SWTObservables.observeSelection(btnConfig), 
					new BizPropertyConfigObservableValue(getEditingDomain(), getModel(), OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__BIZ_PKG, config.getName()));
			
		}
		sectionBizConfig.setClient(client);
	}
	
	//绑定业务类别属性值和radioButton
	private class BizPropertyConfigObservableValue extends EditingDomainEObjectObservableValue{
		String value;
		public BizPropertyConfigObservableValue(EditingDomain domain,
				EObject eObject, EStructuralFeature eStructuralFeature,String value) {
			super(domain, eObject, eStructuralFeature);
			this.value = value;
		}
		
		@Override
		protected Object doGetValue() {
			Object value = super.doGetValue();
			//如果获取的值和button对应的值相等，则表示该button被选中
			if(value.equals(this.value)){
				return true;
			}
			return false;
		}
		
		@Override
		protected void doSetValue(Object value) {
			if(value instanceof Boolean){
				if((Boolean) value){
					//value为true，表示选中了该button，将该button对应的值设置到模型中
					super.doSetValue(this.value);
				}
			}
		}
		
	}
	
	/**
	 * 数据绑定
	 */
	private void databinding() {
		List<String> items = new ArrayList<String>();
		List<ReferenceInfo> refs = ReferenceManager.getInstance().getReferenceInfos(getARESProject(),IOracleRefType.Space , true);
		for(ReferenceInfo info : refs){
			items.add(info.getRefName());
		}
		bingCombo(txtDatabase, items.toArray(new String[0]) , new LabelProvider(), getModel(), OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPACE);
		
		bingCombo(txtTableType, new table_type[]{table_type.COMMON,table_type.TEMP_NO_VALUE,table_type.TEMP_WITH_VALUE} , new LabelProvider(){
			@Override
			public String getText(Object element) {
				table_type type = (table_type)element;
				if(type.getValue() == table_type.COMMON_VALUE) {
					return "一般表";
				}else if(type.getValue() == table_type.TEMP_NO_VALUE_VALUE){
					return "临时表(不保留数据)";
				}else if(type.getValue() == table_type.TEMP_WITH_VALUE_VALUE) {
					return "临时表(保留数据)";
				}
				
				return super.getText(element);
			}
		}, getModel(), OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__TABLE_TYPE);
		

		getBindingContext().bindValue(SWTObservables.observeText(txtsf, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_FIELD));
		
		getBindingContext().bindValue(SWTObservables.observeText(txtsn, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__SPLIT_NUM));
		
		getBindingContext().bindValue(SWTObservables.observeText(txtsd, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						OraclePackage.Literals.DATABASE_MODULE_EXTENSIBLE_PROPERTY__START_DATE));
		
	}
	
	protected void bingCombo(Combo cb, Object[] items, ILabelProvider labelProvider, EObject container, EStructuralFeature feature) {
		
		labelProvider = (ILabelProvider) ObjectUtils.defaultIfNull(labelProvider, new LabelProvider());
		
		// 绑定下拉菜单
		getBindingContext().bindList(
				SWTObservables.observeItems(cb), 
				Observables.staticObservableList(Arrays.asList(items)), null, 
				new UpdateListStrategy().setConverter(new LabelProviderConverter(labelProvider)));
		
		// 数据绑定
		getBindingContext().bindValue(
				SWTObservables.observeSingleSelectionIndex(cb), 
				EMFEditObservables.observeValue(getEditingDomain(), container, feature), new UpdateValueStrategy().setConverter(
						new IndexToItemsConverter(items)
				), new UpdateValueStrategy().setConverter(new ItemsToIndexConverter(items)));
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
		if (StringUtils.equals(getResource().getRoot().getElementName(), "database")) {
			return true;
		}
		return false;
	}


}
