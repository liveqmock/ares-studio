/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.biz.ui.block.InputParameterBlock;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procedure.ui.editor.page.block.RelatedInfoBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedInfoPage extends DataBindingFormPage{

	private RelatedInfoBlock relatedTableInfoBlock;
	private RelatedInfoBlock relatedBasicDataInfoBlock;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ProcedureRelatedInfoPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
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
		
		final SashForm sf = new SashForm(composite, SWT.VERTICAL);
		TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB);
		twd.heightHint = 650;
		sf.setLayoutData(twd);
		sf.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		final Section input = createRelatedTableInfoSection(sf, toolkit);
		final Section output = createRelatedBasicDataInfoSection(sf, toolkit);
		
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		composite.getParent().layout();
	}
	
	protected Section createRelatedTableInfoSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("关联表信息");
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite block = toolkit.createComposite(content);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());
		
		// 列表
		relatedTableInfoBlock = new RelatedInfoBlock(ProcdurePackage.Literals.PROCEDURE__RELATED_TABLE_INFO,
				getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		relatedTableInfoBlock.setEditableControl(getEditableControl());
		relatedTableInfoBlock.createControl(block, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(relatedTableInfoBlock);
		
		addPropertyListener(relatedTableInfoBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(relatedTableInfoBlock);
		
		section.setClient(content);
		return section;
	}
	
	protected Section createRelatedBasicDataInfoSection(Composite composite, FormToolkit toolkit) {
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("关联基础数据信息");
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite block = toolkit.createComposite(content);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(block);
		block.setLayout(new FillLayout());
		
		// 列表
		relatedBasicDataInfoBlock = new RelatedInfoBlock(ProcdurePackage.Literals.PROCEDURE__RELATED_BASIC_DATA_INFO,
				getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		relatedBasicDataInfoBlock.setEditableControl(getEditableControl());
		relatedBasicDataInfoBlock.createControl(block, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(relatedBasicDataInfoBlock);
		
		addPropertyListener(relatedBasicDataInfoBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(relatedBasicDataInfoBlock);
		
		section.setClient(content);
		return section;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		
		if ( relatedTableInfoBlock != null ) {
			relatedTableInfoBlock.setInput(getInfo());
		}
		if ( relatedBasicDataInfoBlock != null ) {
			relatedBasicDataInfoBlock.setInput(getInfo());
		}
		super.infoChange();
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#dispose()
	 */
	@Override
	public void dispose() {
		removePropertyListener(relatedTableInfoBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(relatedTableInfoBlock);
		
		removePropertyListener(relatedBasicDataInfoBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(relatedBasicDataInfoBlock);
		
		super.dispose();
	}
}
