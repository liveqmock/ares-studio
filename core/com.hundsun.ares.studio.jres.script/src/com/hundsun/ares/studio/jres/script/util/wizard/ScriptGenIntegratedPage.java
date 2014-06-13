/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author liaogc
 *
 */
public class ScriptGenIntegratedPage extends WizardPage{
	
	private CheckboxTreeViewerEx viewer;
	private Button btnSelectAll;
	private Button btnSelectNone;
	private List<ScriptGenInteWizardModel> selectedHasXmlConfig = new ArrayList<ScriptGenInteWizardModel>();//有配置的脚本
	private List<ScriptGenInteWizardModel>  selectedElements = new ArrayList<ScriptGenInteWizardModel>();//选择的脚本
	private List<ScriptGenInteWizardModel> inputElements = new ArrayList<ScriptGenInteWizardModel>();//所有的脚本

	protected ScriptGenIntegratedPage(String pageName,List<ScriptGenInteWizardModel>inputElements) {
		super(pageName);
		setTitle("选择文件");
		setMessage("选择需要生成的文件");
		this.inputElements = inputElements;
	}

	@Override
	public void createControl(Composite parent) {
		Composite comps = new Composite(parent, SWT.None);
       FormToolkit toolkit = new FormToolkit(comps.getDisplay());
		viewer = new CheckboxTreeViewerEx(comps);
		viewer.setContentProvider(new ScriptGenInteWizardContentProvider());
		viewer.setLabelProvider(new ScriptGenInteWizardLabelProvider());
		viewer.setInput(inputElements);
		
		Composite btnComps = toolkit.createComposite(comps);
		btnSelectAll = toolkit.createButton(btnComps, "全选", SWT.None);
		
		btnSelectNone = toolkit.createButton(btnComps, "取消全选", SWT.None);
		
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(comps);
		
		GridDataFactory.fillDefaults().span(2, -1).grab(true, true).applyTo(viewer.getControl());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(btnComps);
		
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(btnComps);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(btnSelectAll);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(btnSelectNone);
		
		setControl(comps);
		
		viewer.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				refreshFinishState();
			}
		});
		btnSelectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(ScriptGenInteWizardModel  node :inputElements){
					viewer.setSubtreeChecked(node, true);
				}
				refreshFinishState();
			}
		});
		btnSelectNone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(ScriptGenInteWizardModel  node :inputElements){
					viewer.setSubtreeChecked(node, false);
				}
				refreshFinishState();
			}
		});
	    refreshFinishState();
	}

	protected void refreshFinishState() {
		selectedElements.clear();
		this.selectedHasXmlConfig.clear();
		Object[] selections = viewer.getCheckedElements();
		for(Object sel : selections){
			if(sel instanceof ScriptGenInteWizardModel ){
				ScriptGenInteWizardModel model = (ScriptGenInteWizardModel)sel;
				if(!this.selectedElements.contains(model) && model.getRoot()!=null){
					selectedHasXmlConfig.add(model);
				}
				selectedElements.add(model);
				
			}
		}
		if((ScriptGenIntegratedDetailPage)getNextPage()!=null){
			((ScriptGenIntegratedWizard)this.getWizard()).removePage((ScriptGenIntegratedDetailPage)getNextPage());
			ScriptGenIntegratedDetailPage detailPage =  new ScriptGenIntegratedDetailPage("生成详细信息",((ScriptGenIntegratedWizard)this.getWizard()).getProject());
			((ScriptGenIntegratedWizard)this.getWizard()).setDetailPage(detailPage);
			((ScriptGenIntegratedWizard)this.getWizard()).addPage(detailPage);
			((ScriptGenIntegratedDetailPage)getNextPage()).setSelectedHasXmlConfig(selectedHasXmlConfig);
		}
		
		setPageComplete(true);
	}
	
	
	@Override
	public boolean canFlipToNextPage() {
		return selectedHasXmlConfig.size() > 0;
	}
	/**
	 * @return the selectedHasXmlConfig
	 */
	public List<ScriptGenInteWizardModel> getSelectedHasXmlConfig() {
		return selectedHasXmlConfig;
	}

	/**
	 * @param selectedHasXmlConfig the selectedHasXmlConfig to set
	 */
	public void setSelectedHasXmlConfig(
			List<ScriptGenInteWizardModel> selectedHasXmlConfig) {
		this.selectedHasXmlConfig = selectedHasXmlConfig;
	}
	/**
	 * @return the selectedElements
	 */
	public List<ScriptGenInteWizardModel> getSelectedElements() {
		return selectedElements;
	}

}
