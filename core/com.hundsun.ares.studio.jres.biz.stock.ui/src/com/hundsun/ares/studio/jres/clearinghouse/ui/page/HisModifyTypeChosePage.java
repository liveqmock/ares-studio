/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.ui.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.ui.util.HisModifyWizardFactory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.util.HisModifyWizardFactory.MODIFYACTION_TYPE;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author qinyuan
 *
 */
public class HisModifyTypeChosePage extends WizardPage{

	EMFFormEditor editorPart;
	
	//修改类型
	Combo comboVersion;
	
	//详细修改信息界面
	Composite detailComp;
	
	//修订记录的action
	Modification action = ChouseFactory.eINSTANCE.createModification();
	
	IARESResource resource;
	
	//保存所有修改类型对应的修改信息界面
	List<Composite>detailComps = new ArrayList<Composite>();
	
	//保存修改后的modifyaction信息
	Modification result;
	
	//选中的修改信息界面
	Composite topComp;
	
	//选中的修改类型
	MODIFYACTION_TYPE topVersion;
	
	//所有修改类型
	final MODIFYACTION_TYPE[] values = HisModifyWizardFactory.MODIFYACTION_TYPE.values();
	
	final StackLayout layout=new StackLayout();
	
	/**
	 * @param pageName
	 */
	public HisModifyTypeChosePage(String pageName,EMFFormEditor editorPart) {
		super(pageName);
		setTitle(pageName);
		setDescription("请选择需要修改类型");
		this.editorPart = editorPart;
		resource = editorPart.getARESResource();
	}

	
	public IWizardComposite getWizardComposite() {
		return (IWizardComposite) topComp;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		
		Composite verComp = new Composite(parent, SWT.BORDER);
		
		creatVersionComposite(verComp,action);
		
		detailComp = new Composite(verComp, SWT.BORDER);
		detailComp.setLayout(layout);
		
		
		for(int i =0;i<values.length;i++){
			MODIFYACTION_TYPE select = values[i];
			detailComps.add(HisModifyWizardFactory.getDetailComposite(select,detailComp,action,editorPart));
		}
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(verComp);
		GridDataFactory.fillDefaults().hint(800, 400).span(2, 1).grab(true, true).applyTo(detailComp);
		
		setControl(verComp);
	}
	
	/**
	 * @param verComp
	 * @param action 
	 */
	private void creatVersionComposite(Composite verComp, Modification action) {

		String version = HisModifyWizardFactory.getActionName(action);
		verComp.setLayout(new GridLayout(2, false));
		Label lbVersion = new Label(verComp, SWT.None);
		lbVersion.setText("请选择修改类型：");
		comboVersion = new Combo(verComp, SWT.BORDER | SWT.READ_ONLY);
		
		int select = 0;
		comboVersion.removeAll();
		for(int i = 0; i<values.length; i++){
			comboVersion.add(values[i].name(), i);
			if(version != null && version.equals(values[i].name())){
				select = i;
			}
		}
		comboVersion.select(select);
		
		GridDataFactory.fillDefaults().grab(false, true).applyTo(lbVersion);
		GridDataFactory.fillDefaults().grab(false, true).applyTo(comboVersion);
		
		//根据所选的修改类型显示相应的界面
		comboVersion.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				int i = comboVersion.getSelectionIndex();
				topComp = detailComps.get(i);
				topVersion = values[i];
				layout.topControl = topComp;
				detailComp.layout();
				
			}
		});
		
		comboVersion.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				int i = comboVersion.getSelectionIndex();
				topComp = detailComps.get(i);
				topVersion = values[i];
				layout.topControl = topComp;
				detailComp.layout();
			}
		});
	}
	
}
