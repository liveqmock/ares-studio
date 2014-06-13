package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

public class AddChildMenuItemAction extends ColumnViewerAction {

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public AddChildMenuItemAction(ColumnViewer viewer,
			EditingDomain editingDomain) {
		super(viewer, editingDomain);
		setText("添加下级菜单");
		setId(IMetadataActionIDConstant.CV_ADD_CHILD_MENU_ITEM);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/addSubMenu.png"));
	}

	@Override
	protected Command createCommand() {
		MenuItem item = MetadataFactory.eINSTANCE.createMenuItem();
			if(getSelectedObjects().size() == 1){
				Object obj = getSelectedObjects().get(0);
				if(obj instanceof MenuItem){
					return AddCommand.create(getEditingDomain(), obj, MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS, item);
				}
			}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#run()
	 */
	@Override
	public void run() {
		CompoundCommand command = new CompoundCommand();
		Command addCommand = getCommand();
		if (addCommand != null) {
			if (getViewer().isCellEditorActive()) {
				getViewer().cancelEditing();
			}
			
			Object obj = getSelectedObjects().get(0);
			if(obj instanceof MenuItem){
				if(((MenuItem) obj).getFunctionProxys().size()>0){
					boolean isConfirm = MessageDialog.openConfirm(getViewer().getControl().getShell(), "确认", "菜单有功能项，添加子菜单将删除该菜单对应的所有功能项，是否继续？");
					if(isConfirm){
						Command delCommand = DeleteCommand.create(getEditingDomain(), ((MenuItem) obj).getFunctionProxys());
						command.append(delCommand);
					}else{
						clearCommand();
						return;
					}
				}
			}
			command.append(addCommand);
			
			getEditingDomain().getCommandStack().execute(command);
			clearCommand();

			// 让表格选择影响操作的对象
			Command mostRecentCommand = getEditingDomain().getCommandStack()
					.getMostRecentCommand();
			if (mostRecentCommand != null) {
				setSelectionToViewer(mostRecentCommand.getAffectedObjects());
			}
		}
	}
}
