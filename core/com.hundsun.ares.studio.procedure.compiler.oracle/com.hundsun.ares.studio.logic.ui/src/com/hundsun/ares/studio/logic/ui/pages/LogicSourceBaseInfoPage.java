/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.ui.pages;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

/**
 * @author qinyuan
 *
 */
public class LogicSourceBaseInfoPage extends DataBindingFormPage {
	
	/**
	 * 对象号
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
	 * 结果集返回
	 */
	Button btnResultSetReturn;
	

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public LogicSourceBaseInfoPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		bingText(objectID, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
//		bingText(txtVersion, getInfo(), businessPackage.Literals.FUNCTION__VERSION);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingText(txtCName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtFlag, getInfo(), BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG);
		bingText(txtDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		
		bingSelection(btnResultSetReturn, getInfo(), BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		Section baseSection = createBaseInfoSection(composite, toolkit);
		baseSection.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		
		
	}

	/**
	 * @param composite
	 * @param toolkit
	 * @return
	 */
	protected Section createBaseInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "对象号：");
		objectID = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbName = toolkit.createLabel(client, "名称：");
		txtName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbCName = toolkit.createLabel(client, "中文名：");
		txtCName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());

		Label lbVersion = toolkit.createLabel(client, "版本号：");
		txtVersion = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbFlag = toolkit.createLabel(client, "接口标志：");
		txtFlag = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbUpdateTime = toolkit.createLabel(client, "更新时间：");
		txtUpdateTime = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbDescription = toolkit.createLabel(client, "说明：");
		txtDescription = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultMultiLinesTextStyles());
		
		Label lbResultSetReturn = toolkit.createLabel(client, "结果集返回：");
		btnResultSetReturn = toolkit.createButton(client, "", SWT.CHECK);
		
		
		// 只读控制
		txtName.setEnabled(false);
		txtUpdateTime.setEnabled(false);
		txtVersion.setEnabled(false);
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtCName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDescription));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(objectID));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtFlag));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(btnResultSetReturn));
		
		// 布局
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbVersion);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbFlag);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbResultSetReturn);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(objectID);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtVersion);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtFlag);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(btnResultSetReturn);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbCName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbDescription);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbUpdateTime);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtName);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtCName);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtUpdateTime);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, true).hint(10, 50).applyTo(txtDescription);
		
		section.setClient(client);
		return section;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		destroyDataBindingContext();
		doDataBingingOnControls();
		super.infoChange();
	}

}
