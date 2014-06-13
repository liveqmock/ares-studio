package com.hundsun.ares.studio.internal.ui;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.hundsun.ares.studio.ui.ARESUI;

public class ARESPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		setDescription("ARES首选项");
		setPreferenceStore(ARESUI.INSTANCE.getPreferenceStore());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		Composite lbGroupParent = getFieldEditorParent();
		lbGroupParent.setLayout(new FillLayout());
		Group group = new Group(lbGroupParent, SWT.None);
		group.setText("资源管理视图节点名字显示配置");
		
		StringFieldEditor lbModule = new StringFieldEditor("label.module", "模块:", group);
		addField(lbModule);
		
		StringFieldEditor lbResource = new StringFieldEditor("label", "资源:", group);
		addField(lbResource);
		
		String[][] charset = new String[4][2];
		charset[0][0] = "GB2312";
		charset[0][1] = "GB2312";
		charset[1][0] = "GBK";
		charset[1][1] = "GBK";
		charset[2][0] = "UTF-8";
		charset[2][1] = "UTF-8";
		charset[3][0] = "UNICODE";
		charset[3][1] = "UNICODE";
		addField(new DirectoryFieldEditor(ARESUI.PRE_GENERATE_PATH,"代码生成路径：", getFieldEditorParent()));
		addField(new ComboFieldEditor(ARESUI.PRE_GENERATE_CHARSET,"代码生成编码:",charset,getFieldEditorParent()));
		
		addField(new RadioGroupFieldEditor(ARESUI.PRE_CELLEDITOR_ACTIVE_MODE, "表格编辑方式", 2, new String[][]{
				{"单击编辑", ARESUI.PRE_CELLEDITOR_ACTIVE_MODE_SINGLECLICK}, {"双击编辑", ARESUI.PRE_CELLEDITOR_ACTIVE_MODE_DOUBLECLICK}
		},getFieldEditorParent(),true));
	}

}
