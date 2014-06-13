/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.newwizard;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.ui.AresResourceCategoryFilter;
import com.hundsun.ares.studio.ui.AresResourceFilter;
import com.hundsun.ares.studio.ui.LibFilter;

/**
 * 新建资源向导页面的基类。
 * @author lvgao
 */
public class ARESResourceNewWizardPage extends ElementSelectionWizardPageWithNameInputEX{
	private static final Logger logger = Logger.getLogger(ARESResourceNewWizardPage.class.getName());

	public static final String CONTEXT_KEY_RES_TYPE = "资源类型";
	public static final String CONTEXT_KEY_CNAME = "资源中文名称";
	public static final String CONTEXT_KEY_GROUP = "group";
	
	protected Text txt_CName;
	protected Text txtGroup;
	
	protected String newCName = "";
	protected String group;
	
	protected ModifyListener modifyCNameListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			newCName = txt_CName.getText();
			updateComplete();
		}
	};
	
	// 这个页面创建的资源的类型
	protected String resType;
	
	public ARESResourceNewWizardPage(String pageName, IWorkbench workbench, IARESElement selection, String resType) {
		super(pageName, workbench, selection);
		this.resType = resType;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.ElementSelectionWizardPageWithNameInputEX#addValidators(java.util.List)
	 */
	@Override
	protected void addValidators(List<IWizardPageValidator> validators) {
		super.addValidators(validators);
		validators.add(new ReourceNameModuleDuplicateValidator());
		validators.add(new GroupNameValidator());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.ARESElementSelectionWizardPage#getType()
	 */
	public String getType() {
		return resType;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.CommonElementSelectionPage#addFilter()
	 * 根据模块根类型过滤资源
	 * 
	 */
	@Override
	protected void addFilter() {
		if(viewer != null){
			viewer.addFilter(new NotAresresourceFilter());
			viewer.addFilter(new RestypeViewerFilter(resType));
			viewer.addFilter(new LibFilter());
			viewer.addFilter(new AresResourceCategoryFilter());
			viewer.addFilter(new AresResourceFilter());
			if(null != selection && selection instanceof IARESElement){
				viewer.addFilter(new NotCurrentProjectFilter(((IARESElement)selection).getARESProject()));
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.newwizard.ElementSelectionWizardPageWithNameInputEX#createText(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createText(Composite composite) {
		super.createText(composite);
		Label moduleNameLabel = new Label(composite, SWT.NONE);
		moduleNameLabel.setText("中文名:");
		GridData gd = new GridData();
		moduleNameLabel.setLayoutData(gd);
		
		txt_CName = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		txt_CName.setLayoutData(gd);
		txt_CName.addModifyListener(modifyCNameListener);
		txt_Name.setText(newCName);
		txt_Name.setSelection(newCName.length());
		
		Label lbGroup = new Label(composite, SWT.NONE);
		lbGroup.setText("分组");
		GridDataFactory.fillDefaults().applyTo(lbGroup);
		txtGroup = new Text(composite, SWT.BORDER);
		txtGroup.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				group = txtGroup.getText();
				updateComplete();
			}
		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtGroup);
		
	}
	@Override
	public void setContext(Map<Object, Object> context) {
		super.setContext(context);
		context.put(CONTEXT_KEY_RES_TYPE, resType);
		if(null != txt_CName){
			context.put(CONTEXT_KEY_CNAME, txt_CName.getText());
		}
		if (txtGroup != null) {
			context.put(CONTEXT_KEY_GROUP, group);
		}
		
	}

}
