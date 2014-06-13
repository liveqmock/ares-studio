/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.newwizard;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 包含元素选择和一个名字输入框。
 * @author lvgao
 */
public abstract class ElementSelectionWizardPageWithNameInputEX extends CommonElementSelectionPageEX {

	public static final String CONTEXT_KEY_NAME = "资源名称";
	
	protected Text txt_Name;
	protected IResource resource;
	protected String newName = "";
	
	public ElementSelectionWizardPageWithNameInputEX(String pageName,
			IWorkbench workbench, IARESElement selection) {
		super(pageName, workbench, selection);
	}
	
	public String getNewName() {
		if(null != txt_Name&&!txt_Name.isDisposed()){
			newName = txt_Name.getText();
		}
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	protected ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			newName = txt_Name.getText();
			updateComplete();
		}
	};

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(composite);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	
		Composite treeContainer = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(treeContainer);
		
		createTreeViewer(treeContainer);
		
//		GridData gd = new GridData(GridData.FILL_BOTH);
//		gd.verticalIndent = 0;
//		gd.horizontalIndent = 0;
//		gd.horizontalSpan = 2;
//		gd.heightHint = 300;
//		gd.widthHint = 260;
//		treeContainer.setLayoutData(gd);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).span(2,1).hint(260, 300).applyTo(treeContainer);

		createText(composite);
		
		createClientContent(composite);
		
		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection), true);
		}

		addFilter();
		//错误检查
		addValidators(validators);
		
		setControl(composite);

		updateComplete();
	}
	
	protected void createText(Composite composite){
		Label moduleNameLabel = new Label(composite, SWT.NONE);
		moduleNameLabel.setText("名字:");
		GridData gd = new GridData();
		moduleNameLabel.setLayoutData(gd);
		
		txt_Name = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		txt_Name.setLayoutData(gd);
		txt_Name.addModifyListener(modifyListener);
		txt_Name.setFocus();
		txt_Name.setText(newName);
		txt_Name.setSelection(newName.length());
	}

	/**
	 * 构建用户自定义区域
	 * @param composite
	 */
	protected void createClientContent(Composite composite) {
		// do nothing
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.CommonElementSelectionPageEX#addValidators(java.util.List)
	 */
	@Override
	protected void addValidators(List<IWizardPageValidator> validators) {
		super.addValidators(validators);
		validators.add(new ReourceNameValidator());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.CommonElementSelectionPageEX#setValidateContext(java.util.Map)
	 */
	@Override
	public void setContext(Map<Object, Object> context) {
		super.setContext(context);
		if(null != txt_Name){
			context.put(CONTEXT_KEY_NAME, txt_Name.getText());
		}
	}

}

