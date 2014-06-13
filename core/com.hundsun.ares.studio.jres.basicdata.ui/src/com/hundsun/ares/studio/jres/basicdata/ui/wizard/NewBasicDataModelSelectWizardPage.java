package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.ARESResourceCategory;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.assist.ARESResourceContentProposalHelper;
import com.hundsun.ares.studio.ui.assist.TextWithAssist;

public class NewBasicDataModelSelectWizardPage extends WizardPage {

	private Label labelSingleMaster;
	private TextWithAssist txtSingleMaster;
	
	private Label labelMSMaster;
	private TextWithAssist txtMSMaster;
	private Label labelMSSlave ;
	private TextWithAssist txtMSSlave;
	
	private Label labelMSLMaster;
	private TextWithAssist txtMSLMaster;
	private Label labelMSLSlave;
	private TextWithAssist txtMSLSlave;
	private Label labelMSLInfo;
	private TextWithAssist txtMSLInfo;
	
	private Composite compositClient;
	
	private Group groupTalbeSelect;
	private StackLayout layout;
	
	Composite compositSingle;
	Composite compositMasterSlave;
	Composite compositMasterSlaveLink;

	protected NewBasicDataModelSelectWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		compositClient = new Composite(parent, SWT.NONE);

		groupTalbeSelect = new Group(compositClient, SWT.NONE);
		groupTalbeSelect.setText("定义模型选择");

		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		ARESResourceContentProposalHelper helper = new ARESResourceContentProposalHelper();
		BasicDataWizardContentProposalProvider proposalProvider = new BasicDataWizardContentProposalProvider(helper);
		proposalProvider.updateContent(wizard);
		
		 compositSingle = new Composite(groupTalbeSelect, SWT.NONE);
		labelSingleMaster = new Label(compositSingle, SWT.NONE);
		labelSingleMaster.setText("主表：");
		
		txtSingleMaster = new TextWithAssist(compositSingle);
		txtSingleMaster.getAdapter().setContentProposalProvider(proposalProvider);
		txtSingleMaster.getAdapter().setAutoActivationDelay(100);
		txtSingleMaster.setAutoActivation(true);
		createExplorerButton(compositSingle, txtSingleMaster);
		
		
		 compositMasterSlave = new Composite(groupTalbeSelect, SWT.NONE);
		
		labelMSMaster = new Label(compositMasterSlave, SWT.NONE);
		labelMSMaster.setText("主表：");
		txtMSMaster = new TextWithAssist(compositMasterSlave);
		txtMSMaster.getAdapter().setContentProposalProvider(proposalProvider);
		txtMSMaster.getAdapter().setAutoActivationDelay(100);
		txtMSMaster.setAutoActivation(true);
		createExplorerButton(compositMasterSlave, txtMSMaster);
		
		labelMSSlave = new Label(compositMasterSlave, SWT.NONE);
		labelMSSlave.setText("从表：");
		txtMSSlave = createAssistedText(compositMasterSlave, proposalProvider);
		createExplorerButton(compositMasterSlave, txtMSSlave);
		
		 compositMasterSlaveLink = new Composite(groupTalbeSelect, SWT.NONE);
		
		labelMSLMaster = new Label(compositMasterSlaveLink, SWT.NONE);
		labelMSLMaster.setText("主表：");
		txtMSLMaster = createAssistedText(compositMasterSlaveLink, proposalProvider);
		createExplorerButton(compositMasterSlaveLink, txtMSLMaster);

		labelMSLSlave = new Label(compositMasterSlaveLink, SWT.NONE);
		labelMSLSlave.setText("关联表：");
		txtMSLSlave = createAssistedText(compositMasterSlaveLink, proposalProvider);
		createExplorerButton(compositMasterSlaveLink, txtMSLSlave);

