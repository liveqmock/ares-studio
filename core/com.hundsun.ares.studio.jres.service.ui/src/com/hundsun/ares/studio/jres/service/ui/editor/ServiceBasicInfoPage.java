package com.hundsun.ares.studio.jres.service.ui.editor;

import java.util.EventObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;

/**
 * 服务接口基本信息页面
 * @author sundl
 *
 */
public class ServiceBasicInfoPage extends DataBindingFormPage{

	private Text txtObjId;
	private Text txtEnglishName;
	private Text txtName;
	private Text txtDesc; 
	private Button bInputCollection;
	private Button bOutputCollection;
	private ExtensibleModelComposite emc;
	protected Text txtVersion;
	
	public ServiceBasicInfoPage(EMFFormEditor editor) {
		super(editor, "basic", "基本信息");
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		Section baseSection = createBaseInfoSection(composite, toolkit);
		Section extendSection = createExtendedInfoSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(baseSection);
		GridDataFactory.fillDefaults().grab(true, false).hint(-1, 100).applyTo(extendSection);
		
		composite.getParent().layout();
	}
	
	protected Section createBaseInfoSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		
		Composite contentBase = toolkit.createComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(contentBase);
		
		contentBase.setLayout(new GridLayout());
		
		Composite content = toolkit.createComposite(contentBase, SWT.NONE);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).applyTo(content);
		
		content.setLayout(new GridLayout(2, true));
		
		Composite contentDesc = toolkit.createComposite(contentBase, SWT.NONE);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).applyTo(contentDesc);
		contentDesc.setLayout(new GridLayout(2, false));
		
		Composite partLeft = toolkit.createComposite(content, SWT.NONE);
		Composite partRight = toolkit.createComposite(content, SWT.NONE); 
		
		partLeft.setLayout(new GridLayout(2, false));
		partRight.setLayout(new GridLayout(2, false));
		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(partLeft);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(partRight);
		
		Label lbObjectNo = toolkit.createLabel(partLeft, "功能号");
		GridDataFactory.fillDefaults().applyTo(lbObjectNo);
		txtObjId = toolkit.createText(partLeft, "" ,SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtObjId);
		
		Label lblName = toolkit.createLabel(partLeft, "中文名：");
		txtName = toolkit.createText(partLeft, "", SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblName);
		GridDataFactory.fillDefaults().grab(true, true).hint(10, SWT.DEFAULT).applyTo(txtName);
		
		Label lblUpdate = toolkit.createLabel(partLeft, "更新时间：");
		GridDataFactory.swtDefaults().applyTo(lblUpdate);
		Text txtUpdate = toolkit.createText(partLeft, "", SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtUpdate);
		
		Label lblEnglishName = toolkit.createLabel(partRight, "英文名：");
		txtEnglishName = toolkit.createText(partRight, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		GridDataFactory.fillDefaults().applyTo(lblEnglishName);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtEnglishName);
		
		
		Label lblVersion = toolkit.createLabel(partRight, "版本号：");
		Text txtVersion = toolkit.createText(partRight, "", SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblVersion);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtVersion);
		
		{
			//找出最新的版本号
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule(),IBizResType.Service);
			if (his != null) {
				txtVersion.setText(his.getVersion());
				txtUpdate.setText(his.getModifiedDate());
			}else {
				his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule());
				if (his != null) {
					txtVersion.setText(his.getVersion());
					txtUpdate.setText(his.getModifiedDate());
				}else {
					String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(getEditor().getARESResource().getARESProject());
					if (StringUtils.isBlank(projectVersion)) {
						projectVersion = "1.0.0.1";
					}
					txtVersion.setText(projectVersion);
				}
			}
		}
		Composite partDesc = toolkit.createComposite(contentDesc, SWT.NONE);
		partDesc.setLayout(new GridLayout(2, false));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(partDesc);
		Label lblDesc = toolkit.createLabel(partDesc, "说明：");
		txtDesc = toolkit.createText(partDesc, "", SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(lblDesc);
		GridDataFactory.fillDefaults().grab(true, true).hint(30, 60).indent(-12, 0).applyTo(txtDesc);
		
		txtEnglishName.setEditable(false);
		txtVersion.setEditable(false);
		txtUpdate.setEditable(false);
		txtEnglishName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtVersion.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txtUpdate.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		Label lbInputCollection = toolkit.createLabel(partDesc, "输入结果集：");
		bInputCollection = toolkit.createButton(partDesc, "", SWT.CHECK);
		GridDataFactory.swtDefaults().applyTo(lbInputCollection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bInputCollection);
		
		Label lbOutputCollection = toolkit.createLabel(partDesc, "输出结果集：");
		bOutputCollection = toolkit.createButton(partDesc, "", SWT.CHECK);
		GridDataFactory.swtDefaults().applyTo(lbOutputCollection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bOutputCollection);
		//只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtObjId));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDesc));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(bInputCollection));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(bOutputCollection));
		
		section.setClient(contentBase);
		
		return section;
	}
	
	@Override
	protected void doDataBingingOnControls() {
		bingText(txtObjId, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtDesc, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingText(txtEnglishName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingSelection(bInputCollection, ((Service)getInfo()).getInterface(), BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION);
		bingSelection(bOutputCollection, ((Service)getInfo()).getInterface(), BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION);
		
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
		super.infoChange();
		emc.setInput(getEditor().getARESResource(), getInfo());
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}
}
