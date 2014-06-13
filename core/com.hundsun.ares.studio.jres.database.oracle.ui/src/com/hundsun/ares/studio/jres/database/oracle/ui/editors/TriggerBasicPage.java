/**
 * 源程序名称：TriggerBasicPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.trigger.ui
 * 功能说明：触发器编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import java.util.EventObject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;
/**
 * @author wangxh
 *
 */
public class TriggerBasicPage extends DataBindingFormPage {

	public TriggerBasicPage(TriggerEditor triggerEditor, String id, String title) {
		super(triggerEditor, id, title);
	}
	
	
	IEditableControl controler;
	Section basicSection;
	Text txtSQL;
	Text txtName;
	Text txtChineseName;
	Text txtDescription;

	private ExtensibleModelComposite emc;

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
		composite.setLayout(new GridLayout(4, false));
		
		Label lbName = toolkit.createLabel(composite, "名称", SWT.None);
		txtName = toolkit.createText(composite, "", SWT.BORDER);
		txtName.setEnabled(false);
		
		Label lbChineseName = toolkit.createLabel(composite, "中文名", SWT.None);
		txtChineseName = toolkit.createText(composite, "", SWT.BORDER);
		
		Label lbDescription = toolkit.createLabel(composite, "备注", SWT.None);
		txtDescription = toolkit.createText(composite, "", SWT.BORDER);
		
		Label lbSQL = toolkit.createLabel(composite, "触发器语句", SWT.None);
		txtSQL = toolkit.createText(composite, "", SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		
		Section extendedSection = createExtendedInfoSection(parent, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(parent);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(basicSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendedSection);
		
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbChineseName);
		GridDataFactory.swtDefaults().grab(true, true).align(SWT.FILL, SWT.TOP).applyTo(txtChineseName);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbName);
		GridDataFactory.swtDefaults().grab(true, true).align(SWT.FILL, SWT.TOP).applyTo(txtName);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbSQL);
		GridDataFactory.swtDefaults().hint(-1, 80).grab(true, true).span(3, 1).align(SWT.FILL, SWT.TOP).applyTo(txtSQL);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbDescription);
		GridDataFactory.swtDefaults().grab(true, true).span(3, 1).align(SWT.FILL, SWT.TOP).applyTo(txtDescription);
		
		//只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtChineseName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtSQL));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDescription));
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

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		bingText(txtChineseName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingText(txtSQL, getInfo(), OraclePackage.Literals.TRIGGER_RESOURCE_DATA__SQL);
		
	}

}
