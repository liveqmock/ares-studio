/**
 * 源程序名称：OracleUserBasicPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
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

import com.hundsun.ares.studio.jres.database.oracle.ui.editors.blocks.OracleUserBlock;
import com.hundsun.ares.studio.jres.database.oracle.ui.editors.blocks.OracleUserPrivilegeBlock;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;

/**
 * @author wangbin
 */
public class OracleUserBasicPage extends EMFFormPage {
	
	//Block
	private OracleUserBlock userListBlock;
	private OracleUserPrivilegeBlock userPowerBlock;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public OracleUserBasicPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	/**
	 * 创建Oracle用户列表区域
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createColumnSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("Oracle用户列表");
		userListBlock = new OracleUserBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		userListBlock.setEditableControl(getEditableControl());
		userListBlock.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(userListBlock);
		addPropertyListener(userListBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(userListBlock);
		
		section.setClient(userListBlock.getControl());
		return section;
	}
	
	/**
	 * 创建Oracle用户权限区域
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createIndexSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("Oracle用户权限");
		userPowerBlock = new OracleUserPrivilegeBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		userPowerBlock.setEditableControl(getEditableControl());
		userPowerBlock.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(userPowerBlock);
		
		addPropertyListener(userPowerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(userPowerBlock);
		
		section.setClient(userPowerBlock.getControl());
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
		userListBlock.setInput(getInfo());
		userPowerBlock.setInput(getInfo());
		super.infoChange();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#dispose()
	 */
	@Override
	public void dispose() {
		removePropertyListener(userListBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(userListBlock);
		
		removePropertyListener(userPowerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(userPowerBlock);
		super.dispose();
	}

}
