/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

/**
 * @author qinyuan
 *
 */
public class ProcedureExtendPageForStockTwo extends DataBindingFormPage {

	private int[] weights = new int[]{15,15};
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ProcedureExtendPageForStockTwo(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		createBasicInfoSection(composite,toolkit);
		
		final SashForm sf = new SashForm(composite, SWT.VERTICAL);
		TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB);
		twd.heightHint = 650;
		sf.setLayoutData(twd);
		sf.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		final Section before = createBeforeCodeSection(sf, toolkit);
		final Section after = createAfterCodeSection(sf, toolkit);
		
		sf.setWeights(weights);
		final int unit = weights[0];
		
		before.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if( !after.isExpanded()){
						//展开前输入输出内部都为不展开状态，此时内部占用的空间为展开时的大小
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]/unit});
					}else{
						//将输入占用大小置为展开状态
						sf.setWeights(new int[]{weights[0]*unit,weights[1],weights[2]});
					}
				}else if(!before.isExpanded() && !after.isExpanded() ){
					//输入输出内部为不展开状态，将内部占用大小设置为展开时的大小
					sf.setWeights(new int[]{1,1,unit});
				}else{
					//将输入占用大小置为非展开状态
					sf.setWeights(new int[]{weights[0]/unit,weights[1],weights[2]});
				}
			}
		});
		
		after.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				int[] weights = sf.getWeights();
				if(e.getState()){
					if(!before.isExpanded() ){
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]/unit});
					}else{
						sf.setWeights(new int[]{weights[0],weights[1]*unit,weights[2]});
					}
				}else if(!before.isExpanded() && !after.isExpanded() ){
					sf.setWeights(new int[]{1,1,unit});
				}else{
					sf.setWeights(new int[]{weights[0],weights[1]/unit,weights[2]});
				}
			}
		});
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		composite.getParent().layout();
	}

	//基本信息section
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "返回类型：");
		Text returnType = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lbDefineType = toolkit.createLabel(client, "定义类型：");
		Combo DefineType = new Combo(client, SWT.BORDER);
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(returnType);
		
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbDefineType);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(DefineType);
		
		section.setClient(client);
		return section;
	}
	
	//前置代码section
	protected Section createBeforeCodeSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("前置代码");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "前置代码：");
		Text returnType = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultTreeStyles());
		
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(returnType);
		
		section.setClient(client);
		return section;
	}
	
	//后置代码section
	protected Section createAfterCodeSection(Composite composite,
			FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("后置代码");
		section.setExpanded(true);
		
		// 创建控件
		Composite client = toolkit.createComposite(section);
		
		Label lbID = toolkit.createLabel(client, "后置代码：");
		Text returnType = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultTreeStyles());
		
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(returnType));
		
		// 布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbID);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(returnType);
		
		section.setClient(client);
		return section;
	}
}
