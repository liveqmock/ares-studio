/**
 * 源程序名称：MDModuleCommonPropertyPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.metadata.ui.pages;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.jres.model.metadata.MDModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;

/**
 * @author gongyf
 *
 */
public class MDModuleCommonPropertyPage extends EMFExtendSectionScrolledFormPage<ARESProjectProperty> {
	
	/**元数据支持引用*/
	Button btnModule;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MDModuleCommonPropertyPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		// TODO 元数据支持引用
		
		FormToolkit toolkitModule = managedForm.getToolkit();
		Section sectionModule = createSectionWithTitle(managedForm.getForm()
				.getBody(), toolkitModule, "元数据", true);
		final Composite compModule = toolkitModule.createComposite(sectionModule, SWT.NONE);
		
		btnModule = new Button(compModule, SWT.CHECK);
		
		btnModule.setText("元数据支持引用");
		
		// 设置布局
		compModule.setLayout(new GridLayout(1, false));

		//元数据支持引用
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(btnModule);
		
		sectionModule.setClient(compModule);
		toolkitModule.paintBordersFor(compModule);
		
		//数据绑定
		databinding();
	}

	@Override
	protected MDModuleCommonProperty getModel() {
		return (MDModuleCommonProperty) super.getModel();
	}
	
	/**
	 * 
	 */
	private void databinding() {
		
		IObservableValue btnModuleObserveWidget = SWTObservables.observeSelection(btnModule);
		IObservableValue btnModuleObserveValue = EMFEditObservables.observeValue(getEditingDomain(),
				getModel(), MetadataPackage.Literals.MD_MODULE_COMMON_PROPERTY__USE_REF_FEATURE);
		getBindingContext().bindValue(btnModuleObserveWidget, btnModuleObserveValue);
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
//		try {
//			if(getARESProject().getProject().hasNature(JRESCore.MODULE_NATURE)) {
//				return true;
//			}
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return MetadataPackage.Literals.MD_MODULE_COMMON_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		//return IJRESConstant.MDMODULE_COMMONPROPERTY_KEY;
		return StringUtil.EMPTY_STR;
	}

}
