/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.internal.ui.editor.ExtensionFieldEditorDescriptor;
import com.hundsun.ares.studio.internal.ui.editor.ExtensionFieldEditorRegistry;
import com.hundsun.ares.studio.ui.control.ControlWithChecker;
import com.hundsun.ares.studio.ui.control.TextAdaptor;
import com.hundsun.ares.studio.ui.page.SectionScrolledFormPage;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 项目属性编辑器Overview页面。
 * @author sundl
 */
public class ProjectOverviewPage extends SectionScrolledFormPage<ARESProjectProperty> {

	public ProjectOverviewPage(FormEditor editor) {
		super(editor, "overview", "Overview");		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.SectionScrolledFormPage#getFormLayout()
	 */
	@Override
	protected Layout getFormLayout() {
		return FormLayoutFactory.createFormPaneTableWrapLayout(false, 2);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.SectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		// left and right part.
		Composite left = toolkit.createComposite(managedForm.getForm().getBody());
		left.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false, 1));
		left.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
					
		Composite right = toolkit.createComposite(managedForm.getForm().getBody());
		right.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false, 1));
		right.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
	
		// 'General' section
		//Section general = createCompositeSection("General", true, new GridLayout(2, false));
		
		Section general = createSectionWithTitle(left, toolkit, "基本信息", true, new TableWrapData(TableWrapData.FILL_GRAB));
		createClientInBasicInformationSection(general, managedForm);
		
//		// 'Dependencies' section
//		Section dependencies =  createSectionWithTitle(right, toolkit, "依赖", true, new TableWrapData(TableWrapData.FILL_GRAB));
//		createDependenciesSection(dependencies);
		
		// 'Extension' section
		Section extension = createSectionWithTitle(left, toolkit, "系统配置", true, new TableWrapData(TableWrapData.FILL_GRAB));
		createExtensionSection(extension);
	}

	private void createClientInBasicInformationSection(Section basicSection, final IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Composite client = toolkit.createComposite(basicSection);
		basicSection.setClient(client);
		client.setLayout(new GridLayout(2, false));
		ImporveControlWithDitryStateContext context = new ImporveControlWithDitryStateContext(client, dirty,toolkit,getUndoContext(),managedForm.getMessageManager(),info);
		TextAdaptor idText = new TextAdaptor("ID：", SWT.BORDER, context, "id"){
			@Override
			public IARESProblem check() {	
//				String id = info.getId();				
//				IStatus result = AresConventions.checkProjectId(id);
//				if (!result.isOK()) {
//					IARESProblem problem = ARESProblem.createFrom(result);
//					if (problem != null) {
//						problem.setMessage(result.getMessage());
//						return problem;
//					}
//				}
				return super.check();
			}
			
		};

		idText.setCheckModel(ControlWithChecker.MODIFY_CHECK);
		GridDataFactory.createFrom((GridData) idText.getControl().getLayoutData()).hint(100, -1).applyTo(idText.getControl());
		getEditableComponent().add(idText);
		
		TextAdaptor nameText = new TextAdaptor("名字：",SWT.BORDER,context,"name");
		GridDataFactory.createFrom((GridData) nameText.getControl().getLayoutData()).hint(100, -1).applyTo(nameText.getControl());
		getEditableComponent().add(nameText);
		
		TextAdaptor versionText = new TextAdaptor("版本：",SWT.BORDER,context,"version");
		GridDataFactory.createFrom((GridData) versionText.getControl().getLayoutData()).hint(100, -1).applyTo(versionText.getControl());
		getEditableComponent().add(versionText);
		
		TextAdaptor contactText = new TextAdaptor("联系方式：", SWT.BORDER , context, "contact");
		GridDataFactory.createFrom((GridData) versionText.getControl().getLayoutData()).hint(100, -1).applyTo(contactText.getControl());
		getEditableComponent().add(contactText);
		
		TextAdaptor noteText = new TextAdaptor("说明：",SWT.MULTI|SWT.V_SCROLL | SWT.BORDER,context,"note") {
			protected void setStyle() {
				setLabelLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
				GridData data = new GridData(GridData.FILL_BOTH);
				data.minimumHeight = 30;
				data.heightHint = 50;
				data.widthHint = 300;
				setControlLayoutData(data);
			}
		};
		getEditableComponent().add(noteText);
		
		refreshSection(basicSection);
	}
	
	class DependenciesItemLabelProvider extends LabelProvider {
		public String getText(Object element) {
			if (element instanceof IDependenceDescriptor) {
				IDependenceDescriptor desc = (IDependenceDescriptor)element;
				return desc.getId() + desc.getVersionConstraint();
			}
			return null;
		}
	}
	
	private void createExtensionSection(Section section) {
		FormToolkit toolkit = managedForm.getToolkit();
		final Composite client = toolkit.createComposite(section);
		section.setClient(client);
		client.setLayout(new GridLayout(2, false));
		
		ImporveControlWithDitryStateContext context = new ImporveControlWithDitryStateContext(client, dirty,toolkit,getUndoContext(),managedForm.getMessageManager(),info);
		ExtensionFieldEditorRegistry editorRegistry = ExtensionFieldEditorRegistry.getInstance();
		List<ExtensionFieldEditorDescriptor> editorDescriptors = new ArrayList<ExtensionFieldEditorDescriptor>(editorRegistry.getDescriptors());
		Collections.sort(editorDescriptors, new Comparator<ExtensionFieldEditorDescriptor>() {
			public int compare(ExtensionFieldEditorDescriptor o1, ExtensionFieldEditorDescriptor o2) {
				String order1 = o1.getConfigurationElement().getAttribute("order");
				String order2 = o2.getConfigurationElement().getAttribute("order");
				try {
					int oo1 = Integer.parseInt(order1);
					int oo2 = Integer.parseInt(order2);
					return oo1 - oo2;
				} catch (Exception e) {
				}
				return -1;
			}
		});
		
		for (ExtensionFieldEditorDescriptor desc : editorDescriptors) {
			ExtensionFieldEditor field = desc.createEditor();
			if (field == null)
				continue;
			
			field.init(info);
			if (field instanceof IProjectExtensibleStatus) {
				((IProjectExtensibleStatus)field).setAresProject(((ProjectPropertyEditor)getEditor()).getARESProject());
			}
			field.createControls(toolkit, context);
			getEditableComponent().add(field);
		}
		
		toolkit.paintBordersFor(client);
	}
}

