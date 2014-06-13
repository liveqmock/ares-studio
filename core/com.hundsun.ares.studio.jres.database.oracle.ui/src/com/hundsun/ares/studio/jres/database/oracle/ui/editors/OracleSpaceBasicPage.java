/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.jres.database.oracle.ui.editors.blocks.TableSpaceBlock;
import com.hundsun.ares.studio.jres.database.oracle.ui.editors.blocks.TableSpaceRelationBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;

/**
 * @author wangxh
 *
 */
public class OracleSpaceBasicPage extends EMFFormPage {

	//Block
	private TableSpaceBlock tableSpaceBlock;
	private TableSpaceRelationBlock tableSpaceRelationBlock;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public OracleSpaceBasicPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	/**
	 * 创建表空间列表区域
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createColumnSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("表空间列表");
		tableSpaceBlock = new TableSpaceBlock(getEditingDomain(),getSite(), getEditor().getARESResource(), getProblemPool());
		tableSpaceBlock.setEditableControl(getEditableControl());
		tableSpaceBlock.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(tableSpaceBlock);
		addPropertyListener(tableSpaceBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(tableSpaceBlock);
		
		section.setClient(tableSpaceBlock.getControl());
		return section;
	}
	
	/**
	 * 创建关联表空间区域
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createIndexSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("关联表空间");
		tableSpaceRelationBlock = new TableSpaceRelationBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		tableSpaceRelationBlock.setEditableControl(getEditableControl());
		tableSpaceRelationBlock.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(tableSpaceRelationBlock);
		addPropertyListener(tableSpaceRelationBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(tableSpaceRelationBlock);
		
		section.setClient(tableSpaceRelationBlock.getControl());
		return section;
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		
		final Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		final Section columnSection = createColumnSection(composite, toolkit);
		final Section indexSection = createIndexSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(columnSection);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(indexSection);
		
		
		
		columnSection.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
				
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				((GridData)columnSection.getLayoutData()).grabExcessVerticalSpace = columnSection.isExpanded();
				composite.layout();
			}
		});
		
		indexSection.addExpansionListener(new IExpansionListener() {
			
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
				
			}
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				((GridData)indexSection.getLayoutData()).grabExcessVerticalSpace = indexSection.isExpanded();
				composite.layout();
			}
		});

	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		tableSpaceBlock.setInput(getInfo());
		tableSpaceRelationBlock.setInput(getInfo());
		super.infoChange();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#dispose()
	 */
	@Override
	public void dispose() {
		removePropertyListener(tableSpaceBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(tableSpaceBlock);
		
		removePropertyListener(tableSpaceRelationBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(tableSpaceRelationBlock);
		super.dispose();
	}

}
