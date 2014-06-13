/**
 * 源程序名称：NewTableComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.page.RevisionHistoryColumnsViewerBlock;
import com.hundsun.ares.studio.jres.clearinghouse.ui.page.RevisionHistoryIndexViewerBlock;
import com.hundsun.ares.studio.jres.clearinghouse.ui.page.RevisionHistoryKeyViewerBlock;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author wangxh
 *
 */
public class NewTableComposite extends ModifyActionComposite{

	//新建原表
	Button btnBtable;
	//新建历史表
	Button btnHtable;
	//选中原表
	Boolean btSelect;
	//选中历史表
	Boolean htSelect;
	
	/**
	 * 新建表
	 * @param parent
	 * @param resource
	 * @param modification
	 */
	public NewTableComposite(Composite parent, TableResourceData tableData, IARESResource resource,	Modification modification) {
		super(parent, tableData, resource, modification);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof AddTableModification){
			action = (AddTableModification) modification;
		}
		else{
			action = ChouseFactory.eINSTANCE.createAddTableModification();
			// 2012-05-15 sundl 新建表历史修订信息中保存一个字段和索引列表
			// 此处证明是第一次选择新建表，此时从TabelData中复制字段/索引数据信息
			if (tableData != null) {
				EList<TableColumn> columns = tableData.getColumns();
				Collection<TableColumn> copyedColumns = EcoreUtil.copyAll(columns);
				((AddTableModification) action).getColumns().addAll(copyedColumns);
				
				EList<TableIndex> indexes = tableData.getIndexes();
				Collection<TableIndex> copyedIndexes = EcoreUtil.copyAll(indexes);
				((AddTableModification) action).getIndexes().addAll(copyedIndexes);
				
				EList<TableKey> keys = tableData.getKeys();
				Collection<TableKey> copyedKeys = EcoreUtil.copyAll(keys);
				((AddTableModification) action).getKeys().addAll(copyedKeys);
			}
		}
		btSelect = ((AddTableModification)action).isNewSelfTable();
		htSelect = ((AddTableModification)action).isNewHistoryTable();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionComposite#creatDetailComposite(org.eclipse.swt.widgets.Composite, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatDetailComposite(Composite parent, IARESResource resource) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		
		btnBtable = new Button(composite,SWT.CHECK);
		btnBtable.setText("新建原表");
		
		btnHtable = new Button(composite,SWT.CHECK);
		btnHtable.setText("新建历史表");
		
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(btnBtable);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(btnHtable);
		
		btnBtable.setSelection(((AddTableModification)action).isNewSelfTable());
		btnHtable.setSelection(((AddTableModification)action).isNewHistoryTable());
		
		btnBtable.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				btSelect = btnBtable.getSelection();
			}
		});
		
		btnHtable.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				htSelect = btnHtable.getSelection();
			}
		});
		
		Group columnGroup = new Group(composite, SWT.NONE);
		columnGroup.setText("字段");
		GridLayoutFactory.fillDefaults().applyTo(columnGroup);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(columnGroup);
		createColumnBlock(columnGroup);
		
		Group indexGroup = new Group(composite, SWT.NONE);
		indexGroup.setText("索引");
		GridLayoutFactory.fillDefaults().applyTo(indexGroup);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(indexGroup);
		createIndexBlock(indexGroup);
		
		Group keyGroup = new Group(composite, SWT.NONE);
		keyGroup.setText("键约束");
		GridLayoutFactory.fillDefaults().applyTo(keyGroup);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(keyGroup);
		createKeyBlock(keyGroup);
	}

	private void createColumnBlock(Composite parent) {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		//adapterFactory.addAdapterFactory(new ChouseItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl();
		EditingDomain editingDomain = new TransactionalEditingDomainImpl(adapterFactory, commandStack);

		RevisionHistoryColumnsViewerBlock block = new RevisionHistoryColumnsViewerBlock(editingDomain, resource);
		block.createControl(parent, new FormToolkit(getDisplay()));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
		editingDomain.getCommandStack().addCommandStackListener(block);
		block.setInput(action);
	}
	
	private void createIndexBlock(Composite parent) {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		//adapterFactory.addAdapterFactory(new ChouseItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl();
		EditingDomain editingDomain = new TransactionalEditingDomainImpl(adapterFactory, commandStack);

		RevisionHistoryIndexViewerBlock block = new RevisionHistoryIndexViewerBlock(editingDomain, resource);
		block.createControl(parent, new FormToolkit(getDisplay()));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
		editingDomain.getCommandStack().addCommandStackListener(block);
		block.setInput(action);
	}
	
	private void createKeyBlock(Composite parent) {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		//adapterFactory.addAdapterFactory(new ChouseItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl();
		EditingDomain editingDomain = new TransactionalEditingDomainImpl(adapterFactory, commandStack);

		RevisionHistoryKeyViewerBlock block = new RevisionHistoryKeyViewerBlock(editingDomain, resource ,action);
		block.createControl(parent, new FormToolkit(getDisplay()));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
		editingDomain.getCommandStack().addCommandStackListener(block);
		block.setInput(action);
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.stock3.ui.history.ModifyActionComposite#getAction()
	 */
	@Override
	public Modification getAction() {
		AddTableModification addAction = (AddTableModification)action;
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(action);
		
		if(addAction.isNewHistoryTable() != htSelect){
			if (editingDomain != null) {
				Command command = SetCommand.create(editingDomain, action, ChousePackage.Literals.ADD_TABLE_MODIFICATION__NEW_HISTORY_TABLE, htSelect);
				editingDomain.getCommandStack().execute(command);
			} else {
				((AddTableModification)action).setNewHistoryTable(htSelect);
			}
		}
		if(addAction.isNewSelfTable() != btSelect){
			if (editingDomain != null) {
				Command command = SetCommand.create(editingDomain, action, ChousePackage.Literals.ADD_TABLE_MODIFICATION__NEW_SELF_TABLE, btSelect);
				editingDomain.getCommandStack().execute(command);
			} else {
				((AddTableModification)action).setNewSelfTable(btSelect);
			}
		}
		return action;
	};
	
	
}
