/**
 * 
 */
package com.hundsun.ares.studio.procedure.ui.support;

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

import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenModuleCodeDialog;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;

/**
 * @author yanwj06282
 *
 */
public class GenProcedureModuleCodeDialog extends GenModuleCodeDialog {

	public GenProcedureModuleCodeDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Button headCode;
	
	protected Button endCode;
	
	protected boolean isHeadCode = false;
	
	protected boolean isEndCode = false;
	
	protected boolean isCreateHeadCode = true;
	
	protected boolean isCreateEndCode = true;
	
	@Override
	protected String getTitle() {
		return "模块生成";
	}
	
	public void setIsCreateHeadCode(boolean isCreateHeadCode){
		this.isCreateHeadCode = isCreateHeadCode;
	}
	
	public void setIsCreatEndCode(boolean isCreatEndCode){
		this.isCreateEndCode = isCreatEndCode;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area = createExtendsAres(area);
		return area;
	}
	
	protected Composite createExtendsAres(Composite masterCom){
		Group prodGroup = new Group(masterCom, SWT.BORDER);
		prodGroup.setText("过程");
		
		if (isCreateHeadCode) {
			headCode = new Button(prodGroup, SWT.CHECK);
			headCode.setText("是否生成前置代码");
			isHeadCode = ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_BEGIN_CODE);
			headCode.setSelection(isHeadCode);
			headCode.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					isHeadCode = headCode.getSelection();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
		}
		
		if (isCreateEndCode) {
			isEndCode = ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_END_CODE);
			endCode = new Button(prodGroup, SWT.CHECK);
			endCode.setSelection(isEndCode);
			endCode.setText("是否生成后置代码");
			endCode.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					isEndCode = endCode.getSelection();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
		}
		
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(prodGroup);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(prodGroup);
		if (isCreateHeadCode) {
			GridDataFactory.fillDefaults().applyTo(headCode);
		}
		if (isCreateEndCode) {
			GridDataFactory.fillDefaults().applyTo(endCode);
		}
		return masterCom;
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
