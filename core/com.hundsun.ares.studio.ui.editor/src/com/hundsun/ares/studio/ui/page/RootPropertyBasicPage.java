/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleRootProperty;
import com.hundsun.ares.studio.internal.ui.editor.CommonModelBindingTextAdapter;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;


/**
 * 
 * @author sundl
 */
public class RootPropertyBasicPage extends SectionScrolledFormPage<ModuleRootProperty>{

	public RootPropertyBasicPage(FormEditor editor) {
		super(editor, "modulepropertybasic", "基本信息");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.SectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = createSectionWithTitle(managedForm.getForm().getBody(), toolkit, "基本信息", true);
		Composite client = new Composite(section, SWT.NONE);
		section.setClient(client);
		client.setLayout(new GridLayout(2, false));
		ImporveControlWithDitryStateContext context = new ImporveControlWithDitryStateContext(client, dirty,toolkit,getUndoContext(),managedForm.getMessageManager(),info);
		CommonModelBindingTextAdapter cName = new CommonModelBindingTextAdapter("中文名:", SWT.BORDER, context, ICommonModel.CNAME, info);
		cName.syncControl();
		context.getDirtyStatus().setValue(false);
		getEditableComponent().add(cName);
		refreshSection(section);
	}

}
