package com.hundsun.ares.studio.procedure.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.hundsun.ares.studio.procdure.provider.ProcedureUI;

public class ProdcedurePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ProdcedurePreferencePage() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		setDescription("存储过程");
		setPreferenceStore(ProcedureUI.getPlugin().getPreferenceStore());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(ProcedureUI.PER_AUTO_DEFINE_PROC_INPARAM, "是否自动将输入参数定义成变量", getFieldEditorParent()));
		addField(new BooleanFieldEditor(ProcedureUI.PER_NOT_DEFINE_CONNECT_TYPE, "参数定义类型是否不带“%type”", getFieldEditorParent()));
		addField(new BooleanFieldEditor(ProcedureUI.PER_RETURN_ERROR_INFO, "出错信息不需要返回", getFieldEditorParent()));
		addField(new BooleanFieldEditor(ProcedureUI.PER_GEN_RELATED_INFO, "是否需要生成关联信息", getFieldEditorParent()));
		
		if(ProcedureUI.isStock2Procedure()){
			addField(new BooleanFieldEditor(ProcedureUI.PER_GEN_BEGIN_CODE, "是否生成前置代码", getFieldEditorParent()));
			addField(new BooleanFieldEditor(ProcedureUI.PER_GEN_END_CODE, "是否生成后置代码", getFieldEditorParent()));
		}
	}

}
