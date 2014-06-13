/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.usermacro.ui.editors.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.blocks.JresDetailsPage;
import com.hundsun.ares.studio.usermacro.UserMacroPackage;


/**
 * @author qinyuan
 *
 */
public class UserMacroDetailsPage extends JresDetailsPage{
	//宏名
	private Text txtUserMacroName;
	//宏参数序列
	private Text txtUserMacroParas;
	//宏内容
	private Text txtUserMacroContent;
	//宏说明
	private Text txtUserMacroDesc;

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#getSectionName()
	 */
	@Override
	protected String getSectionName() {
		return "宏参数编辑";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#getDescription()
	 */
	@Override
	protected String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#createSectionContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createSectionContents(Composite client) {
		IARESResource resource = page.getEditor().getARESResource();
		String type = resource.getType();
		Label lbUserMacroName = new Label(client, SWT.NONE);
		lbUserMacroName.setText("宏名称：");
		txtUserMacroName = new Text(client, SWT.NONE|SWT.BORDER);
		
		Label lbUserMacroParas = new Label(client, SWT.NONE);
		lbUserMacroParas.setText("参数列表");
		txtUserMacroParas = new Text(client, SWT.NONE|SWT.BORDER);
		
		Label lbUserMacroContent = new Label(client, SWT.NONE);
		if(type.equals("systemmacro")){
			lbUserMacroContent.setText("宏说明：");
			txtUserMacroDesc = new Text(client, SWT.NONE|SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);

		}else{
			lbUserMacroContent.setText("宏定义：");
			txtUserMacroContent = new Text(client, SWT.NONE|SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);
		}
		
		Label lbUserMacroDesc = new Label(client, SWT.NONE);
		if(type.equals("systemmacro")){
			lbUserMacroDesc.setText("代码示例：");
			txtUserMacroContent = new Text(client, SWT.NONE|SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);

		}else{
			lbUserMacroDesc.setText("宏说明：");
			txtUserMacroDesc = new Text(client, SWT.NONE|SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);
		}
		
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbUserMacroName);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbUserMacroParas);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbUserMacroContent);
		GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.TOP).applyTo(lbUserMacroDesc);
		
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(txtUserMacroName);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(txtUserMacroParas);
		GridDataFactory.swtDefaults().hint(100, 180).align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(txtUserMacroContent);
		GridDataFactory.swtDefaults().hint(100, 180).align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(txtUserMacroDesc);
		 
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresDetailsPage#databinding()
	 */
	@Override
	protected DataBindingContext databinding() {
		DataBindingContext context = new DataBindingContext();
		
		context.bindValue(SWTObservables.observeText(txtUserMacroName, SWT.Modify), 
				EMFEditObservables.observeValue(page.getEditingDomain(),  (EObject) input, UserMacroPackage.Literals.USER_MACRO_ITEM__NAME));
		context.bindValue(SWTObservables.observeText(txtUserMacroParas, SWT.Modify), 
				EMFEditObservables.observeValue(page.getEditingDomain(),  (EObject) input, UserMacroPackage.Literals.USER_MACRO_ITEM__SEQUENCE));

		context.bindValue(SWTObservables.observeText(txtUserMacroContent, SWT.Modify), 
				EMFEditObservables.observeValue(page.getEditingDomain(),  (EObject) input, UserMacroPackage.Literals.USER_MACRO_ITEM__CONTENT));

		context.bindValue(SWTObservables.observeText(txtUserMacroDesc, SWT.Modify), 
				EMFEditObservables.observeValue(page.getEditingDomain(),  (EObject) input, UserMacroPackage.Literals.USER_MACRO_ITEM__DESCRIPTION));

		return context;
	}


}
