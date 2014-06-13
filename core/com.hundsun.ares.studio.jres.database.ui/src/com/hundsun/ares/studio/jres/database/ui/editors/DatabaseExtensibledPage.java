/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import java.util.EventObject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;

/**
 * 数据库资源，用户属性的扩展页面
 * 
 * @author yanwj06282
 */
public class DatabaseExtensibledPage extends DataBindingFormPage{
	
	private ExtensibleModelComposite emc;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DatabaseExtensibledPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
	}
	
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		Section extendedSection = createExtendedInfoSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendedSection);
	}

	@Override
	protected void doDataBingingOnControls() {
	}
	
	@Override
	public void infoChange() {
		emc.setInput(getEditor().getARESResource(), getInfo());
		super.infoChange();
		
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}
	
	protected Section createExtendedInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("扩展信息");
		
		emc = new ExtensibleModelComposite(section, toolkit);
		emc.setProblemPool(null);
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(emc));
		
		section.setClient(emc);
		return section;
	}
	
}
