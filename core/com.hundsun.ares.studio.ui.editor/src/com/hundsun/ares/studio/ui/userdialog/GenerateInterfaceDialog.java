/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.userdialog;

import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * FIXME 生成界面对话框
 * 根据xml配置动态生成用户界面
 * 现提供CHECK,RADIO,TEXT,COMBO几种控件的生成
 * @author maxh
 * @version 1.0
 * @history
 */
public class GenerateInterfaceDialog extends org.eclipse.jface.dialogs.Dialog {
	DialogInterfaceXml xmlDialog;

	public GenerateInterfaceDialog (Shell parentShell, DialogInterfaceXml xmlDialog) {
		super (parentShell);
		this.xmlDialog = xmlDialog;
	}

	// 生成控件的数组
	Control[][] control;
	// 用于封装控件value
	/*
	 * 控件内容集合 key为存放控件xml中定义变量以及value的map value为控件是否选中的布尔值，主要为check和radio服务
	 */
	HashMap<String, Object> resultMap = new HashMap<String, Object> ();

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea (parent);
		control = new Control[xmlDialog.getLstMenuInterfaceGroup().size ()][];
		for (int groupIndex = 0; groupIndex < xmlDialog.getLstMenuInterfaceGroup().size (); groupIndex++) {
			DialogInterfaceGroup group = xmlDialog.getLstMenuInterfaceGroup().get (groupIndex);
			final List<DialogInterfaceItem> lstItem = group.getLstMenuInterfaceItem ();
			Composite subComposite;
			if (group.isUse ()) {
				Group menuGroup = new Group (composite, SWT.NONE);
				menuGroup.setVisible (true);
				menuGroup.setLayout (new RowLayout ());
				menuGroup.setText (group.getGroupName ());
				subComposite = menuGroup;
			}else{
				// 创建一个子组件，用于摆放group之外的布局控件
				subComposite = new Composite (composite, SWT.NONE);
			}
			GridLayout layout = new GridLayout (6, false);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			subComposite.setLayout (layout);
			control[groupIndex] = new Control[lstItem.size ()];
			for (int i = 0; i < lstItem.size (); i++) {

				if (lstItem.get (i).getSwtType ().equalsIgnoreCase ("CHECK")||lstItem.get (i).getSwtType ().equalsIgnoreCase ("RADIO")) {
					// 按钮控件
					int swtType = SWT.NULL;
					if (lstItem.get (i).getSwtType ().equalsIgnoreCase ("CHECK")) swtType = SWT.CHECK;
					if (lstItem.get (i).getSwtType ().equalsIgnoreCase ("RADIO")) swtType = SWT.RADIO;
					Button btn = new Button (subComposite, swtType);
					btn.setText (lstItem.get (i).getLableName ());
					btn.setData (lstItem.get (i).getId());
					btn.setSelection(lstItem.get(i).getValue().equalsIgnoreCase("true"));
					control[groupIndex][i] = btn;
				}else if (lstItem.get (i).getSwtType ().equalsIgnoreCase ("TEXT")) {
					// Text控件
					Label lable = new Label (subComposite, SWT.NONE);
					lable.setText (lstItem.get (i).getLableName () + ":");
					Text text = new Text (subComposite, SWT.BORDER);
					text.setData (lstItem.get (i).getId());
					text.setText(lstItem.get(i).getValue());
					control[groupIndex][i] = text;
				}else if (lstItem.get (i).getSwtType ().equalsIgnoreCase ("COMBO")) {
					// 下拉框控件
					Label lable = new Label (subComposite, SWT.NONE);
					lable.setText (lstItem.get (i).getLableName () + ":");
					Combo combo = new Combo (subComposite, SWT.BORDER);
					String[] values = lstItem.get (i).getValue ().split (",");
					for (String value : values)
						combo.add (value);
					combo.select (0);
					combo.setData (lstItem.get (i).getId());
					control[groupIndex][i] = combo;
				}
			}

		}
		return composite;
	}

	@Override
	protected void okPressed() {
		// 循环获取控件中的内容
		for (int i = 0; i < control.length; i++) {
			for (int j = 0; j < control[i].length; j++) {
				String value = control[i][j].getData ().toString ();
				if (control[i][j] instanceof Button) {
					Button btn = (Button) control[i][j];
					resultMap.put(value, btn.getSelection());
				}else if (control[i][j] instanceof Text) {
					Text text = (Text) control[i][j];
					resultMap.put(value, text.getText());
				}else if (control[i][j] instanceof Combo) {
					Combo combo = (Combo) control[i][j];
					resultMap.put(value, combo.getText());
				}
			}
		}
		super.okPressed ();
	}
	
	@Override
	public Shell getShell() {
		// TODO Auto-generated method stub
		Shell shell = super.getShell();
		shell.setText(xmlDialog.getTitle());
		return shell;
	}
	
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
}
