/**
 * 
 */
package com.hundsun.ares.studio.cres.extend.ui.module.gencode;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.cres.extend.ui.edit.support.GenEndCodeType;
import com.hundsun.ares.studio.cres.ui.action.GenModuleCodeType;

/**
 * @author yanwj06282
 *
 */
public class GenModuleCodeDialog extends Dialog {

	protected int type;
	
	public GenModuleCodeDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		getShell().setText(getTitle());
		return super.createContents(parent);
	}
	
	protected String getTitle(){
		return "代码生成";
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite masterCom = (Composite) super.createDialogArea(parent);
		Group modeGroup = new Group(masterCom, SWT.BORDER);
		modeGroup.setText("模式");
		
		final Button mode1But = new Button(modeGroup, SWT.RADIO);
		mode1But.setText(GenModuleCodeType.CH_DIR_GENMODULE.getDesc());
		final Button mode2But = new Button(modeGroup, SWT.RADIO);
		mode2But.setText(GenModuleCodeType.EN_DIR_GENMODULE.getDesc());
		final Button mode3But = new Button(modeGroup, SWT.RADIO);
		mode3But.setText(GenModuleCodeType.NODIR_GENMODULE.getDesc());
		
		mode1But.setSelection(true);
		type = GenEndCodeType.CH_DIR_GENMODULE.getType();
		
		mode1But.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mode1But.getSelection()) {
					type = GenEndCodeType.CH_DIR_GENMODULE.getType();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		mode2But.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mode2But.getSelection()) {
					type = GenEndCodeType.EN_DIR_GENMODULE.getType();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		mode3But.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mode3But.getSelection()) {
					type = GenEndCodeType.NODIR_GENMODULE.getType();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(masterCom);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(modeGroup);
		
		GridDataFactory.fillDefaults().grab(true, true).hint(500, 50).applyTo(modeGroup);
		GridDataFactory.fillDefaults().applyTo(mode1But);
		GridDataFactory.fillDefaults().applyTo(mode2But);
		GridDataFactory.fillDefaults().applyTo(mode3But);
		return masterCom;
	}
	
	public int getType(){
		return type;
	}
	
}
