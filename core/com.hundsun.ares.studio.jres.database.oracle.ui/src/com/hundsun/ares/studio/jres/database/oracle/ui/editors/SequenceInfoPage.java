/**
 * 源程序名称：SequenceInfoPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.sequence.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.utils.IRevHistoryVersionCompartor;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;

/**
 * @author wangbin
 *
 */
public class SequenceInfoPage extends DataBindingFormPage {
	
	private ExtensibleModelComposite emc;
	private Text txtObjectID;
	
	/**
	 * 中文名
	 */
	private Text txtChineseName;
	
	/**
	 * 英文名
	 */
	private Text txtSequenceName;
	
	/**
	 * 步长
	 */
	private Text txtInCreMent;
	
	/**
	 * 最大值
	 */
	private Text txtMaxValue;
	
	/**
	 * 最小值
	 */
	private Text txtMinValue;
	
	/**
	 * 起始值
	 */
	private Text txtStart;
	
	/**
	 * 是否循环
	 */
	private Combo cmbCycle;
	
	/**
	 * 缓存
	 */
	private Text txtCache;
	
	/**
	 * 是否缓存
	 */
	private Combo cmbIsCache;
	
	/**
	 * 是否存在历史表
	 */
	private Button txtIsHis;
	
	/**
	 * 说明
	 */
	private Text txDescription;
	
	/**
	 * 表空间
	 */
	private Combo comboCurTable;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public SequenceInfoPage(SequenceEMFFormEditor sequenceEditor, String id, String title) {
		super(sequenceEditor, id, title);
	}
	
	IEditableControl controler;
	Section basicSection;
	
