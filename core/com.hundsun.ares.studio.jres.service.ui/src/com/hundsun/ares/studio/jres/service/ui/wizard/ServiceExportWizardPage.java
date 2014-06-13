/**
 * 
 */
package com.hundsun.ares.studio.jres.service.ui.wizard;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.hundsun.ares.studio.biz.ui.wizard.ExportWizardPage;

/**
 * @author yanwj06282
 *
 */
public class ServiceExportWizardPage extends ExportWizardPage {

	private boolean defValueStatus = true;//默认导出默认值
	private boolean multStatus = true;//默认导出数量关系
	private boolean splitdocStatus = false;
	
	public ServiceExportWizardPage(String pageName, ISelection select,
			String moduleRootName) {
		super(pageName, select, moduleRootName);
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Composite client = (Composite) getControl();
		final Group pathGroup = new Group(client, SWT.None);
		
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(pathGroup);
		final Button defaultValue = new Button(pathGroup, SWT.CHECK);
		defaultValue.setText("导出\"默认值\"");//默认导出默认值
		defaultValue.setSelection(true);//默认导出默认值
		final Button mult = new Button(pathGroup, SWT.CHECK);
		mult.setText("导出\"数量关系\"");//默认导出数量关系
		mult.setSelection(true);//默认导出数量关系
		final Button splitdoc = new Button(pathGroup, SWT.CHECK);
		splitdoc.setText("按子系统生成");//按子系统生成
		splitdoc.setSelection(false);//按子系统生成
		
		GridDataFactory.fillDefaults().span(2, 1).applyTo(pathGroup);
		
		defaultValue.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				defValueStatus = defaultValue.getSelection();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				defValueStatus = true;
			}
		});
		
		mult.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				multStatus = mult.getSelection();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				multStatus = true;
			}
		});
		
		splitdoc.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				splitdocStatus = splitdoc.getSelection();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				splitdocStatus = false;
			}
		});
		
		GridDataFactory.swtDefaults().applyTo(defaultValue);
		GridDataFactory.swtDefaults().applyTo(mult);
		setControl(client);
	}
	
	public boolean getDefValueStatus(){
		return defValueStatus;
	}
	
	public boolean getMultStatus (){
		return multStatus;
	}
	
	public boolean getSplitdocStatus(){
		return splitdocStatus;
	}
	
}
