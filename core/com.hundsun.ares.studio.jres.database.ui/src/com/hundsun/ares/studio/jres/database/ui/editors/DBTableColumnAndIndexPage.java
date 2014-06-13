/**
 * 源程序名称：DBTableColumnAndIndexPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.editors.blocks.TableColumnViewerBlock;
import com.hundsun.ares.studio.jres.database.ui.editors.blocks.TableIndexViewerBlock;
import com.hundsun.ares.studio.jres.database.ui.editors.blocks.TableKeyViewerBlock;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

/**
 * @author gongyf
 *
 */
public class DBTableColumnAndIndexPage extends EMFFormPage {

	private TableIndexViewerBlock tableIndex;
	private TableColumnViewerBlock tableColumn;
	private TableKeyViewerBlock tableKey;
	
	private Section columnSection;
	private Section indexSection;
	private Section keySection;
	
	private final String SASH_STATE = "sash_state-ration1";
	SashForm sash;
	
	private TriggerListener trigger = new TriggerListener() {
		
		@Override
		protected Command trigger(TransactionalEditingDomain domain,
				Notification notification) {

//			if (notification.getNotifier() instanceof TableColumn ) {
//				if ( DatabasePackage.Literals.TABLE_COLUMN__PRIMARY_KEY.equals(notification.getFeature())) {
//					final TableColumn pd = (TableColumn) notification.getNotifier();
//					final boolean pk = pd.isPrimaryKey();
//					return new RecordingCommand(domain) {
//						@Override
//						protected void doExecute() {
//							if (pk) {
//								pd.setNullable(false);
//							}
//						}
//					};
//				}
//			}
			CompoundCommand cmd = new CompoundCommand();
			if(notification.getNotifier() instanceof TableKey){
				final TableKey tableKey = (TableKey)notification.getNotifier();
				if(notification.getFeature() == DatabasePackage.Literals.TABLE_KEY__COLUMNS ||
						notification.getFeature() == DatabasePackage.Literals.TABLE_KEY__TYPE){
					for(final TableColumn col : tableKey.getColumns()){
						if(tableKey.getType().equals(key_type.PRIMARY) && col.isNullable()){
							cmd.append(new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									col.setNullable(false);
								}
							});
						}
					}
				}
				if(notification.getFeature() == DatabasePackage.Literals.TABLE_KEY__TYPE && 
						!tableKey.getType().equals(key_type.FOREIGN) &&
						!tableKey.getForeignKey().isEmpty()){
						cmd.append(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							tableKey.getForeignKey().clear();
						}
					});
				}
			}
			return cmd;
		}
	};
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DBTableColumnAndIndexPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
		getEditingDomain().addResourceSetListener(trigger);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		final Composite composite = form.getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		sash = new SashForm(composite, SWT.VERTICAL);
		TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB);
		twd.heightHint = 650;
		sash.setLayoutData(twd);
		sash.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		columnSection = createColumnSection(sash, form, toolkit);//(composite, toolkit);
		indexSection = createIndexSection(sash, form, toolkit);//(composite, toolkit);
		keySection = createKeySection(sash, form, toolkit);
		
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		//一定要再section创建后再恢复
		restoreState(getDialogSettings());
		
		columnSection.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSashWeight();
			}
		});
		
		indexSection.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSashWeight();
			}
		});
		
		keySection.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSashWeight();
			}
		});
		
		sash.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				onDispose();
			}
		});
	}
	
	/**
	 * 
	 */
	protected void refreshSashWeight() {
		int unit = 31;
		int shrinkNum = 0;
		if(!columnSection.isExpanded()){
			shrinkNum ++;
		}
		if(!indexSection.isExpanded()){
			shrinkNum ++;
		}
		if(!keySection.isExpanded()){
			shrinkNum ++;
		}
		if(shrinkNum == 3){
			sash.setWeights(new int[]{1,1,unit-3});
			return;
		}
		int columnWeight = columnSection.isExpanded() ? (unit-shrinkNum)/(3-shrinkNum) : 1;
		int indexWeight = indexSection.isExpanded() ? (unit-shrinkNum)/(3-shrinkNum) : 1;
		int keyWeight = keySection.isExpanded() ? (unit-shrinkNum)/(3-shrinkNum) : 1;
		sash.setWeights(new int[]{columnWeight,indexWeight,keyWeight});
	}

	/**
	 * 恢复上次退出时字段表格和索引表格之间的比率
	 * @param dialogSettings
	 */
	private void restoreState(IDialogSettings dialogSettings) {
		String[] weightsStr = dialogSettings.getArray(SASH_STATE);
		if(null != weightsStr) {
			int[] weights = new int[weightsStr.length];
			for (int i = 0; i < weights.length; i++) {
				weights[i] = Integer.parseInt(weightsStr[i]);
			}
			if(null != sash) {
				sash.setWeights(weights);
			}
		}
	}

	/**
	 * 
	 */
	protected void onDispose() {
		storeState(getDialogSettings());
	}
	
	/**
	 * 保存退出时字段表格和索引表格之间的比率
	 * @param dialogSettings
	 */
	private void storeState(IDialogSettings dialogSettings) {
		if(null != sash) {
			int[] weights = sash.getWeights();
			String[] weightsStr = new String[weights.length];
			for (int i = 0; i < weightsStr.length; i++) {
				weightsStr[i] = String.valueOf(weights[i]);
			}
			
			dialogSettings.put(SASH_STATE, weightsStr);
		}
	}

	private IDialogSettings getDialogSettings() {
		IDialogSettings settings = DatabaseUI.getDefault().getDialogSettings();
		IDialogSettings blockSettings = settings.getSection(DBTableColumnAndIndexPage.class.toString());
		if (blockSettings == null) {
			blockSettings = settings.addNewSection(DBTableColumnAndIndexPage.class.toString());
		}
		return blockSettings;
	}

	/**
	 * 索引
	 * @param sash
	 * @param form
	 * @param toolkit
	 * @return
	 */
	protected Section createKeySection(SashForm sash, ScrolledForm form, FormToolkit toolkit) {
		Section section = createSectionWithTitle(sash, form, toolkit, "键约束", true);
		tableKey = new TableKeyViewerBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		tableKey.setEditableControl(getEditableControl());
		tableKey.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(tableKey);
		addPropertyListener(tableKey);
		getEditingDomain().getCommandStack().addCommandStackListener(tableKey);
		
		section.setClient(tableKey.getControl());
		return section;
	}
	/**
	 * 索引
	 * @param sash
	 * @param form
	 * @param toolkit
	 * @return
	 */
	protected Section createIndexSection(SashForm sash, ScrolledForm form, FormToolkit toolkit) {
		Section section = createSectionWithTitle(sash, form, toolkit, "索引", true);
		tableIndex = new TableIndexViewerBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		tableIndex.setEditableControl(getEditableControl());
		tableIndex.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(tableIndex);
		addPropertyListener(tableIndex);
		getEditingDomain().getCommandStack().addCommandStackListener(tableIndex);
		
		section.setClient(tableIndex.getControl());
		return section;
	}

	/**
	 * 字段
	 * @param sash
	 * @param form
	 * @param toolkit
	 * @return
	 */
	protected Section createColumnSection(SashForm sash, ScrolledForm form, FormToolkit toolkit) {
		Section section = createSectionWithTitle(sash, form, toolkit, "字段", true);
		tableColumn = new TableColumnViewerBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		tableColumn.setEditableControl(getEditableControl());
		tableColumn.createControl(section, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(tableColumn);
		
		addPropertyListener(tableColumn);
		getEditingDomain().getCommandStack().addCommandStackListener(tableColumn);
		
		section.setClient(tableColumn.getControl());
		return section;
	}
	
	/**
	 * Create a Section With title, no description.
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param title the title
	 * @param expanded 
	 * @return the section
	 */
	private Section createSectionWithTitle(Composite parent, final ScrolledForm form, FormToolkit toolkit, String title, boolean expanded) {
		int style = ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE;
		if (expanded)
			style = style | ExpandableComposite.EXPANDED;

		Section section = toolkit.createSection(parent, style);
		section.setText(title);
		
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		return section;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		tableKey.setInput(getInfo());
		tableIndex.setInput(getInfo());
		tableColumn.setInput(getInfo());
		super.infoChange();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#dispose()
	 */
	@Override
	public void dispose() {
		getEditingDomain().removeResourceSetListener(trigger);
		removePropertyListener(tableIndex);
		getEditingDomain().getCommandStack().removeCommandStackListener(tableIndex);
		
		removePropertyListener(tableColumn);
		getEditingDomain().getCommandStack().removeCommandStackListener(tableColumn);
		
		removePropertyListener(tableKey);
		getEditingDomain().getCommandStack().removeCommandStackListener(tableKey);
		super.dispose();
	}
}
