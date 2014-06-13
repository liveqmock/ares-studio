/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.preferences.ErrorCheckPreferenceConstant;
import com.hundsun.ares.studio.core.preferences.ErrorCheckPreferenceHelper;

/**
 * 错误检查首选项Page
 * @author liaogc
 */
public class ErrorCheckPreferencePage extends PreferencePage implements IWorkbenchPreferencePage{
  private Button btCheck;
  private Button btMetadataCheck;
  private Button btBasicdataCheck;
  private Button btDatabaseCheck;
  private Button btServiceCheck;
  private Button btAtomCheck;
  private Button btLogicCheck;
  private Button btObjectCheck;
  private Button btProcedureCheck;
  private Button btRelationeCheck;
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent,parent.getStyle());
		Label lbError = new Label(composite,SWT.NONE);
		lbError.setText("错误检查");
		btCheck = new Button(composite,SWT.CHECK);
		btCheck.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				setButtonEnabled(btMetadataCheck,btCheck.getSelection());
				setButtonEnabled(btBasicdataCheck,btCheck.getSelection());
				setButtonEnabled(btDatabaseCheck,btCheck.getSelection());
				setButtonEnabled(btServiceCheck,btCheck.getSelection());
				setButtonEnabled(btAtomCheck,btCheck.getSelection());
				setButtonEnabled(btLogicCheck,btCheck.getSelection());
				setButtonEnabled(btObjectCheck,btCheck.getSelection());
				setButtonEnabled(btProcedureCheck,btCheck.getSelection());
				setButtonEnabled(btRelationeCheck,btCheck.getSelection());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Label lbRelationeCheck = new Label(composite,SWT.NONE);
		lbRelationeCheck.setText("检查相关资源");
		btRelationeCheck = new Button(composite,SWT.CHECK);
		Label lbErrorSp = new Label(composite,SWT.SEPARATOR|SWT.HORIZONTAL);
		//元数据错误检查
		Label lbMetadataCheck = new Label(composite,SWT.NONE);
		 lbMetadataCheck.setText("元数据");
		btMetadataCheck = new Button(composite,SWT.CHECK);
		if(isCanVisible("com.hundsun.ares.studio.jres.basicdata.core")){
			Label lbBasicdataCheck = new Label(composite,SWT.NONE);
			lbBasicdataCheck.setText("基础数据");
			btBasicdataCheck = new Button(composite,SWT.CHECK);
		}
		
		if(isCanVisible("com.hundsun.ares.studio.jres.database.core")){
			Label  lbDatadataCheck = new Label(composite,SWT.NONE);
				lbDatadataCheck.setText("数据库");
				btDatabaseCheck = new Button(composite,SWT.CHECK);
		}
		if(isCanVisible("com.hundsun.ares.studio.jres.service.core")){
			Label lbServiceeCheck = new Label(composite,SWT.NONE);
			 lbServiceeCheck.setText("服务接口");
			 btServiceCheck = new Button(composite,SWT.CHECK);
		}
		
		if(isCanVisible("com.hundsun.ares.studio.atom.core")){
			Label lbAtomCheck = new Label(composite,SWT.NONE);
			lbAtomCheck.setText("原子");
			btAtomCheck = new Button(composite,SWT.CHECK);	
		}
		
		if(isCanVisible("com.hundsun.ares.studio.logic.core")){
			Label lbLogicCheck = new Label(composite,SWT.NONE);
			lbLogicCheck.setText("逻辑");
			btLogicCheck = new Button(composite,SWT.CHECK);
		}
		if(isCanVisible("com.hundsun.ares.studio.procedure.core")){
			Label lbProcedureCheck = new Label(composite,SWT.NONE);
			lbProcedureCheck.setText("过程");
			btProcedureCheck = new Button(composite,SWT.CHECK);
		}
		
		if(isCanVisible("com.hundsun.ares.studio.biz.core")){
			Label lbObjectCheck = new Label(composite,SWT.NONE);
			lbObjectCheck.setText("对象");
			btObjectCheck = new Button(composite,SWT.CHECK);
			
			
		}
		
		
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(lbErrorSp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(composite);
		initValue();
		
	  return composite;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		IEclipsePreferences preferences  = new InstanceScope().getNode(ARESCore.PLUGIN_ID);
		if(preferences!=null){
			setPreferences(preferences,ErrorCheckPreferenceConstant.ERROR_CHECK,btCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.METADATA_CHECK,btMetadataCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.BASIC_DATA_CHECK,btBasicdataCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.DATABASE_CHECK,btDatabaseCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.SERVICE_CHECK,btServiceCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.ATOM_CHECK,btAtomCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.LOGIC_CHECK,btLogicCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.OBJECT_CHECK,btObjectCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.PROCEDURE_CHECK,btProcedureCheck);
			setPreferences(preferences,ErrorCheckPreferenceConstant.RELATION_CHECK,btRelationeCheck);
		}
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		ErrorCheckPreferenceHelper.getInstance().refresh();
		super.performApply();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performCancel()
	 */
	@Override
	public boolean performCancel() {
		return super.performCancel();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		
		setButtonSelected(btCheck,true);
		setButtonSelected(btMetadataCheck,true);
		setButtonSelected(btBasicdataCheck,true);
		setButtonSelected(btDatabaseCheck,true);
		setButtonSelected(btServiceCheck,true);
		setButtonSelected(btAtomCheck,true);
		setButtonSelected(btLogicCheck,true);
		setButtonSelected(btObjectCheck,true);
		setButtonSelected(btProcedureCheck,true);
		setButtonSelected(btRelationeCheck,true);

	}
	
  
	/**
	 * 初始各项值
	 */
	private void initValue(){
		IPreferencesService service = Platform.getPreferencesService();
		Preferences root = service.getRootNode();
		Preferences corenstanceNode = root.node(InstanceScope.SCOPE).node("com.hundsun.ares.studio.core");	
		if(corenstanceNode!=null){
			setButtonSelected(btCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.ERROR_CHECK, ErrorCheckPreferenceConstant.ERROR_CHECK_DEFVALUE));
			setButtonSelected(btMetadataCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.BASIC_DATA_CHECK, ErrorCheckPreferenceConstant.BASICDATA_CHECK_DEFVALUE));
			setButtonSelected(btBasicdataCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.RELATION_CHECK, ErrorCheckPreferenceConstant.RELATION_CHECK_DEFVALUE));
			setButtonSelected(btDatabaseCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.DATABASE_CHECK, ErrorCheckPreferenceConstant.DATABASE_CHECK_DEFVALUE));
			setButtonSelected(btServiceCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.SERVICE_CHECK, ErrorCheckPreferenceConstant.SERVICE_CHECK_DEFVALUE));
			setButtonSelected(btAtomCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.ATOM_CHECK, ErrorCheckPreferenceConstant.ATOM_CHECK_DEFVALUE));
			setButtonSelected(btLogicCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.LOGIC_CHECK, ErrorCheckPreferenceConstant.LOGIC_CHECK_DEFVALUE));
			setButtonSelected(btObjectCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.PROCEDURE_CHECK, ErrorCheckPreferenceConstant.PROCEDURE_CHECK_DEFVALUE));
			setButtonSelected(btProcedureCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.PROCEDURE_CHECK, ErrorCheckPreferenceConstant.PROCEDURE_CHECK_DEFVALUE));
			setButtonSelected(btRelationeCheck,corenstanceNode.getBoolean(ErrorCheckPreferenceConstant.RELATION_CHECK, ErrorCheckPreferenceConstant.RELATION_CHECK_DEFVALUE));
			if(!btCheck.getSelection()){
				setButtonEnabled(btMetadataCheck,false);
				setButtonEnabled(btBasicdataCheck,false);
				setButtonEnabled(btDatabaseCheck,false);
				setButtonEnabled(btServiceCheck,false);
				setButtonEnabled(btAtomCheck,false);
				setButtonEnabled(btLogicCheck,false);
				setButtonEnabled(btObjectCheck,false);
				setButtonEnabled(btProcedureCheck,false);
				setButtonEnabled(btRelationeCheck,false);
			}
			
		}
	}
	
	/**
	 * 设置是否可见
	 * @param bundleId
	 * @return
	 */
	private boolean isCanVisible(String bundleId){
		return Platform.getBundle(bundleId)!=null;
	}
	
	/**
	 * 设置首选项各项值
	 * @param preferences
	 * @param name
	 * @param bt
	 */
	 private void setPreferences(IEclipsePreferences preferences,String name,Button bt){
		   if(bt!=null){
			   preferences.put(name, bt.getSelection()?"true":"false");
		   }
		   
	   }
	 /**
	  * 设置button的选择状态
	  * @param bt
	  * @param selected
	  */
	 private void setButtonSelected(Button bt,boolean selected){
		 if(bt!=null){
			 bt.setSelection(selected);
		 }
	 }
	 /**
	  * 设置button的enabled属性值
	  * @param bt
	  * @param enabled
	  */
	 private void setButtonEnabled(Button bt,boolean enabled){
		 if(bt!=null){
			 bt.setEnabled(enabled);
		 }
	 }
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		//setDescription("错误检查");
	}



}
