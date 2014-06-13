/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.editors.dnd;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.TreeItem;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author wangxh
 *
 */
public class MenuDropAdapter extends ViewerDropAdapter {

	private EditingDomain domain;
	private EObject dropTargetContainer;
	
	public MenuDropAdapter(TreeViewer viewer, EditingDomain domain) {
		super(viewer);
		this.domain = domain;
	}
	
    // 由于只能通过UI才能取到当前的
	protected Object determineTarget(DropTargetEvent event) {
    	dropTargetContainer = determainDropTargetContainer(event);
    	return super.determineTarget(event);
    }

    private EObject determainDropTargetContainer(DropTargetEvent event) {
			if (!(event.item instanceof TreeItem)) {
				return null;
			}
			TreeItem item = (TreeItem) event.item;
			if (item.getData() instanceof MenuItem) {
				Object data = item.getData();
				if(data instanceof MenuItem){
					MenuItem parent = (MenuItem)data;
					int location = determineLocation(event);
					if (location == LOCATION_ON)
						return parent;
					else if (location == LOCATION_AFTER || location == LOCATION_BEFORE) {
						return parent.eContainer();
					}
				}
			}
			return MetadataViewerUtil.getMetadataModel(getColumnViewer());
    }
    
	@Override
	public boolean performDrop(Object data) {
//		int operation = getCurrentOperation();
		Collection<?> elements = extractDragSource(data);
		Object target = getCurrentTarget();
		int location = getCurrentLocation();
		EStructuralFeature feature = dropTargetContainer instanceof MenuList ? 
				MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS : 
					MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS;
		EList<?> list = (EList<?>) dropTargetContainer.eGet(feature);
		int index = list.indexOf(target);
		if (target.equals(dropTargetContainer)) {
			index = 0;
		} else {
			if (location == LOCATION_AFTER)
				index++;
		}
		
		Command command = null;
		
		CompoundCommand cmd = new CompoundCommand();
		EObject owner = getOwner();
		if (dropTargetContainer == owner) {
			// 同一个container下的拖动，是移动顺序
			for (Object element : elements) {
				int curIndex = list.indexOf(element);
				if(curIndex<index){
					cmd.append(MoveCommand.create(domain, dropTargetContainer, feature, element, index-1));
				}else{
					cmd.append(MoveCommand.create(domain, dropTargetContainer, feature, element, index));
					index++;
				}
			}
		} else {
			EStructuralFeature ft = owner instanceof MenuList ? 
					MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS : 
						MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS;
			cmd.append(RemoveCommand.create(domain, owner, ft, elements));
			// 不同的container下的拖动，是移动到其他菜单号下
			cmd.append(AddCommand.create(domain, dropTargetContainer, feature, elements, index));
		}

		command = cmd;
		if (command != null)
			domain.getCommandStack().execute(command);
		
		return true;
	}
	
		
	@Override
	public boolean validateDrop(Object target, int operation, TransferData transferType) {
		Collection<?> source = getDragSource(getCurrentEvent());
		if (source == null || source.isEmpty())
			return false;
		
		if (dropTargetContainer == null)
			return false;
		
		Object[] elements = source.toArray();
		// 1. 必须只有Item或者只有分组； 必须在同一层，同一个分组下面
		if (!allUnderSameContainer(elements)) 
			return false;
		
		// 2. target是分组
//		int location = getCurrentLocation();
		if (target instanceof MenuItem) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 指定的元素是否都在当前分类下面
	 * 如果元素里面有Item，也有Category，也返回false
	 * @param elements
	 * @return
	 */
	private boolean allUnderSameContainer(Object[] elements) {
		if (elements == null || elements.length == 0)
			return false;
		
		if (elements.length == 1)
			return true;
		
		EStructuralFeature feature = getFeature(elements[0]);
		EObject owner = getOwner();
		
		@SuppressWarnings("rawtypes")
		EList list = (EList) owner.eGet(feature);
		for (Object element : elements) {
			if (!list.contains(element))
				return false;
		}
		return true;
	}
	
	protected EObject getOwner() {
		IStructuredSelection sel = (IStructuredSelection) getViewer().getSelection();
		Object obj = sel.getFirstElement();
		if(obj != null && obj instanceof MenuItem){
			return ((MenuItem)obj).eContainer();
		}
		return null;
	}
	
	private EStructuralFeature getFeature(Object element) {
		if(element instanceof MenuItem){
			MenuItem item = (MenuItem)element;
			if(item.eContainer() instanceof MenuItem){
				return MetadataPackage.Literals.MENU_ITEM__SUB_ITEMS;
			}else{
				return MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS;
			}
		}
		
		// should not happen
		return null;
	}
	
	private ColumnViewer getColumnViewer(){
		return (ColumnViewer) getViewer();
	}
	
	protected Collection<?> getDragSource(DropTargetEvent event) {
		// Check whether the current data type can be transfered locally.
		//
		LocalTransfer localTransfer = LocalTransfer.getInstance();
		if (!localTransfer.isSupportedType(event.currentDataType)) {
			// Iterate over the data types to see if there is a data type that
			// supports a local transfer.
			//
			TransferData[] dataTypes = event.dataTypes;
			for (int i = 0; i < dataTypes.length; ++i) {
				TransferData transferData = dataTypes[i];

				// If the local transfer supports this data type, switch to that
				// data type
				//
				if (localTransfer.isSupportedType(transferData)) {
					event.currentDataType = transferData;
				}
			}

			return null;
		} else {
			// Transfer the data and, if non-null, extract it.
			//
			Object object = localTransfer.nativeToJava(event.currentDataType);
			return object == null ? null : extractDragSource(object);
		}
	}

	/**
	 * This extracts a collection of dragged source objects from the given
	 * object retrieved from the transfer agent. This default implementation
	 * converts a structured selection into a collection of elements.
	 */
	protected Collection<?> extractDragSource(Object object) {
		// Transfer the data and convert the structured selection to a
		// collection of objects.
		//
		if (object instanceof IStructuredSelection) {
			List<?> list = ((IStructuredSelection) object).toList();
			return list;
		} else {
			return Collections.EMPTY_LIST;
		}
	}
}
