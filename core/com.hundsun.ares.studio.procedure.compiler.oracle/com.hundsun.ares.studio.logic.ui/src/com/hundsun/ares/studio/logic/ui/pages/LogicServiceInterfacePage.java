package com.hundsun.ares.studio.logic.ui.pages;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.constants.ILogicResType;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;

public class LogicServiceInterfacePage extends LogicInterfacePage {

	/**
	 * 超时时间
	 */
	Text txtTimeOut;
	
	/**
	 * 许可证检查
	 */
	Button btnCheckLience;
	
	public LogicServiceInterfacePage(EStructuralFeature interfaceFeature,
			EMFFormEditor editor, String id, String title) {
		super(interfaceFeature, editor, id, title);
	}
	
	@Override
	protected void doDataBingingOnControls() {
		super.doDataBingingOnControls();
		bingText(txtTimeOut, getInfo(), LogicPackage.Literals.LOGIC_SERVICE__TIME_OUT);
		bingSelection(btnCheckLience, getInfo(), LogicPackage.Literals.LOGIC_SERVICE__IS_CHECK_ACCESS);
	}
	
	@Override
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = super.createBasicInfoSection(composite, toolkit);
		
		// 追加控件
		Composite client = (Composite)section.getClient();
		Composite item = createGridComposite(client,toolkit);
		Label lbTimeout = toolkit.createLabel(item, "超时时间：");
		txtTimeOut = toolkit.createText(item, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		item = createGridComposite(client,toolkit);
		Label lbCheckLience = toolkit.createLabel(item, "许可证检查：");
		btnCheckLience = toolkit.createButton(item, "", SWT.CHECK);
		
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtTimeOut));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(btnCheckLience));
		
		// 布局
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbTimeout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtTimeOut);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbCheckLience);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(btnCheckLience);

		section.setClient(client);
		return section;
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
	
	
	@Override
	protected String getResType() {
		return ILogicResType.LOGIC_SERVICE;
	}

}
