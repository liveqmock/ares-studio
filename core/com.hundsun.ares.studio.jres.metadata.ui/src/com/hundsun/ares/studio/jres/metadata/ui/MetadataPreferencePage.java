package com.hundsun.ares.studio.jres.metadata.ui;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.hundsun.ares.studio.jres.metadata.MetadataCore;

public class MetadataPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setDescription("元数据相关的首选项");
		setPreferenceStore(MetadataUI.getPlugin().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new RadioGroupFieldEditor(MetadataCore.PRE_APPLICATION_DEPARTMENT, 
				"菜单应用模式", 2,new String[][] {{"证券", MetadataCore.PRE_APPLICATION_DEPARTMENT_STOCK},
						{"银行", MetadataCore.PRE_APPLICATION_DEPARTMENT_FINANCE}},getFieldEditorParent(),true));
	}

}
