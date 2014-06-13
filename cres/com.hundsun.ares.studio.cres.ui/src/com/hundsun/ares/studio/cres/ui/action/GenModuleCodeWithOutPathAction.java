package com.hundsun.ares.studio.cres.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GenModuleCodeWithOutPathAction extends GenModuleCodeWithPathAction{
	
	GenModuleCodeDialog dialog;
	
	@Override
	protected boolean isGenCodeWithPath() {
		int type = dialog.getType();
		if (GenModuleCodeType.NODIR_GENMODULE.getType() != type) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean isGenCodeWithCNamePath() {
		int type = dialog.getType();
		if (GenModuleCodeType.CH_DIR_GENMODULE.getType() == type) {
			return true;
		}
		return false;
	}
	
	@Override
	public void run(IAction action) {
		dialog = new GenModuleCodeDialog(getShell());
		if (dialog.open() == Window.OK) {
			super.run(action);
		}
	}
	
	class GenModuleCodeDialog extends TitleAreaDialog {

		private Combo combp;
		
		private Button headCode;
		
		private Button endCode;
		
		private int type;
		
		private boolean isHeadCode = false;
		
		private boolean isEndCode = false;
		
		public GenModuleCodeDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Control createContents(Composite parent) {
			getShell().setText("模块生成");
			return super.createContents(parent);
		}
		
		@Override
		protected Control createDialogArea(Composite parent) {
			Composite area = (Composite) super.createDialogArea(parent);
			Composite masterCom = new Composite(area, SWT.NONE);
			
			Group modeGroup = new Group(masterCom, SWT.BORDER);
			modeGroup.setText("生成模式");
			
			Label label = new Label(modeGroup, SWT.NONE);
			label.setText("模式");
			combp = new Combo(modeGroup, SWT.NONE | SWT.READ_ONLY);
			String[] items = getComboItem();
			combp.setItems(items);
			combp.setText(items[0]);
			//默认值
			type = GenModuleCodeType.getType(items[0]);
			combp.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					String text = combp.getText();
					if (StringUtils.isNotBlank(text)) {
						type = GenModuleCodeType.getType(text);
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			
			Group prodGroup = new Group(masterCom, SWT.BORDER);
			prodGroup.setText("过程");
			
			headCode = new Button(prodGroup, SWT.CHECK);
			headCode.setText("是否生成前置代码");
			endCode = new Button(prodGroup, SWT.CHECK);
			endCode.setText("是否生成后置代码");
			
			headCode.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					isHeadCode = headCode.getSelection();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			
			endCode.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					isEndCode = endCode.getSelection();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
//			masterCom.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
			GridLayoutFactory.fillDefaults().numColumns(1).applyTo(masterCom);
			GridLayoutFactory.fillDefaults().numColumns(2).applyTo(modeGroup);
			GridLayoutFactory.fillDefaults().numColumns(1).applyTo(prodGroup);
			
			GridDataFactory.fillDefaults().grab(true, true).applyTo(masterCom);
			GridDataFactory.fillDefaults().grab(true, true).applyTo(modeGroup);
			GridDataFactory.fillDefaults().grab(true, true).applyTo(prodGroup);
			GridDataFactory.swtDefaults().applyTo(label);
			GridDataFactory.fillDefaults().applyTo(headCode);
			GridDataFactory.fillDefaults().applyTo(endCode);
			return area;
		}
		
		private String[] getComboItem(){
			List<String> types = new ArrayList<String>();
			for(GenModuleCodeType type : GenModuleCodeType.getEnumTypes()){
				types.add(type.getDesc());
			}
			return types.toArray(new String[types.size()]);
		}
		
		public int getType(){
			return type;
		}
		
		public boolean isHeadCode(){
			return isHeadCode;
		}
		
		public boolean isEndCode(){
			return isEndCode;
		}
		
	}
	
}
