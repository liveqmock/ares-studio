/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.internal.useroption.control.ControlManager;
import com.hundsun.ares.studio.jres.script.internal.useroption.control.IUserOptionControlProvider;
import com.hundsun.ares.studio.ui.ARESElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;


/**
 * FIXME 生成界面对话框
 * 根据xml配置动态生成用户界面
 * 现提供CHECK,RADIO,TEXT,COMBO几种控件的生成
 * @author maxh
 * @version 1.0
 * @history
 */
public class UserOptionDialog extends org.eclipse.jface.dialogs.Dialog {
	UserOptionRoot optionRoot;
	IARESProject project;

	public UserOptionDialog (Shell parentShell, UserOptionRoot xmlDialog , IARESProject project) {
		super (parentShell);
		this.optionRoot = xmlDialog;
		this.project = project;
	}

	protected void setShellStyle(int newShellStyle) { 
		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.MAX); 
	}
	
	
	private void create(Composite parent,IControlContainer container){
		
		for(IControl contrl:container.getChildren()){
			
			if(StringUtils.equals(IControl.TYPE_GROUP, contrl.getType())){
				Group menuGroup = new Group (parent, SWT.NONE);
				menuGroup.setVisible (true);
				if (((IControlContainer)contrl).getChildren().length / 4 > 1) {
					menuGroup.setLayout (new GridLayout(4, true));
				}else {
					menuGroup.setLayout (new GridLayout(4, false));
				}
				menuGroup.setText (contrl.getText());
				
				/**
				 * 因为有RADIO的原因，group必须先创建子控件再调用setControl
				 */
				
				create(menuGroup, (IControlContainer)contrl);
				contrl.setControl(menuGroup);
			} else if(StringUtils.equals(IControl.TYPE_MODULE, contrl.getType())){
				createRadioModuleCom(contrl, parent);
			} else if(StringUtils.equals(IControl.TYPE_TEXT, contrl.getType())){
				Label lable = new Label (parent, SWT.NONE);
				lable.setText(contrl.getText());
				Text text = new Text(parent, SWT.NONE);
				contrl.setControl(text);
			} else if(StringUtils.equals(IControl.TYPE_CHECK, contrl.getType())){
				Button btn = new Button (parent, SWT.CHECK);
				btn.setText (contrl.getText());
				contrl.setControl(btn);
			} else if(StringUtils.equals(IControl.TYPE_RADIO, contrl.getType())){
				Button btn = new Button (parent, SWT.RADIO);
				btn.setText (contrl.getText());
				contrl.setControl(btn);
			} else if(StringUtils.equals(IControl.TYPE_COMBO, contrl.getType())){
				Label lable = new Label (parent, SWT.NONE);
				lable.setText(contrl.getText());
				Combo combo = new Combo(parent, SWT.READ_ONLY);
				contrl.setControl(combo);
			} else {
				// 默认的使用扩展点来创建
				IUserOptionControlProvider provider = ControlManager.getInstance().getTypeProvider(contrl.getType());
				if (provider != null) {
					Control uiControl = provider.createUIControl(parent, contrl, project);
					contrl.setControl(uiControl);
				}
			}
		}
	}
	

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea (parent);
		
		create(composite, optionRoot);
		return composite;
	}

	@Override
	protected void okPressed() {
		optionRoot.setOptionValue();
		super.okPressed ();
	}
	
	@Override
	public Shell getShell() {
		Shell shell = super.getShell();
		shell.setText("用户输入");
		return shell;
	}
	
	private void createRadioModuleCom(final IControl contrl ,final Composite parent){
		Label lable = new Label (parent, SWT.NONE);
		lable.setText(contrl.getText());
		final Text text = new Text(parent, SWT.NONE);
		Button button = new Button(parent, SWT.BUTTON2);
		button.setText("浏览");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(parent);
		GridDataFactory.swtDefaults().applyTo(button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				ARESElementContentProvider cp = new ARESElementContentProvider(){

					@Override
					protected Object[] getModuleRootChildren(
							IARESModuleRoot root) throws ARESModelException {
						List<IARESModule> modules = new ArrayList<IARESModule>();
						for (IARESModule module : root.getModules()) {
							if (StringUtils.isNotBlank(module.getElementName()) && StringUtils.indexOf(module.getElementName(), ".") == -1) {
								modules.add(module);
							}
						}
						return modules.toArray(new IARESModule[0]);
					}
					
					@Override
					protected Object[] getModuleChildren(IARESModule module)
							throws ARESModelException {
						String moduleName = module.getElementName();
						List<IARESModule> reMods = new ArrayList<IARESModule>();
						for(IARESModule sm : module.getSubModules()){
							if (StringUtils.split(sm.getElementName(), ".").length == StringUtils.split(moduleName , ".").length+1) {
								reMods.add(sm);
							}
						}
						return reMods.toArray(new IARESModule[0]);
					}
					
					@Override
					protected Object[] getProjectChildren(IARESProject project) {
						try {
							return new Object[]{project.getModuleRoot(contrl.getModuleRoot())};
						} catch (ARESModelException e) {
							e.printStackTrace();
						}
						return null;
					}
				};
				
				if (StringUtils.equals(contrl.getControlType(), IControl.TYPE_CHECK)) {
					CheckedTreeSelectionDialog checkDialog = new CheckedTreeSelectionDialog(
							parent.getShell(),
							new CommonElementLabelProvider(null),//WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
							cp){
						protected CheckboxTreeViewer createTreeViewer(Composite parent) {
							CheckboxTreeViewer tv = super.createTreeViewer(parent);
							tv.addCheckStateListener(new CheckStateListener(tv));
							return tv;
						}
					};
					checkDialog.setInput(project);
					int returnValue = checkDialog.open();
					if (Dialog.OK == returnValue) {
						Object[] result = checkDialog.getResult();
						List<String> mn = new ArrayList<String>();
						for (Object obj : result) {
							if(obj instanceof IARESModule){
								mn.add(((IARESModule) obj).getElementName());
							}
						}
						text.setText(StringUtils.join(mn, ","));
					}
				}else {
					ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
							parent.getShell(),
							new CommonElementLabelProvider(null),//WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
							cp);//new WorkbenchContentProvider());
					fileDialog.setAllowMultiple(false);
					fileDialog.setInput(project);
					int returnValue = fileDialog.open();
					if (Dialog.OK == returnValue) {
						Object[] result = fileDialog.getResult();
						List<String> mn = new ArrayList<String>();
						for (Object obj : result) {
							if(obj instanceof IARESModule){
								mn.add(((IARESModule) obj).getElementName());
							}
						}
						text.setText(StringUtils.join(mn, ","));
					}
				}
			}
		});
		contrl.setControl(text);
	}
	
	class CheckStateListener implements ICheckStateListener {

		private CheckboxTreeViewer tv;
		
		protected CheckStateListener(CheckboxTreeViewer tv){
			this.tv = tv;
		}
		
		public void checkStateChanged(CheckStateChangedEvent event) {
			Object thisObj = event.getElement();
			tv.setSubtreeChecked(thisObj, event.getChecked());
		}
		
	}
	
}
