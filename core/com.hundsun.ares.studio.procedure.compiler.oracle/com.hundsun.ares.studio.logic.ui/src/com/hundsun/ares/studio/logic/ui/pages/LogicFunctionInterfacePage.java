package com.hundsun.ares.studio.logic.ui.pages;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.logic.LogicPackage;
import com.hundsun.ares.studio.logic.constants.ILogicResType;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;

public class LogicFunctionInterfacePage extends LogicInterfacePage {

	/**
	 * 事务函数
	 */
	Button btnIsTransFunc;
	
	public LogicFunctionInterfacePage(EStructuralFeature interfaceFeature,
			EMFFormEditor editor, String id, String title) {
		super(interfaceFeature, editor, id, title);
	}

	@Override
	protected void doDataBingingOnControls() {
		super.doDataBingingOnControls();
		bingSelection(btnIsTransFunc, getInfo(), LogicPackage.Literals.LOGIC_FUNCTION__IS_TRANS_FUNC);
	}
	
	@Override
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = super.createBasicInfoSection(composite, toolkit);
		
		// 追加控件
		Composite client = (Composite)section.getClient();
		Composite item = createGridComposite(client,toolkit);
		Label lbIsTransFunc = toolkit.createLabel(item, "事务函数：");
		btnIsTransFunc = toolkit.createButton(item, "", SWT.CHECK);
		
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(btnIsTransFunc));
		
		// 布局
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbIsTransFunc);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(btnIsTransFunc);

		section.setClient(client);
		return section;
	}

	@Override
	protected String getResType() {
		return ILogicResType.LOGIC_FUNCTION;
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
	
}
