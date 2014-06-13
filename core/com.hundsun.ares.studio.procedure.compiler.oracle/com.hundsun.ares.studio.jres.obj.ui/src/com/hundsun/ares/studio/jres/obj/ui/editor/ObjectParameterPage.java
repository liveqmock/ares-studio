/**
 * 
 */
package com.hundsun.ares.studio.jres.obj.ui.editor;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.biz.ui.block.ObjectPropertyBlock;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;

/**
 * @author yanwj06282
 *
 */
public class ObjectParameterPage extends EMFFormPage {

	private ObjectPropertyBlock columns;
	//SashForm sash;
	
	public ObjectParameterPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		GridLayoutFactory.fillDefaults().applyTo(composite);
		
		
		createColumnSection(composite,toolkit);

		
	}

	@Override
	public void infoChange() {
		columns.setInput(getInfo());
		super.infoChange();
	}
	
	private Section createColumnSection(Composite parent,FormToolkit toolkit) {
		Composite plCom = toolkit.createComposite(parent,SWT.BORDER);
		GridLayoutFactory.fillDefaults().applyTo(plCom);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(plCom);
		Section section = toolkit.createSection(plCom, FormWidgetUtils.getDefaultSectionStyles());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);
		section.setText("属性列表");
		columns = new ObjectPropertyBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		// 2013-8-19 sundl 有CRES nature的情况下，对象编辑器中添加“参数组”按钮
		IARESResource res = getEditor().getARESResource();
		if (res != null) {
			IARESProject project = res.getARESProject();
			if (BizUtil.hasCRESNature(project.getProject())) {
				columns.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
						   IBizActionIDConstants.ADD_NON_STD_FIELD_PARME,
						   IBizActionIDConstants.ADD_OBJECT_PARAM,
						   IBizActionIDConstants.ADD_PARAM_GROUP});
			}
		}
		columns.setEditableControl(getEditableControl());
		columns.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(columns);
		
		addPropertyListener(columns);
		getEditingDomain().getCommandStack().addCommandStackListener(columns);
		
		section.setClient(columns.getControl());
		return section;
	}

	
}
