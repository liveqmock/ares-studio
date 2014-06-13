package com.hundsun.ares.studio.jres.database.ui.editors;

import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.ui.editor.BooleanExtensionFieldEditor;

public class UseNonStdFieldExtensionFieldEditor extends BooleanExtensionFieldEditor {
	
	public UseNonStdFieldExtensionFieldEditor() {
		setId(IDBConstant.USE_NON_STD_FIELD);
		setName("数据库中允许使用非标准字段");
	}

}
