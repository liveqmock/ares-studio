/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;

/**
 * 移动到最上或者最下
 * @author liaogc
 */
public abstract class ColumnViewerMoveBuestAction extends ColumnViewerAction{

	private boolean moveTop;
	

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	protected ColumnViewerMoveBuestAction(ColumnViewer viewer,
			EditingDomain editingDomain, boolean moveTop) {
		super(viewer, editingDomain);
		this.moveTop = moveTop;
	}
	/**
	 * @return the owner
	 */
	protected abstract EObject getOwner();
	
	/**
	 * @return the reference
	 */
	protected abstract EReference getReference();
	
	/**
	 * 
	 * @param objects
	 * @return
	 */
	protected boolean isCommonContainer(EObject[] objects) {

		Collection collection = (Collection) getOwner().eGet(getReference());
		for (EObject eObject : objects) {
			if (!collection.contains(eObject)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected Command createCommand() {
		if (getOwner() == null || getReference() == null) {
			return null;
		}
		List<Object> selectedObjects = getSelectedObjects();
		if (selectedObjects.isEmpty()) {
			return null;
		}
		
//		EObject owner = objects[0].eContainer();
//		EReference reference = objects[0].eContainmentFeature();
		
		final EList<Object> list = (EList<Object>) getOwner().eGet(getReference());
		
		// 模拟操作的列表
		List<Object> tmpList = new ArrayList<Object>(list);
		
		
//		sort(tmpList,selectedObjects);
		// 强转以前先判断是否EObject类型
		for (Object obj : selectedObjects) {
			if (!(obj instanceof EObject))
				return null;
		}
		EObject[] objects = selectedObjects.toArray(new EObject[selectedObjects.size()]);
		
		if (!isCommonContainer(objects)) {
			return null;
		}
		
		boolean needChanged = false;
		if (moveTop) {
			if(0 == tmpList.indexOf(objects[0])){  //第一个选中对象不能是第一个
				return null;
			}
			
			for (int i = 0; i < objects.length; i++) {
				int index = tmpList.indexOf(objects[i]);
				Collections.swap(tmpList, index, i);
				needChanged = true;
			}
		} else {
			if((tmpList.size() - 1) == tmpList.indexOf(objects[objects.length - 1])){ //最后一个选中对象不能是最后一个
				return null;
			}
			int i = objects.length - 1;
			int j = 1;
			for (; i > -1 ; i--) {
				int index = tmpList.indexOf(objects[i]);
				if(tmpList.size()-j>0){
					Collections.swap(tmpList, index, tmpList.size()-j);
					needChanged = true;
					j++;
				}
				
			}
		}

		
		// 如果顺序无变化则不需要执行命令
		if (!needChanged) {
			return null;
		}
		CompoundCommand command = new CompoundCommand(getText());
		if (moveTop) {  //上移和下移处理不一样,上移时要先移动最上面那个，下移时要先移动最下面个
			for (Object object : selectedObjects) {
				command.append(MoveCommand.create(getEditingDomain(), getOwner(), getReference(), object, tmpList.indexOf(object)));
			}
		}else{
			for (int i = (selectedObjects.size() -1); i > -1 ; i--) {
				Object object = selectedObjects.get(i);
				command.append(MoveCommand.create(getEditingDomain(), getOwner(), getReference(), object, tmpList.indexOf(object)));
			}
		}
		
		return command.unwrap();
	}

}
