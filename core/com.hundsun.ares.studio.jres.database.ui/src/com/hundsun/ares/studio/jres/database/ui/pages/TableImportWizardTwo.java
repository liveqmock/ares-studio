package com.hundsun.ares.studio.jres.database.ui.pages;


import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.IARESModule;

public class TableImportWizardTwo extends WizardPage {
	private String file = "";
	private String targetDir = "";
	boolean genStdField = true ;
	boolean genType = true ;
	boolean genDoc = true ;
	Composite displayCom = null;
	private static final String TARGET_FILENAME = "\\标准字段合并评审记录.xls";
	
	
	public TableImportWizardTwo(String pageName,IARESModule module) {
		super(pageName);
		setTitle(pageName);
	}

	public void createControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		
		composite.setLayout(new GridLayout(4,true));
		final Text text = new Text(composite,SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		text.setEditable(false);
		Button button = new Button(composite,SWT.NORMAL);
		button.setText("选择文件");
		button.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));

		final Text targettext = new Text(composite,SWT.BORDER);
		targettext.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		Button targetButton = new Button(composite,SWT.NORMAL);
		targetButton.setText("标准字段合并评审记录输出目录");
		targetButton.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		button.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(composite.getShell());
				dialog.setFilterExtensions(new String[]{"*.pdm"});
				file = dialog.open();
				
				if (StringUtils.isNotBlank(file)) {
					text.setText(file);
					targetDir = StringUtils.substringBeforeLast(file, File.separator) + TARGET_FILENAME;
					targettext.setText(targetDir);
				}
				TableImportWizardTwo.this.getContainer().updateButtons();
			}
		});
		targetButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());
				targetDir = dialog.open();
				targetDir = targetDir + TARGET_FILENAME;
				targettext.setText(targetDir);
			}
		});
		targettext.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				targetDir = targettext.getText();
			}
		});
		
		//如果用户上传的PDM文件里面没有package节点，则提示用户自己命名一个package
		displayCom = new Composite(composite,SWT.NULL);
		displayCom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5,
				2));
		displayCom.setLayout(new GridLayout(5,true));
		//该区域默认为隐藏,只有文件读取出错的时候才显示
		displayCom.setVisible(false);
		
		Label packageLabel = new Label(displayCom,SWT.NORMAL);
		packageLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		packageLabel.setText("请选择模块名称");
		setControl(composite);
	}
	
	public String getFile() {
		return file;
	}
	
	/**
	 * @return the targetDir
	 */
	public String getTargetDir() {
		if (StringUtils.endsWith(targetDir, File.separator) || StringUtils.endsWith(targetDir, "/")) {
			targetDir += TARGET_FILENAME;
		}
		if (!StringUtils.endsWith(targetDir, ".xls")) {
			targetDir += ".xls";
		}
		return targetDir;
	}

	public boolean isGenStdField(){

		return genStdField;
	}

	public boolean isGenType(){

		return genType;
	}
	
	public boolean isGenDoc(){

		return genDoc;
	}
}
