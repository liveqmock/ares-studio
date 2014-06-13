package com.hundsun.ares.studio.jres.database.ui.pages;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.hundsun.ares.studio.jres.database.DatabaseCore;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;

public class DatabasePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		setDescription("数据库");
		setPreferenceStore(DatabaseUI.getDefault().getPreferenceStore());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		
		StringFieldEditor tableLength = new StringFieldEditor(IDBConstant.TABLE_NAME_LENGTH, "表名长度:", getFieldEditorParent());
		addField(tableLength);
		StringFieldEditor colLength = new StringFieldEditor(IDBConstant.TABLE_COLUMN_LENGTH, "字段名长度:", getFieldEditorParent());
		addField(colLength);
		StringFieldEditor indexLength = new StringFieldEditor(IDBConstant.INDEX_LENGTH, "索引名长度:", getFieldEditorParent());
		addField(indexLength);
		StringFieldEditor constraintLength = new StringFieldEditor(IDBConstant.CONSTRAINT_LENGTH, "约束名长度:", getFieldEditorParent());
		addField(constraintLength);
		
	
		
	}
}
