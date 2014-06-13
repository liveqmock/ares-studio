/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.internal.useroption.IControl;
import com.hundsun.ares.studio.jres.script.internal.useroption.IControlContainer;
import com.hundsun.ares.studio.ui.ARESElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;

/**
 * @author liaogc
 * 统一
 */
public class ScriptGenIntegratedDetailPage extends WizardPage {
	private static final Logger console = ConsoleHelper.getLogger();
	private List<ScriptGenInteWizardModel> selectedHasXmlConfig = new ArrayList<ScriptGenInteWizardModel>();// 选择的存在配置文件的脚本

	/**
	 * @return the project
	 */
	public IARESProject getProject() {
		return project;
	}

	private IARESProject project;

	protected ScriptGenIntegratedDetailPage(String pageName,
			IARESProject project) {
		super(pageName);
		setTitle("详细信息");
		setMessage("生成详细信息设置");
		this.project = project;
	}

	@Override
	public void createControl(Composite parent) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent,  
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);  
		scrolledComposite.setExpandHorizontal(true);  
		scrolledComposite.setExpandVertical(true);  
		Composite composite = new Composite(scrolledComposite, SWT.None);
        scrolledComposite.setContent(composite);
        
        GridLayoutFactory.swtDefaults().applyTo(scrolledComposite);// 设置布局
		FormToolkit toolkit = new FormToolkit(composite.getDisplay());
		GridLayoutFactory.swtDefaults().applyTo(composite);// 设置布局
		for (ScriptGenInteWizardModel model : selectedHasXmlConfig) {
			IControlContainer container = model.getOptionRoot();
			if(container!=null){
				Section section = createSection(model, container, composite,
						toolkit);// 创建Section
				GridDataFactory.fillDefaults().grab(true, false).applyTo(section);// 为
			}
			
		}
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));  
		setControl(scrolledComposite);
	}
	

	/**
	 * @param composite
	 * @param toolkit
	 * @return
	 */
	private Section createSection(ScriptGenInteWizardModel model,
			IControlContainer container, Composite composite,
			FormToolkit toolkit) {

		Section section = toolkit.createSection(composite,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		if (StringUtils.isNotBlank(model.getScriptDesc())) {
			section.setText(model.getScriptDesc());
		}
		Composite client = toolkit.createComposite(section);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(client);
		createItem(client, container);
		section.setClient(client);

		return section;

	}

	/**
	 * 根据配置信息生成对应的界面
	 * 
	 * @param parent
	 * @param container
	 */
	private void createItem(Composite parent, IControlContainer container) {

		for (IControl contrl : container.getChildren()) {

			if (StringUtils.equals(IControl.TYPE_GROUP, contrl.getType())) {
				Group menuGroup = new Group(parent, SWT.NONE);
				menuGroup.setVisible(true);
				if (((IControlContainer) contrl).getChildren().length / 4 > 1) {
					menuGroup.setLayout(new GridLayout(4, true));
				} else {
					menuGroup.setLayout(new GridLayout(4, false));
				}
				menuGroup.setText(contrl.getText());

				/**
				 * 因为有RADIO的原因，group必须先创建子控件再调用setControl
				 */

				createItem(menuGroup, (IControlContainer) contrl);
				contrl.setControl(menuGroup);
			}

			if (StringUtils.equals(IControl.TYPE_MODULE, contrl.getType())) {
				createRadioModuleCom(contrl, parent);
			}

			if (StringUtils.equals(IControl.TYPE_TEXT, contrl.getType())) {
				Label lable = new Label(parent, SWT.NONE);
				lable.setText(contrl.getText());
				Text text = new Text(parent, SWT.BORDER);
				contrl.setControl(text);
			}

			if (StringUtils.equals(IControl.TYPE_CHECK, contrl.getType())) {
				Button btn = new Button(parent, SWT.CHECK);
				btn.setText(contrl.getText());
				contrl.setControl(btn);
			}

			if (StringUtils.equals(IControl.TYPE_RADIO, contrl.getType())) {
				Button btn = new Button(parent, SWT.RADIO);
				btn.setText(contrl.getText());
				contrl.setControl(btn);
			}

			if (StringUtils.equals(IControl.TYPE_COMBO, contrl.getType())) {
				Label lable = new Label(parent, SWT.NONE);
				lable.setText(contrl.getText());
				Combo combo = new Combo(parent, SWT.READ_ONLY);
				contrl.setControl(combo);
			}

		}
	}

	
	/**
	 * 创建单选按纽以及下拉组件
	 * 
	 * @param contrl
	 * @param parent
	 */
	private void createRadioModuleCom(final IControl contrl,
			final Composite parent) {
		Label lable = new Label(parent, SWT.NONE);
		lable.setText(contrl.getText());
		final Text text = new Text(parent, SWT.BORDER);
		Button button = new Button(parent, SWT.BUTTON2);
		button.setText("浏览");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(parent);
		GridDataFactory.swtDefaults().applyTo(button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				ARESElementContentProvider cp = new ARESElementContentProvider() {

					@Override
					protected Object[] getModuleRootChildren(
							IARESModuleRoot root) throws ARESModelException {
						List<IARESModule> modules = new ArrayList<IARESModule>();
						for (IARESModule module : root.getModules()) {
							if (StringUtils.isNotBlank(module.getElementName())
									&& StringUtils.indexOf(
											module.getElementName(), ".") == -1) {
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
						for (IARESModule sm : module.getSubModules()) {
							if (StringUtils.split(sm.getElementName(), ".").length == StringUtils
									.split(moduleName, ".").length + 1) {
								reMods.add(sm);
							}
						}
						return reMods.toArray(new IARESModule[0]);
					}

					@Override
					protected Object[] getProjectChildren(IARESProject project) {
						try {
							return new Object[] { project.getModuleRoot(contrl
									.getModuleRoot()) };
						} catch (ARESModelException e) {
							e.printStackTrace();
						}
						return null;
					}
				};

				if (StringUtils.equals(contrl.getControlType(),
						IControl.TYPE_CHECK)) {
					CheckedTreeSelectionDialog checkDialog = new CheckedTreeSelectionDialog(
							parent.getShell(), new CommonElementLabelProvider(
									null),// WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
							cp) {
						protected CheckboxTreeViewer createTreeViewer(
								Composite parent) {
							CheckboxTreeViewer tv = super
									.createTreeViewer(parent);
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
							if (obj instanceof IARESModule) {
								mn.add(((IARESModule) obj).getElementName());
							}
						}
						text.setText(StringUtils.join(mn, ","));
					}
				} else {
					ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
							parent.getShell(), new CommonElementLabelProvider(
									null),// WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
							cp);// new WorkbenchContentProvider());
					fileDialog.setAllowMultiple(false);
					fileDialog.setInput(project);
					int returnValue = fileDialog.open();
					if (Dialog.OK == returnValue) {
						Object[] result = fileDialog.getResult();
						List<String> mn = new ArrayList<String>();
						for (Object obj : result) {
							if (obj instanceof IARESModule) {
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

	/**
	 * @return the selectedHasXmlConfig
	 */
	public List<ScriptGenInteWizardModel> getSelectedHasXmlConfig() {
		return selectedHasXmlConfig;
	}

	/**
	 * @param selectedHasXmlConfig
	 *            the selectedHasXmlConfig to set
	 */
	public void setSelectedHasXmlConfig(
			List<ScriptGenInteWizardModel> selectedHasXmlConfig) {
		this.selectedHasXmlConfig = selectedHasXmlConfig;
	}


	class CheckStateListener implements ICheckStateListener {

		private CheckboxTreeViewer tv;

		protected CheckStateListener(CheckboxTreeViewer tv) {
			this.tv = tv;
		}

		public void checkStateChanged(CheckStateChangedEvent event) {
			Object thisObj = event.getElement();
			tv.setSubtreeChecked(thisObj, event.getChecked());
		}

	}

}