	protected List<Object> getTableList(String refType){

		List<Object> tableList = new ArrayList<Object>();
		
		IARESProject pro = getEditor().getARESResource().getARESProject();
		
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(pro, refType, true);
		
		for(ReferenceInfo referenceInfo : infoList){
			IARESResource owner = referenceInfo.getResource();
			tableList.add(owner.getName());
		}
		
		return tableList;
	}
	
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {

		final Composite parent = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		basicSection = toolkit.createSection(parent,  FormWidgetUtils.getDefaultSectionStyles());
		basicSection.setText("基本信息");
		
		Composite composite = toolkit.createComposite(basicSection,SWT.NONE);
		basicSection.setClient(composite);
		
		composite.setLayout(new GridLayout(6, false));
		
		Label lblObjectID = toolkit.createLabel(composite, "对象号：");
		txtObjectID = toolkit.createText(composite, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		//序列名
		Label lbSequenceName = new Label(composite, SWT.NONE);
		lbSequenceName.setText("名称");
		txtSequenceName = new Text(composite, SWT.BORDER);
		txtSequenceName.setEditable(false);
		
		Label lbChineseName = new Label(composite, SWT.NONE);
		lbChineseName.setText("中文名");
		txtChineseName = new Text(composite, SWT.BORDER);
		
		Label lblVersion = toolkit.createLabel(composite, "版本号：");
		Text texVersion = toolkit.createText(composite, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		texVersion.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		Label lblUpdate = toolkit.createLabel(composite, "更新时间：");
		Text texUpdate = toolkit.createText(composite, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		texUpdate.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		{
			//找出最新的版本号
			EObject obj = getEditor().getInfo();
			if (obj instanceof SequenceResourceData) {
				List<RevisionHistory> hises = ((SequenceResourceData) obj).getHistories();
				List<RevisionHistory> tempHis = (List<RevisionHistory>) EcoreUtil.copyAll(hises);
				Collections.sort(tempHis, new IRevHistoryVersionCompartor());
				if (hises.size() > 0) {
					texVersion.setText(tempHis.get(0).getVersion());
					texUpdate.setText(tempHis.get(0).getModifiedDate());
				}else {
					//2013年5月24日14:43:41 如果没有修改记录信息，则取所在子系统当前版本号+1
					IARESResource aresResource = getEditor().getARESResource();
					
					IARESModule topModule = null; 
					if (aresResource == null) {
						topModule = null; 
					} else {
						String rootType = aresResource.getRoot().getType(); 
						if (ARESElementUtil.isDatabaseRoot(rootType)) {
							topModule = ARESElementUtil.getTopModule(aresResource);
						} else if (ARESElementUtil.isMetadataRoot(rootType)) {
							// topModule为null的效果就是不计算模块
							topModule = null;
						} else {
							topModule = aresResource.getModule();
						}
					}
					
					// 当前已经保存的资源中的最大版本
					RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(topModule);
					if (his != null) {
						String currentVersion = his.getVersion();
						// 项目属性
						String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(aresResource.getARESProject());
						
						// 找上述3者最大值
						String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, projectVersion));
						// 第一次找不到任何记录的时候
						if (StringUtils.equals(currentVersion, versionStr)) {
							texUpdate.setText(his.getModifiedDate());
						}
						if (StringUtils.isEmpty(versionStr)) {
							versionStr = "1.0.0.0";
						} 
						try{
							texVersion.setText(RevisionHistoryUtil.increase(versionStr));
							
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		//步长
		Label lbInCreMent = new Label(composite, SWT.NONE);
		lbInCreMent.setText("步长");
		txtInCreMent = new Text(composite, SWT.BORDER);
		
		//最大值
		Label lbMaxValue = new Label(composite, SWT.NONE);
		lbMaxValue.setText("最大值");
		txtMaxValue = new Text(composite, SWT.BORDER);
		
		//最小值
		Label lbMinValue = new Label(composite, SWT.NONE);
		lbMinValue.setText("最小值");
		txtMinValue = new Text(composite, SWT.BORDER);
		
		//起始值
		Label lbStart = new Label(composite, SWT.NONE);
		lbStart.setText("起始值");
		txtStart = new Text(composite, SWT.BORDER);
		
		//是否循环
		Label lbCycle = new Label(composite, SWT.NONE);
		lbCycle.setText("是否循环");
		cmbCycle = new Combo(composite, SWT.NONE|SWT.READ_ONLY);
		
		//是否缓存
		Label lbCache = new Label(composite, SWT.NONE);
		lbCache.setText("缓存大小");
		txtCache = new Text(composite, SWT.BORDER);
		
		//是否循环
		Label lbIsCache = new Label(composite, SWT.NONE);
		lbIsCache.setText("是否缓存");
		cmbIsCache = new Combo(composite, SWT.NONE|SWT.READ_ONLY);
		
//		txtIsHis = toolkit.createButton(composite, "存在历史表：",SWT.CHECK);
		Label lbIsHis = new Label(composite, SWT.NONE);
		lbIsHis.setText("存在历史表：");
		txtIsHis = new Button(composite, SWT.CHECK);
		
		Label lblCurTable = toolkit.createLabel(composite, "表空间：");
		comboCurTable = new Combo(composite, SWT.READ_ONLY);
		{
			List<String> items = new ArrayList<String>();
			List<ReferenceInfo> refs = ReferenceManager.getInstance().getReferenceInfos(getEditor().getARESResource().getARESProject(),IOracleRefType.Space , true);
			for(ReferenceInfo info : refs){
				items.add(info.getRefName());
			}
//			Collections.sort(items);//排序
			Collections.sort(items, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.toUpperCase().compareTo(o2.toUpperCase());//不区分大小写
				}
			});
			comboCurTable.setItems(items.toArray(new String[0]));
		}
		
		//是否循环
		Label lbDesc = new Label(composite, SWT.NONE);
		lbDesc.setText("说明");
		txDescription = toolkit.createText(composite, StringUtils.EMPTY, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		
		GridLayoutFactory.swtDefaults().applyTo(parent);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(basicSection);
		//
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbChineseName);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbSequenceName);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbInCreMent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbMaxValue);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbMinValue);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbStart);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbCycle);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbCache);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbIsHis);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lblCurTable);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lbDesc);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lblObjectID);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblVersion);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(texVersion);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblUpdate);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(texUpdate);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lblVersion);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(lblUpdate);
		GridDataFactory.swtDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(texVersion);
		GridDataFactory.swtDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(texUpdate);
		GridDataFactory.swtDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtObjectID);
		GridDataFactory.swtDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtChineseName);
		GridDataFactory.swtDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtSequenceName);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtInCreMent);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtMaxValue);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtMinValue);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtStart);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(cmbCycle);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtCache);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(cmbIsCache);
		GridDataFactory.swtDefaults().grab(true, true).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(txtIsHis);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).align(SWT.FILL, SWT.CENTER).applyTo(comboCurTable);
		GridDataFactory.swtDefaults().hint(-1, 80).grab(true, true).align(SWT.FILL, SWT.TOP).span(5, 1).applyTo(txDescription);
		
		//只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtObjectID));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtChineseName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtInCreMent));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtMaxValue));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtMinValue));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtStart));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(cmbCycle));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtCache));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(cmbIsCache));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtIsHis));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txDescription));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(comboCurTable));

		Section extendSection = createExtendedInfoSection(parent, toolkit);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendSection);
		
		parent.getParent().layout();
		
		if(getInfo() !=null){
			doDataBingingOnControls();
		}
		
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}
	
	protected Section createExtendedInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("扩展信息");
		
		emc = new ExtensibleModelComposite(section, toolkit);
		emc.setProblemPool(getProblemPool());
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(emc));
		
		section.setClient(emc);
		return section;
	}
	
	@Override
	public void infoChange() {
		emc.setInput(getEditor().getARESResource(), getInfo());
		super.infoChange();
	}

	@Override
	protected void doDataBingingOnControls() {
		bingSelection(comboCurTable, getInfo().getData2().get(IOracleConstant.SEQUENCE_DATA2_KEY),OraclePackage.Literals.ORACLE_SEQUENCE_PROPERTY__SPACE );
		bingText(txtSequenceName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingText(txtObjectID, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
		bingSelection(txtIsHis, getInfo(),OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__IS_HISTORY );
		bingText(txDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingText(txtChineseName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtCache, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CACHE);
		bingText(txtMaxValue, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MAX_VALUE);
		bingText(txtMinValue, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__MIN_VALUE);
		bingText(txtStart, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__START);
		bingText(txtInCreMent, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__INCREMENT);
		bingCombo(cmbCycle, new Object[]{Boolean.TRUE, Boolean.FALSE}, new LabelProvider() {
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				if (element instanceof Boolean) {
					if (((Boolean) element).booleanValue()) {
						return "循环";
					} else {
						return "不循环";
					}
				}
				return StringUtils.EMPTY;
			}
		}, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__CYCLE);
		bingCombo(cmbIsCache, new Object[]{Boolean.TRUE, Boolean.FALSE}, new LabelProvider() {
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				if (element instanceof Boolean) {
					if (((Boolean) element).booleanValue()) {
						return "缓存";
					} else {
						return "不缓存";
					}
				}
				return StringUtils.EMPTY;
			}
		}, getInfo(), OraclePackage.Literals.SEQUENCE_RESOURCE_DATA__USE_CACHE);
	}
	
}
