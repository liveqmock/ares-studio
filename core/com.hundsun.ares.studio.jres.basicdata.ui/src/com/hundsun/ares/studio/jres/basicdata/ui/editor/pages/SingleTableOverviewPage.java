/**
 * 源程序名称：DBTableOverviewPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import java.util.EventObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;

/**
 * @author gongyf
 *
 */
public class SingleTableOverviewPage extends DataBindingFormPage {
	
	private Text txtName;
	private Text txtChineseName;
	private Text txtVersionName;
	private Text txtUpdateName;
	private Text txtDescription;
	private Text txtFile;

	private ExtensibleModelComposite emc;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public SingleTableOverviewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		Section baseSection = createBaseInfoSection(composite, toolkit);
		Section basicdataSection = createBasicdataInfoSection(composite,toolkit);
		Section extendedSection = createExtendedInfoSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(baseSection);
		if(basicdataSection != null){
			GridDataFactory.fillDefaults().grab(true, false).applyTo(basicdataSection);
		}
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendedSection);
	}

	/**
	 * 创建基础数据信息
	 * @param composite
	 * @param toolkit
	 * @return
	 */
	private Section createBasicdataInfoSection(Composite parent, FormToolkit toolkit) {
		IARESResource res = getEditor().getARESResource();
		//元数据的基础数据不显示该section
		if (!StringUtils.equals(IBasicDataRestypes.singleTable,res.getType())
				&& !StringUtils.equals(IBasicDataRestypes.MasterSlaveTable,res.getType())
				&& !StringUtils.equals(IBasicDataRestypes.MasterSlaveLinkTable,res.getType())){
			return null;
		}
		
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("表关联信息");
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbType = toolkit.createLabel(client, "基础数据类型：");
		Text txtType = toolkit.createText(client, StringUtils.EMPTY, SWT.BORDER);
		
		Hyperlink lbMaster = toolkit.createHyperlink(client, "表名：", SWT.None);
		final Text txtMaster = toolkit.createText(client, StringUtils.EMPTY, SWT.BORDER);
		
		Hyperlink lbSlave = toolkit.createHyperlink(client, "从表名：",SWT.None);
		final Text txtSlave = toolkit.createText(client, StringUtils.EMPTY, SWT.BORDER);
		
		Hyperlink lbLink = toolkit.createHyperlink(client, "信息表名：",SWT.None);
		final Text txtLink = toolkit.createText(client, StringUtils.EMPTY, SWT.BORDER);
		
		Label lbFile = toolkit.createLabel(client, "生成脚本文件名：");
		txtFile = toolkit.createText(client, StringUtils.EMPTY, SWT.BORDER);
		
		// 只读控制
		txtType.setEditable(false);
		txtType.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtMaster.setEditable(false);
		txtMaster.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtSlave.setEditable(false);
		txtSlave.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtLink.setEditable(false);
		txtLink.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		
		// 布局
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbType);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtType);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbMaster);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtMaster);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbSlave);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtSlave);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbLink);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtLink);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbFile);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtFile);
		
		//设置控件可视和值
		String type = EPackageUtil.getBasicDataType(res.getARESProject());
		if(StringUtils.isNotBlank(type)){
			String name = EPackageUtil.getEpackageFactoryItemName(type);
			txtType.setText(name);
		}
		PackageDefine define = null;
		try {
			define = BasicDataEpackageFactory.eINSTANCE.getDefine(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(define != null){
			if(define instanceof SingleTable){
				txtMaster.setText(((SingleTable) define).getMaster());
				lbSlave.setVisible(false);
				txtSlave.setVisible(false);
				lbLink.setVisible(false);
				txtLink.setVisible(false);
			}else if(define instanceof MasterSlaveTable){
				lbMaster.setText("主表名：");
				txtMaster.setText(((MasterSlaveTable) define).getMaster());
				txtSlave.setText(((MasterSlaveTable) define).getSlave());
				lbLink.setVisible(false);
				txtLink.setVisible(false);
			}else if(define instanceof MasterSlaveLinkTable){
				lbMaster.setText("主表名：");
				txtMaster.setText(((MasterSlaveLinkTable) define).getMaster());
				txtSlave.setText(((MasterSlaveLinkTable) define).getSlave());
				lbLink.setText("关联表名：");
				txtLink.setText(((MasterSlaveLinkTable) define).getLink());
			}
		}
		
		
		section.setClient(client);
		
		//跳转至表格
		lbMaster.addHyperlinkListener(new HyperlinkAdapter(){
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtMaster.getText());
			}
		});
		
		lbSlave.addHyperlinkListener(new HyperlinkAdapter(){
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtSlave.getText());
			}
		});
		
		lbLink.addHyperlinkListener(new HyperlinkAdapter(){
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(txtLink.getText());
			}
		});
		
		return section;
	}
	
	//打开编辑器
	private void linkARESResource(String resName) {
		IARESResource res = getEditor().getARESResource();
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(res.getARESProject(), EPackageUtil.getBasicDataType(res.getARESProject()), resName, true);
		if(info != null ){
			try {
				ARESUIUtil.openEditor(info.getResource());
			} catch (PartInitException e) {
				e.printStackTrace();
			}	
		}
	}

	/**
	 * 创建基本信息页
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createBaseInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lblName = toolkit.createLabel(client, "名称：");
		txtName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblChineseName = toolkit.createLabel(client, "中文名：");
		txtChineseName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblVersionName = toolkit.createLabel(client, "版本号：");
		txtVersionName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblUpdateName = toolkit.createLabel(client, "更新时间：");
		txtUpdateName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblDescription = toolkit.createLabel(client, "说明：");
		txtDescription = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultMultiLinesTextStyles());
		
		{
			//找出最新的版本号
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule(),IBasicDataRestypes.singleTable);
			if (his != null) {
				txtVersionName.setText(his.getVersion());
				txtUpdateName.setText(his.getModifiedDate());
			}else {
				his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule());
				if (his != null) {
					txtVersionName.setText(his.getVersion());
					txtUpdateName.setText(his.getModifiedDate());
				}else {
					String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(getEditor().getARESResource().getARESProject());
					if (StringUtils.isBlank(projectVersion)) {
						projectVersion = "1.0.0.1";
					}
					txtVersionName.setText(projectVersion);
				}
			}
		}
		
		// 只读控制
		txtName.setEditable(false);
		txtName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtVersionName.setEditable(false);
		txtVersionName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtUpdateName.setEditable(false);
		txtUpdateName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		//getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtChineseName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDescription));
		
		// 布局
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblChineseName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtChineseName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblVersionName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtVersionName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblUpdateName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtUpdateName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblDescription);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, true).hint(10, 50).applyTo(txtDescription);
		
		section.setClient(client);
		return section;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		//bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		// BUG #5701 基础数据下资源重命名时资源编辑器中名称未修改
		// 编辑器中的资源名不要用模型中的名字
		IARESResource res = getEditor().getARESResource();
		txtName.setText(res == null ? StringUtils.EMPTY : res.getName());
		bingText(txtChineseName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		if(null != txtFile){//元数据的基础数据中“生成脚本文件名”是为空，需要保护
			bingText(txtFile, getInfo(), BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL__FILE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		emc.setInput(getEditor().getARESResource(), getInfo());
		super.infoChange();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#commandStackChanged(java.util.EventObject)
	 */
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
	
}
