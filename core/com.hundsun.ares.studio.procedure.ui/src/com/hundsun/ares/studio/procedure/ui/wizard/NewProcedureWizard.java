package com.hundsun.ares.studio.procedure.ui.wizard;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ElementSelectionValidator;
import com.hundsun.ares.studio.ui.newwizard.IWizardPageValidator;
import com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameModuleDuplicateValidator;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameValidator;

public class NewProcedureWizard extends ModuleARESResourceNewWizard {

	private Text module;
	private String moduleProcName;
	
	public NewProcedureWizard() {
	}
	
	@Override
	protected String getResType() {
		return "procedure";
	}

	@Override
	protected void initNewResourceInfo(Object info) {
		super.initNewResourceInfo(info);
		if(info instanceof Procedure){
			Map<Object, Object> context = getContext();
			String resname = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_NAME).toString();
			String resCName = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_CNAME).toString();
			if(StringUtils.isNotBlank(resname)){
				((Procedure)info).setName(resname);
			}
			if(StringUtils.isNotBlank(resCName)){
				((Procedure)info).setChineseName(resCName);
			}
			if (StringUtils.isNotBlank(moduleProcName)) {
				try {
					IARESResource modulePro = selectedElement.getARESProject().findResource(moduleProcName, IProcedureResType.PROCEDURE);
					Procedure mp = modulePro.getInfo(Procedure.class);
					((Procedure)info).getInputParameters().addAll(mp.getInputParameters());
					((Procedure)info).getOutputParameters().addAll(mp.getOutputParameters());
					((Procedure)info).getInternalVariables().addAll(mp.getInternalVariables());
					((Procedure)info).setPseudoCode(mp.getPseudoCode());
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void addPages() {

		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getResType());		
		ARESResourceNewWizardPage page = new ARESResourceNewWizardPage("新建一个" + resDescriptor.getName(), workbench, selectedElement, getResType()){

			@Override
			protected void addValidators(List<IWizardPageValidator> validators) {
				validators.add(new ElementSelectionValidator());
				validators.add(new ReourceNameValidator());
				validators.add(new ReourceNameModuleDuplicateValidator());
			}
			
			@Override
			protected void createText(final Composite composite) {
				super.createText(composite);
				Label moduleNameLabel = new Label(composite, SWT.NONE);
				moduleNameLabel.setText("模板:");
				GridData gd = new GridData();
				moduleNameLabel.setLayoutData(gd);
				
				final Composite subCom = new Composite(composite, SWT.NONE);
				module = new Text(subCom, SWT.BORDER | SWT.READ_ONLY);
				module.addModifyListener(new ModifyListener() {
					
					@Override
					public void modifyText(ModifyEvent e) {
						moduleProcName = module.getText();
					}
				});
				Button procSlect = new Button(subCom, SWT.NONE);
				procSlect.setText("选择");
				procSlect.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							queryProcedures(subCom.getShell());
						} catch (ARESModelException e1) {
							e1.printStackTrace();
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						
					}
				});
				
				GridDataFactory.swtDefaults().applyTo(moduleNameLabel);
				GridLayoutFactory.swtDefaults().numColumns(2).applyTo(subCom);
				GridDataFactory.fillDefaults().grab(true, false).applyTo(subCom);
				GridDataFactory.fillDefaults().grab(true, false).applyTo(module);
				GridDataFactory.swtDefaults().hint(30, -1).applyTo(procSlect);
			}
			
		};
		page.setDescription("新建一个" + resDescriptor.getName());
		addPage(page);
		
		//添加可往上下文中添加内容的页面
		addContextPage(page);
	
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(ProcedureUI.PLUGIN_ID, "icons/procedure.gif").createImage());
	}

	private void queryProcedures(Shell shell) throws ARESModelException {
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, 
				new CommonElementLabelProvider(null));
		dialog.setTitle("过程模板选择");
		dialog.setMultipleSelection(false);
		dialog.setElements(selectedElement.getARESProject().getResources(new String[]{IProcedureResType.PROCEDURE}));
		if(dialog.open() == Dialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				if (result[0] instanceof IARESResource) {
					module.setText(((IARESResource)result[0]).getFullyQualifiedName());
				}
			}
		}
	}
	
}
