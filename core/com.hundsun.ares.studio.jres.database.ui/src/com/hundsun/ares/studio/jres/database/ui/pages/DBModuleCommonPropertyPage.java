/**
 * 源程序名称：DBModuleCommonPropertyPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.pages;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;

/**
 * @author gongyf
 *
 */
public class DBModuleCommonPropertyPage extends EMFExtendSectionScrolledFormPage<ARESProjectProperty> {
	
	private static final char DATABASE_SEPARATOR_CHAR = ',';
	
	/**当前数据库*/
	private Combo comboDataBase;
	
	/**支持数据库*/
	private Text txtSupDatabases;
	
	/**浏览按钮*/
	private Button btnChooseSup;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DBModuleCommonPropertyPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getModel()
	 */
	@Override
	protected DBModuleCommonProperty getModel() {
		return (DBModuleCommonProperty) super.getModel();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		
		FormToolkit toolkitDB = managedForm.getToolkit();
		Section sectionDB = createSectionWithTitle(managedForm.getForm()
				.getBody(), toolkitDB, "数据库", true);
		final Composite compDB = toolkitDB.createComposite(sectionDB, SWT.NONE);
		
		Label lbDatabase = toolkitDB.createLabel(compDB, "当前数据库", SWT.NONE);
		comboDataBase = new Combo(compDB, SWT.NONE|SWT.READ_ONLY);
		
		Label lbSupportDatabase = toolkitDB.createLabel(compDB, "支持数据库", SWT.NONE);
		txtSupDatabases = new Text(compDB, SWT.NONE | SWT.BORDER | SWT.READ_ONLY);
		btnChooseSup = new Button(compDB, SWT.PUSH);
		btnChooseSup.setText("浏览");
		
		btnChooseSup.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				DBModuleDatabaseSelectionDialog dbModuledialog = new DBModuleDatabaseSelectionDialog(compDB.getShell(), 
						"选择数据库类型",  
						getModel());
				if(dbModuledialog.open() == Window.OK){
					
					List<String> list = (List<String>) dbModuledialog.getResult();
					getEditingDomain().getCommandStack().execute(
							SetCommand.create(getEditingDomain(), 
									getModel(), 
									DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES, 
									StringUtils.join(list.toArray(), DATABASE_SEPARATOR_CHAR)));
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// 设置布局
		compDB.setLayout(new GridLayout(3, false));
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbDatabase);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(comboDataBase);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbSupportDatabase);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtSupDatabases);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).span(1, 1).applyTo(btnChooseSup);
		
		sectionDB.setClient(compDB);
		toolkitDB.paintBordersFor(compDB);
		
		refreshItems();
		
		//数据绑定
		databinding();
	}

	/**
	 * 
	 */
	private void databinding() {
		
		getBindingContext().bindValue(SWTObservables.observeText(comboDataBase), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY__DATABASE));
		
		getModel().eAdapters().add(new AdapterImpl(){
			/* (non-Javadoc)
			 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			@Override
			public void notifyChanged(Notification msg) {
				if (DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES.equals(msg.getFeature())) {
					refreshItems();
				}
			}
		});
		
		getBindingContext().bindValue(SWTObservables.observeText(txtSupDatabases, SWT.Modify), 
			EMFEditObservables.observeValue(getEditingDomain(), getModel(), DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY__SUPPORT_DATABASES));
		
	}

	private void refreshItems() {
		String oldText = comboDataBase.getText();
		comboDataBase.setItems(StringUtils.split(getModel().getSupportDatabases(), DATABASE_SEPARATOR_CHAR));
		if (!StringUtils.isBlank(oldText)) {
			comboDataBase.setText(oldText);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
//		try {
//			if(getARESProject().getProject().hasNature(JRESCore.MODULE_NATURE)) {
//				return true;
//			}
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return DatabasePackage.Literals.DB_MODULE_COMMON_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		//return IJRESConstant.DBMODULE_COMMONPROPERTY_KEY;
		return "";
	}

}