		labelMSLInfo = new Label(compositMasterSlaveLink, SWT.NONE);
		labelMSLInfo.setText("信息表：");
		txtMSLInfo = createAssistedText(compositMasterSlaveLink, proposalProvider);
		createExplorerButton(compositMasterSlaveLink, txtMSLInfo);

		//添加布局
		compositClient.setLayout(new GridLayout(1, true));
		//groupTalbeSelect.setLayout(new GridLayout(2, false));

		layout = new StackLayout();
		groupTalbeSelect.setLayout(layout);
		
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		.grab(true, true).applyTo(groupTalbeSelect);
		
		compositSingle.setLayout(new GridLayout(3, false));
		compositMasterSlave.setLayout(new GridLayout(3, false));
		compositMasterSlaveLink.setLayout(new GridLayout(3, false));
		
		//二维表
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtSingleMaster);

		//主从表
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtMSMaster);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtMSSlave);

		
		//主从信息表
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtMSLMaster);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtMSLSlave);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.BEGINNING)
		.grab(true, false).span(1, 1).applyTo(txtMSLInfo);

		
		
		ModifyListener txtModifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				writeModel();
			}
		};
		
		//
		txtSingleMaster.addModifyListener(txtModifyListener);

		//
		txtMSMaster.addModifyListener(txtModifyListener);
		txtMSSlave.addModifyListener(txtModifyListener);
		
		//
		txtMSLMaster.addModifyListener(txtModifyListener);
		txtMSLSlave.addModifyListener(txtModifyListener);
		txtMSLInfo.addModifyListener(txtModifyListener);
		
		//初始化
		activeMode(wizard.modeDfine.mode);
		
		//设定控件
		setControl(compositClient);
	}
	
	private TextWithAssist createAssistedText(Composite parent, IContentProposalProvider proposalProvider) {
		TextWithAssist txtWithAssist = new TextWithAssist(parent);
		txtWithAssist.getAdapter().setContentProposalProvider(proposalProvider);
		txtWithAssist.getAdapter().setAutoActivationDelay(100);
		txtWithAssist.setAutoActivation(true);
		return txtWithAssist;
	}
	
	private void createExplorerButton(Composite parent, final TextWithAssist text) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText("浏览...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommonElementContentProvider cp = new CommonElementContentProvider(false);
				ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(), new CommonElementLabelProvider(cp), cp) {
				    protected TreeViewer createTreeViewer(Composite parent) {
				    	TreeViewer viewer = super.createTreeViewer(parent);
				    	viewer.expandToLevel(2);
				    	return viewer;
				    }
				};
				
				final NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
				dialog.setInput(wizard.getProject());
				dialog.setTitle("选择资源");
				
				ResourceTypeMapping mapping = ResourceTypeMapping.getInstance();
				final String resType = mapping.getResourceType(wizard.modeDfine.inputType);
				
				// 如果当前表不为空，尝试自动选中
				String initName = text.getText();
				if (!StringUtils.isEmpty(initName)) {
					ReferenceManager manager = ReferenceManager.getInstance();
					ReferenceInfo ref = manager.getFirstReferenceInfo(wizard.getProject(), wizard.modeDfine.inputType, initName, true);
					if (ref != null) {
						dialog.setInitialSelection(ref.getResource());
					}
				}
				
				dialog.addFilter(new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof IARESModuleRoot) {
							IARESModuleRoot root = (IARESModuleRoot) element;
							ModuleRootType2ResTypeMap reg = ModuleRootType2ResTypeMap.getInstance();
							if (reg.isAllowed(root.getType(), resType)) {
								return true;
							}
							return false;
						} else if (element instanceof IARESResource) {
							IARESResource res = (IARESResource) element;
							return StringUtils.equals(resType, res.getType());
						} else if (element instanceof ARESResourceCategory) {
							ARESResourceCategory cate = (ARESResourceCategory) element;
							return cate.isResTypeAllowed(resType);
						} else if (element instanceof IProjectProperty) {
							return false;
						}
						return true;
					}
				});
				dialog.setAllowMultiple(false);
				dialog.setEmptyListMessage("请选择一个资源");
				dialog.setValidator(new ISelectionStatusValidator() {
					@Override
					public IStatus validate(Object[] selection) {
						if (selection.length == 0) {
							return newErrorStatus("必须选择一个资源");
						}
						if (selection.length > 1) {
							return newErrorStatus("不能选择多个");
						}
						Object first = selection[0];
						if (!(first instanceof IARESResource)) {
							return newErrorStatus("只能选择资源");
						}
						return Status.OK_STATUS;
					}
				});
				if (dialog.open() == Dialog.OK) {
					IARESResource res = (IARESResource) dialog.getFirstResult();
					text.setText(res.getName());
				}
			}
			
			private Status newErrorStatus(String msg) {
				return new Status(IStatus.ERROR, BasicDataUI.PLUGIN_ID, msg);
			}
		});
	}

	/**
	 * 激活模式
	 * 
	 * @param mode
	 */
	private void activeMode(int mode) {
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		wizard.modeDfine.mode = mode;
		
		switch (mode) {
		case NewBasicDataWizard.MODE_SINGLE:
			layout.topControl = compositSingle;
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVE:
			layout.topControl = compositMasterSlave;
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVELINK:
			layout.topControl = compositMasterSlaveLink;
			break;
		default:
			break;
		}
		groupTalbeSelect.layout();
	}

	/**
	 * 写信息到向导模型中
	 */
	private void writeModel() {
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		switch (wizard.modeDfine.mode) {
		case NewBasicDataWizard.MODE_SINGLE:
			wizard.modeDfine.single_masterTable = txtSingleMaster.getControl().getText();
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVE:
			wizard.modeDfine.MS_masterTable= txtMSMaster.getText();
			wizard.modeDfine.MS_slaveTable = txtMSSlave.getText();
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVELINK:
			wizard.modeDfine.MSL_masterTable = txtMSLMaster.getText();
			wizard.modeDfine.MSL_slaveTable = txtMSLSlave.getText();
			wizard.modeDfine.MSL_linkTable = txtMSLInfo.getText();
			break;

		default:
			break;
		}
		
		updatePageComplete();
		
	}
	
	private void updatePageComplete(){
		setPageComplete(false); //设定page未完成
		setErrorMessage(null);
		
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		String basicDataType = wizard.modeDfine.inputType;
		
		String txtContent;
		switch (wizard.modeDfine.mode) {
		case NewBasicDataWizard.MODE_SINGLE:
			 txtContent = txtSingleMaster.getControl().getText();
			 if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelSingleMaster.getText(),txtContent));
				return;
			}
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVE:
			txtContent = txtMSMaster.getText();
			if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelMSMaster.getText(),txtContent));
				return;
			}
			txtContent = txtMSSlave.getText();
			if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelMSSlave.getText(),txtContent));
				return;
			}
			break;
		case NewBasicDataWizard.MODE_MASTERSLAVELINK:
			txtContent = txtMSLMaster.getText();
			if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelMSLMaster.getText(),txtContent));
				return;
			}
			txtContent = txtMSLSlave.getText();
			if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelMSLSlave.getText(),txtContent));
				return;
			}
			txtContent = txtMSLInfo.getText();
			if(ReferenceManager.getInstance().getFirstReferenceInfo(wizard.getProject(), basicDataType, txtContent, true) == null){
				setErrorMessage(String.format("[%s]所关联资源[%s]不存在", labelMSLInfo.getText(),txtContent));
				return;
			}
		default:
			break;
		}
		
		
		
		setPageComplete(true);
	}
	
	public void update(){
		NewBasicDataWizard wizard = (NewBasicDataWizard) getWizard();
		activeMode(wizard.modeDfine.mode);
		
		updatePageComplete();
	}
	
	@Override
	public IWizardPage getNextPage() {
		return null;
	}

}
